import java.util.List;

public class EstrategiaOrdemChegada implements EstrategiaProducao{
    @Override 
    public Demanda selecionarDemanda(List<Demanda> demandas, double orcamentoDisponivel){
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
    public String getNomeEstrategia(){
        return "Ordem de chegada";
    }
}