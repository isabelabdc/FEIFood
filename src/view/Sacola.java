/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControlePedido;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.PedidoAlimento;
import model.Usuario;


public class Sacola extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sacola.class.getName());

    private ControlePedido controle;
    
    /**
     * Creates new form Sacola
     * @param usuario
     */
    public Sacola(Usuario usuario) {
        initComponents();
        controle = new ControlePedido(this, usuario);
        controle.carregarSacola();
        this.setLocationRelativeTo(null);
    }

    //getters e setters:
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

    public JButton getBtnPedir() {
        return btnPedir;
    }

    public void setBtnPedir(JButton btnPedir) {
        this.btnPedir = btnPedir;
    }

    public JButton getBtnSacola() {
        return btnSacola;
    }

    public void setBtnSacola(JButton btnSacola) {
        this.btnSacola = btnSacola;
    }

    public JLabel getLblImagem() {
        return lblImagem;
    }

    public void setLblImagem(JLabel lblImagem) {
        this.lblImagem = lblImagem;
    }

    public JLabel getLblSacola() {
        return lblSacola;
    }

    public void setLblSacola(JLabel lblSacola) {
        this.lblSacola = lblSacola;
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

    public JPanel getPnSacola() {
        return pnSacola;
    }

    public void setPnSacola(JPanel pnSacola) {
        this.pnSacola = pnSacola;
    }

    public ScrollPane getScrllpnSacola() {
        return scrllpnSacola;
    }

    public void setScrllpnSacola(ScrollPane scrllpnSacola) {
        this.scrllpnSacola = scrllpnSacola;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }   

    //mostra todos os itens adicionados ao pedido atual na sacola:
    public void mostrarItens(ArrayList<PedidoAlimento> itens) {
        pnSacola.removeAll();
        pnSacola.setLayout(new BoxLayout(pnSacola, BoxLayout.Y_AXIS));
        pnSacola.setBackground(Color.WHITE);

        //para cada item:
        for (PedidoAlimento a : itens) {
            //cria um painel Item(contendo informações do alimento)
            JPanel pnItem = new JPanel();
            pnItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
            pnItem.setLayout(new BoxLayout(pnItem, BoxLayout.Y_AXIS));
            pnItem.setBackground(Color.WHITE);
            pnItem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), a.getAlimento().getNome()),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

            //linha 1: descrição
            JLabel lblDescricao = new JLabel("Descrição: " + a.getAlimento().getDescricao());
            lblDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            //linha 2: preço unitário, subtotal, botões e quantidade
            JPanel pnLinha2 = new JPanel();
            pnLinha2.setLayout(new BoxLayout(pnLinha2, BoxLayout.X_AXIS));
            pnLinha2.setBackground(Color.WHITE);
            pnLinha2.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            JLabel lblPrecoUnitario = new JLabel("Preço unitário: R$" + String.format("%.2f", a.getAlimento().getPreco()));
            JLabel lblSubtotal = new JLabel("Subtotal: R$ " + String.format("%.2f", a.getSubtotal()));
            JButton btnMenos = new JButton("-");
            btnMenos.setBackground(Color.RED); 
            btnMenos.setForeground(Color.WHITE);
            JLabel lblQuantidade = new JLabel(String.valueOf(a.getQuantidade()));
            JButton btnMais = new JButton("+");
            btnMais.setBackground(Color.RED); 
            btnMais.setForeground(Color.WHITE);
      
            pnLinha2.add(lblPrecoUnitario);
            pnLinha2.add(Box.createHorizontalStrut(15)); //espaço;
            pnLinha2.add(lblSubtotal);
            pnLinha2.add(Box.createHorizontalGlue()); //empurra so botões para a direita;
            pnLinha2.add(btnMenos);
            pnLinha2.add(Box.createHorizontalStrut(10)); //espaço;
            pnLinha2.add(lblQuantidade);
            pnLinha2.add(Box.createHorizontalStrut(10)); //espaço;
            pnLinha2.add(btnMais);

            //adiciona os componentes ao pnItem:
            pnItem.add(lblDescricao);
            pnItem.add(pnLinha2);

            //adiciona o pnItem ao painel principal:
            pnSacola.add(pnItem);
            
            //event listeners: 
            btnMenos.addActionListener(e -> {
                controle.removerItem(a.getAlimento());
                controle.carregarSacola();
            });
            
            btnMais.addActionListener(e -> {
                controle.adicionarItem(a.getAlimento(), 1);
                controle.carregarSacola();
            });
        }

        pnSacola.revalidate();
        pnSacola.repaint();
    }
    
     public void mensagem(){
        pnSacola.removeAll();
        pnSacola.setLayout(new BorderLayout());
        pnSacola.setBackground(Color.WHITE);
        JLabel lblMensagem = new JLabel("Sua sacola está vazia...");
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pnSacola.add(lblMensagem, BorderLayout.CENTER);
        pnSacola.revalidate();
        pnSacola.repaint();
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
        btnPedir = new javax.swing.JButton();
        pnMenu = new javax.swing.JPanel();
        btnPedidos = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnSacola = new javax.swing.JButton();
        lblSacola = new javax.swing.JLabel();
        lblImagem = new javax.swing.JLabel();
        scrllpnSacola = new java.awt.ScrollPane();
        pnSacola = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnBackground.setBackground(new java.awt.Color(255, 255, 255));

        btnPedir.setBackground(java.awt.Color.red);
        btnPedir.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        btnPedir.setForeground(new java.awt.Color(255, 255, 255));
        btnPedir.setText("FINALIZAR PEDIDO");
        btnPedir.setActionCommand("");
        btnPedir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedirActionPerformed(evt);
            }
        });

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

        btnHome.setBackground(java.awt.Color.red);
        btnHome.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("BUSCA");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnSacola.setBackground(new java.awt.Color(204, 0, 51));
        btnSacola.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnSacola.setForeground(new java.awt.Color(255, 255, 255));
        btnSacola.setText("SACOLA");

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnSacola, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSacola, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblSacola.setBackground(new java.awt.Color(255, 255, 255));
        lblSacola.setFont(new java.awt.Font("Yu Gothic Medium", 1, 30)); // NOI18N
        lblSacola.setForeground(java.awt.Color.red);
        lblSacola.setText("Minha Sacola");

        lblImagem.setBackground(new java.awt.Color(255, 255, 255));
        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/sacola.png"))); // NOI18N

        javax.swing.GroupLayout pnSacolaLayout = new javax.swing.GroupLayout(pnSacola);
        pnSacola.setLayout(pnSacolaLayout);
        pnSacolaLayout.setHorizontalGroup(
            pnSacolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );
        pnSacolaLayout.setVerticalGroup(
            pnSacolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        scrllpnSacola.add(pnSacola);

        lblTotal.setBackground(new java.awt.Color(255, 255, 255));
        lblTotal.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        lblTotal.setForeground(java.awt.Color.red);
        lblTotal.setText("Total: R$0,00");

        javax.swing.GroupLayout pnBackgroundLayout = new javax.swing.GroupLayout(pnBackground);
        pnBackground.setLayout(pnBackgroundLayout);
        pnBackgroundLayout.setHorizontalGroup(
            pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnBackgroundLayout.createSequentialGroup()
                .addComponent(lblImagem)
                .addGap(161, 161, 161)
                .addComponent(lblSacola)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBackgroundLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBackgroundLayout.createSequentialGroup()
                        .addComponent(scrllpnSacola, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBackgroundLayout.createSequentialGroup()
                        .addComponent(btnPedir, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBackgroundLayout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addGap(270, 270, 270))))
        );
        pnBackgroundLayout.setVerticalGroup(
            pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBackgroundLayout.createSequentialGroup()
                .addGroup(pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImagem)
                    .addComponent(lblSacola))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(scrllpnSacola, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPedir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
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

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
        this.setVisible(false);
        Usuario usuario = controle.getUsuario();
        Pedidos telaPedidos = new Pedidos(usuario);
        telaPedidos.setVisible(true);
    }//GEN-LAST:event_btnPedidosActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.setVisible(false);
        Usuario usuario = controle.getUsuario();
        Home telaBusca = new Home(usuario);
        telaBusca.setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnPedirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedirActionPerformed
       controle.finalizarPedido();
    }//GEN-LAST:event_btnPedirActionPerformed

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
//        java.awt.EventQueue.invokeLater(() -> new Sacola().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnPedir;
    private javax.swing.JButton btnSacola;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblSacola;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnBackground;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnSacola;
    private java.awt.ScrollPane scrllpnSacola;
    // End of variables declaration//GEN-END:variables
}
