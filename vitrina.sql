-- MySQL Dump
-- Server Version 5.5.27
-- Host: localhost
-- User: root@localhost
-- Password: 1111

DROP DATABASE IF EXISTS vitrina;
CREATE DATABASE vitrina CHARACTER SET utf8 COLLATE utf8_general_ci;

--
-- DataBase: `vitrina`
--

-- --------------------------------------------------------

--
-- DB-Structure `issue`
--

CREATE TABLE IF NOT EXISTS `issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(8) DEFAULT 0,
  `project_id` int(8) NOT NULL,
  `project_name` varchar(16) NOT NULL,
  `tracker_id` int(8) NOT NULL,
  `tracker_name` varchar(16) NOT NULL,
  `fixed_version_id` int(8) NOT NULL,
  `fixed_version_name` varchar(16) NOT NULL,
  `status_id` int(8) NOT NULL,
  `status_name` varchar(32) NOT NULL,
  `subject` varchar(128) NOT NULL,
  `start_date` varchar(32) NOT NULL,
  `due_date` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

--
-- Dump DataTable `issue`
--

INSERT INTO `issue` (`id`, `parent_id`, `project_id`, `project_name`, `tracker_id`, `tracker_name`, `fixed_version_id`, `fixed_version_name`, `status_id`, `status_name`, `subject`, `start_date`, `due_date`) VALUES
(155819, 151358, 2928, 'API 2.x', 5, 'Scrum', 1, 'Новая/Утверждена', 9299, '2.4. Доработки', 'Уведомление потребителей', '2013-09-09T00:00:00+03:00', '2013-09-09T00:00:00+03:00');

INSERT INTO issue (id, parent_id, project_id, project_name, tracker_id, tracker_name, fixed_version_id, fixed_version_name, status_id, status_name, subject, start_date, due_date) VALUES
(155785, 0, 2928, 'API 2.x', 1, 'Ошибка', 1, 'Новая/Утверждена', 9299, '2.4. Доработки', 'В поле "name" тега', '2013-09-09T00:00:00+03:00', '0');

