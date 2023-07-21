/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : db_charity_2

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 09/07/2023 22:28:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for charity
-- ----------------------------
DROP TABLE IF EXISTS `charity`;
CREATE TABLE `charity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目名称',
  `user_id` int(11) NOT NULL COMMENT '发起人',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(1) NOT NULL,
  `total` float(20, 0) NOT NULL COMMENT '目标金额',
  `current` float(20, 0) DEFAULT NULL,
  `type_id` int(11) NOT NULL COMMENT '爱心项目类别id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `type_id`(`type_id`) USING BTREE,
  CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `charity_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charity
-- ----------------------------
INSERT INTO `charity` VALUES (1, '免费午餐', 3, '免费午餐坚持“师生同食、就地取材、透明公开、村校联合”学校执行的四项基本原则，保障为孩子们提供安全、营养的免费午餐。', 'http://localhost:8082/files/f20710616dc04bc080b4d953f7d3a490', 1, 5000, 1651, 2);
INSERT INTO `charity` VALUES (2, '爱心包裹', 3, '爱心包裹项目是在国家乡村振兴局的指导下,由中国扶贫基金会联合中国邮政集团公司、电影频道于2009年4月26日共同发起的一项全民公益活动。', 'http://localhost:8082/files/f5374b383c8b44db8ff0b59515ec1aa3', 1, 4000, 1166, 3);
INSERT INTO `charity` VALUES (3, '爱心项目', 3, '33333333333', 'http://localhost:8082/files/8ac646f99f1c44cb973f0c15aecbeb90', 1, 300, 0, 2);

-- ----------------------------
-- Table structure for charity_item
-- ----------------------------
DROP TABLE IF EXISTS `charity_item`;
CREATE TABLE `charity_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `charity_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `money` float(20, 0) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_item_charity`(`charity_id`) USING BTREE,
  INDEX `FK_charity_user`(`user_id`) USING BTREE,
  CONSTRAINT `FK_charity_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_item_charity` FOREIGN KEY (`charity_id`) REFERENCES `charity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charity_item
-- ----------------------------
INSERT INTO `charity_item` VALUES (2, 1, 1, 1, '2022-01-18 14:54:13');
INSERT INTO `charity_item` VALUES (3, 2, 1, 66, '2022-04-19 22:49:54');
INSERT INTO `charity_item` VALUES (4, 1, 10, 30, '2021-08-20 15:29:01');
INSERT INTO `charity_item` VALUES (5, 1, 10, 20, '2021-11-20 15:30:37');
INSERT INTO `charity_item` VALUES (6, 1, 1, 100, '2022-02-07 23:57:55');
INSERT INTO `charity_item` VALUES (7, 1, 1, 1000, '2021-12-13 13:35:09');
INSERT INTO `charity_item` VALUES (8, 2, 1, 1100, '2021-09-13 13:36:17');
INSERT INTO `charity_item` VALUES (9, 1, 1, 500, '2022-05-13 13:37:21');

-- ----------------------------
-- Table structure for charity_type
-- ----------------------------
DROP TABLE IF EXISTS `charity_type`;
CREATE TABLE `charity_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charity_type
-- ----------------------------
INSERT INTO `charity_type` VALUES (2, '教育助学', '主要为了帮助学生提供教育资源和物质资源');
INSERT INTO `charity_type` VALUES (3, '扶贫救灾', '帮助贫困地区和灾难地区');

-- ----------------------------
-- Table structure for dona_item
-- ----------------------------
DROP TABLE IF EXISTS `dona_item`;
CREATE TABLE `dona_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dona_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `money` float NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_item_dona`(`dona_id`) USING BTREE,
  INDEX `FK_item_user`(`user_id`) USING BTREE,
  CONSTRAINT `FK_item_dona` FOREIGN KEY (`dona_id`) REFERENCES `donation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_item_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dona_item
