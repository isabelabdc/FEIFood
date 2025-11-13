/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControleHistorico;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Pedido;
import model.PedidoAlimento;
import model.Usuario;


public class Pedidos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pedidos.class.getName());

    private ControleHistorico controle;
    
    /**
     * Creates new form Pedidos
     * @param usuario
     */
    public Pedidos(Usuario usuario) {
        initComponents();
        controle = new ControleHistorico(this, usuario);
        controle.historico();
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

    public JButton getBtnSacola() {
        return btnSacola;
    }

    public void setBtnSacola(JButton btnSacola) {
        this.btnSacola = btnSacola;
    }

    public JLabel getLblHistorico() {
        return lblHistorico;
    }

    public void setLblHistorico(JLabel lblHistorico) {
        this.lblHistorico = lblHistorico;
    }

    public JLabel getLblImagem() {
        return lblImagem;
    }

    public void setLblImagem(JLabel lblImagem) {
        this.lblImagem = lblImagem;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public Panel getPnBackground() {
        return pnBackground;
    }

    public void setPnBackground(Panel pnBackground) {
        this.pnBackground = pnBackground;
    }

    public JPanel getPnHistorico() {
        return pnHistorico;
    }

    public void setPnHistorico(JPanel pnHistorico) {
        this.pnHistorico = pnHistorico;
    }

    public JPanel getPnMenu() {
        return pnMenu;
    }

    public void setPnMenu(JPanel pnMenu) {
        this.pnMenu = pnMenu;
    }

    public ScrollPane getScrllpnHistorico() {
        return scrllpnHistorico;
    }

    public void setScrllpnHistorico(ScrollPane scrllpnHistorico) {
        this.scrllpnHistorico = scrllpnHistorico;
    }

    public void mostrarPedidos(ArrayList<Pedido> pedidos){
        pnHistorico.removeAll();
        pnHistorico.setLayout(new BoxLayout(pnHistorico, BoxLayout.Y_AXIS));
        pnHistorico.setBackground(Color.WHITE);
        //para cada pedido:
        for (Pedido p : pedidos) {
            //cria um novo painel:
            JPanel pnPedido = new JPanel();
            pnPedido.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
            pnPedido.setLayout(new BoxLayout(pnPedido, BoxLayout.Y_AXIS));
            pnPedido.setBackground(Color.WHITE);
            pnPedido.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.RED), 
                "Pedido #" + p.getIdPedido() + " - " + p.getStatus()
                ),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
            ));

            //cria o painel contendo os alimentos/itens do pedido:
            JPanel pnItens = new JPanel();
            pnItens.setLayout(new BoxLayout(pnItens, BoxLayout.Y_AXIS));
            pnItens.setBackground(Color.WHITE);
            pnItens.setAlignmentX(Component.LEFT_ALIGNMENT);

            //para cada alimento/item dentro do pedido:
            for (PedidoAlimento item : p.getItens()) {
                JPanel pnItem = new JPanel();
                pnItem.setLayout(new BoxLayout(pnItem, BoxLayout.X_AXIS));
                pnItem.setBackground(Color.WHITE);
                pnItem.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel lblNome = new JLabel(item.getAlimento().getNome());
                JLabel lblQuantidade = new JLabel("x" + item.getQuantidade());
                JLabel lblSubtotal = new JLabel("R$ " + String.format("%.2f", item.getSubtotal()));

                pnItem.add(lblNome);
                pnItem.add(Box.createHorizontalStrut(5));
                pnItem.add(lblQuantidade);
                pnItem.add(Box.createHorizontalStrut(25));
                pnItem.add(lblSubtotal);
            
                pnItens.add(pnItem);
            }

            //linha final: total do pedido e estrelas(botões)
            JPanel pnInferior = new JPanel();
            pnInferior.setLayout(new BoxLayout(pnInferior, BoxLayout.X_AXIS));
            pnInferior.setBackground(Color.WHITE);
            pnInferior.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lblTotal = new JLabel("Total: R$ " + String.format("%.2f", p.getPrecoTotal()));
            lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lblTotal.setForeground(Color.RED);
            
            pnInferior.add(lblTotal);
            pnInferior.add(Box.createHorizontalGlue());

            ArrayList<JButton> estrelas = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                JButton estrela = new JButton("★");
                estrela.setFont(new Font("SansSerif", Font.PLAIN, 22));
                estrela.setFocusPainted(false);
                estrela.setBorderPainted(false);
                estrela.setBackground(Color.WHITE);
                estrela.setForeground(Color.LIGHT_GRAY);

                int indice = i + 1;
                estrela.addActionListener(e -> {
                    controle.avaliar(p, indice);
                    atualizarEstrelas(estrelas, indice);
                });
                estrelas.add(estrela);
                pnInferior.add(estrela);
            }

            //preencher estrelas se o pedido já tiver avaliação:
            if (p.getAvaliacao() > 0) {
                atualizarEstrelas(estrelas, p.getAvaliacao());
            }

            //adiciona tudo ao painel do pedido:
            pnPedido.add(pnItens);
            pnPedido.add(Box.createVerticalStrut(5));
            pnPedido.add(pnInferior);

            //adiciona ao painel principal:
            pnHistorico.add(pnPedido);
        }

        pnHistorico.revalidate();
        pnHistorico.repaint();
    }
    
    private void atualizarEstrelas(ArrayList<JButton> estrelas, int avaliacao) {
        for (int i = 0; i < estrelas.size(); i++) {
            if (i < avaliacao) {
                estrelas.get(i).setForeground(Color.YELLOW);
            } else {
                estrelas.get(i).setForeground(Color.LIGHT_GRAY);
            }
        }
    }
    
    public void mensagem(){
        pnHistorico.removeAll();
        pnHistorico.setLayout(new BorderLayout());
        pnHistorico.setBackground(Color.WHITE);
        JLabel lblMensagem = new JLabel("Você ainda não finalizou nenhum pedido...");
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pnHistorico.add(lblMensagem, BorderLayout.CENTER);
        pnHistorico.revalidate();
        pnHistorico.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnBackground = new java.awt.Panel();
        pnMenu = new javax.swing.JPanel();
        btnPedidos = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnSacola = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblImagem = new javax.swing.JLabel();
        lblHistorico = new javax.swing.JLabel();
        scrllpnHistorico = new java.awt.ScrollPane();
        pnHistorico = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnMenu.setBackground(java.awt.Color.red);
        pnMenu.setForeground(new java.awt.Color(255, 255, 255));

        btnPedidos.setBackground(new java.awt.Color(204, 0, 51));
        btnPedidos.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnPedidos.setForeground(new java.awt.Color(255, 255, 255));
        btnPedidos.setText("PEDIDOS");

        btnHome.setBackground(java.awt.Color.red);
        btnHome.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("BUSCA");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

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
                .addGap(109, 109, 109)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnSacola, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSacola, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Yu Gothic Medium", 1, 30)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.red);
        lblTitulo.setText("Meus pedidos");

        lblImagem.setBackground(new java.awt.Color(255, 255, 255));
        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img_feifood.png"))); // NOI18N

        lblHistorico.setBackground(new java.awt.Color(255, 255, 255));
        lblHistorico.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lblHistorico.setText("Histórico:");

        scrllpnHistorico.setBackground(new java.awt.Color(255, 255, 255));

        pnHistorico.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnHistoricoLayout = new javax.swing.GroupLayout(pnHistorico);
        pnHistorico.setLayout(pnHistoricoLayout);
        pnHistoricoLayout.setHorizontalGroup(
            pnHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );
        pnHistoricoLayout.setVerticalGroup(
            pnHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        scrllpnHistorico.add(pnHistorico);

        javax.swing.GroupLayout pnBackgroundLayout = new javax.swing.GroupLayout(pnBackground);
        pnBackground.setLayout(pnBackgroundLayout);
        pnBackgroundLayout.setHorizontalGroup(
            pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnBackgroundLayout.createSequentialGroup()
                .addComponent(lblImagem)
                .addGap(75, 75, 75)
                .addComponent(lblTitulo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnBackgroundLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrllpnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHistorico))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pnBackgroundLayout.setVerticalGroup(
            pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBackgroundLayout.createSequentialGroup()
                .addGroup(pnBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBackgroundLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblTitulo)))
                .addGap(18, 18, 18)
                .addComponent(lblHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrllpnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
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

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.setVisible(false);
        Usuario usuario = controle.getUsuario();
        Home telaBusca = new Home(usuario);
        telaBusca.setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnSacolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacolaActionPerformed
        this.setVisible(false);
        Usuario usuario = controle.getUsuario();
        Sacola telaSacola = new Sacola(usuario);
        telaSacola.setVisible(true);
    }//GEN-LAST:event_btnSacolaActionPerformed

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
//        java.awt.EventQueue.invokeLater(() -> new Pedidos().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnSacola;
    private javax.swing.JLabel lblHistorico;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblTitulo;
    private java.awt.Panel pnBackground;
    private javax.swing.JPanel pnHistorico;
    private javax.swing.JPanel pnMenu;
    private java.awt.ScrollPane scrllpnHistorico;
    // End of variables declaration//GEN-END:variables
}
