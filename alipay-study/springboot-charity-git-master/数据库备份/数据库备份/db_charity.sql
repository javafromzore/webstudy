/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : db_charity

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 09/07/2023 22:29:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dona_item
-- ----------------------------
DROP TABLE IF EXISTS `dona_item`;
CREATE TABLE `dona_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dona_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `money` float NOT NULL,
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_item_dona`(`dona_id`) USING BTREE,
  INDEX `FK_item_user`(`user_id`) USING BTREE,
  CONSTRAINT `FK_item_dona` FOREIGN KEY (`dona_id`) REFERENCES `donation` (`dona_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_item_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dona_item
-- ----------------------------
INSERT INTO `dona_item` VALUES (1, 2, 1, 1, '2022-02-20 16:14:21');
INSERT INTO `dona_item` VALUES (2, 2, 1, 3, '2022-02-24 14:29:59');
INSERT INTO `dona_item` VALUES (3, 2, 1, 4, '2022-02-24 14:30:06');
INSERT INTO `dona_item` VALUES (4, 2, 1, 10, '2022-02-24 14:38:42');
INSERT INTO `dona_item` VALUES (5, 2, 1, 11, '2022-02-24 14:38:46');
INSERT INTO `dona_item` VALUES (6, 2, 1, 1, '2022-03-05 13:28:10');
INSERT INTO `dona_item` VALUES (7, 2, 1, 1, '2022-03-05 13:45:23');
INSERT INTO `dona_item` VALUES (8, 2, 1, 2, '2022-03-05 13:48:26');
INSERT INTO `dona_item` VALUES (9, 2, 1, 3, '2022-03-05 13:51:04');
INSERT INTO `dona_item` VALUES (10, 2, 1, 4, '2022-03-05 13:56:53');
INSERT INTO `dona_item` VALUES (11, 2, 1, 1, '2022-04-05 10:55:02');
INSERT INTO `dona_item` VALUES (12, 2, 1, 1, '2022-04-05 12:45:24');

-- ----------------------------
-- Table structure for donation
-- ----------------------------
DROP TABLE IF EXISTS `donation`;
CREATE TABLE `donation`  (
  `dona_id` int(11) NOT NULL AUTO_INCREMENT,
  `dona_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `dona_detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dona_state` int(11) NOT NULL,
  `dona_total` float NOT NULL,
  `dona_current` float DEFAULT NULL,
  PRIMARY KEY (`dona_id`) USING BTREE,
  INDEX `FK_donation_user`(`user_id`) USING BTREE,
  CONSTRAINT `FK_donation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of donation
-- ----------------------------
INSERT INTO `donation` VALUES (1, '疾病', 1, '家里无法承担医疗费用', 0, 100000, 0);
INSERT INTO `donation` VALUES (2, '读书', 1, '家里交不起学费', 1, 10000, 42);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_type` int(11) NOT NULL,
  `user_gender` int(11) NOT NULL,
  `user_age` int(11) NOT NULL,
  `user_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_donate` float DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_account`(`user_account`) USING BTREE,
  INDEX `FK_user_category`(`user_type`) USING BTREE,
  CONSTRAINT `FK_user_category` FOREIGN KEY (`user_type`) REFERENCES `user_category` (`cate_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'hhb', 'admin', '123456', NULL, 1, 1, 18, '17307337585', 0);
INSERT INTO `user` VALUES (2, 'kkkkk', 'likui', '123456', NULL, 1, 0, 20, '17307337585', 0);
INSERT INTO `user` VALUES (3, 'lihua', 'lihua', '123456', NULL, 1, 0, 20, '17307337585', 0);

-- ----------------------------
-- Table structure for user_category
-- ----------------------------
DROP TABLE IF EXISTS `user_category`;
CREATE TABLE `user_category`  (
  `cate_id` int(11) NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`cate_id`) USING BTREE,
  UNIQUE INDEX `cate_name`(`cate_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_category
-- ----------------------------
INSERT INTO `user_category` VALUES (1, ' 普通用户');
INSERT INTO `user_category` VALUES (3, '慈善组织');
INSERT INTO `user_category` VALUES (2, '管理员');

SET FOREIGN_KEY_CHECKS = 1;
