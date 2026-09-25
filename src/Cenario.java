public enum Cenario {
    IDEAL(1000.0, 0.10, 0.05, 0.10),
    APOCALIPTICO(100.0, 0.80, 0.70, 0.80);

    private final double budget;
    private final double probabilidadeFalhaHomogeneizador;
    private final double probabilidadeFalhaEmpacotador;
    private final double probabilidadeFalhaInspecao;

    


    Cenario(
        double budget, 
        double probabilidadeFalhaHomogeneizador, 
        double probabilidadeFalhaEmpacotador,
        double probabilidadeFalhaInspecao
    ){
        this.budget = budget;
        this.probabilidadeFalhaHomogeneizador = probabilidadeFalhaHomogeneizador;
        this.probabilidadeFalhaEmpacotador = probabilidadeFalhaEmpacotador;
        this.probabilidadeFalhaInspecao = probabilidadeFalhaInspecao;
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
    
}
