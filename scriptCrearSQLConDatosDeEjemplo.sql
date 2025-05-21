CREATE DATABASE  IF NOT EXISTS `escuela` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `escuela`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: escuela
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administradores`
--

DROP TABLE IF EXISTS `administradores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administradores` (
  `idAdministrador` int NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  KEY `AdminUsuario_idx` (`idUsuario`),
  CONSTRAINT `AdminUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administradores`
--

LOCK TABLES `administradores` WRITE;
/*!40000 ALTER TABLE `administradores` DISABLE KEYS */;
INSERT INTO `administradores` VALUES (1,1);
/*!40000 ALTER TABLE `administradores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calificacion`
--

DROP TABLE IF EXISTS `calificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calificacion` (
  `idCalificacion` int NOT NULL AUTO_INCREMENT,
  `idEvaluacion` int NOT NULL,
  `idEstudiante` int NOT NULL,
  `fechaEntrega` datetime NOT NULL,
  `nota` float DEFAULT NULL,
  PRIMARY KEY (`idCalificacion`),
  KEY `CalificacionEvaluacion_idx` (`idEvaluacion`),
  KEY `CalificacionEstudiante_idx` (`idEstudiante`),
  CONSTRAINT `CalificacionEstudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiantes` (`idEstudiante`),
  CONSTRAINT `CalificacionEvaluacion` FOREIGN KEY (`idEvaluacion`) REFERENCES `evaluaciones` (`idEvaluacion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calificacion`
--

LOCK TABLES `calificacion` WRITE;
/*!40000 ALTER TABLE `calificacion` DISABLE KEYS */;
INSERT INTO `calificacion` VALUES (2,1,1,'2025-05-20 22:31:27',3.4);
/*!40000 ALTER TABLE `calificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `idCurso` int NOT NULL AUTO_INCREMENT,
  `idDepartamento` int DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  `horaInicial` time NOT NULL,
  `horaFinal` time NOT NULL,
  `maxEstudiantes` int NOT NULL,
  `creditos` int NOT NULL,
  PRIMARY KEY (`idCurso`),
  KEY `idDepartamento_idx` (`idDepartamento`),
  CONSTRAINT `DepartamentoDelCurso` FOREIGN KEY (`idDepartamento`) REFERENCES `departamentos` (`idDepartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (2,2,'Prueba curso','00:00:00','00:00:00',15,1);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos_estudiantes`
--

DROP TABLE IF EXISTS `cursos_estudiantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos_estudiantes` (
  `idEstudiante` int NOT NULL,
  `idCurso` int NOT NULL,
  PRIMARY KEY (`idEstudiante`,`idCurso`),
  KEY `idCruso_idx` (`idCurso`),
  CONSTRAINT `idCursoDelEstudiante` FOREIGN KEY (`idCurso`) REFERENCES `cursos` (`idCurso`),
  CONSTRAINT `idEstudianteDelCurso` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiantes` (`idEstudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos_estudiantes`
--

LOCK TABLES `cursos_estudiantes` WRITE;
/*!40000 ALTER TABLE `cursos_estudiantes` DISABLE KEYS */;
INSERT INTO `cursos_estudiantes` VALUES (1,2);
/*!40000 ALTER TABLE `cursos_estudiantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos_profesores`
--

DROP TABLE IF EXISTS `cursos_profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos_profesores` (
  `idProfesor` int NOT NULL,
  `idCurso` int NOT NULL,
  PRIMARY KEY (`idProfesor`,`idCurso`),
  KEY `idCurso_idx` (`idCurso`),
  CONSTRAINT `idCurso` FOREIGN KEY (`idCurso`) REFERENCES `cursos` (`idCurso`),
  CONSTRAINT `idProfesor` FOREIGN KEY (`idProfesor`) REFERENCES `profesores` (`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos_profesores`
--

LOCK TABLES `cursos_profesores` WRITE;
/*!40000 ALTER TABLE `cursos_profesores` DISABLE KEYS */;
INSERT INTO `cursos_profesores` VALUES (1,2),(2,2);
/*!40000 ALTER TABLE `cursos_profesores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `idDepartamento` int NOT NULL AUTO_INCREMENT,
  `idEscuela` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `Jefe` int DEFAULT NULL,
  PRIMARY KEY (`idDepartamento`),
  KEY `idEscuela_idx` (`idEscuela`),
  KEY `Jefeid_idx` (`Jefe`),
  CONSTRAINT `idEscuela` FOREIGN KEY (`idEscuela`) REFERENCES `escuelas` (`idEscuela`),
  CONSTRAINT `Jefeid` FOREIGN KEY (`Jefe`) REFERENCES `profesores` (`idProfesor`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (2,1,'test1',NULL),(3,1,'El depa noob',NULL),(4,3,'el depa pro',NULL);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escuelas`
--

DROP TABLE IF EXISTS `escuelas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `escuelas` (
  `idEscuela` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idEscuela`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escuelas`
--

LOCK TABLES `escuelas` WRITE;
/*!40000 ALTER TABLE `escuelas` DISABLE KEYS */;
INSERT INTO `escuelas` VALUES (1,'Escuela de prueba'),(2,'Test 2'),(3,'3');
/*!40000 ALTER TABLE `escuelas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiantes`
--

DROP TABLE IF EXISTS `estudiantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiantes` (
  `idEstudiante` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `grado` int NOT NULL,
  PRIMARY KEY (`idEstudiante`),
  KEY `EstudianteUsuario_idx` (`idUsuario`),
  CONSTRAINT `EstudianteUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiantes`
--

LOCK TABLES `estudiantes` WRITE;
/*!40000 ALTER TABLE `estudiantes` DISABLE KEYS */;
INSERT INTO `estudiantes` VALUES (1,2,5);
/*!40000 ALTER TABLE `estudiantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluaciones`
--

DROP TABLE IF EXISTS `evaluaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluaciones` (
  `idEvaluacion` int NOT NULL AUTO_INCREMENT,
  `idCurso` int NOT NULL,
  `idProfesor` int NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `fechaInicio` datetime NOT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `tipo` varchar(45) DEFAULT 'Quiz',
  PRIMARY KEY (`idEvaluacion`),
  KEY `EvaluacionProfesor_idx` (`idProfesor`),
  KEY `EvaluacionCurso_idx` (`idCurso`),
  CONSTRAINT `EvaluacionCurso` FOREIGN KEY (`idCurso`) REFERENCES `cursos` (`idCurso`),
  CONSTRAINT `EvaluacionProfesor` FOREIGN KEY (`idProfesor`) REFERENCES `profesores` (`idProfesor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluaciones`
--

LOCK TABLES `evaluaciones` WRITE;
/*!40000 ALTER TABLE `evaluaciones` DISABLE KEYS */;
INSERT INTO `evaluaciones` VALUES (1,2,2,'Prueba','2025-05-01 00:00:00','2025-05-25 00:00:00','Quiz');
/*!40000 ALTER TABLE `evaluaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesores`
--

DROP TABLE IF EXISTS `profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesores` (
  `idProfesor` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int DEFAULT NULL,
  `idDepartamento` int DEFAULT NULL,
  `especialidad` varchar(45) NOT NULL,
  PRIMARY KEY (`idProfesor`),
  KEY `idDepartamento_idx` (`idDepartamento`),
  KEY `ProfesorUsuario_idx` (`idUsuario`),
  CONSTRAINT `idDepartamento` FOREIGN KEY (`idDepartamento`) REFERENCES `departamentos` (`idDepartamento`),
  CONSTRAINT `ProfesorUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesores`
--

LOCK TABLES `profesores` WRITE;
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT INTO `profesores` VALUES (1,3,2,'Programacion'),(2,4,2,'NOSE');
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `identificacion` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `estado` tinyint DEFAULT '1',
  PRIMARY KEY (`idUsuario`,`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'1','Cris','Prince','test','1234','Administradores','1234',1),(2,'2','xdEstudiante','Prueba','est','123','Estudiantes','1234',1),(3,'1567','ProfesorPrueba','Jeje','aaa','123','Profesores','1234',1),(4,'9999','ProfesorPrueba2','jejejejejeje','aaa','123','Profesores','1234',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-20 22:51:30
