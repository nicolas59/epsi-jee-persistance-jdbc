CREATE TABLE marque (
  id SERIAL,
  libelle varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ;


CREATE TABLE produit (
  id SERIAL,
  libelle varchar(100) NOT NULL,
  description text,
  prix NUMERIC NOT NULL,
  marque_id bigint DEFAULT NULL,
  image varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT produit_marque_FK FOREIGN KEY (marque_id) REFERENCES marque (id)
) ;

CREATE TABLE commande (
  id SERIAL,
  date_creation timestamp DEFAULT NULL,
  PRIMARY KEY (id)
) ;


CREATE TABLE comm_produit (
  commande_id bigint NOT NULL,
  produit_id bigint NOT NULL,
  quantite bigint NOT NULL,
  PRIMARY KEY (commande_id,produit_id),
  CONSTRAINT commande_FK FOREIGN KEY (commande_id) REFERENCES commande (id),
  CONSTRAINT produit_FK FOREIGN KEY (produit_id) REFERENCES produit (id)
);

alter sequence marque_id_seq restart with 100;
alter sequence produit_id_seq restart with 100;
alter sequence commande_id_seq restart with 100;

