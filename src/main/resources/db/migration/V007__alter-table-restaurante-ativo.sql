ALTER TABLE restaurante ADD COLUMN ativo TINYINT(1) NOT NULL;
UPDATE restaurante SET ativo = true;