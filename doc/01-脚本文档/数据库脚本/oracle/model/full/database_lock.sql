//分布式锁

DROP TABLE database_lock
go


CREATE TABLE database_lock  (
	auto_id NUMBER(15) NOT NULL primary key,
	id VARCHAR2(32) NOT NULL,
	lock_key VARCHAR2(255) NOT NULL,
	lock_value VARCHAR2(255) NOT NULL,
	lock_status NUMBER(2) default 0 NOT NULL,
	expire_time NUMBER(15) NULL,
	descr VARCHAR2(255) NULL,
	lock_holder VARCHAR2(255) NULL,
    create_time DATE ,
    update_time DATE
	)
	go
// id索引必须有
CREATE unique index idx_database_lock_id on database_lock(id)
go
CREATE unique index idx_database_lock_lock_key on database_lock(lock_key)
go

CREATE sequence database_lock_seq
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20
go

CREATE OR REPLACE TRIGGER database_lock_trigger
before insert on database_lock for each row
begin
  select database_lock_seq.nextval into :new.AUTO_ID from dual;
end;
go