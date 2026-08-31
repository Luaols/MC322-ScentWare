# ScentWare

Sistema desenvolvido para a Tarefa 1 da disciplina MC322 - Programação Orientada a Objetos, da Universidade Estadual de Campinas (UNICAMP).

A ScentWare é uma fábrica de cosméticos corporais que criamos para simular, de forma simples, o processo de produção de produtos como hidratantes e esfoliantes, desde o uso das matérias-primas até o processamento e a inspeção final.

## Produtos

- Hidratante corporal
- Esfoliante corporal

## Matérias-primas

- Óleo de amêndoas
- Essência de baunilha
- Açúcar

## Estrutura da planta

A primeira versão da ScentWare é composta por:

- estoque de matérias-primas;
- esteira de transporte;
- homogeneizador (mistura as matérias-primas e faz o produto);
- estação de inspeção;
- sistema de interação via terminal (scanner).

## Execução

```bash
javac -d bin $(find src -name "*.java")
java -cp bin Main