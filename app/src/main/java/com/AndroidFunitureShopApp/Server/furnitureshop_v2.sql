-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 05, 2023 lúc 04:05 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `furnitureshop_v2`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imageUrl` text COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `imageUrl`) VALUES
(1, 'Bed Room', 'https://xuongnoithatpula.vn/storage/images/2021/11/20/giuong-ngu-pula-pb10-1-1637342942.jpeg'),
(2, 'Living Room', 'https://sofakimphu.com/wp-content/uploads/2022/10/z3743223521986_197960b554c97a0e2dcf54c130175e05.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `imageUrl` text COLLATE utf8_unicode_ci NOT NULL,
  `originalPrice` bigint(20) DEFAULT NULL,
  `discount` int(11) NOT NULL,
  `price` bigint(20) NOT NULL,
  `detail` text COLLATE utf8_unicode_ci NOT NULL,
  `type` text COLLATE utf8_unicode_ci NOT NULL,
  `categoryId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `name`, `quantity`, `imageUrl`, `originalPrice`, `discount`, `price`, `detail`, `type`, `categoryId`) VALUES
(1, 'SONGESAND', 99, 'https://theme.hstatic.net/200000584705/1000969925/14/home_six_banner_item_image_2.png?v=2526', 100, 15, 87, 'Cover for sofa, with chaise/Totebo light beige', 'Wardrobes', 1),
(3, 'FRIHETEN', 200, 'https://theme.hstatic.net/200000584705/1000969925/14/home_insta_item_image_6_large.png?v=2526', 299, 10, 194, 'Sleeper sectional,3 seat w/storage, Skiftebo dark gray', 'Sofas', 2),
(4, 'GLOSTAD', 99, 'https://www.ikea.com/us/en/images/products/glostad-loveseat-knisa-dark-gray__0982867_pe815771_s5.jpg?f=s', 432, 20, 342, 'Loveseat, Knisa dark gray', 'Sofas', 1),
(5, 'SANELA', 99, 'https://www.ikea.com/us/en/images/products/sanela-room-darkening-curtains-1-pair-dark-gray__0565725_pe664471_s5.jpg?f=xl', 432, 15, 123, 'Room darkening curtains, 1 pair, dark gray', 'Curtains', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `role` text NOT NULL,
  `fullname` text DEFAULT NULL,
  `imageAva` text DEFAULT NULL,
  `defaultAdress` text DEFAULT NULL,
  `email` text DEFAULT NULL,
  `phone` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `fullname`, `imageAva`, `defaultAdress`, `email`, `phone`) VALUES
(1, 'Admin', '123456', 'admin', 'Viet Huynh', '0', '0', 'hvvieṭ.1617@gmail.com', '0822852022'),
(2, 'User', '123456', 'user', 'Viet Huynh', '0', '0', 'hvviet93.1617@gmail.com', '0822852022'),
(5, 'admin123', '123456', 'admin', 'Huynh Van Viet', '', '', '', ''),
(6, 'test', '123456', 'user', 'viet', '', '', '', '');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_category` (`categoryId`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`) USING HASH;

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
