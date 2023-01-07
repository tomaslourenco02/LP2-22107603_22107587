package pt.ulusofona.lp2.deisiJungle;

public class Passaro extends Especie{

    public Passaro(String identificador, String nome, String imagem, int energiaInicial, int consumoEnergia, int ganhoEnergiaEmDescanso, String velocidade, String tipo) {
        super("P", "Passaro", "bird.png", 70, 4, 50, "5..6", "Omnívoro");
    }

    public static int dado(){
        return Especie.getRandomNrSquare(7,5); //o dado tem os lados 5,6
    }
    public void movimentoPossivel(){
        nrSquaresPossiveis = new int[2];
        nrSquaresPossiveis[0] = 5;
        nrSquaresPossiveis[1] = 6;
    }

    @Override
    boolean podeMover(int nrSquares) {
        if(nrSquares == 0 || nrSquares == 5 || nrSquares == -5 || nrSquares == 6 || nrSquares == -6){
            return true;
        }
        return false;
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
