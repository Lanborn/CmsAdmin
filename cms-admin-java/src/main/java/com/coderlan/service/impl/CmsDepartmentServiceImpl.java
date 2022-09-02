package com.coderlan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderlan.entity.CmsDepartment;
import com.coderlan.entity.CmsRole;
import com.coderlan.mapper.CmsDepartmentMapper;
import com.coderlan.service.CmsDepartmentService;
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
public class CmsDepartmentServiceImpl extends ServiceImpl<CmsDepartmentMapper, CmsDepartment> implements CmsDepartmentService {

    @Override
    public List<CmsDepartment> listDepartmentByUserId(Long userId) {
        List<CmsDepartment> cmsDepartments = this.list(new QueryWrapper<CmsDepartment>()
                .inSql("id", "select department_id from cms_user_department where user_id = " + userId));

        return cmsDepartments;
    }
}
