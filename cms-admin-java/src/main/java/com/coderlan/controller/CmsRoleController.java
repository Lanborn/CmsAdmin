package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.dto.CmsMenusDto;
import com.coderlan.common.dto.CmsRoleDto;
import com.coderlan.common.lang.Const;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsMenus;
import com.coderlan.entity.CmsRole;
import com.coderlan.entity.CmsRoleMenus;
import com.coderlan.entity.CmsUserRole;
import com.coderlan.mapper.CmsRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@RestController
@RequestMapping("/cms/role")
public class CmsRoleController extends BaseController {

    @Autowired
    CmsRoleMapper roleMapper;

    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {

        CmsRole cmsRole = cmsRoleService.getById(id);

        // 获取角色相关联的菜单id
        List<CmsRoleMenus> roleMenus = cmsRoleMenusService.list(new QueryWrapper<CmsRoleMenus>().eq("role_id", id));
        List<Long> menuIds = roleMenus.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
        List<CmsMenusDto> menusList = cmsMenusService.getRoleMenuList(id);

        cmsRole.setMenuIds(menuIds);
        cmsRole.setMenulist(menusList);
        return Result.succ(cmsRole);
    }

    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/list")
    public Result list(String name) {

        Page<CmsRole> pageData = cmsRoleService.page(getPage(),
                new QueryWrapper<CmsRole>()
                        .like(StrUtil.isNotBlank(name), "name", name)
        );

        for (CmsRole roleInfo:pageData.getRecords()) {

            // 获取角色相关联的菜单id
            List<CmsRoleMenus> roleMenus = cmsRoleMenusService.list(new QueryWrapper<CmsRoleMenus>().eq("role_id", roleInfo.getId()));
            List<Long> menuIds = roleMenus.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
            List<CmsMenusDto> menusList = cmsMenusService.getRoleMenuList(roleInfo.getId());
            roleInfo.setMenulist(menusList);
            roleInfo.setMenuIds(menuIds);
        }


        return Result.succ(pageData);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:role:create')")
    public Result save(@Validated @RequestBody CmsRole cmsRole) {

        System.out.println(cmsRole);

        CmsRole role = roleMapper.selectOne(new QueryWrapper<CmsRole>().eq("name", cmsRole.getName()));

        if(!ObjectUtils.isEmpty(role)) {
            return Result.fail("该角色已存在");
        }

        cmsRole.setCreateTime(LocalDateTime.now());
        cmsRole.setEnable(Const.STATUS_ON);

        cmsRoleService.save(cmsRole);

        cmsRoleMenusService.updateRoleMenus(cmsRole);

        return Result.succ(cmsRole);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:role:update')")
    public Result update(@Validated @RequestBody CmsRole cmsRole) {

        System.out.println(cmsRole.getMenulist());

        cmsRole.setUpdateTime(LocalDateTime.now());

        cmsRoleMenusService.updateRoleMenus(cmsRole);

        cmsRoleService.updateById(cmsRole);

        return Result.succ(cmsRole);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:role:delete')")
    @Transactional
    public Result info(@RequestBody Long[] ids) {

        System.out.println(ids);

        cmsRoleService.removeByIds(Arrays.asList(ids));

        // 删除中间表
        cmsUserRoleService.remove(new QueryWrapper<CmsUserRole>().in("role_id", ids));
        cmsRoleMenusService.remove(new QueryWrapper<CmsRoleMenus>().in("role_id", ids));

        // 缓存同步删除
        Arrays.stream(ids).forEach(id -> {
            // 更新缓存
            cmsUserService.clearUserAuthorityInfoByRoleId(id);
        });

        return Result.succ("删除角色成功");
    }

    // 分配权限
    @Transactional
    @PostMapping("/perm/{roleId}")
    @PreAuthorize("hasAuthority('system:role:perm')")
    public Result info(@PathVariable("roleId") Long roleId, @RequestBody Long[] menuIds) {

        List<CmsRoleMenus> cmsRoleMenus = new ArrayList<>();

        Arrays.stream(menuIds).forEach(menuId -> {
            CmsRoleMenus roleMenu = new CmsRoleMenus();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);

            cmsRoleMenus.add(roleMenu);
        });

        // 先删除原来的记录，再保存新的
        cmsRoleMenusService.remove(new QueryWrapper<CmsRoleMenus>().eq("role_id", roleId));
        cmsRoleMenusService.saveBatch(cmsRoleMenus);

        // 删除缓存
        cmsUserService.clearUserAuthorityInfoByRoleId(roleId);

        return Result.succ(menuIds);
    }
}
