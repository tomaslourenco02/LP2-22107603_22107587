package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;

public class GameManager {

    public static void main(String[] args) {

    }

    public String[][] getSpecies() {

        String[][] especies = new String[20][];



        return null;
    }

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {

        return true;
    }

    public int[] getPlayerIds(int squareNr) {

        int nrJogadores = 0;

        int[] identificadoresJogadores = new int[0];

        if(squareNr == 0 /* || não existam jogadores na posição indicada*/){
            return null;
        }

        return null;
    }

    public String[] getSquareInfo(int squareNr) {

        return null;
    }

    public String[] getPlayerInfo(int playerId) {

        return null;
    }

    public String[] getCurrentPlayerInfo() {

        return null;
    }

    public String[][] getPlayersInfo() {

        return null;
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {

        return true;
    }

    public String[] getWinnerInfo() {

        return null;
    }

    public ArrayList<String> getGameResults() {

        return null;
    }

    public JPanel getAuthorsPanel() {

        return null;
    }

    public String whoIsTaborda() {

        return "Wrestling";
    }

}
