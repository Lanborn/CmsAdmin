package com.coderlan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderlan.entity.CmsDepartment;
import com.coderlan.entity.CmsRole;
import com.coderlan.entity.CmsUserDepartment;
import com.coderlan.mapper.CmsUserDepartmentMapper;
import com.coderlan.service.CmsUserDepartmentService;
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
public class CmsUserDepartmentServiceImpl extends ServiceImpl<CmsUserDepartmentMapper, CmsUserDepartment> implements CmsUserDepartmentService {
}
