/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : cms_admin_sys

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 02/09/2022 09:11:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_bug_issue
-- ----------------------------
DROP TABLE IF EXISTS `cms_bug_issue`;
CREATE TABLE `cms_bug_issue`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `enable` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_bug_issue
-- ----------------------------
INSERT INTO `cms_bug_issue` VALUES (1, '建议用Vite打包', '年轻人多尝试', '2022-09-01 13:19:28', NULL, 0);
INSERT INTO `cms_bug_issue` VALUES (2, '多层封装', '多层封装好复用', '2022-09-01 13:53:27', NULL, 0);
INSERT INTO `cms_bug_issue` VALUES (3, '你好明天 ', NULL, '2022-09-01 13:55:23', NULL, 0);

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_category
-- ----------------------------
INSERT INTO `cms_category` VALUES (1, '鞋子', '2022-08-26 02:48:39', '2022-08-26 02:48:42');
INSERT INTO `cms_category` VALUES (2, '裤子', '2022-08-26 02:48:48', '2022-08-26 02:48:50');
INSERT INTO `cms_category` VALUES (3, '上衣', '2022-08-26 17:57:32', NULL);
INSERT INTO `cms_category` VALUES (5, '家具', '2022-08-27 01:37:44', NULL);

-- ----------------------------
-- Table structure for cms_department
-- ----------------------------
DROP TABLE IF EXISTS `cms_department`;
CREATE TABLE `cms_department`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `enable` int NOT NULL DEFAULT 0 COMMENT '0: 使用，1：弃用',
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parentId`(`parentId`) USING BTREE,
  CONSTRAINT `parentId` FOREIGN KEY (`parentId`) REFERENCES `cms_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_department
-- ----------------------------
INSERT INTO `cms_department` VALUES (1, '总裁办', NULL, '2022-08-15 18:19:49', '2022-08-15 18:19:51', 0, 'coderlan');
INSERT INTO `cms_department` VALUES (2, '研发部', 1, '2022-08-15 18:20:01', '2022-08-15 18:20:03', 0, 'vincent');
INSERT INTO `cms_department` VALUES (3, '运营部', 1, '2022-08-15 18:20:29', '2022-08-15 18:20:30', 0, 'lily');
INSERT INTO `cms_department` VALUES (4, '人事部', 1, '2022-08-15 18:20:43', '2022-08-15 18:20:45', 0, 'lucy');
INSERT INTO `cms_department` VALUES (5, '客服部', 2, '2022-08-15 18:21:06', '2022-08-15 18:21:08', 0, 'alen');
INSERT INTO `cms_department` VALUES (6, '测试部门', 1, '2022-08-24 17:14:23', '2022-08-24 20:24:04', 0, 'Lily');
INSERT INTO `cms_department` VALUES (13, 'Test123123', 2, '2022-08-27 00:43:10', '2022-08-28 08:04:38', 0, 'coderlan');

