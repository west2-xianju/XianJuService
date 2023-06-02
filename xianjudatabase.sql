/*
 Navicat MySQL Data Transfer

 Source Server         : 121.40.105.104
 Source Server Type    : MySQL
 Source Server Version : 50568 (5.5.68-MariaDB)
 Source Host           : 121.40.105.104:3306
 Source Schema         : xianjudatabase

 Target Server Type    : MySQL
 Target Server Version : 50568 (5.5.68-MariaDB)
 File Encoding         : 65001

 Date: 02/06/2023 11:14:49
*/
USE xianjudatabase;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for bid
-- ----------------------------
DROP TABLE IF EXISTS `bid`;
CREATE TABLE `bid`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '出价id',
  `from_user` int(11) NOT NULL COMMENT '用户id',
  `good_id` int(11) NOT NULL COMMENT '货物id',
  `price` decimal(10, 0) NOT NULL COMMENT '价格',
  `time` datetime NOT NULL COMMENT '出价时间',
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `rejected` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid
-- ----------------------------
INSERT INTO `bid` VALUES (2, 234154, 9138300, 29699618, 424, '2023-04-26 16:50:49', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (3, 43151, 516167, 29699618, 283, '2023-04-27 14:37:02', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (4, 3215, 9138300, 345364, 333, '2023-05-28 20:17:24', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (5, 31533, 516167, 345364, 222, '2023-05-28 20:17:39', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (6, 231345, 9138300, 2134523, 333, '2023-05-28 20:18:03', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (7, 32515, 516167, 2134523, 222, '2023-05-28 20:18:18', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (8, 531565, 9138300, 321455, 333, '2023-05-28 20:18:43', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (9, 43256, 516167, 321455, 222, '2023-05-28 20:18:56', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (10, 54267, 9138300, 125435, 333, '2023-05-28 20:19:17', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (11, 32567, 516167, 125435, 222, '2023-05-28 20:19:32', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (12, 456634, 9138300, 1234566, 333, '2023-05-28 20:19:54', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (13, 65375, 516167, 1234566, 222, '2023-05-28 20:20:05', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (14, 73765, 9138300, 34245, 333, '2023-05-28 20:20:23', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (15, 47864, 516167, 34245, 222, '2023-05-28 20:20:34', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (16, 53735, 9138300, 24115, 333, '2023-05-28 20:20:50', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (17, 567845, 516167, 24115, 222, '2023-05-28 20:21:03', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (18, 7858745, 9138300, 5346543, 333, '2023-05-28 20:21:23', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (19, 325754, 516167, 5346543, 222, '2023-05-28 20:21:39', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (20, 5756476, 9138300, 564578, 333, '2023-05-28 20:22:02', NULL, NULL, 0, 0);
INSERT INTO `bid` VALUES (21, 563563, 516167, 564578, 222, '2023-05-28 20:22:11', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '货物id',
  `seller_id` int(11) NOT NULL COMMENT '卖家id',
  `game` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属游戏',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '货物描述',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主图',
  `price` decimal(10, 0) NOT NULL COMMENT '价格',
  `publish_time` datetime NULL DEFAULT NULL,
  `state` enum('01','02','03','04','05','06','07') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '货物状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (6, 29699618, 9138300, '格', '要等在每县', '哈哈', 'http://dummyimage.com/400x400', 233, '2023-04-24 17:16:46', '02');
INSERT INTO `goods` VALUES (7, 3367469, 9138300, '装哈', '能可精精间', '2333', 'http://dummyimage.com/400x400', 49, '2023-05-28 18:09:01', '06');
INSERT INTO `goods` VALUES (8, 75628867, 9138300, '市', '展会约哈哈', '23333333', 'http://dummyimage.com/400x400', 133, NULL, '01');
INSERT INTO `goods` VALUES (10, 345364, 9138300, '吴大维', '的娃都会', '8906857', 'http://dummyimage.com/400x400', 897, '2023-05-28 18:09:04', '02');
INSERT INTO `goods` VALUES (11, 2134523, 81970738, '问答题', '我dart我他', '爱他发生的', 'http://dummyimage.com/400x400', 3214, '2023-05-28 18:10:08', '02');
INSERT INTO `goods` VALUES (12, 321455, 81970738, '撒反悔哦', ' 达瓦闰土股份', '德瓦达发顺丰', 'http://dummyimage.com/400x400', 321, '2023-05-28 18:12:22', '02');
INSERT INTO `goods` VALUES (13, 125435, 516167, '贵哦挖的', '的watt', '符廷贵我iu回复第', 'http://dummyimage.com/400x400', 1234, '2023-05-28 19:22:44', '02');
INSERT INTO `goods` VALUES (14, 1234566, 516167, '规划ida', '达娃躺王', '等我阿3人3他', 'http://dummyimage.com/400x400', 213, '2023-05-28 19:23:39', '02');
INSERT INTO `goods` VALUES (15, 34245, 50068513, '粉砂岩', '福斯特有', '分手粉丝粉条', 'http://dummyimage.com/400x400', 314, '2023-05-28 19:24:30', '02');
INSERT INTO `goods` VALUES (16, 24115, 50068513, '达娃飞艇', '粉色有', 'g人的夙愿', 'http://dummyimage.com/400x400', 563, '2023-05-28 19:25:00', '02');
INSERT INTO `goods` VALUES (17, 5346543, 51707233, '但是他个月', '黑人杜的与人', '会发光的活动', 'http://dummyimage.com/400x400', 325, '2023-05-28 19:25:38', '02');
INSERT INTO `goods` VALUES (18, 564578, 51707233, '说废话 ', '大腿根给', '达瓦认同她', 'http://dummyimage.com/400x400', 3425, '2023-05-28 19:26:16', '02');
INSERT INTO `goods` VALUES (19, 62467, 9138300, '打他阿塔', '的撒跟我给', '三个和尚', 'http://dummyimage.com/400x400', 5346, NULL, '01');
INSERT INTO `goods` VALUES (20, 1245522, 9138300, '达瓦更为光荣', '菲大使馆热缩管', '放入温水歌手队', 'http://dummyimage.com/400x400', 453, '2023-05-28 19:34:17', '03');
INSERT INTO `goods` VALUES (21, 32145, 9138300, '翻挖归属感', '放大三个人色鬼', '发惹萨哥染色', 'http://dummyimage.com/400x400', 4321, '2023-05-28 20:01:24', '03');
INSERT INTO `goods` VALUES (22, 43255, 9138300, '阿斯蒂芬阿三', '发费奥格瑞', '阿飞如果是如果', 'http://dummyimage.com/400x400', 324, '2023-05-28 20:01:46', '03');
INSERT INTO `goods` VALUES (23, 432532, 9138300, '是果然是高手', '给惹萨哥染色', '三个人受伤果然是个', 'http://dummyimage.com/400x400', 342, '2023-05-28 20:06:21', '04');
INSERT INTO `goods` VALUES (24, 436566, 9138300, '发给同事', ' 人身上果然是', '果然是个洞庭湖', 'http://dummyimage.com/400x400', 432, '2023-05-28 20:11:45', '05');
INSERT INTO `goods` VALUES (25, 747664, 9138300, '事故后附加费', '挺好的就有点费劲', '行不通的合同的', 'http://dummyimage.com/400x400', 4567, '2023-05-28 20:12:16', '06');
INSERT INTO `goods` VALUES (26, 65784, 9138300, '发送给他电话', '共同的话题的回忆', '都会遇到建议大家', 'http://dummyimage.com/400x400', 765, '2023-05-28 20:12:37', '07');
INSERT INTO `goods` VALUES (27, 5436443, 9138300, '第三个是挺好', '光辐射大概三点', '人神共愤是德国', 'http://dummyimage.com/400x400', 325, '2023-05-28 20:24:11', '03');
INSERT INTO `goods` VALUES (28, 435744, 9138300, '法国使馆土地上', '公司的人士提供', '固然是公司沟通的是', 'http://dummyimage.com/400x400', 432, '2023-05-28 20:25:47', '03');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '订单id',
  `from_id` int(11) NOT NULL COMMENT '付款方',
  `to_id` int(11) NOT NULL COMMENT '收款方',
  `good_id` int(11) NOT NULL COMMENT '货物id',
  `price` decimal(10, 0) NOT NULL COMMENT '价格',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `state` enum('01','02','03','04','05','06') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (9, 1435151, 516167, 9138300, 1245522, 453, '2023-05-28 19:46:29', '2023-05-28 19:46:40', NULL, NULL, '01');
INSERT INTO `order` VALUES (10, 43151, 516167, 9138300, 32145, 4321, '2023-05-28 20:04:19', '2023-05-28 20:04:21', NULL, NULL, '02');
INSERT INTO `order` VALUES (11, 432151, 516167, 9138300, 43255, 324, '2023-05-28 20:05:20', '2023-05-28 20:05:21', NULL, NULL, '03');
INSERT INTO `order` VALUES (12, 312451, 516167, 9138300, 432532, 342, '2023-05-28 20:08:50', '2023-05-28 20:08:52', NULL, NULL, '06');
INSERT INTO `order` VALUES (13, 65363, 516167, 9138300, 5436443, 325, '2023-05-28 20:28:54', '2023-05-28 20:28:56', NULL, NULL, '04');
INSERT INTO `order` VALUES (14, 542542, 516167, 9138300, 435744, 432, '2023-05-28 20:30:34', '2023-05-28 20:30:35', NULL, NULL, '05');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `updated_at` datetime NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `register_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `blocked` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (NULL, NULL, 8, 9138300, 'gaoyifei', 'miaoshafeige', '$2a$10$qmQ73q0E4fJKrX0b4VC8JOBgHfiJj5SaVgWyRSZZ8LKGPRkPEKZn2', 'gyfcs@163.com', '史敏', '350924200402100052', '2023-04-10 15:22:42', 'https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp', 0);
INSERT INTO `user` VALUES (NULL, NULL, 10, 81970738, 'gaoyifei2', 'feifeifei2', '$2a$10$vwopyJA0EltN6T8nkUMmKO0XZMOzr43N9ZjcUwKn7j94IoeFxLMRu', 'i.ivo@vcjgkahn.gf', NULL, NULL, '2023-04-16 01:24:18', 'https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp', 0);
INSERT INTO `user` VALUES (NULL, NULL, 11, 516167, 'gaoyifei3', 'feifeifei3', '$2a$10$zwRWEmfIWS1WeM2wRFfT.e34TDOQdL3i9ohrEy60lRZeLUih0voNq', 'i.ivo@vcjgkahn.gf', NULL, NULL, '2023-04-16 01:39:32', 'https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp', 0);
INSERT INTO `user` VALUES (NULL, NULL, 18, 50068513, '323323', 'momo', '$2a$10$N8UKR1X3FEYlLTdTC4didOf8fx1S9TeXH2bzdBReRm6F4L6Z99Qpe', 'gaoyifei040229@163.com', NULL, NULL, '2023-05-21 23:08:57', 'https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp', 0);
INSERT INTO `user` VALUES (NULL, NULL, 19, 51707233, '333', '333', '$2a$10$ERZvTosRlraob4BNBJY0.uWdvg5DQTZUgBFsR19WsgZHf4cLRTiLC', 'gaoyifei040229@163.com', NULL, NULL, '2023-05-22 00:04:47', 'https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp', 0);
INSERT INTO `user` VALUES (NULL, NULL, 20, 10420385, 'b2b2b2', 'bbb', '$2a$10$k0mIRq54bVJRVsB6BH.EsuH6t3InrtSjeOfU8EZvza3oDjNRxPMaC', 'gaoyifei040229@163.com', NULL, NULL, '2023-05-28 20:47:40', 'https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp', 1);

-- ----------------------------
-- Table structure for usercomment
-- ----------------------------
DROP TABLE IF EXISTS `usercomment`;
CREATE TABLE `usercomment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '评价id',
  `from_id` int(11) NOT NULL COMMENT '评价人id',
  `to_id` int(11) NOT NULL COMMENT '被评价人id',
  `good_id` int(11) NOT NULL COMMENT '货物id',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `time` datetime NOT NULL COMMENT '评价时间',
  `star` int(11) NOT NULL COMMENT '评价星级',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '评价修改时间',
  `comment_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评价图片',
  `modified` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of usercomment
-- ----------------------------
INSERT INTO `usercomment` VALUES (12, 34125, 9138300, 81970738, 341245, '的方式个人收入', '2023-05-28 20:37:19', 5, NULL, 'http://dummyimage.com/400x400', 0);
INSERT INTO `usercomment` VALUES (13, 43165, 81970738, 9138300, 542345, '肺癌给恶法饿啊', '2023-05-28 20:38:36', 4, NULL, 'http://dummyimage.com/400x400', 0);
INSERT INTO `usercomment` VALUES (14, 432113, 81970738, 9138300, 653633, '刚认识果然是公司规定', '2023-05-28 20:39:58', 5, NULL, 'http://dummyimage.com/400x400', 0);
INSERT INTO `usercomment` VALUES (15, 432153, 516167, 81970738, 653473, '公司如果效果好电视台', '2023-05-28 20:40:39', 2, NULL, 'http://dummyimage.com/400x400', 0);
INSERT INTO `usercomment` VALUES (16, 452532, 50068513, 516167, 43512, '的好地方火眼金睛', '2023-05-28 20:41:18', 5, NULL, 'http://dummyimage.com/400x400', 0);
INSERT INTO `usercomment` VALUES (17, 65478, 50068513, 516167, 5436548, '不同的含有较多的', '2023-05-28 20:41:41', 4, NULL, 'http://dummyimage.com/400x400', 0);
INSERT INTO `usercomment` VALUES (18, 45464, 51707233, 50068513, 543523, '化工等行业飞机能否', '2023-05-28 20:42:44', 5, '2023-05-28 20:42:55', 'http://dummyimage.com/400x400', 1);
INSERT INTO `usercomment` VALUES (19, 563433, 51707233, 50068513, 76547, '公布的一份记忆等等', '2023-05-28 20:43:23', 3, NULL, 'http://dummyimage.com/400x400', 0);
INSERT INTO `usercomment` VALUES (20, 653474, 81970738, 9138300, 765864, '公司的合约的', '2023-05-28 20:43:57', 4, '2023-05-28 20:44:03', 'http://dummyimage.com/400x400', 1);
INSERT INTO `usercomment` VALUES (21, 874658, 9138300, 51707233, 342455, '股东会以后大家', '2023-05-28 20:44:29', 5, NULL, 'http://dummyimage.com/400x400', 0);
INSERT INTO `usercomment` VALUES (22, 5436, 516167, 51707233, 35673567, '并同意大家痛苦', '2023-05-28 20:44:45', 4, NULL, 'http://dummyimage.com/400x400', 0);

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '钱包id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `balance` decimal(10, 0) NOT NULL COMMENT '余额',
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `blocked` tinyint(1) NOT NULL DEFAULT 0 COMMENT '风控状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES (9, 431515, 9138300, 9999, NULL, NULL, 0);
INSERT INTO `wallet` VALUES (10, 32412, 81970738, 34255, NULL, NULL, 0);
INSERT INTO `wallet` VALUES (11, 351451, 516167, 54256, NULL, NULL, 0);
INSERT INTO `wallet` VALUES (12, 5431313, 50068513, 543667, NULL, NULL, 0);
INSERT INTO `wallet` VALUES (13, 4325644, 51707233, 54325, NULL, NULL, 0);
INSERT INTO `wallet` VALUES (14, 38759360, 10420385, 0, NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
