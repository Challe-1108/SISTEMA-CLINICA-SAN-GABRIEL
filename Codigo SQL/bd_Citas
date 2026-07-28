#EJECUCION PRIMORDIAL
CREATE TABLE Citas (
    idCita INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    idMedico INT NOT NULL,
    numeroHistoriaClinica VARCHAR(20) NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'Programada',
    observaciones VARCHAR(255),
    FOREIGN KEY (idMedico) REFERENCES Medicos(idMedico)
);
#FIN DE LA EJECUCION PRIMORDIAL

INSERT INTO Citas (codigo, idMedico, pacienteCodigo, fecha, hora, estado, observaciones) VALUES
('CIT-001', 1, 'PAC-1001', '2026-08-01', '08:30:00', 'Programada', 'Control general de pediatría'),
('CIT-002', 1, 'PAC-1002', '2026-08-01', '10:00:00', 'Programada', 'Evaluación por fiebres recurrentes'),
('CIT-003', 2, 'PAC-1003', '2026-08-02', '09:15:00', 'Atendida', 'Revisión de exámenes de laboratorio');
