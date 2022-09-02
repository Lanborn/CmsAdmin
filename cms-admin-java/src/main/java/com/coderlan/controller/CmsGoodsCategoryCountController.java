package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsGoodsAddressSale;
import com.coderlan.entity.CmsGoodsCategoryCount;
import com.coderlan.service.CmsGoodsCategoryCountService;
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
@RequestMapping("/cms/goods/category/count")
public class CmsGoodsCategoryCountController extends BaseController {

    @Autowired
    CmsGoodsCategoryCountService cmsGoodsCategoryCountService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('system:goods:list')")
    public Result list(String name) {

        Page<CmsGoodsCategoryCount> pageData = cmsGoodsCategoryCountService.page(getPage(), new QueryWrapper<CmsGoodsCategoryCount>()
                .like(StrUtil.isNotBlank(name), "name", name));

        return Result.succ(pageData);
    }
}
