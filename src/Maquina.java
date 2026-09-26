import java.util.Random;

public abstract class Maquina implements Auditavel {
    private String nome;
    private boolean ligada;
    private double capacidadeMaxima;
    private double probabilidadeFalha;
    private double custoOperacao;
    private double health;
    private double maximoDesgaste;
    private static int falhas = 0;

    private Random random = new Random();

    public Maquina(
            String nome,
            double capacidadeMaxima,
            double probabilidadeFalha,
            double custoOperacao,
            double health,
            double maximoDesgaste
    ) {
        this.nome = nome;
        this.ligada = false;
        this.capacidadeMaxima = capacidadeMaxima;
        this.probabilidadeFalha = probabilidadeFalha;
        this.custoOperacao = custoOperacao;
        this.health = health;
        this.maximoDesgaste = maximoDesgaste;
    }

    public abstract boolean processar(Produto produto);

    public abstract String getTipo();

    @Override 
    public String gerarRelatorioDiagnostico(){
        return(nome + " | Probabilidade de falha : " + probabilidadeFalha + " | Saúde : " + health + "% | Precisa de manutenção : Sim");
    }
    @Override 
    public boolean precisaManutencao(){
        if (health < 30){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean quebrada(){
        if (health <= 0){
            return true;
        }
        return false;
    }
    public void contabilizarFalha(){
        falhas += 1;
    }    
    public void reparar(){
        health = 100;
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
    protected void desgasteAleatorio(){
        health -= random.nextDouble() * maximoDesgaste ;
    }
}
