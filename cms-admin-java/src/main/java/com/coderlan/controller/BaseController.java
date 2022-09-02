package com.coderlan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.service.*;
import com.coderlan.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    HttpServletRequest req;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    CmsBugIssueService cmsBugIssueService;

    @Autowired
    CmsUserService cmsUserService;

    @Autowired
    CmsRoleService cmsRoleService;

    @Autowired
    CmsMenusService cmsMenusService;

    @Autowired
    CmsDepartmentService cmsDepartmentService;

    @Autowired
    CmsGoodsService cmsGoodsService;

    @Autowired
    CmsCategoryService cmsCategoryService;


    @Autowired
    CmsUserRoleService cmsUserRoleService;

    @Autowired
    CmsUserDepartmentService cmsUserDepartmentService;

    @Autowired
    CmsRoleMenusService cmsRoleMenusService;


    /**
     * 获取页面
     * @return
     */
    public Page getPage() {
        int current = ServletRequestUtils.getIntParameter(req, "current", 1);
        int size = ServletRequestUtils.getIntParameter(req, "size", 10);

        return new Page(current, size);
    }
}
