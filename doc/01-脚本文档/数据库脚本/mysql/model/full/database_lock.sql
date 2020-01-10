
-- 数据库分布式锁用到的表
DROP TABLE IF EXISTS `database_lock`;

CREATE TABLE `database_lock`(
  `auto_id` INT NOT NULL AUTO_INCREMENT COMMENT '物理主键,自增用',
  `id` VARCHAR(32) NOT NULL COMMENT '逻辑主键，主键，决定唯一性用',
  `lock_key` VARCHAR(255) NOT NULL COMMENT '数据库锁的键',
  `lock_value` VARCHAR(255) NOT NULL COMMENT '数据库锁键的值，比如存储键的版本号',
  `lock_status` INT NOT NULL default '0' COMMENT '锁的状态，未锁:0：已锁：1',
  `expire_time` BIGINT NOT NULL default '-1' COMMENT '锁的过期时间戳，单位:秒，需要结合lock_status为1时才判断使用，如果是小于等于0就永久不过期',
  `descr` VARCHAR(255) NULL COMMENT '描述',
  `lock_holder` VARCHAR(255) NULL COMMENT '锁的持有者',
  `create_time` TIMESTAMP COMMENT '记录创建时间',
  `update_time` TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`auto_id`)
) ENGINE=INNODB CHARSET=utf8;
-- idx_id索引必须有
ALTER TABLE `database_lock`
  ADD  UNIQUE INDEX `idx_id` (`id`);

-- 创建普通索引，可以是组合索引
ALTER TABLE `database_lock`
  ADD UNIQUE INDEX `idx_unique` (`lock_key`);