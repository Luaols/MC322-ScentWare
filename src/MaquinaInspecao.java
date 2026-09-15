public class MaquinaInspecao extends Maquina{
    public MaquinaInspecao(
        String nome, 
        double capacidadeMaxima, 
        double probabilidadeFalha, 
        double custoOperacao 
    ){
        super(nome, capacidadeMaxima, probabilidadeFalha, custoOperacao);
    }

    @Override 
    public boolean processar(
        MateriaPrima materiaPrima,
        Produto produto,
        double demanda
    ){
        if (!estaLigada()) {
            return false;
        }

        /* verifica se a máquina tem capacidade p/ demanda */
        if (!verificarCapacidade(demanda)) {
            return false;
        }

        /*  A função verificarFalha retorna a probabilidade de falha ou 0, utilizando aleatoriedade para decidir o que será retornado*/
        produto.aumentarProbabilidadeFalha(verificarFalha());
        
        /* verifica falha de operação */
        if (verificar(falha) != 0){
            return false;
        }

        produto.processar();

        return true;
    }

    
}