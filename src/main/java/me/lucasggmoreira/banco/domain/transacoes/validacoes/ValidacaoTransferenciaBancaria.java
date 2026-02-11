package me.lucasggmoreira.banco.domain.transacoes.validacoes;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;

public interface ValidacaoTransferenciaBancaria {
    void validar(ContaBancaria conta, DadosEfetuacaoTransferencia dados);

    void validar(ContaBancaria conta, DadosValorTransferencia dados);
}
