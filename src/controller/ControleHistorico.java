/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.PedidoAlimentoDAO;
import dao.PedidoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Pedido;
import model.Usuario;
import view.Pedidos;

public class ControleHistorico {
    private Pedidos telaPedidos;
    private Usuario usuario;

    public ControleHistorico(Pedidos telaPedidos, Usuario usuario) {
        this.telaPedidos = telaPedidos;
        this.usuario = usuario;
    }
     
    public Usuario getUsuario() {
        return usuario;
    }  
        
    public void historico() {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            PedidoDAO dao = new PedidoDAO(conn);
            PedidoAlimentoDAO daoItens = new PedidoAlimentoDAO(conn);
            
            ArrayList<Pedido> pedidos = dao.consultarPedidos(usuario);
            System.out.println("Consultando pedidos...");
            System.out.println("Pedidos encontrados: " + pedidos.size());
            
            //valida se o histórico está vazio:
            if(pedidos.isEmpty()){
                telaPedidos.mensagem();
                return;
            }
            
            //consulta os alimentos contidos nos pedidos encontrados:
            for (Pedido p : pedidos){
                p.setItens(daoItens.consultarItens(p));
            }
            
            //mostra o histórico de pedidos:
            telaPedidos.mostrarPedidos(pedidos);
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(telaPedidos, "Erro ao carregar histórico:" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);  
        }    
    }

    public void avaliar(Pedido pedido, int avaliacao){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            pedido.setAvaliacao(avaliacao);
            PedidoDAO dao = new PedidoDAO(conn);
            dao.atualizarAvaliacao(pedido.getIdPedido(), avaliacao);
            System.out.println("Avaliacao salva!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(telaPedidos, "Erro ao salvar avaliação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
