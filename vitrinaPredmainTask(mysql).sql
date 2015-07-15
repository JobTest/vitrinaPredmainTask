-- MySQL Dump
-- Server Version 5.5.27
-- Host: 10.13.71.153
-- User: root@10.13.71.153
-- Password: 1111

--
-- DataBase: `issues`
--

-- --------------------------------------------------------

--
-- DB-Structure `issue`
--

CREATE TABLE `issue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT '0',
  `project_id` bigint(20) NOT NULL,
  `project_name` varchar(64) DEFAULT NULL,
  `tracker_id` int(6) NOT NULL,
  `tracker_name` varchar(16) DEFAULT NULL,
  `status_id` int(6) NOT NULL,
  `status_name` varchar(32) DEFAULT NULL,
  `fixed_version_id` int(6) NOT NULL,
  `fixed_version_name` varchar(16) DEFAULT NULL,
  `subject` varchar(256) DEFAULT NULL,
  `start_date` varchar(10) NOT NULL,
  `due_date` varchar(10) NOT NULL,
  `target` ENUM('debt', 'biplane_web', 'irbis') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8