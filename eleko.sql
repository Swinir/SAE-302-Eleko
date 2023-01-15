-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : dim. 15 jan. 2023 à 15:02
-- Version du serveur : 10.10.2-MariaDB
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `eleko`
--

-- --------------------------------------------------------

--
-- Structure de la table `datas`
--

DROP TABLE IF EXISTS `datas`;
CREATE TABLE IF NOT EXISTS `datas` (
  `id_data` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `date_gen` varchar(50) NOT NULL,
  `jour` varchar(50) NOT NULL COMMENT 'jours de la capture',
  `dvalue` tinyint(4) DEFAULT NULL,
  `hours0_data` tinyint(3) NOT NULL COMMENT 'état de l''heure ( 1= normal; 2= moyen; 3=  critique',
  `hours1_data` tinyint(3) NOT NULL,
  `hours2_data` tinyint(3) NOT NULL,
  `hours3_data` tinyint(3) NOT NULL,
  `hours4_data` tinyint(3) NOT NULL,
  `hours5_data` tinyint(3) NOT NULL,
  `hours6_data` tinyint(3) NOT NULL,
  `hours7_data` tinyint(3) NOT NULL,
  `hours8_data` tinyint(3) NOT NULL,
  `hours9_data` tinyint(3) NOT NULL,
  `hours10_data` tinyint(3) NOT NULL,
  `hours11_data` tinyint(3) NOT NULL,
  `hours12_data` tinyint(3) NOT NULL,
  `hours13_data` tinyint(3) NOT NULL,
  `hours15_data` tinyint(3) NOT NULL,
  `hours16_data` tinyint(3) NOT NULL,
  `hours17_data` tinyint(3) NOT NULL,
  `hours18_data` tinyint(3) NOT NULL,
  `hours19_data` tinyint(3) NOT NULL,
  `hours20_data` tinyint(3) NOT NULL,
  `hours21_data` tinyint(3) NOT NULL,
  `hours22_data` tinyint(3) NOT NULL,
  `hours23_data` tinyint(3) NOT NULL,
  `message_data` varchar(50) NOT NULL,
  PRIMARY KEY (`id_data`)
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci COMMENT='import api';
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
