-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2020 at 09:15 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paymentdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `paymentdetails`
--

CREATE TABLE `paymentdetails` (
  `PaymentID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `CardNo` varchar(50) NOT NULL,
  `Cvc` int(11) NOT NULL,
  `Exp` varchar(50) NOT NULL,
  `AppointmentNo` int(11) NOT NULL,
  `Amount` float NOT NULL,
  `PayDate` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paymentdetails`
--

INSERT INTO `paymentdetails` (`PaymentID`, `Name`, `CardNo`, `Cvc`, `Exp`, `AppointmentNo`, `Amount`, `PayDate`, `Email`) VALUES
(1018, 'Chanuka Dilusha Athalage', '2345123445567', 232, '09/23', 3, 3000, '2020-05-07', 'chanukaathalage@gmail.com'),
(1022, 'Chanuka Athalage', '3453453456788765', 345, '7/22', 15, 3000, '2020-05-14', 'chanukadilusha@gmail.com'),
(1023, 'Chanuka Athalage', '1234432112344321', 123, '9/23', 23, 3000, '2020-05-13', 'chanukadilusha@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `paymentdetails`
--
ALTER TABLE `paymentdetails`
  ADD PRIMARY KEY (`PaymentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `paymentdetails`
--
ALTER TABLE `paymentdetails`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1024;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
