public abstract class Produto implements Auditavel {
    private String id;
    private String nome;
    private String status;
    private double quantidadeMateriaPrimaPorUnidade;
    private double qualidade;
    private double probabilidadeFalhaAcumulada;
    private static int totalProdutosFabricados = 0;


    public Produto(
            String id,
            String nome,
            double quantidadeMateriaPrimaPorUnidade,
            double qualidade
    ) {
        this.id = id;
        this.nome = nome;
        this.status = "Pendente";
        this.quantidadeMateriaPrimaPorUnidade = quantidadeMateriaPrimaPorUnidade;
        this.qualidade = qualidade;
        this.probabilidadeFalhaAcumulada = 0.0;
    }

    public abstract void processar();

    public abstract double calcularTempoProducao();

    public abstract TipoProduto getTipo();

    @Override 
    public String gerarRelatorioDiagnostico(){
        return(nome + "| Qualidade: " + qualidade + "% | Probabilidade de falha acumulada : " + probabilidadeFalhaAcumulada);
    }
    @Override
    public boolean precisaManutencao(){
        if (probabilidadeFalhaAcumulada >= 1){
            return true;
        }
        else{
            return false;
        }
    }
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getQuantidadeMateriaPrimaPorUnidade() {
        return quantidadeMateriaPrimaPorUnidade;
    }

    public double getQualidade() {
        return qualidade;
    }

    public double getProbabilidadeFalhaAcumulada() {
        return probabilidadeFalhaAcumulada;
    }

    public void aumentarProbabilidadeFalha(double aumento) {
        probabilidadeFalhaAcumulada += aumento;
        if (probabilidadeFalhaAcumulada > 1.0) {
            probabilidadeFalhaAcumulada = 1.0;
        }
    }

    protected static void incrementarTotalProdutosFabricados() {
        totalProdutosFabricados++;
    }

    public static int getTotalProdutosFabricados() {
        return totalProdutosFabricados;
    }

}
