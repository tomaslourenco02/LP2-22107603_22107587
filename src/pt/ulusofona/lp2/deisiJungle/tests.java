package pt.ulusofona.lp2.deisiJungle;

import org.testng.annotations.Test;

public class tests {

    @Test
    public void testJungle(){

        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "E"};
        String[] jogador2 = {"2", "Pedro", "L"};

        String[][] jogadores = {jogador1, jogador2};


        System.out.println(gamemaneger.createInitialJungle(2,2, jogadores));

    }

}