-- ----------------------------
INSERT INTO `dona_item` VALUES (1, 2, 1, 1, '2022-01-05 15:45:50');
INSERT INTO `dona_item` VALUES (2, 3, 1, 2, '2022-04-05 16:03:03');
INSERT INTO `dona_item` VALUES (3, 2, 1, 2, '2021-06-05 16:05:20');
INSERT INTO `dona_item` VALUES (4, 3, 1, 10, '2021-08-05 16:08:38');
INSERT INTO `dona_item` VALUES (5, 3, 1, 5, '2021-12-05 16:12:17');
INSERT INTO `dona_item` VALUES (6, 8, 1, 1, '2022-04-15 20:40:53');
INSERT INTO `dona_item` VALUES (7, 8, 1, 2, '2022-04-18 01:04:38');
INSERT INTO `dona_item` VALUES (8, 2, 10, 20, '2022-04-20 14:57:02');
INSERT INTO `dona_item` VALUES (9, 8, 10, 15, '2022-01-20 14:58:31');
INSERT INTO `dona_item` VALUES (10, 8, 12, 100, '2022-05-05 21:59:16');
INSERT INTO `dona_item` VALUES (11, 10, 1, 100, '2022-03-07 18:16:57');
INSERT INTO `dona_item` VALUES (12, 11, 2, 200, '2022-05-08 10:51:19');
INSERT INTO `dona_item` VALUES (13, 11, 2, 1800, '2021-09-08 10:52:50');
INSERT INTO `dona_item` VALUES (14, 8, 1, 1, '2022-05-08 19:37:56');
INSERT INTO `dona_item` VALUES (15, 8, 1, 1, '2022-05-08 19:38:32');
INSERT INTO `dona_item` VALUES (16, 8, 1, 1, '2022-05-08 19:39:08');
INSERT INTO `dona_item` VALUES (17, 8, 1, 1, '2022-05-08 19:50:05');
INSERT INTO `dona_item` VALUES (18, 8, 1, 1, '2022-05-08 19:50:37');
INSERT INTO `dona_item` VALUES (19, 8, 1, 1, '2022-05-08 19:51:04');
INSERT INTO `dona_item` VALUES (20, 8, 1, 1, '2022-05-08 19:51:32');
INSERT INTO `dona_item` VALUES (21, 8, 1, 1, '2022-05-08 19:52:12');
INSERT INTO `dona_item` VALUES (22, 10, 1, 1000, '2022-02-13 13:31:58');
INSERT INTO `dona_item` VALUES (23, 3, 1, 1500, '2021-12-13 13:33:36');

-- ----------------------------
-- Table structure for donation
-- ----------------------------
DROP TABLE IF EXISTS `donation`;
CREATE TABLE `donation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  `total` float NOT NULL,
  `current` float DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_donation_user`(`user_id`) USING BTREE,
  CONSTRAINT `FK_donation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of donation
-- ----------------------------
INSERT INTO `donation` VALUES (2, '读书', 1, '交不起学费', 1, 10000, 23);
INSERT INTO `donation` VALUES (3, '突发意外', 2, '突发意外', 1, 5000, 1517);
INSERT INTO `donation` VALUES (4, '救助空巢老人', 1, '为贫困空巢老人送去日常生活物资与生活补助，帮助老人们减轻生活压力。', 2, 5000, 0);
INSERT INTO `donation` VALUES (8, '免费午餐', 1, '免费午餐坚持“师生同食、就地取材、透明公开、村校联合”学校执行的四项基本原则，保障为孩子们提供安全、营养的免费午餐。', 1, 3000, 126);
INSERT INTO `donation` VALUES (10, '健康课桌', 1, '让大山里的孩子用上适合他们身体发育的、设计科学的学生专用课桌椅', 1, 7000, 1100);
INSERT INTO `donation` VALUES (11, '求助', 1, '333333333', 1, 2000, 2000);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT 0,
  `enable` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, '慈善组织');
INSERT INTO `role` VALUES (1, '普通用户');
INSERT INTO `role` VALUES (3, '管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role` int(11) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `donate` float unsigned,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  INDEX `FK_user_category`(`role`) USING BTREE,
  CONSTRAINT `FK_user_category` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'hhb', 'admin', '1234567', 'http://localhost:8082/files/2ee79e4b55ee4df496bec5fb89d627ef', 1, 1, 22, '17307337585', 20);
INSERT INTO `user` VALUES (2, 'aaa', 'abc', '123456', NULL, 1, 0, 21, '17307337585', NULL);
INSERT INTO `user` VALUES (3, '中国扶贫基金会', 'zhangwei', '1234567', 'http://localhost:8082/files/f20710616dc04bc080b4d953f7d3a490', 2, 1, NULL, '17307337585', NULL);
INSERT INTO `user` VALUES (4, 'manage', 'lihua', '123456', 'http://localhost:8082/files/703a41cd96d24c7c94f535a82df8bad4', 3, NULL, NULL, '', NULL);
INSERT INTO `user` VALUES (10, 'Daniel', 'likui', '123456', 'http://localhost:8082/files/7f0b42215b3047f69cfc5d6fbb41fe18', 1, 1, 22, '17307337585', NULL);
INSERT INTO `user` VALUES (11, 'dasdsad', 'dasdasd', '123456', NULL, 1, NULL, NULL, '17307337585', NULL);
INSERT INTO `user` VALUES (12, 'Tim', 'zhangfei', '123456', NULL, 1, 1, 40, '17307337585', NULL);

SET FOREIGN_KEY_CHECKS = 1;
