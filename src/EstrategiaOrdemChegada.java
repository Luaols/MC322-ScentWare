import java.util.List;

public interface EstrategiaOrdemChegada extends EstrategiaProducao{
    @Override 
    default Demanda selecionarDemanda(List<Demanda> demandas, double orcamentoDisponivel){
        if (demandas.isEmpty()){
            return null;
        }
        for ( Demanda demanda : demandas ){
            if (demanda.getStatus() == StatusDemanda.PENDENTE){
                return demanda;
            }
        }
        return null;
    }
    @Override 
    default String getNomeEstrategia(){
        return "Estratégia de ordem de chegada";
    }
}