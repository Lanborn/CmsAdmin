package com.coderlan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author CoderLan
 * @since 2022-08-26
 */
@Data

public class CmsGoods {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    @TableField(value = "oldPrice")
    private String oldPrice;

    @TableField(value = "newPrice")
    private String newPrice;

    private String descption;

    /**
     * 0: 可用
     */
    private String status;

    @TableField("imgUrl")
    private String imgUrl;

    @TableField("inventoryCount")

    private Integer inventoryCount;

    /**
     * 销售数量
     */
    @TableField("saleCount")

    private Integer saleCount;

    /**
     * 收藏数量
     */
    @TableField("favorCount")

    private Integer favorCount;

    private String address;

    @TableField("categoryId")

    private Integer categoryId;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
