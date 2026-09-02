public class Produto {
    //atributos
    private String id;
    private String nome;
    private String status;
    private double quantidadeMateriaPrimaNecessaria;

    //constructor
    public Produto(
        String id,
        String nome,
        double quantidadeMateriaPrimaNecessaria
    ) {
        this.id = id;
        this.nome = nome;
        this.status = "AGUARDANDO PROCESSAMENTO";
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
    }

    //métodos
    public void processar(){
        this.status = "PROCESSADO";
    }

    public void inspecionar() {
        this.status = "INSPECIONADO";
    }

    public void definirDemandaMateriaPrima(double demanda) {
        this.quantidadeMateriaPrimaNecessaria = demanda;
    }

    public double getDemandaMateriaPrima() {
        return quantidadeMateriaPrimaNecessaria;
    }

    public String getId(){
        return (id);
    }

    public String getNome(){
        return(nome);
    }
    
    public String getStatus(){
        return(status);
    }
}
