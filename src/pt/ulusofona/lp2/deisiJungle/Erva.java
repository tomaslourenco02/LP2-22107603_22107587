package pt.ulusofona.lp2.deisiJungle;

public class Erva extends Alimento{
    public Erva(String identificador, String nome, String imagem) {
        super("e", "Erva", "grass.png");
    }

    @Override
    public String toString() {
        return "Erva : +- 20 energia";
    }
}
