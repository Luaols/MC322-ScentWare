public class MateriaPrima {
    //atributos
    private String id;
    private String nome;
    private double quantidade;
    private String unidade;
    private double quantidadeMinima;

    //constructor
    public MateriaPrima(
        String id,
        String nome,
        double quantidade,
        String unidade,
        double quantidadeMinima
    ) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.quantidadeMinima = quantidadeMinima;
    }

    //métodos
    public void consumir(double quantidade) {
        this.quantidade = this.quantidade - quantidade;
    }
    public void adicionarEstoque(double quantidade) {
        this.quantidade = this.quantidade + quantidade;
    }
    public boolean verificarDisponibilidade(double demanda){
        return (quantidade >= demanda);
    }
    public boolean estaAbaixoDoMinimo() {
        return (quantidade <= quantidadeMinima);
    }
    public String getNome(){
        return nome;
    }
    public String getId(){
        return id;
    }
    public double getQuantidade(){
        return quantidade;
    }
    public String getUnidade() {
        return unidade;
    }
}
