DROP TABLE IF EXISTS `tb_seckill_goods`;
CREATE TABLE `tb_seckill_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT NULL COMMENT 'spu ID',
  `item_id` bigint(20) DEFAULT NULL COMMENT 'sku ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `small_pic` varchar(150) DEFAULT NULL COMMENT '商品图片',
  `price` decimal(10,2) DEFAULT NULL COMMENT '原价格',
  `cost_price` decimal(10,2) DEFAULT NULL COMMENT '秒杀价格',
  `seller_id` varchar(100) DEFAULT NULL COMMENT '商家ID',
  `create_time` datetime DEFAULT NULL COMMENT '添加日期',
  `check_time` datetime DEFAULT NULL COMMENT '审核日期',
  `status` varchar(1) DEFAULT NULL COMMENT '审核状态',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `num` int(11) DEFAULT NULL COMMENT '秒杀商品数',
  `stock_count` int(11) DEFAULT NULL COMMENT '剩余库存数',
  `introduction` varchar(2000) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_seckill_goods
-- ----------------------------
INSERT INTO `tb_seckill_goods` VALUES (1, 149187842867960, NULL, '秒杀精品女装', 'http://img.mp.itc.cn/upload/20160804/6881885758bb42e09bff6e3d60d18230_th.jpg', 100.00, 0.01, 'qiandu', NULL, '2017-10-14 21:07:51', '1', '2017-10-14 18:07:27', '2017-10-14 18:07:31', 10, 5, NULL);
INSERT INTO `tb_seckill_goods` VALUES (2, 149187842867953, NULL, '轻轻奶茶', 'http://sem.g3img.com/site/50021489/image/c2_20190411232047_66099.jpg', 10.00, 0.01, 'yijia', NULL, NULL, '1', '2017-10-12 18:24:18', '2017-10-28 18:24:20', 10, 5, '清仓打折');
INSERT INTO `tb_seckill_goods` VALUES (3, 11, NULL, '11', 'http://i2.sinaimg.cn/ty/2014/0326/U5295P6DT20140326155117.jpg', 44.00, 0.03, NULL, NULL, NULL, '1', '2017-1-1 00:00:00', '2017-12-1 00:00:00', 10, 2, NULL);
INSERT INTO `tb_seckill_goods` VALUES (4, 2, NULL, '测试', 'http://www.cnr.cn/junshi/ztl/leifeng/smlf/201202/W020120226838451234901.jpg', 10.00, 0.01, 'qiandu', '2017-10-14 19:18:18', NULL, '0', '2017-11-11 00:00:00', '2017-11-11 23:59:59', 100, 99, NULL);
INSERT INTO `tb_seckill_goods` VALUES (5, NULL, NULL, '羽绒服', 'http://img14.360buyimg.com/popWaterMark/g13/M03/0A/1D/rBEhU1Kmlr8IAAAAAATBCejgYvoAAGmMAC0zhIABMEh349.jpg', 100.00, 0.02, 'qiandu', '2017-10-15 09:50:52', '2017-10-15 10:06:27', '1', '2017-10-10 00:00:00', '2017-11-11 23:59:59', 10, 10, '清仓打折');

-- ----------------------------
-- Table structure for `tb_seckill_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_seckill_order`;
CREATE TABLE `tb_seckill_order` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `seckill_id` bigint(20) DEFAULT NULL COMMENT '秒杀商品ID',
  `money` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户',
  `seller_id` varchar(50) DEFAULT NULL COMMENT '商家',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `status` varchar(1) DEFAULT NULL COMMENT '状态',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '收货人地址',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `receiver` varchar(20) DEFAULT NULL COMMENT '收货人',
  `transaction_id` varchar(30) DEFAULT NULL COMMENT '交易流水',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_seckill_order
-- ----------------------------
INSERT INTO `tb_seckill_order` VALUES ('919473120379723776', null, '0.02', 'lijialong', 'qiandu', '2017-10-15 16:00:49', '2017-10-15 16:03:36', '1', null, null, null, '4200000013201710158227452548');
INSERT INTO `tb_seckill_order` VALUES ('919474775091339264', null, '0.02', 'lijialong', 'qiandu', '2017-10-15 16:07:24', '2017-10-15 16:07:58', '1', null, null, null, '4200000007201710158230411417');
INSERT INTO `tb_seckill_order` VALUES ('919497114331951104', '2', '0.01', null, 'yijia', '2017-10-15 17:36:10', '2017-10-15 17:37:35', '1', null, null, null, '4200000004201710158248971034');
INSERT INTO `tb_seckill_order` VALUES ('919497943340302336', '2', '0.01', null, 'yijia', '2017-10-15 17:39:27', '2017-10-15 17:39:49', '1', null, null, null, '4200000011201710158245347392')
