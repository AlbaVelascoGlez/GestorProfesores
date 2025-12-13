-- Script de datos iniciales para el Sistema de Gestión de Docentes
-- Base de datos: gestor_profesores

-- Inserción de Departamentos
INSERT INTO departamento (nombre, codigo, telefono) VALUES
('Informática y Comunicaciones', 'IFC', '984100101'),
('Electricidad y Electrónica', 'ELE', '984100102'),
('Fabricación Mecánica', 'FME', '984100103'),
('Instalación y Mantenimiento', 'IMA', '984100104'),
('Química', 'QUI', '984100105'),
('Administración y Gestión', 'ADG', '984100109'),
('Comercio y Marketing', 'COM', '984100110'),
('Edificación y Obra Civil', 'EOC', '984100113');

-- Inserción de Docentes
INSERT INTO docente (nombre, apellidos, email, siglas, departamento_id) VALUES

-- IFC – Informática y Comunicaciones. Departamento 1
('Sergio', 'Martínez López', 'sergioml@educastur.org', 'MLSe', 1),
('Laura', 'González Pérez', 'lauragp@educastur.org', 'GPLa', 1),
('David', 'Álvarez Rubio', 'davidar@educastur.org', 'ARDa', 1),

-- ELE – Electricidad y Electrónica. Departamento 2
('Diego', 'Fernández Álvarez', 'diegofa@educastur.org', 'FADi', 2),
('María', 'Suárez Prieto', 'mariasu@educastur.org', 'SPMa', 2),

-- FME – Fabricación Mecánica. Departamento 3
('Javier', 'Campos Rubio', 'javiercr@educastur.org', 'CRJa', 3),

-- IMA – Instalación y Mantenimiento. Departamento 4
('Hugo', 'García Prado', 'hugogp@educastur.org', 'GPHu', 4),
('Elena', 'López Castro', 'elenalc@educastur.org', 'LCEl', 4),

-- QUI – Química. Departamento 5
('Daniel', 'Santos Vega', 'danielsv@educastur.org', 'SVDa', 5),
('Lucía', 'Ortega Rivas', 'luciaor@educastur.org', 'ORLu', 5),
('Noelia', 'Lago Souto', 'noelials@educastur.org', 'LSNo', 5),

-- ADG – Administración y Gestión. Departamento 6
('Beatriz', 'Sánchez Rojo', 'beatrizsr@educastur.org', 'SRBe', 6),

-- COM – Comercio y Marketing. Departamento 7
('Raquel', 'Vega Soto', 'raquelvs@educastur.org', 'SVRa', 7),
('Alberto', 'Lorenzo Díaz', 'albertold@educastur.org', 'LDAl', 7),
('Marta', 'Rey Castro', 'martarc@educastur.org', 'CRMa', 7);
