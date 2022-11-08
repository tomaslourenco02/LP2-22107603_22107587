package pt.ulusofona.lp2.deisiJungle;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Test {
    @org.junit.Test

    public void testJungle(){

        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Pedro", "L"};

        String[][] jogadores = {jogador1, jogador2};

        assertTrue(gamemaneger.createInitialJungle(5,2, jogadores));

        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(1)));
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
    }

    @org.junit.Test
    public void testGetPlayersIds(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"89", "José", "Z"};
        String[] jogador4 = {"34", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2,jogador3,jogador4};

        gamemaneger.createInitialJungle(10,2, jogadores);

        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
    }

}
