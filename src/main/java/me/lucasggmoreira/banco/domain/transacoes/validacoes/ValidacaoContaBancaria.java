package me.lucasggmoreira.banco.domain.transacoes.validacoes;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.infra.exception.custom.DadoInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoContaBancaria implements ValidacaoTransferenciaBancaria{

    @Override
    public void validar(ContaBancaria conta, DadosEfetuacaoTransferencia dados) {
        if (dados.numeroConta() != null)
        {
            try{
                var numero = Integer.parseInt(dados.numeroConta().replaceAll("[^0-9]", ""));
                if (numero < 1000) throw new DadoInvalidoException("Número da conta inválido.");
            } catch (NumberFormatException e){
                throw new DadoInvalidoException("Número da conta inválido.");
            }
        }
    }

    @Override
    public void validar(ContaBancaria conta, DadosValorTransferencia dados) {

    }
}
