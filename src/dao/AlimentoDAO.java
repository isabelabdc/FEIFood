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


public class AlimentoDAO {
    private Connection conn;

    public AlimentoDAO(Connection conn) {
        this.conn = conn;
    }
    
    //lê todos os alimentos cadastrados no banco de dados e os retorna em uma arraylist:
    public ArrayList<Alimento> consultarTodos() throws SQLException{
        ArrayList<Alimento> alimentos = new ArrayList<>();
        String sql = "SELECT * FROM alimentos ORDER BY nome";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        //adiciona cada alimento salvo como um elemento da arraylist:
        while(res.next()){
            Alimento a = new Alimento();
            a.setId(res.getInt("id"));
            a.setNome(res.getString("nome"));
            a.setDescricao(res.getString("descricao"));
            a.setPreco(res.getDouble("preco"));
            a.setTipo(res.getString("tipo"));
            alimentos.add(a); 
        }
        
        res.close();
        statement.close();
        return alimentos;
    }
    
    //procura alimentos no banco de dados cujos nomes correspondem a busca:
    public ArrayList<Alimento> consultarBusca(String busca) throws SQLException{
        ArrayList<Alimento> resultadoBusca = new ArrayList<>();
        String sql = "SELECT * FROM alimentos WHERE LOWER(nome) LIKE LOWER(?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "%" + busca + "%");
        ResultSet res = statement.executeQuery();
        //adiciona cada alimento encontrado como um elemento da arraylist:
        while(res.next()){
            Alimento a = new Alimento();
            a.setId(res.getInt("id"));
            a.setNome(res.getString("nome"));
            a.setDescricao(res.getString("descricao"));
            a.setPreco(res.getDouble("preco"));
            a.setTipo(res.getString("tipo"));
            resultadoBusca.add(a); 
        }
        
        res.close();
        statement.close();
        return resultadoBusca;
    }
    
}
