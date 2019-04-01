/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : blogroom_blog

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 01/04/2019 12:09:18
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
  `visit_number` int(8) NULL DEFAULT NULL COMMENT '博客被访问次数',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态（0. 正常　1.禁用）',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '博客创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, 1, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (2, 2, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (3, 15, 1, 0, 8, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (4, 16, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (5, 17, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (6, 18, 1, 10, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (7, 19, 10, 100000, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (8, 20, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (9, 21, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (10, 22, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (11, 23, 1, 0, 10, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (12, 24, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (13, 25, 1, 0, 7, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (14, 26, 4, 1395, 11, 0, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (17, 30, 1, 0, 0, 0, '2019-03-20 10:22:59', NULL);
INSERT INTO `blog` VALUES (18, 31, 1, 0, 0, 0, '2019-03-20 10:25:25', NULL);
INSERT INTO `blog` VALUES (19, 32, 1, 0, 0, 0, '2019-03-20 10:32:50', NULL);
INSERT INTO `blog` VALUES (20, 33, 1, 0, 0, 0, '2019-03-20 10:44:35', NULL);
INSERT INTO `blog` VALUES (21, 34, 1, 0, 4, 0, '2019-03-20 15:01:23', NULL);
INSERT INTO `blog` VALUES (22, 35, 1, 0, 13, 0, '2019-03-27 20:22:24', NULL);

-- ----------------------------
-- Table structure for integral_config
-- ----------------------------
DROP TABLE IF EXISTS `integral_config`;
CREATE TABLE `integral_config`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `type` int(2) NULL DEFAULT NULL COMMENT '积分变动类型（1.撰写文章　）',
  `illustrate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '积分变动描述',
  `number` int(5) NULL DEFAULT NULL COMMENT '变动积分数量',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of integral_config
-- ----------------------------
INSERT INTO `integral_config` VALUES (1, 1, '撰写文章获得积分', 10, '2019-02-27 10:30:14', NULL);

-- ----------------------------
-- Table structure for integral_log
-- ----------------------------
DROP TABLE IF EXISTS `integral_log`;
CREATE TABLE `integral_log`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `blog_id` mediumint(9) NULL DEFAULT NULL COMMENT '用户id',
  `type` mediumint(9) NULL DEFAULT NULL COMMENT '积分改变类型',
  `change_number` int(5) NULL DEFAULT NULL COMMENT '积分变动数额',
  `article_id` mediumint(9) NULL DEFAULT NULL COMMENT '文章id',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of integral_log
-- ----------------------------
INSERT INTO `integral_log` VALUES (1, 14, 1, 10, 59, '2019-03-27 16:11:49', NULL);
INSERT INTO `integral_log` VALUES (2, 14, 1, 10, 58, '2019-03-27 16:15:31', NULL);
INSERT INTO `integral_log` VALUES (3, 14, 1, 10, 55, '2019-03-27 16:25:09', NULL);
INSERT INTO `integral_log` VALUES (4, 14, 1, 10, 54, '2019-03-27 16:56:00', NULL);
INSERT INTO `integral_log` VALUES (5, 14, 1, 10, 53, '2019-03-27 17:00:00', NULL);
INSERT INTO `integral_log` VALUES (6, 14, 1, 10, 52, '2019-03-27 17:02:48', NULL);
INSERT INTO `integral_log` VALUES (7, 9, 1, 10, 48, '2019-03-27 17:26:06', NULL);
INSERT INTO `integral_log` VALUES (8, 14, 1, 10, 59, '2019-03-27 17:27:34', NULL);
INSERT INTO `integral_log` VALUES (9, 14, 1, 10, 58, '2019-03-27 17:27:59', NULL);
INSERT INTO `integral_log` VALUES (10, 14, 1, 10, 55, '2019-03-27 17:28:29', NULL);
INSERT INTO `integral_log` VALUES (11, 14, 1, 10, 54, '2019-03-27 17:59:26', NULL);
INSERT INTO `integral_log` VALUES (12, 7, 1, 10, 36, '2019-03-28 16:18:43', NULL);
INSERT INTO `integral_log` VALUES (13, 14, 1, NULL, 52, '2019-03-29 20:05:23', NULL);
INSERT INTO `integral_log` VALUES (14, 6, 1, NULL, 35, '2019-03-29 20:05:41', NULL);
INSERT INTO `integral_log` VALUES (15, 14, 1, 10, 51, '2019-03-29 20:11:32', NULL);

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '等级ID',
  `level` int(5) NOT NULL COMMENT '等级',
  `integral` int(8) NULL DEFAULT NULL COMMENT '等级积分要求',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级名称',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES (1, 1, 0, '见习会员 ', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (2, 2, 100, '正式会员', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (3, 3, 500, '小有名气', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (4, 4, 1000, '知名人士', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (5, 5, 2500, '见习写手', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (6, 6, 5000, '正式写手', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (7, 7, 10000, '著名写手', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (8, 8, 20000, '见习作家', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (9, 9, 40000, '职业作家', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (10, 10, 80000, '知名作家', '2019-03-25 19:29:53', '2019-03-25 19:30:03');
INSERT INTO `rank` VALUES (11, 11, 160000, '自成一家', '2019-03-25 19:29:53', '2019-03-25 19:30:03');

SET FOREIGN_KEY_CHECKS = 1;
