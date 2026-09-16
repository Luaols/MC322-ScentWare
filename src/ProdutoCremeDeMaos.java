public class ProdutoCremeDeMaos extends Produto {
    public ProdutoCremeDeMaos(String id, String nome, double quantidadeMateriaPrimaPorUnidade) {
        super(id, nome, quantidadeMateriaPrimaPorUnidade, 0.7);
    }

    @Override
    public void processar() {
        setStatus("Em processamento");
    }

    @Override
    public double calcularTempoProducao() {
        return 7.0;
    }

    @Override
    public String getTipo() {
        return "Creme de Maos";
    }
}
