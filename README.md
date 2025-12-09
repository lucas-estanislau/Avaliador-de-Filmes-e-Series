# Avaliador de Filmes e Séries

*Sistema em Java com organização em packages, leitura de arquivos, anotações, exceções personalizadas e árvore AVL.*

---

## Sobre o Projeto

Este projeto implementa um sistema de avaliação de filmes e séries utilizando conceitos de Programação Orientada a Objetos, Anotações (annotations), Estruturas de Dados (como AVL Tree), e manipulação de arquivos externos.

Desenvolvido por alunos do Instituto Metrópole Digital (IMD) da Universidade Federal do Rio Grande do Norte (UFRN), o referente é apresentado à disciplina de Linguagem de Programação II como projeto final.  


## Estrutura do Projeto
```
Avaliador-de-Filmes-e-Series/
│
├── src/                          # Código-fonte principal
│   └── br/ufrn/repo/
│       ├── annotations/          # Anotações personalizadas (@InfoAutor, @Funcionamento...)
│       ├── audiovisual/          # Classes de Filme, Série e Mídia
│       ├── avaliacao/            # Classe Avaliacao (nota, comentário, etc.)
│       ├── avltree/              # Implementação da Árvore AVL (ArvoreAVL, No, Tree)
│       ├── exception/            # Exceções personalizadas
│       └── sistema/              # Núcleo do sistema (Main, Menu, Sistema, Leitor)
│
├── bin/                          # Arquivos compilados (.class)
│
├── database/                     # Base de dados utilizada pelo programa
│   ├── dados.txt
│
├── README.md                     
├── LICENSE
│
├── .classpath
├── .project
├── .gitignore
└── Avaliador-de-Filmes-e-Series.iml
```

Os arquivos compilados são gerados em:
**bin/**
---
## Requisitos

* Java JDK 17 ou superior

* Terminal / Prompt de Comando
* * Power Shell (Windows)

* Sistema operacional: Windows / Linux

### Verifique a instalação:

`java -version`

`javac -version`

---
## Como Compilar e Executar

## Windows (PowerShell)

### 1. Na pasta raiz do projeto, execute:

`javac -d bin src/module-info.java src/br/ufrn/repo/annotations/*.java src/br/ufrn/repo/audiovisual/*.java src/br/ufrn/repo/avaliacao/*.java src/br/ufrn/repo/avltree/*.java src/br/ufrn/repo/exception/*.java src/br/ufrn/repo/sistema/*.java`

Se a pasta bin não existir, crie:

`mkdir bin`

e execute o passo 1 novamente.

### 2. Depois de compilar, execute o programa principal:

`java -cp bin br.ufrn.repo.sistema.Main`


Obs.: É importante estar na pasta raiz para que os caminhos dos arquivos do diretório database/ funcionem corretamente.

## Linux (Ubuntu, Debian, Mint, Arch, Fedora, etc.)

### 1. Na pasta raiz do projeto, execute:

`javac -d bin src/module-info.java src/br/ufrn/repo/**/*.java`

Se a pasta bin não existir, crie:

`mkdir bin`

e execute o passo 1 novamente.

### 2. Depois de compilar, execute o programa principal:

`java -cp bin br.ufrn.repo.sistema.Main`

## Executando pela IDE (Opcional)

Se preferir usar IntelliJ ou Eclipse:

1. Abra File > Open e selecione a pasta do projeto

2. Aguarde o carregamento dos módulos

3. Abra Main.java

4. Clique no botão ▶︎ para executar
---

## Colaboradores
Agradecimentos aos colaboradores que contribuíram para o desenvolvimento deste projeto:

**Bianca Jennifer Franklin Da Silva** (https://github.com/Bianca-Jennifer)

**Flavia Jamily dos Santos Macena** (https://github.com/FlaviaMacenax)

**Isabele de Oliveira Ferreira** (https://github.com/isaferreirasz)

**Juvam Rodrigues do Nascimento Neto** (https://github.com/Juvam-Rodrigues)

**Lucas Estanislau Gomes da Silva** (https://github.com/lucas-estanislau)


## Licença

Este projeto utiliza o arquivo LICENSE incluído no repositório.