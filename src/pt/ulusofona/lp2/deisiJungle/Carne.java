package pt.ulusofona.lp2.deisiJungle;

public class Carne extends Alimento{

    int jogadasEfetuadas;

    //Se ingerido por carnívoros (ex: Leão) ou omnívoros (ex: Tarzan), aumenta a energia em 50 unidades
    //Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
    //Deteriora-se à medida que o tempo passa. Só é comestível nas primeiras 12 jogadas. A partir daí, se fôr ingerida, reduz para metade a energia do animal.

    public Carne(String identificador, String nome, String imagem) {
        super("c", "Carne", "meat.png");
    }

    @Override
    String info() {
        if(jogadasEfetuadas > 12){
            return "Carne toxica";
        }
        String info ="Carne : + 50 energia : " + jogadasEfetuadas + " jogadas";
        return info;
    }

    @Override
    void energiaFornecida(Jogador jogador, int jogadasFeitas) {
        if (jogador.especie.tipo.equals("Herbívoro")) {
            jogador.nrAlimentosIngeridos--;
        } else {
            if (jogadasFeitas > 12) {
                jogador.energiaAtual = jogador.energiaAtual / 2;
            }
        }
    }

    @Override
    public String toString() {
        return "Apanhou Carne";
    }
}
