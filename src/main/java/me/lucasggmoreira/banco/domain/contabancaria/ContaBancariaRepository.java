package me.lucasggmoreira.banco.domain.contabancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    UserDetails findByConta(String conta);
}
