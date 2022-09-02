package com.coderlan.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//            {
//                    "id": 6155,
//                    "name": "运营2",
//                    "intro": "日常管理2",
//                    "createAt": "2022-08-27T23:07:08.000Z",
//                    "updateAt": "2022-08-27T23:07:08.000Z",
//                    "menuList": [
//                    {
//                    "id": 38,
//                    "name": "系统总览",
//                    "type": 1,
//                    "url": "/main/analysis",
//                    "icon": "el-icon-monitor",
//                    "sort": 1,
//                    "children": [
//                    {
//                    "id": 39,
//                    "url": "/main/analysis/overview",
//                    "name": "核心技术",
//                    "sort": 106,
//                    "type": 2,
//                    "children": null,
//                    "parentId": 38
//                    },
public class CmsRoleDto implements Serializable {
    private Long id;
    private String name;
    private String intro;
    private List<CmsMenusDto> menuList;
}
