package com.coderlan.service;

import com.coderlan.common.dto.CmsMenusDto;
import com.coderlan.common.dto.CmsRoleDto;
import com.coderlan.entity.CmsMenus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
public interface CmsMenusService extends IService<CmsMenus> {

    List<CmsMenusDto> getCurrentUserNav();

    List<CmsMenus> tree();

    List<CmsMenusDto> getRoleMenuList(Long roleId);

}
