package me.lucasggmoreira.banco.domain.transacoes.dto;

import jakarta.validation.constraints.Positive;

public record DadosValorTransferencia(
        @Positive(message = "O valor da transferência deve ser maior que zero")
        double valor
) {
}
