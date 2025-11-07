/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControleAlimento;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Alimento;
import model.Usuario;

public class Home extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Home.class.getName());

    private ControleAlimento controle;
    
    /**
     * Creates new form Home
     * @param usuario
     */
    public Home(Usuario usuario) {
        initComponents();
        lblOla.setText("Olá, " +(usuario.getNome())+ "!");
        controle = new ControleAlimento(this, usuario);
        controle.listarAlimentos(); //automaticamente lista todos os alimentos do banco ao abrir a tela;
        this.setLocationRelativeTo(null);
    }

    //getters e setters
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnHome() {
        return btnHome;
    }

    public void setBtnHome(JButton btnHome) {
        this.btnHome = btnHome;
    }

    public JButton getBtnPedidos() {
        return btnPedidos;
    }

    public void setBtnPedidos(JButton btnPedidos) {
        this.btnPedidos = btnPedidos;
    }

    public JButton getBtnSacola() {
        return btnSacola;
    }

    public void setBtnSacola(JButton btnSacola) {
        this.btnSacola = btnSacola;
    }

    public JLabel getLblBemVindo() {
        return lblBemVindo;
    }

    public void setLblBemVindo(JLabel lblBemVindo) {
        this.lblBemVindo = lblBemVindo;
    }

    public JLabel getLblComer() {
        return lblComer;
    }

    public void setLblComer(JLabel lblComer) {
        this.lblComer = lblComer;
    }

    public JLabel getLblImagem() {
        return lblImagem;
    }

    public void setLblImagem(JLabel lblImagem) {
        this.lblImagem = lblImagem;
    }

    public Label getLblOla() {
        return lblOla;
    }

    public void setLblOla(Label lblOla) {
        this.lblOla = lblOla;
    }

    public JPanel getPnAlimentos() {
        return pnAlimentos;
    }

    public void setPnAlimentos(JPanel pnAlimentos) {
        this.pnAlimentos = pnAlimentos;
    }

    public JPanel getPnBackground() {
        return pnBackground;
    }

    public void setPnBackground(JPanel pnBackground) {
        this.pnBackground = pnBackground;
    }

    public JPanel getPnMenu() {
        return pnMenu;
    }

    public void setPnMenu(JPanel pnMenu) {
        this.pnMenu = pnMenu;
    }

    public ScrollPane getScrllpnAlimentos() {
        return scrllpnAlimentos;
    }

    public void setScrllpnAlimentos(ScrollPane scrllpnAlimentos) {
        this.scrllpnAlimentos = scrllpnAlimentos;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    //mostrar alimentos no painel principal:
    public void mostrarAlimentos(ArrayList<Alimento> alimentos) {
        pnAlimentos.removeAll();
        pnAlimentos.setLayout(new BoxLayout(pnAlimentos, BoxLayout.Y_AXIS));
        pnAlimentos.setBackground(Color.WHITE);

        //para cada alimento:
        for (Alimento a : alimentos) {
            //cria um painel Item(contendo informações do alimento e um botão)
            JPanel pnItem = new JPanel();
            pnItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
            pnItem.setLayout(new BoxLayout(pnItem, BoxLayout.Y_AXIS));
            pnItem.setBackground(Color.WHITE);
            pnItem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), a.getNome()),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

            //linha 1: descrição
            JLabel lblDescricao = new JLabel("Descrição: " + a.getDescricao());
            lblDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            //linha 2: preço, tipo e botão:
            JPanel pnLinha2 = new JPanel();
            pnLinha2.setLayout(new BoxLayout(pnLinha2, BoxLayout.X_AXIS));
            pnLinha2.setBackground(Color.WHITE);
            pnLinha2.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            JLabel lblPreco = new JLabel("Preço: R$ " + String.format("%.2f", a.getPreco()));
            JLabel lblTipo = new JLabel("Tipo: " + a.getTipo());

            JButton btnAdd = new JButton("Adicionar à sacola");
            btnAdd.setBackground(Color.RED); 
            btnAdd.setForeground(Color.WHITE);
            
            pnLinha2.add(lblPreco);
            pnLinha2.add(Box.createHorizontalStrut(15)); //espaço;
            pnLinha2.add(lblTipo);
            pnLinha2.add(Box.createHorizontalGlue()); //empurra o botão para a direita;
            pnLinha2.add(btnAdd);

            //adiciona os componentes ao pnItem:
            pnItem.add(lblDescricao);
            pnItem.add(pnLinha2);

            //adiciona o item ao painel principal:
            pnAlimentos.add(pnItem);

            //event listener: 
            btnAdd.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Adicionado ao pedido: " + a.getNome(), "Sacola", JOptionPane.INFORMATION_MESSAGE);
            });
        }

        pnAlimentos.revalidate();
        pnAlimentos.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnBackground = new javax.swing.JPanel();
        lblBemVindo = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblOla = new java.awt.Label();
        lblImagem = new javax.swing.JLabel();
        lblComer = new javax.swing.JLabel();
        pnMenu = new javax.swing.JPanel();
        btnPedidos = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnSacola = new javax.swing.JButton();
        scrllpnAlimentos = new java.awt.ScrollPane();
        pnAlimentos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnBackground.setBackground(new java.awt.Color(255, 255, 255));

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));
        txtBuscar.setText("Buscar por alimento");
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarMouseClicked(evt);
            }
        });

        btnBuscar.setBackground(java.awt.Color.red);
        btnBuscar.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblOla.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        lblOla.setForeground(java.awt.Color.red);
        lblOla.setText("Olá,");

        lblImagem.setBackground(new java.awt.Color(255, 255, 255));
        lblImagem.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img_feifood.png"))); // NOI18N

        lblComer.setBackground(new java.awt.Color(255, 255, 255));
        lblComer.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblComer.setForeground(java.awt.Color.red);
        lblComer.setText("O que deseja comer? ");

        pnMenu.setBackground(java.awt.Color.red);
        pnMenu.setForeground(new java.awt.Color(255, 255, 255));

        btnPedidos.setBackground(java.awt.Color.red);
        btnPedidos.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnPedidos.setForeground(new java.awt.Color(255, 255, 255));
        btnPedidos.setText("PEDIDOS");
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });

        btnHome.setBackground(new java.awt.Color(153, 0, 0));
        btnHome.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("BUSCA");

        btnSacola.setBackground(java.awt.Color.red);
        btnSacola.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnSacola.setForeground(new java.awt.Color(255, 255, 255));
        btnSacola.setText("SACOLA");
        btnSacola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacolaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(btnSacola, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSacola, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnAlimentosLayout = new javax.swing.GroupLayout(pnAlimentos);
        pnAlimentos.setLayout(pnAlimentosLayout);
        pnAlimentosLayout.setHorizontalGroup(
            pnAlimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );
        pnAlimentosLayout.setVerticalGroup(
            pnAlimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        scrllpnAlimentos.add(pnAlimentos);

        javax.swing.GroupLayout pnBackgroundLayout = new javax.swing.GroupLayout(pnBackground);
        pnBackground.setLayout(pnBackgroundLayout);
        pnBackgroundLayout.setHorizontalGroup(
            pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBackgroundLayout.createSequentialGroup()
                .addGroup(pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBackgroundLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblComer)
                            .addGroup(pnBackgroundLayout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrllpnAlimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnBackgroundLayout.createSequentialGroup()
                        .addComponent(lblImagem)
                        .addGap(65, 65, 65)
                        .addComponent(lblOla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(343, 343, 343)
                        .addComponent(lblBemVindo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
            .addComponent(pnMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnBackgroundLayout.setVerticalGroup(
            pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBackgroundLayout.createSequentialGroup()
                .addGroup(pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBackgroundLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblBemVindo))
                    .addGroup(pnBackgroundLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblOla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(lblComer)
                .addGap(18, 18, 18)
                .addGroup(pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(scrllpnAlimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        controle.buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPedidosActionPerformed

    private void btnSacolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacolaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSacolaActionPerformed

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked
        txtBuscar.setText("");
    }//GEN-LAST:event_txtBuscarMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new Home().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnSacola;
    private javax.swing.JLabel lblBemVindo;
    private javax.swing.JLabel lblComer;
    private javax.swing.JLabel lblImagem;
    private java.awt.Label lblOla;
    private javax.swing.JPanel pnAlimentos;
    private javax.swing.JPanel pnBackground;
    private javax.swing.JPanel pnMenu;
    private java.awt.ScrollPane scrllpnAlimentos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
