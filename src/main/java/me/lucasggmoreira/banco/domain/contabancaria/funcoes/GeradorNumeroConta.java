package me.lucasggmoreira.banco.domain.contabancaria.funcoes;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GeradorNumeroConta {
    public String gerarNumeroConta() {
        int numeroConta = new Random().nextInt(9000000) + 1000000;
        int dv = calcularDigitoModulo11(numeroConta);
        return numeroConta + "-" + (dv == 10 ? "X" : dv);
    }

    private int calcularDigitoModulo11(int numero) {
        String numStr = String.valueOf(numero);
        int soma = 0, peso = 2;

        for (int i = numStr.length() - 1; i >= 0; i--) {
            soma += Character.getNumericValue(numStr.charAt(i)) * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }

        int resto = soma % 11;
        int dv = 11 - resto;
        return (dv == 10) ? 0 : (dv == 11 ? 0 : dv);
    }
}
