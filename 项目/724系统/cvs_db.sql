-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 cvs_db 的数据库结构
DROP DATABASE IF EXISTS `cvs_db`;
CREATE DATABASE IF NOT EXISTS `cvs_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cvs_db`;


-- 导出  表 cvs_db.t_address 结构
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE IF NOT EXISTS `t_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `contact` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人姓名',
  `addressDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货地址明细',
  `postCode` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮编',
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人电话',
  `createdUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedUserId` bigint(20) DEFAULT NULL COMMENT '修改者',
  `updatedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  cvs_db.t_address 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_address` ENABLE KEYS */;


-- 导出  表 cvs_db.t_storage_record 结构
DROP TABLE IF EXISTS `t_storage_record`;
CREATE TABLE IF NOT EXISTS `t_storage_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `srCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '入库记录编码',
  `goodsName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `goodsDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `goodsUnit` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品单位',
  `goodsCount` decimal(20,2) DEFAULT NULL COMMENT '入库数量',
  `totalAmount` decimal(20,2) DEFAULT NULL COMMENT '入库商品总额',
  `payStatus` int(1) DEFAULT NULL COMMENT '支付状态（1：未支付 2：已支付）',
  `createdUserId` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedUserId` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `updatedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `supplierId` bigint(20) DEFAULT NULL COMMENT '供货商ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='入库记录';

-- 正在导出表  cvs_db.t_storage_record 的数据：~17 rows (大约)
/*!40000 ALTER TABLE `t_storage_record` DISABLE KEYS */;
INSERT INTO `t_storage_record` (`id`, `srCode`, `goodsName`, `goodsDesc`, `goodsUnit`, `goodsCount`, `totalAmount`, `payStatus`, `createdUserId`, `createdTime`, `updatedUserId`, `updatedTime`, `supplierId`) VALUES
	(1, 'sr_2019_011', '习酒-习红酱', '饮料-国酒', '瓶', 50.00, 10000.00, 1, 1, '2019-04-14 16:16:00', NULL, NULL, 1),
	(2, 'sr_2019_017', '百事可乐', '饮料', '瓶', 1500.00, 4500.00, 2, 1, '2015-05-10 12:00:00', NULL, NULL, 2),
	(3, 'sr_2019_016', '红牛', '饮料', '瓶', 2000.00, 6000.00, 2, 1, '2019-03-27 13:03:01', NULL, NULL, 2),
	(4, 'sr_2019_004', '橄榄油', '食品-进口食用油', '斤', 200.00, 9800.00, 2, 1, '2013-10-10 03:12:13', NULL, NULL, 7),
	(5, 'sr_2019_005', '洗洁精', '日用品-厨房清洁', '瓶', 500.00, 7000.00, 2, 1, '2014-12-14 13:02:03', NULL, NULL, 9),
	(6, 'sr_2019_014', '五常大米', '日用品', '斤', 600.00, 4000.00, 2, 1, '2016-11-14 14:00:00', NULL, '2019-11-14 15:55:08', 3),
	(7, 'sr_2019_007', '润体乳', '日用品-沐浴类', '瓶', 500.00, 23000.00, 1, 1, '2016-07-22 10:10:22', NULL, NULL, 14),
	(8, 'sr_2019_012', '大麦茶', NULL, '两', 80.00, 6000.00, 1, 1, '2019-09-01 17:00:00', 1, '2019-11-15 11:27:00', 6),
	(9, 'sr_2019_009', '咖啡杯', '日用品-杯子', '个', 350.00, 1750.00, 2, 1, '2016-02-04 11:40:20', NULL, NULL, 14),
	(10, 'sr_2019_010', '辣椒酱', '食品-调料', '瓶', 200.00, 2000.00, 2, 1, '2013-10-29 05:07:03', NULL, NULL, 8),
	(11, 'sr_2019_001', '润发乳', '日用品', '瓶', 500.00, 25000.00, 2, 1, '2014-12-14 13:02:03', 1, '2019-11-13 18:58:28', 2),
	(12, 'sr_2019_008', '多用途刀具', '日用品-厨房用具', '个', 600.00, 6000.00, 2, 1, '2019-01-14 05:12:13', NULL, NULL, 14),
	(14, 'sr_2019_013', '泰国-香米', '食品-大米', '斤', 400.00, 5000.00, 2, 1, '2016-10-09 15:20:00', NULL, NULL, 3),
	(15, 'sr_2019_006', '美国大杏仁', '食品-坚果', '袋', 300.00, 5000.00, 2, 1, '2016-04-14 06:08:09', NULL, NULL, 4),
	(16, 'sr_2019_003', '大豆油', '食品-食用油', '斤', 300.00, 5890.00, 2, 1, '2014-12-14 13:02:03', NULL, NULL, 6),
	(17, 'sr_2019_002', '老上海药皂', '日用品-皂类', '块', 1000.00, 10000.00, 2, 1, '2016-03-23 04:20:40', NULL, NULL, 13),
	(18, 'sr_2019_018', '茉莉蜜茶', '饮料', '瓶', 2000.00, 4000.00, 2, 1, '2015-11-24 15:12:03', NULL, NULL, 2);