-- ----------------------------
-- Table structure for cms_goods
-- ----------------------------
DROP TABLE IF EXISTS `cms_goods`;
CREATE TABLE `cms_goods`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `oldPrice` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `newPrice` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `descption` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '0: 可用',
  `imgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `inventoryCount` int NULL DEFAULT NULL,
  `saleCount` int NULL DEFAULT NULL COMMENT '销售数量',
  `favorCount` int NULL DEFAULT NULL COMMENT '收藏数量',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `categoryId` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 402 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_goods
-- ----------------------------
INSERT INTO `cms_goods` VALUES (165, '2018秋装女装韩版新款休闲时尚套装女圆领条纹薄款上衣+高腰束脚灯笼裤两件套女潮', '198', '198', '2018秋装女装韩版新款休闲时尚套装女圆领条纹薄款上衣+高腰束脚灯笼裤两件套女潮', '0', 'http://s11.mogucdn.com/mlcdn/55cf19/180803_44ec95haiehdddjk126fgidfg52le_640x960.jpg_560x999.jpg', 1582, 1459, 13, '重庆', 5, '2022-08-17 09:37:56', '2022-08-26 16:32:18');
INSERT INTO `cms_goods` VALUES (166, '秋装2018新款牛仔外套女韩版宽松短款针织背心高腰半身裙中长裙小个子显高时尚套装裙子三件套', '39', '27', '秋装2018新款牛仔外套女韩版宽松短款针织背心高腰半身裙中长裙小个子显高时尚套装裙子三件套', '0', 'http://s3.mogucdn.com/mlcdn/c45406/180731_5be6jhh7ggj68d4063gkca4egh02i_750x1000.jpg_560x999.jpg', 7012, 1432, 103, '昆明', 8, '2022-08-17 09:37:56', '2022-08-26 16:32:20');
INSERT INTO `cms_goods` VALUES (167, '格姬2018秋装新款两件套圆领灯笼袖连衣裙春装新款韩版名媛大裙摆套装裙时尚套装女6254', '285', '199', '格姬2018秋装新款两件套圆领灯笼袖连衣裙春装新款韩版名媛大裙摆套装裙时尚套装女6254', '0', 'http://s3.mogucdn.com/mlcdn/c45406/180302_4ji3hab7c3kdhfdg4i0lc86a1287h_640x960.jpg_560x999.jpg', 2455, 1416, 62, '南京', 7, '2022-08-17 09:37:56', '2022-08-26 16:32:21');
INSERT INTO `cms_goods` VALUES (168, '连衣裙女新款2018秋款韩版小碎花裙子长袖文艺学生百搭小黑裙', '97', '68', '连衣裙女新款2018秋款韩版小碎花裙子长袖文艺学生百搭小黑裙', '0', 'http://s11.mogucdn.com/mlcdn/c45406/170402_06ehihjk325cjc7jc4653k1bkek2b_640x960.jpg_560x999.jpg', 7702, 1416, 441, '西安', 5, '2022-08-17 09:37:56', '2022-08-26 16:32:22');
INSERT INTO `cms_goods` VALUES (169, '时尚套装两件套网红同款2018秋装女套装新款学生牛仔外套配内搭温柔风小黑裙黑色v领吊带裙潮', '100', '70', '时尚套装两件套网红同款2018秋装女套装新款学生牛仔外套配内搭温柔风小黑裙黑色v领吊带裙潮', '0', 'http://s3.mogucdn.com/mlcdn/c45406/180819_44ee3hf251agika4lji8958i46e6d_640x960.jpg_560x999.jpg', 1281, 1404, 75, '青岛', 7, '2022-08-17 09:37:56', '2022-08-26 16:32:24');
INSERT INTO `cms_goods` VALUES (170, '2018秋装套装新款韩版百搭显瘦长袖条纹雪纺衬衫女宽松直筒背带裤套装两件套', '84', '59', '2018秋装套装新款韩版百搭显瘦长袖条纹雪纺衬衫女宽松直筒背带裤套装两件套', '0', 'http://s11.mogucdn.com/mlcdn/c45406/180312_5ebi8i8k389leic0g487h3l611kek_640x960.jpg_560x999.jpg', 7966, 1384, 80, '西安', 3, '2022-08-17 09:37:56', '2022-08-26 16:32:24');
INSERT INTO `cms_goods` VALUES (171, '2018秋冬新款时尚韩范百搭显瘦背带裤套装灯芯绒裤子+毛衣两件套女', '99', '69', '2018秋冬新款时尚韩范百搭显瘦背带裤套装灯芯绒裤子+毛衣两件套女', '0', 'http://s11.mogucdn.com/mlcdn/55cf19/180831_3lccd4912aec0lb8fga9ji7ah6bkd_640x960.jpg_560x999.jpg', 4339, 1382, 55, '南京', 8, '2022-08-17 09:37:56', '2022-08-26 16:32:24');
INSERT INTO `cms_goods` VALUES (173, '时尚套装韩版气质甜美镂空灯笼袖针织衫百搭显瘦毛衣女2018秋季新款连衣裙套装', '70', '70', '时尚套装韩版气质甜美镂空灯笼袖针织衫百搭显瘦毛衣女2018秋季新款连衣裙套装', '0', 'http://s11.mogucdn.com/mlcdn/c45406/180822_5bl46cl4g934133a6cbhkk8l37hl0_640x960.jpg_560x999.jpg', 1615, 1360, 286, '沈阳', 3, '2022-08-17 09:37:56', '2022-08-26 16:32:27');
INSERT INTO `cms_goods` VALUES (174, '2018秋季新款时尚套装蝴蝶结波点衬衫圆领麻花毛衣无袖马甲百褶半身裙中长款A字裙套装三件套', '86', '60', '2018秋季新款时尚套装蝴蝶结波点衬衫圆领麻花毛衣无袖马甲百褶半身裙中长款A字裙套装三件套', '0', 'http://s11.mogucdn.com/mlcdn/c45406/180131_1kgh02j1j4lbb74g0427ljk976612_640x960.jpg_560x999.jpg', 4118, 1356, 311, '青岛', 8, '2022-08-17 09:37:56', '2022-08-26 16:32:25');
INSERT INTO `cms_goods` VALUES (183, '吊带背心女夏2018秋季新款内搭吊带衫短款性感修身针织打底衫上衣显瘦', '43', '30', '吊带背心女夏2018秋季新款内搭吊带衫短款性感修身针织打底衫上衣显瘦', '0', 'http://s11.mogucdn.com/mlcdn/17f85e/180927_5i77e04lhaalbg3dai0j4588lbahh_640x960.jpg_560x999.jpg', 6285, 2, 3, '天津', 6, '2022-08-17 09:37:56', '2022-08-26 17:24:15');
INSERT INTO `cms_goods` VALUES (191, '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套123', '99', '69', '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套', '0', NULL, NULL, NULL, NULL, '杭州', 2, '2022-08-26 17:05:43', NULL);
INSERT INTO `cms_goods` VALUES (192, '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套124', '99', '69', '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套', '0', NULL, NULL, NULL, NULL, '杭州', 2, '2022-08-26 17:07:07', NULL);
INSERT INTO `cms_goods` VALUES (193, '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套134', '99', '69', '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套', '0', NULL, NULL, NULL, NULL, '杭州', 2, '2022-08-26 17:13:29', NULL);
INSERT INTO `cms_goods` VALUES (194, '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套156', '99', '69', '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套', '0', NULL, NULL, NULL, NULL, '杭州', 2, '2022-08-26 17:15:03', NULL);
INSERT INTO `cms_goods` VALUES (401, '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套31312', '99', '69', '2018秋季新款时尚套装宽松BF风学生格子长袖衬衫女上衣+韩版百搭学院风牛仔背带裤女两件套', '0', 'http://s11.mogucdn.com/mlcdn/c45406/180812_5ejaf15h8i9182c1765lbf6464d8h_640x960.jpg_560x999.jpg', 10060, 1673, 7, '杭州', NULL, '2022-08-26 18:32:31', NULL);

-- ----------------------------
-- Table structure for cms_goods_address_sale
-- ----------------------------
DROP TABLE IF EXISTS `cms_goods_address_sale`;
CREATE TABLE `cms_goods_address_sale`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `count` int NULL DEFAULT NULL COMMENT '不同城市销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_goods_address_sale
-- ----------------------------
INSERT INTO `cms_goods_address_sale` VALUES (1, '上海', 62239);
INSERT INTO `cms_goods_address_sale` VALUES (2, '南京', 55683);
INSERT INTO `cms_goods_address_sale` VALUES (3, '郑州', 53716);
INSERT INTO `cms_goods_address_sale` VALUES (4, '广州', 6364);
INSERT INTO `cms_goods_address_sale` VALUES (5, '西安', 64976);
INSERT INTO `cms_goods_address_sale` VALUES (6, '长沙', 4142);
INSERT INTO `cms_goods_address_sale` VALUES (7, '昆明', 9524);
INSERT INTO `cms_goods_address_sale` VALUES (8, '武汉', 28212);
INSERT INTO `cms_goods_address_sale` VALUES (9, '重庆', 60777);
INSERT INTO `cms_goods_address_sale` VALUES (10, '沈阳', 20900);
INSERT INTO `cms_goods_address_sale` VALUES (11, '宁波', 66584);
INSERT INTO `cms_goods_address_sale` VALUES (12, '苏州', 1136);
INSERT INTO `cms_goods_address_sale` VALUES (13, '青岛', 9021);
INSERT INTO `cms_goods_address_sale` VALUES (14, '成都', 23378);
INSERT INTO `cms_goods_address_sale` VALUES (15, '北京', 6107);
INSERT INTO `cms_goods_address_sale` VALUES (16, '天津', 5096);
INSERT INTO `cms_goods_address_sale` VALUES (17, '深圳', 12480);
INSERT INTO `cms_goods_address_sale` VALUES (18, '杭州', 19654);

-- ----------------------------
-- Table structure for cms_goods_amount_list
-- ----------------------------
DROP TABLE IF EXISTS `cms_goods_amount_list`;
CREATE TABLE `cms_goods_amount_list`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tips` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `subtitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `number1` int NULL DEFAULT NULL,
  `number2` int NULL DEFAULT NULL,
  `amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_goods_amount_list
