package pt.ulusofona.lp2.deisiJungle;

public class Jogador {

    int identificador; //Os IDs dos jogadores podem nÃƒÆ’Ã‚Â£o ser consecutivos, por exemplo J1 = 1, J2 = 3, J3 = 5;
    String nome;       //Apenas poderÃƒÆ’Ã‚Â¡ existir um jogador da espÃƒÆ’Ã‚Â©cie Tarzan
    String especieDoJogador;
    int energiaInicial;
    int energiaAtual;
    int posicaoAtual = 1;
    boolean aJogar = false;
    boolean ganhou = false;


    public Jogador(int identificador, String nome, String especieDoJogador, int energiaAtual) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        this.energiaAtual = energiaAtual;
        posicaoAtual = 1;
    }


    public int getIdentificador() {
        return identificador;
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public boolean AJogar() {
        return aJogar;
    }

    public boolean Ganhou() {
        return ganhou;
    }

    public String[] infoJogador() {
        String[] infoJogador = new String[4];

        infoJogador[0] = Integer.toString(identificador);
        infoJogador[1] = nome;
        infoJogador[2] = especieDoJogador;
        infoJogador[3] = Integer.toString(energiaAtual);

        return infoJogador;
    }

    public String getNome(){

        return nome;
    }

    public String getEspecieDoJogador(){

        return especieDoJogador;
    }

    public int getEnergiaAtual(){

        return energiaAtual;
    }

    public int getEnergiaInicial(){

        return energiaInicial;
    }

    public int verificaEnergiaAtual(){

        if(energiaAtual > 200){

            energiaAtual = 200;
        }
        return energiaAtual;
    }
}
