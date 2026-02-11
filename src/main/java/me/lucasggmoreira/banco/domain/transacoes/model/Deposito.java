package me.lucasggmoreira.banco.domain.transacoes.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.enums.MetodoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.enums.TipoTransacao;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Deposito")
public class Deposito extends Transacao{

    public Deposito(ContaBancaria conta, DadosValorTransferencia dadosTransferencia) {
        super(conta, dadosTransferencia.valor());
        setMetodoTransacao(MetodoTransacao.DEPOSITO);
        setTipoTransacao(TipoTransacao.ENTRADA);
    }
}
