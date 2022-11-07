package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class SquareInfo {

    String imagemAColocar;
    String texto; //Uma descrição textual do que existe nessa posição (nesta fase pode ser apenas “Vazio” ou “Meta”
    ArrayList<String> identificadoresNoQuadrado; //identificadores dos jogadores que estão nessa posição, ex "3,5" jog. 3 e 5
    Boolean meta = false;
    int tamanho;

    public SquareInfo(String imagemAColocar, String texto, ArrayList<String> identificadoresNoQuadrado) {
        this.imagemAColocar = "blank.png";
        this.texto = "Vazio";
        this.identificadoresNoQuadrado = identificadoresNoQuadrado;
        meta = false;
        tamanho = 0;
    }

    public SquareInfo() {
        imagemAColocar = "blank.png";
        texto = "Vazio";
        tamanho = 0;
    }
}
