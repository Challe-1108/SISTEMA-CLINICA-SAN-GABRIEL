-- Orden: primero Pago, luego Comprobante --
-- Ejecucion primordial --

CREATE TABLE IF NOT EXISTS comprobante (
    id_comprobante INT AUTO_INCREMENT PRIMARY KEY,
    numero_comprobante VARCHAR(20) NOT NULL UNIQUE,
    fecha_emision DATE NOT NULL,
    tipo_comprobante VARCHAR(20) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    id_pago INT NOT NULL UNIQUE,

    FOREIGN KEY (id_pago) REFERENCES pago(id_pago)
);
-- Fin de ejecucion primordial --

-- Comprobantes de prueba
INSERT INTO comprobante (numero_comprobante, fecha_emision, tipo_comprobante, total, id_pago)
VALUES ('B001-000001', '2026-07-26', 'Boleta', 120.00, 1);

INSERT INTO comprobante (numero_comprobante, fecha_emision, tipo_comprobante, total, id_pago)
VALUES ('F001-000001', '2026-07-26', 'Factura', 250.00, 2);