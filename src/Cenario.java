public enum Cenario {
    IDEAL(1000.0, 0.10, 0.05, 0.10, 3.0, 3.0, 3.0),
    APOCALIPTICO(100.0, 0.80, 0.70, 0.80, 30.0, 30.0, 40.0);

    private final double budget;
    private final double probabilidadeFalhaHomogeneizador;
    private final double probabilidadeFalhaEmpacotador;
    private final double probabilidadeFalhaInspecao;
    private final double maximoDesgasteHomogeneizador;
    private final double maximoDesgasteEmpacotador;
    private final double maximoDesgasteInspecao;

    Cenario(
        double budget, 
        double probabilidadeFalhaHomogeneizador, 
        double probabilidadeFalhaEmpacotador,
        double probabilidadeFalhaInspecao,
        double maximoDesgasteHomogeneizador,
        double maximoDesgasteEmpacotador,
        double maximoDesgasteInspecao
    ){
        this.budget = budget;
        this.probabilidadeFalhaHomogeneizador = probabilidadeFalhaHomogeneizador;
        this.probabilidadeFalhaEmpacotador = probabilidadeFalhaEmpacotador;
        this.probabilidadeFalhaInspecao = probabilidadeFalhaInspecao;
        this.maximoDesgasteHomogeneizador = maximoDesgasteHomogeneizador;
        this.maximoDesgasteEmpacotador = maximoDesgasteEmpacotador;
        this.maximoDesgasteInspecao = maximoDesgasteInspecao;

    }

    public double getBudget(){
        return budget;
    }
    public double getProbabilidadeFalhaHomogeneizador(){
        return probabilidadeFalhaHomogeneizador;
    }
    public double getProbabilidadeFalhaEmpacotador(){
        return probabilidadeFalhaEmpacotador;
    }
    public double getProbabilidadeFalhaInspecao(){
        return probabilidadeFalhaInspecao;
    }
    public void exibirCenarioAtual(){
        System.out.println("Cenario atual : " + this.name());
    }
    public double getMaximoDesgasteHomogeneizador(){
        return maximoDesgasteHomogeneizador;
    }
    public double getMaximoDesgasteEmpacotador(){
        return maximoDesgasteEmpacotador;
    }
    public double getMaximoDesgasteInspecao(){
        return maximoDesgasteInspecao;
    }
    
}
