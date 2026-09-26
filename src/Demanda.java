public class Demanda {
    private TipoProduto tipoProduto;
    private int quantidadeProdutos;
    private StatusDemanda status;
    private double custoTotal;

    public Demanda(
            TipoProduto tipoProduto,
            int quantidadeProdutos,
            double custoTotal
    ) {
        this.tipoProduto = tipoProduto;
        this.quantidadeProdutos = quantidadeProdutos;
        status = StatusDemanda.PENDENTE;
        this.custoTotal = custoTotal;
    }

    public void atualizarQuantidade(int novaQuantidade) {
        if (novaQuantidade >= 0) {
            this.quantidadeProdutos = novaQuantidade;
            status = StatusDemanda.PENDENTE;
        }
    }

    public double calcularMateriaPrimaNecessaria(Produto produto) {
        return quantidadeProdutos * produto.getQuantidadeMateriaPrimaPorUnidade();
    }

    
    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }
    public StatusDemanda getStatus(){
        return status ;
    }
    public boolean viabilidadeFinanceira(double budget){
        if (custoTotal >= budget){
            return true;
        }
        return false;
    }
    public void setCustoTotal(Double custoTotal){
        this.custoTotal = custoTotal;
    }
    public void setStatus(StatusDemanda statusDemanda){
        status = statusDemanda;
    }

    public void atender(Demanda demanda) {
        if (demanda.getStatus() != StatusDemanda.CANCELADA){
            demanda.setStatus(StatusDemanda.CONCLUIDA);
        }
    }

}
