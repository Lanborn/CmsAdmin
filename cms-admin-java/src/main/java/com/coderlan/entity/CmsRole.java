package com.coderlan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.coderlan.common.dto.CmsMenusDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class CmsRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "角色名称不能为空")

    private String name;

    @NotBlank(message = "角色编码不能为空")
    private String code;

    private String intro;

    @TableField(value = "menuList")
    private List<CmsMenusDto> menulist;

    @TableField(exist = false)
    private List<Long> menuIds = new ArrayList<>();

}
