/*
 Navicat Premium Data Transfer

 Source Server         : server
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : ddns.webbershaw.fun:3306
 Source Schema         : relationship

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 15/03/2023 11:28:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class-task
-- ----------------------------
DROP TABLE IF EXISTS `class-task`;
CREATE TABLE `class-task` (
  `class_id` int NOT NULL,
  `task_id` int NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `invisible` tinyint DEFAULT '1',
  `accessible` tinyint DEFAULT '1',
  `weight` double DEFAULT NULL,
  `resubmit` int DEFAULT '1',
  `check_after_submit` tinyint DEFAULT '0',
  `correction_mode` tinyint DEFAULT NULL,
  `deleted` tinyint DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for student-class
-- ----------------------------
DROP TABLE IF EXISTS `student-class`;
CREATE TABLE `student-class` (
  `user_id` bigint NOT NULL,
  `class_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for student-task
-- ----------------------------
DROP TABLE IF EXISTS `student-task`;
CREATE TABLE `student-task` (
  `user_id` bigint NOT NULL,
  `task_id` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `score` double DEFAULT NULL,
  `accessible` tinyint NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for student-task-resource
-- ----------------------------
DROP TABLE IF EXISTS `student-task-resource`;
CREATE TABLE `student-task-resource` (
  `user_id` bigint NOT NULL,
  `task_id` int NOT NULL,
  `recource_id` int NOT NULL,
  `status` tinyint DEFAULT NULL,
  `score` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
