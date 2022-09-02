package com.coderlan.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsGoodsCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long goodsId;

    private Long categoryId;


}
