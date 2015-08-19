CREATE TABLE `Teacher` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Teacher` (`name`) VALUES ("Dmitry Anatolievitch");
INSERT INTO `Teacher` (`name`) VALUES ("Aleg Valerievitch");

CREATE TABLE `Subject` (
  `name` VARCHAR(255) DEFAULT NULL,
  `teacher_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`teacher_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`teacher_id`) REFERENCES `Teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Subject` VALUES ("Agregation",1);
INSERT INTO `Subject` VALUES ("Language",2);




CREATE TABLE categories (
  id int unsigned not null primary key,
  name VARCHAR(255) default null
);
insert into `categories` values (1,"cat1");
insert into `categories` values (2,"cat2");

CREATE TABLE products (
  id int unsigned not null primary key,
  name VARCHAR(255) default null
);
insert into `products` values (1,"prod1");
insert into `products` values (2,"prod2");

CREATE TABLE categories_products (
  category_id int unsigned not null,
  product_id int unsigned not null,
  -- PRIMARY KEY (category_id, product_id),
  KEY pkey (product_id),
  FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE
);
insert into `categories_products` values (1,1);
insert into `categories_products` values (2,2);
describe categories_products;
alter table categories_products drop foreign key categories_products_ibfk_1;
alter table categories_products drop foreign key categories_products_ibfk_2;
select * from categories_products;

create temporary table if not exists my_categories_products as (select * from categories_products);
drop temporary table if exists my_categories_products;
select * from my_categories_products;

show tables;