#EJECUCION PRIMORDIAL
CREATE TABLE Medicos (
    idMedico INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    colegiatura VARCHAR(20) NOT NULL UNIQUE,
    dni VARCHAR(15) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);
#FIN DE EJECUCION PRIMORDIAL

INSERT INTO Medicos (codigo,colegiatura,dni,nombres,apellidos,telefono,correo) VALUES
('98752','02131','14149829','Maria','Linares','951666018','marialin@colegiomedico.pe');
