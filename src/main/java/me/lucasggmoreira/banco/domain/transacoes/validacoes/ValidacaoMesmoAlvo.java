package me.lucasggmoreira.banco.domain.transacoes.validacoes;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.infra.exception.custom.DadoInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoMesmoAlvo implements ValidacaoTransferenciaBancaria{
    @Override
    public void validar(ContaBancaria conta, DadosEfetuacaoTransferencia dados) {
        if (dados.cpf() != null && dados.cpf().equals(conta.getCpf()))
            throw new DadoInvalidoException("O CPF destino não pode ser o mesmo que o seu.");
        if ((dados.numeroConta() != null && dados.agencia() != null) &&
                (conta.getNumeroConta().equals(dados.numeroConta()) && (conta.getAgencia() == dados.agencia())))
            throw new DadoInvalidoException("A conta destino não pode ser a mesma que a sua.");
    }

    @Override
    public void validar(ContaBancaria conta, DadosValorTransferencia dados) {

    }
}
