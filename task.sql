/*
 Navicat Premium Data Transfer

 Source Server         : server
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : ddns.webbershaw.fun:3306
 Source Schema         : task

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 15/03/2023 11:29:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resource_id` int NOT NULL AUTO_INCREMENT,
  `resource_type` tinyint DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` longtext COLLATE utf8mb4_general_ci,
  `resource_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  `classification_id` int DEFAULT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL,
  `task_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `task_type` tinyint DEFAULT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of task
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for task-resource
-- ----------------------------
DROP TABLE IF EXISTS `task-resource`;
CREATE TABLE `task-resource` (
  `task_id` int NOT NULL,
  `resource_id` int NOT NULL,
  `score` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of task-resource
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
