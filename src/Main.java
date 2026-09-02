import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        exibirIntroducao();

        /* ======================== INSTÂNCIAS ========================= */
        /* Instância das matérias-primas */
        MateriaPrima oleoAmendoas = new MateriaPrima(
            "MP001",
            "Óleo de Amêndoas",
            500.0,
            "mL",
            100.0
        );

        /* Instância dos produtos */
        Produto hidratante = new Produto(
            "P001",
            "Hidratante Corporal",
            30.0
        );
       
        /* Instância dos equipamentos e scanner */
        Esteira esteira = new Esteira(100.0);
        Maquina homogeneizador = new Maquina("Homogeneizador M-01", 100.0);
        EstacaoInspecao estacaoInspecao = new EstacaoInspecao();

        Scanner scanner = new Scanner(System.in);

        /* ======================== MENU - AÇÕES ========================= */
        boolean execucao = true;
        while (execucao) {
            exibirMenu();
            int opcao = lerInteiro(scanner, "Escolha: ");

            while (opcao < 1 || opcao > 4) {
                System.out.println("Opção inválida. Escolha um número entre 1 e 4.");
                opcao = lerInteiro(scanner,"Escolha: ");
            }

            if (opcao == 1) {
                iniciarProducao(
                    scanner,
                    hidratante,
                    oleoAmendoas,
                    homogeneizador,
                    esteira,
                    estacaoInspecao
                );
            }
            else if (opcao == 2) {
                exibirEstoque(oleoAmendoas);
            }
            else if (opcao == 3) {
                adicionarMateria(scanner, oleoAmendoas);
            }
            else if (opcao == 4) {
                execucao = false;
            }
        }
        scanner.close();
    }

    /* Método de exibição de um menu que possibilita o usuário escolher uma ação */
    private static void exibirMenu() {
        System.out.println("==============================================");
        System.out.println("               MENU PRINCIPAL");
        System.out.println("==============================================");
        System.out.println("1 - Iniciar produção");
        System.out.println("2 - Consultar estoque");
        System.out.println("3 - Adicionar matéria-prima");
        System.out.println("4 - Sair");
        System.out.println("----------------------------------------------");
    }

    /* Método para exibir uma tela de introdução quando inciarmos */
    private static void exibirIntroducao() {
        System.out.println("===============================================================");
        System.out.println("                         SCENTWARE");
        System.out.println("                 Cuidar de você é Essencial       ");
        System.out.println("               Cuidar da sua pele é ScentWare      ");
        System.out.println("===============================================================");
        System.out.println("Bem-vindos! Somos uma fábrica de cosméticos corporais.");
        System.out.println();
        System.out.println("Nossos produtos são desenvolvidos com muito carinho ");
        System.out.println("e cuidado, estimulando autocuidado e bem-estar. ");
        System.out.println();
        System.out.println("Produtos:");
        System.out.println("[01] Hidratante corporal");
        System.out.println(" - Evita ressecamento, irritações e melhora o aspecto da pele");
        System.out.println(" - Matéria-prima principal: Óleo de Amêndoas");
        System.out.println();
        System.out.println("Desenvolvido por: Luana Oliveira e Roberta Santos");
        System.out.println("===============================================================");
        System.out.println();
    }

    /* Método de exibição do estoque de cada uma das matérias-primas */
    private static void exibirEstoque(MateriaPrima materiaPrima) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("               ESTOQUE ATUAL");
        System.out.println("==============================================");

        System.out.println(
            materiaPrima.getId() + " - "
            + materiaPrima.getNome() + ": "
            + materiaPrima.getQuantidade() + " "
            + materiaPrima.getUnidade()
        );
        
        System.out.println();
        System.out.println("===============================================================");
        System.out.println();
    }

    /* Método de exibição para a opção de reabastecer o estoque */
    private static void adicionarMateria(
        Scanner scanner,
        MateriaPrima materiaPrima
    ) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("             REABASTECER ESTOQUE");
        System.out.println("==============================================");

        /* o usuário escolhe a quantidade de matérias-prima para abastecer */
         System.out.println("Matéria-prima: " + materiaPrima.getNome());

        double quantidade = lerDoublePositivo(scanner, "Quantidade a adicionar: ");

        materiaPrima.adicionarEstoque(quantidade);

        System.out.println();
        System.out.println("[OK] Estoque de " + materiaPrima.getNome() + " atualizado.");
        System.out.println("Novo estoque: " + materiaPrima.getQuantidade() + " " + materiaPrima.getUnidade());
        System.out.println();
        System.out.println("===============================================================");
        System.out.println();
    }

    /* Método para iniciar a produção */
    private static void iniciarProducao(
        Scanner scanner,
        Produto hidratante,
        MateriaPrima oleoAmendoas,
        Maquina homogeneizador,
        Esteira esteira,
        EstacaoInspecao estacaoInspecao
    ) {
        /* seleciona o produto a ser produzido */
        System.out.println();
        System.out.println("==============================================");
        System.out.println("              NOVA PRODUÇÃO");
        System.out.println("==============================================");
        System.out.println("1 - " + hidratante.getNome());
        System.out.println("----------------------------------------------");

        boolean execucao = true;
        int escolhaProduto = 0;
        while(execucao) {
            escolhaProduto = lerInteiro(scanner, "Selecione o produto: ");
            if (escolhaProduto==1) {
                execucao = false;
            } else {
                System.out.println("Inválido.");
            }
        }

        Produto produtoSelecionado = hidratante;

        /* selecionar a demanda de matéria-prima a ser produzida */
        double demanda = lerDoublePositivo(scanner, "Informe a demanda de matéria-prima (mL): ");
        produtoSelecionado.definirDemandaMateriaPrima(demanda);
        demanda = produtoSelecionado.getDemandaMateriaPrima();

        /* verificando se tem estoque antes de começar a produzir */
        if (!oleoAmendoas.verificarDisponibilidade(demanda)) {
            System.out.println();
            System.out.println("[PRODUÇÃO INTERROMPIDA]");
            System.out.println("Estoque insuficiente de " + oleoAmendoas.getNome());
            System.out.println();
            System.out.println("Necessário: " + demanda + " " + oleoAmendoas.getUnidade());
            System.out.println("Disponível: " + oleoAmendoas.getQuantidade() + " " + oleoAmendoas.getUnidade());
            return;
        }

        /* verifica se a produção ultrapassa o estoque mínimo */
        if (oleoAmendoas.ultrapassaQuantidadeMinima(demanda)) {
            System.out.println();
            System.out.println("[PRODUÇÃO INTERROMPIDA]");
            System.out.println("Prosseguir com a operação comprometerá " + "o estoque mínimo de " + oleoAmendoas.getNome());
            System.out.println("Necessário: " + demanda + " " + oleoAmendoas.getUnidade());
            System.out.println("Disponível: " + oleoAmendoas.getQuantidade() + " " + oleoAmendoas.getUnidade());
            System.out.println("Estoque mínimo: " + oleoAmendoas.getQuantidadeMinima() + " " + oleoAmendoas.getUnidade());
            return;
        }

        /* verificando se a esteira suporta a demanda */
        if (!esteira.verificarCapacidade(demanda)) {
            System.out.println();
            System.out.println("[PRODUÇÃO INTERROMPIDA]");
            System.out.println("A demanda excede a capacidade máxima da esteira.");
            System.out.println("Demanda: " + demanda + " " + oleoAmendoas.getUnidade());
            return;
        }

        /* verificando se a máquina suporta a demanda */
        if (!homogeneizador.verificarCapacidade(demanda)) {
            System.out.println();
            System.out.println("[PRODUÇÃO INTERROMPIDA]");
            System.out.println("A demanda excede a capacidade máxima do " + homogeneizador.getNome() + ".");
            System.out.println("Demanda: " + demanda + " " + oleoAmendoas.getUnidade());
            return;
        }

        /* esteira carrega um matéria-prima por vez até o Homogeneizador */
        System.out.println();
        boolean adicionou =esteira.adicionarItem(oleoAmendoas, demanda);

        if (!adicionou) {
            System.out.println("[E-01] Não foi possível carregar " + oleoAmendoas.getNome());
            return;
        }

        System.out.println("[E-01] " + oleoAmendoas.getNome() + " carregado.");

        esteira.ligar();

        System.out.println("[E-01] Transportando " + oleoAmendoas.getNome() + "...");

        esteira.desligar();
        esteira.removerItem();

        System.out.println("[E-01] " + oleoAmendoas.getNome() + " entregue ao " + homogeneizador.getNome() + ".");
        
        /* inicia o processo de homogeneização */
        homogeneizador.ligar();
        System.out.println();
        System.out.println("[M-01] Homogeneizador iniciado.");

        boolean processado = homogeneizador.processar(oleoAmendoas, produtoSelecionado, demanda);

        homogeneizador.desligar();

        if (!processado) {
            System.out.println("[M-01] Não foi possível produzir seu produto.");
            return;
        }
        System.out.println("[M-01] " + produtoSelecionado.getNome() + " produzido com sucesso.");

        /* produto volta para esteira e vai até a inspeção */
        boolean produtoAdicionado = esteira.adicionarItem(produtoSelecionado, demanda);
        if (!produtoAdicionado) {
            System.out.println("[E-01] Não foi possível transportar o produto.");
            return;
        }

        esteira.ligar();
        System.out.println("[E-01] Transportando " + produtoSelecionado.getNome() + " para Controle de Qualidade...");
        esteira.desligar();
        esteira.removerItem();

        /* inspeção do produto */
        estacaoInspecao.ativar();
        boolean inspecionado = estacaoInspecao.inspecionar(produtoSelecionado);
        estacaoInspecao.desativar();

        if (!inspecionado) {
            System.out.println("[CQ-01] Não foi possível realizar a inspeção.");
            return;
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("           PRODUÇÃO CONCLUÍDA");
        System.out.println("==============================================");
        System.out.println("Produto: " + produtoSelecionado.getId() + " " + produtoSelecionado.getNome());
        System.out.println("Matéria-prima utilizada: " + oleoAmendoas.getId() + " - "+ oleoAmendoas.getNome());
        System.out.println("Demanda processada: " + demanda + " " + oleoAmendoas.getUnidade());
        System.out.println("Status: " + produtoSelecionado.getStatus());
        System.out.println("Controle de Qualidade: APROVADO");
        System.out.println();
        System.out.println("===============================================================");
        System.out.println();
    }

    /* Métodos de tratamento de leitura do terminal com o scanner */
    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Inválido. Digite apenas números.");
            scanner.next();
        }
    }

    private static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            System.out.println("Inválido. Digite apenas números.");
            scanner.next();
        }
    }

    private static double lerDoublePositivo(Scanner scanner, String mensagem) {
        while (true) {
            double valor = lerDouble(scanner, mensagem);
            if (valor > 0) {
                return valor;
            }
            System.out.println("Inválido. O valor deve ser maior que zero.");
        }
    }
}