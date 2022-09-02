package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Const;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsCategory;
import com.coderlan.entity.CmsGoods;
import com.coderlan.mapper.CmsCategoryMapper;
import com.coderlan.mapper.CmsGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/cms/category")
public class CmsCategoryController extends BaseController {

    @Autowired
    CmsCategoryMapper categoryMapper;


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:category:list')")
    public Result list(String name) {

        Page<CmsCategory> pageData = cmsCategoryService.page(getPage(), new QueryWrapper<CmsCategory>()
                .like(StrUtil.isNotBlank(name), "name", name));

        return Result.succ(pageData);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:category:create')")
    public Result save(@Validated @RequestBody CmsCategory cmsCategory) {
        CmsCategory category = categoryMapper.selectOne(new QueryWrapper<CmsCategory>().eq("name",cmsCategory.getName()));

        if (!ObjectUtils.isEmpty(category)) {
            return Result.fail("类别已存在");
        } else{
            cmsCategory.setCreateTime(LocalDateTime.now());
            cmsCategoryService.save(cmsCategory);
            return Result.succ(cmsCategory);
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:category:update')")
    public Result update(@Validated @RequestBody CmsCategory cmsCategory) {
        CmsCategory category = categoryMapper.selectOne(new QueryWrapper<CmsCategory>().eq("name",cmsCategory.getName()));

        if (!ObjectUtils.isEmpty(category)) {
            return Result.fail("类别名重复");
        } else{
            cmsCategory.setUpdateTime(LocalDateTime.now());

            cmsCategoryService.updateById(cmsCategory);
            return Result.succ(cmsCategory);
        }
    }

    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:category:delete')")
    public Result delete(@RequestBody Long[] ids) {

        cmsCategoryService.removeByIds(Arrays.asList(ids));

        return Result.succ("删除类别成功");
    }
}
