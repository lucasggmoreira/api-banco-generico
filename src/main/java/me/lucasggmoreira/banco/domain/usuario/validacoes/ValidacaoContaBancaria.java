package me.lucasggmoreira.banco.domain.usuario.validacoes;

import me.lucasggmoreira.banco.domain.usuario.DadosCadastroConta;

public interface ValidacaoContaBancaria {

    void validar(DadosCadastroConta dados);
}