-- ----------------------------
INSERT INTO `cms_goods_amount_list` VALUES (1, '商品总销量', '所有商品的总销量', '商品总销量', 509989, 509989, 'sale');
INSERT INTO `cms_goods_amount_list` VALUES (2, '商品总收藏', '所有商品的总收藏', '商品总收藏', 87226, 87226, 'favor');
INSERT INTO `cms_goods_amount_list` VALUES (3, '商品总库存', '所有商品的总库存', '商品总库存', 853667, 853667, 'inventory');
INSERT INTO `cms_goods_amount_list` VALUES (4, '商品总销售额', '所有商品的总销售额', '商品总销售额', 43118530, 43118530, 'saleroom');

-- ----------------------------
-- Table structure for cms_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_goods_category`;
CREATE TABLE `cms_goods_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods_id` int NULL DEFAULT NULL,
  `category_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gId`(`goods_id`) USING BTREE,
  INDEX `cId`(`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_goods_category
-- ----------------------------

-- ----------------------------
-- Table structure for cms_goods_category_count
-- ----------------------------
DROP TABLE IF EXISTS `cms_goods_category_count`;
CREATE TABLE `cms_goods_category_count`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsCount` int NULL DEFAULT NULL COMMENT '分类商品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_goods_category_count
-- ----------------------------
INSERT INTO `cms_goods_category_count` VALUES (2, '上衣', 14);
INSERT INTO `cms_goods_category_count` VALUES (3, '裤子', 19);
INSERT INTO `cms_goods_category_count` VALUES (4, '鞋子', 19);
INSERT INTO `cms_goods_category_count` VALUES (5, '厨具', 18);
INSERT INTO `cms_goods_category_count` VALUES (6, '家具', 18);
INSERT INTO `cms_goods_category_count` VALUES (7, '床上用品', 19);
INSERT INTO `cms_goods_category_count` VALUES (8, '女装', 35);

-- ----------------------------
-- Table structure for cms_goods_category_favor
-- ----------------------------
DROP TABLE IF EXISTS `cms_goods_category_favor`;
CREATE TABLE `cms_goods_category_favor`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsFavor` int NULL DEFAULT NULL COMMENT '分类商品收藏量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_goods_category_favor
-- ----------------------------
INSERT INTO `cms_goods_category_favor` VALUES (2, '上衣', 6091);
INSERT INTO `cms_goods_category_favor` VALUES (3, '裤子', 4939);
INSERT INTO `cms_goods_category_favor` VALUES (4, '鞋子', 19647);
INSERT INTO `cms_goods_category_favor` VALUES (5, '厨具', 13906);
INSERT INTO `cms_goods_category_favor` VALUES (6, '家具', 15425);
INSERT INTO `cms_goods_category_favor` VALUES (7, '床上用品', 7307);
INSERT INTO `cms_goods_category_favor` VALUES (8, '女装', 19911);

