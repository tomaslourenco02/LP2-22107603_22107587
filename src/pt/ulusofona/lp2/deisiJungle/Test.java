package pt.ulusofona.lp2.deisiJungle;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Test {



    @org.junit.Test
    public void testMove(){

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "joana", "E"};
        String[] jogador5 = {"9", "ju", "E"};
        String[] jogador3 = {"2", "tom", "E"};
        String[] jogador2 = {"8", "Pedro", "L"};

        String[][] jogadores = {jogador1, jogador2, jogador5, jogador3};

        assertTrue(gameManager.createInitialJungle(8,10, jogadores));

        System.out.println(Arrays.toString(gameManager.getCurrentPlayerInfo()));
        System.out.println(gameManager.moveCurrentPlayer(3, true));
        System.out.println(Arrays.toString(gameManager.getCurrentPlayerInfo()));
        System.out.println(gameManager.moveCurrentPlayer(3, true));
        System.out.println(Arrays.toString(gameManager.getCurrentPlayerInfo()));
        System.out.println(gameManager.moveCurrentPlayer(8, true));
        System.out.println(Arrays.toString(gameManager.getWinnerInfo()));




    }


    @org.junit.Test
    public void testJungle(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "joana", "E"};
        String[] jogador2 = {"2", "Pedro", "L"};
        String[] jogador3 = {"3", "tomas", "P"};
        String[] jogador4 = {"4", "miguel", "Z"};


        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        assertTrue(gamemaneger.createInitialJungle(8,10, jogadores));

        System.out.println(gamemaneger.moveCurrentPlayer(2, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(7, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(2, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));

        System.out.println(Arrays.toString(gamemaneger.getWinnerInfo()));

        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(5)));
        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(3)));
    }

    @org.junit.Test
    public void testMovePlayer(){

        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};

        String[][] jogadores = {jogador1, jogador2};

        System.out.println(gamemaneger.createInitialJungle(10,4, jogadores));
        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(1)));

        System.out.println(gamemaneger.moveCurrentPlayer(5, false));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));
        System.out.println(gamemaneger.moveCurrentPlayer(3, false));
        System.out.println(gamemaneger.moveCurrentPlayer(3, false));
        System.out.println(gamemaneger.moveCurrentPlayer(3, false));

        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(10)));
    }


    @org.junit.Test
    public void testGetPlayersIds(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"1", "José", "Z"};
        String[] jogador4 = {"90", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2,jogador3,jogador4};

        System.out.println(gamemaneger.createInitialJungle(10,4, jogadores));

        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(2, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));

        System.out.println(Arrays.toString(gamemaneger.getWinnerInfo()));

        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));

        System.out.println(gamemaneger.moveCurrentPlayer(6, false));
        System.out.println(Arrays.toString(gamemaneger.getWinnerInfo()));




    }

    @org.junit.Test
    public void testGetSquareInfo(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"89", "José", "Z"};
        String[] jogador4 = {"34", "Ana", "P"};

        Jogador jogadorPos2 = new Jogador(1, "Joana", "L", 2);
        Jogador jogadorPos2_2 = new Jogador(2, "Tomas", "E", 2);




        String[][] jogadores = {jogador1, jogador2,jogador3,jogador4};

        gamemaneger.createInitialJungle(10,2, jogadores);

        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(1)));
        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
    }
}
