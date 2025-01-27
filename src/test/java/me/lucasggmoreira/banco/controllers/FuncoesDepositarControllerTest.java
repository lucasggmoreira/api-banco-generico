package me.lucasggmoreira.banco.controllers;

import jakarta.persistence.EntityManager;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.contabancaria.funcoes.GeradorNumeroConta;
import me.lucasggmoreira.banco.domain.transacoes.dto.DadosValorTransferencia;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosAutenticacao;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosCadastroConta;
import me.lucasggmoreira.banco.domain.usuario.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Transactional
class FuncoesDepositarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAutenticacao> dadosLogin;

    @Autowired
    private JacksonTester<DadosCadastroConta> dadosCadastroConta;

    @Autowired
    private JacksonTester<DadosValorTransferencia> dadosValorTransferencia;

    public String token;

    @BeforeEach
    void setUp() throws Exception {
        gerarContaTeste(new DadosCadastroConta("Teste1", "teste1@email.com", "02979714097", "senhateste"));
        var response = mvc.perform(post("/auth/login").
                contentType(MediaType.APPLICATION_JSON).
                content(dadosLogin.write(new DadosAutenticacao("teste1@email.com", "senhateste"))
                .getJson())).andReturn();
        this.token = response.getResponse().getContentAsString().substring(10).replace("\"}", "");
    }

    @Test
    @DisplayName("Deve retornar o código 400 ao tentar depositar sem informar o valor")
    public void transferencia_1() throws Exception {
        var request = mvc.perform(post("/funcoes/depositar").
                header("Authorization", "Bearer " + token)).
                andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar o código 400 ao tentar depositar zero")
    public void transferencia_2() throws Exception {
        var request = mvc.perform(post("/funcoes/depositar").
                contentType(MediaType.APPLICATION_JSON)
                .content(dadosValorTransferencia.write(new DadosValorTransferencia(0)).getJson())
                .header("Authorization", "Bearer " + token)).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Deve retornar o código 400 ao tentar depositar um valor negativo")
    public void transferencia_5() throws Exception {
        var request = mvc.perform(post("/funcoes/depositar").
                contentType(MediaType.APPLICATION_JSON)
                .content(dadosValorTransferencia.write(new DadosValorTransferencia(-100)).getJson())
                .header("Authorization", "Bearer " + token)).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar o código 200 ao depositar com sucesso")
    public void transferencia_4() throws Exception {
        var request = mvc.perform(post("/funcoes/depositar").
                contentType(MediaType.APPLICATION_JSON)
                .content(dadosValorTransferencia.write(new DadosValorTransferencia(300)).getJson())
                .header("Authorization", "Bearer " + token)).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.OK.value());
    }



    private void gerarContaTeste(DadosCadastroConta dados) throws Exception {
        var request = mvc.perform(post("/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroConta.write(dados).getJson())).andReturn().getResponse();
    }



}