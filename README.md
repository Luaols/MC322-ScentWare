# ScentWare
Sistema desenvolvido para a disciplina MC322 - Programação Orientada a Objetos, da Universidade Estadual de Campinas (UNICAMP). Esta versão corresponde à Tarefa 3.
A ScentWare é uma fábrica de cosméticos corporais que simula o processo de produção desde o controle das demandas e da matéria-prima até a inspeção e o armazenamento dos produtos acabados.

## Produtos
- Hidratante corporal
- Creme de mãos
- Esfoliante corporal

## Matéria-prima
- Óleo de amêndoas

## Estrutura atual do sistema
A linha de produção possui:
- estoque de matéria-prima;
- homogeneizador;
- empacotadora;
- máquina de inspeção;
- armazém de produtos acabados;
- gerenciamento de demandas e orçamento;
- estratégias de seleção de demandas;
- auditoria de produtos e máquinas;
- cenários Ideal e Apocalíptico.

## Tarefa 3
Nesta versão foram adicionados o padrão Strategy, com estratégias por ordem de chegada, maior demanda e máximo de produtos, o enum `StatusDemanda`, a interface `Auditavel`, o desgaste progressivo das máquinas, os cenários Ideal e Apocalíptico e a consulta detalhada do armazém de produtos acabados.
O menu também foi reorganizado em submenus para separar as funcionalidades de demandas, fabricação, consultas, estratégias e auditoria.

## Execução
```bash
javac -d bin $(find src -name "*.java")
java -cp bin Main
```

```powershell
javac -d bin (Get-ChildItem -Path src -Filter *.java -Recurse).FullName
java -cp bin Main
```
