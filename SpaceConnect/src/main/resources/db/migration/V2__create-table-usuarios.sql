CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          login VARCHAR(100) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (login, senha) VALUES ('admin@gmail.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');