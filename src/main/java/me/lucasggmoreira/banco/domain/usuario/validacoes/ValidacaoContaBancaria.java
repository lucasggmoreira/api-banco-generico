package me.lucasggmoreira.banco.domain.usuario.validacoes;

import me.lucasggmoreira.banco.domain.usuario.dto.DadosCadastroConta;

public interface ValidacaoContaBancaria {

    void validar(DadosCadastroConta dados);
    void validar(String dados);
}
