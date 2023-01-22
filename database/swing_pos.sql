-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2023 at 12:42 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `swing_pos`
--

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `contact_id` int(11) NOT NULL,
  `phone` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `zip_code` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `city` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `country` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`contact_id`, `phone`, `email`, `address`, `zip_code`, `city`, `province`, `country`) VALUES
(1, '12346345', 'mnmstr@yahoo.it', 'piazza masaniello', '3453', 'il vomero', 'napoli', 'italy'),
(2, '123456789', 'ak@gmail.com', 'kakuda square 45', '98787', 'nyc', 'new york', 'usa'),
(3, '123443', 'ma@yahoo.it', 'via masakona 23', '234', 'milazzo', 'milazzo', 'italy'),
(4, '0897675', 'rb@gmail.com', 'balboa road 23', '234234', 'philadelphia', 'pennsilvanya', 'usa'),
(5, '0998768', 'ac@hotmail.com', 'creed bvd 5676', '4444', 'philly', 'pennsilvanya', 'usa'),
(6, '0998678', 'ak@hotmail.com', 'kolonova andreina street 98', '2345', 'moscow', 'bobobob', 'russia'),
(7, '345345', 'suppliercontact1@hotmail.com', 'supplier road 323', '3we23', 'quebec', 'quebec', 'canada'),
(8, '08567', 'suppliertest2@gmail.com', 'supplier 2 bvd 523', '1234', 'lilehammer', 'oslo', 'norway'),
(12, '66666', 'sup4@gmail.com', 'supplier4 road 234', '5654', 'ontario', 'ontario', 'canada'),
(13, '1234', 'qwerty', 'zxcvbn', 'qqq', 'www', 'eee', 'rrrr'),
(14, '1234', 'asdf@zxcv.com', 'gioggio road 98', '333', 'new delhi', 'new delhi', 'india'),
(15, '32346345', 'info@sarv.com', 'sarv road 23', '745', 'colorado', 'colorado', 'usa'),
(16, 'sdfsdf', 'sdfsdf', 'sdfsdfsd', 'fsdfsdf', 'sdfsdfsdf', 'sdfsdf', 'sdfsdfsdf'),
(17, '08768', 'bt@bitrans.info', 'via dei tori 3', '00456', 'colinari', 'TO', 'italia'),
(18, '089765', 'smg@gmaic.com', 'machida road 34', '54354', 'saitama', 'tokyo', 'japan'),
(19, '12345', 'gc@giggiocorp.com', 'giggo road 450', '534', 'positano', 'napoli', 'italy'),
(20, '967756765', 'jj@gmail.com', 'Jabbei road', '643234', 'Kathampur', 'Mumbai', 'India'),
(21, '0987665', 'mb78@hotmail.com', 'brownie land 45', '234234', 'brownland', 'brownland', 'neverland'),
(22, '4354', 'info@supplino.com', 'supplino road 34', '65454', 'ariccia', 'roma', 'italy'),
(23, '+19843423', 'sm@yahoo.com', 'miller street 192', '23231', 'fofofofof', 'indiana', 'usa'),
(24, '234234', 'info@mgoodsinc.com', 'magic goods road 23', '24234', 'Terranova ', 'ontario', 'canada'),
(25, '2389080956', 'mnewton@hotmail.com', 'mechanics road 46', '9566', 'somewhere nearby', 'london', 'uk'),
(26, '23443', 'tutankhamon@email.com', 'Piramid 1', '222', 'lapress', 'El Cairo', 'Egypt'),
(27, '3453465', 'fghfgh', 'rytryrty', '3453', 'colorado', 'pokemon', 'usa'),
(28, '6344354', 'oozzyy@osbourne.com', 'asdfg', '324423', 'austin', 'texas', 'usa'),
(29, '2345345', 'th@hoganinc.net', 'hogan street', '234', 'atlantic city', 'new jersey', 'usa'),
(30, '645', 'fas@katamail.com', 'first street 23', '23432', 'oakland', 'rhode island', 'usa'),
(31, '9756657', 'jk@krishnamurti.org', 'krishnamurti road 35', '7777545', 'mumbai', 'mumbai', 'india'),
(32, '56435', 'info@soranos.com', 'soranos road 745', '234', 'mumbai', 'bangalore', 'india'),
(33, '3453', 'info@lincolnsgoods.com', 'asdfg road', '234', 'oklahoma', 'oklahoma', 'usa'),
(34, '846546', 'mt@japmail.com', 'mashiro road 534', '8546', 'nara', 'nara', 'japan'),
(35, '934345', 'af@gmail.com', 'franklin bvd 95', '345', 'philly', 'pennsilvanya', 'usa'),
(36, '000787', 'll@hotmail.com', 'lucy lu square 8', '1234', 'newak', 'new jersey', 'usa'),
(37, '5465', 'info@koalabear.com', 'koala road 13', '234', 'weissbier', 'hamgurg', 'germany'),
(38, '35432', 'sg@hotmail.com', 'golden bvd 98', '754', 'wichita', 'nevada', 'usa'),
(39, '956765', 'nt@tesla.com', 'tesla road 34', '234', 'arezzo', 'italia', 'italia'),
(40, '34534', 'tg@hotmail.com', 'tiger goods bvd 324', '435', 'oklahoma city', 'ohio', 'usa'),
(41, '7854654', 'gp@yahoo.it', 'pompetta road 32', '2333', 'pompei', 'napoli', 'italia'),
(42, '5435345', 'sp@email.com', 'podtown road 34', '967', 'pottstown', 'philly', 'usa'),
(44, '896546', 'lt@hotmail.com', 'johnson road 34', '3454', 'las vegas', 'nevada', 'usa'),
(45, '432wer', 'as@katamail.com', 'samansah road 23', '345', 'new delhi', 'new delhi', 'india'),
(46, '845654', 'tj@us.gov', 'jefferson street 23', '5324', 'washington ', 'district of columbia', 'usa'),
(47, '6435', 'ki@katamail.com', 'icelmani square 634', '856', 'algeri', 'algeri', 'algeria'),
(48, '87456', 'scic@gmail.com', 'ciccone bvd 234', '8657', 'napoli', 'napoli', 'italy'),
(49, '87546', 'dj@hotmail.com', 'johnson road 98', '234', 'boston', 'massachussets', 'usa'),
(51, '6435345', 'em@yahoo.it', 'mouseth road 54', '324', 'portland', 'oregon', 'usa'),
(52, '2324', 'contact@pokemon.com', 'pokemon road 34', '234', 'seattle', 'washington', 'usa'),
(53, '8456', 'ms@yahoo.it', 'calle sanchez 434', '877456', 'ciudad de mexico', 'ciudad de mexico', 'mexico'),
(54, '078678', 'info@slayers.com', 'slayers road 87', '234', 'buffalo', 'usa', 'usa'),
(55, '523432', 'jf@gmail.com', 'feltman bvd 23', '234', 'wichita', 'kansas', 'usa'),
(56, '23324', 'info@dakota.com', 'dakota bvd 34', '234', 'seattle', 'washington', 'usa'),
(57, '2343', 'mk@hotmail.com', 'kensington square 34', '234', 'london', 'london', 'uk'),
(58, '6234', 'gp@gmail.com', 'calle pompetta 34', '9678', 'sevilla', 'sevilla', 'spain'),
(59, '0768', 'info@corpa.es', 'calle basc 34', '8567', 'granada', 'granada', 'spain');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` enum('male','female') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'male',
  `id_number` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthdate` date NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `name`, `lastname`, `gender`, `id_number`, `birthdate`, `creation_date`) VALUES
