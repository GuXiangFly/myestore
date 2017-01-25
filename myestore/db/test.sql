
create database estore_system;
use estore_system;
--用户表--
create table  users(
       id int primary key auto_increment,
       username varchar(40),       
       password varchar(60),       
       email varchar(40),       
       nickname varchar(40),
       status int,
       regsittime timestamp,
       activecode varchar(100)
);

--商品表==
create table products(
	id varchar(50) PRIMARY key,
	name varchar(50),
	price double,
	category varchar(50),
	count int,
	imageurl varchar(100)
);

--订单表--
create table orders(
	id varchar(50) PRIMARY key,
	moneys double,
	ordertime datetime,
	receverinfo varchar(255),
	paystate int,
	user_id int,
	foreign key(user_id) references users(id)
);

--由于商品和订单是多对多的关系，所以引入第三章关系表

--关系表
create table orderitems(
	order_id varchar(50),
	product_id varchar(50),
	foreign key(order_id) references orders(id),
	foreign key(product_id) references products(id),
	buynum int
);