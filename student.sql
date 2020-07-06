-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 06, 2020 at 02:38 AM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `students`
--

-- --------------------------------------------------------

--
-- Table structure for table `studentinfo`
--

CREATE TABLE `studentinfo` (
  `sid` int(11) NOT NULL,
  `name` varchar(60) default NULL,
  `dob` varchar(15) default NULL,
  `major` varchar(50) default NULL,
  `level` varchar(15) default NULL,
  `year` varchar(20) default NULL,
  PRIMARY KEY  (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentinfo`
--

INSERT INTO `studentinfo` (`sid`, `name`, `dob`, `major`, `level`, `year`) VALUES
(1, 'Anuj Singh', '8/03/99', 'Computer Science', 'Graduate', 'Sophomore'),
(2, 'Avinash Sharma', '5/07/98', 'Information Security', 'Graduate', 'Third'),
(3, 'Yogen Sood', '7/23/99', 'Chemical Engineering', 'Undergraduate', 'Senior'),
(4, 'Nikita Singh', '8/22/99', 'Information Science and Technology', 'Undergraduate', 'Freshman'),
(5, 'Abdul Kalam', '7/18/98', 'Computer Science', 'Undergraduate', 'Sophomore'),
(6, 'Abhimanyu Singh', '12/13/97', 'Computer Science', 'Graduate', 'First'),
(7, 'Kumar Vivek', '8/27/98', 'Electrical Engineering', 'Undergraduate', 'Senior');
