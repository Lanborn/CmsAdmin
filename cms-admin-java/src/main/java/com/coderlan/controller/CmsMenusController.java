package com.coderlan.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderlan.common.dto.CmsMenusDto;
import com.coderlan.common.lang.Const;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsMenus;
import com.coderlan.entity.CmsRoleMenus;
import com.coderlan.entity.CmsUser;
import com.coderlan.mapper.CmsMenusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@RestController
@RequestMapping("/cms/menu")
public class CmsMenusController extends BaseController {

    @Autowired
    CmsMenusMapper menusMapper;

    /**
     * 用户当前用户的菜单和权限信息
     * @param principal
     * @return
     */
    @GetMapping("/nav")
    public Result nav (Principal principal) {
        CmsUser cmsUser = cmsUserService.getByUsername(principal.getName());
        // 获取权限信息
        String authorityInfo = cmsUserService.getUserAuthorityInfo(cmsUser.getId());// ROLE_admin,ROLE_normal,cms:user:list,....
        String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

        // 获取导航栏信息
        List<CmsMenusDto> navs = cmsMenusService.getCurrentUserNav();

        return Result.succ(MapUtil.builder()
                .put("authoritys", authorityInfoArray)
                .put("nav", navs)
                .map()
        );
    }
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('system:menu:list')")
    public Result info(@PathVariable(name = "id") Long id) {
        return Result.succ(cmsMenusService.getById(id));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:menu:list')")
    public Result list() {

        List<CmsMenus> menus = cmsMenusService.tree();

        return Result.succ(menus);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:menu:create')")
    public Result save(@Validated @RequestBody CmsMenus cmsMenus) {

        CmsMenus menus = menusMapper.selectOne(new QueryWrapper<CmsMenus>().eq("name",cmsMenus.getName()));
        if(!ObjectUtils.isEmpty(menus)) {
            return Result.fail("该菜单已存在");
        }

        cmsMenus.setEnable(Const.STATUS_ON);
        cmsMenus.setCreateTime(LocalDateTime.now());
        System.out.println(cmsMenus);

        cmsMenusService.save(cmsMenus);
        return Result.succ(cmsMenus);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:menu:update')")
    public Result update(@Validated @RequestBody CmsMenus cmsMenus) {

        cmsMenus.setUpdateTime(LocalDateTime.now());

        cmsMenusService.updateById(cmsMenus);

        // 清除所有与该菜单相关的权限缓存
        cmsUserService.clearUserAuthorityInfoByMenuId(cmsMenus.getId());
        return Result.succ(cmsMenus);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:menu:delete')")
    public Result delete(@RequestBody Long id) {

        int count = cmsMenusService.count(new QueryWrapper<CmsMenus>().eq("parentId", id));

        if (count > 0) {
            return Result.fail("请先删除子菜单");
        }

        // 清除所有与该菜单相关的权限缓存
        cmsUserService.clearUserAuthorityInfoByMenuId(id);

        cmsMenusService.removeById(id);

        // 同步删除中间关联表
        cmsRoleMenusService.remove(new QueryWrapper<CmsRoleMenus>().eq("menu_id", id));
        return Result.succ("");
    }

}
