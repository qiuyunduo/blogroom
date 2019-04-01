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

 Date: 01/04/2019 12:09:34
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
  `user2_id` mediumint(9) NOT NULL COMMENT '被关注者ID',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '记录添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attention
-- ----------------------------
INSERT INTO `attention` VALUES (1, 15, 34, '2019-03-20 19:21:56', NULL);
INSERT INTO `attention` VALUES (2, 15, 33, '2019-03-20 19:51:03', NULL);
INSERT INTO `attention` VALUES (3, 15, 32, '2019-03-20 19:51:15', NULL);
INSERT INTO `attention` VALUES (4, 15, 30, '2019-03-20 19:51:19', NULL);
INSERT INTO `attention` VALUES (5, 15, 27, '2019-03-20 19:51:24', NULL);
INSERT INTO `attention` VALUES (6, 15, 23, '2019-03-20 19:51:34', NULL);
INSERT INTO `attention` VALUES (7, 15, 25, '2019-03-20 19:51:38', NULL);
INSERT INTO `attention` VALUES (8, 15, 26, '2019-03-20 20:30:25', NULL);
INSERT INTO `attention` VALUES (18, 26, 15, '2019-03-25 16:40:53', NULL);
INSERT INTO `attention` VALUES (19, 26, 33, '2019-03-25 16:41:42', NULL);
INSERT INTO `attention` VALUES (23, 26, 34, '2019-03-26 19:40:43', NULL);
INSERT INTO `attention` VALUES (24, 26, 22, '2019-03-27 09:48:36', NULL);
INSERT INTO `attention` VALUES (25, 35, 34, '2019-03-27 20:23:54', NULL);
INSERT INTO `attention` VALUES (26, 35, 32, '2019-03-27 20:23:58', NULL);
INSERT INTO `attention` VALUES (27, 35, 30, '2019-03-27 20:24:02', NULL);
INSERT INTO `attention` VALUES (28, 35, 27, '2019-03-27 20:24:11', NULL);
INSERT INTO `attention` VALUES (29, 35, 23, '2019-03-27 20:24:15', NULL);
INSERT INTO `attention` VALUES (30, 26, 23, '2019-03-29 19:03:49', NULL);
INSERT INTO `attention` VALUES (31, 26, 35, '2019-03-29 19:03:56', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
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
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '1518060478', '123456@qq.com', 1, '2016-09-14', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', '一个刚刚进入博客屋的小白shu', 0, '192.168.1.168', '2018-08-21 10:33:56', '2019-02-22 10:47:12');
INSERT INTO `user` VALUES (2, 'qyd2', '202cb962ac59075b964b07152d234b70', 'windGIrlasd', '1518060478', '456@qq.com', 1, '2019-03-04', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', 'I am shy...', 0, '192.168.1.168', '2018-08-21 10:34:49', '2019-03-20 14:40:57');
INSERT INTO `user` VALUES (15, '苏宏鹏', '202cb962ac59075b964b07152d234b70', '苏宏鹏', '1518060478', 'suhongpeng@aliyun.com', 1, '2019-03-17', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', '软件3班－苏鸿鹏报道', 0, '192.168.1.168', '2018-08-21 21:22:58', '2019-03-20 20:04:22');
INSERT INTO `user` VALUES (16, 'test1', '202cb962ac59075b964b07152d234b70', 'test1', '1518060478', '123@qq.com', 1, '2019-03-10', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', '帅', 0, '192.168.1.168', '2018-08-21 21:54:14', NULL);
INSERT INTO `user` VALUES (17, 'test2', '202cb962ac59075b964b07152d234b70', 'test2', '1518060478', 'test2@aliyun.com', 1, '2019-03-14', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2018-08-21 21:55:24', NULL);
INSERT INTO `user` VALUES (18, 'shp', '202cb962ac59075b964b07152d234b70', 'shp', '1518060478', 'suhongpen@xinlang.com', 1, '2019-02-25', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', '苏宏鹏小号', 0, '192.168.1.168', '2018-08-22 08:54:14', NULL);
INSERT INTO `user` VALUES (19, 'qiuyunduo', '202cb962ac59075b964b07152d234b70', 'qiuyunduo', '1518060478', 'qiuyunduo@aliyun.com', 1, '2019-03-12', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', '已经是博客屋的一名老将了', 0, '192.168.1.168', '2018-08-22 21:40:14', '2019-03-20 12:47:37');
INSERT INTO `user` VALUES (20, '1', '202cb962ac59075b964b07152d234b70', '1111111', '1518060478', '123@qq.com', 1, '2019-03-03', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '北京', 'java程序员', '天不生我李淳罡，万古剑道入长夜。', 0, '192.168.1.168', '2018-08-23 10:26:31', '2019-03-20 14:55:07');
INSERT INTO `user` VALUES (21, 'asd', '7815696ecbf1c96e6894b779456d330e', 'asd', '1518060478', 'asd', 0, '2019-03-04', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:01:43', NULL);
INSERT INTO `user` VALUES (22, 'testuser1', '41da76f0fc3ec62a6939e634bfb6a342', 'testuser1', '1518060478', 'testuser1@qq.com', 0, '2019-03-14', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:16:13', NULL);
INSERT INTO `user` VALUES (23, 'testuser11', '41da76f0fc3ec62a6939e634bfb6a342', 'testuser11', '1518060478', 'testuser1@qq.com', 0, '2019-03-25', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:16:14', NULL);
INSERT INTO `user` VALUES (24, 'testuser2', '58dd024d49e1d1b83a5d307f09f32734', 'testuser2', '1518060478', 'testuser2@qq.com', 0, '2019-03-04', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:22:36', NULL);
INSERT INTO `user` VALUES (25, 'testuser3', '1e4332f65a7a921075fbfb92c7c60cce', 'testuser3', '1518060478', 'testuser3@qq.com', 0, '2019-03-04', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '深圳', 'java程序员', NULL, 0, '192.168.1.168', '2019-03-05 17:24:30', NULL);
INSERT INTO `user` VALUES (26, '123', '202cb962ac59075b964b07152d234b70', '褚禄山', '1518060478', '123', 1, '2019-02-03', 'http://localhost:9000/static/storage/itk7fwu75jf0stf0wasb.jpeg', '深圳', 'java程序员', '我是在进行项目重构时测试用户，如何123123123123', 0, '192.168.1.167', '2019-03-07 17:36:55', '2019-03-28 15:15:07');
INSERT INTO `user` VALUES (27, 'qiu', '202cb962ac59075b964b07152d234b70', '风吹起的落叶', NULL, 'qiuyunduo@qq.com', 1, '2019-03-04', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', 'asd', 'asd', '天不生我李淳罡，万古剑道入长夜。', 0, NULL, '2019-03-20 09:47:03', '2019-03-20 14:57:13');
INSERT INTO `user` VALUES (30, 'test4', '202cb962ac59075b964b07152d234b70', '徐凤年', NULL, 'testuser4@qq.com', 1, '2019-03-19', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '襄樊', '剑客', '还个屁', 0, NULL, '2019-03-20 10:22:56', '2019-03-20 14:58:52');
INSERT INTO `user` VALUES (32, 'windBoy', '202cb962ac59075b964b07152d234b70', '风一样的男孩', NULL, 'windBoy@qq.com', 1, NULL, 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', NULL, NULL, NULL, 0, NULL, '2019-03-20 10:32:49', NULL);
INSERT INTO `user` VALUES (33, 'test5', '202cb962ac59075b964b07152d234b70', 'test5', NULL, 'testuser5@qq.com', 0, NULL, 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', NULL, NULL, NULL, 0, NULL, '2019-03-20 10:44:35', NULL);
INSERT INTO `user` VALUES (34, 'chenzhibao', '04b7ca0d73cd7117e29c04593201fb53', '儒圣-陈芝豹', NULL, 'chenzhibao@163.com', 1, '2019-03-20', 'http://localhost:9000/static/storage/0e6ej2p3bl3nn418392g.jpeg', '北凉', '北凉都护', '徐晓义子－最终离去到达蜀国成为蜀王', 0, '192.168.1.167', '2019-03-20 15:01:23', '2019-03-20 15:05:12');
INSERT INTO `user` VALUES (35, 'wangxianzhi', '202cb962ac59075b964b07152d234b70', '王仙芝-王老怪', NULL, 'wangxianzhi@qq.com', 1, '2019-03-11', 'http://localhost:9000/static/storage/cs6pp35z3pq04prq97hd.jpeg', '武帝城', '散人', '五百年一出的武夫，以一己之力镇压江湖五百年', 0, NULL, '2019-03-27 20:22:23', '2019-03-28 15:02:39');

SET FOREIGN_KEY_CHECKS = 1;
