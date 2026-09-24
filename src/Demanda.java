public class Demanda {
    private String tipoProduto;
    private int quantidadeProdutos;
    private StatusDemanda status;

    public Demanda(
            String tipoProduto,
            int quantidadeProdutos
    ) {
        this.tipoProduto = tipoProduto;
        this.quantidadeProdutos = quantidadeProdutos;
        this.atendida = false;
    }

    public void atualizarQuantidade(int novaQuantidade) {
        if (novaQuantidade >= 0) {
            this.quantidadeProdutos = novaQuantidade;
            this.atendida = false;
        }
    }

    public double calcularMateriaPrimaNecessaria(Produto produto) {
        return quantidadeProdutos * produto.getQuantidadeMateriaPrimaPorUnidade();
    }

    
    public String getTipoProduto() {
        return tipoProduto;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }
    public StatusDemanda getStatus(){
        return status ;
    }

    public void atender(Demanda demanda) {
        if (demanda.getStatus() == StatusDemanda.CANCELADA){
        atendida = true;
        }
    }

}
