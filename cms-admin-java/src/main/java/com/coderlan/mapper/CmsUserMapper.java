package com.coderlan.mapper;

import com.coderlan.entity.CmsUser;
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
public interface CmsUserMapper extends BaseMapper<CmsUser> {

    List<Long>  getNavMenuIds(Long userId);

    List<CmsUser> listByMenuId(Long menuId);

    Long getUserDepartmentId(Long userId);

    Long getUserRoleId(Long userId);


}
