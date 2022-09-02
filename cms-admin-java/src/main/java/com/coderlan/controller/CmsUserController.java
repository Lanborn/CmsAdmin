package com.coderlan.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderlan.common.lang.Const;
import com.coderlan.common.lang.Result;
import com.coderlan.entity.*;
import com.coderlan.mapper.CmsDepartmentMapper;
import com.coderlan.mapper.CmsUserDepartmentMapper;
import com.coderlan.mapper.CmsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@RestController
@RequestMapping("/cms/users")
public class CmsUserController extends BaseController {

    @Autowired
    CmsUserMapper userMapper;

    @Autowired
    CmsUserDepartmentMapper userDepartmentMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('system:users:list')")
    public Result info(@PathVariable("id") Long id) {

        CmsUser cmsUser = cmsUserService.getById(id);
        Assert.notNull(cmsUser, "找不到该管理员");

        List<CmsRole> roles = cmsRoleService.listRolesByUserId(id);
        cmsUser.setDepartmentId(userMapper.getUserDepartmentId(cmsUser.getId()));
        cmsUser.setCmsDepartments(null);
        cmsUser.setCmsRoles(roles);
        return Result.succ(cmsUser);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:users:list')")
    public Result list(CmsUser cmsUser) {

        Page<CmsUser> pageData = cmsUserService.page(getPage(), new QueryWrapper<CmsUser>()
                .like(StrUtil.isNotBlank(cmsUser.getUsername()), "username", cmsUser.getUsername()));

        pageData.getRecords().forEach(u -> {
            u.setDepartmentId(userMapper.getUserDepartmentId(u.getId()));
            u.setCmsDepartments(null);
            u.setCmsRoles(cmsRoleService.listRolesByUserId(u.getId()));
            u.setRoleId(userMapper.getUserRoleId(u.getId()));
        });

        return Result.succ(pageData);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('system:users:create')")
    public Result save(@Validated @RequestBody CmsUser cmsUser) {

        System.out.println(cmsUser);

        CmsUser user = userMapper.selectOne(new QueryWrapper<CmsUser>().eq("username", cmsUser.getUsername()));

        if (!ObjectUtils.isEmpty(user)) {
            return Result.fail("用户名重复");
        }

        cmsUser.setCreateTime(LocalDateTime.now());
        cmsUser.setEnable(Const.STATUS_ON);

        // 默认密码
        String password = passwordEncoder.encode(Const.DEFULT_PASSWORD);
        cmsUser.setPassword(password);

        // 默认头像
        cmsUser.setAvatar(Const.DEFULT_AVATAR);

        cmsUserService.save(cmsUser);
        // 分配角色和部门
        CmsUserDepartment userDepartment = new CmsUserDepartment();
        CmsUserRole userRole = new CmsUserRole();
        userDepartment.setUserId(cmsUser.getId());
        userRole.setUserId(cmsUser.getId());
        userDepartment.setDepartmentId(cmsUser.getDepartmentId());
        userRole.setRoleId(cmsUser.getRoleId());
        cmsUserDepartmentService.save(userDepartment);
        cmsUserRoleService.save(userRole);

        return Result.succ(cmsUser);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:users:update')")
    public Result update(@Validated @RequestBody CmsUser cmsUser) {
        System.out.println(cmsUser);

        cmsUser.setUpdateTime(LocalDateTime.now());

        // 更新部门和角色表

        List<CmsUserRole> userRoles = new ArrayList<>();

        CmsUserRole cmsUserRole = new CmsUserRole();
        cmsUserRole.setRoleId(cmsUser.getRoleId());
        cmsUserRole.setUserId(cmsUser.getId());
        userRoles.add(cmsUserRole);

        // 更新部门
        userDepartmentMapper.updateDepartmentByUserId(cmsUser.getId(), cmsUser.getDepartmentId());

        // 清空缓存
        cmsUserRoleService.remove(new QueryWrapper<CmsUserRole>().eq("user_id", cmsUser.getId()));
        cmsUserRoleService.saveBatch(userRoles);
        cmsUserService.clearUserAuthorityInfo(cmsUser.getUsername());


        cmsUserService.updateById(cmsUser);
        return Result.succ(cmsUser);

    }

    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:users:delete')")
    public Result delete(@RequestBody Long[] ids) {

        cmsUserService.removeByIds(Arrays.asList(ids));
        cmsUserRoleService.remove(new QueryWrapper<CmsUserRole>().in("user_id", ids));
        cmsUserDepartmentService.remove(new QueryWrapper<CmsUserDepartment>().in("user_id", ids));

        return Result.succ("删除用户成功");


    }

    // 分配角色
    @Transactional
    @PostMapping("/role/{userId}")
    @PreAuthorize("hasAuthority('system:users:role')")
    public Result rolePerm(@PathVariable("userId") Long userId, @RequestBody Long[] roleIds) {

        List<CmsUserRole> userRoles = new ArrayList<>();

        Arrays.stream(roleIds).forEach(r -> {
            CmsUserRole cmsUserRole = new CmsUserRole();
            cmsUserRole.setRoleId(r);
            cmsUserRole.setUserId(userId);

            userRoles.add(cmsUserRole);
        });

        cmsUserRoleService.remove(new QueryWrapper<CmsUserRole>().eq("user_id", userId));
        cmsUserRoleService.saveBatch(userRoles);

        // 删除缓存
        CmsUser cmsUser = cmsUserService.getById(userId);
        cmsUserService.clearUserAuthorityInfo(cmsUser.getUsername());

        return Result.succ("修改用户成功");
    }

    // 分配部门
    @Transactional
    @PostMapping("/department/{userId}")
    @PreAuthorize("hasAuthority('system:department:role')")
    public Result DepartmentPerm(@PathVariable("userId") Long userId, @RequestBody Long[] departmentIds) {

        List<CmsUserDepartment> userDepartments = new ArrayList<>();

        Arrays.stream(departmentIds).forEach(r -> {
            CmsUserDepartment cmsUserDepartment = new CmsUserDepartment();

            cmsUserDepartment.setDepartmentId(r);

            cmsUserDepartment.setUserId(userId);

            userDepartments.add(cmsUserDepartment);
        });

        cmsUserDepartmentService.remove(new QueryWrapper<CmsUserDepartment>().eq("user_id", userId));
        cmsUserDepartmentService.saveBatch(userDepartments);


        return Result.succ("修改用户成功");
    }

}