-- ----------------------------
-- Table structure for cms_goods_category_sale
-- ----------------------------
DROP TABLE IF EXISTS `cms_goods_category_sale`;
CREATE TABLE `cms_goods_category_sale`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodsCount` int NULL DEFAULT NULL COMMENT '分类商品销售量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_goods_category_sale
-- ----------------------------
INSERT INTO `cms_goods_category_sale` VALUES (2, '上衣', 49749);
INSERT INTO `cms_goods_category_sale` VALUES (3, '裤子', 84754);
INSERT INTO `cms_goods_category_sale` VALUES (4, '鞋子', 58283);
INSERT INTO `cms_goods_category_sale` VALUES (5, '厨具', 57354);
INSERT INTO `cms_goods_category_sale` VALUES (6, '家具', 49002);
INSERT INTO `cms_goods_category_sale` VALUES (7, '床上用品', 98579);
INSERT INTO `cms_goods_category_sale` VALUES (8, '女装', 112268);

-- ----------------------------
-- Table structure for cms_goods_sale_top10
-- ----------------------------
DROP TABLE IF EXISTS `cms_goods_sale_top10`;
CREATE TABLE `cms_goods_sale_top10`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `saleCount` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_goods_sale_top10
-- ----------------------------
INSERT INTO `cms_goods_sale_top10` VALUES (7, '秋装女2018新款早秋女装裙子韩版针织连衣裙+衬衫上衣时尚套装', 32070);
INSERT INTO `cms_goods_sale_top10` VALUES (8, '套装女春秋2018新款时尚复古气质个性连帽卫衣两件套', 28990);
INSERT INTO `cms_goods_sale_top10` VALUES (11, '时尚套装两件套2018韩版网红社会宽松连帽卫衣+高腰显瘦开叉半身裙学院风休闲秋装女套装新款', 23765);
INSERT INTO `cms_goods_sale_top10` VALUES (17, '网红同款实拍秋季女装2018新款女初恋复古中长款针织连衣裙半身裙时尚套装两件套', 21355);
INSERT INTO `cms_goods_sale_top10` VALUES (18, '2018秋冬新款ins超火针织时尚两件套小香风套装女', 20288);
INSERT INTO `cms_goods_sale_top10` VALUES (22, 'chic港味秋装女套装新款2018韩版格子西装外套+显瘦高腰破洞小脚裤学院风时尚套装两件套', 19993);
INSERT INTO `cms_goods_sale_top10` VALUES (27, '中长款长袖连衣裙秋装新款2018韩版休闲胖mm大码女装裙子女学生宽松松垮垮中长款卫衣裙外套', 19045);
INSERT INTO `cms_goods_sale_top10` VALUES (28, '2018秋新款韩版时尚简约系带显瘦条纹系带西服套装女', 17587);
INSERT INTO `cms_goods_sale_top10` VALUES (31, '秋季新款套装两件套秋装2018新款韩版chic泡泡袖衬衫上衣+高腰显瘦牛仔裤学院风时尚套装', 17225);
INSERT INTO `cms_goods_sale_top10` VALUES (32, '2018新款时尚百搭黑色宽松机车皮夹克+网纱半身裙套装两件套', 16985);

-- ----------------------------
-- Table structure for cms_menus
-- ----------------------------
DROP TABLE IF EXISTS `cms_menus`;
CREATE TABLE `cms_menus`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int NOT NULL COMMENT '1: 目录 2：菜单 3：按钮',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0: 启用，1：弃用',
  `enable` int NULL DEFAULT 0 COMMENT '0: 启用，1：弃用',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  `parentId` int NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1220 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_menus
-- ----------------------------
INSERT INTO `cms_menus` VALUES (1, '系统管理', 1, '/main/system', 'system:list', NULL, 'el-icon-setting', 0, '2', 0, '2022-08-15 17:33:39', '2022-08-22 20:54:19');
INSERT INTO `cms_menus` VALUES (2, '用户管理', 2, '/main/system/user', 'system:users:list', 'cms/User', '', 0, '100', 1, '2022-08-15 17:34:55', '2022-08-22 20:45:38');
INSERT INTO `cms_menus` VALUES (3, '创建用户', 3, NULL, 'system:users:create', NULL, NULL, 0, NULL, 2, '2022-08-15 17:54:52', NULL);
INSERT INTO `cms_menus` VALUES (4, '删除用户', 3, NULL, 'system:users:delete', NULL, NULL, 0, NULL, 2, '2022-08-15 17:55:30', NULL);
INSERT INTO `cms_menus` VALUES (5, '修改用户', 3, NULL, 'system:users:update', NULL, NULL, 0, NULL, 2, '2022-08-15 17:56:00', NULL);
INSERT INTO `cms_menus` VALUES (6, '查询用户', 3, NULL, 'system:users:query', NULL, NULL, 0, NULL, 2, '2022-08-15 17:56:34', NULL);
INSERT INTO `cms_menus` VALUES (7, '部门管理', 2, '/main/system/department', 'system:department:list', 'cms/Department', NULL, 0, '101', 1, '2022-08-15 17:35:43', '2022-08-22 20:45:44');
INSERT INTO `cms_menus` VALUES (8, '创建部门', 3, NULL, 'system:department:create', NULL, NULL, 0, NULL, 7, '2022-08-15 18:03:17', NULL);
INSERT INTO `cms_menus` VALUES (9, '删除部门', 3, NULL, 'system:department:delete', NULL, NULL, 0, NULL, 7, '2022-08-15 18:03:42', NULL);
INSERT INTO `cms_menus` VALUES (10, '修改部门', 3, NULL, 'system:department:update', NULL, NULL, 0, NULL, 7, '2022-08-15 18:04:04', NULL);
INSERT INTO `cms_menus` VALUES (11, '查询部门', 3, NULL, 'system:department:query', NULL, NULL, 0, NULL, 7, '2022-08-15 18:04:48', NULL);
INSERT INTO `cms_menus` VALUES (12, '菜单管理', 2, '/main/system/menu', 'system:menu:list', 'cms/Menu', NULL, 0, '103', 1, '2022-08-15 17:37:03', '2022-08-22 20:45:48');
INSERT INTO `cms_menus` VALUES (13, '创建菜单', 3, NULL, 'system:menu:create', NULL, NULL, 0, NULL, 12, '2022-08-15 18:06:25', NULL);
INSERT INTO `cms_menus` VALUES (14, '删除菜单', 3, NULL, 'system:menu:delete', NULL, NULL, 0, NULL, 12, '2022-08-15 18:06:44', NULL);
INSERT INTO `cms_menus` VALUES (15, '修改菜单', 3, NULL, 'system:menu:update', NULL, NULL, 0, NULL, 12, '2022-08-15 18:07:09', NULL);
INSERT INTO `cms_menus` VALUES (16, '查询菜单', 3, NULL, 'system:menu:query', NULL, NULL, 0, NULL, 12, '2022-08-15 18:07:30', NULL);
INSERT INTO `cms_menus` VALUES (17, '角色管理', 2, '/main/system/role', 'system:role:list', 'cms/Role', NULL, 0, '102', 1, '2022-08-15 17:37:34', '2022-08-22 20:45:54');
INSERT INTO `cms_menus` VALUES (18, '创建角色', 3, NULL, 'system:role:create', NULL, NULL, 0, NULL, 17, '2022-08-15 18:09:13', NULL);
INSERT INTO `cms_menus` VALUES (19, '删除角色', 3, NULL, 'system:role:delete', NULL, NULL, 0, NULL, 17, '2022-08-15 18:09:31', NULL);
INSERT INTO `cms_menus` VALUES (20, '修改角色', 3, NULL, 'system:role:update', NULL, NULL, 0, NULL, 17, '2022-08-15 18:09:48', NULL);
INSERT INTO `cms_menus` VALUES (21, '查询角色', 3, NULL, 'system:role:query', NULL, NULL, 0, NULL, 17, '2022-08-15 18:10:08', NULL);
INSERT INTO `cms_menus` VALUES (22, '商品中心', 1, '/main/product', 'product:list', NULL, 'el-icon-goods', 0, '3', 0, '2022-08-15 17:45:47', '2022-08-25 01:07:29');
INSERT INTO `cms_menus` VALUES (23, '商品类别', 2, '/main/product/category', 'system:category:list', 'product/Category', NULL, 0, '104', 22, '2022-08-15 17:51:36', '2022-08-22 20:54:42');
INSERT INTO `cms_menus` VALUES (24, '创建类别', 3, NULL, 'system:category:create', NULL, NULL, 0, NULL, 23, '2022-08-15 18:11:33', NULL);
INSERT INTO `cms_menus` VALUES (25, '删除类别', 3, NULL, 'system:category:delete', NULL, NULL, 0, NULL, 23, '2022-08-15 18:11:51', NULL);
INSERT INTO `cms_menus` VALUES (26, '修改类别', 3, NULL, 'system:category:update', NULL, NULL, 0, NULL, 23, '2022-08-15 18:12:15', NULL);
INSERT INTO `cms_menus` VALUES (27, '查询类别', 3, NULL, 'system:category:query', NULL, NULL, 0, NULL, 23, '2022-08-15 18:12:34', NULL);
INSERT INTO `cms_menus` VALUES (28, '商品信息', 2, '/main/product/goods', 'system:goods:list', 'product/Goods', NULL, 0, '105', 22, '2022-08-15 17:52:39', '2022-08-22 20:54:49');
INSERT INTO `cms_menus` VALUES (29, '创建商品', 3, NULL, 'system:goods:create', NULL, NULL, 0, NULL, 28, '2022-08-15 18:13:25', NULL);
INSERT INTO `cms_menus` VALUES (30, '删除商品', 3, NULL, 'system:goods:delete', NULL, NULL, 0, NULL, 28, '2022-08-15 18:13:47', NULL);
INSERT INTO `cms_menus` VALUES (31, '修改商品', 3, NULL, 'system:goods:update', NULL, NULL, 0, NULL, 28, '2022-08-15 18:14:04', NULL);
INSERT INTO `cms_menus` VALUES (32, '查询商品', 3, NULL, 'system:goods:query', NULL, NULL, 0, NULL, 28, '2022-08-15 18:14:23', NULL);
INSERT INTO `cms_menus` VALUES (33, '系统总览', 1, '/main/analysis', 'analysis:list', NULL, 'el-icon-monitor', 0, '1', 0, '2022-08-15 18:15:28', '2022-08-22 20:55:02');
INSERT INTO `cms_menus` VALUES (34, '核心技术', 2, '/main/analysis/overview', 'analysis:overview:list', 'analysis/Overview', NULL, 0, '106', 33, '2022-08-15 18:16:20', '2022-08-22 20:55:24');
INSERT INTO `cms_menus` VALUES (35, '商品统计', 2, '/main/analysis/dashboard', 'analysis:dashboard:list', 'analysis/Dashboard', NULL, 0, '107', 33, '2022-08-15 18:17:08', '2022-08-22 20:55:35');
INSERT INTO `cms_menus` VALUES (36, '角色授权', 3, NULL, 'system:role:perm', NULL, NULL, 0, NULL, 17, '2022-08-22 23:31:35', '2022-08-22 23:31:43');
INSERT INTO `cms_menus` VALUES (37, '分配角色', 3, NULL, 'system:users:role', NULL, NULL, 0, NULL, 2, '2022-08-22 23:59:05', '2022-08-23 23:33:32');
INSERT INTO `cms_menus` VALUES (38, '分配部门', 3, NULL, 'system:department:role', NULL, NULL, 0, NULL, 7, '2022-08-24 17:03:35', '2022-08-24 17:03:41');
INSERT INTO `cms_menus` VALUES (1218, '测试中心', 1, '/main/test', NULL, NULL, 'el-icon-chat-line-round', 0, NULL, 0, '2022-08-28 13:41:56', '2022-09-01 13:00:49');
INSERT INTO `cms_menus` VALUES (1219, '测试管理', 2, '/main/test/manager', NULL, NULL, NULL, 0, NULL, 1218, '2022-08-28 13:43:13', NULL);
INSERT INTO `cms_menus` VALUES (1220, '反馈中心', 1, '/main/bugIssue', 'system:issue:list', NULL, 'el-icon-chat-line-round', 0, NULL, 0, '2022-09-01 13:07:02', '2022-09-01 13:24:22');
INSERT INTO `cms_menus` VALUES (1221, '你的Issue', 2, '/main/bugIssue/list', 'system:issue:list', NULL, NULL, 0, NULL, 1220, '2022-09-01 13:08:38', '2022-09-01 13:21:51');
INSERT INTO `cms_menus` VALUES (1222, '提点Issue', 2, '/main/bugIssue/chat', 'system:issue:create', NULL, NULL, 0, NULL, 1220, '2022-09-01 13:25:07', '2022-09-01 13:25:24');

-- ----------------------------
-- Table structure for cms_role
-- ----------------------------
DROP TABLE IF EXISTS `cms_role`;
CREATE TABLE `cms_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enable` int NOT NULL DEFAULT 0 COMMENT '0: 使用，1：弃用',
  `menuList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_role
