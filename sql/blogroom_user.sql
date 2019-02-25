/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : blogroom_user

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 25/02/2019 23:56:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attention
-- ----------------------------
DROP TABLE IF EXISTS `attention`;
CREATE TABLE `attention`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '关注表的自增ID',
  `attention_id` mediumint(9) NOT NULL COMMENT '用户ID',
  `attentioned_id` mediumint(9) NOT NULL COMMENT '用户关注ID',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attention
-- ----------------------------
INSERT INTO `attention` VALUES (1, 15, 16, NULL, NULL);
INSERT INTO `attention` VALUES (2, 16, 15, NULL, NULL);
INSERT INTO `attention` VALUES (3, 15, 19, NULL, NULL);
INSERT INTO `attention` VALUES (5, 16, 19, NULL, NULL);
INSERT INTO `attention` VALUES (6, 15, 17, NULL, NULL);
INSERT INTO `attention` VALUES (8, 15, 2, NULL, NULL);
INSERT INTO `attention` VALUES (35, 19, 2, NULL, NULL);
INSERT INTO `attention` VALUES (36, 19, 1, NULL, NULL);
INSERT INTO `attention` VALUES (37, 19, 18, NULL, NULL);
INSERT INTO `attention` VALUES (42, 18, 20, NULL, NULL);
INSERT INTO `attention` VALUES (43, 18, 15, NULL, NULL);
INSERT INTO `attention` VALUES (45, 18, 19, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话号码',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户EMAIL地址',
  `sex` int(1) NULL DEFAULT NULL COMMENT '用户性别（0.未知　1.男　2.女）',
  `birthday` date NULL DEFAULT NULL COMMENT '用户生日',
  `head_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default.jpg' COMMENT '用户头像存储路径',
  `area` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `occupation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `description` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自我描述',
  `status` int(1) NULL DEFAULT 0 COMMENT '用户状态，0为正常，1为锁定，2为注销',
  `last_login_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户最近一次登录ip',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '用户注册时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '用户信息更新',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123', NULL, NULL, '123456@qq.com', 1, '2016-09-14', 'fd4fdfbf-29dd-4186-b4c3-43130b87c077.jpg', NULL, NULL, '一个刚刚进入博客屋的小白shu', 0, '', '2018-08-21 10:33:56', '2019-02-22 10:47:12');
INSERT INTO `user` VALUES (2, 'qyd2', '123', NULL, NULL, '456@qq.com', 1, NULL, 'default.jpg', NULL, NULL, NULL, 0, '', '2018-08-21 10:34:49', NULL);
INSERT INTO `user` VALUES (15, '苏宏鹏', '1234', NULL, NULL, 'suhongpeng@aliyun.com', 1, '2018-08-07', '29e23821-d437-41df-b891-f5302332fbf1.jpg', NULL, NULL, '', 0, '', '2018-08-21 21:22:58', NULL);
INSERT INTO `user` VALUES (16, 'test1', '123', NULL, NULL, '123@qq.com', 1, '2018-07-30', '87800dfd-5618-458d-a8e3-b54159d9765d.jpg', NULL, NULL, '帅', 0, '', '2018-08-21 21:54:14', NULL);
INSERT INTO `user` VALUES (17, 'test2', 'test2', NULL, NULL, 'test2@aliyun.com', 1, NULL, 'default.jpg', NULL, NULL, NULL, 0, '', '2018-08-21 21:55:24', NULL);
INSERT INTO `user` VALUES (18, 'shp', '123', NULL, NULL, 'suhongpen@xinlang.com', 1, '2018-08-07', '1bced002-a5c5-48ee-8faa-e0f18c01f210.jpg', NULL, NULL, '苏宏鹏小号', 0, '', '2018-08-22 08:54:14', NULL);
INSERT INTO `user` VALUES (19, 'qiuyunduo', '123456', NULL, NULL, 'qiuyunduo@aliyun.com', 1, '1998-06-16', '82f69a8b-8ae0-44b8-8c7b-187d2833a6cf.jpg', NULL, NULL, '已经是博客屋的一名老将了', 0, '', '2018-08-22 21:40:14', NULL);
INSERT INTO `user` VALUES (20, '1', '1', NULL, NULL, '123@qq.com', 1, NULL, '68a0873b-8236-41ed-8c68-7b20e5a1c947.jpg', NULL, NULL, '', 0, '', '2018-08-23 10:26:31', NULL);

SET FOREIGN_KEY_CHECKS = 1;
