package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Testes {

    @org.junit.Test
    public void testGetPlayerInfo() throws InvalidInitialJungleException {
        GameManager manager = new GameManager();
        // Add some players to the manager's list of players

        String[] jogador1 = {"4", "Joao", "P"};
        String[] jogador2 = {"6", "Pedro", "Z"};
        String[][] jogadores = {jogador1, jogador2};


        manager.createInitialJungle(30, jogadores, null);

        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("Joao");
        ArrayList<String> nomeQueNaoExiste = new ArrayList<>();
        nomes.add("Leo");
        ArrayList<String> nomesVazio = new ArrayList<>();


        String result1 = FunctionsKt.getPlayerInfo(manager, nomes);
        assertEquals(result1, "4 | Joao | Passaro | 70 | 1");

        String result3 = FunctionsKt.getPlayerInfo(manager, nomeQueNaoExiste);
        assertEquals(result3, "Inexistent player");

        String result4 = FunctionsKt.getPlayerInfo(manager, nomesVazio);
        assertEquals(result4 , "Inexistent player");
    }
    @org.junit.Test

    public void testGetPlayersBySpecies() throws InvalidInitialJungleException {
        GameManager manager = new GameManager();

        String[] jogador1 = {"4", "Joao", "T"};
        String[] jogador2 = {"6", "Pedro", "T"};
        String[] jogador3 = {"1", "Miguel", "L"};
        String[][] jogadores = {jogador1, jogador2, jogador3};

        manager.createInitialJungle(30, jogadores, null);

        ArrayList<String> especie = new ArrayList<>();
        especie.add("T");
        ArrayList<String> especie2 = new ArrayList<>();
        especie2.add("L");
        ArrayList<String> especieQueNaoExiste = new ArrayList<>();
        especieQueNaoExiste.add("E");
        ArrayList<String> especieVazio = new ArrayList<>();


        String result1 = FunctionsKt.getPlayersBySpecies(manager, especie);
        assertEquals(result1, "Joao,Pedro");

        String result2 = FunctionsKt.getPlayersBySpecies(manager, especie2);
        assertEquals(result2, "Miguel");

        String result3 = FunctionsKt.getPlayersBySpecies(manager, especieQueNaoExiste);
        assertEquals(result3, "");

        String result4 = FunctionsKt.getPlayersBySpecies(manager, especieVazio);
        assertEquals(result4, "");
    }

    @org.junit.Test

    public void testJungle() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "P"};
        String[] jogador2 = {"6", "Pedro", "Z"};

        String[][] jogadores = {jogador1, jogador2};


        System.out.println(gamemaneger.moveCurrentPlayer(6, false));      //6*4=24   70-24=46
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
        System.out.println(gamemaneger.moveCurrentPlayer(2, false));      //2*2=4     70-4=66
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(6)));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));      //6*4=24   46-24=22
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
        System.out.println(gamemaneger.moveCurrentPlayer(4, false));     //4*2=8     66-8=58
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(6)));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));     //5*4=20    22-20=2
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
        System.out.println(gamemaneger.moveCurrentPlayer(4, false));     //4*2=8     58-8=50
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(6)));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));     //5*4=20    20-20=0 NO Energy
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
        System.out.println(gamemaneger.moveCurrentPlayer(4, false));     //4*2=8     58-8=50
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(6)));


        System.out.println(gamemaneger.moveCurrentPlayer(5, false));     //5*4=20    20-20=0 NO Energy
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));     //4*2=8     58-8=50
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(6)));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));     //5*4=20    20-20=0 NO Energy
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));     //4*2=8     58-8=50
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(6)));
        System.out.println(gamemaneger.moveCurrentPlayer(5, false));     //5*4=20    20-20=0 NO Energy
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
        System.out.println(gamemaneger.moveCurrentPlayer(6, false));     //4*2=8     58-8=50
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(6)));

        System.out.println(Arrays.toString(gamemaneger.getWinnerInfo()));

    }

    @org.junit.Test
    public void testLoad() {

        GameManager gameManager = new GameManager();
        String[] jogador1 = {"1", "Joao", "Z"};
        String[] jogador2 = {"2", "Pedro", "T"};


        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"c", String.valueOf(5)};

        String[][] comida = {comida1};

        try {
            gameManager.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Arrays.toString(gameManager.getPlayerIds(1)));
        System.out.println(gameManager.moveCurrentPlayer(4, true));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(4)));

        File saveFile = new File("textoSave");
        System.out.println(gameManager.saveGame(saveFile));
        System.out.println(gameManager.loadGame(saveFile));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(1)));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(4)));
        System.out.println(gameManager.moveCurrentPlayer(3, true));
        System.out.println(Arrays.toString(gameManager.getPlayerIds(4)));


    }

    @org.junit.Test
    public void testJogo1() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "Z"};
        String[] jogador2 = {"3", "Pedro", "T"};
        String[] jogador3 = {"2", "Tomas", "L"};
        String[] jogador4 = {"7", "Joana", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[] comida1 = {"b", String.valueOf(5)};
        String[] comida2 = {"c", String.valueOf(8)};
        String[] comida6 = {"c", String.valueOf(6)};
        String[] comida3 = {"m", String.valueOf(7)};
        String[] comida4 = {"a", String.valueOf(17)};
        String[] comida5 = {"e", String.valueOf(23)};

        String[][] comida = {comida1, comida2, comida3, comida4, comida5, comida6};

        try {
            gamemaneger.createInitialJungle(30, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        gamemaneger.moveCurrentPlayer(7, false);
        gamemaneger.moveCurrentPlayer(4, false);
        gamemaneger.moveCurrentPlayer(2, false);
        gamemaneger.moveCurrentPlayer(5, false);

        assertEquals(Arrays.toString(gamemaneger.getSquareInfo(3)), "[blank.png, Vazio, 3]");

        gamemaneger.moveCurrentPlayer(0, false);
        gamemaneger.moveCurrentPlayer(5, false);
        gamemaneger.moveCurrentPlayer(3, false);
        gamemaneger.moveCurrentPlayer(6, false);

        assertEquals(Arrays.toString(gamemaneger.getSquareInfo(5)), "[bananas.png, Bananas : 2 : + 40 energia, ]");
        assertEquals(Arrays.toString(gamemaneger.getCurrentPlayerInfo()), "[1, Joao, Z, 90, 1..6]");

        gamemaneger.moveCurrentPlayer(1, false);
        gamemaneger.moveCurrentPlayer(0, false);
        gamemaneger.moveCurrentPlayer(1, false);
        gamemaneger.moveCurrentPlayer(6, false);

        gamemaneger.moveCurrentPlayer(1, false);
        gamemaneger.moveCurrentPlayer(0, false);
        gamemaneger.moveCurrentPlayer(0, false);
        gamemaneger.moveCurrentPlayer(6, false);

        assertEquals(Arrays.toString(gamemaneger.getWinnerInfo()), "null");

        gamemaneger.moveCurrentPlayer(0, false);
        gamemaneger.moveCurrentPlayer(0, false);
        gamemaneger.moveCurrentPlayer(0, false);
        gamemaneger.moveCurrentPlayer(6, false);

        assertEquals(gamemaneger.moveCurrentPlayer(6, false), new MovementResult(MovementResultCode.VALID_MOVEMENT, null));

        assertEquals(Arrays.toString(gamemaneger.getWinnerInfo()), "[7, Joana, P, 4]");
    }

    @org.junit.Test
    public void testGetPlayersIds() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"1", "José", "E"};
        String[] jogador4 = {"90", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(2)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(9, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerEnergyInfo(0)));
        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(2)));

        System.out.println(gamemaneger.moveCurrentPlayer(2, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(2, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(5, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerEnergyInfo(1)));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(gamemaneger.moveCurrentPlayer(3, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerEnergyInfo(2)));

        System.out.println(gamemaneger.moveCurrentPlayer(1, true));
        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));


    }

    @org.junit.Test
    public void testGetSpecies() {
        GameManager gamemaneger = new GameManager();
        assertEquals(Arrays.deepToString(gamemaneger.getSpecies()), "[[E, Elefante, elephant.png, 180, 4, 10, 1..6], [L, Leão, lion.png, 80, 2, 10, 4..6], " +
                "[T, Tartaruga, turtle.png, 150, 1, 5, 1..3], " + "[P, Pássaro, bird.png, 70, 4, 50, 5..6]," +
                " [Z, Tarzan, tarzan.png, 70, 2, 20, 1..6]]");
    }

    @org.junit.Test
    public void testGetFoods() {
        GameManager gamemaneger = new GameManager();
        assertEquals(Arrays.deepToString(gamemaneger.getFoodTypes()), "[[e, Erva, grass.png]," +
                " [a, Agua, water.png], [b, Cacho de bananas, bananas.png], " +
                "[c, Carne, meat.png], [m, Cogumelos magicos, mushroom.png]]");
    }

    @org.junit.Test
    public void testWinnerAvancado() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"1", "José", "E"};
        String[] jogador4 = {"90", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(2)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        gamemaneger.moveCurrentPlayer(7, true);
        assertEquals(Arrays.toString(gamemaneger.getWinnerInfo()), "null");
        assertEquals(Arrays.toString(gamemaneger.getCurrentPlayerInfo()), "[4, Joao, E, 180, 1..6]");
        gamemaneger.moveCurrentPlayer(1, true);
        gamemaneger.moveCurrentPlayer(1, true);
        assertEquals(gamemaneger.moveCurrentPlayer(1, true), new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne"));
        gamemaneger.moveCurrentPlayer(1, true);
        gamemaneger.moveCurrentPlayer(1, true);
        assertEquals(Arrays.toString(gamemaneger.getWinnerInfo()), "[4, Joao, E, 176]");


    }

    @org.junit.Test
    public void test2Tarzans() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "Z"};
        String[] jogador2 = {"5", "Miguel", "Z"};
        String[] jogador3 = {"1", "José", "E"};
        String[] jogador4 = {"90", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(2)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.Test
    public void testMoveWithByPassFalse() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "L"};
        String[] jogador2 = {"5", "Miguel", "Z"};
        String[] jogador3 = {"1", "José", "E"};
        String[] jogador4 = {"90", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};


        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        assertEquals(gamemaneger.moveCurrentPlayer(7, false), new MovementResult(MovementResultCode.INVALID_MOVEMENT, null));


    }

    @org.junit.Test
    public void testRecuarAteAPrimeiraCasa() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};
        String[] jogador2 = {"2", "Miguel", "Z"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        gamemaneger.moveCurrentPlayer(3, true);
        assertEquals(gamemaneger.moveCurrentPlayer(3, true), new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne"));

        gamemaneger.moveCurrentPlayer(2, true);
        assertEquals(gamemaneger.moveCurrentPlayer(2, true), new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Bananas"));

        gamemaneger.moveCurrentPlayer(-4, true); //INVALID
        assertEquals(gamemaneger.moveCurrentPlayer(-4, true), new MovementResult(MovementResultCode.VALID_MOVEMENT, null));
    }

    @org.junit.Test
    public void testCriarJungleComPoucasCasas() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};
        String[] jogador2 = {"2", "Miguel", "Z"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.Test
    public void testGetPlayerEnergyInfo() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};
        String[] jogador2 = {"2", "Miguel", "Z"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"b", String.valueOf(3)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"c", String.valueOf(5)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(50, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        gamemaneger.moveCurrentPlayer(3, true);
        assertEquals(gamemaneger.moveCurrentPlayer(3, true), new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne"));
        gamemaneger.moveCurrentPlayer(2, true);
        assertEquals(gamemaneger.moveCurrentPlayer(2, true), new MovementResult(MovementResultCode.VALID_MOVEMENT, null));
        gamemaneger.moveCurrentPlayer(3, true);
        assertEquals(gamemaneger.moveCurrentPlayer(3, true), new MovementResult(MovementResultCode.VALID_MOVEMENT, null));
        gamemaneger.moveCurrentPlayer(2, true);
        assertEquals(gamemaneger.moveCurrentPlayer(2, true), new MovementResult(MovementResultCode.VALID_MOVEMENT, null));

        Arrays.toString(gamemaneger.getCurrentPlayerEnergyInfo(1));
        assertEquals(Arrays.toString(gamemaneger.getCurrentPlayerEnergyInfo(2)), "[4, 10]");


    }

    @org.junit.Test
    public void testGetPlayersInfo() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};
        String[] jogador2 = {"2", "Miguel", "Z"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        System.out.println(gamemaneger.moveCurrentPlayer(3, true));
        System.out.println(gamemaneger.moveCurrentPlayer(2, true));

        System.out.println(Arrays.deepToString(gamemaneger.getPlayersInfo()));

    }

    @org.junit.Test
    public void testGetCurrentPlayerInfo() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};
        String[] jogador2 = {"2", "Miguel", "Z"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        System.out.println(gamemaneger.moveCurrentPlayer(3, true));
        System.out.println(gamemaneger.moveCurrentPlayer(2, true));

        System.out.println(Arrays.toString(gamemaneger.getCurrentPlayerInfo()));
    }

    @org.junit.Test
    public void testGetSquareInfo() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};
        String[] jogador2 = {"2", "Miguel", "Z"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        System.out.println(gamemaneger.moveCurrentPlayer(3, true));
        System.out.println(gamemaneger.moveCurrentPlayer(2, true));

        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(6)));
    }

    @org.junit.Test
    public void testGetPlayerIds() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};
        String[] jogador2 = {"2", "Miguel", "Z"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores, comida);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
        System.out.println(gamemaneger.moveCurrentPlayer(3, true));
        System.out.println(gamemaneger.moveCurrentPlayer(3, true));

        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(4)));
    }

    @org.junit.Test
    public void test_comandos() {
        GameManager gameManager = new GameManager();
        ArrayList<String> args = new ArrayList<>();
        args.add("GET_PLAYER_INFO");
        args.add("arg2");
        args.add("arg3");
        String result1 = FunctionsKt.getComando(gameManager, args);
        String result2 = FunctionsKt.postComando(gameManager, args);

        System.out.println(result1);
        System.out.println(result2);

    }

    @org.junit.Test
    public void testExceptionValido() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};

        String[][] jogadores = {jogador1};

        String[] comida1 = {"b", String.valueOf(6)};
        String[] comida2 = {"c", String.valueOf(4)};
        String[] comida3 = {"a", String.valueOf(3)};

        String[][] comida = {comida1, comida2, comida3};

        try {
            gamemaneger.createInitialJungle(10, jogadores);
        } catch (InvalidInitialJungleException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.Test
    public void testExceptionInvalido() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"1", "Joao", "L"};
        String[] jogador2 = {"2", "Pedro", "L"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"b", String.valueOf(6)};


        String[][] comida = {comida1};

        Assert.assertTrue(gamemaneger.verificaJogadores(jogadores));
    }
}

    /*@org.junit.Test
    public void testGetSquareInfo() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"89", "José", "Z"};
        String[] jogador4 = {"34", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2, jogador3, jogador4};

        gamemaneger.createInitialJungle(10, 2, jogadores);

        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(1)));
        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
    }
*/
