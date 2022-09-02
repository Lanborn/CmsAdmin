package com.coderlan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderlan.entity.CmsRole;
import com.coderlan.mapper.CmsRoleMapper;
import com.coderlan.service.CmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class CmsRoleServiceImpl extends ServiceImpl<CmsRoleMapper, CmsRole> implements CmsRoleService {

    @Override
    public List<CmsRole> listRolesByUserId(Long userId) {
        List<CmsRole> cmsRoles = this.list(new QueryWrapper<CmsRole>()
                .inSql("id", "select role_id from cms_user_role where user_id = " + userId));

        return cmsRoles;
    }
}
