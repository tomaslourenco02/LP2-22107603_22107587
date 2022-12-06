package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;

public class Especie {

    protected String identificador;
    protected String nome;
    protected String imagem;
    protected int energiaInicial;
    protected int consumoEnergia;
    protected int ganhoEnergiaEmDescanso;
    protected String velocidade;
    protected String tipo;

    public Especie(String identificador, String nome, String imagem, int energiaInicial, int consumoEnergia, int ganhoEnergiaEmDescanso, String velocidade, String tipo) {
        this.identificador = identificador;
        this.nome = nome;
        this.imagem = imagem;
        this.energiaInicial = energiaInicial;
        this.consumoEnergia = consumoEnergia;
        this.ganhoEnergiaEmDescanso = ganhoEnergiaEmDescanso;
        this.velocidade = velocidade;
        this.tipo = tipo;
    }
}