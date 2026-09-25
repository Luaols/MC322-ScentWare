public class MaquinaEmpacotadora extends Maquina {
    public MaquinaEmpacotadora(
            String nome,
            double capacidadeMaxima,
            double probabilidadeFalha,
            double custoOperacao,
            double health
    ) {
        super(nome, capacidadeMaxima, probabilidadeFalha, custoOperacao, health);
    }
    @Override 
    public String gerarRelatorioDiagnostico(){
        return("" + getHealth());
    }

    @Override
    public boolean processar(Produto produto) {
        ligar();

        // A falha da empacotadora aumenta a probabilidade acumulada do produto.
        if (verificarFalha()) {
            produto.aumentarProbabilidadeFalha(getProbabilidadeFalha());
        }

        desligar();

        return true;
    }

    @Override
    public String getTipo() {
        return "Empacotadora";
    }
}
