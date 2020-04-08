CREATE DATABASE test;

create table pg_demo(
    auto_id integer not null,
    id character(32) not null,
    name character(255) not null,
    price decimal(8,2) not null,
    primary key(auto_id)
);


insert into pg_demo (auto_id,id,name,price)
    values(''a1'',101,''apple'',5.2),
    (102,''b1'',''berry'',10.2),
    (103,''bs1'',''orange'',11.2),
    (104,''bs2'',''melon'',8.2),
    (105,''t1'',''banana'',7.2),
    (106,''t2'',''grape'',16.5),
    (107,''o2'',''cherry'',9.6);







