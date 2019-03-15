/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : blogroom_user

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 15/03/2019 14:20:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attention
-- ----------------------------
DROP TABLE IF EXISTS `attention`;
CREATE TABLE `attention`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '关注表的自增ID',
  `user1_id` mediumint(9) NOT NULL COMMENT '关注者id',
  `user1_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关注者姓名',
  `user1_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关注者头像',
  `user2_id` mediumint(9) NOT NULL COMMENT '被关注者ID',
  `user2_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被关注者姓名',
  `user2_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被关注者头像',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attention
-- ----------------------------
INSERT INTO `attention` VALUES (1, 15, NULL, NULL, 16, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (2, 16, NULL, NULL, 15, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (3, 15, NULL, NULL, 19, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (5, 16, NULL, NULL, 19, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (6, 15, NULL, NULL, 17, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (8, 15, NULL, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (35, 19, NULL, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (36, 19, NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (37, 19, NULL, NULL, 18, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (42, 18, NULL, NULL, 20, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (43, 18, NULL, NULL, 15, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (45, 18, NULL, NULL, 19, NULL, NULL, NULL, NULL);
INSERT INTO `attention` VALUES (46, 1, 'admin', 'fd4fdfbf-29dd-4186-b4c3-43130b87c077.jpg', 2, 'qyd2', 'default.jpg', '2019-03-01 09:59:07', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话号码',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户EMAIL地址',
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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '1518060478', '123456@qq.com', 1, '2016-09-14', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', '一个刚刚进入博客屋的小白shu', 0, '192.168.1.168', '2018-08-21 10:33:56', '2019-02-22 10:47:12');
INSERT INTO `user` VALUES (2, 'qyd2', '123', '1518060478', '456@qq.com', 1, '2019-03-04', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2018-08-21 10:34:49', NULL);
INSERT INTO `user` VALUES (15, '苏宏鹏', '1234', '1518060478', 'suhongpeng@aliyun.com', 1, '2019-03-17', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', '', 0, '192.168.1.168', '2018-08-21 21:22:58', NULL);
INSERT INTO `user` VALUES (16, 'test1', '123', '1518060478', '123@qq.com', 1, '2019-03-10', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', '帅', 0, '192.168.1.168', '2018-08-21 21:54:14', NULL);
INSERT INTO `user` VALUES (17, 'test2', 'test2', '1518060478', 'test2@aliyun.com', 1, '2019-03-14', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2018-08-21 21:55:24', NULL);
INSERT INTO `user` VALUES (18, 'shp', '123', '1518060478', 'suhongpen@xinlang.com', 1, '2019-02-25', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', '苏宏鹏小号', 0, '192.168.1.168', '2018-08-22 08:54:14', NULL);
INSERT INTO `user` VALUES (19, 'qiuyunduo', '202cb962ac59075b964b07152d234b70', '1518060478', 'qiuyunduo@aliyun.com', 1, '2019-03-12', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', '已经是博客屋的一名老将了', 0, '192.168.1.168', '2018-08-22 21:40:14', NULL);
INSERT INTO `user` VALUES (20, '1', '1', '1518060478', '123@qq.com', 1, '2019-03-03', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', '', 0, '192.168.1.168', '2018-08-23 10:26:31', NULL);
INSERT INTO `user` VALUES (21, 'asd', '7815696ecbf1c96e6894b779456d330e', '1518060478', 'asd', 0, '2019-03-04', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:01:43', NULL);
INSERT INTO `user` VALUES (22, 'testuser1', '41da76f0fc3ec62a6939e634bfb6a342', '1518060478', 'testuser1@qq.com', 0, '2019-03-14', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:16:13', NULL);
INSERT INTO `user` VALUES (23, 'testuser11', '41da76f0fc3ec62a6939e634bfb6a342', '1518060478', 'testuser1@qq.com', 0, '2019-03-25', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:16:14', NULL);
INSERT INTO `user` VALUES (24, 'testuser2', '58dd024d49e1d1b83a5d307f09f32734', '1518060478', 'testuser2@qq.com', 0, '2019-03-04', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:22:36', NULL);
INSERT INTO `user` VALUES (25, 'testuser3', '1e4332f65a7a921075fbfb92c7c60cce', '1518060478', 'testuser3@qq.com', 0, '2019-03-04', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:24:30', NULL);
INSERT INTO `user` VALUES (26, '123', '202cb962ac59075b964b07152d234b70', '1518060478', '123', 0, '2019-02-25', 'http://localhost:9000/static/images/default.jpg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-07 17:36:55', NULL);

SET FOREIGN_KEY_CHECKS = 1;
