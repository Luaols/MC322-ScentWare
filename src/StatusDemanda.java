public enum StatusDemanda{
    PENDENTE("Pendente"),
    EM_PRODUCAO("Em produção"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada");

private final String nome;

StatusDemanda(String nome){
    this.nome = nome;
}
public String getNome(){
    return nome;
}

}