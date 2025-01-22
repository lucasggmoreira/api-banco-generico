package me.lucasggmoreira.banco.domain.transacoes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosValorTransferencia(
        @Positive(message = "O valor da transferência deve ser maior que zero")
        double valor
) {
}
