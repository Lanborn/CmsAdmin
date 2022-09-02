package com.coderlan.mapper;

import com.coderlan.entity.CmsUserDepartment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@Repository

public interface CmsUserDepartmentMapper extends BaseMapper<CmsUserDepartment> {
    // 更新用户部门信息
    void updateDepartmentByUserId(Long userId, Long DepartmentId);
}
