public class ProdutoEsfoliante extends Produto {
    public ProdutoEsfoliante(String id, String nome, double quantidadeMateriaPrimaPorUnidade) {
        super(id, nome, quantidadeMateriaPrimaPorUnidade, 0.9);
    }
    @Override 
    public String gerarRelatorioDiagnostico(){
        return("" + getQualidade());
    }
    @Override
    public boolean precisaManutencao(){
        if (getProbabilidadeFalhaAcumulada() >= 1.0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void processar() {
        setStatus("Em processamento");
    }

    @Override
    public double calcularTempoProducao() {
        return 9.0;
    }

    @Override
    public String getTipo() {
        return "Esfoliante";
    }
}
