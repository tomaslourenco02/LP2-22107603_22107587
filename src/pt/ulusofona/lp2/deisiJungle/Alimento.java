package pt.ulusofona.lp2.deisiJungle;

public abstract class Alimento {

    protected String identificador;
    protected String nome;
    protected String imagem;
    protected int posicao;

    public Alimento(String identificador, String nome, String imagem) {
        this.identificador = identificador;
        this.nome = nome;
        this.imagem = imagem;
    }

    public int getPosicao() {
        return posicao;
    }

    abstract String info();
}
