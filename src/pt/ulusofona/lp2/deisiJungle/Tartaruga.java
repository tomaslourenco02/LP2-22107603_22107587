package pt.ulusofona.lp2.deisiJungle;

public class Tartaruga extends Especie{

    public Tartaruga(String identificador, String nome, String imagem, int energiaInicial, int consumoEnergia, int ganhoEnergiaEmDescanso, String velocidade, String tipo) {
        super("T", "Tartaruga", "turtle.png", 150, 1, 5, "1..3", "Carnívoro");
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
