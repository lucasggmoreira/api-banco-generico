package me.lucasggmoreira.banco.domain.transacoes.validacoes;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.domain.usuario.validacoes.ValidacaoCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoCPFTransferencia implements ValidacaoTransferenciaBancaria {

    @Autowired
    private ValidacaoCPF validacaoCPF;

    @Override
    public void validar(ContaBancaria conta, DadosEfetuacaoTransferencia dados) {
        if (dados.cpf() != null){
            validacaoCPF.validar(dados.cpf());
        }
    }

    @Override
    public void validar(ContaBancaria conta, DadosValorTransferencia dados) {

    }
}
