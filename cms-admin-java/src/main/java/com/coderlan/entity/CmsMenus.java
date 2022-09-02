package com.coderlan.entity;


import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class CmsMenus extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 1: 目录 2：菜单 3：按钮
     */
    @NotNull(message = "菜单类型不能为空")

    private Integer type;

    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String permission;

    private String component;

    /**
     * 0: 启用，1：弃用
     */
    private Integer enable;

    private String icon;

    /**
     * 排序
     */
    @TableField("sort")
    private String sort;

    @TableField(exist = false)
    private List<CmsMenus> children = new ArrayList<>();

    @TableField("parentId")
    @NotNull(message = "上级菜单不能为空")
    private Long parentId;


}
