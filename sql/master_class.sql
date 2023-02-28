-- コードテーブル
-- 下記のコードを管理します（以降追加）
-- 状態コード、部署コード
CREATE TABLE `master_class` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `type` varchar(5),
    `type_name` varchar(255),
    `code` VARCHAR(20),
    `value` VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('GHST','付与状態','GHST01','未開始');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('GHST','付与状態','GHST02','有効');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('GHST','付与状態','GHST03','繰越');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('GHST','付与状態','GHST04','失効');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('GHST','付与状態','GHST05','消化済');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT001','システム事業部・第一グループ');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT002','システム事業部・第二グループ');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT003','システム事業部・第三グループ');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT004','ソリューション事業部');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT005','営業本部・１部');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT006','営業本部・２部');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT007','管理本部');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT008','システム事業部');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT009','管理本部・人材サービス');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT010','管理本部・購買グループ');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT011','管理本部・総務財務');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT012','管理本部・人事採用');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('DPT','部署','DPT013','管理本部・法務広報セキュリティー');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('PROC','休暇処理','PROC01','付与');
INSERT INTO `master_class`(`type`,`type_name`,`code`,`value`) VALUES ('PROC','休暇処理','PROC02','消化');
