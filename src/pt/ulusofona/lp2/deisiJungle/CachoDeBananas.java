package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class CachoDeBananas extends Alimento{

    //Pode ser ingerido por qualquer animal
    //Aumenta a energia em 40 unidades
    //Só existem 3 bananas no cacho

    ArrayList<Integer> idsJogadores = new ArrayList<>(); //contém os Ids de quem ja comeu banana

    int nrBananas;

    public CachoDeBananas(String identificador, String nome, String imagem) {
        super("b", "Cacho de bananas", "bananas.png");
    }

    @Override
    String info() {
        String info ="Bananas : " + nrBananas + " : + 40 energia";

        return info;
    }

    @Override
    void energiaFornecida(Jogador jogador, int jogadasFeitas) {

    }

    @Override
    public String toString() {
        return "Bananas : " + nrBananas + " : 40 energia";

    }
}
