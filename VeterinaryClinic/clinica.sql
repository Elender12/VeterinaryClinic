SET GLOBAL time_zone = '-1:00';
CREATE SCHEMA `clinica` ;
use clinica;
CREATE TABLE `clinica`.`clients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_document` varchar(15),
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `phone` VARCHAR(14) NULL,
  `zipcode` int  NULL,
  `city` varchar(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY unique_document (id_document)
  );

CREATE TABLE `clinica`.`patients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `owner_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `weight` float NULL,
  `age` int not NULL,
  `type` varchar(45) not null,
  `breed` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
   CONSTRAINT `owner_id`
    FOREIGN KEY (`owner_id`)
    REFERENCES `clinica`.`clients` (`id`)
    ON DELETE cascade
    ON UPDATE cascade
  );

CREATE TABLE `clinica`.`treatments` (
  `id` INT NOT NULL auto_increment,
  `description` VARCHAR(150) NOT NULL,
  `price` float NULL,
  `isVaccine` boolean NULL,
   PRIMARY KEY (`id`)
 );
 
 CREATE TABLE  `clinica`.`applied_treatments` (
 `id_treatment` int not null,
 `id_patient` int not null,
 `treatment_date` date not null,
  PRIMARY KEY (`id_treatment`, `id_patient`, `treatment_date`),
  CONSTRAINT `id_treatment`
    FOREIGN KEY (`id_treatment`)
    REFERENCES `clinica`.`treatments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `clinica`.`patients` (`id`)
    ON DELETE cascade
    ON UPDATE cascade); 

/* insert client data */
INSERT INTO clients (id_document, name, surname, address, phone, zipcode, city) values ('74859612X','Elena','El', 'C/Mayor, 3','654127896', '50171', 'La Puebla');
INSERT INTO clients ( id_document, name, surname, address, phone, zipcode, city) values ('3692541E','Denis','Pérez', 'C/Alba, 23','654178956', '50005', 'Zaragoza');
INSERT INTO clients (id_document, name, surname, address, phone, zipcode, city) values ('65581236R','María','Gonzalez', 'C/Grande, 3','654108896', '50100', 'Cuarte de Huerva');
INSERT INTO clients (id_document, name, surname, address, phone, zipcode, city) values ('147236695V','Jennifer','Lopez', 'C/Alvar, 4','624122896', '50110', 'Utebo');
INSERT INTO clients (id_document, name, surname, address, phone, zipcode, city) values ('74120014F','Mercedes','Gómez', 'C/Mansion, 53','674120896', '50102', 'Zaragoza');
INSERT INTO clients (id_document, name, surname, address, phone, zipcode, city) values ('03247895Z','Lorena','Ayuso', 'Avenida Principal, 67','694121896', '50111', 'María de Huerva');
INSERT INTO clients (id_document, name, surname, address, phone, zipcode, city) values ('66587412Q','Fabian','Pablo', 'C/Constitucion, 11','634167896', '50001', 'Zaragoza');

/*insert patient data*/
INSERT INTO patients (owner_id, name, weight, age, breed, type) values (1, 'Dante', 28, 2, 'Staffordshire', 'Canine');
INSERT INTO patients (owner_id, name, weight, age, breed, type) values (1, 'Mish', 8, 1.4, 'Chartreux', 'Feline');
INSERT INTO patients (owner_id, name, weight, age, breed, type) values (2, 'Rocko', 0.3, 1, 'Budgerigar', 'Bird');
INSERT INTO patients (owner_id, name, weight, age, breed, type) values (3, 'Puppy', 3, 2, 'Beagle', 'Canine');
INSERT INTO patients (owner_id, name, weight, age, breed, type) values (4, 'Max', 2.5, 1.4, 'European Cat', 'Feline');
INSERT INTO patients (owner_id, name, weight, age, breed, type) values (5, 'Pitt', 20, 7.4, 'Labrador', 'Canine');
INSERT INTO patients (owner_id, name, weight, age, breed, type) values (6, 'Blue', 2.5, 1.4, 'Macaw', 'Bird');
INSERT INTO patients (owner_id, name, weight, age, breed, type) values (7, 'Tom', 6, 5, 'Bengala', 'Feline');

/*insert treatment data */
INSERT INTO treatments (description, price, isVaccine) values ('Sterilization for adult', 45, 0);
INSERT INTO treatments (description, price, isVaccine) values ('Type A Vaccine', 100, 1);
INSERT INTO treatments (description, price, isVaccine) values ('Type B Vaccine', 100, 1);
INSERT INTO treatments (description, price, isVaccine) values ('Heal wound', 80, 0);
INSERT INTO treatments (description, price, isVaccine) values ('Treat broken paw', 80, 0);
INSERT INTO treatments (description, price, isVaccine) values ('General medication', 50, 0);
INSERT INTO treatments (description, price, isVaccine) values ('Standard checkup', 200, 0);

/*insert applied treatments*/
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (1, 3, '2020-3-15' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (7, 3, '2020-6-10' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (6, 6, '2020-3-28' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (5, 7, '2020-4-01' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (2, 7, '2020-11-23' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (3, 7, '2020-10-01' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (3, 5, '2021-01-02' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (4, 1, '2021-01-03' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (4, 7, '2021-01-04' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (2, 2, '2021-01-04' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (5, 1, '2021-01-04' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (6, 4, '2021-01-04' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (7, 6, '2021-01-04' );
INSERT INTO applied_treatments (id_treatment, id_patient, treatment_date) values (1, 3, '2021-01-04' );
