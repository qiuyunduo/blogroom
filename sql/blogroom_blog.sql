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

 Date: 19/03/2019 19:07:28
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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, 1, 1, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (2, 2, 1, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (3, 15, 1, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (4, 16, 2, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (5, 17, 1, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (6, 18, 7, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (7, 19, 8, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (8, 20, 1, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (9, 21, 5, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (10, 22, 1, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (11, 23, 4, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (12, 24, 1, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (13, 25, 4, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);
INSERT INTO `blog` VALUES (14, 26, 1, 0, 3, 4, 5, 6, 7, 1450000, 1, '2019-03-08 13:46:57', NULL);

-- ----------------------------
-- Table structure for integral_config
-- ----------------------------
DROP TABLE IF EXISTS `integral_config`;
CREATE TABLE `integral_config`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `type` int(2) NULL DEFAULT NULL COMMENT '积分变动情况',
  `describe` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '积分变动描述',
  `number` int(5) NULL DEFAULT NULL COMMENT '变动积分数量',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of integral_config
-- ----------------------------
INSERT INTO `integral_config` VALUES (1, 1, '撰写文章,积分+10', 10, '2019-02-27 10:30:14', NULL);

-- ----------------------------
-- Table structure for integral_log
-- ----------------------------
DROP TABLE IF EXISTS `integral_log`;
CREATE TABLE `integral_log`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `blog_id` mediumint(9) NULL DEFAULT NULL COMMENT '用户id',
  `type_id` mediumint(9) NULL DEFAULT NULL COMMENT '积分改变方式id',
  `article_id` mediumint(9) NULL DEFAULT NULL COMMENT '文章id',
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
INSERT INTO `rank` VALUES (1, 1, 0, '见习会员 ', NULL, NULL);
INSERT INTO `rank` VALUES (2, 2, 100, '正式会员', NULL, NULL);
INSERT INTO `rank` VALUES (3, 3, 500, '小有名气', NULL, NULL);
INSERT INTO `rank` VALUES (4, 4, 1000, '知名人士', NULL, NULL);
INSERT INTO `rank` VALUES (5, 5, 2500, '见习写手', NULL, NULL);
INSERT INTO `rank` VALUES (6, 6, 5000, '正式写手', NULL, NULL);
INSERT INTO `rank` VALUES (7, 7, 10000, '著名写手', NULL, NULL);
INSERT INTO `rank` VALUES (8, 8, 20000, '见习作家', NULL, NULL);
INSERT INTO `rank` VALUES (9, 9, 40000, '职业作家', NULL, NULL);
INSERT INTO `rank` VALUES (10, 10, 80000, '知名作家', NULL, NULL);
INSERT INTO `rank` VALUES (11, 11, 160000, '自成一家', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
