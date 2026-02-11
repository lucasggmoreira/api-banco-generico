CREATE TABLE transacoes (
           id BIGINT AUTO_INCREMENT PRIMARY KEY,
           conta_bancaria_id BIGINT,
           conta_bancaria_alvo_id BIGINT,
            tipo_conta varchar(30),
           valor DOUBLE NOT NULL,
           tipo_transacao VARCHAR(255) NOT NULL,
           metodo_transacao VARCHAR(255) NOT NULL,
           data TIMESTAMP NOT NULL,
           FOREIGN KEY (conta_bancaria_id) REFERENCES contas_bancarias(id)
);