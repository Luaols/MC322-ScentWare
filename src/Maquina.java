public class Maquina {
    //atributos
    private String nome;
    private boolean ligada;
    private double capacidadeMaxima;

    //constructor
    public Maquina(String nome, double capacidadeMaxima) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.ligada = false;
    }

    //métodos
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

    //processamento
    public boolean processar(
        MateriaPrima materiaPrima,
        Produto produto,
        double demanda
    ){
        if (!ligada) {
            return false;
        }

        /* verifica se a máquina tem capacidade p/ demanda */
        if (!verificarCapacidade(demanda)) {
            return false;
        }

        /* verifica se temos disponibilidade matéria prima */
        if (!materiaPrima.verificarDisponibilidade(demanda)) {
            return false;
        }

        /* não consumir abaixo do estoque mínimo*/
        if (materiaPrima.ultrapassaQuantidadeMinima(demanda)) {
            return false;
        }

        /* consome matérias prima do estoque */
        materiaPrima.consumir(demanda);
        produto.processar();

        return true;
    }
}