-- ----------------------------
INSERT INTO `cms_role` VALUES (1, '超级管理员', 'admin', '所有权限', '2022-08-15 18:17:58', '2022-09-01 13:31:01', 0, NULL);
INSERT INTO `cms_role` VALUES (2, '开发者', 'develop', '所有权限', '2022-08-15 18:18:36', '2022-09-01 13:31:05', 0, NULL);
INSERT INTO `cms_role` VALUES (3, '运营', 'Operation', '日常事务', '2022-08-15 18:18:53', '2022-08-21 11:12:37', 0, NULL);
INSERT INTO `cms_role` VALUES (4, '人事', 'personnel', '人事管理', '2022-08-15 18:19:09', '2022-08-29 00:00:41', 0, NULL);
INSERT INTO `cms_role` VALUES (13, '测试人员', 'Test', '应用测试', '2022-08-28 23:41:15', '2022-09-01 13:30:48', 0, NULL);
INSERT INTO `cms_role` VALUES (14, '前端开发', 'Frontend', '所有查询权限', '2022-08-29 00:32:45', '2022-09-01 12:58:50', 0, NULL);
INSERT INTO `cms_role` VALUES (15, '后端开发', 'Back', '修改权限', '2022-08-29 00:34:52', '2022-08-29 20:42:42', 0, NULL);

