public class MaquinaHomogeneizador extends Maquina {
    public MaquinaHomogeneizador(
            String nome,
            double capacidadeMaxima,
            double probabilidadeFalha,
            double custoOperacao
    ) {
        super(nome, capacidadeMaxima, probabilidadeFalha, custoOperacao);
    }

    @Override
    public boolean processar(Produto produto) {
        ligar();
        produto.processar();

        // A falha do homogeinizador aumenta a probabilidade acumulada do produto.
        if (verificarFalha()) {
            produto.aumentarProbabilidadeFalha(getProbabilidadeFalha());
        }

        desligar();
        return true;
    }

    @Override
    public String getTipo() {
        return "Homogeneizador";
    }
}
