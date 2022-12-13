package pt.ulusofona.lp2.deisiJungle;

public class Tarzan extends Especie{

    public Tarzan(String identificador, String nome, String imagem, int energiaInicial, int consumoEnergia, int ganhoEnergiaEmDescanso, String velocidade, String tipo) {
        super("Z", "Tarzan", "tarzan.png", 70, 2, 20, "1..6", "Omnívoro");
    }

    public static int dado(){
        return Especie.getRandomNrSquare(7,1); //o dado tem os lados 1,2,3,4,5,6
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
