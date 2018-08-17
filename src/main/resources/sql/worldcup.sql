/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : worldcup

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 16/08/2018 09:22:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'adm', '123456', 'ADMIN', '2018-08-13 14:10:38', '2018-08-13 14:10:52');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `file_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `publish_time` datetime(0) NULL DEFAULT NULL,
  `weight` tinyint(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 1, 'Dapps和传统WebApps区别', 'article_1_1.html', '2018-08-14 15:02:56', 2, 'Sticker', '2018-08-14 16:31:42', 0);
INSERT INTO `article` VALUES (2, 1, 'Ethereum以太坊中的gas、gas price、gas limit', 'article_1_2.html', '2018-08-14 15:06:41', 2, 'Sticker', '2018-08-14 16:31:39', 0);
INSERT INTO `article` VALUES (3, 1, 'JPA中@Query使用注意事项', 'article_1_3.html', '2018-08-14 15:14:19', 2, 'Sticker', '2018-08-14 16:29:39', 0);

-- ----------------------------
-- Table structure for article_type
-- ----------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `weight` tinyint(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_type
-- ----------------------------
INSERT INTO `article_type` VALUES (1, '区块链', 1, 0);
INSERT INTO `article_type` VALUES (2, 'Spring Boot', 1, 0);
INSERT INTO `article_type` VALUES (3, 'Spring Data', 1, 0);

-- ----------------------------
-- Table structure for login_user
-- ----------------------------
DROP TABLE IF EXISTS `login_user`;
CREATE TABLE `login_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `real_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `user_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `invest_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `registry_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000003 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_user
-- ----------------------------
INSERT INTO `login_user` VALUES (1000002, 'xjy', 'e6dba595bd8bcd070cf39859395e329f', '徐敬尧', 'stickerxu@hotmail.com', '17600205419', '6CC517ECC16AD8BE', '2018-08-14 14:30:11');

SET FOREIGN_KEY_CHECKS = 1;
