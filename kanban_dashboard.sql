-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 09, 2023 at 10:20 AM
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
  `performance_record_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
);

--
-- Dumping data for table `modules`
--

INSERT INTO `modules` (`id`, `title`, `credits`, `preset_effort`, `teaching_resources`, `learning_objectives`, `performance_record_id`, `created_date`, `end_date`) VALUES
(1, 'PS5', 90, 2, 'asfasf', 'qqweqw', 1, '2023-11-30', '2023-12-14'),
(2, 'OOP', 50, 6, 'sfdf', 'sdfs', 1, '2023-11-30', '2023-12-01'),
(6, 'Module 1', 90, 60, 'test 123', 'test 123', 4, '2023-11-30', '2023-12-14'),
(8, 'Module 1', 60, 60, 'test Teaching Resources', 'test Learning Objectives', 4, '2023-11-30', '2023-12-14'),
(10, 'module 2', 100, 60, 'test', 'test', 4, '2023-11-30', '2023-12-14'),
(11, 'mmmm', 122, 111, 'asdad', 'asdas', 2, '2023-11-30', '2023-11-23'),
(12, 'qqqqqqqqqqqqq', 120, 120, 'warwrwq', 'wqeqwe', 1, '2023-11-29', '2023-11-30'),
(13, 'aaaaaaaa', 131, 12, 'asdas', 'asdas', 2, '2023-11-30', '2023-12-02'),
(14, '1111111', 1111111111, 11111, '111111', '111111', 1, '2023-11-30', '2023-12-09');

-- --------------------------------------------------------

--
-- Table structure for table `modules_users`
--

CREATE TABLE `modules_users` (
  `id` int NOT NULL,
  `module_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL
) ;

--
-- Dumping data for table `modules_users`
--

INSERT INTO `modules_users` (`id`, `module_id`, `user_id`) VALUES
(3, 1, 1),
(4, 2, 1),
(8, 6, 7),
(10, 8, 8),
(12, 10, 8),
(13, 11, 8),
(14, 12, 8),
(15, 13, 1),
(16, 14, 1);

-- --------------------------------------------------------

--
-- Table structure for table `performance_records`
--

CREATE TABLE `performance_records` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ;

--
-- Dumping data for table `performance_records`
--

INSERT INTO `performance_records` (`id`, `name`) VALUES
(1, 'schriftliche Prüfung'),
(2, 'mündliche Prüfung'),
(3, 'Semesterarbeit'),
(4, 'anderes');

-- --------------------------------------------------------

--
-- Table structure for table `priorities`
--

CREATE TABLE `priorities` (
  `id` int NOT NULL,
  `level` varchar(20) NOT NULL
) ;

--
-- Dumping data for table `priorities`
--

INSERT INTO `priorities` (`id`, `level`) VALUES
(1, 'Hoch'),
(2, 'Mittel'),
(3, 'Niedrig');

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
  `module_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL
) ;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `title`, `description`, `preset_effort`, `actual_effort`, `status_id`, `priority_id`, `end_date`, `module_id`, `created_date`) VALUES
(38, 'task 1', '', 10, 10, 1, 3, '2023-11-30', 8, '2023-11-29'),
(39, 'task 2', 'task 2222', 24, 24, 1, 1, '2023-12-02', 8, '2023-11-29'),
(40, 'task 3', 'task 3', 12, 10, 3, 1, '2024-01-02', 8, '2023-11-29'),
(41, 'task4', 'task4', 10, 10, 2, 2, '2023-11-25', 8, '2023-11-29'),
(42, 'test', ' 1111', 1, 3, 4, 1, '2023-11-25', 8, '2023-11-29'),
(43, 'task 1', 'task 1', 10, 10, 1, 1, '2023-11-28', 10, '2023-11-29'),
(44, 'test', 'test 121212', 12, 13, 2, 3, '2023-11-29', 8, '2023-11-29'),
(45, 'aa', 'cghvjnm', 10, 10, 3, 1, '2023-12-01', 2, '2023-11-29'),
(46, 'qqq', 'qqq as fasd fsad fads f fa qqq as fasd fsad fads f fa qqq as fasd fsad fads f fa qqq as fasd fsad fads f fa qqq as fasd fsad fads f faqqq as fasd fsad fads f faqqq as fasd fsad fads f faqqq as fasd fsad fads f fa', 12, 12, 1, 1, '2023-11-30', 2, NULL),
(47, '11', '11', 1, 1, 1, 3, '2023-12-27', 13, '2023-12-09'),
(48, 'qqq', 'qq', 11, 11, 2, 3, '2024-01-04', 2, NULL),
(49, '11111', '1111', 11, 11, 2, 3, '2023-12-29', 1, '2023-12-09'),
(50, 'qqqqqqqqqqqq', 'qqqqqq', 11, 11, 2, 2, '2024-01-05', 1, '2023-12-09'),
(51, '1', '1', 1, 1, 2, 3, '2023-12-29', 1, '2023-12-09');

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
(1, 'offen'),
(2, 'in Arbeit'),
(3, 'Beendet'),
(4, 'Gelernt');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `calander_url` text
) ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `username`, `password`, `email`, `phone`, `calander_url`) VALUES
(1, 'dilanka', 'e10adc3949ba59abbe56e057f20f883e', 'test@gmail.com', '0711111ww', 'https://chat.openai.com'),
(2, 'user1', '123456', 'test1@gmail.com', '0715655654', 'https://chat.openai.com'),
(3, 'Dilanka2', 'e10adc3949ba59abbe56e057f20f883e', 'test2@gmail.com', '', NULL),
(4, 'test123', 'e10adc3949ba59abbe56e057f20f883e', 'test123@gmail.com', '1234567890', NULL),
(5, 'test111', 'e10adc3949ba59abbe56e057f20f883e', 'test111@gmail.com', '01111111', NULL),
(6, 'test222', 'e10adc3949ba59abbe56e057f20f883e', 'test222@gmail.com', '1111111111', NULL),
(7, 'test12', 'e10adc3949ba59abbe56e057f20f883e', 'test12@gmail.com', '1231231230', 'http://localhost:3001/updateaccount'),
(8, 'test23', 'e10adc3949ba59abbe56e057f20f883e', 'test23@gmail.com', '98765432310', 'https://outlook.live.com/calendar/0/view/month');

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
-- Indexes for table `modules_users`
--
ALTER TABLE `modules_users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `modules_users_ibfk_1` (`module_id`);

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
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `modules`
--
ALTER TABLE `modules`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `modules_users`
--
ALTER TABLE `modules_users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `task_statuses`
--
ALTER TABLE `task_statuses`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `modules`
--
ALTER TABLE `modules`
  ADD CONSTRAINT `modules_ibfk_1` FOREIGN KEY (`performance_record_id`) REFERENCES `performance_records` (`id`);

--
-- Constraints for table `modules_users`
--
ALTER TABLE `modules_users`
  ADD CONSTRAINT `modules_users_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `modules` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `modules_users_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`userID`);

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
