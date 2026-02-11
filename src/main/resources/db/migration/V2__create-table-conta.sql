CREATE TABLE contas_bancarias (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
        cpf VARCHAR(11) UNIQUE NOT NULL,
        agencia INT NOT NULL DEFAULT 1,
        numero_conta VARCHAR(255) UNIQUE NOT NULL,
        saldo DOUBLE NOT NULL DEFAULT 0,
        usuario_id BIGINT,
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);