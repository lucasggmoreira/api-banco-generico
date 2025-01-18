package me.lucasggmoreira.banco.domain.usuario.validacoes;

import me.lucasggmoreira.banco.domain.usuario.DadosCadastroConta;
import me.lucasggmoreira.banco.infra.exception.custom.DadoInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoCPF implements ValidacaoContaBancaria{


    @Override
    public void validar(DadosCadastroConta dados) {
        var cpf = dados.cpf().replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            throw new DadoInvalidoException("CPF inválido");
        }

        int soma = 0, peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }
        int primeiroDigito = (soma * 10) % 11;
        if (primeiroDigito == 10) primeiroDigito = 0;

        if (primeiroDigito != (cpf.charAt(9) - '0')) {
            throw new DadoInvalidoException("CPF inválido");
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }
        int segundoDigito = (soma * 10) % 11;
        if (segundoDigito == 10) segundoDigito = 0;

        if (segundoDigito != (cpf.charAt(10) - '0')) {
            throw new DadoInvalidoException("CPF inválido");
        }
    }
}
