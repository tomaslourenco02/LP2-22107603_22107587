package pt.ulusofona.lp2.deisiJungle;

public class Erva extends Alimento{

    //Se ingerido por herbívoros/omnívoros, energia =+ 20un. || ingerido por carnívoros, energia -= 20un.


    public Erva(String identificador, String nome, String imagem) {
        super("e", "Erva", "grass.png");
    }

    @Override
    String[] info() {
        String[] info = new String[1];
        info[0] = "Erva : +- 20 energia";


        return info;
    }

    @Override
    public String toString() {
        return "Erva : +- 20 energia";
    }

}
