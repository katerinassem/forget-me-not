CREATE DATABASE youcontact
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

CREATE  TABLE `youcontact`.`contact` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `first_name` VARCHAR(30) NOT NULL,
  `second_name` VARCHAR(30) NOT NULL,
  `name_by_father` VARCHAR(30) NULL,
  `date_of_birth` DATE NULL,
  `sex` ENUM('m', 'f') NULL,
  `sitizenship` VARCHAR(40) NULL,
  `web_site` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `company` VARCHAR(50) NULL,
  `photo_url` VARCHAR(100) NULL,
  `address_id` INT(10) UNSIGNED NULL,     
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE  TABLE `youcontact`.`address` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `country` VARCHAR(40) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `street` VARCHAR(100) NULL,
  `building` INT(10) UNSIGNED NULL,
  `apartment` INT(10) UNSIGNED NULL,
  `post_index` BIGINT(20) UNSIGNED NULL,
  `contact_id` INT(10) UNSIGNED NOT NULL,
  FOREIGN KEY (`contact_id`)
  REFERENCES `youcontact`.`contact` (`id`)
  ON UPDATE NO ACTION
  ON DELETE CASCADE,    
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `youcontact`.`contact`
ADD
FOREIGN KEY (`address_id`)
  REFERENCES `youcontact`.`address` (`id`)
  ON DELETE SET NULL;

CREATE  TABLE `youcontact`.`attachment` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `file_name` VARCHAR(100) NOT NULL,
  `unique_name` VARCHAR(200) NOT NULL,
  `upload_date` DATETIME NOT NULL,  
  `comment` VARCHAR(200) NULL,
  `contact_id` INT(10) UNSIGNED NOT NULL,
  FOREIGN KEY (`contact_id`)
  REFERENCES `youcontact`.`contact` (`id`)
  ON UPDATE NO ACTION
  ON DELETE CASCADE,    
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE  TABLE `youcontact`.`telephone` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `country_code` TINYINT UNSIGNED NOT NULL,
  `operator_code` TINYINT UNSIGNED NOT NULL,
  `telephone_number` BIGINT UNSIGNED NOT NULL,  
  `telephone_type` ENUM('h', 'm') NOT NULL,
  `comment` VARCHAR(200) NULL,
  `contact_id` INT(10) UNSIGNED NOT NULL,
  FOREIGN KEY (`contact_id`)
  REFERENCES `youcontact`.`contact` (`id`)
  ON UPDATE NO ACTION
  ON DELETE CASCADE,    
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `youcontact`.`contact` (`first_name`, `second_name`, `name_by_father`, `date_of_birth`, `sex`, `sitizenship`, `email`) VALUES ('Katerina', 'Semikina', 'Sergeevna', '1994-12-18', 'f', 'belarus', 'katarina.sem@mail.ru');

INSERT INTO `youcontact`.`contact` (`first_name`, `second_name`, `name_by_father`, `date_of_birth`, `sex`, `sitizenship`, `email`) VALUES ('Daria', 'Semikina', 'Sergeevna', '1996-04-08', 'f', 'belarus', 'dashulya_plyus@mail.ru');

INSERT INTO `youcontact`.`contact` (`first_name`, `second_name`, `name_by_father`, `date_of_birth`, `sex`, `sitizenship`, `email`) VALUES ('Timofey', 'Markusenko', 'Sergeevich', '1995-02-04', 'm ', 'belarus', 'tim.marcusenko@gmail.com');
