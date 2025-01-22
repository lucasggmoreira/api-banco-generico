package me.lucasggmoreira.banco.domain.transacoes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_bancaria_id")
    private ContaBancaria contaBancaria;

    private Double valor;
    private TipoTransacao tipoTransacao;
    private MetodoTransacao metodoTransacao;
    private LocalDateTime data;


    public Transacao(ContaBancaria conta, DadosValorTransferencia dadosTransferencia, MetodoTransacao metodoTransacao, TipoTransacao tipoTransacao) {
        this.contaBancaria = conta;
        this.valor = dadosTransferencia.valor();
        this.tipoTransacao = tipoTransacao;
        this.metodoTransacao = metodoTransacao;
        this.data = LocalDateTime.now();
    }

}
