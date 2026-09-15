public abstract class Produto {
    //atributos
    private String id;
    private String nome;
    private String status;
    private double quantidadeMateriaPrimaNecessaria;
    private double qualidade;
    private double probabilidadeFalhaAcumulada;
    private double quantidadeMateriaPrimaPorUnidade;
    private static int totalProdutosFabricados;

    //constructor
    public Produto(
        String nome,
        double quantidadeMateriaPrimaNecessaria,
        double qualidade
    ) {
        this.nome = nome;
        this.status = "AGUARDANDO PROCESSAMENTO";
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
        this.qualidade = qualidade;
        this.probabilidadeFalhaAcumulada = 0;
        this.totalProdutosFabricados = 0;
    }

    //subclasses


    //métodos abstratos
    public abstract void processar();

    public abstract double calcularTempoProducao(double quantidadeProdutos);

    public abstract String getTipo();

    //métodos concretos
    public void inspecionar() {
        this.status = "INSPECIONADO";
    }

    public void definirDemandaMateriaPrima(double demanda) {
        this.quantidadeMateriaPrimaNecessaria = demanda;
    }

    public double getDemandaMateriaPrima() {
        return quantidadeMateriaPrimaNecessaria;
    }

    public String getId(){
        return (id);
    }

    public String getNome(){
        return(nome);
    }
    
    public String getStatus(){
        return(status);
    }
    public void setStaus(String novo_status){
        status = novo_status;
    }
    public void aumentarProbabilidadeFalha(double aumento){
        probabilidadeFalhaAcumulada = probabilidadeFalhaAcumulada + aumento;
    }
    public double getQualidade(){
        return(qualidade);
    }
}
