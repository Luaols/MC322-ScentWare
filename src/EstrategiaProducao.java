import java.util.List;
public interface EstrategiaProducao {
    //métodos
    Demanda selecionarDemanda(List<Demanda> demandas, double orcamentoDisponivel);
    String getNomeEstrategia();
}