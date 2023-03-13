-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 13, 2023 at 06:31 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 7.4.30

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
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `NameCategory` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ImageCategory` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `NameCategory`, `ImageCategory`) VALUES
(1, 'Bed Room', 'https://xuongnoithatpula.vn/storage/images/2021/11/20/giuong-ngu-pula-pb10-1-1637342942.jpeg'),
(2, 'Living Room', 'https://sofakimphu.com/wp-content/uploads/2022/10/z3743223521986_197960b554c97a0e2dcf54c130175e05.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `quantity` int(11) NOT NULL,
  `imageUrl` text NOT NULL,
  `originalPrice` bigint(20) DEFAULT NULL,
  `discount` int(11) NOT NULL,
  `price` bigint(20) NOT NULL,
  `detail` text NOT NULL,
  `type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `quantity`, `imageUrl`, `originalPrice`, `discount`, `price`, `detail`, `type`) VALUES
(1, 'SONGESAND', 99, 'https://theme.hstatic.net/200000584705/1000969925/14/home_six_banner_item_image_2.png?v=2526', 100, 15, 87, 'Cover for sofa, with chaise/Totebo light beige', 'Wardrobes'),
(2, 'FRIHETEN', 200, 'https://theme.hstatic.net/200000584705/1000969925/14/home_insta_item_image_6_large.png?v=2526', 299, 10, 194, 'Sleeper sectional,3 seat w/storage, Skiftebo dark gray', 'Sofas'),
(3, 'GLOSTAD', 99, 'https://www.ikea.com/us/en/images/products/glostad-loveseat-knisa-dark-gray__0982867_pe815771_s5.jpg?f=s', 432, 20, 342, 'Loveseat, Knisa dark gray\r\n\r\n', 'Sofas'),
(4, 'SANELA', 99, 'https://www.ikea.com/us/en/images/products/sanela-room-darkening-curtains-1-pair-dark-gray__0565725_pe664471_s5.jpg?f=xl', 432, 15, 123, 'Room darkening curtains, 1 pair, dark gray, 55x98 \"', 'Curtains'),
(5, 'test Name', 123, 'https://theme.hstatic.net/200000584705/1000969925/14/home_six_banner_item_image_1.png?v=2526', 432, 3423, 321, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry', 'test');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
