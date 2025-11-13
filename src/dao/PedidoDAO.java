/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Pedido;
import model.Usuario;


public class PedidoDAO {
    private Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }
    
    //consulta todos os pedidos finalizados e os retorna em uma arraylist(para mostrar no histórico)
    public ArrayList<Pedido> consultarPedidos(Usuario usuario) throws SQLException{
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE email = ? AND status = 'Finalizado'";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getEmail());
        ResultSet res = statement.executeQuery();
        //adiciona cada pedido encontrado para o usuário na arraylist pedidos:
        while (res.next()) {
            Pedido p = new Pedido();
            p.setIdPedido(res.getInt("id_pedido"));
            p.setPrecoTotal(res.getDouble("preco_total"));
            p.setStatus(res.getString("status"));
            p.setAvaliacao(res.getInt("avaliacao"));
            pedidos.add(p);
            }
        
        res.close();
        statement.close();
        return pedidos;
    }
    
    public Pedido inicializarPedido(Usuario usuario) throws SQLException{
        //verifica se já existe um pedido em andamento:
        String sqlConsulta = "SELECT * FROM pedidos WHERE email = ? AND status = 'Em_andamento'";
        PreparedStatement ps = conn.prepareStatement(sqlConsulta);
        ps.setString(1, usuario.getEmail());
        ResultSet res = ps.executeQuery();

        //se já existe, retorna o pedido atual(em andamento)
        if (res.next()) {
            Pedido p = new Pedido();
            p.setIdPedido(res.getInt("id_pedido"));
            p.setUsuario(usuario);
            p.setPrecoTotal(res.getDouble("preco_total"));
            p.setStatus(res.getString("status"));
            p.setAvaliacao(res.getInt("avaliacao"));
            res.close();
            ps.close();
            return p;
        }

        //se não existir, cria um novo pedido:
        String sqlInsert = "INSERT INTO pedidos (email, preco_total, status, avaliacao) VALUES (?, 0, 'Em_andamento', 0) RETURNING id_pedido";
        PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
        psInsert.setString(1, usuario.getEmail());
        ResultSet resInsert = psInsert.executeQuery();

        Pedido novo = new Pedido();
        if (resInsert.next()) {
            novo.setIdPedido(resInsert.getInt("id_pedido"));  //pega o id gerado pelo banco de dados(gerado automaticamente incrementando de 1 em 1)
        }
        novo.setUsuario(usuario);
        novo.setPrecoTotal(0);
        novo.setStatus("Em_andamento");
        novo.setAvaliacao(0);

        resInsert.close();
        psInsert.close();
        return novo;
    }

    public void atualizarPreco(Pedido pedido) throws SQLException{
        String sql = "UPDATE pedidos SET preco_total = ? WHERE id_pedido = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDouble(1, pedido.getPrecoTotal());
        statement.setInt(2, pedido.getIdPedido());
        statement.executeUpdate();
        statement.close();
    }
    
    //muda o status para 'Finalizado':
    public void finalizarPedido(int idPedido) throws SQLException{
        String sql = "UPDATE pedidos SET status = 'Finalizado' WHERE id_pedido = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idPedido);
        statement.executeUpdate();
        statement.close();
    }
    
    
    public void atualizarAvaliacao(int idPedido, int avaliacao) throws SQLException {
        String sql = "UPDATE pedidos SET avaliacao = ? WHERE id_pedido = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, avaliacao);
        statement.setInt(2, idPedido);
        statement.executeUpdate();
        statement.close();
    }
    
}
