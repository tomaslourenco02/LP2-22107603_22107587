package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


public class GameManager {
    ArrayList<Jogador> jogadores = new ArrayList();
    ArrayList<SquareInfo> squares = new ArrayList<>();
    int countJogadores = 0;
    int jogadoresSemEnergia = 0;
    int jogadorVencedorID = 0;
    int tamanhoTabuleiro = 0;
    boolean jogoAcabou = false;

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
        especies[1][1] = "LeÃ£o";
        especies[1][2] = "lion.png";

        especies[2][0] = "T";
        especies[2][1] = "Tartaruga";
        especies[2][2] = "turtle.png";

        especies[3][0] = "P";
        especies[3][1] = "PÃ¡ssaro";
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

        jogadores.clear();
        squares.clear();
        countJogadores = 0;
        jogadoresSemEnergia = 0;
        jogadorVencedorID = 0;
        tamanhoTabuleiro = 0;
        jogoAcabou = false;

        if (initialEnergy <= 0) {
            return false;
        }

        if (jungleSize < playersInfo.length * 2) {
            return false;
        }

        tamanhoTabuleiro = jungleSize;


        if (!verificaJogadores(playersInfo)) {
            return false;
        }

        squares.add(new SquareInfo());


        for (int i = 0; i < playersInfo.length; i++) {
            jogadores.add(new Jogador(Integer.parseInt(playersInfo[i][0]), playersInfo[i][1], playersInfo[i][2], initialEnergy));
            if (squares != null) {
                squares.get(0).identificadoresNoQuadrado.add(playersInfo[i][0]); //NAO POSSO FAZER ISTO
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
            if (menorID == jogadores.get(i).identificador) {
                jogadores.get(i).aJogar = true;
            }
        }

        return true;
    }

