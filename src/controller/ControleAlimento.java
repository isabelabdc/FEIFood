/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AlimentoDAO;
import dao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Alimento;
import model.Usuario;
import view.Home;

public class ControleAlimento {
    private Home telaBusca;
    private Usuario usuario;

    public ControleAlimento(Home telaBusca, Usuario usuario) {
        this.telaBusca = telaBusca;
        this.usuario = usuario;
    }
    
    public void listarAlimentos(){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            AlimentoDAO dao = new AlimentoDAO(conn);
            ArrayList<Alimento> alimentos = dao.consultarTodos();
            telaBusca.mostrarAlimentos(alimentos);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(telaBusca, "Erro ao carregar alimentos:" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);  
        }    
    }
    
    public void buscar(){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            AlimentoDAO dao = new AlimentoDAO(conn);
            String busca = telaBusca.getTxtBuscar().getText().trim();
            //valida se a caixa de texto está vazia:
            if(busca.isEmpty()){
                JOptionPane.showMessageDialog(telaBusca, "Digite um alimento para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            //consulta ao banco de dados:
            ArrayList<Alimento> resultadoBusca = dao.consultarBusca(busca);
            
            //se não encontrar o alimento buscado:
            if(resultadoBusca.isEmpty()){
                JOptionPane.showMessageDialog(telaBusca, "Nenhum alimento encontrado para:  " + busca, "Aviso", JOptionPane.INFORMATION_MESSAGE);
                ArrayList<Alimento> alimentos = dao.consultarTodos();
                telaBusca.mostrarAlimentos(alimentos);
                return;
            }
            
            telaBusca.mostrarAlimentos(resultadoBusca);
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(telaBusca, "Erro ao carregar alimentos:" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
}
