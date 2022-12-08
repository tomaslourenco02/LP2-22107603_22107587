package pt.ulusofona.lp2.deisiJungle;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Testes {
    @org.junit.Test

    public void testJungle(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"6", "Pedro", "L"};

        String[][] jogadores = {jogador1, jogador2};

        assertTrue(gamemaneger.createInitialJungle(5,2, jogadores));

        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
    }

    @org.junit.Test
    public void testGetPlayersIds(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"1", "José", "Z"};
        String[] jogador4 = {"90", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2,jogador3,jogador4};

        System.out.println(gamemaneger.createInitialJungle(8,2, jogadores));

        System.out.println(gamemaneger.moveCurrentPlayer(7, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));

        System.out.println(gamemaneger.moveCurrentPlayer(3, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));

        System.out.println("Quem ganhou " + Arrays.toString(gamemaneger.getWinnerInfo()) + "\n");

        System.out.println(gamemaneger.moveCurrentPlayer(6, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));

        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println("Quem ganhou "  + Arrays.toString(gamemaneger.getWinnerInfo()) + "\n");

        /*
        System.out.println("1 jogada \n");

        System.out.println("quem está a ganhar:");
        System.out.println(gamemaneger.winnerPlayer() );
        System.out.println(Arrays.toString(gamemaneger.getWinnerInfo())+ "\n"); */

        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));

        System.out.println(gamemaneger.moveCurrentPlayer(6, false));


        System.out.println("Quem ganhou" + Arrays.toString(gamemaneger.getWinnerInfo()) + "\n");


    }

    @org.junit.Test
    public void testGetSquareInfo(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"89", "José", "Z"};
        String[] jogador4 = {"34", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2,jogador3,jogador4};

        gamemaneger.createInitialJungle(10,2, jogadores);

        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(1)));
        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
    }
    }
