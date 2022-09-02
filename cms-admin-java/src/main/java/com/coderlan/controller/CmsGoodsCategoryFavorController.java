package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsGoodsCategoryCount;
import com.coderlan.entity.CmsGoodsCategoryFavor;
import com.coderlan.service.CmsGoodsCategoryCountService;
import com.coderlan.service.CmsGoodsCategoryFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-29
 */
@RestController
@RequestMapping("/cms/goods/category/favor")
public class CmsGoodsCategoryFavorController extends BaseController {
    @Autowired
    CmsGoodsCategoryFavorService cmsGoodsCategoryFavorService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('system:goods:list')")
    public Result list(String name) {

        Page<CmsGoodsCategoryFavor> pageData = cmsGoodsCategoryFavorService.page(getPage(), new QueryWrapper<CmsGoodsCategoryFavor>()
                .like(StrUtil.isNotBlank(name), "name", name));

        return Result.succ(pageData);
    }
}
