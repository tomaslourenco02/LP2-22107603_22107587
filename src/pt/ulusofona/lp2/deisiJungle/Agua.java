package pt.ulusofona.lp2.deisiJungle;

public class Agua extends Alimento{

    //Se ingerido por carnívoros ou herbívoros, energia +=15 un || omnivoros, energia =+ 20% (energia)

    public Agua(String identificador, String nome, String imagem) {
        super("a", "Agua", "water.png");
    }

    @Override
    public String toString() {
        return "Agua : + 10U|20% energia";
    }
}
