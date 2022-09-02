package com.coderlan.service;

import com.coderlan.entity.CmsRole;
import com.coderlan.entity.CmsRoleMenus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
public interface CmsRoleMenusService extends IService<CmsRoleMenus> {
    void updateRoleMenus(CmsRole role);
}