(141, 'masaniello', 'muzzica', 'male', '24234', '1973-07-28', '2000-01-24 15:54:22'),
(142, 'akiko', 'kakuda', 'female', '3245', '2002-10-07', '2000-04-25 16:11:05'),
(143, 'masako', 'angeli', 'female', '24324', '1984-09-16', '2001-05-26 09:16:15'),
(146, 'rocky', 'balboa', 'male', '324324', '1988-04-15', '2002-06-26 14:50:30'),
(147, 'apollo', 'creed', 'male', '777777', '1972-12-19', '2002-08-26 15:34:13'),
(148, 'andreina', 'kolonova', 'female', '98756754', '2001-07-21', '2003-02-27 09:20:56'),
(149, 'kyoko', 'machida', 'female', '0856767', '1962-03-30', '2003-03-10 16:23:08'),
(150, 'Jajji', 'Jabbei', 'male', '23423423', '1988-03-07', '2003-08-11 12:57:39'),
(151, 'melissa', 'brown', 'female', '234234', '1954-12-29', '2003-09-11 13:22:44'),
(152, 'Jenniffer', 'Miller', 'female', '876546', '1992-08-28', '2004-03-14 16:07:49'),
(153, 'marcos', 'lawson', 'male', '33325', '1902-04-04', '2004-03-15 12:02:30'),
(154, 'Abdullah', 'Icelmani', 'male', '000000', '1902-01-01', '2004-08-16 16:15:04'),
(155, 'mohammad', 'ali', 'male', '7866', '1936-08-02', '2005-01-17 09:05:10'),
(156, 'jiddu', 'krishnamurti', 'male', '35445', '1934-09-18', '2005-06-28 12:02:25'),
(157, 'mashiro', 'tamigi', 'male', '5634534', '1967-10-14', '2006-04-30 15:11:09'),
(158, 'nonnina', 'franklin', 'female', '0348ty', '1973-12-17', '2007-08-30 16:00:16'),
(159, 'lucy', 'lu', 'female', '623424', '1977-09-18', '2008-03-30 16:45:42'),
(160, 'Soichirou', 'Golden', 'male', '8946', '1902-01-01', '2008-04-02 11:30:23'),
(161, 'Nicola', 'Tesla', 'male', '6345', '1908-08-04', '2008-07-02 12:05:53'),
(162, 'girolamo', 'pompetta', 'male', '24234', '1990-10-17', '2009-02-02 13:49:38'),
(163, 'sarah', 'mccormick', 'female', '6435', '1985-09-20', '2009-08-02 14:25:02'),
(164, 'leah ', 'johnson', 'female', '7546', '1948-09-04', '2010-01-02 14:46:08'),
(165, 'tonia', 'jefferson', 'female', '8456', '1956-09-19', '2010-04-02 15:40:18'),
(166, 'sofia', 'ciccone', 'female', '645345', '1949-03-04', '2022-12-16 15:52:06'),
(167, 'don', 'johnson', 'male', '89456', '1962-11-17', '2022-12-16 16:07:28'),
(168, 'helena', 'mouseth', 'female', '6345', '1912-02-22', '2022-12-21 06:18:23'),
(169, 'maureen', 'sanchez', 'female', '234', '1927-09-30', '2022-12-21 06:47:29'),
(170, 'jonathan', 'feltman', 'male', '111111', '2003-10-18', '2022-12-22 06:17:21'),
(171, 'maurice', 'kensington', 'male', '234', '2000-10-26', '2022-12-22 07:41:50'),
(172, 'girolamo ', 'pompetta', 'male', '234', '1977-08-15', '2022-12-27 07:44:20');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `invoice_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `applied_tax` int(2) NOT NULL DEFAULT 0,
  `total_amount` decimal(6,2) NOT NULL,
  `discount` decimal(6,2) DEFAULT NULL,
  `total_paid` decimal(6,2) NOT NULL,
  `payment_method` enum('cash','banktransfer','creditcard','debitcard','cheque','paypal') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'cash',
  `note` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `invoiced_on` datetime NOT NULL DEFAULT current_timestamp(),
  `is_voided` tinyint(1) DEFAULT 0,
  `void_reason` text COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoice_id`, `customer_id`, `applied_tax`, `total_amount`, `discount`, `total_paid`, `payment_method`, `note`, `invoiced_on`, `is_voided`, `void_reason`) VALUES
