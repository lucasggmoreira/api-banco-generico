package me.lucasggmoreira.banco.domain.transacoes.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DadosEfetuacaoTransferencia (
        Integer agencia,
        String numeroConta,
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres.")
        String cpf,
        @Positive
        double valor
) {
}
