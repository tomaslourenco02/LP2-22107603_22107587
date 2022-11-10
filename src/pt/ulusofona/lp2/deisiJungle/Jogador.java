package pt.ulusofona.lp2.deisiJungle;

public class Jogador {

    int identificador; //Os IDs dos jogadores podem nÃ£o ser consecutivos, por exemplo J1 = 1, J2 = 3, J3 = 5;
    String nome;       //Apenas poderÃ¡ existir um jogador da espÃ©cie Tarzan
    String especieDoJogador;
    int energiaInicial;
    int energiaAtual;
    int posicaoAtual = 1;
    boolean aJogar = false;
    boolean ganhou = false;

    public Jogador(int identificador, String nome, String especieDoJogador) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        posicaoAtual = 1;
    }

    public Jogador(int identificador, String nome, String especieDoJogador, int energiaInicial, int energiaAtual) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        this.energiaInicial = energiaInicial;
        this.energiaAtual = energiaInicial;
        posicaoAtual = 0;
    }

    public Jogador(int identificador, String nome, String especieDoJogador, int energiaAtual) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        this.energiaAtual = energiaAtual;
    }

    public Jogador(int identificador, String nome, int posicaoAtual) {
        this.identificador = identificador;
        this.nome = nome;
        this.posicaoAtual = posicaoAtual;
    }

    public Jogador() {
        identificador = 0;
        nome = null;
        especieDoJogador = null;
        energiaInicial = 0;
        posicaoAtual = 1;
    }

    public String[] infoJogador() {
        String[] infoJogador = new String[4];

        infoJogador[0] = Integer.toString(identificador);
        infoJogador[1] = nome;
        infoJogador[2] = especieDoJogador;
        infoJogador[3] = Integer.toString(energiaAtual);

        return infoJogador;
    }
}