    public int[] getPlayerIds(int squareNr) {
        int[] idJogadores = new int[jogadores.size()];
        int count = 0;

        squareNr--;

        if (squareNr < 0) {
            return new int[0];
        }

        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).posicaoAtual == squareNr) {
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

        squareNr--;

        SquareInfo square = new SquareInfo();

        String[] squareInfo = new String[3];

        int[] jogadoresNoSquare = getPlayerIds(squareNr);

        String identificadores = "";

        if (squareNr < 0 || squareNr > tamanhoTabuleiro) {
            return null;
        }

        for (int i = 0; i < jogadoresNoSquare.length; i++) {
            square.identificadoresNoQuadrado.add(Integer.toString(jogadoresNoSquare[i]));
        }

        for (int i = 0; i < square.identificadoresNoQuadrado.size(); i++) {
            if (i == square.identificadoresNoQuadrado.size() - 1) {
                identificadores += square.identificadoresNoQuadrado.get(i) + "";
            } else {
                identificadores += square.identificadoresNoQuadrado.get(i) + ",";
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


        for (int i = 0; i < jogadores.size(); i++) {
            if(jogadores.get(i).identificador == playerId){
                return jogadores.get(i).infoJogador();
            }
        }

        return null;
    }

    public String[] getCurrentPlayerInfo() {
        int[] ids = ordenarIds();
        String[] infoJogadorAtual = new String[4];

        for (int i = 0; i < jogadores.size(); i++) {
            if (countJogadores == 0) {
                if (jogadores.get(i).identificador == ids[countJogadores]) {
                    infoJogadorAtual = jogadores.get(i).infoJogador();
                }
            } else {
                if (jogadores.get(i).identificador == ids[countJogadores - 1]) {
                    infoJogadorAtual = jogadores.get(i).infoJogador();

                }
            }
        }
        return infoJogadorAtual;
    }

    //EXTRA ORDENAR IDS
    public int[] ordenarIds() {
        int[] ids = new int[jogadores.size()];

        for (int i = 0; i < jogadores.size(); i++) {
            ids[i] = jogadores.get(i).identificador;
        }

        Arrays.sort(ids);

        return ids;
    }

    public String[][] getPlayersInfo() {
        String[][] informacaoDosJogadores = new String[jogadores.size()][4];
        int[] ids = ordenarIds();
        int jogador = 0;


        for (int j = 0; j < ids.length; j++) {
            for (int i = 0; i < jogadores.size(); i++) { //ids Ordenados
                if (jogadores.get(i).identificador == ids[j]) {
                    informacaoDosJogadores[j] = jogadores.get(i).infoJogador();
                    jogador++;
                }
            }

        }
        return informacaoDosJogadores;
    }

    public boolean verificaEnergia(){

        for (int i = 0; i < jogadores.size(); i++) {
            if(jogadores.get(i).energiaAtual >= 2){
                return true;
            }
        }
        return false;
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {

        Jogador[] jogadoresNaCasa = new Jogador[jogadores.size()];

        if (!bypassValidations) {
            if (nrSquares <= 0 || nrSquares > 6) {
                return false;
            }
        }

        int[] ids = ordenarIds();


        /*for (int i = 0; i < jogadores.size(); i++) {

            if(jogadores.get(i).identificador == ids[i]){
                if(jogadores.get(i).energiaAtual >= 2)
            }

        }*/
        if (countJogadores == jogadores.size()) {
            countJogadores = 0;
        }
        if (jogoAcabou == false) {
            for (int i = 0; i < jogadores.size(); i++) {
                if (jogadores.get(i).identificador == ids[countJogadores]) {
                    if (jogadores.get(i).energiaAtual >= 2) {
                        jogadores.get(i).aJogar = true;
                        jogadores.get(i).posicaoAtual += nrSquares;
                        jogadores.get(i).energiaAtual -= 2;

                        countJogadores++;

                        if (jogadores.get(i).energiaAtual <= 0) {
                            jogadoresSemEnergia++;

                            if (jogadoresSemEnergia == jogadores.size()) {
                                jogoAcabou = true;

                                //ver as posicoes e caso as posicoes sejam iguais ganha o id menor
                            }
                        }

                        if (jogadores.get(i).posicaoAtual >= tamanhoTabuleiro) {
                            jogadores.get(i).posicaoAtual = tamanhoTabuleiro;
                            jogoAcabou = true;
                            jogadores.get(i).ganhou = true;
                        }

                        System.out.println(jogadores.get(i).posicaoAtual);
                        return true;


                    }
                    jogadores.get(i).aJogar = false;
                }

            }
        }
        return false;
    }

    int id = 0;

    public int[] ordenarPosicoes() {
        int[] posicoesOrdenadas = new int[jogadores.size()];
        int[] idsOrdenados = ordenarIds();

        if (jogoAcabou == true) {
            for (int i = 0; i < idsOrdenados.length; i++) {
                for (int j = 0; j < jogadores.size(); j++) {
                    if (jogadores.get(i).identificador == idsOrdenados[1]) {
                        posicoesOrdenadas[i] = jogadores.get(i).posicaoAtual;
                    }
                }

            }
        }
        Arrays.sort(posicoesOrdenadas);
        return posicoesOrdenadas;
    }

    public String[] getWinnerInfo() {

        String[] winnerInfo = new String[4];
        int[] posicaoOrdenada = ordenarPosicoes();
        int count = 0;
        Jogador[] jogadoresNaCasa = new Jogador[jogadores.size()];

        if(!verificaEnergia()){
            for (int i = tamanhoTabuleiro; i > 0; i--) {
                if(squares.get(i).identificadoresNoQuadrado.size() > 0){
                    if(squares.get(i).identificadoresNoQuadrado.size() ==1){

                    }
                }
            }
        }

        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).ganhou == true) {
                winnerInfo[0] = String.valueOf(jogadores.get(i).identificador);
                winnerInfo[1] = jogadores.get(i).nome;
                winnerInfo[2] = jogadores.get(i).especieDoJogador;
                winnerInfo[3] = String.valueOf(jogadores.get(i).energiaAtual);
                return winnerInfo;
            }
        }
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