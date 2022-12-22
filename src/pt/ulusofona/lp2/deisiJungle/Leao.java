package pt.ulusofona.lp2.deisiJungle;

public class Leao extends Especie{

    public Leao(String identificador, String nome, String imagem, int energiaInicial, int consumoEnergia, int ganhoEnergiaEmDescanso, String velocidade, String tipo) {
        super("L", "Leão", "lion.png", 80, 2, 10, "4..6", "Carnívoro");
    }

    public static int dado(){
        return Especie.getRandomNrSquare(7,4); //o dado tem os lados 4,5,6
    }

    public void movimentoPossivel(){
        nrSquaresPossiveis = new int[3];
        nrSquaresPossiveis[0] = 4;
        nrSquaresPossiveis[1] = 5;
        nrSquaresPossiveis[2] = 6;
    }

    @Override
    boolean podeMover(int nrSquares) {
        if(nrSquares == 0){
            return true;
        }
        if (nrSquares < -6 || nrSquares > -4 || nrSquares < 4 || nrSquares > 6) {
                return false;
            }
        return true;
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
