-- phpMyAdmin SQL Dump
-- version 4.9.6
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 09 nov. 2021 à 22:56
-- Version du serveur :  10.1.48-MariaDB-0+deb9u2
-- Version de PHP : 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `groupe01`
--

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `idEvent` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `desactiver` tinyint(1) NOT NULL,
  `nbParticipantsTotal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `events`
--

INSERT INTO `events` (`idEvent`, `date`, `name`, `desactiver`, `nbParticipantsTotal`) VALUES
(1, '2021-12-24', 'Noël', 0, 14),
(2, '2022-03-28', 'Anniversaire', 0, 2),
(3, '2022-04-14', 'Pot de départ', 1, 6);

-- --------------------------------------------------------

--
-- Structure de la table `participants`
--

CREATE TABLE `participants` (
  `idParticipant` int(11) NOT NULL,
  `idEvent` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `convives` int(11) NOT NULL,
  `entree` varchar(255) NOT NULL,
  `partEntree` int(11) NOT NULL,
  `plat` varchar(255) NOT NULL,
  `partPlat` int(11) NOT NULL,
  `dessert` varchar(255) NOT NULL,
  `partDessert` int(11) NOT NULL,
  `boisson` varchar(255) NOT NULL,
  `partBoisson` int(11) NOT NULL,
  `commentaire` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `participants`
--

INSERT INTO `participants` (`idParticipant`, `idEvent`, `idUser`, `convives`, `entree`, `partEntree`, `plat`, `partPlat`, `dessert`, `partDessert`, `boisson`, `partBoisson`, `commentaire`) VALUES
(1, 1, 2, 1, 'Toast', 1, 'Magret de cannard', 2, 'Buche', 1, 'N/A', 0, 'N/A'),
(2, 1, 10, 2, 'Foie Gras', 2, 'Patate', 1, 'N/A', 0, 'Soda', 2, 'Gourmand'),
(3, 2, 10, 1, 'N/A', 0, 'Gateau d\'anniversaire', 5, 'Glace', 5, 'N/A', 0, 'N/A'),
(4, 2, 4, 3, 'Toast', 5, 'N/A', 0, 'N/A', 0, 'Oasis', 5, 'N/A'),
(5, 1, 3, 5, 'N/A', 0, 'Dinde & Patate', 3, 'N/A', 0, 'Eau', 5, 'Vivement'),
(6, 1, 7, 2, 'Amuse bouche avocat & crevette', 2, 'Pintade rôtie', 2, 'Sablés à la vanille', 2, 'N/A', 0, 'N/A'),
(7, 2, 8, 5, 'Choux à la crème ', 4, 'N/A', 0, 'Tarte au pomme', 4, 'N/A', 0, 'N/A'),
(9, 3, 2, 5, 'Carotte Rapée', 5, 'Raclette + charcuterie', 5, 'N/A', 0, 'Champagne', 2, 'Hey ! ');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `idUser` int(11) NOT NULL,
  `alias` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`idUser`, `alias`, `password`, `contact`) VALUES
(1, 'Admin', '$51$16$9kyMUh6xFE0OVBIzOgiLNe-bZ1hAZFHaaXhlDOh6bdE', 'admin@gmail.com'),
(2, 'Alan', '$51$16$kg6BrnSlZV8cOjcq90CUU0bVPX2dVGalz3cyjwyIwEE', '0505055'),
(3, 'Farid', '$51$16$NQmHltZ_d9-AFol9L3Hz-oI8sib32llnA_i6JWspQbc', 'farid@mail.com'),
(4, 'Patrick', '$51$16$OShixuk5hEkKn7ZGMCFJ5_AX5FfgZYbUKK7x72-xDGM', 'patrick@mail.fr'),
(5, 'Mathieu', '$51$16$3r2ZK-kJrjL1fLZxlp8j54dCviMLJOT-hF9l64TaVxs', '065345124'),
(6, 'Camille', '$51$16$v_-tyPqbNOqXIW70X1PVO49nZTgMs0j4olyIAYHLlcg', 'camille@mail.eu'),
(7, 'John', '$51$16$RiGh0iGI29FbNH04TtTLxdtzqEe-fihyn20b0SJYrog', '044044444'),
(8, 'Eva', '$51$16$GnJe9J-7qIIq8EfrAEQC6sxBrzwWXHZsc6cYN1hp6do', 'eva@gmail.fr'),
(9, 'Vincent', '$51$16$5yGoRChMFpnsKy16kFaVGePRwhvbbUbLBhecMJ-OYAI', '011234567'),
(10, 'Lucien', '$51$16$w-Rm36fyLP5ddI4_mrncQkpp-d-lEksRKnP_Tl97xnY', 'lulu@lucien.mail');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`idEvent`);

--
-- Index pour la table `participants`
--
ALTER TABLE `participants`
  ADD PRIMARY KEY (`idParticipant`),
  ADD KEY `idEvents` (`idEvent`),
  ADD KEY `idUsers` (`idUser`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `unique_index` (`alias`(150),`contact`(150)) USING BTREE;

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `idEvent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `participants`
--
ALTER TABLE `participants`
  MODIFY `idParticipant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `participants`
--
ALTER TABLE `participants`
  ADD CONSTRAINT `participants_ibfk_1` FOREIGN KEY (`idEvent`) REFERENCES `events` (`idEvent`),
  ADD CONSTRAINT `participants_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
