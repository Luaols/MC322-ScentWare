import java.util.Random;

public abstract class Maquina implements Auditavel {
    private String nome;
    private boolean ligada;
    private double capacidadeMaxima;
    private double probabilidadeFalha;
    private double custoOperacao;
    private double health;

    private Random random = new Random();

    public Maquina(
            String nome,
            double capacidadeMaxima,
            double probabilidadeFalha,
            double custoOperacao,
            double health
    ) {
        this.nome = nome;
        this.ligada = false;
        this.capacidadeMaxima = capacidadeMaxima;
        this.probabilidadeFalha = probabilidadeFalha;
        this.custoOperacao = custoOperacao;
        this.health = health;
    }

    public abstract boolean processar(Produto produto);

    public abstract String getTipo();

    @Override 
    public boolean precisaManutencao(){
        if (health < 30){
            return true;
        }
        else {
            return false;
        }
    }
    public void reparar(){
        health = 100;
    }
    public double getHealth(){
        return health;
    }

    public void ligar() {
        ligada = true;
    }

    public void desligar() {
        ligada = false;
    }

    public boolean estaLigada() {
        return ligada;
    }

    public String getNome() {
        return nome;
    }

    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public double getCustoOperacao() {
        return custoOperacao;
    }

    protected double getProbabilidadeFalha() {
        return probabilidadeFalha;
    }

    // Verifica se a falha acontece de acordo com a probabilidade da maquina
    protected boolean verificarFalha() {
        return random.nextDouble() < probabilidadeFalha;
    }
}
