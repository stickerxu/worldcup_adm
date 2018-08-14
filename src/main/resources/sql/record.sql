/*预存数据*/
INSERT INTO `worldcup`.`admin_user`(`id`, `username`, `password`, `role`, `update_time`, `create_time`) VALUES (1, 'adm', '123456', 'ADMIN', '2018-08-13 14:10:38', '2018-08-13 14:10:52');

/*未上线*/
alter table worldcup.article add column author varchar(20) NOT NULL DEFAULT ''
alter table worldcup.article add column publish_time datetime