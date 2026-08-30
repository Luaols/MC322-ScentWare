public class materiaPrima {
    // atributos
    private String id;
    private String nome;
    private double quantidade;
    private String unidade;
    private double quantidadeMinima;

    //constructor
    public materiaPrima(String id, String nome, double quantidade, String unidade, double quantidadeMinima){
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.quantidadeMinima = quantidadeMinima;
    }

    // métodos
    public void consumir(double quantidade) {
        this.quantidade = this.quantidade - quantidade;
    }
    public void adcionarEstoque(double quantidade) {
        this.quantidade = this.quantidade + quantidade;
    }
    public boolean vericarDisponibilidade(double demanda){
        return (quantidade > demanda);
    }
    public String getId(){
        return (id);
    }
    public double getQuantidade(){
        return (quantidade);
    }

}
