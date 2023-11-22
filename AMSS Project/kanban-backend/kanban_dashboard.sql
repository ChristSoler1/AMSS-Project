-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 18, 2023 at 02:15 PM
-- Server version: 8.0.35-0ubuntu0.20.04.1
-- PHP Version: 7.1.33-51+ubuntu20.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kanban_dashboard`
--

-- --------------------------------------------------------

--
-- Table structure for table `modules`
--

CREATE TABLE `modules` (
  `id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `credits` int DEFAULT NULL,
  `preset_effort` int DEFAULT NULL,
  `teaching_resources` text,
  `learning_objectives` text,
  `performance_record_id` int DEFAULT NULL
);

--
-- Dumping data for table `modules`
--

INSERT INTO `modules` (`id`, `title`, `credits`, `preset_effort`, `teaching_resources`, `learning_objectives`, `performance_record_id`) VALUES
(1, 'test0', 90, 2, 'asfasf', 'qqweqw', 1);

-- --------------------------------------------------------

--
-- Table structure for table `performance_records`
--

CREATE TABLE `performance_records` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
);

--
-- Dumping data for table `performance_records`
--

INSERT INTO `performance_records` (`id`, `name`) VALUES
(1, 'Written Exam'),
(2, 'Oral Exam'),
(3, 'Submission'),
(4, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `priorities`
--

CREATE TABLE `priorities` (
  `id` int NOT NULL,
  `level` varchar(20) NOT NULL
);

--
-- Dumping data for table `priorities`
--

INSERT INTO `priorities` (`id`, `level`) VALUES
(1, 'High'),
(2, 'Medium'),
(3, 'Low');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text,
  `preset_effort` int DEFAULT NULL,
  `actual_effort` int DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `priority_id` int DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `module_id` int DEFAULT NULL
);

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `title`, `description`, `preset_effort`, `actual_effort`, `status_id`, `priority_id`, `end_date`, `module_id`) VALUES
(1, 'Sample Task', 'This is a sample task description.', 10, 5, 1, 2, '2023-12-01', 1);

-- --------------------------------------------------------

--
-- Table structure for table `task_statuses`
--

CREATE TABLE `task_statuses` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ;

--
-- Dumping data for table `task_statuses`
--

INSERT INTO `task_statuses` (`id`, `name`) VALUES
(1, 'Open'),
(2, 'In Progress'),
(3, 'Finished'),
(4, 'Learned');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `performance_record_id` (`performance_record_id`);

--
-- Indexes for table `performance_records`
--
ALTER TABLE `performance_records`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `priorities`
--
ALTER TABLE `priorities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `status_id` (`status_id`),
  ADD KEY `priority_id` (`priority_id`),
  ADD KEY `module_id` (`module_id`);

--
-- Indexes for table `task_statuses`
--
ALTER TABLE `task_statuses`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `modules`
--
ALTER TABLE `modules`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `performance_records`
--
ALTER TABLE `performance_records`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `priorities`
--
ALTER TABLE `priorities`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `task_statuses`
--
ALTER TABLE `task_statuses`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `modules`
--
ALTER TABLE `modules`
  ADD CONSTRAINT `modules_ibfk_1` FOREIGN KEY (`performance_record_id`) REFERENCES `performance_records` (`id`);

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `task_statuses` (`id`),
  ADD CONSTRAINT `tasks_ibfk_2` FOREIGN KEY (`priority_id`) REFERENCES `priorities` (`id`),
  ADD CONSTRAINT `tasks_ibfk_3` FOREIGN KEY (`module_id`) REFERENCES `modules` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