(8, 156, 20, '14.39', '4.39', '10.00', 'debitcard', 'test', '2000-12-16 12:05:54', 0, NULL),
(9, 141, 20, '4.68', '0.00', '4.68', 'paypal', 'muzzica', '2001-12-16 12:22:29', 0, NULL),
(10, 161, 20, '57.60', '17.60', '40.00', 'cash', '', '2002-12-16 14:24:30', 0, NULL),
(11, 146, 20, '23.98', '0.98', '23.00', 'cash', '', '2002-12-16 16:45:56', 0, NULL),
(12, 168, 20, '10.79', '0.79', '10.00', 'creditcard', 'test sale', '2003-12-21 06:26:31', 0, NULL),
(13, 171, 20, '90.00', '10.00', '80.00', 'banktransfer', 'test', '2004-12-24 07:39:42', 0, NULL),
(14, 171, 20, '96.00', '6.00', '90.00', 'debitcard', 'test', '2005-12-24 08:05:42', 0, NULL),
(15, 155, 20, '11.39', '0.39', '11.00', 'cash', 'good one', '2006-12-24 08:30:57', 0, NULL),
(16, 172, 20, '12.00', '0.00', '12.00', 'debitcard', 'test', '2007-12-27 07:50:24', 0, NULL),
(17, 170, 20, '9.60', '0.60', '9.00', 'cash', 'test', '2007-12-27 08:32:49', 0, NULL),
(20, 148, 20, '12.01', '0.00', '12.01', 'cash', 'test', '2008-12-27 08:49:05', 0, NULL),
(21, 154, 20, '36.00', '0.00', '36.00', 'paypal', '', '2008-12-27 08:52:00', 0, NULL),
(22, 161, 20, '2.40', '0.00', '2.40', 'cash', '', '2008-12-28 06:31:52', 0, NULL),
(23, 150, 20, '3.60', '0.60', '3.00', 'debitcard', 'test', '2009-12-28 07:07:58', 0, NULL),
(24, 172, 20, '20.38', '0.38', '20.00', 'creditcard', 'teessetsetstsetsetse', '2010-12-31 14:14:29', 0, NULL),
(25, 166, 20, '28.78', '0.00', '28.78', 'creditcard', 'ertert\neryertertert', '2022-12-31 14:16:53', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `media_url1` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `media_url2` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `media_url3` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `acquisition_price` decimal(6,2) NOT NULL,
  `selling_price` decimal(6,2) NOT NULL,
  `items_per_unit` int(2) NOT NULL,
  `product_condition` enum('brandnew','likenew','good','acceptable','worn') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_purchased` tinyint(4) NOT NULL DEFAULT 0,
  `in_cart` tinyint(1) NOT NULL DEFAULT 0,
  `added_on` datetime NOT NULL DEFAULT current_timestamp(),
  `product_category_id` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `invoice_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `name`, `description`, `media_url1`, `media_url2`, `media_url3`, `acquisition_price`, `selling_price`, `items_per_unit`, `product_condition`, `is_purchased`, `in_cart`, `added_on`, `product_category_id`, `supplier_id`, `invoice_id`) VALUES
