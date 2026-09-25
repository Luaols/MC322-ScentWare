import java.util.List;

public interface EstrategiaMaximoProdutos extends EstrategiaProducao{
    @Override 
    default Demanda selecionarDemanda(List<Demanda> demandas, double orcamentoDisponivel){
        if (demandas.isEmpty()){
            return null;
        }
        double orcamento = 0;
        Demanda demandaMaximoProdutos = null;
        for ( Demanda demanda : demandas ){
            if (demanda.getStatus() == StatusDemanda.PENDENTE && ){
                demandaMaximoProdutos = demanda;
            }
        }
        return (demandaMaximoProdutos);
    }
    @Override 
    default String getNomeEstrategia(){
        return "Estratégia de maximização de produtos fabricados";
    }
}