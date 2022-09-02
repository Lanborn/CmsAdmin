package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.CmsBugIssue;
import com.coderlan.entity.CmsCategory;
import com.coderlan.mapper.CmsBugIssueMapper;
import com.coderlan.mapper.CmsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLan
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/cms/bug/issue")
public class CmsBugIssueController extends BaseController {
    @Autowired
    CmsBugIssueMapper bugIssueMapper;


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:issue:list')")
    public Result list(String title) {

        Page<CmsBugIssue> pageData = cmsBugIssueService.page(getPage(), new QueryWrapper<CmsBugIssue>()
                .like(StrUtil.isNotBlank(title), "title", title));

        return Result.succ(pageData);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:issue:create')")
    public Result save(@Validated @RequestBody CmsBugIssue cmsBugIssue) {

        cmsBugIssue.setCreateTime(LocalDateTime.now());
        cmsBugIssueService.save(cmsBugIssue);
        return Result.succ(cmsBugIssue);

    }
}
