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
    int nrCasasMovimentou = 0;
    int nrAlimentosIngeridos = 0;

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
        infoJogador[3] = Integer.toString(energiaAtual);

        if(especieDoJogador == "E"){
            infoJogador[4] = "1..6";
        }
        if(especieDoJogador == "Z"){
            infoJogador[4] = "1..6";
        }
        if(especieDoJogador == "L"){
            infoJogador[4] = "4..6";
        }
        if(especieDoJogador == "P"){
            infoJogador[4] = "5..6";
        }
        if(especieDoJogador == "E"){
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
