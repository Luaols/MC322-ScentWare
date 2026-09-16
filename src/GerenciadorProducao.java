import java.util.ArrayList;

public class GerenciadorProducao {
    private ArrayList<Demanda> demandas;
    private ArrayList<Produto> produtosFabricados;
    private ArrayList<Maquina> maquinas;
    private MateriaPrima materiaPrima;
    private double budget;

    public GerenciadorProducao(
            MateriaPrima materiaPrima,
            double budget
    ) {
        this.materiaPrima = materiaPrima;
        this.budget = budget;
        this.demandas = new ArrayList<>();
        this.produtosFabricados = new ArrayList<>();
        this.maquinas = new ArrayList<>();
    }

    public void registrarDemanda(Demanda demanda) {
        demandas.add(demanda);
    }

    public void adicionarMaquina(Maquina maquina) {
        maquinas.add(maquina);
    }

    public Demanda buscarDemanda(String tipoProduto) {
        for (Demanda demanda : demandas) {
            if (demanda.getTipoProduto().equals(tipoProduto)) {
                return demanda;
            }
        }
        return null;
    }

    public boolean atualizarDemanda(String tipoProduto, int quantidade) {
        Demanda demanda = buscarDemanda(tipoProduto);
        if (demanda == null || quantidade < 0) {
            return false;
        }
        demanda.atualizarQuantidade(quantidade);
        return true;
    }

    public boolean comprarMateriaPrima(double quantidade) {
        if (!Double.isFinite(quantidade) || quantidade <= 0) {
            return false;
        }

        double custo = quantidade * materiaPrima.getCustoPorUnidade();

        if (custo > budget) {
            return false;
        }

        budget -= custo;
        materiaPrima.adicionarEstoque(quantidade);
        return true;
    }

    public boolean fabricarDemanda(String tipoProduto, Produto produtoModelo) {
        Demanda demanda = buscarDemanda(tipoProduto);

        if (demanda == null) {
            System.out.println("Demanda nao encontrada.");
            return false;
        }
        if (demanda.isAtendida()) {
            System.out.println("Essa demanda ja foi atendida.");
            return false;
        }
        if (demanda.getQuantidadeProdutos() <= 0) {
            System.out.println("Nao ha produtos pendentes nessa demanda.");
            return false;
        }

        int quantidadeSolicitada = demanda.getQuantidadeProdutos();

        double materiaPrimaNecessaria = demanda.calcularMateriaPrimaNecessaria(produtoModelo);

        if (!materiaPrima.verificarDisponibilidade(materiaPrimaNecessaria)) {
            System.out.println(
                    "Materia-prima insuficiente. Necessario: "
                    + materiaPrimaNecessaria + " "
                    + materiaPrima.getUnidade()
            );
            return false;
        }

        double custoProducao = calcularCustoProducao(quantidadeSolicitada);

        if (custoProducao > budget) {
            System.out.printf(
                    "Budget insuficiente. Custo da producao: R$ %.2f | Budget atual: R$ %.2f%n",
                    custoProducao,
                    budget
            );
            return false;
        }

        int produtosAprovados = 0;

        for (int i = 0; i < quantidadeSolicitada; i++) {
            materiaPrima.consumir(produtoModelo.getQuantidadeMateriaPrimaPorUnidade());

            int numeroProduto = Produto.getTotalProdutosFabricados() + 1;

            Produto produto = criarProduto(produtoModelo, numeroProduto);

            Produto.incrementarTotalProdutosFabricados();

            boolean aprovado = true;

            for (Maquina maquina : maquinas) {
                budget -= maquina.getCustoOperacao();
                if (!maquina.processar(produto)) {
                    aprovado = false;
                    break;
                }
            }

            if (aprovado) {
                produtosFabricados.add(produto);
                produtosAprovados++;
            }
        }

        int produtosRejeitados = quantidadeSolicitada - produtosAprovados;

        System.out.println("\n=== RESULTADO DA PRODUCAO ===");
        System.out.println("Produtos produzidos: " + quantidadeSolicitada);
        System.out.println("Produtos aprovados: " + produtosAprovados);
        System.out.println("Produtos rejeitados: " + produtosRejeitados);

        if (produtosRejeitados == 0) {
            demanda.atender();
            System.out.println("Demanda atendida completamente.");
            return true;
        }

        // Os produtos rejeitados continuam pendentes na demanda
        demanda.atualizarQuantidade(produtosRejeitados);

        System.out.println(
                "Ainda faltam "
                + produtosRejeitados
                + " produto(s) para atender a demanda."
        );

        return false;
    }

    private double calcularCustoProducao(int quantidadeProdutos) {
        double custoPorProduto = 0;
        for (Maquina maquina : maquinas) {
            custoPorProduto += maquina.getCustoOperacao();
        }
        return custoPorProduto * quantidadeProdutos;
    }

    private Produto criarProduto(Produto produtoModelo, int numeroProduto) {
        String id = produtoModelo.getId() + "-" + numeroProduto;

        if (produtoModelo instanceof ProdutoHidratante) {
            return new ProdutoHidratante(
                    id,
                    produtoModelo.getNome(),
                    produtoModelo.getQuantidadeMateriaPrimaPorUnidade()
            );
        }

        if (produtoModelo instanceof ProdutoCremeDeMaos) {
            return new ProdutoCremeDeMaos(
                    id,
                    produtoModelo.getNome(),
                    produtoModelo.getQuantidadeMateriaPrimaPorUnidade()
            );
        }

        return new ProdutoEsfoliante(
                id,
                produtoModelo.getNome(),
                produtoModelo.getQuantidadeMateriaPrimaPorUnidade()
        );
    }

    public void exibirBudget() {
        System.out.printf("Budget atual: R$ %.2f%n", budget);
    }

    public void exibirArmazem() {
        if (produtosFabricados.isEmpty()) {
            System.out.println("O armazem esta vazio.");
            return;
        }

        System.out.println(
                "Total de produtos no armazem: "
                + produtosFabricados.size()
        );

        for (Produto produto : produtosFabricados) {
            System.out.println(
                    produto.getId() + " - "
                    + produto.getNome() + " - "
                    + produto.getStatus()
            );
        }
    }

    public double getBudget() {
        return budget;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public ArrayList<Produto> getProdutosFabricados() {
        return produtosFabricados;
    }

    public ArrayList<Demanda> getDemandas() {
        return demandas;
    }
}
