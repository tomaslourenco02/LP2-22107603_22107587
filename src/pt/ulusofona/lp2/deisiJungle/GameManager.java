package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

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

        HashMap<String, String> IDjogadores = new HashMap<>();

        //verifica ID´s Jogadores
        for (int i = 0; i < playersInfo.length; i++) {
            if (!IDjogadores.containsKey(playersInfo[i][0])) {
                IDjogadores.put(playersInfo[i][0], playersInfo[i][1]);
            } else {
                return false;
            }
        }

        //verifica especies jogadores
        for (int i = 0; i < playersInfo.length; i++) {
            if(!(playersInfo[i][2].equals("L") || playersInfo[i][2].equals("E") || playersInfo[i][2].equals("P") || playersInfo[i][2].equals("T")
            || playersInfo[i][2].equals("Z"))){
                return false;
            }
        }

        //verifica nr jogadores
        if(IDjogadores.size() < 2 || IDjogadores.size() > 4){
            return false;
        }

        //verificar nomes dos jogadores
        for (int i = 0; i < playersInfo.length; i++) {
            if (playersInfo[i][1] == null || playersInfo[i][1].equals("")) {
                return false;
            }
        }

        if(jungleSize < IDjogadores.size() * 2){
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
