-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2020 at 03:29 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `covid`
--

-- --------------------------------------------------------

--
-- Table structure for table `shop_owner`
--

CREATE TABLE `shop_owner` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `owner_name` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `mask_count` varchar(255) NOT NULL,
  `sanitize_count` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shop_owner`
--

INSERT INTO `shop_owner` (`id`, `name`, `owner_name`, `mobile`, `email`, `address`, `location`, `uname`, `password`, `mask_count`, `sanitize_count`) VALUES
(1, 'Abhishek Medical Shop', 'Abhishek Jangitwar', '1234567892', 'ajangitwar@gmail.com', '41,balaji nagar nagpur punapur road pardi nagpur:440035', 'Nagpur', 'abhi', 'admin', '1000', '500'),
(2, 'Bhushan Medical Shop', 'Bhushan kuthe', '5465696558', 'bhushankuthe@gmail.com', '51,Bandi near hanuman nagar shantinagar nagpur', 'Nagpur', 'bhushan', 'admin', '565', '95'),
(3, 'Jo bhi ho Medical', 'Kuch bhi', '4564578965', 'bhushankuthe@gmail.com', '51,Bandi near hanuman nagar shantinagar nagpur', 'Nagpur', 'admin', 'admin', '555', '666'),
(5, 'Aniket', 'Kuch bhi', '4564578965', 'bhushankuthe@gmail.com', '51,Bandi near hanuman nagar shantinagar nagpur', 'Nagpur', 'aniket', 'admin', '458', '565'),
(6, 'Anil Medical Shop', 'Anil Rathod', '5465696558', 'bhushankuthe@gmail.com', '51,Bandi near hanuman nagar shantinagar nagpur', 'Nagpur', 'anil', 'admin', '963', '741'),
(7, 'Rishi Medical Shop', 'Rishi Singh', '1234567892', 'ajangitwar@gmail.com', '41,balaji nagar nagpur punapur road pardi nagpur:440035', 'Nagpur', 'rishi', 'admin', '456', '789'),
(8, 'Bhushan Medical Shop', 'Bhushan kuthe', '5465696558', 'bhushankuthe@gmail.com', '51,Bandi near hanuman nagar shantinagar nagpur', 'Nagpur', 'admin', 'admin', '565', '95'),
(9, 'Abhishek Medical Shop', 'Abhishek Jangitwar', '1234567892', 'ajangitwar@gmail.com', '41,balaji nagar nagpur punapur road pardi nagpur:440035', 'Nagpur', 'shop', 'admin', '150', '250'),
(10, 'Aniket', 'Kuch bhi', '4564578965', 'bhushankuthe@gmail.com', '51,Bandi near hanuman nagar shantinagar nagpur', 'Nagpur', 'admin1', 'admin', '11', '565');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `mobile`, `email`, `address`, `uname`, `password`) VALUES
(1, 'Abhishek Jangitwar', '8975889364', 'ajangitwar@gmail.com', '41, Balaji nagar punapur road pardi nagpur', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `shop_owner`
--
ALTER TABLE `shop_owner`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `shop_owner`
--
ALTER TABLE `shop_owner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
