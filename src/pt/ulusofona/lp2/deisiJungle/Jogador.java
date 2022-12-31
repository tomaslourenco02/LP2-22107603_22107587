package pt.ulusofona.lp2.deisiJungle;

import java.util.Objects;

public class Jogador {

    int identificador; //Os IDs dos jogadores podem nÃƒÆ’Ã‚Â£o ser consecutivos, por exemplo J1 = 1, J2 = 3, J3 = 5;
    String nome;       //Apenas poderÃƒÆ’Ã‚Â¡ existir um jogador da espÃƒÆ’Ã‚Â©cie Tarzan
    String especieDoJogador;
    int energiaAtual;
    double energia;
    int posicaoAtual=1;
    boolean aJogar = false;
    int bananasConsumidas = 0;
    boolean ganhou = false;
    int nrCasasMovimentou = 0;
    int nrAlimentosIngeridos = 0;

    Especie especie = new Especie();



    public Jogador(int identificador, String nome, String especieDoJogador) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        this.especie = setEspecie(especieDoJogador);
        this.energiaAtual = especie.energiaInicial;
        posicaoAtual = 1;
    }
     public Jogador(int identificador, String nome, int energiaAtual ,String especieDoJogador, int posicaoAtual) {
        this.identificador = identificador;
        this.nome = nome;
        this.especieDoJogador = especieDoJogador;
        this.especie = setEspecie(especieDoJogador);
        this.energiaAtual = energiaAtual;
        this.posicaoAtual = posicaoAtual;
    }



    public Especie setEspecie(String especieDoJogador) {
        return especie.definirEspecie(especieDoJogador);
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public boolean aJogar() {
        return aJogar;
    }

    public boolean ganhou() {
        return ganhou;
    }

    public String[] infoJogador() {
        String[] infoJogador = new String[5];

        infoJogador[0] = Integer.toString(identificador);
        infoJogador[1] = nome;
        infoJogador[2] = especieDoJogador;
        infoJogador[3] = Integer.toString((int) energiaAtual);

        if(Objects.equals(especieDoJogador, "E")){
            infoJogador[4] = "1..6";
        }
        if(Objects.equals(especieDoJogador, "Z")){
            infoJogador[4] = "1..6";
        }
        if(Objects.equals(especieDoJogador, "L")){
            infoJogador[4] = "4..6";
        }
        if(Objects.equals(especieDoJogador, "P")){
            infoJogador[4] = "5..6";
        }
        if(Objects.equals(especieDoJogador, "T")){
            infoJogador[4] = "1..3";
        }
        return infoJogador;
    }

    public String getNome(){

        return nome;
    }

    public String getEspecieDoJogador(){

        return especieDoJogador;
    }

    public int getEnergiaAtual(){

        return (int) energiaAtual;
    }

    public void setEnergiaAtual(int energiaAtual) {
        this.energiaAtual = energiaAtual;
    }

    public int verificaEnergiaAtual(){

        if(energiaAtual > 200){
            energiaAtual = 200;
        }
        return (int) energiaAtual;
    }
}
