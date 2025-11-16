# FEIFood
CCM310 - Arquitetura de Software e Programação Orientada a Objetos

1.   Visão Geral do Projeto

   
O objetivo do projeto é construir uma plataforma de pedidos de alimentos. Desenvolvido em Java, utilizando o NetBeans, banco de dados PostgreSQL, e arquitetrua MVC (Model-View-Controller).

As principais funcionalidades do sistema incluem: Cadastro e login de usuário, buscar por alimentos, listar informações de alimentos, criar, editar, excluir pedidos, permitindo adicionar e remover alimentos da

sacola livremente, avaliar pedidos cadastrados, atribuindo uma nota de até 5 estrelas.


2.   Arquitetura do Sistema

   
O projeto segue o padrão de arquitetura MVC, separando os pacotes em:


- Model (M): classes de entidades


- View (V): telas de interface Swing


- Controller (C): ligação entre view e model

  
- DAO: acesso ao banco de dados



3.   Estruturados pacotes:
   
 
 ├── controller/

     ├── ControleAlimento.java

 
     ├── ControleCadastro.java
 
     ├── ControleHistorico.java
 
     ├── ControleLogin.java
 
     ├── ControlePedido.java
 
 

 ├── dao/

     ├── AlimentoDAO.java

     ├── Conexao.java
 
     ├── PedidoDAO.java

     ├── PedidoAlimentoDAO.java

     ├── UsuarioDAO.java
 
 

 ├── feifood/  (MAIN)

     ├──FEIFood.java

 

 ├── model/

     ├── Alimento.java

     ├── Comida.java
 
     ├── Bebida.java
 
     ├── Imposto.java
 
     ├── Pedido.java

     ├── PedidoAlimento.java
 
     ├── Usuario.java
 
 

 ├── view/

     ├── Cadastro.java
 
     ├── Home.java

     ├── Login.java
 
     ├── Pedidos.java
 
     ├── Sacola.java



4.   Modelagem do banco de dados


- Modelagens:


-- <a href="modeloEntidadeRelacionamento">Modelo Entidade Relacionamento</a>



-- <a href="modeloRelacional">Modelo Relacional</a>




5.   Entidades do pacote model: 

  
- Classe Usuário - contém os atributos: email, nome e senha;


  
- Superclasse Alimento - contém os atributos: idAlimento, nome, descricao, tipo, categoria e preco;



- Interface Imposto - contém o método: calcularImposto();



- Subclasse Comida - Herda todos os atributos da classe Alimento;



- Subclasse Bebida - Herda todos os atributos da classe Alimento e implementa a interface Imposto, sobreescrevendo o método;



- Classe Pedido - contém os atributos: idPedido, precoTotal, status, avaliacao, usuario(objeto da classe Usuario) e itens(ArrayList de objetos da classe PedidoAlimento);



- Classe PedidoAlimento (classe intermediária que conecta Pedido e Alimento) - contém os atributos: quantidade, subtotal, alimento(objeto da classe Alimento) e pedido(objeto da classe Pedido);




6.   Funcionalidades:
   
- Cadastro de Usuário

Criação de nova conta com: nome, e-mail e senha que serão validados e salvos via UsuarioDAO.

- Login
  
Validação de credenciais (e-mail e senha) e acesso ao sistema.


- Buscar Alimento
  
Permite pesquisar alimentos pelo nome e mostra: nome, descrição, preço, tipo e categoria.


- Criar e Gerenciar Pedido (Cadastrar Pedido*)
  
O usuário pode: criar pedido, editar pedido, excluir pedido, adicionar alimentos, remover alimentos e cada alteração atualiza o total, subtotal, quantidade, imposto e salva no banco.


- Avaliar Pedido (Avaliar Pedido**)
  
Após finalizar um pedido, o usuário pode avaliá-lo no histórico com uma nota de 0 a 5 estrelas. O sistema exibe as estrelas preenchidas e salva o valor no banco.



7.   Fluxo Geral do Sistema


- Usuário faz login ou se cadastra


- Vê o catálogo de todos alimentos


- Busca alimentos


- Adiciona ou remove itens do pedido


- Subtotal, total e imposto são calculados automaticamente


- Finaliza pedido


-  Histórico é atualizado


- Usuário avalia o pedido


