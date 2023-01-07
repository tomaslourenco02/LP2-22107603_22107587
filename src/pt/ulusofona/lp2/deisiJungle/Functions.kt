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
        "PLAYERS_BY_SPECIE" -> return getPlayersBySpecies(gameManager, args)
        "MOST_TRAVELED" -> return getMostTraveledPlayer(gameManager)
        "TOP_ENERGETIC_OMNIVORES" -> return getTopEnergeticOmnivores(gameManager)
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
    if(args.isNotEmpty()) {
        val nomePretendido = args[0]
        val jogadores: List<Jogador> = manager.jogadores.filter { it.nome.equals(nomePretendido) }
        if (jogadores.isEmpty()) {
            return ""
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
    if (args.size < 1) {
        return ""
    }

    if(args.isNotEmpty()) {
        val especiePretendida = args[0];
        val jogadores: List<Jogador> = manager.jogadores.filter { it.especieDoJogador.equals(especiePretendida) }
        val nomes: ArrayList<String> = ArrayList()

        if (jogadores.isEmpty()) {
            return ""
        }

        jogadores.forEach {
            val nomeJogador = it.nome
            nomes.add(nomeJogador)
        }

        val nomesOrdenados = nomes.sorted()
        val stringNomes: String = nomesOrdenados.joinToString(separator = ",")

        if (nomes.size == 0) {
            return stringNomes
        } else
            return ""
    }
    return ""
}

fun getMostTraveledPlayer(manager: GameManager): String? {
    val jogadores: List<Jogador> = manager.jogadores
    val jOrdenadosPelaDistancia: List<Jogador> = jogadores.sortedByDescending { it.nrCasasMovimentou }
    val string: String

    jOrdenadosPelaDistancia.forEach {

    }
return null;
}

fun getTopEnergeticOmnivores(manager: GameManager):String?{
    return null;
}

fun getConsumedFood(manager: GameManager):String?{
    return null;
}

fun postMove(manager: GameManager, args: List<String>):String?{
    return null;
}
