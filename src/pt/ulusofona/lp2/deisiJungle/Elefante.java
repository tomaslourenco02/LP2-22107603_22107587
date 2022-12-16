package pt.ulusofona.lp2.deisiJungle;

public class Elefante extends Especie{

    public Elefante(String identificador, String nome, String imagem, int energiaInicial, int consumoEnergia, int ganhoEnergiaEmDescanso, String velocidade, String tipo) {
        super("E", "Elefante", "elephant.png", 180, 4, 10, "1..6", "Herbívoro");
    }

    public static int dado(){
        return Especie.getRandomNrSquare(7,1); //o dado tem os lados 1,2,3,4,5,6
    }

    public void movimentoPossivel(){
        nrSquaresPossiveis = new int[6];
        nrSquaresPossiveis[0] = 1;
        nrSquaresPossiveis[1] = 2;
        nrSquaresPossiveis[2] = 3;
        nrSquaresPossiveis[3] = 4;
        nrSquaresPossiveis[4] = 5;
        nrSquaresPossiveis[5] = 6;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public int getEnergiaInicial() {
        return energiaInicial;
    }

    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    public int getGanhoEnergiaEmDescanso() {
        return ganhoEnergiaEmDescanso;
    }

    public String getVelocidade() {
        return velocidade;
    }

}
