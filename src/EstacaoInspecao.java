public class EstacaoInspecao {
    //atributos
    private boolean ativa;
    private int produtosInspecionados;

    //constructor
    public EstacaoInspecao() {
        this.ativa = false;
        this.produtosInspecionados = 0;
    }

    //métodos
    public void ativar() {
        ativa = true;
    }

    public void desativar() {
        ativa = false;
    }

    public boolean estaAtiva() {
        return ativa;
    }

    public boolean inspecionar(Produto produto) {
        if(!ativa) {
            return false;
        }

        if (!produto.getStatus().equals("PROCESSADO")) {
            return false;
        }

        produto.inspecionar();
        produtosInspecionados++;

        return true;
    }

    public int getTotalInspecionados() {
        return produtosInspecionados;
    }
}