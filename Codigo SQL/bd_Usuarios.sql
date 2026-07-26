USE sistema_clinica_san_gabriel;

CREATE TABLE Usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);





-- EJEMPLOS DE INSERCION DE USUARIOS--
INSERT INTO Usuarios (username, password, rol, estado)
VALUES ("yordin_cr", "12345678", "Administrador", true);


-- CONSULTAS

SELECT * FROM Usuarios;