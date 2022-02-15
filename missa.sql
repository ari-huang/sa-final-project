-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2022-01-07 10:16:55
-- 伺服器版本： 10.4.14-MariaDB
-- PHP 版本： 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `missa`
--

-- --------------------------------------------------------

--
-- 資料表結構 `course`
--

CREATE TABLE `course` (
  `Course_ID` int(11) NOT NULL,
  `Course_NAME` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `course`
--

INSERT INTO `course` (`Course_ID`, `Course_NAME`) VALUES
(1, '英語會話-日常篇(一)'),
(2, 'python'),
(3, 'java');

-- --------------------------------------------------------

--
-- 資料表結構 `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `login_times` int(11) DEFAULT 0,
  `status` varchar(255) DEFAULT '偶數會員'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `members`
--

INSERT INTO `members` (`id`, `name`, `email`, `password`, `modified`, `created`, `login_times`, `status`) VALUES
(1, '測試人員', 'test@cc.ncu.edu.tw', 'Test1234567', '2019-10-18 11:30:28', '2019-10-18 11:30:28', 0, '偶數會員'),
(3, '蔡亞恩', 'soba1021@gmail.com', 'Sobal02l', '2021-12-16 12:59:22', '2021-12-16 12:59:22', 59, '奇數會員'),
(4, '郝珮涵', 'patr8909@gmail.com', 'lulu6033', '2021-12-29 12:11:59', '2021-12-29 12:11:59', 11, '奇數會員'),
(5, '123', 'vivian12321@gmail.com', 'qaz3455666', '2022-01-06 13:48:17', '2022-01-06 13:48:17', 48, '偶數會員');

-- --------------------------------------------------------

--
-- 資料表結構 `message`
--

CREATE TABLE `message` (
  `Message_ID` int(11) NOT NULL,
  `QAarea_ID` varchar(45) DEFAULT NULL,
  `FinalModifyTime` varchar(45) DEFAULT NULL,
  `Message_content` varchar(45) DEFAULT NULL,
  `Video_Name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `message`
--

INSERT INTO `message` (`Message_ID`, `QAarea_ID`, `FinalModifyTime`, `Message_content`, `Video_Name`) VALUES
(33, NULL, '2022/1/5 下午7:55:57', '蔡亞恩好可愛！', 'java'),
(34, NULL, '2022/1/5 下午9:19:06', '你好！', 'python'),
(36, NULL, '2022/1/7 下午4:18:34', '你好', 'java');

-- --------------------------------------------------------

--
-- 資料表結構 `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `last_name` varchar(10) DEFAULT NULL,
  `first_name` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `create` datetime DEFAULT NULL,
  `modify` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `orders`
--

INSERT INTO `orders` (`id`, `last_name`, `first_name`, `email`, `address`, `phone`, `create`, `modify`) VALUES
(1, 'Chen', 'Yiwen', 'patr8909@gmail.com', '桃園市中壢區中大路300號', '0939-077-987', '2022-01-05 13:19:31', '2022-01-05 13:19:31'),
(2, 'Chen', 'Yiwen', 'patr8909@gmail.com', '桃園市中壢區中大路300號', '0939-077-987', '2022-01-06 12:12:24', '2022-01-06 12:12:24'),
(18, 'Chen', 'Yiwen', 'patr8909@gmail.com', '桃園市中壢區中大路300號', '0939-077-987', '2022-01-07 08:17:47', '2022-01-07 08:17:47');

-- --------------------------------------------------------

--
-- 資料表結構 `order_product`
--

CREATE TABLE `order_product` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(5) DEFAULT NULL,
  `subtotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `order_product`
--

INSERT INTO `order_product` (`id`, `order_id`, `product_id`, `price`, `quantity`, `subtotal`) VALUES
(1, 1, 3, 399.99, 12, 4799.88),
(2, 1, 4, 499.99, 14, 6999.86),
(3, 2, 1, 199.99, 11, 2199.89),
(4, 2, 3, 399.99, 5, 1999.95),
(5, 2, 2, 299.99, 7, 2099.93),
(6, 2, 4, 499.99, 3, 1499.97),
(7, 3, 1, 199.99, 10, 1999.9),
(8, 3, 4, 499.99, 7, 3499.93),
(9, 4, 2, 299.99, 3, 899.97),
(10, 4, 4, 499.99, 5, 2499.95),
(11, 5, 2, 299.99, 1, 299.99),
(12, 6, 5, 4300, 1, 4300),
(13, 7, 7, 999, 1, 999),
(14, 7, 2, 299.99, 1, 299.99),
(15, 7, 8, 8787, 1, 8787),
(16, 7, 12, 1111, 1, 1111),
(17, 8, 7, 999, 1, 999),
(18, 9, 3, 399.99, 1, 399.99),
(19, 10, 1, 199.99, 1, 199.99),
(20, 11, 2, 299.99, 1, 299.99),
(21, 11, 3, 399.99, 1, 399.99),
(22, 12, 16, 2022, 1, 2022),
(23, 13, 16, 2022, 1, 2022),
(24, 14, 16, 2022, 1, 2022),
(25, 15, 16, 2022, 1, 2022),
(26, 16, 16, 2022, 1, 2022),
(27, 17, 16, 2022, 1, 2022),
(28, 18, 16, 2022, 1, 2022);

-- --------------------------------------------------------

--
-- 資料表結構 `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `image`, `describe`, `category`) VALUES
(1, '市場行銷學', 199.99, 'mk1.jfif', '這是iPhone5C', 1),
(2, '電商行銷', 299.99, 'mk2.jfif', '這是iPhone5S', 1),
(3, '顧客樣貌分析', 399.99, 'mk3.jfif', '這是iPhone 6', 1),
(4, '提升行銷力', 499.99, 'mk4.jfif', '這是iPhone 6 Plus', 1),
(5, '自學英文最短路徑', 4300, 'EN2.jfif', '自學英文的最短路徑', 2),
(7, '跟外國人學道地英文', 999, 'EN.jpg', '自學英文的最短路徑', 2),
(8, '職場英文大補帖', 8787, 'EN3.jfif', '自學英文的最短路徑', 2),
(9, '英文文法基礎', 8787, 'html.jfif', '英文文法基礎', 2),
(10, 'HTML1', 1111, 'html.jfif', 'HTML', 3),
(11, 'HTML2', 1111, 'html2.jfif', 'HTML', 3),
(12, 'HTML3', 1111, 'html1.jfif', 'HTML', 3),
(13, 'HTML4', 1111, 'html3.jpg', 'HTML', 3),
(14, '教你寫出漂亮文案', 499.99, 'mk6.jfif', '這是iPhone 6 Plus', 1),
(15, '從0開始學行銷', 499.99, 'mk5.jfif', '這是iPhone 6 Plus', 1),
(16, 'java', 2022, 'EN2.jfif', '✨好評熱銷中✨簡俊謙老師的不藏私教學！', 4),
(17, '區塊鏈技術與應用', 1110107, 'BC.jpg', '✨名師葉羅堯親自上陣✨\r\n好課，不修嗎？', 5);

-- --------------------------------------------------------

--
-- 資料表結構 `students`
--

CREATE TABLE `students` (
  `Student_email` varchar(200) DEFAULT NULL,
  `Student_name` varchar(200) DEFAULT NULL,
  `Student_password` varchar(200) DEFAULT NULL,
  `Student_modified` datetime DEFAULT current_timestamp(),
  `Student_created` datetime DEFAULT current_timestamp(),
  `Student_login_times` int(11) DEFAULT 0,
  `Student_status` varchar(200) DEFAULT '偶數會員',
  `Student_ID` int(11) NOT NULL,
  `Student_NumberOfRegisteredCourse` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `students`
--

INSERT INTO `students` (`Student_email`, `Student_name`, `Student_password`, `Student_modified`, `Student_created`, `Student_login_times`, `Student_status`, `Student_ID`, `Student_NumberOfRegisteredCourse`) VALUES
('patr8909@gmail.com', '123', 'qaz741852', '2022-01-07 08:17:14', '2021-12-29 22:33:47', 17, '奇數會員', 3, 0),
('peihan914@gmail.com', '9999', 'lll454545', '2021-12-30 07:41:06', '2021-12-30 07:41:06', 58, '偶數會員', 5, NULL),
('patr8909@yahoo.com.tw', 'test3', 'hello123456', '2021-12-30 16:39:23', '2021-12-30 16:39:23', 39, '奇數會員', 6, NULL),
('ppp@ef.com', 'kkkkk1254', 'qaz741852', '2022-01-03 14:28:55', '2021-12-30 16:50:58', 28, '偶數會員', 7, NULL),
('pdojfi@kwpoejf.com', 'Pei-Han Hao', 'helloworld789', '2021-12-30 17:02:38', '2021-12-30 16:57:04', 2, '偶數會員', 8, NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `video`
--

CREATE TABLE `video` (
  `Video_ID` int(11) NOT NULL,
  `Video_url` varchar(45) DEFAULT NULL,
  `Video_Name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `video`
--

INSERT INTO `video` (`Video_ID`, `Video_url`, `Video_Name`) VALUES
(1, 'https://www.youtube.com/embed/zdMUJJKFdsU', 'python'),
(2, 'https://www.youtube.com/embed/xk4_1vDrzzo', 'java'),
(3, 'https://www.youtube.com/embed/ktdqlQ1zhg4', '英語會話-日常篇(一)');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`Course_ID`);

--
-- 資料表索引 `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`Message_ID`);

--
-- 資料表索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `order_product`
--
ALTER TABLE `order_product`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`Student_ID`);

--
-- 資料表索引 `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`Video_ID`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `course`
--
ALTER TABLE `course`
  MODIFY `Course_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `message`
--
ALTER TABLE `message`
  MODIFY `Message_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `order_product`
--
ALTER TABLE `order_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `students`
--
ALTER TABLE `students`
  MODIFY `Student_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `video`
--
ALTER TABLE `video`
  MODIFY `Video_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
