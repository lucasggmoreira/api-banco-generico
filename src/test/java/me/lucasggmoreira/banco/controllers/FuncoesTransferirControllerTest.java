package me.lucasggmoreira.banco.controllers;

import me.lucasggmoreira.banco.domain.transacoes.dto.DadosEfetuacaoTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosAutenticacao;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosCadastroConta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Transactional
class FuncoesTransferirControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAutenticacao> dadosLogin;

    @Autowired
    private JacksonTester<DadosCadastroConta> dadosCadastroConta;

    private String token;

    @Autowired
    private JacksonTester<DadosEfetuacaoTransferencia> dadosTransferencia;


    @BeforeEach
    void setUp() throws Exception {
        gerarContaTeste(new DadosCadastroConta("Teste1", "teste1@email.com", "02979714097", "senhateste"));
        gerarContaTeste(new DadosCadastroConta("Teste2", "teste2@email.com", "02979714097", "senhateste"));
        var response = mvc.perform(post("/auth/login").
                contentType(MediaType.APPLICATION_JSON).
                content(dadosLogin.write(new DadosAutenticacao("teste1@email.com", "senhateste"))
                        .getJson())).andReturn();
        this.token = response.getResponse().getContentAsString().substring(10).replace("\"}", "");
    }

    private void gerarContaTeste(DadosCadastroConta dados) throws Exception {
        mvc.perform(post("/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroConta.write(dados).getJson())).andReturn().getResponse();
    }

    @Test
    @DisplayName("Deve retornar o código 400 ao tentar transferir sem informar nada")
    public void transferencia_1() throws Exception {
        var request = mvc.perform(post("/funcoes/transferir").
                        header("Authorization", "Bearer " + token)).
                andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }





}