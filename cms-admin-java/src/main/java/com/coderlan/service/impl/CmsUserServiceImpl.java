package com.coderlan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderlan.entity.CmsMenus;
import com.coderlan.entity.CmsRole;
import com.coderlan.entity.CmsUser;
import com.coderlan.mapper.CmsUserMapper;
import com.coderlan.service.CmsMenusService;
import com.coderlan.service.CmsRoleService;
import com.coderlan.service.CmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderlan.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@Service
public class CmsUserServiceImpl extends ServiceImpl<CmsUserMapper, CmsUser> implements CmsUserService {

    @Autowired
    CmsRoleService cmsRoleService;

    @Autowired
    CmsUserMapper cmsUserMapper;

    @Autowired
    CmsMenusService cmsMenusService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public CmsUser getByUsername(String username) {

        return getOne(new QueryWrapper<CmsUser>().eq("username", username));
    }

    @Override
    public String getUserAuthorityInfo(Long userId) {

        CmsUser cmsUser = cmsUserMapper.selectById(userId);

        // ROLE_admin,ROLE_normal,cms:user:list,....
        String authority = "";

        if (redisUtil.hasKey("GrantedAuthority:" + cmsUser.getUsername())) {
            authority = (String) redisUtil.get("GrantedAuthority:" + cmsUser.getUsername());

        } else {
            // 获取角色编码
            List<CmsRole> roles = cmsRoleService.list(new QueryWrapper<CmsRole>()
                    .inSql("id", "select role_id from cms_user_role where user_id = " + userId));

            if (roles.size() > 0) {
                String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
                authority = roleCodes.concat(",");
            }

            // 获取菜单操作编码
            List<Long> menuIds = cmsUserMapper.getNavMenuIds(userId);
            if (menuIds.size() > 0) {

                List<CmsMenus> menus = cmsMenusService.listByIds(menuIds);
                String menuPerms = menus.stream().map(m -> m.getPermission()).collect(Collectors.joining(","));

                authority = authority.concat(menuPerms);
            }

            redisUtil.set("GrantedAuthority:" + cmsUser.getUsername(), authority, 60 * 60);
        }

        return authority;
    }

    @Override
    public void clearUserAuthorityInfo(String username) {
        redisUtil.del("GrantedAuthority:" + username);
    }

    @Override
    public void clearUserAuthorityInfoByRoleId(Long roleId) {

        List<CmsUser> cmsUsers = this.list(new QueryWrapper<CmsUser>()
                .inSql("id", "select user_id from cms_user_role where role_id = " + roleId));

        cmsUsers.forEach(u -> {
            this.clearUserAuthorityInfo(u.getUsername());
        });
    }

    @Override
    public void clearUserAuthorityInfoByMenuId(Long menuId) {
        List<CmsUser> cmsUsers = cmsUserMapper.listByMenuId(menuId);

        cmsUsers.forEach(u -> {
            this.clearUserAuthorityInfo(u.getUsername());
        });
    }
}
