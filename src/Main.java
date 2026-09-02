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
        MateriaPrima[] estoque = {oleoAmendoas};

        /* Arrays para servir de "receita": matérias e demandas por produto */
        MateriaPrima[] materiasHidratante = {oleoAmendoas};
        double[] demandasHidratante = {30.0};

        /* Instância dos produtos */
        Produto hidratante = new Produto(
            "P001",
            "Hidratante Corporal",
            materiasHidratante,
            demandasHidratante
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
                    homogeneizador,
                    esteira,
                    estacaoInspecao
                );
            }
            else if (opcao == 2) {
                exibirEstoque(estoque);
            }
            else if (opcao == 3) {
                adicionarMateria(scanner, estoque);
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
        System.out.println();
        System.out.println("Desenvolvido por: Luana e Roberta");
        System.out.println("===============================================================");
        System.out.println();
    }

    /* Método de exibição do estoque de cada uma das matérias-primas */
    private static void exibirEstoque(MateriaPrima[] estoque) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("               ESTOQUE ATUAL");
        System.out.println("==============================================");

        for (int i=0; i < estoque.length; i++) {
            System.out.println(
                estoque[i].getId() + " - "
                + estoque[i].getNome() + ": "
                + estoque[i].getQuantidade()+ " "
                + estoque[i].getUnidade()
            );
        }
        
        System.out.println();
        System.out.println("===============================================================");
        System.out.println();
    }

    /* Método de exibição para a opção de reabastecer o estoque */
    private static void adicionarMateria(
        Scanner scanner,
        MateriaPrima[] estoque
    ) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("             REABASTECER ESTOQUE");
        System.out.println("==============================================");

        /* o usuário escolhe uma das matérias-primas para abastecer */
        for (int i=0; i < estoque.length; i++) {
            System.out.println(
                (i+1) + " - " + estoque[i].getNome()
            );
        }
        System.out.println();

        int escolha = 0;
        boolean execucao = true;
        while (execucao) {
            escolha = lerInteiro(scanner,"Selecione a matéria-prima: ");
            if (1 <= escolha && escolha <= estoque.length) {
                execucao = false;
            } else {
                System.out.println("Inválido. Escolha uma matéria-prima disponível.");
            }
        }
        int indice = escolha - 1;

        System.out.println("Matéria-prima selecionada: " + estoque[indice].getNome());

        /* o usuário escolhe quanto deseja abastecer da matéria-prima */
        double quantidade = lerDoublePositivo(scanner, "Quantidade a adicionar: ");

        estoque[indice].adicionarEstoque(quantidade);

        System.out.println();
        System.out.println("[OK] Estoque de " + estoque[indice].getNome() + " atualizado.");
        System.out.println("Novo estoque: " + estoque[indice].getQuantidade() + " " + estoque[indice].getUnidade());
        System.out.println();
        System.out.println("===============================================================");
        System.out.println();
    }

    /* Método para iniciar a produção */
    private static void iniciarProducao(
        Scanner scanner,
        Produto hidratante,
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

        Produto produtoSelecionado;
        if (escolhaProduto == 1) { produtoSelecionado = hidratante; }
        else {
            System.out.println("Produto inválido.");
            return;
        }

        /* selecionar a quantidade a ser produzida */
        int quantidadeProduzir = lerInteiroPositivo(scanner,"Quantidade a produzir: ");

        /* array da matéria prima selecionada */
        MateriaPrima[] materiasPrimas = produtoSelecionado.getMateriasPrimasNecessarias();
        double[] demandas = produtoSelecionado.getDemandasMateriasPrimas();

        double[] demandasTotais = new double[demandas.length];
        for (int i=0; i < demandas.length; i++) {
            demandasTotais[i] = demandas[i] * quantidadeProduzir;
        }

        /* verificando se tem estoque antes de começar a produzir */
        for (int i = 0; i < materiasPrimas.length; i++) {
            if (!materiasPrimas[i].verificarDisponibilidade(demandasTotais[i])) {
                System.out.println();
                System.out.println("[PRODUÇÃO INTERROMPIDA]");
                System.out.println("Estoque insuficiente de " + materiasPrimas[i].getNome());
                System.out.println();
                System.out.println("Necessário: " + demandasTotais[i]);
                System.out.println("Disponível: " + materiasPrimas[i].getQuantidade());
                return;
            }
            if (materiasPrimas[i].ultrapassaQuantidadeMinima(demandasTotais[i])){
                System.out.println();
                System.out.println("[PRODUÇÃO INTERROMPIDA]");
                System.out.println("Prosseguir com a operação comprometerá o estoque mínimo de " + materiasPrimas[i].getNome());
                System.out.println("Necessário: " + demandasTotais[i]);
                System.out.println("Disponível: " + materiasPrimas[i].getQuantidade());
                System.out.println("Mínimo: " + materiasPrimas[i].getQuantidadeMinima());
                return;
            }
        }

        /* verificando se a esteira suporta a demanda */

        /* esteira carrega um matéria-prima por vez até o Homogeneizador */
        System.out.println();
        for (int i = 0; i < materiasPrimas.length; i++) {
            boolean adicionou = esteira.adicionarItem(materiasPrimas[i], demandasTotais[i]);

            if (!adicionou) {
                System.out.println("[E-01] Não foi possível carregar " + materiasPrimas[i].getNome());
                return;
            }

            System.out.println("[E-01] " + materiasPrimas[i].getNome() + " carregado.");
            esteira.ligar();
            System.out.println("[E-01] Transportando " + materiasPrimas[i].getNome() + "...");
            esteira.desligar();
            esteira.removerItem();
            System.out.println("[E-01] " + materiasPrimas[i].getNome() + " entregue ao Homogeneizador M-01.");
        }

        /* inicia o processo de homogeneização */
        homogeneizador.ligar();
        System.out.println();
        System.out.println("[M-01] Homogeneizador iniciado.");

        boolean processado = homogeneizador.processar(produtoSelecionado, quantidadeProduzir);

        homogeneizador.desligar();

        if (!processado) {
            System.out.println("[M-01] Não foi possível produzir seu produto.");
            return;
        }
        System.out.println("[M-01] " + produtoSelecionado.getNome() + " produzido com sucesso.");

        /* produto volta para esteira e vai até a inspeção */
        double cargaProduto = 0;
        for (int i = 0; i < demandasTotais.length; i++) {
            cargaProduto += demandasTotais[i];
        }

        boolean produtoAdicionado = esteira.adicionarItem(produtoSelecionado, cargaProduto);
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
        System.out.println("Produto: " + produtoSelecionado.getNome());
        System.out.println("Quantidade: " + quantidadeProduzir);
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

    private static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        while (true) {
            int valor = lerInteiro(scanner, mensagem);
            if (valor > 0) {
                return valor;
            }
            System.out.println("Inválido. O valor deve ser maior que zero.");
        }
    }

    private static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            System.out.println("Inválido. Digite apenas números (caso fracionado: xxx,x).");
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