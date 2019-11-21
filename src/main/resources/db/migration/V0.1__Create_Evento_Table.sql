CREATE TABLE IF NOT EXISTS `hievents`.`evento` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NOT NULL,
	`endereco`VARCHAR(255) NOT NULL,
	`data`DATE NOT NULL,
	`horario`VARCHAR(100) NOT NULL,
	`anunciante_id` INT NOT NULL,
	PRIMARY KEY (`id`),
INDEX `fk_pending_anunciante_idx1` (`anunciante_id` ASC),
 CONSTRAINT `fk_anunciante_idx1`
   FOREIGN KEY (`anunciante_id`)
   REFERENCES `hievents`.`anunciante` (`id`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION)  
 ENGINE=InnoDB;