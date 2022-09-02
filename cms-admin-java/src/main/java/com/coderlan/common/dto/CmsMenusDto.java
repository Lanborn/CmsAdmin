package com.coderlan.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * {
 * 					name: 'SysUser',
 * 					title: '用户管理',
 * 					icon: 'el-icon-s-custom',
 * 					path: '/sys/users',
 * 					component: 'sys/User',
 * 					children: []
 *                                },
 */
//        {
//				"id": 38,
//				"name": "系统总览",
//				"type": 1,
//				"url": "/main/analysis",
//				"icon": "el-icon-monitor",
//				"sort": 1,
//				"children": [
//				]
//				},
@Data
public class CmsMenusDto implements Serializable {

	private Long id;
	private String name;
	private Integer type;
	private String url;
	private String icon;
	private String sort;
	private String Permission;
	private String component;
	private Long parentId;
	private List<CmsMenusDto> children = new ArrayList<>();

}
