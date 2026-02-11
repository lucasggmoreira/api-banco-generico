package me.lucasggmoreira.banco.domain.transacoes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.enums.MetodoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.enums.MetodoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.enums.TipoTransacao;

@Entity
@DiscriminatorValue("Transferencia")
@NoArgsConstructor
@Getter
@Setter
public class Transferencia extends Transacao{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_bancaria_alvo_id")
    private ContaBancaria contaAlvo;
    private MetodoTransferencia tipoConta;

    public Transferencia(ContaBancaria contaOrigem, ContaBancaria contaAlvo, DadosEfetuacaoTransferencia dadosTransferencia, MetodoTransferencia tipoConta) {
        super(contaOrigem, dadosTransferencia.valor());
        this.contaAlvo = contaAlvo;
        this.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
        this.setMetodoTransacao(MetodoTransacao.TRANSFERENCIA);
        setTipoConta(tipoConta);
    }
}
