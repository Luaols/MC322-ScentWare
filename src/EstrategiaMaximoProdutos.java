import java.util.List;

public class EstrategiaMaximoProdutos implements EstrategiaProducao{
    @Override 
    public Demanda selecionarDemanda(List<Demanda> demandas, double orcamentoDisponivel){

        if (demandas.isEmpty()){
            return null;
        }

        double quantidadeProdutos = 0;
        Demanda demandaMaximoProdutos = null;

        for ( Demanda demanda : demandas ){
            if (
                demanda.getStatus() == StatusDemanda.PENDENTE && 
                demanda.viabilidadeFinanceira(orcamentoDisponivel) && 
                demanda.getQuantidadeProdutos() > quantidadeProdutos){
                demandaMaximoProdutos = demanda;
                quantidadeProdutos = demanda.getQuantidadeProdutos();
            }
        }
        return (demandaMaximoProdutos);
    }
    @Override 
    public String getNomeEstrategia(){
        return "Estratégia de maximização de produtos fabricados";
    }
}