/*!40000 ALTER TABLE `t_storage_record` ENABLE KEYS */;


-- 导出  表 cvs_db.t_supplier 结构
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE IF NOT EXISTS `t_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供货商编号',
  `supName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供货商名称',
  `supDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供货商描述',
  `supContact` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供货商联系人',
  `supPhone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `supAddress` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供货商地址',
  `supFax` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '传真',
  `createdUserId` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `updatedUserId` bigint(20) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='药品供货商';

-- 正在导出表  cvs_db.t_supplier 的数据：~15 rows (大约)
/*!40000 ALTER TABLE `t_supplier` DISABLE KEYS */;
INSERT INTO `t_supplier` (`id`, `supCode`, `supName`, `supDesc`, `supContact`, `supPhone`, `supAddress`, `supFax`, `createdUserId`, `createdTime`, `updatedTime`, `updatedUserId`) VALUES
	(1, 'BJ_GYS004', '北京隆盛日化科技', '主营产品：墙体除霉剂、日化环保清洗剂，家居洗涤专卖、洗涤用品网、墙面霉菌清除剂等', '王明', '13689865678', '北京大兴青云店开发区', '010-35576786', 1, '2014-11-21 12:51:11', NULL, NULL),
	(2, 'GZ_GYS003', '黑龙江哈尔滨星五金制品厂', '主营产品：海绵枕头、海绵床垫、坐垫、靠垫、头枕等', '时天天', '13562276775', '广州市白云区钟落潭镇福龙路20号', '020-85542231', 1, '2016-12-21 06:12:17', NULL, NULL),
	(3, 'GZ_GYS001', '乐山市泰国大米有限公司', '主营产品：稻香鱼香米、龙轮香米、良记金轮米等', '王圣丹', '13402013312', '广东省深圳市福田区深南大道6006华丰大厦', '0755-67776212', 1, '2014-03-21 16:56:07', NULL, NULL),
	(4, 'GZ_GYS002', '香港喜乐贸有限公司', '主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉', '孙浩', '18599897645', '广东省深圳市福龙工业区B2栋3楼西', '0755-67772341', 1, '2013-03-22 16:52:07', NULL, NULL),
	(5, 'JS_GYS001', '福建味美美调味品厂', '主营产品：花椒、胡椒、香叶、天然香辛料、鸡精、复合调味料', '徐国洋', '13754444221', '江苏省兴化市林湖工业区', '0523-21299098', 1, '2015-11-22 16:52:07', NULL, NULL),
	(6, 'BJ_GYS002', '北京趣买买食用油有限公司', '主营产品：山茶油、大豆油、花生油、橄榄油等', '王明明', '13422235678', '北京市朝阳区珠江帝景1号楼', '010-588634233', 1, '2012-03-21 17:52:07', NULL, NULL),
	(7, 'BJ_GYS003', '中粮集团有限公司', '主营产品：橄榄油、大豆油、小磨油、花生油等', '王驰', '13344441135', '北京大兴青云店开发区', '010-588134111', 1, '2016-04-13 00:00:00', NULL, NULL),
	(8, 'ZJ_GYS001', '慈溪市广和绿色食品厂', '主营产品：甜面酱，辣椒，大蒜等农产品、豆瓣酱、黄豆酱', '黄海', '18099953223', '浙江省宁波市慈溪周巷小安村', '0574-34449090', 1, '2013-11-21 06:02:07', NULL, NULL),
	(9, 'GX_GYS001', '优百商贸有限公司', '主营产品：洗面奶、润肤乳等、日化产品', '孙爱国', '13323566543', '广西南宁市秀厢大道42-1号', '0771-98861134', 1, '2013-03-21 19:52:07', '2019-11-14 16:11:18', NULL),
	(10, 'JS_GYS002', '南京火头军信息技术有限公司', '主营产品：厨房刀具、不锈钢厨具等', '懂栗', '13098992113', '江苏省南京市浦口区浦口大道1号新城总部大厦A座903室', '025-86223345', 1, '2013-03-25 16:52:07', NULL, NULL),
	(11, 'HB_GYS001', '石家庄帅益食品贸易有限公司', '主营产品：可乐饮料、水饮料、植物蛋白饮料、休闲食品、果汁饮料、功能饮料等', '赵传军', '13309094212', '河北省石家庄新华区', '0311-67738876', 1, '2016-04-13 04:20:40', NULL, NULL),
	(12, 'BJ_GYS001', '北京三木堂商贸有限公司', '主营产品：泸州老窖、赖茅酒、法国红酒等、五粮液、郎酒、酒鬼酒', '刘雯雯', '13566667777', '北京市丰台区育芳园北路', '010-58858787', 1, '2013-03-21 16:52:07', NULL, NULL),
	(13, 'SD_GYS001', '山东豪克华光联合发展有限公司', '主营产品：洗衣皂、洗衣粉、洗衣液、洗洁精、消杀类、香皂等', '周康', '13245468787', '山东济阳济北工业区仁和街21号', '0531-53362445', 1, '2015-01-28 10:52:07', NULL, NULL),
	(14, 'JS_GYS003', '江苏大源坤太商行', '主营产品：香水、洗发露、护发素等日化品批销', '宋军', '18567674532', '江苏无锡盛岸西路', '0510-32274422', 1, '2016-04-23 11:11:11', NULL, NULL),
	(15, 'ZJ_GYS002', '乐摆日用品厂', '主营产品：礼品杯、咖啡杯、各种中、高档塑料杯，塑料乐扣水杯（密封杯）、保鲜杯（保鲜盒）、广告', '李莉', '13212331567', '浙江省金华市义乌市义东路', '0579-34452321', 1, '2016-08-22 10:01:30', NULL, NULL);
