package pt.ulusofona.lp2.deisiJungle;

import static junit.framework.TestCase.assertEquals;

public class Test {
    @org.junit.Test
    public void test01MoveCurrentPlayer() {

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "Joana", "T"};
        String[] jogador2 = {"2", "Tomás", "L"};

        String[][] jogadores = {jogador1, jogador2};

        gameManager.createInitialJungle(10, 8, jogadores);

        boolean esperado = true;
        boolean obtido = gameManager.moveCurrentPlayer(3, false);

        assertEquals("Resultado obtido diferente do esperado: ", esperado, obtido);

    }
        /*System.out.println(Arrays.toString(gameManager.getCurrentPlayerInfo()));
        System.out.println(gameManager.moveCurrentPlayer(3, true));
        System.out.println(Arrays.toString(gameManager.getCurrentPlayerInfo()));
        System.out.println(gameManager.moveCurrentPlayer(3, true));
        System.out.println(Arrays.toString(gameManager.getCurrentPlayerInfo()));
        System.out.println(gameManager.moveCurrentPlayer(8, true));
        System.out.println(Arrays.toString(gameManager.getWinnerInfo()));

        System.out.println(Arrays.toString(gameManager.ordenarPosicoes()));
        System.out.println(gameManager.getGameResults());*/

    @org.junit.Test
    public void test02MoveCurrentPlayer(){

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "Joana", "E"};
        String[] jogador2 = {"2", "Tomás", "L"};

        String[][] jogadores = {jogador1, jogador2};

        gameManager.createInitialJungle(10, 8, jogadores);

        boolean esperado = true;
        boolean obtido = gameManager.moveCurrentPlayer(8, true);

        assertEquals("Resultado obtido diferente do esperado: ", esperado, obtido);
    }

    @org.junit.Test
    public void test03MoveCurrentPlayer(){

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"1", "Joana", "Z"};
        String[] jogador2 = {"2", "Tomás", "E"};

        String[][] jogadores = {jogador1, jogador2};

        gameManager.createInitialJungle(10, 8, jogadores);

        boolean esperado = false;
        boolean obtido = gameManager.moveCurrentPlayer(8, false);

        assertEquals("Resultado obtido diferente do esperado: ", esperado, obtido);
    }

    @org.junit.Test
    public void test04MoveCurrentPlayer(){

        GameManager gameManager = new GameManager();

        String[] jogador1 = {"2", "Joana", "P"};
        String[] jogador2 = {"1", "Tomás", "Z"};

        String[][] jogadores = {jogador1, jogador2};

        gameManager.createInitialJungle(10, 8, jogadores);

        boolean esperado = false;
        boolean obtido = gameManager.moveCurrentPlayer(0, false);

        assertEquals("Resultado obtido diferente do esperado: ", esperado, obtido);
    }


    /*@org.junit.Test
    public void testJungle() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "joana", "E"};
        String[] jogador2 = {"2", "Pedro", "L"};
        String[] jogador3 = {"3", "tomas", "P"};
        String[] jogador4 = {"4", "miguel", "Z"};


        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        assertTrue(gamemaneger.createInitialJungle(8, 10, jogadores));

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
    public void testMovePlayer() {

        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};

        String[][] jogadores = {jogador1, jogador2};

        System.out.println(gamemaneger.createInitialJungle(10, 4, jogadores));
        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(1)));

        System.out.println(gamemaneger.moveCurrentPlayer(5, false));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));
        System.out.println(gamemaneger.moveCurrentPlayer(3, false));
        System.out.println(gamemaneger.moveCurrentPlayer(3, false));
        System.out.println(gamemaneger.moveCurrentPlayer(3, false));

        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(10)));
    }


    @org.junit.Test
    public void testGetPlayersIds() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"1", "José", "Z"};
        String[] jogador4 = {"90", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        System.out.println(gamemaneger.createInitialJungle(10, 4, jogadores));

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
    public void testGetSquareInfo() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"89", "José", "Z"};
        String[] jogador4 = {"34", "Ana", "P"};

        Jogador jogadorPos2 = new Jogador(1, "Joana", "L", 2);
        Jogador jogadorPos2_2 = new Jogador(2, "Tomas", "E", 2);


        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        gamemaneger.createInitialJungle(10, 2, jogadores);

        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(1)));
        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
    }*/
}
