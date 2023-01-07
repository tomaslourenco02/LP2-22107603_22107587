package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;


public class GameManager {
    ArrayList<Jogador> jogadores = new ArrayList<>();
    ArrayList<SquareInfo> squares = new ArrayList<>();
    int countJogadores = 0;
    int jogadorVencedorID = 0;
    int tamanhoTabuleiro = 0;
    int jogadasFeitas = 0;
    boolean jogoAcabou = false;

    public GameManager() {
    }

    public GameManager(ArrayList<Jogador> jogadores, ArrayList<SquareInfo> squares, int countJogadores, int jogadorVencedorID, int tamanhoTabuleiro, int jogadasFeitas, boolean jogoAcabou, int count) {
        this.jogadores = jogadores;
        this.squares = squares;
        this.countJogadores = countJogadores;
        this.jogadorVencedorID = jogadorVencedorID;
        this.tamanhoTabuleiro = tamanhoTabuleiro;
        this.jogadasFeitas = jogadasFeitas;
        this.jogoAcabou = jogoAcabou;
        this.count = count;
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

    public void createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) throws InvalidInitialJungleException{
        jogadores.clear();
        squares.clear();
        countJogadores = 0;
        jogadorVencedorID = 0;
        tamanhoTabuleiro = jungleSize;
        jogoAcabou = false;
        count = 0; int menorID = 0;

        if (foodsInfo != null) {
            for (int i = 0; i < foodsInfo.length; i++) {
                if (!verificaIdAlimentos(foodsInfo[i][0])) {
                    throw  new InvalidInitialJungleException("Erro na inicialização do terreno!", false, true); }
            }
        }

        if (foodsInfo != null) {
            for (int i = 0; i < foodsInfo.length; i++) {
                if (foodsInfo[i][1].matches("[a-zA-Z]+")) { throw  new InvalidInitialJungleException("Erro na inicialização do terreno!", false, true); }
                if (foodsInfo[i][1].matches("[0-9]*")) {
                    if (Integer.parseInt(foodsInfo[i][1]) >= jungleSize || Integer.parseInt(foodsInfo[i][1]) <= 1) {
                        throw  new InvalidInitialJungleException("Erro na inicialização do terreno!", false, true);                    }
                }
            }
        }

        if (!verificaJogadores(playersInfo)) { throw new InvalidInitialJungleException("Erro na inicialização do terreno!", true, false);}

        if (jungleSize < playersInfo.length * 2) { throw new InvalidInitialJungleException("Erro na inicialização do terreno!", true, false);}

        for (int i = 0; i < jungleSize; i++) { squares.add(new SquareInfo()); }

        for (int i = 0; i < playersInfo.length; i++) {
            jogadores.add(new Jogador(Integer.parseInt(playersInfo[i][0]), playersInfo[i][1], playersInfo[i][2]));
            if (squares != null) { squares.get(0).identificadoresNoQuadrado.add(Integer.valueOf(playersInfo[i][0])); }
        }

        if (foodsInfo != null) {
            for (int i = 0; i < foodsInfo.length; i++) {
                for (int j = 0; j < squares.size(); j++) {
                    if (j == Integer.parseInt(foodsInfo[i][1])) { squares.get(j).identificadoresAlimentosNoQuadrado = foodsInfo[i][0]; }
                    if (foodsInfo[i][0].equals("m")) {
                        squares.get(i).cogumelo = new CogumelosMagicos("m", "Cogumelos Magicos", "mushroom.png");
                    }
                    if (foodsInfo[i][0].equals("b")) { squares.get(j).bananas = 3; }
                }
            }
        }
        for (int i = 0; i < jogadores.size(); i++) {
            for (int j = 0; j < jogadores.size(); j++) {
                if (i != j) {
                    if (jogadores.get(i).getIdentificador() < jogadores.get(j).getIdentificador()) {
                        menorID = jogadores.get(i).getIdentificador();
                    } else { menorID = jogadores.get(j).getIdentificador(); }
                }
            }
        }
        for (int i = 0; i < jogadores.size(); i++) {
            if (menorID == jogadores.get(i).getIdentificador()) { jogadores.get(i).aJogar = true; }
        }
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException{

        try{
            createInitialJungle(jungleSize,playersInfo,null);
        } catch (InvalidInitialJungleException e){
            throw new InvalidInitialJungleException("Erro na inicialização do terreno!", true, true);
        }
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
                        if(Objects.equals(squares.get(squareNr).identificadoresAlimentosNoQuadrado, "m")){
                            Alimento alimento = squares.get(squareNr).cogumelo;
                            squareInfo[0] = alimento.imagem;
                            squareInfo[1] = alimento.info();
                            squareInfo[2] = identificadores.toString();
                        }else
                            if (squares.get(squareNr).identificadoresAlimentosNoQuadrado.equals("b")) {
                                Alimento alimento = definirAlimento(squares.get(squareNr).identificadoresAlimentosNoQuadrado);
                                squareInfo[0] = alimento.imagem;
                                squareInfo[1] = "Bananas : " + squares.get(squareNr).bananas + " : + 40 energia";
                                squareInfo[2] = identificadores.toString();
                        } else {
                            Alimento alimento = definirAlimento(squares.get(squareNr).identificadoresAlimentosNoQuadrado);
                            squareInfo[0] = alimento.imagem;
                            squareInfo[1] = alimento.info();
                            squareInfo[2] = identificadores.toString();
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

        if (posDestino < 1) { posDestino = 1; }

        if (posDestino > tamanhoTabuleiro) { posDestino = tamanhoTabuleiro; }
        if (squares.get(posDestino).identificadoresAlimentosNoQuadrado != null) {
            String alimento = squares.get(posDestino).identificadoresAlimentosNoQuadrado;
            if (alimento.equals("e")) {
                if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Omnívoro")) {
                    ganhoDeEnergia = 20;
                } else if (jogadorAJogar.especie.tipo.equals("Carnívoro")) {
                    ganhoDeEnergia = -20;
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
                squares.get(posJogador).cogumelo.energiaFornecida(jogadorAJogar, jogadasFeitas);
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

    public GameManager(ArrayList<Jogador> jogadores, ArrayList<SquareInfo> squares, int countJogadores, int jogadorVencedorID, int tamanhoTabuleiro, int jogadasFeitas, boolean jogoAcabou) {
        this.jogadores = jogadores;
        this.squares = squares;
        this.countJogadores = countJogadores;
        this.jogadorVencedorID = jogadorVencedorID;
        this.tamanhoTabuleiro = tamanhoTabuleiro;
        this.jogadasFeitas = jogadasFeitas;
        this.jogoAcabou = jogoAcabou;
    }

    public boolean saveGame(File file) {

        StringBuilder texto = new StringBuilder();
        texto.append("Tamanho tabuleiro: \n");
        texto.append(tamanhoTabuleiro).append("\n");
        texto.append("Jogadas feitas: \n");
        texto.append(jogadasFeitas).append("\n");
        texto.append("Nr de jogadores: \n");
        texto.append(jogadores.size()).append("\n");
        texto.append("Count jogadores: \n");
        texto.append(countJogadores).append("\n");
        texto.append("ID jogador vencedor: \n");
        texto.append(jogadorVencedorID).append("\n");
        texto.append("Jogo Acabou: \n");
        texto.append(jogoAcabou).append("\n");

        for (int i = 0; i < squares.size(); i++) {
            texto.append("Quadrado: ").append(i).append("\n");
            for (int k = 0; k < squares.get(i).identificadoresNoQuadrado.size(); k++) {
                for (int j = 0; j < jogadores.size(); j++) {

                    if (squares.get(i).identificadoresNoQuadrado.get(k) == jogadores.get(j).identificador) {
                        texto.append(squares.get(i).identificadoresNoQuadrado.get(k)).append(";");
                        texto.append(jogadores.get(j).nome).append(";");
                        texto.append(jogadores.get(j).energiaAtual).append(";");
                        texto.append(jogadores.get(j).especieDoJogador).append(" - ");
                    }

                }
            }
            texto.append(squares.get(i).identificadoresAlimentosNoQuadrado).append("\n");
        }

        try {
            FileWriter fw = new FileWriter(file, false);

            PrintWriter pw = new PrintWriter(fw);

            pw.println(texto);

            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean loadGame(File file) {

        ArrayList<SquareInfo> squaresLoad = new ArrayList<>();
        ArrayList<Jogador> jogadoresLoad = new ArrayList<>();
        ArrayList<Alimento> alimentosLoad = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals("Tamanho tabuleiro: ")) { tamanhoTabuleiro = Integer.parseInt(myReader.nextLine()); }
                if (data.equals("Jogadas feitas: ")) { jogadasFeitas = Integer.parseInt(myReader.nextLine()); }
                if (data.equals("Count jogadores: ")) { countJogadores = Integer.parseInt(myReader.nextLine()); }
                if (data.equals("ID jogador vencedor: ")) { jogadorVencedorID = Integer.parseInt(myReader.nextLine());}
                for (int i = 0; i <= tamanhoTabuleiro; i++) {
                    if (data.equals("Quadrado: " + i + "")) {
                        squaresLoad.add(new SquareInfo());
                        String[] info = myReader.nextLine().split(" - ");
                        for (int j = 0; j < info.length; j++) {
                            if (!info[j].equals("null")) {
                                String[] info2 = info[j].split(";");
                                if (!(info2[0].equals("null"))) {
                                    if (info2[0].matches("[0-9]*")) {
                                        if (Integer.parseInt(info2[0]) > 0) {
                                            squaresLoad.add(new SquareInfo());
                                            Jogador jogador;
                                            if (i == 0) {
                                                jogador = new Jogador(Integer.parseInt(info2[0]), info2[1], Integer.parseInt(info2[2]), info2[3], 1);
                                            } else {
                                                jogador = new Jogador(Integer.parseInt(info2[0]), info2[1], Integer.parseInt(info2[2]), info2[3], i);
                                            }
                                            jogadoresLoad.add(jogador);
                                            squaresLoad.get(i).identificadoresNoQuadrado.add(jogador.identificador);
                                        }
                                    } else if (info2[0].matches("[a-zA-Z]+")) {
                                        Alimento alimento = definirAlimento(info2[0].trim());
                                        if (alimento != null) {
                                            squaresLoad.add(new SquareInfo());
                                            alimento.posicao = i;
                                            alimentosLoad.add(alimento);
                                            squaresLoad.get(i).identificadoresAlimentosNoQuadrado = alimento.identificador;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            jogadores = jogadoresLoad;
            ordenarJogadores();
            squares = squaresLoad;
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return true;
        }
        return true;
    }

    public int jglSize() {
        return tamanhoTabuleiro;
    }


    public int gastaEnergia(int consumoEnergia, int nrSquares) {

        int energiaFinal = consumoEnergia * nrSquares;

        if (energiaFinal < 0) {
            energiaFinal *= -1;
        }

        return energiaFinal;
    }

    public Alimento definirAlimento(String idAlimento) {

        if (idAlimento.equals("e")) {
            Erva alimento = new Erva("e", "Erva", "grass.png");
            return alimento;
        }
        if (idAlimento.equals("a")) {
            Agua alimento = new Agua("a", "Agua", "water.png");
            return alimento;
        }
        if (idAlimento.equals("b")) {
            CachoDeBananas alimento = new CachoDeBananas("b", "Cacho de bananas", "bananas.png");
            return alimento;
        }
        if (idAlimento.equals("c")) {
            Carne alimento = new Carne("c", "Carne", "meat.png");
            alimento.jogadasEfetuadas = jogadasFeitas;
            return alimento;
        }
        if (idAlimento.equals("m")) {
            CogumelosMagicos alimento = new CogumelosMagicos("m", "Cogumelos Magicos", "mushroom.png");
            return alimento;
        }
        return null;
    }

    public void proximoPlayer(int countJogadores) {

        countJogadores++;
        if (countJogadores > jogadores.size() - 1) {
            countJogadores = 0;
        }
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        ArrayList<Jogador> jogadoresOrdenados = ordenarJogadores();
        Jogador jogadorAJogar = jogadoresOrdenados.get(countJogadores);
        int posJogador = jogadorAJogar.getPosicaoAtual(); int posDestino = posJogador + nrSquares;
        int energiaGasta = gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares);
        jogadasFeitas++;

        if (!bypassValidations) {
            if (nrSquares < -6 || nrSquares > 6) { turnosJogadores();
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null); }
            if (!jogadorAJogar.especie.podeMover(nrSquares)) { turnosJogadores();
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null); }
        }
        if (posDestino <= 0) { turnosJogadores();
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null); }

        if (jogadorAJogar.energiaAtual <= 0 || jogadorAJogar.energiaAtual - gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares) < 0) {
            turnosJogadores();
            return new MovementResult(MovementResultCode.NO_ENERGY, null); }

        if (nrSquares < 0) { jogadorAJogar.nrCasasMovimentou += nrSquares * (-1);
        } else { jogadorAJogar.nrCasasMovimentou += nrSquares; }

        if (nrSquares == 0) { //descanso
            if (jogadorAJogar.energiaAtual + jogadorAJogar.especie.ganhoEnergiaEmDescanso > 200) { jogadorAJogar.energiaAtual = 200;
            } else { jogadorAJogar.energiaAtual += jogadorAJogar.especie.ganhoEnergiaEmDescanso; }
            if (squares.get(posDestino).identificadoresAlimentosNoQuadrado != null) {
                jogadorAJogar.nrAlimentosIngeridos++;
                String alimento = squares.get(posDestino).identificadoresAlimentosNoQuadrado;
                if(energiaFornecidaAlimento(jogadorAJogar, alimento)){
                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, definirAlimento(alimento).toString());
                }
            } turnosJogadores();
            return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
        }
        if (posDestino >= tamanhoTabuleiro) {
            jogadorAJogar.posicaoAtual = tamanhoTabuleiro;
            jogadorAJogar.energiaAtual -= gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares);
            jogoAcabou = true; jogadorAJogar.ganhou = true;
        }
        if (!jogoAcabou) {
            for (int i = 0; i < squares.size(); i++) {
                for (int j = 0; j < squares.get(i).identificadoresNoQuadrado.size(); j++) {
                    if (squares.get(i).identificadoresNoQuadrado.get(j) == jogadorAJogar.identificador) {

                        squares.get(i).identificadoresNoQuadrado.remove(Integer.valueOf(jogadorAJogar.identificador));
                        squares.get(posDestino).identificadoresNoQuadrado.add(jogadorAJogar.identificador);
                        jogadorAJogar.posicaoAtual = posDestino;
                        jogadorAJogar.energiaAtual -= gastaEnergia(jogadorAJogar.especie.consumoEnergia, nrSquares);

                        if (posDestino >= tamanhoTabuleiro) { jogadorAJogar.ganhou = true; jogoAcabou = true; }
                        if (jogadasFeitas > jogadores.size() - 1) {if (jogadorAvancado()) { jogoAcabou = true;} }
                        if (squares.get(posDestino).identificadoresAlimentosNoQuadrado != null) {
                            jogadorAJogar.nrAlimentosIngeridos++;
                            String alimento = squares.get(posDestino).identificadoresAlimentosNoQuadrado;
                            if(energiaFornecidaAlimento(jogadorAJogar, alimento)){
                                return new MovementResult(MovementResultCode.CAUGHT_FOOD, definirAlimento(alimento).toString());
                            }
                        } turnosJogadores();
                        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
                    }
                }
            }
        }turnosJogadores();
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
    }

    public boolean energiaFornecidaAlimento(Jogador jogadorAJogar, String alimento) {
        if (alimento.equals("e")) {
            if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Omnívoro")) {
                jogadorAJogar.energiaAtual += 20;
                turnosJogadores();
                if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
                return true;
            } else if (jogadorAJogar.especie.tipo.equals("Carnívoro")) {
                jogadorAJogar.energiaAtual -= 20;
                turnosJogadores();
                if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
                return true; }
        } else if (alimento.equals("a")) {
            if (jogadorAJogar.especie.tipo.equals("Herbívoro") || jogadorAJogar.especie.tipo.equals("Carnívoro")) {
                jogadorAJogar.energiaAtual += 15;
                turnosJogadores();
                if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
                return true;
            } else if (jogadorAJogar.especie.tipo.equals("Omnívoro")) {
                jogadorAJogar.energiaAtual += ((jogadorAJogar.energiaAtual * 20) / 100);
                turnosJogadores();
                if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
                return true;
            }
        } else if (alimento.equals("b")) {
            if (jogadorAJogar.bananasConsumidas >= 1) { jogadorAJogar.energiaAtual -= 40;
                squares.get(jogadorAJogar.posicaoAtual).bananas--;
                turnosJogadores();
                if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
                return true;

            } else if (squares.get(jogadorAJogar.posicaoAtual).bananas > 0) { jogadorAJogar.energiaAtual += 40;
                jogadorAJogar.bananasConsumidas++;
                squares.get(jogadorAJogar.posicaoAtual).bananas--;
                turnosJogadores();
                if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
                return true;
            }
        } else if (alimento.equals("c")) {
            if (jogadorAJogar.especie.tipo.equals("Herbívoro")) { jogadorAJogar.nrAlimentosIngeridos--;
            } else {
                if (jogadasFeitas > 12) {
                    jogadorAJogar.energiaAtual = jogadorAJogar.energiaAtual / 2;
                    turnosJogadores();
                    if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
                    return true;
                }
                jogadorAJogar.energiaAtual += 50;
                turnosJogadores();
                if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
                return true;
            }
        } else if (alimento.equals("m")) {
            squares.get(jogadorAJogar.posicaoAtual).cogumelo.energiaFornecida(jogadorAJogar, jogadasFeitas);
            turnosJogadores();
            if (jogadorAJogar.energiaAtual > 200) { jogadorAJogar.energiaAtual = 200; }
            return true;
        }
        return false;
    }

    public boolean jogadorAvancado() {
        ArrayList<Jogador> jogadoresOrdenados = ordenarJogadores();
        int[] posicoesJogadores = ordenarPosicoes();
        int distancia;
        for (int i = 0; i < jogadoresOrdenados.size(); i++) {
            distancia = posicoesJogadores[0] - posicoesJogadores[1];
            if (distancia >= tamanhoTabuleiro / 2) {
                if (jogadoresOrdenados.get(i).posicaoAtual == posicoesJogadores[1]) {
                    jogadoresOrdenados.get(i).ganhou = true;
                    jogoAcabou = true;
                    return true;
                }
            }
        }
        return false;
    }

    public void turnosJogadores(){
        countJogadores++;
        if (countJogadores > jogadores.size() - 1) {
            countJogadores = 0;
        }
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

        for (int i = 0; i < jogadoresOrdenados.size(); i++) {
            if (jogadoresOrdenados.get(i).ganhou) {
                if (!ordenadosClassificacao.contains(jogadoresOrdenados.get(i))) {
                    ordenadosClassificacao.add(jogadoresOrdenados.get(i));
                }
            }
        }

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
                nomeDaEspecie = "Leao";
            }
            if (Objects.equals(ordenadosClassificacao.get(i).getEspecieDoJogador(), "P")) {
                nomeDaEspecie = "Passaro";
            }
            if (Objects.equals(ordenadosClassificacao.get(i).getEspecieDoJogador(), "Z")) {
                nomeDaEspecie = "Tarzan";
            }
            if (Objects.equals(ordenadosClassificacao.get(i).getEspecieDoJogador(), "E")) {
                nomeDaEspecie = "Elefante";
            }
            resultadosDoJogo.add("#" + classificacao + " " + ordenadosClassificacao.get(i).getNome() + ", " + nomeDaEspecie + ", " + ordenadosClassificacao.get(i).getPosicaoAtual() + ", "
                    + ordenadosClassificacao.get(i).nrCasasMovimentou + ", " + ordenadosClassificacao.get(i).nrAlimentosIngeridos);
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