CREATE DATABASE how2java;
use how2java;
CREATE table category (
id int(11) not null AUTO_INCREMENT,
name VARCHAR(30),
PRIMARY KEY(id)
)DEFAULT CHARSET=UTF8;
#插入数据
INSERT into category (id,name)VALUES(null,'category1');
INSERT into category (id,name)VALUES(null,'category2');
INSERT into category (id,name)VALUES(null,'category3');
INSERT into category (id,name)VALUES(null,'category4');
INSERT into category (id,name)VALUES(null,'category5');
SELECT * from category;