/*!40000 ALTER TABLE `t_supplier` ENABLE KEYS */;


-- 导出  表 cvs_db.t_sys_role 结构
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE IF NOT EXISTS `t_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色编码',
  `roleName` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `createdUserId` bigint(20) DEFAULT NULL COMMENT '创建者',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedUserId` bigint(20) DEFAULT NULL COMMENT '修改者',
  `updatedTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统角色';

-- 正在导出表  cvs_db.t_sys_role 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_sys_role` DISABLE KEYS */;
INSERT INTO `t_sys_role` (`id`, `code`, `roleName`, `createdUserId`, `createdTime`, `updatedUserId`, `updatedTime`) VALUES
	(1, 'SMBMS_ADMIN', '管理系统员', 1, '2019-04-13 00:00:00', NULL, NULL),
	(2, 'SMBMS_MANAGER', '店长', 1, '2019-04-13 00:00:00', NULL, NULL),
	(3, 'SMBMS_EMPLOYEE', '店员', 1, '2019-04-13 00:00:00', NULL, NULL);
/*!40000 ALTER TABLE `t_sys_role` ENABLE KEYS */;


-- 导出  表 cvs_db.t_sys_user 结构
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE IF NOT EXISTS `t_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账号',
  `realName` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '真是姓名',
  `password` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `sex` int(1) DEFAULT NULL COMMENT '性别（1:女、 2:男）',
  `birthday` date DEFAULT NULL COMMENT '出生日期（年-月-日）',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `address` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户地址',
  `roleId` bigint(20) DEFAULT NULL COMMENT '用户角色id',
  `createdUserId` bigint(20) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedUserId` bigint(20) DEFAULT NULL COMMENT '修改人',
  `updatedTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统用户';

-- 正在导出表  cvs_db.t_sys_user 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` (`id`, `account`, `realName`, `password`, `sex`, `birthday`, `phone`, `address`, `roleId`, `createdUserId`, `createdTime`, `updatedUserId`, `updatedTime`) VALUES
	(1, 'admin', 'admin', '1111111', 1, '1980-11-01', '13521214507', '北京市朝阳区北辰中心12号', 1, 1, '2013-03-21 16:52:07', NULL, NULL),
	(2, 'limingxing', '李明星', '0000000', 2, '1983-12-10', '13685584457', '北京市东城区前门东大街9号', 2, 1, '2014-12-31 19:52:09', NULL, NULL),
	(5, 'wanglulu', '王璐璐', '0000000', 2, '1984-06-05', '18567542321', '北京市朝阳区北苑家园茉莉园20号楼', 2, 1, '2014-12-31 19:52:09', NULL, NULL),
	(6, 'hauzhiqing', '华志强', '0000000', 1, '1983-06-15', '13382386623', '北京市通州区学院路61号', 3, 1, '2013-02-11 10:51:17', NULL, NULL),
	(7, 'huangweihua', '黄卫华', '0000000', 2, '1982-12-31', '13388451555', '北京市海淀区西二旗辉煌国际26层', 3, 1, '2014-06-11 19:09:07', NULL, NULL),
	(10, 'zhaogang', '赵刚', '0000000', 2, '1980-01-01', '13387676762', '北京市丰台区管庄新月小区', 2, 1, '2015-05-06 10:52:07', NULL, NULL),
	(11, 'liuyang', '刘阳', '0000000', 2, '1978-03-12', '13367890900', '北京市海淀区成府路207号', 2, 1, '2016-11-09 16:51:17', NULL, NULL),
	(12, 'lijiangtao', '李江涛', '0000000', 1, '1983-12-10', '18098765434', '朝阳区管庄路口北柏林爱乐三期13号楼', 3, 1, '2016-08-09 05:52:37', 1, '2016-04-14 14:15:36'),
	(13, 'liuzhong', '刘忠', '0000000', 2, '1981-11-04', '13689674534', '北京市海淀区北航家属院10号楼', 3, 1, '2016-07-11 08:02:47', NULL, NULL),
	(14, 'zhangfeng', '张峰', '0000000', 2, '1980-01-01', '13645884457', '北京市海淀区北苑家园20号楼', 3, 1, '2015-02-01 03:52:07', NULL, NULL),
	(15, 'songke', '宋科', '0000000', 1, '1983-10-10', '13387676762', '北京市海淀区西二旗辉煌国际26层', 2, 1, '2015-09-12 12:02:12', NULL, NULL),
	(16, '111', '222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `t_sys_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
