package me.lucasggmoreira.banco.domain.transacoes.funcoes;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancariaRepository;
import me.lucasggmoreira.banco.domain.transacoes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FerramentaTransacoes {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;


    public DadosTransacaoDetalhado transferir(ContaBancaria conta, DadosValorTransferencia dadosTransferencia, TipoTransacao tipoTransacao){
        if (tipoTransacao == TipoTransacao.ENTRADA){
            conta.depositar(dadosTransferencia.valor());
        }
        if (tipoTransacao == TipoTransacao.SAIDA){
            conta.sacar(dadosTransferencia.valor());
        }
        var transacao = new Transacao(conta, dadosTransferencia, MetodoTransacao.DEPOSITO, tipoTransacao);
        transacaoRepository.save(transacao);
        contaBancariaRepository.save(conta);
        return new DadosTransacaoDetalhado(transacao);
    }


}
