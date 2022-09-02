package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsDepartment;
import com.coderlan.entity.CmsGoods;
import com.coderlan.entity.CmsGoodsAddressSale;
import com.coderlan.service.CmsGoodsAddressSaleService;
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
@RequestMapping("/cms/goods/address/sale")
public class CmsGoodsAddressSaleController extends BaseController {

    @Autowired
    CmsGoodsAddressSaleService cmsGoodsAddressSaleService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('system:goods:list')")
    public Result list(String name) {

        Page<CmsGoodsAddressSale> pageData = cmsGoodsAddressSaleService.page(getPage(), new QueryWrapper<CmsGoodsAddressSale>()
                .like(StrUtil.isNotBlank(name), "name", name));
        return Result.succ(pageData);
    }
}
