package pt.ulusofona.lp2.deisiJungle;

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

    public void testJungle() throws InvalidInitialJungleException {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "P"};
        String[] jogador2 = {"6", "Pedro", "Z"};

        String[][] jogadores = {jogador1, jogador2};


        gamemaneger.createInitialJungle(20, jogadores);
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
    public void testLoad() throws InvalidInitialJungleException {

        GameManager gameManager = new GameManager();
        String[] jogador1 = {"1", "Joao", "Z"};
        String[] jogador2 = {"2", "Pedro", "T"};


        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"c", String.valueOf(5)};

        String[][] comida = {comida1};

        gameManager.createInitialJungle(30, jogadores);
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
    public void testGetPlayersIds() throws InvalidInitialJungleException {
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

        gamemaneger.createInitialJungle(30, jogadores);
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
    public void testComidaIngerida() throws InvalidInitialJungleException {

        GameManager gameManager = new GameManager();
        String[] jogador1 = {"1", "Joao", "Z"};
        String[] jogador2 = {"2", "Pedro", "T"};


        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"c", String.valueOf(5)};
        String[] comida2 = {"e", String.valueOf(6)};
        String[] comida3 = {"m", String.valueOf(7)};
        String[] comida4 = {"c", String.valueOf(8)};
        String[] comida5 = {"e", String.valueOf(9)};
        String[] comida6 = {"e", String.valueOf(10)};


        String[][] comida = {comida1, comida2, comida3, comida4, comida5, comida6};

        gameManager.createInitialJungle(30, jogadores, comida);
        System.out.println(gameManager.moveCurrentPlayer(4, true));
        System.out.println(gameManager.moveCurrentPlayer(0, true));
        System.out.println(gameManager.moveCurrentPlayer(1, true));
        System.out.println(gameManager.moveCurrentPlayer(0, true));
        System.out.println(gameManager.moveCurrentPlayer(1, true));
        System.out.println(gameManager.moveCurrentPlayer(0, true));
        System.out.println(gameManager.moveCurrentPlayer(1, true));
        System.out.println(gameManager.moveCurrentPlayer(0, true));
        System.out.println(gameManager.moveCurrentPlayer(1, true));
        System.out.println(gameManager.moveCurrentPlayer(0, true));
        System.out.println(gameManager.moveCurrentPlayer(1, true));
        System.out.println(gameManager.alimentosIngeridos);


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
