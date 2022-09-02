package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Const;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.*;
import com.coderlan.mapper.CmsGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/cms/goods")
public class CmsGoodsController extends BaseController {

    @Autowired
    CmsGoodsMapper cmsGoodsMapper;


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:goods:list')")
    public Result list(String name) {

        Page<CmsGoods> pageData = cmsGoodsService.page(getPage(), new QueryWrapper<CmsGoods>()
                .like(StrUtil.isNotBlank(name), "name", name));


        return Result.succ(pageData);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:goods:create')")
    public Result save(@Validated @RequestBody CmsGoods cmsGoods) {
        System.out.println(cmsGoods);
        CmsGoods goods = cmsGoodsMapper.selectOne(new QueryWrapper<CmsGoods>().eq("name",cmsGoods.getName()));

        if (!ObjectUtils.isEmpty(goods)) {
            return Result.fail("商品已存在");
        } else{
            cmsGoods.setCreateTime(LocalDateTime.now());
            cmsGoods.setStatus(String.valueOf(Const.STATUS_ON));
            cmsGoodsService.save(cmsGoods);
            return Result.succ(cmsGoods);
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:goods:update')")
    public Result update(@Validated @RequestBody CmsGoods cmsGoods) {
        CmsGoods goods = cmsGoodsMapper.selectOne(new QueryWrapper<CmsGoods>().eq("name",cmsGoods.getName()));

        if (!ObjectUtils.isEmpty(goods)) {
            return Result.fail("商品名重复");
        } else{
            cmsGoods.setUpdateTime(LocalDateTime.now());
            cmsGoodsService.updateById(cmsGoods);
            return Result.succ(cmsGoods);
        }
    }

    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:goods:delete')")
    public Result delete(@RequestBody Long[] ids) {

        cmsGoodsService.removeByIds(Arrays.asList(ids));

        return Result.succ("删除商品成功");
    }

}
