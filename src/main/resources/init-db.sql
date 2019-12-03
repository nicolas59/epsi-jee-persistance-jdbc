CREATE TABLE `marque` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ;


CREATE TABLE `produit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(100) NOT NULL,
  `description` text,
  `prix` double NOT NULL,
  `marque_id` bigint(20) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `produit_marque_FK` FOREIGN KEY (`marque_id`) REFERENCES `marque` (`id`)
) ;

CREATE TABLE `commande` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;


CREATE TABLE `comm_produit` (
  `commande_id` bigint(20) NOT NULL,
  `produit_id` bigint(20) NOT NULL,
  `quantite` bigint(20) NOT NULL,
  PRIMARY KEY (`commande_id`,`produit_id`),
  CONSTRAINT `commande_FK` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`id`),
  CONSTRAINT `produit_FK` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`)
);


