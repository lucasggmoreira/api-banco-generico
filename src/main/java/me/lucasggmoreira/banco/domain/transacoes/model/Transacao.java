package me.lucasggmoreira.banco.domain.transacoes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.transacoes.enums.MetodoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.enums.TipoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;

import java.time.LocalDateTime;

@Table(name = "transacoes")
@Entity(name = "Transacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_bancaria_id")
    private ContaBancaria contaBancaria;
    private Double valor;
    private TipoTransacao tipoTransacao;
    private MetodoTransacao metodoTransacao;
    private LocalDateTime data = LocalDateTime.now();


    public Transacao(ContaBancaria conta, double dadosTransferencia) {
        this.contaBancaria = conta;
        this.valor = dadosTransferencia;
    }

}
