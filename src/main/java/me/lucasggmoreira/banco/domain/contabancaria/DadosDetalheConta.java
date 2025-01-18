package me.lucasggmoreira.banco.domain.contabancaria;

public record DadosDetalheConta(
    Long id,
    String nome,
    int agencia,
    String numeroConta,
    String cpf,
    String email
) {
    public DadosDetalheConta(ContaBancaria conta) {
        this(conta.getId(), conta.getNome(), conta.getAgencia(), conta.getConta(), conta.getCpf(), conta.getUsuario().getUsername());
    }
}
