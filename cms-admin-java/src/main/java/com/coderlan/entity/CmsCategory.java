package com.coderlan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

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
public class CmsCategory {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
