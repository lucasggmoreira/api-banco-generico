package me.lucasggmoreira.banco.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosTransacaoDetalhado;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosTransferenciaDetalhado;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.enums.TipoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.funcoes.FerramentaTransacoes;
import me.lucasggmoreira.banco.domain.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcoes")
@SecurityRequirement(name = "bearer-key")
public class FuncoesController {

    @Autowired
    private FerramentaTransacoes ferramentaTransacoes;

    @PostMapping("/depositar")
    @Transactional
    public ResponseEntity<DadosTransacaoDetalhado> depositar(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid DadosValorTransferencia dados){
        var dadosTransacao = ferramentaTransacoes.transferir(usuario.getContaBancaria(), dados, TipoTransacao.ENTRADA);
        return ResponseEntity.ok(dadosTransacao);
    }

    @PostMapping("/sacar")
    @Transactional
    public ResponseEntity<DadosTransacaoDetalhado> sacar(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid DadosValorTransferencia dados){
        var dadosTransacao = ferramentaTransacoes.transferir(usuario.getContaBancaria(), dados, TipoTransacao.SAIDA);
        return ResponseEntity.ok(dadosTransacao);
    }

    @PostMapping("/transferir")
    @Transactional
    public ResponseEntity<DadosTransferenciaDetalhado> transferir(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid DadosEfetuacaoTransferencia dados){
        var dadosTransacao = ferramentaTransacoes.transferir(usuario.getContaBancaria(), dados);
        return ResponseEntity.ok(dadosTransacao);
    }




}
