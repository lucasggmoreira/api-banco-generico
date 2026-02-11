package me.lucasggmoreira.banco.controllers;

import jakarta.persistence.EntityManager;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosCadastroConta;
import me.lucasggmoreira.banco.domain.usuario.model.Usuario;
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
class AutenticacaoRegistroControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroConta> dadosCadastroContaJson;

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Deve devolver código 400 com formato de cadastro inválido")
    void cadastro_cenario1() throws Exception {
        var request = mvc.perform(post("/auth/cadastro").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver código 400 com cpf inválido")
    void cadastro_cenario2() throws Exception {
        var request = mvc.perform(post("/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroContaJson.write(new DadosCadastroConta("Teste",
                                "teste@teste.com", "12345678", "senhateste"))
                        .getJson())).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver código 400 com email inválido")
    void cadastro_cenario3() throws Exception {
        var request = mvc.perform(post("/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroContaJson.write(new DadosCadastroConta("Teste",
                                "teste", "07530508032", "senhateste"))
                        .getJson())).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver código 409 com email já cadastrado")
    void cadastro_cenario4() throws Exception {
        criarContaTeste();
        var request = mvc.perform(post("/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroContaJson.write(new DadosCadastroConta("Teste",
                                "teste@email.com", "07530508032", "senhateste"))
                        .getJson())).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    @DisplayName("Deve devolver código 409 com cpf já cadastrado")
    void cadastro_cenario5() throws Exception {
        criarContaTeste();
        var request = mvc.perform(post("/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroContaJson.write(new DadosCadastroConta("Teste",
                                "teste@email.com", "07530508032", "senhateste"))
                        .getJson())).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    @DisplayName("Deve devolver código 201 com conta criada válida")
    void cadastro_cenario6() throws Exception {
        var request = mvc.perform(post("/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroContaJson.write(new DadosCadastroConta("Teste",
                        "teste@teste.com", "07530508032", "senhateste"))
                        .getJson())).andReturn().getResponse();
        assertThat(request.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    private void criarContaTeste() {
        var cadastroConta = new DadosCadastroConta("Teste", "teste@email.com", "07530508032",
                "senhateste");
        var usuario = new Usuario(cadastroConta.email(), passwordEncoder.encode(cadastroConta.senha()));
        var contaBancaria = new ContaBancaria(cadastroConta, "12345678");
        contaBancaria.setId(1L);
        usuario.setContaBancaria(contaBancaria);
        em.persist(usuario);
    }


}