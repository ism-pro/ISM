USE ISM;


#####
### point_infos
### Contain oint information
####################################################################
/*
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS point_infos;
CREATE TABLE IF NOT EXISTS point_infos (
	pi_id 				INT 			NOT NULL 	AUTO_INCREMENT,
    pi_company			VARCHAR(45) 	NOT NULL 	,
    pi_group			INT 			NOT NULL 	,
    pi_slideshow 		VARCHAR(45) 	NOT NULL	,
    pi_slide			int				NOT NULL	,
    pi_slidePath		VARCHAR(512)	NOT NULL	,
	pi_description		int 			NOT NULL	,
    pi_staff	 		VARCHAR(45) 	NULL 		,
    pi_slideDuration	int				NOT NULL	DEFAULT 3000,
    pi_slideShowDuration	int			NOT NULL	DEFAULT 3000,
	pi_enabled			BIT 			NOT NULL 	DEFAULT 1 	,
    pi_lockOnStaff		BIT				NOT NULL	DEFAULT 1	,
	pi_deleted			BIT 			NOT NULL 	DEFAULT 0 	,
	pi_created			DATETIME 		NOT NULL	DEFAULT 0	,
	pi_changed			TIMESTAMP 		NOT NULL 	DEFAULT CURRENT_TIMESTAMP 
													ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (pi_id),
	UNIQUE INDEX ui_pi (pi_id ASC, pi_group ASC),
    INDEX i_slideshow(pi_slideshow ASC),
	CONSTRAINT pi_staff
		FOREIGN KEY (pi_staff) REFERENCES staff (st_staff)
			ON DELETE RESTRICT
			ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
SET FOREIGN_KEY_CHECKS=1;
*/


/*
#####
### mailsender
### Information d'application pour envoie d'eamil
####################################################################
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS MailSender;
CREATE TABLE IF NOT EXISTS MailSender (
	ms_id 				INT 			NOT NULL 	AUTO_INCREMENT,
    ms_company			VARCHAR(45) 	NOT NULL 	,
    ms_address			VARCHAR(45) 	NOT NULL	,
    ms_smtpsrv 			VARCHAR(45) 	NOT NULL	,
    ms_port				int				NOT NULL	DEFAULT 25,
    ms_ssl				BIT 						DEFAULT 0, 	
	ms_requiresAuth		BIT 						DEFAULT 0,
    ms_username			VARCHAR(45)		NULL		,
    ms_password			VARCHAR(256)	NULL		,
    am_enabled	 		BIT 						DEFAULT 0,
    ms_deleted			BIT 			NOT NULL 	DEFAULT 0,
	ms_created			DATETIME 		NOT NULL	DEFAULT 0,
	ms_changed			TIMESTAMP 		NOT NULL 	DEFAULT CURRENT_TIMESTAMP 
													ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (ms_id),
	INDEX i_address(ms_address ASC),
	CONSTRAINT ms_company
		FOREIGN KEY (ms_company) REFERENCES company (c_company)
			ON DELETE RESTRICT
			ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
SET FOREIGN_KEY_CHECKS=1;

ALTER TABLE `ism`.`staff` 
ADD COLUMN `st_maillist` VARCHAR(256) NULL DEFAULT NULL AFTER `st_borned`;
*/









#####
### process_access
### Gestion des accès sur la documentation
### A partir de la version 1706.20
####################################################################
/*
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS process_access;
CREATE TABLE IF NOT EXISTS process_access (
	pa_id 				INT 			NOT NULL 	AUTO_INCREMENT,
    pa_company			VARCHAR(45) 	NOT NULL 	,
    pa_docexplorer	    int				NOT NULL	,
    pa_groupdef			VARCHAR(45) 	NULL	,
    pa_staff 			VARCHAR(45) 	NULL	,
    pa_isgroup			BIT							DEFAULT 0,
    pa_deleted			BIT 			NOT NULL 	DEFAULT 0,
	pa_created			DATETIME 		NOT NULL	DEFAULT 0,
	pa_changed			TIMESTAMP 		NOT NULL 	DEFAULT CURRENT_TIMESTAMP 
													ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (pa_id),
	INDEX ui_cgs_(pa_id asc),
	CONSTRAINT pa_company
		FOREIGN KEY (pa_company) REFERENCES company (c_company) ON DELETE RESTRICT ON UPDATE NO ACTION,
        FOREIGN KEY (pa_docexplorer) REFERENCES doc_explorer (dc_id) ON DELETE RESTRICT ON UPDATE NO ACTION,
        FOREIGN KEY (pa_groupdef) REFERENCES staff_group_def (stgd_group_def) ON DELETE RESTRICT ON UPDATE NO ACTION,
        FOREIGN KEY (pa_staff) REFERENCES staff (st_staff) ON DELETE RESTRICT ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO `ism`.`ism_role` (`role`, `rolename`) VALUES ('S_EXPLORER_CA', 'Create document access');
INSERT INTO `ism`.`ism_role` (`role`, `rolename`) VALUES ('S_EXPLORER_EA', 'Edit document access');
INSERT INTO `ism`.`ism_role` (`role`, `rolename`) VALUES ('S_EXPLORER_LA', 'List documents access');
INSERT INTO `ism`.`ism_role` (`role`, `rolename`) VALUES ('S_EXPLORER_VA', 'View document access');

INSERT INTO `ism`.`staff_group_def_role` (`stgdr_company`, `stgdr_group_def`, `stgdr_role`, `stgdr_activated`, `stgdr_created`) VALUES ('39', 'GOUROU', 'S_EXPLORER_CA', 1, NOW());
INSERT INTO `ism`.`staff_group_def_role` (`stgdr_company`, `stgdr_group_def`, `stgdr_role`, `stgdr_activated`, `stgdr_created`) VALUES ('39', 'GOUROU', 'S_EXPLORER_EA', 1, NOW());
INSERT INTO `ism`.`staff_group_def_role` (`stgdr_company`, `stgdr_group_def`, `stgdr_role`, `stgdr_activated`, `stgdr_created`) VALUES ('39', 'GOUROU', 'S_EXPLORER_LA', 1, NOW());
INSERT INTO `ism`.`staff_group_def_role` (`stgdr_company`, `stgdr_group_def`, `stgdr_role`, `stgdr_activated`, `stgdr_created`) VALUES ('39', 'GOUROU', 'S_EXPLORER_VA', 1, NOW());
*/














