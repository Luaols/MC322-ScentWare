import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        exibirIntroducao();
        Scanner scanner = new Scanner(System.in);

        int cenario = 0; 
        Cenario cenarioAtual = null;
        while(cenario < 1 && cenario > 2) {

            System.out.println("Escolha com qual cenário de operação deseja executar:");
            System.out.println("[1] Ideal");
            System.out.println("[2] Apocalíptico");
            System.out.println();

            cenario = lerInteiro(scanner);
        
            switch(cenario){
                case 1 :
                    cenarioAtual = Cenario.IDEAL;
                    break;
                case 2 :
                    cenarioAtual = Cenario.APOCALIPTICO;
                    break;
                default :
                    System.out.println("Opção inválida");
            }
        } 
        


        // Materia-prima principal da fabrica
        MateriaPrima oleoAmendoas = new MateriaPrima(
                "MP001",
                "Oleo de Amendoas",
                1000.0,
                "mL",
                0.10
        );

        // Produtos da ScentWare
        Produto hidratante = new ProdutoHidratante(
                "P001",
                "Hidratante Corporal",
                20.0
        );

        Produto cremeDeMaos = new ProdutoCremeDeMaos(
                "P002",
                "Creme de Maos",
                30.0
        );

        Produto esfoliante = new ProdutoEsfoliante(
                "P003",
                "Esfoliante Corporal",
                40.0
        );

        // Gerenciador da fabrica
        GerenciadorProducao gerenciador = new GerenciadorProducao(
                oleoAmendoas,
                cenarioAtual.getBudget()
        );

        // Demandas iniciais
        gerenciador.registrarDemanda(new Demanda(hidratante.getTipo(), 0));
        gerenciador.registrarDemanda(new Demanda(cremeDeMaos.getTipo(), 0));
        gerenciador.registrarDemanda(new Demanda(esfoliante.getTipo(), 0));

        // Maquinas da linha de producao
        gerenciador.adicionarMaquina(
                new MaquinaHomogeneizador(
                        "Homogeneizador",
                        1000.0,
                        cenarioAtual.getProbabilidadeFalhaHomogeneizador(),
                        20.0,
                        100.0
                )
        );

        gerenciador.adicionarMaquina(
                new MaquinaEmpacotadora(
                        "Empacotadora",
                        1000.0,
                        cenarioAtual.getProbabilidadeFalhaEmpacotador(),
                        10.0,
                        100.0
                )
        );

        gerenciador.adicionarMaquina(
                new MaquinaInspecao(
                        "Inspecao",
                        1000.0,
                        cenarioAtual.getProbabilidadeFalhaInspecao(),
                        15.0,
                        100.0
                )
        );

        int opcao;

        do {
            exibirMenu(gerenciador);

            System.out.print("Escolha uma opcao: ");
            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    atualizarDemanda(
                            scanner,
                            gerenciador,
                            hidratante.getTipo()
                    );
                    break;
                case 2:
                    atualizarDemanda(
                            scanner,
                            gerenciador,
                            cremeDeMaos.getTipo()
                    );
                    break;
                case 3:
                    atualizarDemanda(
                            scanner,
                            gerenciador,
                            esfoliante.getTipo()
                    );
                    break;
                case 4:
                    fabricarProduto(
                            gerenciador,
                            hidratante
                    );
                    break;
                case 5:
                    fabricarProduto(
                            gerenciador,
                            cremeDeMaos
                    );
                    break;
                case 6:
                    fabricarProduto(
                            gerenciador,
                            esfoliante
                    );
                    break;
                case 7:
                    System.out.println("\n=== ARMAZEM ===");
                    gerenciador.exibirArmazem();
                    break;
                case 8:
                    exibirEstoque(gerenciador);
                    break;
                case 9:
                    comprarMateriaPrima(
                            scanner,
                            gerenciador
                    );
                    break;
                case 0:
                    System.out.println("\nEncerrando a ScentWare...");
                    System.out.println("Ate a proxima! :)");
                    break;
                default:
                    System.out.println("\nOpcao invalida.");
            }

        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirIntroducao() {
        System.out.println("===============================================================");
        System.out.println("                         SCENTWARE");
        System.out.println("                 Cuidar de voce e Essencial");
        System.out.println("               Cuidar da sua pele e ScentWare");
        System.out.println("===============================================================");
        System.out.println("Bem-vindos! Somos uma fabrica de cosmeticos corporais.");
        System.out.println();
        System.out.println("Produtos:");
        System.out.println("[01] Hidratante Corporal");
        System.out.println("[02] Creme de Maos");
        System.out.println("[03] Esfoliante Corporal");
        System.out.println();
        System.out.println("Materia-prima principal: Oleo de Amendoas");
        System.out.println();
        System.out.println("Desenvolvido por: Luana Oliveira e Roberta Santos");
        System.out.println("===============================================================");
        System.out.println();
    }

    private static void exibirMenu(GerenciadorProducao gerenciador) {
        System.out.println("\n================================");
        System.out.println("            SCENTWARE");
        System.out.println("================================");
        gerenciador.exibirBudget();
        System.out.println("\nATUALIZAR DEMANDAS");
        System.out.println("1 - Atualizar demanda de Hidratante");
        System.out.println("2 - Atualizar demanda de Creme de Maos");
        System.out.println("3 - Atualizar demanda de Esfoliante");
        System.out.println("\nFABRICAR");
        System.out.println("4 - Fabricar Hidratante");
        System.out.println("5 - Fabricar Creme de Maos");
        System.out.println("6 - Fabricar Esfoliante");
        System.out.println("\nCONSULTAR");
        System.out.println("7 - Ver armazem");
        System.out.println("8 - Ver estoque de materia-prima");
        System.out.println("\nCOMPRAR MATERIA-PRIMA");
        System.out.println("9 - Comprar materia-prima");
        System.out.println("\n0 - Sair");
        System.out.println("================================");
    }

    private static void atualizarDemanda(
            Scanner scanner,
            GerenciadorProducao gerenciador,
            String tipoProduto
    ) {
        System.out.print("\nInforme a quantidade de produtos: ");
        int quantidade = lerInteiro(scanner);

        if (gerenciador.atualizarDemanda(tipoProduto, quantidade)) {
            System.out.println(
                    "Demanda de " + tipoProduto
                    + " atualizada para "
                    + quantidade + " unidades."
            );
        } else {
            System.out.println("Quantidade invalida para a demanda.");
        }
    }

    private static void fabricarProduto(
            GerenciadorProducao gerenciador,
            Produto produto) {
        System.out.println(
                "\nIniciando producao de "
                + produto.getNome() + "..."
        );

        gerenciador.fabricarDemanda(
                produto.getTipo(),
                produto
        );
    }

    private static void exibirEstoque(GerenciadorProducao gerenciador) {
        MateriaPrima materiaPrima = gerenciador.getMateriaPrima();

        System.out.println("\n=== ESTOQUE DE MATERIA-PRIMA ===");

        System.out.println(
                materiaPrima.getNome()
                + ": "
                + materiaPrima.getQuantidade()
                + " "
                + materiaPrima.getUnidade()
        );

        System.out.printf(
                "Custo por unidade: R$ %.2f%n",
                materiaPrima.getCustoPorUnidade()
        );
    }

    private static void comprarMateriaPrima(Scanner scanner, GerenciadorProducao gerenciador) {
        System.out.print(
                "\nInforme a quantidade de materia-prima que deseja comprar: "
        );

        double quantidade = lerDouble(scanner);

        double custo = quantidade * gerenciador.getMateriaPrima().getCustoPorUnidade();

        if (gerenciador.comprarMateriaPrima(quantidade)) {
            System.out.printf(
                    "Materia-prima comprada com sucesso. Custo: R$ %.2f%n",
                    custo
            );
        } else {
            System.out.println(
                    "Nao foi possivel realizar a compra. Verifique a quantidade e o budget."
            );
        }
    }

    private static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print(
                        "Valor invalido. Digite um numero inteiro: "
                );
            }
        }
    }

    private static double lerDouble(Scanner scanner) {
        while (true) {
            try {
                double valor = Double.parseDouble(
                        scanner.nextLine().trim().replace(",", ".")
                );

                if (Double.isFinite(valor)) {
                    return valor;
                }

                System.out.print(
                        "Valor invalido. Digite um numero: "
                );
            } catch (NumberFormatException e) {
                System.out.print(
                        "Valor invalido. Digite um numero: "
                );
            }
        }
    }
}
