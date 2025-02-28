package me.lucasggmoreira.banco.domain.usuario;

import me.lucasggmoreira.banco.domain.usuario.enums.TipoConta;
import me.lucasggmoreira.banco.domain.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);

    boolean existsByTipoConta(TipoConta tipoConta);
}
