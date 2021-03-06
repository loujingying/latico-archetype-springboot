DROP TABLE demo
go

CREATE TABLE demo  (
	auto_id NUMBER(15) NOT NULL primary key,
	id VARCHAR2(32) NOT NULL,
	task_id NUMBER(11) NOT NULL,
	exec_type VARCHAR2(255) NULL,
	exec_status NUMBER(2) default 0 NOT NULL,
	status_descr VARCHAR2(255) NULL,
	call_param VARCHAR2(2048) NULL,
	call_client_id VARCHAR2(64) NULL,
	collect_server_id VARCHAR2(64) NULL,
	handle_server_id VARCHAR2(64) NULL,
    create_time DATE ,
    update_time DATE,
    handle_status NUMBER(2) default 0 NOT NULL,
	handle_descr VARCHAR2(255) NULL,
	handle_start_time DATE ,
    handle_end_time DATE
	)
	go
// id索引必须有
CREATE unique index idx_demo_id on demo(id)
go
// 创建普通索引，建议是组合索引
CREATE unique index idx_demo on demo(task_id)
go

CREATE sequence demo_seq
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20
go

CREATE OR REPLACE TRIGGER demo_trigger
before insert on demo for each row
begin
  select demo_seq.nextval into :new.AUTO_ID from dual;
end;
go
