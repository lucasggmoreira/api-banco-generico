package me.lucasggmoreira.banco.domain.transacoes.dto;

import me.lucasggmoreira.banco.domain.transacoes.enums.MetodoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.model.Transacao;
import me.lucasggmoreira.banco.domain.transacoes.model.Transferencia;

public record DadosTransferenciaDetalhado(
        Long idTransacao,
        String cpfOrigem,
        int agenciaOrigem,
        String contaOrigem,
        String cpfDestino,
        int agenciaDestino,
        String contaDestino,
        double valor,
        String data,
        MetodoTransferencia metodoTransferencia
) {
    public DadosTransferenciaDetalhado(Transferencia transacao, MetodoTransferencia metodoTransferencia) {
        this(transacao.getId(), transacao.getContaBancaria().getCpf(),
                transacao.getContaBancaria().getAgencia(),
                transacao.getContaBancaria().getNumeroConta(),
                transacao.getContaAlvo().getCpf(),
                transacao.getContaAlvo().getAgencia(),
                transacao.getContaAlvo().getNumeroConta(),
                transacao.getValor(),
                transacao.getData().toString(),
                metodoTransferencia
        );
    }
}
