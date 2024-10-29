-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.27-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para shutdawndb
CREATE DATABASE IF NOT EXISTS `shutdawndb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `shutdawndb`;

-- Copiando estrutura para tabela shutdawndb.acesso
CREATE TABLE IF NOT EXISTS `acesso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela shutdawndb.acesso: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela shutdawndb.desenvolvedor
CREATE TABLE IF NOT EXISTS `desenvolvedor` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `squadId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `squadId` (`squadId`),
  CONSTRAINT `desenvolvedor_ibfk_1` FOREIGN KEY (`squadId`) REFERENCES `squad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela shutdawndb.desenvolvedor: ~6 rows (aproximadamente)
REPLACE INTO `desenvolvedor` (`id`, `nome`, `phone`, `squadId`) VALUES
	(2, 'Jão', '999221478', 1),
	(3, 'Armenio', '999221478', 1),
	(4, 'Armenio', '999221478', 1),
	(5, 'Armenio', '999221478', 1),
	(6, 'Armenio', '999221478', 1),
	(7, 'ovos', '999221478', 1);

-- Copiando estrutura para tabela shutdawndb.papel
CREATE TABLE IF NOT EXISTS `papel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela shutdawndb.papel: ~1 rows (aproximadamente)
REPLACE INTO `papel` (`id`, `nome`) VALUES
	(1, 'admin');

-- Copiando estrutura para tabela shutdawndb.squad
CREATE TABLE IF NOT EXISTS `squad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela shutdawndb.squad: ~2 rows (aproximadamente)
REPLACE INTO `squad` (`id`, `nome`, `descricao`) VALUES
	(1, 'jonasponas', NULL),
	(2, 'Armenio', NULL);

-- Copiando estrutura para tabela shutdawndb.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela shutdawndb.status: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela shutdawndb.tarefa
CREATE TABLE IF NOT EXISTS `tarefa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desenvolvedor_id` int(11) DEFAULT NULL,
  `tipo_tarefa_id` int(11) DEFAULT NULL,
  `titulo` varchar(255) NOT NULL,
  `descricao` text DEFAULT NULL,
  `prioridade` varchar(50) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `data_inicio` date DEFAULT curdate(),
  `data_fim` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_desenvolvedor` (`desenvolvedor_id`),
  KEY `fk_tipo_tarefa` (`tipo_tarefa_id`),
  KEY `fk_status` (`status_id`),
  CONSTRAINT `fk_desenvolvedor` FOREIGN KEY (`desenvolvedor_id`) REFERENCES `desenvolvedor` (`id`),
  CONSTRAINT `fk_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_tipo_tarefa` FOREIGN KEY (`tipo_tarefa_id`) REFERENCES `tipo_tarefa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela shutdawndb.tarefa: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela shutdawndb.tipo_tarefa
CREATE TABLE IF NOT EXISTS `tipo_tarefa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `descricao` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela shutdawndb.tipo_tarefa: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela shutdawndb.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `dev_id` int(11) DEFAULT NULL,
  `papel_id` int(11) DEFAULT NULL,
  `acesso_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_acesso_id` (`acesso_id`),
  CONSTRAINT `fk_acesso_id` FOREIGN KEY (`acesso_id`) REFERENCES `acesso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela shutdawndb.usuario: ~3 rows (aproximadamente)
REPLACE INTO `usuario` (`id`, `email`, `senha`, `dev_id`, `papel_id`, `acesso_id`) VALUES
	(1, 'admin@gmail.com', '1234', NULL, 1, NULL),
	(4, 'outroUsu@gmail.com', '6789', 2, 1, NULL),
	(5, 'NicolasSezerino@gmail', '420', 3, 1, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
