package pt.ulusofona.lp2.deisiJungle;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Testes {

    /*@org.junit.Test

    public void testJungle(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"6", "Pedro", "L"};

        String[][] jogadores = {jogador1, jogador2};

        assertTrue(gamemaneger.createInitialJungle(5,2, jogadores));

        System.out.println(Arrays.toString(gamemaneger.getPlayerIds(1)));
    }*/

    @org.junit.Test
    public void testGetPlayersIds(){
        GameManager gamemaneger = new GameManager();
        String[] jogador1 = {"4", "Joao", "E"};
        String[] jogador2 = {"5", "Miguel", "L"};
        String[] jogador3 = {"1", "José", "Z"};
        String[] jogador4 = {"90", "Ana", "P"};

        String[][] jogadores = {jogador1, jogador2,jogador3,jogador4};

        String[] comida1 = {"b", String.valueOf(9)};
        String[] comida2 = {"c", String.valueOf(2)};
        String[] comida3 = {"m", String.valueOf(1)};

        String[][] comida = {comida1, comida2,comida3};

        System.out.println(gamemaneger.createInitialJungle(14,jogadores,null));

        System.out.println(gamemaneger.createInitialJungle(9,jogadores,comida));
        System.out.println(gamemaneger.createInitialJungle(10, jogadores));

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
