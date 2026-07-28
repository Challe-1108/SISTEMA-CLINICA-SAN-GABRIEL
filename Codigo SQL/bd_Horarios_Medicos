#EJECUCIÓN PRIMORDIAL
CREATE TABLE Horarios_Medicos (
    idHorario INT AUTO_INCREMENT PRIMARY KEY,
    idMedico INT NOT NULL,
    diaSemana VARCHAR(15) NOT NULL,
    horaInicio TIME NOT NULL,
    horaFin TIME NOT NULL,
    FOREIGN KEY (idMedico) REFERENCES Medicos(idMedico)
);
#FIN DE EJECUCIÓN PRIMORDIAL

INSERT INTO Horarios_Medicos (idMedico, diaSemana, horaInicio, horaFin) VALUES
(1, 'Lunes', '08:00:00', '13:00:00'),
(1, 'Miércoles', '08:00:00', '13:00:00'),
(1, 'Viernes', '14:00:00', '19:00:00');
