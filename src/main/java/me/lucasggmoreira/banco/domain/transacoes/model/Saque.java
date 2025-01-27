package me.lucasggmoreira.banco.domain.transacoes.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.enums.MetodoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.enums.TipoTransacao;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Saque")
public class Saque extends Transacao{

    public Saque(ContaBancaria conta, DadosValorTransferencia dadosTransferencia) {
        super(conta, dadosTransferencia.valor());
        setMetodoTransacao(MetodoTransacao.SAQUE);
        setTipoTransacao(TipoTransacao.SAIDA);
    }
}
