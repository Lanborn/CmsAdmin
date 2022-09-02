package com.coderlan.service;

import com.coderlan.entity.CmsDepartment;
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
public interface CmsDepartmentService extends IService<CmsDepartment> {

    List<CmsDepartment> listDepartmentByUserId(Long userId);
}
