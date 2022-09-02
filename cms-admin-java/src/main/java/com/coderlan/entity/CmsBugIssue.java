package com.coderlan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author CoderLan
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsBugIssue extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Issue标题不能为空")
    private String title;

    private String content;


}
