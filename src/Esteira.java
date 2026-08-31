/* Essa classe é responsável por transportar a matéria prima
até o homogeneizador e o produto até a inspeção */

public class Esteira {
    //atributos
    private Object item;
    private boolean emMovimento;
    private double capacidadeMaxima;

    //constructor
    public Esteira(
        double capacidadeMaxima
    ) {
        this.item = null;
        this.emMovimento = false;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    //métodos
    public void ligar() {
        emMovimento = true;
    }

    public void desligar() {
        emMovimento = false;
    }

    public boolean verificarCapacidade(double peso) {
        return (peso <= capacidadeMaxima);
    }

    public boolean estaEmMovimento() {
        return emMovimento;
    }

    public boolean estaOcupada() {
        return (item != null);
    }

    public boolean adicionarItem(Object novoItem, double quantidade) {
        if (emMovimento) {
            return false;
        }

        if (item != null) {
            return false;
        }

        if (!verificarCapacidade(quantidade)) {
            return false;
        }

        item = novoItem;
        return true;
    }

    public Object removerItem() {
        if (emMovimento) {
            return null;
        }

        if (item == null) {
            return null;
        }

        Object itemRemovido = item;
        item = null;
        return itemRemovido;
    }
}