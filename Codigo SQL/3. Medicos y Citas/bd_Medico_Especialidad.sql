#EJECUCION PRIMORDIAL
CREATE TABLE Medico_Especialidad (
    idMedico INT NOT NULL,
    idEspecialidad INT NOT NULL,
    PRIMARY KEY (idMedico, idEspecialidad),
    FOREIGN KEY (idMedico) REFERENCES Medicos(idMedico),
    FOREIGN KEY (idEspecialidad) REFERENCES Especialidades(idEspecialidad)
);
#FIN DE EJECUCIÓN PRIMORDIAL
