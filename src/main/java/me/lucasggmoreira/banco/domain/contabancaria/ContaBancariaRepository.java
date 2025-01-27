package me.lucasggmoreira.banco.domain.contabancaria;

import me.lucasggmoreira.banco.domain.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    ContaBancaria findByUsuario(Usuario usuario);
    ContaBancaria findByCpf(String cpf);
    ContaBancaria findByNumeroConta(String agencia);
    ContaBancaria findByAgenciaAndNumeroConta(int numeroConta, String agencia);
}
