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
    protected int[] nrSquaresPossiveis;

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

    public static int getRandomNrSquare(int max, int min) {  // retorna um numero random entre 1 e 6 caso 1_min e 6_max
        return ((int) (Math.random() * (max - min))) + min;
    }

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

    boolean podeMover(int nrSquares) {
        return true;
    }
}