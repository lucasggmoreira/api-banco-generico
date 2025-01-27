package me.lucasggmoreira.banco.domain.transacoes.dto;

import me.lucasggmoreira.banco.domain.transacoes.model.Transacao;

public record DadosTransacaoDetalhado(
        Long id_transacao,
        String tipo_transacao,
        String metodo_transacao,
        String data_transacao,
        Double valor_transacao
) {

    public DadosTransacaoDetalhado(Transacao transacao){
        this(transacao.getId(),
                transacao.getTipoTransacao().toString(),
                transacao.getMetodoTransacao().toString(),
                transacao.getData().toString(),
                transacao.getValor());
    }
}
