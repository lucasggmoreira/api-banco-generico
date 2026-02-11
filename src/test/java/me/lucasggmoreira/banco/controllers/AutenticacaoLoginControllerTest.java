package me.lucasggmoreira.banco.controllers;

import jakarta.persistence.EntityManager;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosAutenticacao;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosCadastroConta;
import me.lucasggmoreira.banco.domain.usuario.model.Usuario;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Transactional
class AutenticacaoLoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAutenticacao> dadosAutenticacaoJson;

    @Autowired
    private JacksonTester<DadosCadastroConta> dadosCadastroContaJson;

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void criar_conta() throws Exception {
        var usuario = new Usuario("teste@email.com", passwordEncoder.encode("senhateste"));
        var contaBancaria = new ContaBancaria();
        contaBancaria.setId(1L);
        usuario.setContaBancaria(contaBancaria);
        em.persist(usuario);
    }


    @Test
    @DisplayName("Deve devolver código 400 com formato de login inválido")
    void login_cenario1() throws Exception {
        var request = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver código 401 com login inválido")
    void login_cenario2() throws Exception {
        var request = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAutenticacaoJson.write(new DadosAutenticacao("login@invalido.com", "invalida"))
                .getJson())).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Deve devolver código 200 com login válido")
    void login_cenario3() throws Exception {
        var request = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAutenticacaoJson.write(new DadosAutenticacao("teste@email.com", "senhateste"))
                        .getJson())).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}