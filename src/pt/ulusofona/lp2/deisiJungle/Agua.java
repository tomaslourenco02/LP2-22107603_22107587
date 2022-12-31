package pt.ulusofona.lp2.deisiJungle;

public class Agua extends Alimento{

    //Se ingerido por carnívoros ou herbívoros, energia +=15 un || omnivoros, energia =+ 20% (energia)

    public Agua(String identificador, String nome, String imagem) {
        super("a", "Agua", "water.png");
    }

    @Override
    String info() {
        String info = "Agua : + 15U|20% energia";

        return info;

    }

    @Override
    void energiaFornecida(Jogador jogador, int jogadasFeitas) {

    }

    @Override
    public String toString() {
        return "Apanhou Agua";
    }

}

