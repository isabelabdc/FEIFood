/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.PedidoDAO;
import dao.PedidoAlimentoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Alimento;
import model.Imposto;
import model.Pedido;
import model.PedidoAlimento;
import model.Usuario;
import view.Home;
import view.Sacola;

public class ControlePedido {
    private Sacola telaSacola;
    private Home telaBusca;
    private Usuario usuario;
    private Pedido pedidoAtual;

    public ControlePedido() {
    }

    public ControlePedido(Home telaBusca, Usuario usuario) {
        this.telaBusca = telaBusca;
        this.usuario = usuario;
    }

    public ControlePedido(Sacola telaSacola, Usuario usuario) {
        this.telaSacola = telaSacola;
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void adicionarItem(Alimento alimento, int quantidade){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            PedidoDAO daoPedido = new PedidoDAO(conn);
            PedidoAlimentoDAO dao = new PedidoAlimentoDAO(conn);
            
            if(pedidoAtual == null){
                pedidoAtual = daoPedido.inicializarPedido(usuario);
            }
            
            PedidoAlimento jaExiste = null;
            for (PedidoAlimento item : pedidoAtual.getItens()){
                if(item.getAlimento().getIdAlimento() == alimento.getIdAlimento()){
                    jaExiste = item;
                    break;
                }
            }
            
            //se o alimento já existe na sacola:
            if (jaExiste != null) {
            //atualiza quantidade e subtotal:
            int novaQuantidade = jaExiste.getQuantidade() + quantidade;
            jaExiste.setQuantidade(novaQuantidade);
            jaExiste.atualizarSubtotal();  //método da classe PedidoAlimento(model)
            dao.atualizarItem(jaExiste);
            System.out.println("Atualizado item: " + alimento.getNome() + 
                               " nova qtd: " + novaQuantidade +
                               " subtotal: " + jaExiste.getSubtotal());
        } else {
            //se o alimento não existe na sacola:
            double imposto = 0.00;
            if (alimento instanceof Imposto bebidaComImposto) {
                imposto = bebidaComImposto.calcularImposto();
                if (imposto > 0){
                    JOptionPane.showMessageDialog(telaSacola, "Imposto de 10% aplicado(álcool) valor: R$ " + String.format("%.2f", imposto), "Imposto", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
            double subtotal = (alimento.getPreco() + imposto) * quantidade;
            PedidoAlimento novoItem = new PedidoAlimento();
            novoItem.setPedido(pedidoAtual);
            novoItem.setAlimento(alimento);
            novoItem.setQuantidade(quantidade);
            novoItem.setSubtotal(subtotal);

            dao.adicionarItem(novoItem);
            pedidoAtual.getItens().add(novoItem);
            
            System.out.println("Novo item adicionado: " + alimento.getNome() + 
                               " qtd: " + quantidade +
                               " subtotal: " + subtotal);
        }
            
            //atualiza:
            pedidoAtual.atualizarPrecoTotal();
            daoPedido.atualizarPreco(pedidoAtual);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(telaBusca, "Erro ao adicionar item à sacola: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void carregarSacola() {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            PedidoDAO daoPedido = new PedidoDAO(conn);
            PedidoAlimentoDAO dao = new PedidoAlimentoDAO(conn);

            if (pedidoAtual == null) {
                pedidoAtual = daoPedido.inicializarPedido(usuario);
            }

            //carrega todos os itens do pedido:
            ArrayList<PedidoAlimento> itens = dao.consultarItens(pedidoAtual);
            pedidoAtual.setItens(itens);

            pedidoAtual.atualizarPrecoTotal();  //método da classe Pedido(model)
            daoPedido.atualizarPreco(pedidoAtual);

            if (itens.isEmpty()){
                telaSacola.mensagem();
            } else {
                telaSacola.mostrarItens(itens);
            }
            telaSacola.getLblTotal().setText("Total: R$ " + String.format("%.2f", pedidoAtual.getPrecoTotal()));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(telaSacola, "Erro ao carregar sacola: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   public void removerItem(Alimento alimento) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            PedidoDAO daoPedido = new PedidoDAO(conn);
            PedidoAlimentoDAO dao = new PedidoAlimentoDAO(conn);

            dao.removerItem(pedidoAtual.getIdPedido(), alimento.getIdAlimento());
            
            ArrayList<PedidoAlimento> itens = dao.consultarItens(pedidoAtual);
            pedidoAtual.setItens(itens);
            
            //atualiza:
            for (PedidoAlimento p : itens) {
                p.atualizarSubtotal();
                dao.atualizarItem(p);
            }
            
            pedidoAtual.atualizarPrecoTotal();
            daoPedido.atualizarPreco(pedidoAtual);
            
            if (itens.isEmpty()){
                telaSacola.mensagem();
            } else {
                telaSacola.mostrarItens(itens);
            }
            telaSacola.getLblTotal().setText("Total: R$ " + String.format("%.2f", pedidoAtual.getPrecoTotal()));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(telaSacola, "Erro ao remover item: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void finalizarPedido() {
        if (pedidoAtual == null) {
            JOptionPane.showMessageDialog(telaSacola, "Nenhum pedido em andamento.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }       

        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            PedidoAlimentoDAO dao = new PedidoAlimentoDAO(conn);
            PedidoDAO daoPedido = new PedidoDAO(conn);

            //verifica se o pedido tem itens:
            ArrayList<PedidoAlimento> itens = dao.consultarItens(pedidoAtual);
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(telaSacola, "Sua sacola está vazia.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //atualiza:
            pedidoAtual.setItens(itens);
            pedidoAtual.atualizarPrecoTotal();

            daoPedido.finalizarPedido(pedidoAtual.getIdPedido());

            JOptionPane.showMessageDialog(telaSacola, "Pedido finalizado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            
            //limpa a sacola:
            telaSacola.mensagem();
            pedidoAtual = null;
            telaSacola.getLblTotal().setText("Total: R$ 0.00");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(telaSacola, "Erro ao finalizar pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
        
}
    