package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


public class GameManager {
    ArrayList<Jogador> jogadores = new ArrayList();
    ArrayList<SquareInfo> tabuleiro = new ArrayList<>();
    int countJogadores = 0;

    public GameManager() {

        ArrayList<Jogador> jogadores = new ArrayList<>();
        ArrayList<SquareInfo> tabuleiro = new ArrayList<>();
    }

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

    public boolean verificaEspecies(String especie) {

        String[][] especies = getSpecies();
        ArrayList<String> especiesArrayList = new ArrayList<>();

        for (int i = 0; i < especies.length; i++) {
            especiesArrayList.add(especies[i][0]);
        }
        if (especiesArrayList.contains(especie)) {
            return true;
        }
        return false;
    }

    public boolean verificaJogadores(String[][] playersInfo) {

        int countTarzan = 0;

        if (playersInfo == null) {
            return false;
        }

        int[] ids = new int[playersInfo.length];

        if (playersInfo.length > 4 || playersInfo.length < 2) {
            return false;
        }

        for (int i = 0; i < playersInfo.length; i++) {

            if (playersInfo[i][1] == null || Objects.equals(playersInfo[i][1], "")) {
                return false;
            }
            if (!verificaEspecies(playersInfo[i][2])) {
                return false;
            }

            if (playersInfo[i][0] == null) {
                return false;
            }
            if (!playersInfo[i][0].matches("[0-9]*")) {
                return false;
            }
            if (Integer.parseInt(playersInfo[i][0]) <= 0) {
                return false;
            }

            ids[i] = Integer.parseInt(playersInfo[i][0]);
        }
        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < ids.length; j++) {
                if (i != j) {
                    if (ids[i] == ids[j]) {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < playersInfo.length; i++) {
            if (Objects.equals(playersInfo[i][2], "T")) {
                countTarzan++;
            }
        }
        if (countTarzan > 1) {
            return false;
        }

        return true;
    }

    int count = 0;

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {

        if (initialEnergy <= 0) {
            return false;
        }

        if (jungleSize < playersInfo.length * 2) {
            return false;
        }

        if (!verificaJogadores(playersInfo)) {
            return false;
        }

        tabuleiro.add(new SquareInfo());


        for (int i = 0; i < playersInfo.length; i++) {
            jogadores.add(new Jogador(Integer.parseInt(playersInfo[i][0]), playersInfo[i][1], playersInfo[i][2]));
            if (tabuleiro != null) {
                tabuleiro.get(0).identificadoresNoQuadrado.add(playersInfo[i][0]); //NAO POSSO FAZER ISTO
            }
        }

        int menorID = 0;

        for (int i = 0; i < jogadores.size(); i++) {
            for (int j = 0; j < jogadores.size(); j++) {
                if (i != j) {
                    if (jogadores.get(i).identificador < jogadores.get(j).identificador) {
                        menorID = jogadores.get(i).identificador;
                    } else {
                        menorID = jogadores.get(j).identificador;
                    }
                }
            }
        }

        for (int i = 0; i < jogadores.size(); i++) {
            if(menorID == jogadores.get(i).identificador){
                jogadores.get(i).aJogar = true;
            }
        }

        return true;
    }

    public int[] getPlayerIds(int squareNr) {
        int[] idJogadores = new int[jogadores.size()];
        int count = 0;

        if (squareNr <= 0) {
            return new int[0];
        }

        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).posicaoAtual == squareNr - 1) {
                idJogadores[count] = jogadores.get(i).identificador;
                count++;
            }
        }

        if (idJogadores.length == 0) {
            return new int[0];
        }

        int[] idJogadoresRetornar = new int[count];

        for (int i = 0; i < idJogadoresRetornar.length; i++) {
            idJogadoresRetornar[i] = idJogadores[i];
        }

        return idJogadoresRetornar;
    }

    public String[] getSquareInfo(int squareNr) {

        SquareInfo square = new SquareInfo();

        String[] squareInfo = new String[3];

        int[] jogadoresNoSquare = getPlayerIds(squareNr);

        String identificadores = "";

        if (squareNr <= 0/* || squareNr > tabuleiro.size()*/) {
            return null;
        }

        for (int i = 0; i < jogadoresNoSquare.length; i++) {
            square.identificadoresNoQuadrado.add(Integer.toString(jogadoresNoSquare[i]));
        }

        for (int i = 0; i < square.identificadoresNoQuadrado.size(); i++) {
            if(i == square.identificadoresNoQuadrado.size()-1){
                identificadores += square.identificadoresNoQuadrado + "";
            }else{
                identificadores += square.identificadoresNoQuadrado + ",";
            }
        }

        squareInfo[0] = square.imagemAColocar;
        squareInfo[1] = square.texto;
        squareInfo[2] = identificadores;

        if (square.meta) {
            square.imagemAColocar = "Finish.png";
            square.texto = "Meta";
        }
        return squareInfo;
    }

    public String[] getPlayerInfo(int playerId) {
        String[] infoJogador = new String[4];
        int count = 0;

        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).identificador == playerId) {
                infoJogador[0] = Integer.toString(jogadores.get(i).identificador);
                infoJogador[1] = jogadores.get(i).nome;
                infoJogador[2] = jogadores.get(i).especieDoJogador;
                infoJogador[3] = Integer.toString(jogadores.get(i).energiaAtual);
                count++;
            }
        }
        if (count <= 0) {
            return null;
        }
        if (infoJogador[0] == null || infoJogador[1] == null || infoJogador[2] == null || infoJogador[3] == null) {
            return null;
        }
        return infoJogador;
    }

    public String[] getCurrentPlayerInfo() {

        return null;
    }

    public String[][] getPlayersInfo() {
        return getPlayersInfo();
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {

        if (bypassValidations == false) {
            if (nrSquares <= 0 || nrSquares > 6) {
                return false;
            }
        }

            int[] ids = new int[jogadores.size()];

            for (int i = 0; i < jogadores.size(); i++) {
                ids[i] = jogadores.get(i).identificador;
            }

            Arrays.sort(ids);
        System.out.println(String.valueOf(ids[countJogadores]));

                    for (int i = 0; i < jogadores.size(); i++) {
                        if (jogadores.get(i).identificador == ids[countJogadores]) {
                            jogadores.get(i).aJogar = true;
                            jogadores.get(i).posicaoAtual += nrSquares;
                            jogadores.get(i).aJogar = false;
                            System.out.println("O jogador " + jogadores.get(i).nome + " andou até " + jogadores.get(i).posicaoAtual);
                        }
                    }


                    if(jogadores.get(countJogadores).posicaoAtual >= tabuleiro.size()){
                        jogadores.get(countJogadores).ganhou = true;
                    }

                    countJogadores++;

                    if(countJogadores == jogadores.size()){
                        countJogadores = 0;
                    }

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