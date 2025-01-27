package me.lucasggmoreira.banco.domain.transacoes.validacoes;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.infra.exception.custom.DadoInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoSaldoSuficiente implements ValidacaoTransferenciaBancaria {

    @Override
    public void validar(ContaBancaria conta, DadosEfetuacaoTransferencia dados) {
        if (conta.getSaldo() < dados.valor()){
            throw new DadoInvalidoException("Saldo insuficiente");
        }
    }

    @Override
    public void validar(ContaBancaria conta, DadosValorTransferencia dados) {

    }
}
