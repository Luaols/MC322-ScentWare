import java.util.List;
public interface EstrategiaMaiorDemanda extends EstrategiaProducao{
    @Override 
    default Demanda selecionarDemanda(List<Demanda> demandas, double orcamentoDisponivel){
        if (demandas.isEmpty()){
            return null;
        }
        Demanda maiorDemanda = null;
        int quantidadeProdutos = 0;
        for ( Demanda demanda : demandas ){
            if (demanda.getStatus() == StatusDemanda.PENDENTE && demanda.getQuantidadeProdutos() > quantidadeProdutos){
                quantidadeProdutos = demanda.getQuantidadeProdutos();
                maiorDemanda = demanda ;
            }
        }
        return maiorDemanda;
    }
    @Override 
    default String getNomeEstrategia(){
        return "Estratégia de fabricação da maior demanda de produtos";
    } 
}