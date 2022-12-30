package pt.ulusofona.lp2.deisiJungle;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Testes {

    @org.junit.Test

    public void testJungle() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "P"};
        String[] jogador2 = {"6", "Pedro", "Z"};

        String[][] jogadores = {jogador1, jogador2};


        System.out.println((gamemaneger.createInitialJungle(26, jogadores, null)));
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
    public void testLoad(){
        GameManager gameManager = new GameManager();


        File saveFile = new File("textoSave");
        System.out.println(gameManager.saveGame(saveFile));

        gameManager.loadGame(saveFile);

        System.out.println(gameManager.loadGame(saveFile));
    }

    @org.junit.Test
    public void testComida() {
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "Z"};
        String[] jogador2 = {"6", "Pedro", "T"};

        String[][] jogadores = {jogador1, jogador2};

        String[] comida1 = {"m", String.valueOf(5)};
        String[] comida2 = {"c", String.valueOf(8)};
        String[] comida3 = {"m", String.valueOf(7)};

        String[][] comida = {comida1, comida2, comida3};

        System.out.println((gamemaneger.createInitialJungle(10, jogadores, comida)));
        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(1)));

        File saveFile = new File("textoSave");
        System.out.println(gamemaneger.saveGame(saveFile));
        System.out.println(gamemaneger.moveCurrentPlayer(4, true));
        System.out.println(Arrays.toString(gamemaneger.getSquareInfo(0)));
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(4)));
        System.out.println(gamemaneger.moveCurrentPlayer(1, true));
        System.out.println(Arrays.toString(gamemaneger.getPlayerInfo(6)));
        System.out.println(Arrays.toString(gamemaneger.getWinnerInfo()));


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

        System.out.println(gamemaneger.createInitialJungle(9, jogadores, comida));
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
