package org.sysimc.utils;

import org.sysimc.model.Pessoa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String NOME_ARQUIVO = "dados_pessoas.txt";

    public static void salvar(Pessoa pessoa) throws IOException {

        try (FileWriter writer = new FileWriter(NOME_ARQUIVO, true)) {

            writer.write(
                    pessoa.getNome() + "," +
                            pessoa.getAltura() + "," +
                            pessoa.getPeso() + "," +
                            pessoa.getImc() +
                            System.lineSeparator()
            );
        }
    }

    public static List<Pessoa> carregar() throws IOException {

        List<Pessoa> pessoas = new ArrayList<>();

        File arquivo = new File(NOME_ARQUIVO);

        if (!arquivo.exists()) {
            return pessoas;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                if (linha.isBlank()) {
                    continue;
                }

                String[] dados = linha.split(",");

                if (dados.length == 4) {

                    String nome = dados[0];
                    float altura = Float.parseFloat(dados[1]);
                    float peso = Float.parseFloat(dados[2]);
                    float imc = Float.parseFloat(dados[3]);

                    Pessoa pessoa =
                            new Pessoa(
                                    nome,
                                    altura,
                                    peso,
                                    imc
                            );

                    pessoas.add(pessoa);
                }
            }
        }

        return pessoas;
    }
}