# ScentWare

Sistema desenvolvido para a disciplina MC322 - Programação Orientada a Objetos, da Universidade Estadual de Campinas (UNICAMP). Atualmente está na sua segunda versão.

A ScentWare é uma fábrica de cosméticos corporais que criamos para simular, de forma simples, o processo de produção de produtos, desde o uso das matérias-primas até a inspeção final.

## Produtos

- Hidratante corporal
- Creme de mãos
- Esfoliante

## Matérias-primas

- Óleo de amêndoas

## Estrutura da planta

A primeira versão da ScentWare é composta por:

- estoque de matérias-primas;
- esteira de transporte;
- homogeneizador (transforma matéria prima em produto);
- máquina de embalagem
- máquina de inspeção de qualidade
- estação de inspeção;
- sistema de interação via terminal (scanner).

## Fluxo de produção

A produção na ScentWare segue o seguinte fluxo:

`Estoque → Esteira → Homogeneizador → Esteira → Controle de Qualidade → Produto Final`

Antes de iniciar uma produção, o sistema verifica se há quantidade suficiente de matérias prima necessária para o produto escolhido. Os ingredientes são transportados individualmente até o homogeneizador, passam pela empacotadora e, logo após, o produto final segue pela esteira até a máquina de inspeção.

## Execução

```bash
javac -d bin $(find src -name "*.java")
java -cp bin Main