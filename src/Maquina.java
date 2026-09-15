public abstract class Maquina {
    //atributos
    private String nome;
    private boolean ligada;
    private double capacidadeMaxima;
    private double probabilidadeFalha;
    private double custoOperacao;

    //constructor
    public Maquina(String nome, double capacidadeMaxima, double probabilidadeFalha, double custoOperacao) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.probabilidadeFalha = probabilidadeFalha;
        this.custoOperacao = custoOperacao;
        this.ligada = false;
    }

    //métodos concretos
    public void ligar() {
        ligada = true;
    }
    public void desligar() {
        ligada = false;
    }
    public String getNome() {
        return nome;
    }
    public boolean estaLigada() {
        return ligada;
    }
    public boolean verificarCapacidade(double demanda) {
        return (demanda <= capacidadeMaxima);
    }
    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    public double getCustoOperacao() {
        return custoOperacao;
    }
    public double verificarFalha() {
        if (Math.random() < this.probabilidadeFalha)
            return this.probabilidadeFalha;
        else
            return 0;   
    }

    //métodos abstratos
    public abstract boolean processar(Produto produto, double demanda);
    public abstract String getTipo();

}