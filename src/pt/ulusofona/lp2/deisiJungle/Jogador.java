package pt.ulusofona.lp2.deisiJungle;

public class Jogador {

    int identificador; //Os IDs dos jogadores podem não ser consecutivos, por exemplo J1 = 1, J2 = 3, J3 = 5;
    String nome;       //Apenas poderá existir um jogador da espécie Tarzan
    String especieDoJogador;
    int energiaInicial;
    int energiaAtual;
    int posicaoAtual = 0;
    boolean aJogar = false;
    boolean ganhou = false;

    public Jogador(int identificador, String nome, String especieDoJogador) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        posicaoAtual = 0;
    }

    public Jogador(int identificador, String nome, String especieDoJogador, int energiaInicial, int energiaAtual) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        this.energiaInicial = energiaInicial;
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
}
