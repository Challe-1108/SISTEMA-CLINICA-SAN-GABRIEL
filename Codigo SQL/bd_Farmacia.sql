-- Ejecucion primordial --
CREATE TABLE IF NOT EXISTS medicamento (
    id_medicamento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    stock_actual INT NOT NULL DEFAULT 0,
    stock_minimo INT NOT NULL DEFAULT 5,
    precio_unitario DECIMAL(10,2) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS entrega_medicamento (
    id_entrega INT AUTO_INCREMENT PRIMARY KEY,
    id_atencion INT NOT NULL,
    id_medicamento INT NOT NULL,
    cantidad INT NOT NULL,
    fecha_entrega DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_medicamento) REFERENCES medicamento(id_medicamento)
);

-- Fin de ejecucion primordial --

-- Medicamentos de prueba --
INSERT INTO medicamento (nombre, descripcion, stock_actual, stock_minimo, precio_unitario, estado)
VALUES 
('Paracetamol 500mg', 'Analgésico y antipirético', 50, 10, 1.50, TRUE),
('Amoxicilina 500mg', 'Antibiótico de amplio espectro', 8, 10, 3.00, TRUE),
('Ibuprofeno 400mg', 'Antiinflamatorio no esteroideo', 100, 15, 2.00, TRUE);