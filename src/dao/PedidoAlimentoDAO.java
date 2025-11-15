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
import model.Alimento;
import model.Bebida;
import model.Comida;
import model.Pedido;
import model.PedidoAlimento;


public class PedidoAlimentoDAO {
    private Connection conn;

    public PedidoAlimentoDAO(Connection conn) {
        this.conn = conn;
    }   

    //consulta todos os alimentos de um pedido e os retorna em arraylist:
    public ArrayList<PedidoAlimento> consultarItens(Pedido pedido) throws SQLException{
        ArrayList<PedidoAlimento> itens = new ArrayList<>();
        String sql = "SELECT pa.*, a.nome, a.descricao, a.preco, a.tipo, a.categoria FROM pedido_alimento pa JOIN alimentos a ON pa.id_alimento = a.id_alimento WHERE pa.id_pedido = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pedido.getIdPedido());
        ResultSet res = ps.executeQuery();

        while (res.next()) {
            Alimento alimento;
            if (res.getString("tipo").equals("Bebida")) {
                alimento = new Bebida(res.getInt("id_alimento"),
                                    res.getString("nome"),
                                    res.getString("descricao"),
                                    res.getString("categoria"),
                                    res.getString("tipo"),
                                    res.getDouble("preco")
                                );
            } else {
                alimento = new Comida( res.getInt("id_alimento"),
                                       res.getString("nome"),
                                       res.getString("descricao"),
                                       res.getString("categoria"),
                                       res.getString("tipo"),
                                       res.getDouble("preco")
                                );
            }

            PedidoAlimento item = new PedidoAlimento();
            item.setPedido(pedido);
            item.setAlimento(alimento);
            item.setQuantidade(res.getInt("quantidade"));
            item.setSubtotal(res.getDouble("subtotal"));
            
            itens.add(item);
        }

        res.close();
        ps.close();
        return itens;
    }
   
    public void atualizarItem(PedidoAlimento item) throws SQLException {
        String sql = "UPDATE pedido_alimento SET quantidade = ?, subtotal = ? WHERE id_pedido = ? AND id_alimento = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, item.getQuantidade());
        statement.setDouble(2, item.getSubtotal());
        statement.setInt(3, item.getPedido().getIdPedido());
        statement.setInt(4, item.getAlimento().getIdAlimento());
        statement.executeUpdate();
        statement.close();
    }
    
    //adicionar um alimento à sacola:
    public void adicionarItem(PedidoAlimento item) throws SQLException{
        //verifica se já existe esse alimento no pedido:
        String sqlConsulta = "SELECT quantidade FROM pedido_alimento WHERE id_pedido = ? AND id_alimento = ?";
        PreparedStatement psConsulta = conn.prepareStatement(sqlConsulta);
        psConsulta.setInt(1, item.getPedido().getIdPedido());
        psConsulta.setInt(2, item.getAlimento().getIdAlimento());
        ResultSet res = psConsulta.executeQuery();

        //se já existe, atualiza a quantidade e o subtotal:
        if (res.next()) {
            int novaQuantidade = res.getInt("quantidade") + item.getQuantidade();
            double novoSubtotal = novaQuantidade * item.getAlimento().getPreco();

            String sqlUpdate = "UPDATE pedido_alimento SET quantidade = ?, subtotal = ? WHERE id_pedido = ? AND id_alimento = ?";
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setInt(1, novaQuantidade);
            psUpdate.setDouble(2, novoSubtotal);
            psUpdate.setInt(3, item.getPedido().getIdPedido());
            psUpdate.setInt(4, item.getAlimento().getIdAlimento());
            psUpdate.executeUpdate();
            psUpdate.close();
        } else {
            //se não existe, insere um novo alimento:
            String sqlInsert = "INSERT INTO pedido_alimento (id_pedido, id_alimento, quantidade, subtotal) VALUES (?, ?, ?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            psInsert.setInt(1, item.getPedido().getIdPedido());
            psInsert.setInt(2, item.getAlimento().getIdAlimento());
            psInsert.setInt(3, item.getQuantidade());
            psInsert.setDouble(4, item.getSubtotal());
            psInsert.executeUpdate();
            psInsert.close();
        }

        res.close();
        psConsulta.close();
    }
   
    //remover um item da sacola
    public void removerItem(int idPedido, int idAlimento) throws SQLException {
        String sqlConsulta = "SELECT quantidade FROM pedido_alimento WHERE id_pedido = ? AND id_alimento = ?";
        PreparedStatement psConsulta = conn.prepareStatement(sqlConsulta);
        psConsulta.setInt(1, idPedido);
        psConsulta.setInt(2, idAlimento);
        ResultSet res = psConsulta.executeQuery();

        if (res.next()) {
            int quantidadeAtual = res.getInt("quantidade");

            if (quantidadeAtual > 1) {
                String sqlUpdate = "UPDATE pedido_alimento SET quantidade = ? WHERE id_pedido = ? AND id_alimento = ?";
                PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
                psUpdate.setInt(1, quantidadeAtual - 1);
                psUpdate.setInt(2, idPedido);
                psUpdate.setInt(3, idAlimento);
                psUpdate.executeUpdate();
                psUpdate.close();

            } else {
                //se a quantidade era 1, remove o item da sacola:
                String sqlDelete = "DELETE FROM pedido_alimento WHERE id_pedido = ? AND id_alimento = ?";
                PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
                psDelete.setInt(1, idPedido);
                psDelete.setInt(2, idAlimento);
                psDelete.executeUpdate();
                psDelete.close();
            }
        }

        res.close();
        psConsulta.close();
    }

}
