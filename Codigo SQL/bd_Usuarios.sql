
-- EJECUCION PRIMORDIAL
USE sistema_clinica_san_gabriel;

CREATE TABLE Usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);


-- FIN DE EJECUCION PRIMORDIAL



-- EJEMPLOS DE INSERCION DE USUARIOS--
INSERT INTO Usuarios (username, password, rol, estado)
VALUES ("yordin_cr", "12345678", "Administrador", true);

INSERT INTO Usuarios (username, password, rol, estado)
VALUES ("teffo", "87654321", "Administrador", true);

-- ELIMINAR REGISTROS
TRUNCATE TABLE Usuarios;

-- CONSULTAS

SELECT * FROM Usuarios;


-- BUSQUEDA DE USUARIO POR USERNAME Y PASSWORD

SELECT * FROM Usuarios WHERE (username = "teffo" AND password = "87654321");