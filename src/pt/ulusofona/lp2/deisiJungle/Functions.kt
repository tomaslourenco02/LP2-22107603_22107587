package pt.ulusofona.lp2.deisiJungle


enum class CommandType {
    GET, POST
}

fun router(): Function1<CommandType, Function2<GameManager, List<String>, String?>> {
    return { commandType ->
        when (commandType) {
            CommandType.GET -> ::getComando
            CommandType.POST -> ::postComando
        }
    }
}


fun criaComandos(commandType: CommandType): Function2<GameManager, List<String>, String?> {
    return when (commandType) {
        CommandType.GET -> { gameManager, args -> getComando(gameManager, args) }
        CommandType.POST -> { gameManager, args -> postComando(gameManager, args) }
    }
}

fun getComando(gameManager: GameManager, args: List<String>): String? {
    if (args.isEmpty()) {
        return null
    }
    when (args[0]) {
        "PLAYER_INFO" -> return getPlayerInfo(gameManager, args.drop(1))
        "PLAYERS_BY_SPECIE" -> return getPlayersBySpecies(gameManager, args.drop(1));
        "MOST_TRAVELED" -> return getMostTraveledPlayer(gameManager)
        "TOP_ENERGETIC_OMNIVORES" -> return getTopEnergeticOmnivores(gameManager, args.drop(1))
        "CONSUMED_FOODS" -> return getConsumedFood(gameManager)
        else -> return null
    }
}

fun postComando(gameManager: GameManager, args: List<String>): String? {
    if (args.isEmpty()) {
        return null
    }
    when (args[0]) {
        "MOVE" -> return postMove(gameManager, args.drop(1))
        else -> return null
    }
}

fun getPlayerInfo(manager: GameManager, args: List<String>): String? {
    if (args.isNotEmpty()) {
        val nomePretendido = args[0]
        val jogadores: List<Jogador> = manager.jogadores.filter { it.nome.equals(nomePretendido) }
        if (jogadores.isEmpty()) {
            return "Inexistent player"
        }
        val jogadorPretendido: Jogador = jogadores[0];

        val id = jogadorPretendido.identificador
        val nome = jogadorPretendido.nome
        val especie = jogadorPretendido.especie.nome
        val energia = jogadorPretendido.energiaAtual
        val posicao = jogadorPretendido.posicaoAtual

        if (jogadorPretendido != null) {
            return "$id | $nome | $especie | $energia | $posicao"
        }
    }
    return "Inexistent player"
}

fun getPlayersBySpecies(manager: GameManager, args: List<String>): String? {
    if (args.isNotEmpty()) {
        val especiePretendida = args[0];
        val jogadores: List<Jogador> = manager.jogadores.filter { it.especieDoJogador.equals(especiePretendida) }
        val jogadoresOrdenados: List<Jogador> = jogadores.sortedByDescending { it.nome };
        val nomes: ArrayList<String> = ArrayList()

        if (jogadores.isEmpty()) {
            return ""
        }

        jogadoresOrdenados.forEach {
            val nomeJogador = it.nome
            nomes.add(nomeJogador)
        }

        val nomesOrdenados = nomes.sortedByDescending { it }
        val stringNomes: String = nomesOrdenados.joinToString(separator = ",")

        return stringNomes
    }

    return ""
}

fun getMostTraveledPlayer(manager: GameManager): String {
    val jogadores: List<Jogador> = manager.jogadores
    val jOrdenadosPelaDistancia: List<Jogador> = jogadores.sortedByDescending { it.nrCasasMovimentou }
    var string: String = ""
    var casasAndadas: Int = 0;

    jOrdenadosPelaDistancia.forEach {
        val nome = it.nome
        val especie = it.especie.identificador
        val movimento = it.nrCasasMovimentou

        casasAndadas += movimento

        string += "$nome:$especie:$movimento\n"
    }
    string += "Total:$casasAndadas"

    return string
}

fun getTopEnergeticOmnivores(manager: GameManager, args: List<String>): String? {
    var max_results = args[0].toInt()
    var count: Int = 0
    val jogadores: List<Jogador> = manager.jogadores.filter { it.especie.tipo.equals("Omnívoro") }
    val jogadoresOrdenados: List<Jogador> = jogadores.sortedByDescending { it.energiaAtual }
    var string: String = ""

    jogadoresOrdenados.forEach {
        val nome = it.nome
        val energia = it.energiaAtual

        if (max_results == count) {
            return string.trim()
        }
        if (jogadoresOrdenados.size - 1 == count) {
            string += "$nome:$energia"
            return string
        } else {
            string += "$nome:$energia\n"
            count++
        }
    }

    return string;
}

fun getConsumedFood(manager: GameManager): String? {
    var string: String = ""
    manager.alimentosIngeridos.forEach {
        val alimentos = manager.alimentosIngeridos.sorted();
        string = alimentos.joinToString(separator = "\n")
    }
    return string
}

fun postMove(manager: GameManager, args: List<String>): String? {
    var nrCasasAMover = args[0].toInt()
    var infoJogadorAtual = manager.currentPlayerInfo
    var idJogador = infoJogadorAtual[0].toInt()
    val jogadores: List<Jogador> = manager.jogadores.filter { it.identificador == idJogador }
    val jogador: Jogador = jogadores[0]

    var posicaoFutura = nrCasasAMover + jogador.posicaoAtual
    if(posicaoFutura < 1){
        manager.turnosJogadores()
        return "Movimento invalido"
    }
    if(manager.jogoAcabou){
        manager.jogoAcabou = false;
    }
    if(posicaoFutura >= manager.tamanhoTabuleiro){
        posicaoFutura = manager.tamanhoTabuleiro
        nrCasasAMover = posicaoFutura - jogador.posicaoAtual
        manager.moveCurrentPlayer(nrCasasAMover, true)
        return "OK"
    }
    val alimento = manager.squares.get(posicaoFutura).identificadoresAlimentosNoQuadrado;
    val movimento = manager.moveCurrentPlayer(nrCasasAMover, true)

    if(movimento == MovementResult(MovementResultCode.VALID_MOVEMENT, null)){
        return "OK"
    }
    if (movimento == MovementResult(MovementResultCode.INVALID_MOVEMENT, null)) {
        return "Movimento invalido"
    } else if (movimento == MovementResult(MovementResultCode.NO_ENERGY, null)) {
            return "Sem energia"
    }else if (alimento != null) {
        if (movimento == MovementResult(MovementResultCode.CAUGHT_FOOD, manager.definirAlimento(alimento).toString())) {
            return "Apanhou comida"
        }
        if(movimento == MovementResult(MovementResultCode.VALID_MOVEMENT, null)){
            return "OK"}
    }
    return ""
}


