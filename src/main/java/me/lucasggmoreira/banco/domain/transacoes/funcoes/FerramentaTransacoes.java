package me.lucasggmoreira.banco.domain.transacoes.funcoes;

import jakarta.validation.Valid;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancariaRepository;
import me.lucasggmoreira.banco.domain.transacoes.*;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosTransacaoDetalhado;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosTransferenciaDetalhado;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.enums.MetodoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.enums.MetodoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.enums.TipoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.model.Deposito;
import me.lucasggmoreira.banco.domain.transacoes.model.Saque;
import me.lucasggmoreira.banco.domain.transacoes.model.Transacao;
import me.lucasggmoreira.banco.domain.transacoes.model.Transferencia;
import me.lucasggmoreira.banco.domain.transacoes.validacoes.ValidacaoTransferenciaBancaria;
import me.lucasggmoreira.banco.domain.usuario.validacoes.ValidacaoCPF;
import me.lucasggmoreira.banco.infra.exception.custom.DadoInvalidoException;
import me.lucasggmoreira.banco.infra.exception.custom.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class FerramentaTransacoes {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private List<ValidacaoTransferenciaBancaria> validacoes;



    public DadosTransacaoDetalhado transferir(ContaBancaria conta, DadosValorTransferencia dadosTransferencia, TipoTransacao tipoTransacao){
        Transacao transacao = null;
        if (tipoTransacao == TipoTransacao.ENTRADA){
            transacao = new Deposito(conta, dadosTransferencia);
            conta.depositar(dadosTransferencia.valor());
        }
        if (tipoTransacao == TipoTransacao.SAIDA){
            validacoes.forEach(v -> v.validar(conta, dadosTransferencia));
            transacao = new Saque(conta, dadosTransferencia);
            conta.sacar(dadosTransferencia.valor());
        }
        transacaoRepository.save(transacao);
        contaBancariaRepository.save(conta);
        return new DadosTransacaoDetalhado(transacao);
    }


    public DadosTransferenciaDetalhado transferir(ContaBancaria contaOrigem, @Valid DadosEfetuacaoTransferencia dados) {
        validacoes.forEach(v -> v.validar(contaOrigem, dados));
        var tipoConta = verificarTipoConta(dados);
        var contaDestino = new ContaBancaria();
        if (tipoConta.equals(MetodoTransferencia.CPF)) contaDestino = contaBancariaRepository.findByCpf(dados.cpf());
        if (tipoConta.equals(MetodoTransferencia.CONTA)) contaDestino = contaBancariaRepository.findByAgenciaAndNumeroConta(dados.agencia(), dados.numeroConta());
        contaOrigem.transferir(contaDestino, dados.valor());
        var transacao = new Transferencia(contaOrigem, contaDestino, dados, tipoConta);
        transacaoRepository.save(transacao);
        contaBancariaRepository.save(contaOrigem);
        contaBancariaRepository.save(contaDestino);
        return new DadosTransferenciaDetalhado(transacao, tipoConta);
    }


    private MetodoTransferencia verificarTipoConta(DadosEfetuacaoTransferencia dados) {
        if (dados.cpf() == null && (dados.agencia() == null || dados.numeroConta() == null)){
            throw new DadoInvalidoException("CPF ou Agência e Número da conta devem ser informados.");
        }
        if (dados.cpf() != null){
            var validarConta = contaBancariaRepository.findByCpf(dados.cpf());
            if (validarConta == null){
                throw new NaoEncontradoException("Conta não encontrada.");
            }
            return MetodoTransferencia.CPF;
        } else {
            var validarConta = contaBancariaRepository.findByAgenciaAndNumeroConta(dados.agencia(), dados.numeroConta());
            if (validarConta == null){
                throw new NaoEncontradoException("Conta não encontrada.");
            }
            return MetodoTransferencia.CONTA;
        }
    }
}
