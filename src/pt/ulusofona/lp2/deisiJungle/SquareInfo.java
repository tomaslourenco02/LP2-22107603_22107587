package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class SquareInfo {

    String imagemAColocar;
    String texto; //Uma descrição textual do que existe nessa posição (nesta fase pode ser apenas “Vazio” ou “Meta”
    ArrayList<Integer> identificadoresNoQuadrado = new ArrayList<>(); //identificadores dos jogadores que estão nessa posição, ex "3,5" jog. 3 e 5
    Boolean meta = false;
    String identificadoresAlimentosNoQuadrado;

    int bananas;

    CogumelosMagicos cogumelo = new CogumelosMagicos("m", "Cogumelos Magicos", "mushroom.png", "");

    public SquareInfo(Boolean meta) {
        this.meta = meta;
    }

    public SquareInfo(String imagemAColocar, String texto, ArrayList<String> identificadoresNoQuadrado) {
        this.imagemAColocar = "blank.png";
        this.texto = "Vazio";
        this.identificadoresNoQuadrado = new ArrayList<>();
        meta = false;
    }

    public String getImagemAColocar() {

        return imagemAColocar;
    }

    public String getTexto() {

        return texto;
    }

    public ArrayList<Integer> getIdentificadoresNoQuadrado() {

        return identificadoresNoQuadrado;
    }

    public Boolean getMeta() {
        return meta;
    }

    public SquareInfo() {
        imagemAColocar = "blank.png";
        texto = "Vazio";
        identificadoresNoQuadrado = new ArrayList<>();
    }
}
