CREATE TABLE IF NOT EXISTS animals
(
    id      INT       NOT NULL AUTO_INCREMENT,
    name    CHAR(255) NOT NULL,
    type_id INT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS types
(
    id           INT       NOT NULL AUTO_INCREMENT,
    name         CHAR(255) NOT NULL,
    approx_count INT       NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO types (name, approx_count)
VALUES ('Porifera or Spongia', 8000);
INSERT INTO types (name, approx_count)
VALUES ('Placozoa', 3);
INSERT INTO types (name, approx_count)
VALUES ('Ctenophora', 150);
INSERT INTO types (name, approx_count)
VALUES ('Cnidaria', 11000);
INSERT INTO types (name, approx_count)
VALUES ('Xenacoelomorpha', 400);
INSERT INTO types (name, approx_count)
VALUES ('Chaetognatha', 120);
INSERT INTO types (name, approx_count)
VALUES ('Dicyemida', 75);
INSERT INTO types (name, approx_count)
VALUES ('Orthonectida', 30);
INSERT INTO types (name, approx_count)
VALUES ('Plathelmintes', 25000);
INSERT INTO types (name, approx_count)
VALUES ('Gastrotricha', 600);
INSERT INTO types (name, approx_count)
VALUES ('Gnathostomulida', 100);
INSERT INTO types (name, approx_count)
VALUES ('Micrognathozo', 1);
INSERT INTO types (name, approx_count)
VALUES ('Acanthocephala', 750);
INSERT INTO types (name, approx_count)
VALUES ('Rotatoria or Rotifera', 1500);
INSERT INTO types (name, approx_count)
VALUES ('Entoprocta', 150);
INSERT INTO types (name, approx_count)
VALUES ('Cycliophora', 3);
INSERT INTO types (name, approx_count)
VALUES ('Bryozoa or Ectoprocta', 5000);
INSERT INTO types (name, approx_count)
VALUES ('Phoronida', 12);
INSERT INTO types (name, approx_count)
VALUES ('Brachiopoda', 280);
INSERT INTO types (name, approx_count)
VALUES ('Nemertina or Nemertini', 1000);
INSERT INTO types (name, approx_count)
VALUES ('Sipuncula', 320);
INSERT INTO types (name, approx_count)
VALUES ('Annelida', 12000);
INSERT INTO types (name, approx_count)
VALUES ('Mollusca', 150000);
INSERT INTO types (name, approx_count)
VALUES ('Scalidophora', 300);
INSERT INTO types (name, approx_count)
VALUES ('Nematoda', 1000000);
INSERT INTO types (name, approx_count)
VALUES ('Nematomorpha or Gordiacea', 320);
INSERT INTO types (name, approx_count)
VALUES ('Tardigrada', 900);
INSERT INTO types (name, approx_count)
VALUES ('Onychophora', 100);
INSERT INTO types (name, approx_count)
VALUES ('Arthropoda', 15000000);
INSERT INTO types (name, approx_count)
VALUES ('Echinodermata', 7000);
INSERT INTO types (name, approx_count)
VALUES ('Hemichordata', 130);
INSERT INTO types (name, approx_count)
VALUES ('Chordata', 51000);
