package pt.ulusofona.lp2.deisiJungle;

public class Agua extends Alimento{

    //Se ingerido por carnívoros ou herbívoros, energia +=15 un || omnivoros, energia =+ 20% (energia)

    public Agua(String identificador, String nome, String imagem, String foodPos) {
        super("a", "Agua", "water.png", foodPos);
    }

    @Override
    String info() {
        String info = "Agua : + 15U|20% energia";

        return info;

    }

    @Override
    public String toString() {
        return "Agua : + 15U|20% energia";
    }

}

