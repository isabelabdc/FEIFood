/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import dao.UsuarioDAO;
import dao.Conexao;
import model.Usuario;
import view.Cadastro;
import view.Login;

public class ControleCadastro {
    private Cadastro telaCadastro;

    public ControleCadastro(Cadastro telaCadastro) {
        this.telaCadastro = telaCadastro;
    }
    
    public void cadastro(){
        //valida se tem campos em branco:
        if(telaCadastro.getTxtNome().getText().isEmpty() || telaCadastro.getTxtEmail().getText().isEmpty() || telaCadastro.getTxtSenha().getText().isEmpty()){
            JOptionPane.showMessageDialog(telaCadastro, "Preencha os campos vazios!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nome = telaCadastro.getTxtNome().getText();
        String email = telaCadastro.getTxtEmail().getText();
        String senha = telaCadastro.getTxtSenha().getText();
        Usuario usuario = new Usuario(nome, email, senha);
        
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuarioDAO dao = new UsuarioDAO(conn);
            dao.inserir(usuario);
            JOptionPane.showMessageDialog(telaCadastro, "Cadastro realizado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            Login telaLogin = new Login();
            telaLogin.setVisible(true);
            telaCadastro.setVisible(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(telaCadastro, "Cadastro não efetuado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
