public class Maquina {
    // Atributos
    private String nome;
    private boolean ligada;
    private double capacidadeMaxima;

    //Constructor
    public Maquina(String nome, double capacidadeMaxima) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.ligada = false;
    }

    //Métodos
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

    //Processamento
    public boolean processar(
        MateriaPrima[] materiasPrimas,
        double[] demandas,
        Produto produto
    ) {
        if (!ligada) {
            return false;
        }

        if(materiasPrimas.length != demandas.length) {
            return false;
        }
        
        /* verifica se a máquina tem capacidade p/ demanda */
        double demandaTotal = 0;
        for(int i=0; i< demandas.length; i++) {
            demandaTotal += demandas[i];
        }
        if(!verificarCapacidade(demandaTotal)) {
            return false;
        }

        /* verifica se temos disponibilidade de matéria prima */
        for(int i=0; i < materiasPrimas.length; i++) {
            if (!materiasPrimas[i].verificarDisponibilidade(demandas[i])) {
                return false;
            }
        }

        /* consome as matérias primas do estoque */
        for (int i = 0; i < materiasPrimas.length; i++) {
            materiasPrimas[i].consumir(demandas[i]);
        }

        produto.processar();

        return true;
    }
}