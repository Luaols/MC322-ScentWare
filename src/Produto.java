public class Produto {
    //atributos
    private String id;
    private String nome;
    private String status;
    private MateriaPrima[] materiasPrimasNecessarias;
    private double[] demandasMateriasPrimas;

    //constructor
    public Produto(
        String id,
        String nome,
        MateriaPrima[] materiasPrimasNecessarias,
        double[] demandasMateriasPrimas
    ) {
        this.id = id;
        this.nome = nome;
        this.status = "AGUARDANDO PROCESSAMENTO";
        this.materiasPrimasNecessarias = materiasPrimasNecessarias;
        this.demandasMateriasPrimas = demandasMateriasPrimas;
    }

    //métodos
    public void processar(){
        this.status = "PROCESSADO";
    }

    public void inspecionar() {
        this.status = "INSPECIONADO";
    }

     public MateriaPrima[] getMateriasPrimasNecessarias() {
        return materiasPrimasNecessarias;
    }

    public double[] getDemandasMateriasPrimas() {
        return demandasMateriasPrimas;
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