-- ----------------------------
-- Table structure for cms_role_menus
-- ----------------------------
DROP TABLE IF EXISTS `cms_role_menus`;
CREATE TABLE `cms_role_menus`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL,
  `menu_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `menu_id` FOREIGN KEY (`menu_id`) REFERENCES `cms_menus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `cms_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 503 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_role_menus
-- ----------------------------
INSERT INTO `cms_role_menus` VALUES (43, 3, 28);
INSERT INTO `cms_role_menus` VALUES (44, 3, 22);
INSERT INTO `cms_role_menus` VALUES (45, 3, 23);
INSERT INTO `cms_role_menus` VALUES (129, 4, 1);
INSERT INTO `cms_role_menus` VALUES (130, 4, 2);
INSERT INTO `cms_role_menus` VALUES (131, 4, 3);
INSERT INTO `cms_role_menus` VALUES (132, 4, 4);
INSERT INTO `cms_role_menus` VALUES (133, 4, 5);
INSERT INTO `cms_role_menus` VALUES (134, 4, 6);
INSERT INTO `cms_role_menus` VALUES (328, 15, 1218);
INSERT INTO `cms_role_menus` VALUES (329, 15, 1219);
INSERT INTO `cms_role_menus` VALUES (330, 15, 33);
INSERT INTO `cms_role_menus` VALUES (331, 15, 34);
INSERT INTO `cms_role_menus` VALUES (332, 15, 35);
INSERT INTO `cms_role_menus` VALUES (333, 15, 1);
INSERT INTO `cms_role_menus` VALUES (334, 15, 2);
INSERT INTO `cms_role_menus` VALUES (335, 15, 3);
INSERT INTO `cms_role_menus` VALUES (336, 15, 4);
INSERT INTO `cms_role_menus` VALUES (337, 15, 5);
INSERT INTO `cms_role_menus` VALUES (338, 15, 6);
INSERT INTO `cms_role_menus` VALUES (339, 15, 37);
INSERT INTO `cms_role_menus` VALUES (340, 15, 7);
INSERT INTO `cms_role_menus` VALUES (341, 15, 8);
INSERT INTO `cms_role_menus` VALUES (342, 15, 9);
INSERT INTO `cms_role_menus` VALUES (343, 15, 10);
INSERT INTO `cms_role_menus` VALUES (344, 15, 11);
INSERT INTO `cms_role_menus` VALUES (345, 15, 38);
INSERT INTO `cms_role_menus` VALUES (346, 15, 17);
INSERT INTO `cms_role_menus` VALUES (347, 15, 21);
INSERT INTO `cms_role_menus` VALUES (348, 15, 36);
INSERT INTO `cms_role_menus` VALUES (349, 15, 18);
INSERT INTO `cms_role_menus` VALUES (350, 15, 19);
INSERT INTO `cms_role_menus` VALUES (351, 15, 20);
INSERT INTO `cms_role_menus` VALUES (352, 15, 12);
INSERT INTO `cms_role_menus` VALUES (353, 15, 13);
INSERT INTO `cms_role_menus` VALUES (354, 15, 14);
INSERT INTO `cms_role_menus` VALUES (355, 15, 15);
INSERT INTO `cms_role_menus` VALUES (356, 15, 16);
INSERT INTO `cms_role_menus` VALUES (463, 14, 1218);
INSERT INTO `cms_role_menus` VALUES (464, 14, 1219);
INSERT INTO `cms_role_menus` VALUES (465, 14, 33);
INSERT INTO `cms_role_menus` VALUES (466, 14, 34);
INSERT INTO `cms_role_menus` VALUES (467, 14, 35);
INSERT INTO `cms_role_menus` VALUES (468, 14, 1);
INSERT INTO `cms_role_menus` VALUES (469, 14, 2);
INSERT INTO `cms_role_menus` VALUES (470, 14, 3);
INSERT INTO `cms_role_menus` VALUES (471, 14, 4);
INSERT INTO `cms_role_menus` VALUES (472, 14, 5);
INSERT INTO `cms_role_menus` VALUES (473, 14, 6);
INSERT INTO `cms_role_menus` VALUES (474, 14, 37);
INSERT INTO `cms_role_menus` VALUES (475, 14, 7);
INSERT INTO `cms_role_menus` VALUES (476, 14, 8);
INSERT INTO `cms_role_menus` VALUES (477, 14, 9);
INSERT INTO `cms_role_menus` VALUES (478, 14, 10);
INSERT INTO `cms_role_menus` VALUES (479, 14, 11);
INSERT INTO `cms_role_menus` VALUES (480, 14, 38);
INSERT INTO `cms_role_menus` VALUES (481, 14, 17);
INSERT INTO `cms_role_menus` VALUES (482, 14, 21);
INSERT INTO `cms_role_menus` VALUES (483, 14, 36);
INSERT INTO `cms_role_menus` VALUES (484, 14, 18);
INSERT INTO `cms_role_menus` VALUES (485, 14, 19);
INSERT INTO `cms_role_menus` VALUES (486, 14, 20);
INSERT INTO `cms_role_menus` VALUES (487, 14, 12);
INSERT INTO `cms_role_menus` VALUES (488, 14, 13);
INSERT INTO `cms_role_menus` VALUES (489, 14, 14);
INSERT INTO `cms_role_menus` VALUES (490, 14, 15);
INSERT INTO `cms_role_menus` VALUES (491, 14, 16);
INSERT INTO `cms_role_menus` VALUES (492, 14, 22);
INSERT INTO `cms_role_menus` VALUES (493, 14, 23);
INSERT INTO `cms_role_menus` VALUES (494, 14, 24);
INSERT INTO `cms_role_menus` VALUES (495, 14, 25);
INSERT INTO `cms_role_menus` VALUES (496, 14, 26);
INSERT INTO `cms_role_menus` VALUES (497, 14, 27);
INSERT INTO `cms_role_menus` VALUES (498, 14, 28);
INSERT INTO `cms_role_menus` VALUES (499, 14, 29);
INSERT INTO `cms_role_menus` VALUES (500, 14, 30);
INSERT INTO `cms_role_menus` VALUES (501, 14, 31);
INSERT INTO `cms_role_menus` VALUES (502, 14, 32);
INSERT INTO `cms_role_menus` VALUES (529, 13, 1218);
INSERT INTO `cms_role_menus` VALUES (530, 13, 1219);
INSERT INTO `cms_role_menus` VALUES (531, 13, 1220);
INSERT INTO `cms_role_menus` VALUES (532, 13, 1221);
INSERT INTO `cms_role_menus` VALUES (533, 13, 1222);
INSERT INTO `cms_role_menus` VALUES (534, 13, 22);
INSERT INTO `cms_role_menus` VALUES (535, 13, 23);
INSERT INTO `cms_role_menus` VALUES (536, 13, 27);
INSERT INTO `cms_role_menus` VALUES (537, 13, 24);
INSERT INTO `cms_role_menus` VALUES (538, 13, 25);
INSERT INTO `cms_role_menus` VALUES (539, 13, 26);
INSERT INTO `cms_role_menus` VALUES (540, 13, 28);
INSERT INTO `cms_role_menus` VALUES (541, 13, 29);
INSERT INTO `cms_role_menus` VALUES (542, 13, 30);
INSERT INTO `cms_role_menus` VALUES (543, 13, 31);
INSERT INTO `cms_role_menus` VALUES (544, 13, 32);
INSERT INTO `cms_role_menus` VALUES (586, 1, 1220);
INSERT INTO `cms_role_menus` VALUES (587, 1, 1221);
INSERT INTO `cms_role_menus` VALUES (588, 1, 1222);
INSERT INTO `cms_role_menus` VALUES (589, 1, 33);
INSERT INTO `cms_role_menus` VALUES (590, 1, 34);
INSERT INTO `cms_role_menus` VALUES (591, 1, 35);
INSERT INTO `cms_role_menus` VALUES (592, 1, 1);
INSERT INTO `cms_role_menus` VALUES (593, 1, 2);
INSERT INTO `cms_role_menus` VALUES (594, 1, 6);
INSERT INTO `cms_role_menus` VALUES (595, 1, 3);
INSERT INTO `cms_role_menus` VALUES (596, 1, 4);
INSERT INTO `cms_role_menus` VALUES (597, 1, 5);
INSERT INTO `cms_role_menus` VALUES (598, 1, 37);
INSERT INTO `cms_role_menus` VALUES (599, 1, 7);
INSERT INTO `cms_role_menus` VALUES (600, 1, 11);
INSERT INTO `cms_role_menus` VALUES (601, 1, 8);
INSERT INTO `cms_role_menus` VALUES (602, 1, 9);
INSERT INTO `cms_role_menus` VALUES (603, 1, 10);
INSERT INTO `cms_role_menus` VALUES (604, 1, 38);
INSERT INTO `cms_role_menus` VALUES (605, 1, 17);
INSERT INTO `cms_role_menus` VALUES (606, 1, 18);
INSERT INTO `cms_role_menus` VALUES (607, 1, 19);
INSERT INTO `cms_role_menus` VALUES (608, 1, 20);
INSERT INTO `cms_role_menus` VALUES (609, 1, 21);
INSERT INTO `cms_role_menus` VALUES (610, 1, 36);
INSERT INTO `cms_role_menus` VALUES (611, 1, 12);
INSERT INTO `cms_role_menus` VALUES (612, 1, 13);
INSERT INTO `cms_role_menus` VALUES (613, 1, 14);
INSERT INTO `cms_role_menus` VALUES (614, 1, 15);
INSERT INTO `cms_role_menus` VALUES (615, 1, 16);
INSERT INTO `cms_role_menus` VALUES (616, 1, 22);
INSERT INTO `cms_role_menus` VALUES (617, 1, 23);
INSERT INTO `cms_role_menus` VALUES (618, 1, 27);
INSERT INTO `cms_role_menus` VALUES (619, 1, 24);
INSERT INTO `cms_role_menus` VALUES (620, 1, 25);
INSERT INTO `cms_role_menus` VALUES (621, 1, 26);
INSERT INTO `cms_role_menus` VALUES (622, 1, 28);
INSERT INTO `cms_role_menus` VALUES (623, 1, 29);
INSERT INTO `cms_role_menus` VALUES (624, 1, 30);
INSERT INTO `cms_role_menus` VALUES (625, 1, 31);
INSERT INTO `cms_role_menus` VALUES (626, 1, 32);
INSERT INTO `cms_role_menus` VALUES (668, 2, 1220);
INSERT INTO `cms_role_menus` VALUES (669, 2, 1221);
INSERT INTO `cms_role_menus` VALUES (670, 2, 1222);
INSERT INTO `cms_role_menus` VALUES (671, 2, 33);
INSERT INTO `cms_role_menus` VALUES (672, 2, 34);
INSERT INTO `cms_role_menus` VALUES (673, 2, 35);
INSERT INTO `cms_role_menus` VALUES (674, 2, 1);
INSERT INTO `cms_role_menus` VALUES (675, 2, 2);
INSERT INTO `cms_role_menus` VALUES (676, 2, 6);
INSERT INTO `cms_role_menus` VALUES (677, 2, 3);
INSERT INTO `cms_role_menus` VALUES (678, 2, 4);
INSERT INTO `cms_role_menus` VALUES (679, 2, 5);
INSERT INTO `cms_role_menus` VALUES (680, 2, 37);
INSERT INTO `cms_role_menus` VALUES (681, 2, 7);
INSERT INTO `cms_role_menus` VALUES (682, 2, 11);
INSERT INTO `cms_role_menus` VALUES (683, 2, 8);
INSERT INTO `cms_role_menus` VALUES (684, 2, 9);
INSERT INTO `cms_role_menus` VALUES (685, 2, 10);
INSERT INTO `cms_role_menus` VALUES (686, 2, 38);
INSERT INTO `cms_role_menus` VALUES (687, 2, 17);
INSERT INTO `cms_role_menus` VALUES (688, 2, 18);
INSERT INTO `cms_role_menus` VALUES (689, 2, 19);
INSERT INTO `cms_role_menus` VALUES (690, 2, 20);
INSERT INTO `cms_role_menus` VALUES (691, 2, 21);
INSERT INTO `cms_role_menus` VALUES (692, 2, 36);
INSERT INTO `cms_role_menus` VALUES (693, 2, 12);
INSERT INTO `cms_role_menus` VALUES (694, 2, 13);
INSERT INTO `cms_role_menus` VALUES (695, 2, 14);
INSERT INTO `cms_role_menus` VALUES (696, 2, 15);
INSERT INTO `cms_role_menus` VALUES (697, 2, 16);
INSERT INTO `cms_role_menus` VALUES (698, 2, 22);
INSERT INTO `cms_role_menus` VALUES (699, 2, 23);
INSERT INTO `cms_role_menus` VALUES (700, 2, 27);
INSERT INTO `cms_role_menus` VALUES (701, 2, 24);
INSERT INTO `cms_role_menus` VALUES (702, 2, 25);
INSERT INTO `cms_role_menus` VALUES (703, 2, 26);
INSERT INTO `cms_role_menus` VALUES (704, 2, 28);
INSERT INTO `cms_role_menus` VALUES (705, 2, 29);
INSERT INTO `cms_role_menus` VALUES (706, 2, 30);
INSERT INTO `cms_role_menus` VALUES (707, 2, 31);
INSERT INTO `cms_role_menus` VALUES (708, 2, 32);

-- ----------------------------
-- Table structure for cms_user
-- ----------------------------
DROP TABLE IF EXISTS `cms_user`;
CREATE TABLE `cms_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `cellphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `enable` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否启用（0启用 1禁止）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_user
-- ----------------------------
INSERT INTO `cms_user` VALUES (1, 'admin', '$2a$10$NtZqyVYdsWuvIImQYMOepOJ/p3IRXry2PDfmUZ/FujLTX/gs/rvCO', 'CoderLan', 'https://blog.lanboc.cn/logo.png', '123@qq.com', '19910000001', 0, '2022-08-17 09:37:56', '2022-08-19 14:58:11');
INSERT INTO `cms_user` VALUES (2, 'test', '$2a$10$NtZqyVYdsWuvIImQYMOepOJ/p3IRXry2PDfmUZ/FujLTX/gs/rvCO', 'CoderTest', 'https://assets.smallsunnyfox.com/music/3.jpg', 'test@qq.com', '18910000001', 0, '2022-08-17 09:39:19', '2022-08-27 01:30:58');
INSERT INTO `cms_user` VALUES (3, 'CoderLan', '$2a$10$g3MDYw3xplCLVIQgCuA/ce9JbruaAqba9D20mMcLr.sNR3eySfthK', '帅气的波', 'http://blog.lanboc.cn/logo.png', '123123@qq.com', '18970001999', 0, '2022-08-23 22:50:23', '2022-08-27 18:30:35');
INSERT INTO `cms_user` VALUES (14, 'xulao8', '$2a$10$hbS0XsR.xYAkJeJe2I30Leu.gp5OmW323sdkctagwgDPU4qfDtszm', 'rcx', 'http://blog.lanboc.cn/logo.png', '8787@qq.com', '18766884499', 0, '2022-08-27 11:41:33', NULL);
INSERT INTO `cms_user` VALUES (15, 'ikuna', '$2a$10$FpdWEWoksyiYYgMxu0TNLOXC20WTy2kzfAB.o5L1vjN9lqmDlqe2S', 'ikun', 'http://blog.lanboc.cn/logo.png', '1556@qq.com', '15522331112', 0, '2022-08-27 11:46:58', '2022-09-01 12:58:21');
INSERT INTO `cms_user` VALUES (16, 'CoderGuo', '$2a$10$.bo.HMcpkNwqsModoZeAluiLqE/C8cAUbyQVTmq10vXoL4cSDAUjm', '郭翔华', 'http://blog.lanboc.cn/logo.png', '31123@qq.com', '13310001234', 0, '2022-08-27 17:43:52', '2022-08-28 08:02:07');

-- ----------------------------
-- Table structure for cms_user_department
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_department`;
CREATE TABLE `cms_user_department`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `department_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `department_id`(`department_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `department_id` FOREIGN KEY (`department_id`) REFERENCES `cms_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `cms_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_user_department
-- ----------------------------
INSERT INTO `cms_user_department` VALUES (1, 1, 1);
INSERT INTO `cms_user_department` VALUES (2, 2, 2);
INSERT INTO `cms_user_department` VALUES (3, 3, 1);
INSERT INTO `cms_user_department` VALUES (6, 14, 2);
INSERT INTO `cms_user_department` VALUES (7, 15, 1);
INSERT INTO `cms_user_department` VALUES (8, 16, 13);

-- ----------------------------
-- Table structure for cms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_role`;
CREATE TABLE `cms_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE,
  INDEX `roleId`(`role_id`) USING BTREE,
  CONSTRAINT `roleId` FOREIGN KEY (`role_id`) REFERENCES `cms_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId` FOREIGN KEY (`user_id`) REFERENCES `cms_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cms_user_role
-- ----------------------------
INSERT INTO `cms_user_role` VALUES (2, 2, 2);
INSERT INTO `cms_user_role` VALUES (4, 1, 1);
INSERT INTO `cms_user_role` VALUES (8, 14, 2);
INSERT INTO `cms_user_role` VALUES (11, 3, 1);
INSERT INTO `cms_user_role` VALUES (20, 16, 2);
INSERT INTO `cms_user_role` VALUES (21, 15, 14);

SET FOREIGN_KEY_CHECKS = 1;
