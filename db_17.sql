/*
 Navicat Premium Data Transfer

 Source Server         : it4409
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : db_17

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 11/04/2021 21:54:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `publisher` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `price` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (4, 'Harry Potter and the Sorcerer\'s Stone, Book 1, ', ' Pottermore Publishing, P', 10000000);
INSERT INTO `book` VALUES (5, 'From Blood and Ash', 'Jennifer L. Armentrout ', 1000000);
INSERT INTO `book` VALUES (6, 'The Women of the Bible Speak: The Wisdom of 16 Women and Their Lessons for Today', 'Shannon Bream', 23666666);
INSERT INTO `book` VALUES (7, 'Faucian Bargain: The Most Powerful and Dangerous Bureaucrat in American History', 'Steve Deace, Todd Erzen ', 3000000);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('test123', 'test123');

SET FOREIGN_KEY_CHECKS = 1;
