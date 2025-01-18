package me.lucasggmoreira.banco.domain.contabancaria;


import jakarta.persistence.*;
import lombok.*;
import me.lucasggmoreira.banco.domain.usuario.DadosCadastroConta;
import me.lucasggmoreira.banco.domain.usuario.Usuario;


@Table(name = "conta_bancaria")
@Entity(name = "Contas Bancarias")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private int agencia = 1;

    @Column(unique = true, nullable = false)
    private String conta;

    private double saldo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public ContaBancaria(DadosCadastroConta dados, Usuario usuario) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.conta = "1";
        this.saldo = 0;
        this.usuario = usuario;
    }

}

