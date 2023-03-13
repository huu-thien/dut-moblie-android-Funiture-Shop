-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 13, 2023 at 11:30 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `furnitureshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `quantity` int(11) NOT NULL,
  `imageUrl` text NOT NULL,
  `originalprice` bigint(20) NOT NULL,
  `discount` int(11) NOT NULL,
  `price` bigint(20) NOT NULL,
  `detail` text NOT NULL,
  `type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `quantity`, `imageUrl`, `originalprice`, `discount`, `price`, `detail`, `type`) VALUES
(1, 'SONGESAND', 99, 'https://www.ikea.com/us/en/images/products/songesand-wardrobe-white__0858618_pe660187_s5.jpg?f=s', 8888888, 15, 7555555, 'Cover for sofa, with chaise/Totebo light beige', 'Wardrobes'),
(2, 'FRIHETEN', 100, 'https://www.ikea.com/us/en/images/products/friheten-sleeper-sectional-3-seat-w-storage-skiftebo-dark-gray__0175610_pe328883_s5.jpg?f=xl', 10800000, 10, 9720000, 'Sleeper sectional,3 seat w/storage, Skiftebo dark gray', 'Sofas'),
(3, 'GLOSTAD', 99, 'https://www.ikea.com/us/en/images/products/glostad-loveseat-knisa-dark-gray__0982867_pe815771_s5.jpg?f=s', 6000000, 20, 4800000, 'Loveseat, Knisa dark gray\r\n\r\n', 'Sofas'),
(4, 'SANELA', 99, 'https://www.ikea.com/us/en/images/products/sanela-room-darkening-curtains-1-pair-dark-gray__0565725_pe664471_s5.jpg?f=xl', 4560000, 15, 3876000, 'Room darkening curtains, 1 pair, dark gray, 55x98 \"', 'Curtains');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
