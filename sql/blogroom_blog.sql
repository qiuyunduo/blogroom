/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : blogroom_blog

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 25/02/2019 23:56:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `user_id` mediumint(9) NULL DEFAULT NULL COMMENT '用户id',
  `rank_id` mediumint(9) NULL DEFAULT NULL COMMENT '等级id',
  `integral` int(8) NULL DEFAULT NULL COMMENT '博客积分',
  `article_number` int(8) NULL DEFAULT NULL COMMENT '文章数量',
  `fans_number` int(8) NULL DEFAULT NULL COMMENT '粉丝数',
  `like_number` int(8) NULL DEFAULT NULL COMMENT '被喜欢的次数',
  `comment_number` int(8) NULL DEFAULT NULL COMMENT '评论数',
  `visit_number` int(8) NULL DEFAULT NULL COMMENT '博客被访问次数',
  `ranking` int(8) NULL DEFAULT NULL COMMENT '排名',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态（0. 正常　2.禁用）',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '博客创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for integral_config
-- ----------------------------
DROP TABLE IF EXISTS `integral_config`;
CREATE TABLE `integral_config`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '等级ID',
  `rank_level` int(5) NOT NULL COMMENT '等级',
  `rank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级名称',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES (1, 1, '见习会员 ', NULL, NULL);
INSERT INTO `rank` VALUES (2, 2, '正式会员', NULL, NULL);
INSERT INTO `rank` VALUES (3, 3, '小有名气', NULL, NULL);
INSERT INTO `rank` VALUES (4, 4, '知名人士', NULL, NULL);
INSERT INTO `rank` VALUES (5, 5, '见习写手', NULL, NULL);
INSERT INTO `rank` VALUES (6, 6, '正式写手', NULL, NULL);
INSERT INTO `rank` VALUES (7, 7, '著名写手', NULL, NULL);
INSERT INTO `rank` VALUES (8, 8, '见习作家', NULL, NULL);
INSERT INTO `rank` VALUES (9, 9, '职业作家', NULL, NULL);
INSERT INTO `rank` VALUES (10, 10, '知名作家', NULL, NULL);
INSERT INTO `rank` VALUES (11, 11, '自成一家', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;