package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class GameManager {
    ArrayList<Jogador> jogadores = new ArrayList<>();
    ArrayList<Alimento> alimentos = new ArrayList<>();
    ArrayList<SquareInfo> squares = new ArrayList<>();
    int countJogadores = 0;
    int jogadoresSemEnergia = 0;
    int jogadorVencedorID = 0;
    int tamanhoTabuleiro = 0;
    int jogadasFeitas = 0;
    boolean jogoAcabou = false;

    public GameManager() {
    }

    public GameManager(ArrayList<Jogador> jogadores, ArrayList<SquareInfo> squares, int countJogadores, int jogadoresSemEnergia, int jogadorVencedorID, int tamanhoTabuleiro, int jogadasFeitas, boolean jogoAcabou, int count, ArrayList<Alimento> alimentos) {
        this.jogadores = jogadores;
        this.squares = squares;
        this.countJogadores = countJogadores;
        this.jogadoresSemEnergia = jogadoresSemEnergia;
        this.jogadorVencedorID = jogadorVencedorID;
        this.tamanhoTabuleiro = tamanhoTabuleiro;
        this.jogadasFeitas = jogadasFeitas;
        this.jogoAcabou = jogoAcabou;
        this.count = count;
        this.alimentos = alimentos;
    }

    public String[][] getSpecies() {

        String[][] especies = new String[5][7];

        Random aleatorio = new Random();

        especies[0][0] = "E";
        especies[0][1] = "Elefante";
        especies[0][2] = "elephant.png";
        especies[0][3] = String.valueOf(180);
        especies[0][4] = String.valueOf(4);
        especies[0][5] = String.valueOf(10);
        especies[0][6] = "1..6";

        especies[1][0] = "L";
        especies[1][1] = "Leão";
        especies[1][2] = "lion.png";
        especies[1][3] = String.valueOf(80);
        especies[1][4] = String.valueOf(2);
        especies[1][5] = String.valueOf(10);
        especies[1][6] = "4..6";

        especies[2][0] = "T";
        especies[2][1] = "Tartaruga";
        especies[2][2] = "turtle.png";
        especies[2][3] = String.valueOf(150);
        especies[2][4] = String.valueOf(1);
        especies[2][5] = String.valueOf(5);
        especies[2][6] = "1..3";

        especies[3][0] = "P";
        especies[3][1] = "Pássaro";
        especies[3][2] = "bird.png";
        especies[3][3] = String.valueOf(70);
        especies[3][4] = String.valueOf(4);
        especies[3][5] = String.valueOf(50);
        especies[3][6] = "5..6";

        especies[4][0] = "Z";
        especies[4][1] = "Tarzan";
        especies[4][2] = "tarzan.png";
        especies[4][3] = String.valueOf(70);
        especies[4][4] = String.valueOf(2);
        especies[4][5] = String.valueOf(20);
        especies[4][6] = "1..6";

        return especies;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) {
        jogadores.clear();
        squares.clear();
        countJogadores = 0;
        jogadoresSemEnergia = 0;
        jogadorVencedorID = 0;
        tamanhoTabuleiro = 0;
        jogoAcabou = false;
        count = 0;

        if (foodsInfo != null) {
            for (int i = 0; i < foodsInfo.length; i++) {
                if (!verificaIdAlimentos(foodsInfo[i][0])) {
                    return new InitializationError("Erro na inicialização do terreno!");
                }
            }
        }

        if (foodsInfo != null) {
            for (int i = 0; i < foodsInfo.length; i++) {
                if (foodsInfo[i][1].matches("[a-zA-Z]+")) {
                    return new InitializationError("Erro na inicialização do terreno!");
                }

                if (foodsInfo[i][1].matches("[0-9]*")) {
                    if (Integer.parseInt(foodsInfo[i][1]) >= jungleSize || Integer.parseInt(foodsInfo[i][1]) <= 1) {
                        return new InitializationError("Erro na inicialização do terreno!");
                    }
                }
            }
        }

        if (!verificaJogadores(playersInfo)) {
            return new InitializationError("Erro na inicialização do terreno!");
        }

        if (jungleSize < playersInfo.length * 2) {
            return new InitializationError("Erro na inicialização do terreno!");
        }

        tamanhoTabuleiro = jungleSize;

        for (int i = 0; i < tamanhoTabuleiro; i++) {
            squares.add(new SquareInfo());
        }

        for (int i = 0; i < playersInfo.length; i++) {
            jogadores.add(new Jogador(Integer.parseInt(playersInfo[i][0]), playersInfo[i][1], playersInfo[i][2]));
            if (squares != null) {
                squares.get(0).identificadoresNoQuadrado.add(Integer.valueOf(playersInfo[i][0]));
            }
        }

        if (foodsInfo != null) {
            for (int i = 0; i < foodsInfo.length; i++) {
                for (int j = 0; j < squares.size(); j++) {

                    if (j == Integer.parseInt(foodsInfo[i][1])) {
                        squares.get(j).identificadoresAlimentosNoQuadrado = foodsInfo[i][0];
                    }
                    if (foodsInfo[i][0].equals("m")) {
                        squares.get(i).cogumelo = new CogumelosMagicos("m", "Cogumelos Magicos", "mushroom.png");
                    }
                    if (foodsInfo[i][0].equals("b")) {
                        squares.get(j).bananas = 3;
                    }
                }
            }
        }

        int menorID = 0;

        for (int i = 0; i < jogadores.size(); i++) {
            for (int j = 0; j < jogadores.size(); j++) {
                if (i != j) {
                    if (jogadores.get(i).getIdentificador() < jogadores.get(j).getIdentificador()) {
                        menorID = jogadores.get(i).getIdentificador();
                    } else {
                        menorID = jogadores.get(j).getIdentificador();
                    }
                }
            }
        }
        for (int i = 0; i < jogadores.size(); i++) {
            if (menorID == jogadores.get(i).getIdentificador()) {
                jogadores.get(i).aJogar = true;
            }
        }
        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo) {

        return createInitialJungle(jungleSize, playersInfo, null);
    }

    public boolean verificaPosicaoAlimentos() {

        String[][] alimentos = getFoodTypes();
        ArrayList<String> alimentosArrayList = new ArrayList<>();
        int count = 0;

        int posFinal = squares.size();

        if (!(squares.get(0).identificadoresAlimentosNoQuadrado.isEmpty()) || !(squares.get(posFinal - 1).identificadoresAlimentosNoQuadrado.isEmpty())) {

            return false;
        }

        for (int i = 0; i < alimentos.length; i++) {
            alimentosArrayList.add(alimentos[i][0]);
        }
        for (int i = 0; i < squares.size(); i++) {

            if (squares.get(i).identificadoresAlimentosNoQuadrado.isEmpty()) {
                count++;
            }
        }
        if (alimentosArrayList.size() != count) {
            return false;
        }
        return true;
    }

    public boolean verificaIdAlimentos(String alimento) {

        String[][] alimentos = getFoodTypes();
        ArrayList<String> alimentosArrayList = new ArrayList<>();

        for (int i = 0; i < alimentos.length; i++) {
            alimentosArrayList.add(alimentos[i][0]);
        }
        if (alimentosArrayList.contains(alimento)) {
            return true;
        }
        return false;
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
            if (Objects.equals(playersInfo[i][2], "Z")) {
                countTarzan++;
            }
        }
        if (countTarzan > 1) {
            return false;
        }

        return true;
    }

    int count = 0;

    public String[][] getFoodTypes() {
        String[][] alimentos = new String[5][3];

        alimentos[0][0] = "e";
        alimentos[0][1] = "Erva";
        alimentos[0][2] = "grass.png";

        alimentos[1][0] = "a";
        alimentos[1][1] = "Agua";
        alimentos[1][2] = "water.png";

        alimentos[2][0] = "b";
        alimentos[2][1] = "Cacho de bananas";
        alimentos[2][2] = "bananas.png";

        alimentos[3][0] = "c";
        alimentos[3][1] = "Carne";
        alimentos[3][2] = "meat.png";

        alimentos[4][0] = "m";
        alimentos[4][1] = "Cogumelos magicos";
        alimentos[4][2] = "mushroom.png";

        return alimentos;
    }

    /*public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {

        jogadores.clear();
        squares.clear();
        countJogadores = 0;
        jogadoresSemEnergia = 0;
        jogadorVencedorID = 0;
        tamanhoTabuleiro = 0;
        jogoAcabou = false;
        count = 0;

        if (initialEnergy <= 0) {
            return false;
        }

        if (jungleSize < playersInfo.length * 2) {
            return false;
        }

        if (!verificaJogadores(playersInfo)) {
            return false;
        }

        tamanhoTabuleiro = jungleSize;

        for (int i = 0; i < tamanhoTabuleiro; i++) {
            squares.add(new SquareInfo());
        }


        for (int i = 0; i < playersInfo.length; i++) {
            jogadores.add(new Jogador(Integer.parseInt(playersInfo[i][0]), playersInfo[i][1], playersInfo[i][2], initialEnergy));
            if (squares != null) {
                squares.get(0).identificadoresNoQuadrado.add(Integer.valueOf(playersInfo[i][0])); //NAO POSSO FAZER ISTO
            }
        }

        int menorID = 0;

        for (int i = 0; i < jogadores.size(); i++) {
            for (int j = 0; j < jogadores.size(); j++) {
                if (i != j) {
                    if (jogadores.get(i).getIdentificador() < jogadores.get(j).getIdentificador()) {
                        menorID = jogadores.get(i).getIdentificador();
                    } else {
                        menorID = jogadores.get(j).getIdentificador();
                    }
                }
            }
        }

        for (int i = 0; i < jogadores.size(); i++) {
            if (menorID == jogadores.get(i).getIdentificador()) {
                jogadores.get(i).aJogar = true;
            }
        }
        return true;
    }*/

    public int[] getPlayerIds(int squareNr) {
        int[] idJogadores = new int[jogadores.size()];
        int count = 0;

        if (squareNr < 1) {
            return new int[0];
        }

        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getPosicaoAtual() == squareNr) {
                idJogadores[count] = jogadores.get(i).getIdentificador();
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

        int[] jogadoresNoSquare = new int[0];

        String[] squareInfo = new String[3];

        SquareInfo square = new SquareInfo();

        if (squareNr - 1 == 0) {
            jogadoresNoSquare = getPlayerIds(squareNr);
        } else if (squareNr - 1 > 0) {
            jogadoresNoSquare = getPlayerIds(squareNr);
        }

        StringBuilder identificadores = new StringBuilder();

        if (squareNr < 1 || squareNr > tamanhoTabuleiro) {
            return null;
        }

        for (int i = 0; i < jogadoresNoSquare.length; i++) {
            square.identificadoresNoQuadrado.add(Integer.valueOf(Integer.toString(jogadoresNoSquare[i])));
        }

        for (int i = 0; i < square.identificadoresNoQuadrado.size(); i++) {
            if (i == square.identificadoresNoQuadrado.size() - 1) {
                identificadores.append(square.identificadoresNoQuadrado.get(i));
            } else {
                identificadores.append(square.identificadoresNoQuadrado.get(i)).append(",");
            }
        }

        if (squareNr < squares.size() && squareNr > 1) {

            if (squares.get(squareNr).identificadoresAlimentosNoQuadrado != null) {
                Alimento alimento = definirAlimento(squares.get(squareNr).identificadoresAlimentosNoQuadrado);
                System.out.println(squares.get(squareNr).cogumelo.nrAleatorio);
                squareInfo[0] = alimento.imagem;
                squareInfo[1] = alimento.info();
                squareInfo[2] = identificadores.toString();
                if (squares.get(squareNr).identificadoresAlimentosNoQuadrado.equals("b")) {
                    squareInfo[1] = "Bananas : " + squares.get(squareNr).bananas + " : + 40 energia";
                }
                return squareInfo;
            }
        }

        if (squareNr == tamanhoTabuleiro) {
            squareInfo[0] = "finish.png";
            squareInfo[1] = "Meta";
            squareInfo[2] = identificadores.toString();
            return squareInfo;
        } else {
            squareInfo[0] = square.imagemAColocar;
            squareInfo[1] = square.texto;
            squareInfo[2] = identificadores.toString();
            return squareInfo;
        }
    }

    public String[] getPlayerInfo(int playerId) {


        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getIdentificador() == playerId) {
                return jogadores.get(i).infoJogador();
            }
        }

        return null;
    }

    public String[] getCurrentPlayerInfo() {
        int[] ids = ordenarIds();
        String[] infoJogadorAtual = new String[4];


        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getIdentificador() == ids[countJogadores]) {
                infoJogadorAtual = jogadores.get(i).infoJogador();
            }
        }
        return infoJogadorAtual;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {
        String[] energyInfo = new String[2];
        ArrayList<Jogador> jogadoresOrdenados = ordenarJogadores();
        Jogador jogadorAJogar = jogadoresOrdenados.get(countJogadores);
        int posJogador = jogadorAJogar.getPosicaoAtual();
        int posDestino = posJogador + nrPositions;
        int energiaGasta = gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrPositions);
        int ganhoDeEnergia = 0;

        if (posDestino < 1) {
            posDestino = 1;
        }
        if (posDestino > tamanhoTabuleiro) {
            posDestino = tamanhoTabuleiro;
        }
        if (squares.get(posDestino).identificadoresAlimentosNoQuadrado != null) {
            String alimento = squares.get(posDestino).identificadoresAlimentosNoQuadrado;
            if (alimento.equals("e")) {
                if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Omnívoro")) {
                    ganhoDeEnergia = 20;
                } else if (jogadorAJogar.especie.tipo.equals("Carnívoro")) {
                    ganhoDeEnergia = 20;
                }
            }
            if (alimento.equals("a")) {
                if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Carnívoro")) {
                    ganhoDeEnergia = 15;
                } else if (jogadorAJogar.especie.tipo.equals("Omnívoro")) {
                    ganhoDeEnergia = ((jogadorAJogar.energiaAtual * 20) / 100);
                }
            }
            if (alimento.equals("b")) {
                if (jogadorAJogar.bananasConsumidas > 1) {
                    ganhoDeEnergia = -40;
                } else if (squares.get(posDestino).bananas > 0) {
                    ganhoDeEnergia = 40;
                }
            }
            if (alimento.equals("c")) {
                if (jogadasFeitas > 12) {
                    ganhoDeEnergia = jogadorAJogar.energiaAtual / 2;
                } else if (jogadorAJogar.especie.tipo.equals("Omnívoros") || jogadorAJogar.especie.tipo.equals("Carnívoro")) {
                    ganhoDeEnergia = 50;
                }
            }
            if (alimento.equals("m")) {
                int nrAleatorio = squares.get(posDestino).cogumelo.nrAleatorio;
                if (jogadasFeitas % 2 == 0) {
                    double energia = jogadorAJogar.energiaAtual + ((nrAleatorio * jogadorAJogar.energiaAtual) / 100.0);
                    jogadorAJogar.energiaAtual = (int) Math.round(energia);
                    ;
                } else {
                    double energia = jogadorAJogar.energiaAtual - ((nrAleatorio * jogadorAJogar.energiaAtual) / 100.0);
                    jogadorAJogar.energiaAtual = (int) Math.round(energia);
                    ;
                }
            }
        }
        if (ganhoDeEnergia == 0) {

            ganhoDeEnergia = jogadorAJogar.especie.ganhoEnergiaEmDescanso;
        }

        energyInfo[0] = String.valueOf(energiaGasta);
        energyInfo[1] = String.valueOf(ganhoDeEnergia);

        return energyInfo;
    }

    //EXTRA ORDENAR IDS
    public int[] ordenarIds() {
        int[] ids = new int[jogadores.size()];

        for (int i = 0; i < jogadores.size(); i++) {
            ids[i] = jogadores.get(i).getIdentificador();
        }

        Arrays.sort(ids);

        return ids;
    }

    public String[][] getPlayersInfo() {
        String[][] informacaoDosJogadores = new String[jogadores.size()][4];
        int[] ids = ordenarIds();


        for (int j = 0; j < ids.length; j++) {
            for (int i = 0; i < jogadores.size(); i++) { //ids Ordenados
                if (jogadores.get(i).getIdentificador() == ids[j]) {
                    informacaoDosJogadores[j] = jogadores.get(i).infoJogador();
                }
            }
        }
        return informacaoDosJogadores;
    }

    public boolean verificaEnergia() {

        if (jogadores.get(countJogadores).getEnergiaAtual() > 200) {
            return false;
        }
        if (jogadores.get(countJogadores).getEnergiaAtual() < 0) {
            return false;
        }
        return true;
    }

    public ArrayList<Jogador> ordenarJogadores() {
        int[] ids = ordenarIds();

        ArrayList<Jogador> jogadoresOrdenados = new ArrayList<>();


        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < jogadores.size(); j++) {
                if (jogadores.get(j).getIdentificador() == ids[i]) {
                    jogadoresOrdenados.add(jogadores.get(j));
                }
            }
        }
        return jogadoresOrdenados;
    }

    public boolean saveGame(File file) {

        try {

            FileOutputStream ficheiro = new FileOutputStream(file);
            ObjectOutputStream objeto = new ObjectOutputStream(ficheiro);

            GameManager jogo = new GameManager(jogadores, squares, countJogadores, jogadoresSemEnergia,
                    jogadorVencedorID, tamanhoTabuleiro, jogadasFeitas, jogoAcabou, count, alimentos);

            objeto.writeObject(jogo);
            objeto.close();

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean loadGame(File file) {

        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            GameManager manager = (GameManager)objectInput.readObject();
            //Substitui os valores atuais do construtor com os valores do saveGame
            this.jogadores = manager.jogadores;
            this.alimentos = manager.alimentos;
            this.countJogadores = manager.countJogadores;
            this.jogadorVencedorID = manager.jogadorVencedorID;
            this.tamanhoTabuleiro = manager.tamanhoTabuleiro;
            this.jogadasFeitas = manager.jogadasFeitas;
            objectInput.close();
            return true;

        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public int gastaEnergia(int consumoEnergia, int nrSquares) {

        int energiaFinal = consumoEnergia * nrSquares;

        if (energiaFinal < 0) {
            energiaFinal *= -1;
        }

        return energiaFinal;
    }

    public Alimento definirAlimento(String idAlimento) {

        if (idAlimento == "e") {
            Erva alimento = new Erva("e", "Erva", "grass.png");
            return alimento;
        }
        if (idAlimento == "a") {
            Agua alimento = new Agua("a", "Agua", "water.png");
            return alimento;
        }
        if (idAlimento == "b") {
            CachoDeBananas alimento = new CachoDeBananas("b", "Cacho de bananas", "bananas.png");
            return alimento;
        }
        if (idAlimento == "c") {
            Carne alimento = new Carne("c", "Carne", "meat.png");
            alimento.jogadasEfetuadas = jogadasFeitas;
            return alimento;
        }
        if (idAlimento == "m") {
            CogumelosMagicos alimento = new CogumelosMagicos("m", "Cogumelos Magicos", "mushroom.png");
            return alimento;
        }
        return null;
    }

    /*public boolean moveValido(Jogador player, int nrSquares){

        if(player.energiaAtual )
    }*/

    public void proximoPlayer(int countJogadores) {

        countJogadores++;
        if (countJogadores > jogadores.size() - 1) {
            countJogadores = 0;
        }
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {

        ArrayList<Jogador> jogadoresOrdenados = ordenarJogadores();
        Jogador jogadorAJogar = jogadoresOrdenados.get(countJogadores);
        int posJogador = jogadorAJogar.getPosicaoAtual();
        int posDestino = posJogador + nrSquares;
        int energiaGasta = gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares);
        jogadasFeitas++;

        if (!bypassValidations) {
            if (nrSquares < -6 || nrSquares > 6) {
                countJogadores++;
                if (countJogadores > jogadores.size() - 1) {
                    countJogadores = 0;
                }
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }
            if (!jogadorAJogar.especie.podeMover(nrSquares)) {

                countJogadores++;
                if (countJogadores > jogadores.size() - 1) {
                    countJogadores = 0;
                }
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }
        }

        /*for (int i = squares.size()-1; i > 0; i--) {

                if(squares.get(i).identificadoresNoQuadrado != null){

                    int distancia = posDestino - i+1;
                    if(distancia > tamanhoTabuleiro/2){
                        jogadorAJogar.ganhou = true;
                        jogoAcabou = true;
                    }
                }
        }*/

        if (posDestino <= 0) {
            countJogadores++;
            if (countJogadores > jogadores.size() - 1) {
                countJogadores = 0;
            }
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
        }

        if (jogadorAJogar.energiaAtual <= 0 || jogadorAJogar.energiaAtual - gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares) < 0) {

            countJogadores++;
            if (countJogadores > jogadores.size() - 1) {
                countJogadores = 0;
            }
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }

        if (nrSquares == 0) { //descanso
            int nrAleatorio = squares.get(posJogador).cogumelo.nrAleatorio;
            if (jogadorAJogar.energiaAtual + jogadorAJogar.especie.ganhoEnergiaEmDescanso > 200) {
                jogadorAJogar.energiaAtual = 200;
            } else {
                jogadorAJogar.energiaAtual += jogadorAJogar.especie.ganhoEnergiaEmDescanso;
            }
            if (squares.get(posDestino).identificadoresAlimentosNoQuadrado != null) {
                String alimento = squares.get(posDestino).identificadoresAlimentosNoQuadrado;

                if (alimento.equals("e")) {
                    if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Omnívoro")) {

                        jogadorAJogar.energiaAtual += 20;
                        countJogadores++;
                        if (countJogadores > jogadores.size() - 1) {
                            countJogadores = 0;
                        }
                        if (jogadorAJogar.energiaAtual > 200) {
                            jogadorAJogar.energiaAtual = 200;
                        }
                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Erva");
                    } else if (jogadorAJogar.especie.tipo.equals("Carnívoro")) {

                        jogadorAJogar.energiaAtual -= 20;
                        countJogadores++;
                        if (countJogadores > jogadores.size() - 1) {
                            countJogadores = 0;
                        }
                        if (jogadorAJogar.energiaAtual > 200) {
                            jogadorAJogar.energiaAtual = 200;
                        }
                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Erva");
                    }
                }
                if (alimento.equals("a")) {

                    if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Carnívoro")) {

                        jogadorAJogar.energiaAtual += 15;
                        countJogadores++;
                        if (countJogadores > jogadores.size() - 1) {
                            countJogadores = 0;
                        }
                        if (jogadorAJogar.energiaAtual > 200) {
                            jogadorAJogar.energiaAtual = 200;
                        }
                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Agua");

                    } else if (jogadorAJogar.especie.tipo.equals("Omnívoro")) {

                        jogadorAJogar.energiaAtual += ((jogadorAJogar.energiaAtual * 20) / 100);
                        countJogadores++;
                        if (countJogadores > jogadores.size() - 1) {
                            countJogadores = 0;
                        }
                        if (jogadorAJogar.energiaAtual > 200) {
                            jogadorAJogar.energiaAtual = 200;
                        }
                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Agua");
                    }
                }
                if (alimento.equals("b")) {
                    if (jogadorAJogar.bananasConsumidas > 1) {

                        jogadorAJogar.energiaAtual -= 40;
                        countJogadores++;
                        squares.get(posDestino).bananas--;
                        if (countJogadores > jogadores.size() - 1) {
                            countJogadores = 0;
                        }
                        if (jogadorAJogar.energiaAtual > 200) {
                            jogadorAJogar.energiaAtual = 200;
                        }
                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Banana");

                    } else if (squares.get(posDestino).bananas > 0) {

                        jogadorAJogar.energiaAtual += 40;
                        jogadorAJogar.bananasConsumidas++;
                        squares.get(posDestino).bananas--;
                        countJogadores++;
                        if (countJogadores > jogadores.size() - 1) {
                            countJogadores = 0;
                        }
                        if (jogadorAJogar.energiaAtual > 200) {
                            jogadorAJogar.energiaAtual = 200;
                        }
                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Banana");
                    }
                }
                if (alimento.equals("c")) {
                    if (jogadorAJogar.especie.tipo.equals("Herbívoro")) {
                    } else {
                        if (jogadasFeitas > 12) {
                            jogadorAJogar.energiaAtual = jogadorAJogar.energiaAtual / 2;
                            countJogadores++;
                            if (countJogadores > jogadores.size() - 1) {
                                countJogadores = 0;
                            }
                            if (jogadorAJogar.energiaAtual > 200) {
                                jogadorAJogar.energiaAtual = 200;
                            }
                            return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne");
                        }
                        jogadorAJogar.energiaAtual += 50;
                        countJogadores++;
                        if (countJogadores > jogadores.size() - 1) {
                            countJogadores = 0;
                        }
                        if (jogadorAJogar.energiaAtual > 200) {
                            jogadorAJogar.energiaAtual = 200;
                        }
                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne");
                    }
                }
                if (alimento.equals("m")) {
                    if (jogadasFeitas % 2 == 0) {
                        double energia = jogadorAJogar.energiaAtual + ((nrAleatorio * jogadorAJogar.energiaAtual) / 100.0);
                        jogadorAJogar.energiaAtual = (int) Math.round(energia);
                    } else {
                        double energia = jogadorAJogar.energiaAtual - ((nrAleatorio * jogadorAJogar.energiaAtual) / 100.0);
                        jogadorAJogar.energiaAtual = (int) Math.round(energia);
                    }
                    countJogadores++;
                    if (countJogadores > jogadores.size() - 1) {
                        countJogadores = 0;
                    }
                    if (jogadorAJogar.energiaAtual > 200) {
                        jogadorAJogar.energiaAtual = 200;
                    }
                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Cogumelo Magico");
                }
            }
            countJogadores++;
            if (countJogadores > jogadores.size() - 1) {
                countJogadores = 0;
            }
            return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
        }
        if (posDestino >= tamanhoTabuleiro) {

            jogadorAJogar.posicaoAtual = tamanhoTabuleiro;
            jogadorAJogar.energiaAtual -= gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares);
            jogoAcabou = true;
            jogadorAJogar.ganhou = true;
        }
        if (!jogoAcabou) {
            if (posDestino >= tamanhoTabuleiro) {
                posDestino = tamanhoTabuleiro;
                jogadoresOrdenados.get(countJogadores).ganhou = true;
                jogadorAJogar.energiaAtual -= gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares);
                jogoAcabou = true;
            }

            int nrAleatorio = squares.get(posDestino).cogumelo.nrAleatorio;

            for (int i = 0; i < squares.size(); i++) {
                for (int j = 0; j < squares.get(i).identificadoresNoQuadrado.size(); j++) {
                    if (squares.get(i).identificadoresNoQuadrado.get(j) == jogadorAJogar.identificador) {

                        squares.get(i).identificadoresNoQuadrado.remove(Integer.valueOf(jogadorAJogar.identificador));
                        squares.get(posDestino).identificadoresNoQuadrado.add(jogadorAJogar.identificador);
                        jogadorAJogar.posicaoAtual = posDestino;
                        jogadorAJogar.energiaAtual -= gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares);

                        if (posDestino >= tamanhoTabuleiro) {
                            jogadorAJogar.ganhou = true;
                            jogoAcabou = true;
                        }

                        if (squares.get(posDestino).identificadoresAlimentosNoQuadrado != null) {

                            String alimento = squares.get(posDestino).identificadoresAlimentosNoQuadrado;

                            if (alimento.equals("e")) {

                                if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Omnívoro")) {
                                    jogadorAJogar.energiaAtual += 20;
                                    countJogadores++;
                                    if (countJogadores > jogadores.size() - 1) {
                                        countJogadores = 0;
                                    }
                                    if (jogadorAJogar.energiaAtual > 200) {
                                        jogadorAJogar.energiaAtual = 200;
                                    }
                                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Erva");
                                } else if (jogadorAJogar.especie.tipo.equals("Carnívoro")) {

                                    jogadorAJogar.energiaAtual -= 20;
                                    countJogadores++;
                                    if (countJogadores > jogadores.size() - 1) {
                                        countJogadores = 0;
                                    }
                                    if (jogadorAJogar.energiaAtual > 200) {
                                        jogadorAJogar.energiaAtual = 200;
                                    }
                                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Erva");
                                }
                            }

                            if (alimento.equals("a")) {

                                if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Carnívoro")) {

                                    jogadorAJogar.energiaAtual += 15;
                                    countJogadores++;
                                    if (countJogadores > jogadores.size() - 1) {
                                        countJogadores = 0;
                                    }
                                    if (jogadorAJogar.energiaAtual > 200) {
                                        jogadorAJogar.energiaAtual = 200;
                                    }
                                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Agua");

                                } else if (jogadorAJogar.especie.tipo.equals("Omnívoro")) {

                                    jogadorAJogar.energiaAtual += ((jogadorAJogar.energiaAtual * 20) / 100);
                                    countJogadores++;
                                    if (countJogadores > jogadores.size() - 1) {
                                        countJogadores = 0;
                                    }
                                    if (jogadorAJogar.energiaAtual > 200) {
                                        jogadorAJogar.energiaAtual = 200;
                                    }
                                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Agua");
                                }
                            }
                            if (alimento.equals("b")) {
                                if (jogadorAJogar.bananasConsumidas >= 1) {

                                    jogadorAJogar.energiaAtual -= 40;
                                    countJogadores++;
                                    squares.get(posDestino).bananas--;
                                    if (countJogadores > jogadores.size() - 1) {
                                        countJogadores = 0;
                                    }
                                    if (jogadorAJogar.energiaAtual > 200) {
                                        jogadorAJogar.energiaAtual = 200;
                                    }
                                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Bananas");

                                } else if (squares.get(posDestino).bananas > 0) {

                                    jogadorAJogar.energiaAtual += 40;
                                    jogadorAJogar.bananasConsumidas++;
                                    squares.get(posDestino).bananas--;
                                    countJogadores++;
                                    if (countJogadores > jogadores.size() - 1) {
                                        countJogadores = 0;
                                    }
                                    if (jogadorAJogar.energiaAtual > 200) {
                                        jogadorAJogar.energiaAtual = 200;
                                    }
                                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Bananas");
                                }
                            }
                            if (alimento.equals("c")) {
                                if (jogadorAJogar.especie.tipo.equals("Herbívoro")) {
                                } else {
                                    if (jogadasFeitas > 12) {
                                        jogadorAJogar.energiaAtual = jogadorAJogar.energiaAtual / 2;
                                        countJogadores++;
                                        if (countJogadores > jogadores.size() - 1) {
                                            countJogadores = 0;
                                        }
                                        if (jogadorAJogar.energiaAtual > 200) {
                                            jogadorAJogar.energiaAtual = 200;
                                        }
                                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne");
                                    }
                                    jogadorAJogar.energiaAtual += 50;
                                    countJogadores++;
                                    if (countJogadores > jogadores.size() - 1) {
                                        countJogadores = 0;
                                    }
                                    if (jogadorAJogar.energiaAtual > 200) {
                                        jogadorAJogar.energiaAtual = 200;
                                    }
                                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne");
                                }
                            }
                            if (alimento.equals("m")) {
                                if (jogadasFeitas % 2 == 0) {
                                    double energia = jogadorAJogar.energiaAtual + ((nrAleatorio * jogadorAJogar.energiaAtual) / 100.0);
                                    jogadorAJogar.energiaAtual = (int) Math.round(energia);
                                } else {
                                    double energia = jogadorAJogar.energiaAtual - ((nrAleatorio * jogadorAJogar.energiaAtual) / 100.0);
                                    jogadorAJogar.energiaAtual = (int) Math.round(energia);
                                }
                                countJogadores++;
                                if (countJogadores > jogadores.size() - 1) {
                                    countJogadores = 0;
                                }
                                if (jogadorAJogar.energiaAtual > 200) {
                                    jogadorAJogar.energiaAtual = 200;
                                }
                                if (jogadorAJogar.energiaAtual < 0) {
                                    jogadorAJogar.energiaAtual = 0;
                                }
                                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Cogumelo Magico");
                            }
                        }
                        countJogadores++;
                        if (countJogadores > jogadores.size() - 1) {
                            countJogadores = 0;
                        }
                        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
                    }
                }
            }
        }

        countJogadores++;
        if (countJogadores > jogadores.size() - 1) {
            countJogadores = 0;
        }
        return new

                MovementResult(MovementResultCode.VALID_MOVEMENT, null);

    }

    public boolean jogadorAvancado(Jogador jogador) {
        int posicaoJogador = jogador.posicaoAtual;
        int[] posicoesJogadores = ordenarPosicoes();
        int distancia;

        if (posicoesJogadores[posicoesJogadores.length - 1] == posicaoJogador) {
            distancia = posicoesJogadores[posicoesJogadores.length - 2] - posicaoJogador;
        } else {
            distancia = posicoesJogadores[posicoesJogadores.length - 1] - posicaoJogador;
        }

        for (int i = 0; i < jogadores.size(); i++) {
            if (distancia < 0) {
                if (jogadores.get(i).posicaoAtual == (distancia + posicaoJogador)) {

                }
            }
        }

        if (distancia > tamanhoTabuleiro / 2) {
            jogador.ganhou = true;
            jogoAcabou = true;
            return true;
        }
        return false;
    }

    public int[] ordenarPosicoes() {
        int[] posicoesOrdenadas = new int[jogadores.size()];
        int[] idsOrdenados = ordenarIds();

        for (int i = 0; i < idsOrdenados.length; i++) {
            for (int j = 0; j < jogadores.size(); j++) {
                if (jogadores.get(j).getIdentificador() == idsOrdenados[i]) {
                    posicoesOrdenadas[i] = jogadores.get(i).getPosicaoAtual();
                }
            }
        }
        for (int i = 0; i < posicoesOrdenadas.length; i++) {
            for (int j = 0; j < i; j++) {
                if (posicoesOrdenadas[i] > posicoesOrdenadas[j]) {
                    int temp = posicoesOrdenadas[i];
                    posicoesOrdenadas[i] = posicoesOrdenadas[j];
                    posicoesOrdenadas[j] = temp;
                }
            }
        }
        return posicoesOrdenadas;
    }

    public String[] getWinnerInfo() {

        String[] winnerInfo = new String[4];

        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).ganhou() == true) {
                winnerInfo[0] = String.valueOf(jogadores.get(i).getIdentificador());
                winnerInfo[1] = jogadores.get(i).getNome();
                winnerInfo[2] = jogadores.get(i).getEspecieDoJogador();
                winnerInfo[3] = String.valueOf(jogadores.get(i).getEnergiaAtual());
                return winnerInfo;
            }
        }
        return null;
    }


    public ArrayList<String> getGameResults() {

        int classificacao = 1;
        String nomeDaEspecie = "";
        ArrayList<String> resultadosDoJogo = new ArrayList<>();
        ArrayList<Jogador> ordenadosClassificacao = new ArrayList<>();
        ArrayList<Jogador> jogadoresOrdenados = ordenarJogadores();
        int[] posicoes = ordenarPosicoes();

        for (int i = 0; i < posicoes.length; i++) {
            for (int j = 0; j < jogadoresOrdenados.size(); j++) {
                if (jogadoresOrdenados.get(j).getPosicaoAtual() == posicoes[i]) {
                    if (!ordenadosClassificacao.contains(jogadoresOrdenados.get(j))) {
                        ordenadosClassificacao.add(jogadoresOrdenados.get(j));
                    }
                }
            }
        }

        for (int i = 0; i < ordenadosClassificacao.size(); i++) {
            if (Objects.equals(ordenadosClassificacao.get(i).getEspecieDoJogador(), "T")) {
                nomeDaEspecie = "Tartaruga";
            }
            if (Objects.equals(ordenadosClassificacao.get(i).getEspecieDoJogador(), "L")) {
                nomeDaEspecie = "Leão";
            }
            if (Objects.equals(ordenadosClassificacao.get(i).getEspecieDoJogador(), "P")) {
                nomeDaEspecie = "Pássaro";
            }
            if (Objects.equals(ordenadosClassificacao.get(i).getEspecieDoJogador(), "Z")) {
                nomeDaEspecie = "Tarzan";
            }
            if (Objects.equals(ordenadosClassificacao.get(i).getEspecieDoJogador(), "E")) {
                nomeDaEspecie = "Elefante";
            }
            resultadosDoJogo.add("#" + classificacao + " " + ordenadosClassificacao.get(i).getNome() + ", " + nomeDaEspecie + ", " + ordenadosClassificacao.get(i).getPosicaoAtual());
            classificacao++;
        }


        return resultadosDoJogo;
    }

    public JPanel getAuthorsPanel() {

        JFrame f = new JFrame("Authors");
        JPanel panel = new JPanel();
        panel.setBounds(40, 80, 200, 200);
        panel.setBackground(Color.white);
        JButton b1 = new JButton("Botão 1");
        b1.setBounds(50, 100, 80, 30);
        b1.setBackground(Color.pink);
        JButton b2 = new JButton("Botão 2");
        b2.setBounds(100, 100, 80, 30);
        b2.setBackground(Color.pink);
        f.add(b1);
        f.add(b2);
        f.add(panel);
        f.setSize(300, 300);
        f.setLayout(null);
        f.setVisible(true);

        return panel;
    }

    public String whoIsTaborda() {

        return "Wrestling";
    }

}