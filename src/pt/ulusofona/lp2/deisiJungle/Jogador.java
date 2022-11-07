package pt.ulusofona.lp2.deisiJungle;

public class Jogador {

    int identificador; //Os IDs dos jogadores podem não ser consecutivos, por exemplo J1 = 1, J2 = 3, J3 = 5;
    String nome;       //Apenas poderá existir um jogador da espécie Tarzan
    String especieDoJogador;
    int energiaInicial;
    int energiaAtual;
    int posicaoAtual;

    public Jogador(int identificador, String nome, String especieDoJogador) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
    }

    public Jogador(int identificador, String nome, String especieDoJogador, int energiaInicial, int energiaAtual) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        this.energiaInicial = energiaInicial;
        this.energiaAtual = energiaAtual;
    }

    public Jogador() {
        identificador = 0;
        nome = null;
        especieDoJogador = null;
        energiaInicial = 0;
        posicaoAtual = 1;
    }
}
