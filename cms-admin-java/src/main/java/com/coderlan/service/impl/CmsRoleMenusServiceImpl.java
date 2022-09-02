package com.coderlan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderlan.entity.CmsRole;
import com.coderlan.entity.CmsRoleMenus;
import com.coderlan.mapper.CmsRoleMenusMapper;
import com.coderlan.service.CmsRoleMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderlan.service.CmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CmsRoleMenusServiceImpl extends ServiceImpl<CmsRoleMenusMapper, CmsRoleMenus> implements CmsRoleMenusService {

    @Autowired
    CmsRoleMenusService cmsRoleMenusService;
    @Autowired
    CmsUserService cmsUserService;

    @Override
    public void updateRoleMenus(CmsRole role) {
        List<CmsRoleMenus> cmsRoleMenus = new ArrayList<>();

        for (Long menuId:
                role.getMenuIds()) {
            CmsRoleMenus roleMenus = new CmsRoleMenus();
            roleMenus.setMenuId(menuId);
            roleMenus.setRoleId(role.getId());
            cmsRoleMenus.add(roleMenus);
        }

        cmsRoleMenusService.saveBatch(cmsRoleMenus);

        if (cmsRoleMenus.size()>0) {
            // 先删除原来的记录，再保存新的
            cmsRoleMenusService.remove(new QueryWrapper<CmsRoleMenus>().eq("role_id", role.getId()));
            cmsRoleMenusService.saveBatch(cmsRoleMenus);

            // 删除缓存
            cmsUserService.clearUserAuthorityInfoByRoleId(role.getId());
        }

    }
}
