package me.lucasggmoreira.banco.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank(message = "O login não pode ser vazio.")
        String login,
        @NotBlank(message = "A senha não pode ser vazia.")
        String senha
) {
}
