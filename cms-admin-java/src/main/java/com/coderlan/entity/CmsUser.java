package com.coderlan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不能为空")
    private String username;

    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    private String avatar;

    /**
     * 邮箱地址
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 电话号码
     */
    private String cellphone;

    @TableField(exist = false)
    private List<CmsRole> cmsRoles = new ArrayList<>();

    @TableField(exist = false)
    private Long  roleId;

    @TableField(exist = false)
    private CmsDepartment cmsDepartments = new CmsDepartment();

    @TableField(exist = false)
    private Long departmentId;

}
