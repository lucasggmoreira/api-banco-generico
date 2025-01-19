package me.lucasggmoreira.banco.domain.contabancaria;

import me.lucasggmoreira.banco.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    UserDetails findByConta(String conta);

    ContaBancaria findByUsuario(Usuario usuario);
}
