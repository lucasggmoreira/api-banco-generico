package me.lucasggmoreira.banco.domain.contabancaria;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import me.lucasggmoreira.banco.domain.transacoes.model.Transacao;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosCadastroConta;
import me.lucasggmoreira.banco.domain.usuario.model.Usuario;
import me.lucasggmoreira.banco.infra.exception.custom.DadoInvalidoException;

import java.util.List;


@Table(name = "contas_bancarias")
@Entity(name = "Contas Bancarias")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    @OneToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(unique = true, nullable = false)
    private String numeroConta;
    private int agencia = 1;
    private double saldo;

    @OneToMany(mappedBy = "contaBancaria")
    @JsonIgnore
    private List<Transacao> transacoes;

    public ContaBancaria(DadosCadastroConta dados, String numeroConta) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.numeroConta = numeroConta;
        this.saldo = 0;
    }

    public void depositar(double valor){
        if (valor <= 0){
            throw new DadoInvalidoException("O valor do depósito deve ser maior do que 0!");
        }
        this.saldo += valor;
    }

    public void sacar(double valor){
        if (valor <= 0){
            throw new DadoInvalidoException("O valor do saque deve ser maior do que 0!");
        }
        if (this.saldo < valor){
            throw new DadoInvalidoException("Saldo insuficiente!");
        }
        this.saldo -= valor;
    }

    public void transferir(ContaBancaria contaDestino, double valor) {
        this.saldo -= valor;
        contaDestino.depositar(valor);
    }
}

