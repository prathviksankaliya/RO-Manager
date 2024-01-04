-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jan 04, 2024 at 07:19 AM
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
  `cust_name` varchar(255) NOT NULL,
  `cust_phone` varchar(11) DEFAULT NULL,
  `cust_address` varchar(255) NOT NULL,
  `cust_date` varchar(255) NOT NULL,
  `total_balance` int(255) DEFAULT 0,
  `money_status` varchar(255) DEFAULT 'DUE',
  `cust_msg` varchar(255) DEFAULT NULL,
  `cust_status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer_transactions`
--

CREATE TABLE `customer_transactions` (
  `cust_tra_id` int(11) NOT NULL,
  `cust_id` int(255) NOT NULL,
  `debit` int(255) NOT NULL DEFAULT 0,
  `credit` int(255) NOT NULL DEFAULT 0,
  `note` varchar(255) DEFAULT NULL,
  `cust_tra_date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `plant_security` varchar(255) DEFAULT NULL,
  `plant_status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `plant_details`
--

INSERT INTO `plant_details` (`plant_id`, `auth_id`, `plant_name`, `plant_phone`, `plant_email`, `plant_image`, `plant_city`, `plant_address`, `plant_security`, `plant_status`) VALUES
(1, 'pvEmL0RTjHhqwDpPOBmEQk9MmaD2', 'Aashapura Water', '9723816724', '', 'images/plants/IMG_1703660925.png', 'limbdi', 'surendranagar', '', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer_details`
--
ALTER TABLE `customer_details`
  ADD PRIMARY KEY (`cust_id`);

--
-- Indexes for table `customer_transactions`
--
ALTER TABLE `customer_transactions`
  ADD PRIMARY KEY (`cust_tra_id`),
  ADD KEY `cust_id_fk` (`cust_id`);

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
  MODIFY `cust_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer_transactions`
--
ALTER TABLE `customer_transactions`
  MODIFY `cust_tra_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `plant_details`
--
ALTER TABLE `plant_details`
  MODIFY `plant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_transactions`
--
ALTER TABLE `customer_transactions`
  ADD CONSTRAINT `cust_id_fk` FOREIGN KEY (`cust_id`) REFERENCES `customer_details` (`cust_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
