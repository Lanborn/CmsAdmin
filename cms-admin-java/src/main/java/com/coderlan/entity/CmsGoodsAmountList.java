package com.coderlan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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

public class CmsGoodsAmountList {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String tips;

    private String subtitle;

    private Integer number1;

    private Integer number2;

    private String amount;


}
