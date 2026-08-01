#EJECUCION PRIMORDIAL
CREATE TABLE Especialidades (
    idEspecialidad INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);
#FIN DE EJECUCION PRIMORDIAL

INSERT INTO Especialidades (codigo, nombre, descripcion) VALUES
('654322', 'Cardiología', 'Diagnóstico y tratamiento de enfermedades del corazón y sistema circulatorio'),
('654323', 'Dermatología', 'Atención y cuidado médico de la piel, cabello y uñas'),
('654324', 'Endocrinología', 'Diagnóstico y tratamiento de trastornos hormonales y metabólicos'),
('654325', 'Neurología', 'Tratamiento de trastornos del sistema nervioso central y periférico'),
('654326', 'Traumatología', 'Evaluación y tratamiento de lesiones del sistema músculo-esquelético');

