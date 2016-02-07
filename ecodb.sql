-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2015 at 12:35 PM
-- Server version: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecodb`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL,
  `type` varchar(200) NOT NULL,
  `price_per_weight` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `type`, `price_per_weight`) VALUES
(1, 'Plastic', 1),
(2, 'Glass', 3),
(3, 'Aluminium', 6),
(6, 'Steel', 4);

-- --------------------------------------------------------

--
-- Table structure for table `rcm`
--

CREATE TABLE `rcm` (
  `rcm_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `location` varchar(200) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `item_list` varchar(1000) NOT NULL,
  `total_capacity` int(11) NOT NULL,
  `total_amount` float NOT NULL,
  `remaining_amount` int(11) NOT NULL,
  `remaining_capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rcm`
--

INSERT INTO `rcm` (`rcm_id`, `name`, `location`, `active`, `item_list`, `total_capacity`, `total_amount`, `remaining_amount`, `remaining_capacity`) VALUES
(15, 'RCM01', 'East Campus', 1, '[2, 3]', 450, 900, 642, 442),
(16, 'RCM02', 'North Campus', 1, '[2, 1]', 920, 1200, 1169, 905),
(21, 'RCM04', 'North Campus', 1, '[2, 3]', 10, 50, 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `rmos`
--

CREATE TABLE `rmos` (
  `rmos_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `connected_rcm` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `rcm_id` int(11) NOT NULL,
  `last_service_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id`, `rcm_id`, `last_service_date`) VALUES
(1, 21, '2015-12-09'),
(2, 15, '2015-12-09'),
(3, 17, '2015-12-09');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `rcm_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `rcm_id`, `item_id`, `weight`, `price`, `date`) VALUES
(68, 21, 3, 5, 30, '2015-12-09'),
(69, 16, 1, 1, 1, '2015-12-09'),
(70, 16, 1, 9, 9, '2015-12-09'),
(71, 15, 2, 8, 24, '2015-12-09');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(500) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `password`) VALUES
(1, 'admin', '#Abc123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `rcm`
--
ALTER TABLE `rcm`
  ADD PRIMARY KEY (`rcm_id`);

--
-- Indexes for table `rmos`
--
ALTER TABLE `rmos`
  ADD PRIMARY KEY (`rmos_id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `rcm`
--
ALTER TABLE `rcm`
  MODIFY `rcm_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `rmos`
--
ALTER TABLE `rmos`
  MODIFY `rmos_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
