-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2025 at 02:59 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uas_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `category_package`
--

CREATE TABLE `category_package` (
  `category` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category_package`
--

INSERT INTO `category_package` (`category`) VALUES
('Fast'),
('Regular'),
('Super Fast');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `password`, `email`, `name`, `address`, `phone`) VALUES
(1, '123', 'cristian@gmail.com', 'Aloy', 'Bandung', '0891');

-- --------------------------------------------------------

--
-- Table structure for table `shipment_detail`
--

CREATE TABLE `shipment_detail` (
  `id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  `status` enum('Pending','Transit','Delivered') NOT NULL,
  `current_position` varchar(30) NOT NULL,
  `evidence` varchar(30) NOT NULL,
  `date` datetime NOT NULL,
  `updated_by` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `shipment_detail`
--

INSERT INTO `shipment_detail` (`id`, `transaction_id`, `status`, `current_position`, `evidence`, `date`, `updated_by`) VALUES
(1, 1, 'Pending', 'Jakarta', 'Photos\\Tokopaedi.jpg', '2025-01-09 00:00:00', 'Aloy'),
(2, 2, 'Delivered', 'Ciwidey', 'Photos\\Tokopaedi.jpg', '2025-01-09 00:00:00', 'Aloy');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `package_type` varchar(30) NOT NULL,
  `package_weight` decimal(10,2) NOT NULL,
  `total_cost` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `receipt_name` varchar(30) NOT NULL,
  `receipt_address` varchar(30) NOT NULL,
  `receipt_phone` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `customer_id`, `package_type`, `package_weight`, `total_cost`, `created_at`, `receipt_name`, `receipt_address`, `receipt_phone`) VALUES
(1, 1, 'Super Fast', 0.20, 1, '2025-01-09 00:00:00', 'Juan', 'Ciwidey', '0892'),
(2, 1, 'Regular', 3.70, 4, '2025-01-09 00:00:00', 'Kezia', 'Maleber', '0893');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category_package`
--
ALTER TABLE `category_package`
  ADD PRIMARY KEY (`category`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shipment_detail`
--
ALTER TABLE `shipment_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaction_id` (`transaction_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `fk_package_type` (`package_type`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shipment_detail`
--
ALTER TABLE `shipment_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `shipment_detail`
--
ALTER TABLE `shipment_detail`
  ADD CONSTRAINT `shipment_detail_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `fk_package_type` FOREIGN KEY (`package_type`) REFERENCES `category_package` (`category`),
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
