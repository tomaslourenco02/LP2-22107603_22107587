package pt.ulusofona.lp2.deisiJungle;

public class CogumelosMagicos extends Alimento{

    //Todos os animais podem ingerir
    //Como são mágicos, o seu comportamento varia de cogumelo para cogumelo e de jogada para jogada.
    //Cada cogumelo tem associado um número (N) entre 10 e 50, que é gerado aleatoriamente na sua criação
    //Se comerem o cogumelo nas jogadas pares, os animais aumentam em N% a sua energia
    //Se comerem o cogumelo nas jogadas ímpares, ele torna-se venenoso e reduzem em N% a sua energia

    int nrAleatorio = getRandomNrSquare(51,10);



    public CogumelosMagicos(String identificador, String nome, String imagem) {
        super("m", "Cogumelos Magicos", "mushroom.png");
    }

    public static int getRandomNrSquare(int max, int min) {  // retorna um numero random entre 1 e 6 caso 1_min e 6_max
        return ((int) (Math.random() * (max - min))) + min;
    }

    @Override
    String info() {
        String info = "Cogumelo Magico : +- " + nrAleatorio + "% energia";

        return info;
    }


    @Override
    public String toString() {
        return "Cogumelo Magico : +- " + nrAleatorio + "% energia" ;
    }
}
