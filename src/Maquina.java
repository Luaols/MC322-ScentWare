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

    //processamento
    public boolean processar(Produto produto, int quantidade) {
        if (!ligada) {
            return false;
        }

        MateriaPrima[] materiasPrimas = produto.getMateriasPrimasNecessarias();
        double[] demandas = produto.getDemandasMateriasPrimas();

        if(materiasPrimas.length != demandas.length) {
            return false;
        }
        
        /* verifica se a máquina tem capacidade p/ demanda */
        double demandaTotal = 0;
        for(int i=0; i< demandas.length; i++) {
            demandaTotal += demandas[i] * quantidade;
        }
        if(!verificarCapacidade(demandaTotal)) {
            return false;
        }

        /* verifica se temos disponibilidade de todas as matéria prima */
        for (int i = 0; i < materiasPrimas.length; i++) {
            double demanda = demandas[i] * quantidade;
            if (!materiasPrimas[i].verificarDisponibilidade(demanda)) {
                return false;
            }
        }

        /* consome as matérias primas do estoque */
        for (int i = 0; i < materiasPrimas.length; i++) {
            double demanda = demandas[i] * quantidade;
            materiasPrimas[i].consumir(demanda);
        }

        produto.processar();

        return true;
    }
}