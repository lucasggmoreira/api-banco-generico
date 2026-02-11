package me.lucasggmoreira.banco.domain.usuario.dto;

import me.lucasggmoreira.banco.domain.usuario.model.Usuario;

public record DadosDetalheUsuario(
        Long id,
        String usuario,
        String roles
) {
    public DadosDetalheUsuario (Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getAuthorities().toString());
    }
}
