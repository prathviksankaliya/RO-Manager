-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jan 09, 2024 at 08:34 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `romanager`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer_details`
--

CREATE TABLE `customer_details` (
  `cust_id` int(11) NOT NULL,
  `plant_id` int(11) NOT NULL,
  `cust_name` varchar(255) NOT NULL,
  `cust_phone` varchar(11) DEFAULT NULL,
  `cust_address` varchar(255) NOT NULL,
  `cust_date` varchar(255) NOT NULL,
  `total_balance` int(255) DEFAULT 0,
  `money_status` varchar(255) DEFAULT 'DUE',
  `cust_msg` varchar(255) DEFAULT NULL,
  `cust_status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_details`
--

INSERT INTO `customer_details` (`cust_id`, `plant_id`, `cust_name`, `cust_phone`, `cust_address`, `cust_date`, `total_balance`, `money_status`, `cust_msg`, `cust_status`) VALUES
(2, 1, 'Aryan sankaliya ', '', 'bhavnagar', '09 Jan, 2024', 0, 'DUE', 'Added on 09 Jan, 2024', 1),
(3, 1, 'prathvik Sankaliya ', '', 'limbdi', '09 Jan, 2024', -595, 'DUE', '₹50 is debited on 09 Jan 2024-13:00 pm', 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer_transactions`
--

CREATE TABLE `customer_transactions` (
  `cust_tra_id` int(11) NOT NULL,
  `cust_id` int(255) NOT NULL,
  `plant_id` int(11) NOT NULL,
  `debit` int(255) DEFAULT NULL,
  `credit` int(255) DEFAULT NULL,
  `total` int(255) DEFAULT NULL,
  `jag` int(255) DEFAULT 0,
  `bottle` int(255) DEFAULT 0,
  `note` varchar(255) DEFAULT NULL,
  `cust_tra_date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_transactions`
--

INSERT INTO `customer_transactions` (`cust_tra_id`, `cust_id`, `plant_id`, `debit`, `credit`, `total`, `jag`, `bottle`, `note`, `cust_tra_date`) VALUES
(1, 3, 1, 100, NULL, -100, 4, 0, '', '09 Jan 2024-12:54 pm'),
(2, 3, 1, 100, NULL, -200, 0, 5, '', '09 Jan 2024-12:55 pm'),
(3, 3, 1, 220, NULL, -420, 4, 6, '', '09 Jan 2024-12:57 pm'),
(4, 3, 1, 125, NULL, -545, 5, 0, '', '09 Jan 2024-12:59 pm'),
(5, 3, 1, 50, NULL, -595, 2, 0, '', '09 Jan 2024-13:00 pm');

--
-- Triggers `customer_transactions`
--
DELIMITER $$
CREATE TRIGGER `after_customer_transaction_insert` AFTER INSERT ON `customer_transactions` FOR EACH ROW BEGIN
    -- Update total_balance based on credit or debit
    UPDATE customer_details
    SET total_balance = total_balance + IFNULL(NEW.credit, 0) - IFNULL(NEW.debit, 0),
        money_status = CASE
            WHEN total_balance + IFNULL(NEW.credit, 0) - IFNULL(NEW.debit, 0) > 0 THEN 'ADVANCE'
            ELSE 'DUE'
        END,
        cust_msg = CASE
            WHEN NEW.credit IS NOT NULL THEN CONCAT('₹', NEW.credit, ' is credited on ', NEW.cust_tra_date)
            WHEN NEW.debit IS NOT NULL THEN CONCAT('₹', NEW.debit, ' is debited on ', NEW.cust_tra_date)
            ELSE NULL
        END
    WHERE cust_id = NEW.cust_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `plant_details`
--

CREATE TABLE `plant_details` (
  `plant_id` int(11) NOT NULL,
  `auth_id` varchar(255) NOT NULL,
  `plant_name` varchar(255) NOT NULL,
  `plant_phone` varchar(13) NOT NULL,
  `plant_email` varchar(30) DEFAULT NULL,
  `plant_image` varchar(255) NOT NULL,
  `plant_city` varchar(50) NOT NULL,
  `plant_address` varchar(255) NOT NULL,
  `jag_price` int(255) DEFAULT 25,
  `bottle_price` int(255) DEFAULT 20,
  `plant_security` varchar(255) DEFAULT NULL,
  `plant_status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `plant_details`
--

INSERT INTO `plant_details` (`plant_id`, `auth_id`, `plant_name`, `plant_phone`, `plant_email`, `plant_image`, `plant_city`, `plant_address`, `jag_price`, `bottle_price`, `plant_security`, `plant_status`) VALUES
(1, 'pvEmL0RTjHhqwDpPOBmEQk9MmaD2', 'Ashapura Water', '9723816724', '', 'images/plants/IMG_1704779777.png', 'limbdi', 'surendranagar limbdi', 25, 20, '', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer_details`
--
ALTER TABLE `customer_details`
  ADD PRIMARY KEY (`cust_id`),
  ADD KEY `plant_id_fk` (`plant_id`);

--
-- Indexes for table `customer_transactions`
--
ALTER TABLE `customer_transactions`
  ADD PRIMARY KEY (`cust_tra_id`),
  ADD KEY `cust_id_fk` (`cust_id`),
  ADD KEY `plant_id_tra_fk` (`plant_id`);

--
-- Indexes for table `plant_details`
--
ALTER TABLE `plant_details`
  ADD PRIMARY KEY (`plant_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer_details`
--
ALTER TABLE `customer_details`
  MODIFY `cust_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customer_transactions`
--
ALTER TABLE `customer_transactions`
  MODIFY `cust_tra_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `plant_details`
--
ALTER TABLE `plant_details`
  MODIFY `plant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_details`
--
ALTER TABLE `customer_details`
  ADD CONSTRAINT `plant_id_fk` FOREIGN KEY (`plant_id`) REFERENCES `plant_details` (`plant_id`);

--
-- Constraints for table `customer_transactions`
--
ALTER TABLE `customer_transactions`
  ADD CONSTRAINT `cust_id_fk` FOREIGN KEY (`cust_id`) REFERENCES `customer_details` (`cust_id`),
  ADD CONSTRAINT `plant_id_tra_fk` FOREIGN KEY (`plant_id`) REFERENCES `plant_details` (`plant_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
