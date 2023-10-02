ALTER TABLE pacientes ADD activo TINYINT;
UPDATE medicos SET activo = 1;