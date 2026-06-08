
CREATE TABLE dispositivos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        tipo_dispositivo VARCHAR(50) NOT NULL,
                        nome VARCHAR(100) NOT NULL,
                        localizacao VARCHAR(100),
                        ativo TINYINT DEFAULT 1,
                        orbita VARCHAR(100),
                        cultura_monitorada VARCHAR(100)
);

CREATE TABLE alertas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         descricao VARCHAR(255) NOT NULL,
                         gravidade VARCHAR(50) NOT NULL,
                         data_hora_emissao DATETIME NOT NULL,
                         data_hora_resolucao DATETIME,
                         dispositivo_id BIGINT NOT NULL,
                         CONSTRAINT fk_alertas_dispositivo_id FOREIGN KEY (dispositivo_id) REFERENCES dispositivos(id)
);

INSERT INTO dispositivos (tipo_dispositivo, nome, localizacao, orbita, ativo)
VALUES ('SATELITE', 'Space Connect Sat-01', 'Espaço Sideral', 'Órbita Baixa (LEO)', 1);

INSERT INTO dispositivos (tipo_dispositivo, nome, localizacao, cultura_monitorada, ativo)
VALUES ('SENSOR_SOLO', 'AgroScan Camera 01', 'Fazenda São Jorge - Setor A', 'Morango', 1);