package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;

public class Especie {

    char identificador;
    String nome;
    JPanel imagem;

    public Especie(char identificador, String nome, JPanel imagem) {

        this.identificador = identificador;
        this.nome = nome;
        this.imagem = imagem;
    }
}
