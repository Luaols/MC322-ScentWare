import java.util.List;
public class EstrategiaMaiorDemanda implements EstrategiaProducao{
    @Override 
    public Demanda selecionarDemanda(List<Demanda> demandas, double orcamentoDisponivel){
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
    public String getNomeEstrategia(){
        return "Fabricação da maior demanda de produtos";
    } 
}