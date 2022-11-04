package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;

public class GameManager {

    public String[][] getSpecies() {

        String[][] especies = new String[5][3];

        especies[0][0] = "E";
        especies[0][1] = "Elefante";
        especies[0][2] = "elephant.png";

        especies[1][0] = "L";
        especies[1][1] = "Leão";
        especies[1][2] = "lion.png";

        especies[2][0] = "T";
        especies[2][1] = "Tartaruga";
        especies[2][2] = "turtle.png";

        especies[3][0] = "P";
        especies[3][1] = "Pássaro";
        especies[3][2] = "bird.png";

        especies[4][0] = "Z";
        especies[4][1] = "Tarzan";
        especies[4][2] = "tarzan.png";

        return especies;
    }

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {

        //verificar dados iniciais
        if (jungleSize == 0 || initialEnergy == 0 || playersInfo == null) {
            return false;
        }

        //verificar ID´s dos jogadores

        for (int i = 0; i < playersInfo.length; i++) {
            if(playersInfo[i][1] == null || playersInfo[i][1].equals("")){
                return false;
            }
        }

        //verificar ID´s duplicados e iguais COM HASH MAP ou HASH SET



        //verificar nomes jogadores
        if ((playersInfo[0][1] == null || playersInfo[0][1].equals("")) ||
                (playersInfo[1][1] == null || playersInfo[1][1].equals("")) ||
                (playersInfo[2][1] == null || playersInfo[2][1].equals("")) ||
                (playersInfo[3][1] == null || playersInfo[3][1].equals(""))) {

            return false;
        }

        return true;
    }

    public int[] getPlayerIds(int squareNr) {

        int nrJogadores = 0;

        int[] identificadoresJogadores = new int[0];

        if (squareNr == 0 /* || não existam jogadores na posição indicada*/) {
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
