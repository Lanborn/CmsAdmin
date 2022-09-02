package com.coderlan.mapper;

import com.coderlan.entity.CmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@Repository

public interface CmsRoleMapper extends BaseMapper<CmsRole> {

    List<Long> getRoleMenuList(Long roleId);

}
