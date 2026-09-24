public interface EstrategiaOrdemChegada extends EstrategiaProducao{
    @Override 
    default Demanda selecionarDemanda(List<Demanda> demandas, double orcamentoDisponivel){
        if (lista.isEmpty()){
            return null;
        }
        for ( Demanda demanda : demandas ){
            if (demanda.getStatus() == StatusDemanda.PENDENTE){
                return demanda;
            }
        }
    }
    @Override 
    default String getNomeEstrategia(){
        return "Estratégia de ordem de chegada";
    }
}