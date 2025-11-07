/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import dao.UsuarioDAO;
import dao.Conexao;
import model.Usuario;
import view.Home;
import view.Login;

public class ControleLogin {
    private Login telaLogin;

    public ControleLogin(Login telaLogin) {
        this.telaLogin = telaLogin;
    }
    
     public void login(){
        Usuario usuario = new Usuario(null, telaLogin.getTxtEmail().getText(), telaLogin.getTxtSenha().getText());
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            UsuarioDAO dao = new UsuarioDAO(conn);
            ResultSet res = dao.consultar(usuario);
            if (res.next()){
                JOptionPane.showMessageDialog(telaLogin, "Login realizado!", "Aviso.", JOptionPane.INFORMATION_MESSAGE);
                String nome = res.getString("nome");
                String email = res.getString("email");
                String senha = res.getString("senha");
                Home telaBusca = new Home(new Usuario(nome, email, senha));
                telaBusca.setVisible(true);
                telaLogin.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(telaLogin, "Login não efetuado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(telaLogin, "Erro de conexão com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
