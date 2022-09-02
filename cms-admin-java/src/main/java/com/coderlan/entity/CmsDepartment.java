package com.coderlan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class CmsDepartment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "部门名称不能为空")

    private String name;

    @NotNull(message = "上级部门不能为空")
    @TableField("parentId")
    private Integer parentId;

    @NotBlank(message = "部门领导不能为空")
    private String leader;


}
