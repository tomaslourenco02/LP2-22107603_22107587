package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class SquareInfo {

    String imagemAColocar;
    String texto; //Uma descrição textual do que existe nessa posição (nesta fase pode ser apenas “Vazio” ou “Meta”
    ArrayList<Integer> identificadoresNoQuadrado = new ArrayList<>(); //identificadores dos jogadores que estão nessa posição, ex "3,5" jog. 3 e 5
    Boolean meta = false;

    public SquareInfo(Boolean meta) {
        this.meta = meta;
    }

    int tamanho;

    public SquareInfo(String imagemAColocar, String texto, ArrayList<String> identificadoresNoQuadrado) {
        this.imagemAColocar = "blank.png";
        this.texto = "Vazio";
        this.identificadoresNoQuadrado = new ArrayList<>();
        meta = false;
    }

    public SquareInfo() {
        imagemAColocar = "blank.png";
        texto = "Vazio";
        identificadoresNoQuadrado = new ArrayList<>();
    }
}
