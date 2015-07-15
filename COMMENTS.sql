-- MySQL Dump
-- Server Version 5.5.27
-- Host: localhost
-- User: root@localhost
-- Password: 1111


DROP DATABASE IF EXISTS issues;
CREATE DATABASE issues CHARACTER SET utf8 COLLATE utf8_general_ci ;

--
-- DataBase: `issues`
--

-- --------------------------------------------------------

--
-- DB-Structure `COMMENTS`
--

CREATE TABLE COMMENTS (id INT NOT NULL AUTO_INCREMENT,
    MYUSER VARCHAR(30) NOT NULL,
    EMAIL VARCHAR(30), 
    WEBPAGE VARCHAR(100) NOT NULL, 
    DATUM DATE NOT NULL, 
    SUMMARY VARCHAR(40) NOT NULL,
    COMMENTS VARCHAR(400) NOT NULL,
    PRIMARY KEY (ID));

--
-- Dump DataTable `COMMENTS`
--

INSERT INTO COMMENTS values (default, 'lars', 'myemail@gmail.com','http://www.vogella.com', '2009-09-14 10:33:11', 'Summary','My first comment');

INSERT INTO COMMENTS values (default, 'dn200978lak', 'dn200978lak@gmail.com','http://www.vogella.com', '2013-09-10 17:30:32', 'Bla-bla-bla','My swcond comment #2')

