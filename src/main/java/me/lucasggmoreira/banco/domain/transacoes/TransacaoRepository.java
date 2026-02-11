package me.lucasggmoreira.banco.domain.transacoes;

import me.lucasggmoreira.banco.domain.transacoes.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
