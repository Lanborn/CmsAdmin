package com.coderlan.service;

import com.coderlan.entity.CmsRole;
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
public interface CmsRoleService extends IService<CmsRole> {
    List<CmsRole> listRolesByUserId(Long userId);

}
