package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    ArrayList<Jogador> jogadores = new ArrayList();
    SquareInfo tabuleiro = new SquareInfo();

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

    int count = 0;
    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {
        String[][] casosPossiveis = new String[4][3];
        tabuleiro.tamanho = jungleSize;
        //verificar dados iniciais
        if (jungleSize == 0 || initialEnergy == 0 || initialEnergy < 0 || playersInfo == null) {
            return false;
        }
        if(playersInfo.length > casosPossiveis.length){
            return false;
        }

        int[] IDjogador = new int[4];
        int[] IDjogador_comparacao = new int[4];

        for (int i = 0; i < IDjogador.length; i++) {
            IDjogador[i] = Integer.parseInt(playersInfo[i][0]);
            for (int j = 0; j < IDjogador_comparacao.length; j++) {
                IDjogador_comparacao[j] = Integer.parseInt(playersInfo[j][0]);
                if(IDjogador[i] == IDjogador_comparacao[j]){
                    return false;
                }
            }
        }

        HashMap<String, Integer> IDjogadores = new HashMap<>();

        //verifica ID´s Jogadores
        for (int i = 0; i < playersInfo.length; i++) {
            if (!IDjogadores.containsKey(playersInfo[i][0])) {
                IDjogadores.put(playersInfo[i][0], Integer.valueOf(playersInfo[i][(1)]));
            } else {
                return false;
            }
        }

         //verifica especies jogadores
        for (int i = 0; i < playersInfo.length; i++) {
            if (!(playersInfo[i][2].equals("L") || playersInfo[i][2].equals("E") || playersInfo[i][2].equals("P")
                    || playersInfo[i][2].equals("T")
                    || playersInfo[i][2].equals("Z"))) {
                if(playersInfo[i][2].equals("T")){
                    count++;
                }
                return false;
            }
            if( count > 1 ){
                return false;
            }
        }

        /*
        String[][] especies = getSpecies();

        for (int i = 0; i < IDjogadores.size(); i++) {
            for (int j = 0; j < getSpecies().length; j++) {
                if(!playersInfo[i][2].equals(especies[j][0])){
                    return false;
                }
            }
        }
        */

        //verificar nomes dos jogadores
        for (int i = 0; i < playersInfo.length; i++) {
            if (playersInfo[i][1] == null || playersInfo[i][1].equals("")) {
                return false;
            }
        }

        //verifica nr jogadores
        if (IDjogadores.size() < 2 || IDjogadores.size() > 4) {
            return false;
        }

        if (jungleSize < IDjogadores.size() * 2) {
            return false;
        }

        int i = 0;

        while(i < 4){
            Jogador bro = new Jogador();
            bro.identificador = Integer.parseInt(playersInfo[i][1]);
            bro.nome = playersInfo[i][2];
            bro.especieDoJogador.identificador = playersInfo[i][3];
            bro.energiaInicial = initialEnergy;

            jogadores.add(bro);

            i++;
        }

        return true;

    }

    public int[] getPlayerIds(int squareNr) {
        int[] IDjogadores = new int[4];
        int count1 = 0;


        for (int j = 0; j < IDjogadores.length ; j++) {
            for (int i = 0; i < jogadores.size(); i++) {
             if (squareNr == 0 || squareNr > tabuleiro.tamanho) {
                 return new int[0];
             }
             if (squareNr != jogadores.get(i).posicaoAtual) {
                 count1++;
             }
             if (squareNr == jogadores.get(i).posicaoAtual) {
                 IDjogadores[j] = jogadores.get(i).identificador;
             }
         }
        }

        int[] IDjogadores_retornar = new int[count1];

        for (int i = 0; i < IDjogadores_retornar.length; i++) {
            IDjogadores_retornar[i] = IDjogadores[i];
        }

        return IDjogadores_retornar;

         /* int[] identificadoresJogadores = new int[4];

        Jogador[] jogadoresEmJogo = new Jogador[4];

        if (squareNr == 0) {
            return new int[0]; //array vazio
        }

        for (int i = 0; i < jogadoresEmJogo.length; i++) {
            jogadoresEmJogo[i] = new Jogador();

            if(jogadoresEmJogo[i].posicaoAtual != squareNr){
                return new int[0];
            }

            else{
                if(jogadoresEmJogo[i].posicaoAtual == squareNr){
                    identificadoresJogadores[i] = jogadoresEmJogo[i].identificador;
                }
            }
        }
        return identificadoresJogadores; */
    }

    public String[] getSquareInfo(int squareNr) {

        if(squareNr == 0){
            return null;
        }

        SquareInfo square = new SquareInfo();

        String[] squareInfo = new String[3];

        squareInfo[0] = square.imagemAColocar;
        squareInfo[1] = square.texto;
        squareInfo[2] = square.identificadoresNoQuadrado;

        if(square.meta == true){
        square.imagemAColocar = "Finish.png";
        square.texto = "Meta";
        }
        return squareInfo;
    }

    public String[] getPlayerInfo(int playerId) {
        String[] infoJogador = new String[4];

        for (int i = 0; i < jogadores.size(); i++) {
            if(jogadores.get(i).identificador == playerId){
            infoJogador[0] = Integer.toString(jogadores.get(i).identificador);
            infoJogador[1] = jogadores.get(i).nome;
            infoJogador[2] = jogadores.get(i).especieDoJogador.identificador;
            infoJogador[3] = Integer.toString(jogadores.get(i).energiaAtual);
            }
        }
        if(infoJogador[0] == null && infoJogador[1] == null && infoJogador[2] == null && infoJogador[3] == null){
            return null;
        }
        return infoJogador;
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
