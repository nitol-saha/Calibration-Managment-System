-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 22, 2021 at 02:22 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `equipment_table`
--

CREATE TABLE IF NOT EXISTS `equipment_table` (
  `ID` int(50) NOT NULL,
  `Equipment ID` varchar(50) NOT NULL,
  `Equipment Name` varchar(50) NOT NULL,
  `Facility Name` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipment_table`
--

INSERT INTO `equipment_table` (`ID`, `Equipment ID`, `Equipment Name`, `Facility Name`) VALUES
(9, 'Eq-001', 'Coating Machine', 'GMF');

-- --------------------------------------------------------

--
-- Table structure for table `login_table`
--

CREATE TABLE IF NOT EXISTS `login_table` (
  `ID` int(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_table`
--

INSERT INTO `login_table` (`ID`, `Username`, `Password`) VALUES
(1, 'nitol', '123'),
(2, 'root', '456');

-- --------------------------------------------------------

--
-- Table structure for table `standard_device_table`
--

CREATE TABLE IF NOT EXISTS `standard_device_table` (
  `ID` varchar(50) NOT NULL,
  `Standard Device Name` varchar(50) NOT NULL,
  `Standard Device Model` varchar(50) NOT NULL,
  `Serial No` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `standard_device_table`
--

INSERT INTO `standard_device_table` (`ID`, `Standard Device Name`, `Standard Device Model`, `Serial No`) VALUES
('SD-001-PPF', 'Velocity Meter', 'VM-001', '23423423'),
('SD-001-SSF', 'Flow Meter', 'FL-001', '123123123'),
('SD-002-PPF', 'Inensity Meter', 'IM-001', '123123');

-- --------------------------------------------------------

--
-- Table structure for table `standard_schedule_table`
--

CREATE TABLE IF NOT EXISTS `standard_schedule_table` (
  `ID` varchar(50) NOT NULL,
  `January` varchar(50) DEFAULT NULL,
  `February` varchar(50) DEFAULT NULL,
  `March` varchar(50) DEFAULT NULL,
  `April` varchar(50) DEFAULT NULL,
  `May` varchar(50) DEFAULT NULL,
  `June` varchar(50) DEFAULT NULL,
  `July` varchar(50) DEFAULT NULL,
  `August` varchar(50) DEFAULT NULL,
  `September` varchar(50) DEFAULT NULL,
  `October` varchar(50) DEFAULT NULL,
  `November` varchar(50) DEFAULT NULL,
  `December` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `equipment_table`
--
ALTER TABLE `equipment_table`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `login_table`
--
ALTER TABLE `login_table`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `standard_device_table`
--
ALTER TABLE `standard_device_table`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `standard_schedule_table`
--
ALTER TABLE `standard_schedule_table`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `equipment_table`
--
ALTER TABLE `equipment_table`
  MODIFY `ID` int(50) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `login_table`
--
ALTER TABLE `login_table`
  MODIFY `ID` int(50) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
