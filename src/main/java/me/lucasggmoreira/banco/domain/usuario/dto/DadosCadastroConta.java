package me.lucasggmoreira.banco.domain.usuario.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record DadosCadastroConta(
        @NotBlank(message = "O nome não pode ser vazio.")
        String nome,
        @Email(message = "O email deve ser válido.")
        @NotBlank(message = "O email não pode ser vazio.")
        String email,
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres.")
        @NotBlank(message = "O CPF não pode ser vazio.")
        String cpf,
        @NotBlank(message = "A senha não pode ser vazia.")
        @Size(min = 8, max = 24, message = "A senha deve ter entre 8 e 24 caracteres.")
        String senha
) {
}
