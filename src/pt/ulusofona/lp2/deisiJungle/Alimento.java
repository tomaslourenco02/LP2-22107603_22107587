package pt.ulusofona.lp2.deisiJungle;

public abstract class Alimento {

    protected String identificador;
    protected String nome;
    protected String imagem;
    protected String foodPos;

    public Alimento(String identificador, String nome, String imagem, String foodPos) {
        this.identificador = identificador;
        this.nome = nome;
        this.imagem = imagem;
        this.foodPos = foodPos;
    }

    public String getPosicao() {
        return foodPos;
    }

    abstract String info();
}
