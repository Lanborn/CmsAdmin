package com.coderlan.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-29
 */
@Data

public class CmsGoodsCategoryFavor {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 分类商品收藏量
     */
    @TableField("goodsFavor")
    private Integer goodsFavor;


}
