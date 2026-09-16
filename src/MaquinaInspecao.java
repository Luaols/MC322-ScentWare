import java.util.Random;

public class MaquinaInspecao extends Maquina {
    private Random random = new Random();

    public MaquinaInspecao(
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

        // A qualidade influencia diretamente a chance de rejeicao
        // A probabilidade acumulada aumenta ainda mais essa chance
        double chanceRejeicao = produto.getQualidade()
                * (0.5 + 0.5 * produto.getProbabilidadeFalhaAcumulada());

        boolean rejeitado = random.nextDouble() < chanceRejeicao;

        // Se a propria maquina de inspecao falhar, o resultado da inspecao fica incorreto
        if (verificarFalha()) {
            rejeitado = !rejeitado;
        }
        if (rejeitado) {
            produto.setStatus("Rejeitado");
        } else {
            produto.setStatus("Aprovado");
        }

        desligar();
        return !rejeitado;
    }

    @Override
    public String getTipo() {
        return "Inspecao";
    }
}
