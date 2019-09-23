DROP TABLE IF EXISTS `demo`;

CREATE TABLE `demo`(
  `auto_id` INT NOT NULL AUTO_INCREMENT COMMENT '物理主键,自增用',
  `id` VARCHAR(32) NOT NULL COMMENT '逻辑主键，主键，决定唯一性用',
  `task_id` INT NOT NULL COMMENT '批次',
  `exec_type` VARCHAR(255) NULL COMMENT '执行类型，区分执行的任务类别',
  `exec_status` INT NOT NULL default '0' COMMENT '执行状态，0：未处理，1：正在执行，2：执行采集成功，3：执行采集部分成功，4：执行采集全部失败',
  `status_descr` VARCHAR(255) NULL COMMENT '状态原因描述',
  `call_param` VARCHAR(2048) NULL COMMENT '调用参数',
  `call_client_id` VARCHAR(64) NULL COMMENT '调用者ID，建议使用IP',
  `collect_server_id` VARCHAR(64) NULL COMMENT '接收采集的服务器的ID，建议使用IP',
  `handle_server_id` VARCHAR(64) NULL COMMENT '适配处理的服务器的ID，建议使用IP',
  `create_time` TIMESTAMP COMMENT '记录创建时间',
  `update_time` TIMESTAMP COMMENT '记录更新时间',
  `handle_status` INT default '0' COMMENT '处理/适配状态，0：未处理，1：正在执行，2：执行采集成功，3：执行采集部分成功，4：执行采集全部失败',
  `handle_descr` VARCHAR(255) NULL COMMENT '处理/适配状态描述',
  `handle_start_time` TIMESTAMP COMMENT '处理开始时间',
  `handle_end_time` TIMESTAMP COMMENT '处理结束时间',
  PRIMARY KEY (`auto_id`)
) ENGINE=INNODB CHARSET=utf8;
-- idx_id索引必须有
ALTER TABLE `demo`
  ADD  UNIQUE INDEX `idx_id` (`id`);

-- 创建普通索引，可以是组合索引
ALTER TABLE `demo`
  ADD UNIQUE INDEX `idx_unique` (`task_id`);






