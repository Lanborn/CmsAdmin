package com.coderlan.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderlan.common.dto.CmsMenusDto;
import com.coderlan.common.dto.CmsRoleDto;
import com.coderlan.entity.CmsMenus;
import com.coderlan.entity.CmsRole;
import com.coderlan.entity.CmsUser;
import com.coderlan.mapper.CmsMenusMapper;
import com.coderlan.mapper.CmsRoleMapper;
import com.coderlan.mapper.CmsUserMapper;
import com.coderlan.service.CmsMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderlan.service.CmsRoleService;
import com.coderlan.service.CmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@Service
public class CmsMenusServiceImpl extends ServiceImpl<CmsMenusMapper, CmsMenus> implements CmsMenusService {

    @Autowired
    CmsUserService cmsUserService;

    @Autowired
    CmsRoleService cmsRoleService;

    @Autowired
    CmsUserMapper cmsUserMapper;

    @Autowired
    CmsRoleMapper roleMapper;

    @Override
    public List<CmsMenusDto> getCurrentUserNav() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CmsUser cmsUser = cmsUserService.getByUsername(username);

        List<Long> menuIds = cmsUserMapper.getNavMenuIds(cmsUser.getId());
        List<CmsMenus> menus = this.listByIds(menuIds);
        // 转树状结构
        List<CmsMenus> menuTree = buildTreeMenu(menus);

        return convert(menuTree);

    }

    @Override
    public List<CmsMenus> tree() {
        // 获取所有菜单信息
        List<CmsMenus> cmsMenus = this.list(new QueryWrapper<CmsMenus>().orderByAsc("sort"));

        // 转成树状结构
        return buildTreeMenu(cmsMenus);
    }

    @Override
    public List<CmsMenusDto> getRoleMenuList(Long roleId) {

        List<Long> menuIds = roleMapper.getRoleMenuList(roleId);
        if (menuIds.size() > 0) {
            List<CmsMenus> menus = this.listByIds(menuIds);
            List<CmsMenus> menusList = buildTreeMenu(menus);
            return convert(menusList);

        }else {
            List<CmsMenus> nullMenus = null;
            return convert(nullMenus);
        }

    }


    private List<CmsMenusDto> convert(List<CmsMenus> menuTree) {
        List<CmsMenusDto> menuDtos = new ArrayList<>();

        if (menuTree!=null) {
            menuTree.forEach(m -> {
                CmsMenusDto dto = new CmsMenusDto();

                dto.setId(m.getId());
                dto.setUrl(m.getUrl());
                dto.setName(m.getName());
                dto.setPermission(m.getPermission());
                dto.setIcon(m.getIcon());
                dto.setSort(m.getSort());
                dto.setType(m.getType());
                dto.setParentId(m.getParentId());
                dto.setComponent(m.getComponent());

                if (m.getChildren().size() > 0) {

                    // 子节点调用当前方法进行再次转换
                    dto.setChildren(convert(m.getChildren()));
                }

                menuDtos.add(dto);
            });

            return menuDtos;

        } else {
            return null;
        }


    }

    private List<CmsMenus> buildTreeMenu(List<CmsMenus> menus) {
        List<CmsMenus> finalMenus = new ArrayList<>();

        // 先各自寻找到各自的孩子
        for (CmsMenus menu : menus) {

            for (CmsMenus e : menus) {
                if (menu.getId().equals(e.getParentId())) {
                    menu.getChildren().add(e);
                }
            }

            // 提取出父节点
            if (menu.getParentId() == 0L) {
                finalMenus.add(menu);
            }
        }

        return finalMenus;
    }
}