(1, 'arduino cookbook', 'learn arduino easily', NULL, NULL, NULL, '0.00', '11.99', 1, 'likenew', 1, 0, '2000-11-03 12:22:05', 1, 2, 8),
(2, 'ride the lightning', 'metallica\'s rocking album', NULL, NULL, NULL, '0.00', '9.50', 1, 'good', 0, 0, '2000-11-04 14:48:05', 2, 7, NULL),
(3, 'timer 555', 'a set of timer 555 by texas instruments', NULL, NULL, NULL, '0.00', '3.90', 10, 'brandnew', 1, 0, '2000-11-13 14:48:48', 3, 5, 9),
(4, 'attiny 85', 'a set of attiny 85 microcontrollers by atmel', NULL, NULL, NULL, '0.00', '9.90', 10, 'brandnew', 0, 0, '2001-01-03 14:49:37', 3, 1, NULL),
(5, 'electronics for losers', 'learn robotics with this easy hand-on guide', NULL, NULL, NULL, '0.00', '11.90', 1, 'likenew', 0, 0, '2001-01-10 16:25:56', 1, 10, NULL),
(6, 'relic', 'first installment of a sci-fi horror story ', NULL, NULL, NULL, '0.00', '3.99', 1, 'good', 0, 0, '2001-02-11 16:28:36', 1, 10, NULL),
(7, 'reliquary', 'second instalment of the sci-fi horror story', NULL, NULL, NULL, '0.00', '4.99', 1, 'good', 0, 0, '2001-03-10 16:29:19', 1, 10, NULL),
(8, 'mozart classic', 'classical music by mozart', NULL, NULL, NULL, '0.00', '4.99', 1, 'likenew', 0, 0, '2001-03-14 16:56:19', 2, 13, NULL),
(9, 'doom 2016 ost', 'grat ost from the latest doom game', NULL, NULL, NULL, '0.00', '7.99', 1, 'likenew', 0, 0, '2001-03-15 09:11:42', 2, 12, NULL),
(10, 'ceramic capacitors', 'assortments of capacitors ', NULL, NULL, NULL, '0.00', '3.75', 10, 'good', 0, 0, '2001-03-16 13:06:22', 3, 8, NULL),
(11, 'war and peace', 'long book by tolstoy', NULL, NULL, NULL, '0.00', '5.99', 1, 'worn', 0, 0, '2001-03-17 13:08:22', 1, 12, NULL),
(13, '2n2222 npn transistor', 'set of 2n2222 transistors', NULL, NULL, NULL, '0.00', '1.99', 20, 'brandnew', 0, 0, '2001-07-28 12:05:56', 3, 16, NULL),
(14, 'buddha statue', 'a middle-sized buddha statue made with copper', NULL, NULL, NULL, '0.00', '249.00', 1, 'likenew', 0, 0, '2001-10-28 13:05:19', 4, 17, NULL),
(15, 'grumpy statue', 'a little statue of one of teh dwarves from Snow White', NULL, NULL, NULL, '0.00', '50.00', 1, 'likenew', 0, 0, '2001-11-29 09:44:57', 4, 18, NULL),
(16, 'acacia plant', 'a nice acacia plant', NULL, NULL, NULL, '0.00', '15.00', 1, 'brandnew', 1, 0, '2001-12-29 15:02:26', 4, 18, 10),
(17, 'arduino uno', 'basic arduino board', NULL, NULL, NULL, '0.00', '19.99', 1, 'brandnew', 1, 0, '2001-12-30 09:12:48', 3, 18, 11),
(18, 'back to the future ost', 'the entire theme songs from the cult movie back to the future', NULL, NULL, NULL, '0.00', '9.99', 1, 'good', 0, 0, '2002-01-01 09:17:03', 2, 14, NULL),
(19, 'wooden mallet', 'wooden mallet for small housework', NULL, NULL, NULL, '0.00', '4.99', 1, 'likenew', 1, 0, '2002-01-01 11:10:23', 5, 16, 25),
(20, 'gardenia', 'nice gardenia flower', NULL, NULL, NULL, '0.00', '6.99', 1, 'brandnew', 1, 0, '2002-01-02 12:02:22', 4, 19, 15),
(21, 'robotics for noobs', 'learn how to build robots with this easy guide', NULL, NULL, NULL, '0.00', '9.90', 1, 'good', 0, 0, '2002-01-02 14:23:12', 1, 20, NULL),
(22, 'chainsaw', 'doom\'s chainsaw', NULL, NULL, NULL, '0.00', '9999.99', 1, 'worn', 0, 0, '2002-01-02 15:14:32', 5, 8, NULL),
(23, 'jurassic park themes', 'all themes from the cult movie jurassic park', NULL, NULL, NULL, '0.00', '9.50', 1, 'likenew', 0, 0, '2002-01-02 15:49:43', 2, 16, NULL),
(24, 'gardening for noobs', 'gardening book', NULL, NULL, NULL, '5.00', '8.99', 1, 'acceptable', 1, 0, '2002-01-13 13:28:15', 1, 23, 24),
(25, 'playboy', 'release of june 2001', NULL, NULL, NULL, '2.00', '3.00', 1, 'acceptable', 1, 0, '2022-12-14 14:23:30', 1, 5, 10),
(26, 'shubert collection', 'shubert\'s masterpieces', NULL, NULL, NULL, '5.00', '6.99', 1, 'likenew', 1, 0, '2022-12-14 14:26:44', 2, 6, 12),
(27, 'Arduino UNO', 'basic arduino board', NULL, NULL, NULL, '5.99', '7.99', 1, 'likenew', 1, 0, '2022-12-14 14:58:38', 3, 8, 24),
(28, 'wooden fences', 'wooden fences, max length: 120m', NULL, NULL, NULL, '75.00', '110.00', 1, 'likenew', 0, 0, '2022-12-14 15:21:56', 4, 7, NULL),
(29, 'b&D drill', 'Black and Decker\'s drill with 2 tips included', NULL, NULL, NULL, '20.00', '20.00', 1, 'good', 0, 0, '2022-12-14 15:23:47', 5, 7, NULL),
(30, 'hatchet', 'used hatchet for small chopping work', NULL, NULL, NULL, '3.00', '6.00', 1, 'acceptable', 0, 0, '2022-12-14 15:24:40', 5, 7, NULL),
(31, 'levi\'s jeans', 'XL-sized denim trousers', NULL, NULL, NULL, '5.00', '8.99', 1, 'good', 1, 0, '2022-12-14 15:25:36', 6, 8, 25),
(32, 'napapijri wind breaker', 'rainproof windbraker by napapijri', NULL, NULL, NULL, '15.00', '25.00', 1, 'good', 0, 0, '2022-12-14 15:27:12', 6, 8, NULL),
(33, 'H&M wool hat', 'a wool hat by H&M', NULL, NULL, NULL, '3.00', '5.99', 1, 'likenew', 0, 0, '2022-12-14 15:28:50', 6, 8, NULL),
(34, 'trekking shoes', 'trekking shoes by decathlon', NULL, NULL, NULL, '5.00', '7.99', 1, 'acceptable', 0, 0, '2022-12-14 15:29:46', 6, 9, NULL),
(35, 'sephora makeup box', 'makeup kit by sephora', NULL, NULL, NULL, '6.00', '10.00', 1, 'good', 1, 0, '2022-12-14 15:30:26', 7, 9, 21),
(36, 'baby shampoo', 'Johnson\'s baby shampoo', NULL, NULL, NULL, '1.00', '2.00', 1, 'brandnew', 1, 0, '2022-12-14 15:31:05', 7, 9, 22),
(37, 'swarowsky pendant', 'original swarowsky pendant', NULL, NULL, NULL, '10.00', '20.00', 1, 'good', 1, 0, '2022-12-14 15:32:50', 8, 14, 21),
(38, 'star earrings', 'earrings with shape of a star', NULL, NULL, NULL, '0.90', '1.50', 1, 'likenew', 0, 0, '2022-12-14 15:34:00', 8, 14, NULL),
(39, 'white gold ring', '18-carat-ring made of platinum', NULL, NULL, NULL, '120.00', '150.00', 1, 'likenew', 0, 0, '2022-12-14 15:35:41', 8, 14, NULL),
(40, 'raspberry pi 3', 'raspberry pi hardly used', NULL, NULL, NULL, '20.00', '30.00', 1, 'likenew', 1, 0, '2022-12-15 14:06:31', 3, 6, 10),
(41, '32\" tv set', 'tv set by samsung', NULL, NULL, NULL, '150.00', '250.00', 1, 'good', 0, 0, '2022-12-15 14:07:22', 3, 6, NULL),
(42, 'asus rog laptop', 'gaming laptop', NULL, NULL, NULL, '350.00', '450.01', 1, 'good', 0, 0, '2022-12-15 14:08:16', 3, 6, NULL),
(43, 'bafang ebike kit', 'kit for converting manual bikes into ebikes', NULL, NULL, NULL, '50.00', '100.00', 1, 'acceptable', 0, 0, '2022-12-15 14:09:12', 3, 6, NULL),
(44, 'wooden bench', 'old wooden bench', NULL, NULL, NULL, '30.00', '55.00', 1, 'acceptable', 0, 0, '2022-12-15 14:10:41', 4, 10, NULL),
(45, 'gazebo', 'gazebo in good condition', NULL, NULL, NULL, '100.00', '200.00', 1, 'acceptable', 0, 0, '2022-12-15 14:11:34', 4, 10, NULL),
(46, 'football', 'football from brand mizuno', NULL, NULL, NULL, '20.00', '35.00', 1, 'good', 0, 0, '2022-12-15 14:12:14', 6, 11, NULL),
(47, 'nike jordan', 'sneakers in good conditions', NULL, NULL, NULL, '35.00', '55.00', 1, 'good', 0, 0, '2022-12-15 14:12:52', 6, 11, NULL),
(48, 'tennis raquets', 'a pair of tennis raquets', NULL, NULL, NULL, '5.00', '10.00', 1, 'worn', 0, 0, '2022-12-15 14:13:30', 6, 11, NULL),
(49, 'muay thai bag', 'muay thai banana bag by twins', NULL, NULL, NULL, '100.00', '200.00', 1, 'good', 0, 0, '2022-12-15 14:14:26', 6, 11, NULL),
(50, 'silver necklace', 'silver necklace', NULL, NULL, NULL, '10.00', '20.00', 1, 'good', 0, 0, '2022-12-15 14:16:37', 8, 13, NULL),
(51, 'rolex watch', 'used rolex watch', NULL, NULL, NULL, '900.00', '1500.00', 1, 'good', 0, 0, '2022-12-15 14:17:31', 8, 13, NULL),
(52, 'alien vs predator', 'first movie of the avp saga', NULL, NULL, NULL, '1.00', '3.00', 1, 'acceptable', 1, 0, '2022-12-15 14:18:37', 12, 15, 23),
(53, 'alien vs predator 2', 'second chapter of the avp saga', NULL, NULL, NULL, '2.00', '4.00', 1, 'good', 0, 0, '2022-12-15 14:19:26', 12, 15, NULL),
(54, 'titanic', 'movie staring leonardo di caprio', NULL, NULL, NULL, '3.00', '5.00', 1, 'worn', 0, 0, '2022-12-15 14:19:56', 12, 15, NULL),
(55, 'night rider', 'original series in vhs', NULL, NULL, NULL, '100.00', '200.00', 1, 'acceptable', 0, 0, '2022-12-15 14:20:42', 12, 15, NULL),
(56, 'blender', 'food blender by braun', NULL, NULL, NULL, '5.00', '10.00', 1, 'good', 0, 0, '2022-12-15 14:21:34', 5, 15, NULL),
(57, 'screwdriver set', 'set of screwdrivers', NULL, NULL, NULL, '2.00', '4.00', 1, 'acceptable', 1, 0, '2022-12-15 14:22:23', 5, 15, 16),
(58, 'dylan dog n1', 'first ever printed copy of dylan dog', NULL, NULL, NULL, '300.00', '450.00', 1, 'good', 0, 0, '2022-12-15 14:23:20', 1, 16, NULL),
(59, 'dog training for noobs', 'book for beginner dog trainers', NULL, NULL, NULL, '5.00', '8.00', 1, 'acceptable', 0, 0, '2022-12-15 14:23:56', 1, 16, NULL),
(60, 'kamasutra', '99 poses and more for the insatiable sex lovers', NULL, NULL, NULL, '6.00', '10.00', 1, 'acceptable', 0, 0, '2022-12-16 14:07:53', 1, 8, NULL),
(61, 'raspberyy pi for noobs', 'book about raspberry pi', NULL, NULL, NULL, '30.00', '50.00', 1, 'likenew', 0, 0, '2022-12-16 14:11:14', 1, 8, NULL),
(63, 'archonian\'s story', 'book about archonians', NULL, NULL, NULL, '1.00', '2.00', 1, 'acceptable', 1, 0, '2022-12-21 06:24:26', 1, 25, 12),
(64, 'chipped frog statue', 'frog statue for the garden', NULL, NULL, NULL, '5.01', '10.01', 1, 'good', 1, 0, '2022-12-21 06:49:02', 4, 26, 20),
(65, 'handmade soap', 'ecologic soap ', NULL, NULL, NULL, '3.00', '10.00', 1, 'brandnew', 1, 0, '2022-12-22 06:22:15', 7, 26, 25),
(66, 'laptop repair guide', 'learn how to repair laptops', NULL, NULL, NULL, '3.00', '5.00', 1, 'acceptable', 1, 0, '2022-12-22 07:34:03', 1, 27, 17),
(67, 'the walkin dead n 34', 'the walking dead comic', NULL, NULL, NULL, '2.00', '3.00', 1, 'likenew', 1, 0, '2022-12-22 07:34:38', 1, 27, 17),
(68, 'the wlaking dead n1', 'original first instalment of the comic saga', NULL, NULL, NULL, '50.00', '75.00', 1, 'likenew', 1, 0, '2022-12-22 07:35:26', 1, 27, 13),
(69, 'attiny85', 'set of attiny85 microchips', NULL, NULL, NULL, '3.00', '6.00', 5, 'brandnew', 1, 0, '2022-12-22 07:40:05', 3, 18, 16),
(70, 'topolino n1', 'frst instalment of the comic topolino', NULL, NULL, NULL, '50.00', '80.00', 1, 'likenew', 1, 0, '2022-12-24 08:02:00', 1, 2, 14),
(71, 't-shirt', 'erter', NULL, NULL, NULL, '4.00', '6.00', 1, 'acceptable', 0, 0, '2022-12-24 08:02:49', 9, 2, NULL),
(72, 'irz44 mosfet transistor', 'set of mosfet transistors', NULL, NULL, NULL, '1.00', '2.50', 10, 'brandnew', 1, 0, '2022-12-24 08:30:05', 3, 5, 15),
(73, 'levi\'s denim jeans', 'size 52 denim jeans', NULL, NULL, NULL, '10.00', '19.99', 1, 'good', 0, 0, '2022-12-27 07:48:05', 9, 28, NULL),
(74, 'hp omen laptop', 'omen gaming laptop', NULL, NULL, NULL, '350.00', '500.00', 1, 'likenew', 0, 0, '2022-12-28 06:22:05', 3, 22, NULL),
(75, 'garden lamp', 'owl-shaped garden lamp', NULL, NULL, NULL, '50.00', '79.99', 1, 'likenew', 0, 0, '2022-12-28 06:27:44', 4, 22, NULL),
(76, 'cactus', 'decorative cactus ', NULL, NULL, NULL, '2.00', '4.00', 1, 'good', 0, 0, '2022-12-28 06:29:05', 4, 22, NULL),
(77, 'messi football', 'footbal with messi\'s signature', NULL, NULL, NULL, '75.00', '120.00', 1, 'good', 0, 0, '2022-12-28 06:30:08', 6, 16, NULL),
(78, 'homemade shampoo', 'homemade shampoo', NULL, NULL, NULL, '5.00', '10.00', 1, 'brandnew', 0, 0, '2022-12-28 06:31:17', 7, 14, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `product_category_id` int(11) NOT NULL,
  `category_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`product_category_id`, `category_name`) VALUES
(1, 'Books'),
(2, 'Music'),
(3, 'Electronics'),
(4, 'Home and Garden'),
(5, 'Hardware'),
(6, 'Sport and Outdoor'),
(7, 'Health and Beauty'),
(8, 'Jewelry'),
(9, 'Clothing'),
(10, 'Baby'),
(11, 'Antiques'),
(12, 'Movies'),
(13, 'Pottery'),
(14, 'Toys');

-- --------------------------------------------------------

--
-- Table structure for table `shop_user_contact`
--

CREATE TABLE `shop_user_contact` (
  `contact_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `shop_user_contact`
--

INSERT INTO `shop_user_contact` (`contact_id`, `customer_id`, `supplier_id`, `creation_date`) VALUES
(1, 141, NULL, '2022-10-24 15:54:24'),
(2, 142, NULL, '2022-10-25 16:11:06'),
(3, 143, NULL, '2022-10-26 09:16:17'),
(4, 146, NULL, '2022-10-26 14:50:32'),
(5, 147, NULL, '2022-10-26 15:34:15'),
(6, 148, NULL, '2022-10-27 09:20:58'),
(7, NULL, 1, '2022-10-28 13:20:09'),
(8, NULL, 2, '2022-10-28 13:20:09'),
(12, NULL, 5, '2022-11-02 15:07:02'),
(13, NULL, 6, '2022-11-02 15:35:55'),
(14, NULL, 7, '2022-11-02 16:12:25'),
(15, NULL, 8, '2022-11-07 13:06:37'),
(16, NULL, 9, '2022-11-07 15:18:17'),
(17, NULL, 10, '2022-11-07 15:18:54'),
(18, 149, NULL, '2022-11-10 16:23:10'),
(19, NULL, 11, '2022-11-10 16:24:30'),
(20, 150, NULL, '2022-11-11 12:57:41'),
(21, 151, NULL, '2022-11-11 13:22:45'),
(22, NULL, 12, '2022-11-11 15:59:20'),
(23, 152, NULL, '2022-11-14 16:07:51'),
(24, NULL, 13, '2022-11-14 16:09:39'),
(25, 153, NULL, '2022-11-15 12:02:36'),
(26, 154, NULL, '2022-11-16 16:15:06'),
(27, 155, NULL, '2022-11-17 09:05:12'),
(28, NULL, 14, '2022-11-17 09:11:15'),
(29, NULL, 15, '2022-11-17 10:20:14'),
(30, NULL, 16, '2022-11-17 10:21:02'),
(31, 156, NULL, '2022-11-28 12:02:27'),
(32, NULL, 17, '2022-11-28 12:03:42'),
(33, NULL, 18, '2022-11-29 09:20:45'),
(34, 157, NULL, '2022-11-30 15:11:11'),
(35, 158, NULL, '2022-11-30 16:00:18'),
(36, 159, NULL, '2022-11-30 16:45:43'),
(37, NULL, 19, '2022-11-30 16:47:39'),
(38, 160, NULL, '2022-12-02 11:30:24'),
(39, 161, NULL, '2022-12-02 12:05:54'),
(40, NULL, 20, '2022-12-02 12:09:14'),
(41, 162, NULL, '2022-12-02 13:49:40'),
(42, 163, NULL, '2022-12-02 14:25:03'),
(44, 164, NULL, '2022-12-02 14:46:09'),
(45, NULL, 22, '2022-12-02 14:49:54'),
(46, 165, NULL, '2022-12-02 15:40:19'),
(47, NULL, 23, '2022-12-02 15:41:52'),
(48, 166, NULL, '2022-12-16 15:52:07'),
(49, 167, NULL, '2022-12-16 16:07:29'),
(51, 168, NULL, '2022-12-21 06:18:25'),
(52, NULL, 25, '2022-12-21 06:20:12'),
(53, 169, NULL, '2022-12-21 06:47:30'),
(54, NULL, 26, '2022-12-21 06:48:11'),
(55, 170, NULL, '2022-12-22 06:17:23'),
(56, NULL, 27, '2022-12-22 06:20:31'),
(57, 171, NULL, '2022-12-22 07:41:51'),
(58, 172, NULL, '2022-12-27 07:44:21'),
(59, NULL, 28, '2022-12-27 07:45:38');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supplier_id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `vat_number` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_business` tinyint(1) NOT NULL DEFAULT 0,
  `creation_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplier_id`, `name`, `vat_number`, `is_business`, `creation_date`) VALUES
(1, 'corpa company ltd', 'qwer234', 1, '2022-10-25 16:58:16'),
(2, 'john oslorgh', '-', 0, '2022-10-25 16:58:16'),
(5, 'supplier-4-good', '-', 0, '2022-11-02 15:07:01'),
(6, 'masasupplier', '-', 0, '2022-11-02 15:35:53'),
(7, 'giggiocom', '8888', 1, '2022-11-02 16:12:24'),
(8, 'sarv', '234234', 1, '2022-11-07 13:06:36'),
(9, 'topo seller', 'ts0985', 1, '2022-11-07 15:18:14'),
(10, 'bitran goods', '67543454', 1, '2022-11-07 15:18:53'),
(11, 'giggio corporation', '234w', 1, '2022-11-10 16:24:29'),
(12, 'supplino', '89456456', 1, '2022-11-11 15:59:19'),
(13, 'magicgoods inc', '763453245', 1, '2022-11-14 16:09:38'),
(14, 'ozzy osbourne', '234324', 1, '2022-11-17 09:11:14'),
(15, 'tom hogan', '34324', 1, '2022-11-17 10:20:13'),
(16, 'first always supplier', '-', 0, '2022-11-17 10:21:00'),
(17, 'kamal soranos', '-', 0, '2022-11-28 12:03:41'),
(18, 'lincoln\'s goods', 'ht6', 1, '2022-11-29 09:20:43'),
(19, 'koalabear inc', 'ewrewr', 1, '2022-11-30 16:47:38'),
(20, 'tiger goods inc', '-', 0, '2022-12-02 12:09:13'),
(22, 'kamal samansah ', '-', 0, '2022-12-02 14:49:52'),
(23, 'kamel icelmani', '-', 0, '2022-12-02 15:41:51'),
(25, 'pokemon inc', 'y6787', 1, '2022-12-21 06:20:11'),
(26, 'slayers inc', '-', 0, '2022-12-21 06:48:10'),
(27, 'dakota goods', '90678', 1, '2022-12-22 06:20:29'),
(28, 'basc goods', '34er34', 1, '2022-12-27 07:45:37');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`contact_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoice_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `product_category_id` (`product_category_id`),
  ADD KEY `supplier_ibfk_1` (`supplier_id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`product_category_id`);

--
-- Indexes for table `shop_user_contact`
--
ALTER TABLE `shop_user_contact`
  ADD KEY `contact_id` (`contact_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `supplier_id` (`supplier_id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplier_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `contact_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=173;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `product_category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supplier_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`product_category_id`),
  ADD CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`);

--
-- Constraints for table `shop_user_contact`
--
ALTER TABLE `shop_user_contact`
  ADD CONSTRAINT `shop_user_contact_ibfk_1` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`contact_id`),
  ADD CONSTRAINT `shop_user_contact_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `shop_user_contact_ibfk_3` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
