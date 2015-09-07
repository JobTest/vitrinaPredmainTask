-- Oracle Dump
-- Server Version: 11g Express Edition
-- Host: localhost
-- User: HR@localhost
-- Password: hr

--
-- DataBase: `vitrina`
--

-- --------------------------------------------------------

--
-- DB-Structure `issue`
--

CREATE TABLE issue (
  id int NOT NULL,
  parent_id int DEFAULT 0,
  project_id int NOT NULL,
  project_name char(255) NOT NULL,
  tracker_id int NOT NULL,
  tracker_name char(255) NOT NULL,
  fixed_version_id int NOT NULL,
  fixed_version_name char(255) NOT NULL,
  status_id int NOT NULL,
  status_name char(255) NOT NULL,
  subject char(255) NOT NULL,
  start_date char(255) NOT NULL,
  due_date char(255) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Dump DataTable `issue`
--

INSERT INTO issue (id,parent_id,project_id,project_name,tracker_id,tracker_name,fixed_version_id,fixed_version_name,status_id,status_name,subject,start_date,due_date) VALUES
(155819,151358,2928,'API 2.x',5,'Scrum',1,'Новая/Утверждена',9299,'2.4. Доработки','Уведомление потребителей','2013-09-09T00:00:00+03:00','2013-09-09T00:00:00+03:00');

INSERT INTO issue (id, parent_id,project_id,project_name,tracker_id,tracker_name,fixed_version_id,fixed_version_name,status_id,status_name,subject,start_date,due_date) VALUES
(155785,0,2928,'API 2.x',1,'Ошибка',1,'Новая/Утверждена',9299,'2.4. Доработки','В поле "name" тега','2013-09-09T00:00:00+03:00','0');
