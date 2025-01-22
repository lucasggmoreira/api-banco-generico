CREATE TABLE transacao (
           id BIGINT AUTO_INCREMENT PRIMARY KEY,
           conta_bancaria_id BIGINT,
           valor DOUBLE NOT NULL,
           tipo_transacao VARCHAR(255) NOT NULL,
           metodo_transacao VARCHAR(255) NOT NULL,
           data TIMESTAMP NOT NULL,
           FOREIGN KEY (conta_bancaria_id) REFERENCES conta_bancaria(id)
);