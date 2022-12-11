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

    public Especie() {}

    public Especie definirEspecie(String idEspecie) {
        if(!idEspecie.equals(null)) {
            if (idEspecie.equals("E")) {
                return new Elefante("E", "Elefante", "elephant.png", 180, 4, 10, "1..6", "Herbívoro");
            }
            if (idEspecie.equals("L")) {
                return new Leao("L", "Leão", "lion.png", 80, 2, 10, "4..6", "Carnívoro");
            }
            if (idEspecie.equals("P")) {
                return new Passaro("P", "Pássaro", "bird.png", 70, 4, 50, "5..6", "Omnívoro");
            }
            if (idEspecie.equals("T")) {
                return new Tartaruga("T", "Tartaruga", "turtle.png", 150, 1, 5, "1..3", "Carnívoro");
            }
            if(idEspecie.equals("Z")) {
                return new Tarzan("Z", "Tarzan", "tarzan.png", 70, 2, 20, "1..6", "Omnívoro");
            }
        }
        return null;
    }
}