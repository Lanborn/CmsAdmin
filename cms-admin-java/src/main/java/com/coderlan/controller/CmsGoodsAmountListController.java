package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsGoodsAmountList;
import com.coderlan.entity.CmsGoodsSaleTop10;
import com.coderlan.service.CmsGoodsAmountListService;
import com.coderlan.service.CmsGoodsSaleTop10Service;
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
@RequestMapping("/cms/goods/amount/list")
public class CmsGoodsAmountListController extends BaseController {
    @Autowired
    CmsGoodsAmountListService cmsGoodsAmountListService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('system:goods:list')")
    public Result list(String name) {

        Page<CmsGoodsAmountList> pageData = cmsGoodsAmountListService.page(getPage(), new QueryWrapper<CmsGoodsAmountList>()
                .like(StrUtil.isNotBlank(name), "name", name));

        return Result.succ(pageData);
    }
}
