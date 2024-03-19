--
-- DB: `simpleblogdb`
--

-- --------------------------------------------------------

--
-- Table `blogentries`
--

CREATE TABLE IF NOT EXISTS `blogentries` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contents` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
