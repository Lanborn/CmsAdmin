package com.coderlan.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Const;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.*;
import com.coderlan.mapper.CmsDepartmentMapper;
import com.coderlan.mapper.CmsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@RestController
@RequestMapping("/cms/department")
public class CmsDepartmentController extends BaseController {

    @Autowired
    CmsDepartmentMapper departmentMapper;

    @Autowired
    CmsUserMapper userMapper;

    @PreAuthorize("hasAuthority('system:department:list')")
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {

        CmsUser cmsUser = cmsUserService.getById(id);
        Assert.notNull(cmsUser, "找不到该管理员");

        CmsDepartment cmsDepartments = cmsDepartmentService.getById(id);
        List<CmsRole> roles = cmsRoleService.listRolesByUserId(id);

        cmsUser.setCmsRoles(roles);
        cmsUser.setCmsDepartments(cmsDepartments);
        return Result.succ(cmsUser);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:department:list')")
    public Result list(String name) {

        Page<CmsDepartment> pageData = cmsDepartmentService.page(getPage(), new QueryWrapper<CmsDepartment>()
                .like(StrUtil.isNotBlank(name), "name", name));

        return Result.succ(pageData);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:department:create')")
    public Result save(@Validated @RequestBody CmsDepartment cmsDepartment) {
        CmsDepartment department = departmentMapper.selectOne(new QueryWrapper<CmsDepartment>().eq("name",cmsDepartment.getName()));
        CmsUser user = userMapper.selectOne(new QueryWrapper<CmsUser>().eq("username",cmsDepartment.getLeader()));

        if (!ObjectUtils.isEmpty(department)) {
            return Result.fail("部门已存在");
        } else if (ObjectUtils.isEmpty(user)){
            return Result.fail("领导人不存在");
        } else{
            cmsDepartment.setCreateTime(LocalDateTime.now());
            cmsDepartment.setEnable(Const.STATUS_ON);
            cmsDepartmentService.save(cmsDepartment);
            return Result.succ(cmsDepartment);
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:department:update')")
    public Result update(@Validated @RequestBody CmsDepartment cmsDepartment) {

        CmsUser user = userMapper.selectOne(new QueryWrapper<CmsUser>().eq("username",cmsDepartment.getLeader()));

        if (ObjectUtils.isEmpty(user) ) {
            return Result.fail("领导人不存在");
        }
        cmsDepartment.setUpdateTime(LocalDateTime.now());

        cmsDepartmentService.updateById(cmsDepartment);
        return Result.succ(cmsDepartment);

    }

    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:department:delete')")
    public Result delete(@RequestBody Long[] ids) {

        cmsDepartmentService.removeByIds(Arrays.asList(ids));
        cmsUserDepartmentService.remove(new QueryWrapper<CmsUserDepartment>().in("department_id", ids));

        return Result.succ("删除部门成功");
    }


}
