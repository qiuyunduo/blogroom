/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : blogroom_website

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 01/04/2019 12:09:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员姓名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'EMAIL地址',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别（0.未知　1.男　2.女）',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `head_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default.jpg' COMMENT '头像存储路径',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态，0超级管理员，1普通管理员',
  `login_number` int(4) NULL DEFAULT 0 COMMENT '登录次数',
  `last_login_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最近一次登录ip',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '信息更新',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', NULL, 'admin@163.com', NULL, NULL, NULL, 0, 76, '192.168.1.167', '2019-03-29 11:28:01', '2019-02-27 14:31:35', NULL);
INSERT INTO `admin` VALUES (4, 'qiuyunduo', '202cb962ac59075b964b07152d234b70', NULL, 'qiuyunduo@qq.com', NULL, NULL, NULL, 1, 2, '192.168.1.167', '2019-03-29 11:29:22', '2019-03-29 11:27:57', NULL);

-- ----------------------------
-- Table structure for friendly_link
-- ----------------------------
DROP TABLE IF EXISTS `friendly_link`;
CREATE TABLE `friendly_link`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '记录主键',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '友情链接地址',
  `content` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接文本内容',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friendly_link
-- ----------------------------
INSERT INTO `friendly_link` VALUES (1, '#', '思衡网络', '2019-03-13 16:03:00', NULL);
INSERT INTO `friendly_link` VALUES (2, 'http://www.welcom212.com/wordpress/wp-login.php', '3DST技术网', '2019-02-27 14:28:31', NULL);
INSERT INTO `friendly_link` VALUES (3, 'https://github.com/qiuyunduo', 'qiuyunduo博主的github地址', '2019-02-27 14:29:00', NULL);
INSERT INTO `friendly_link` VALUES (4, 'https://blog.csdn.net/qydcsdn', 'qiuyunduo的csdn地址', '2019-02-27 14:29:48', NULL);

SET FOREIGN_KEY_CHECKS = 1;
