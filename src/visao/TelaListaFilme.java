/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import entidades.Filme;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.FilmeDAO;


/**
 *
 * @author julio.gabardo
 */
public class TelaListaFilme extends javax.swing.JFrame {
      private List<Filme>listaFilmes;
    /**
     * Creates new form TelaListaFilme
     */
    public TelaListaFilme() {
        initComponents();
        montarListaFilmes();
    }
    public void montarListaFilmes(){
        listaFilmes = FilmeDAO.listar();
        DefaultTableModel modelo = (DefaultTableModel)tabFilmes.getModel();
        modelo.setRowCount(0);
        for(Filme filme : listaFilmes){
            Object[] linha ={
            filme.getTitulo(),
            filme.getCategoria().getNome(),
            filme.getPreco(),
            filme.getNumeroDias()
        };
            modelo.addRow(linha);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabFilmes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        tabFilmes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo", "Categoria", "Preço", "N°Dias"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabFilmes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabFilmesMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabFilmes);
        if (tabFilmes.getColumnModel().getColumnCount() > 0) {
            tabFilmes.getColumnModel().getColumn(0).setResizable(false);
            tabFilmes.getColumnModel().getColumn(1).setResizable(false);
            tabFilmes.getColumnModel().getColumn(2).setResizable(false);
            tabFilmes.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        new TelaCadastroFilme(this).setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
       int linha = tabFilmes.getSelectedRow();
       if(linha == -1){
           JOptionPane.showMessageDialog(this, "Selecione um filme para alterar!");
       }else{
           TelaCadastroFilme cadastro = new TelaCadastroFilme(this);
           //pega o filme selecionado e envia para a tela de cadastro
           cadastro.setFilme(listaFilmes.get(linha));
           cadastro.setVisible(true);
       }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linha = tabFilmes.getSelectedRow();
        if (linha ==-1) {
            JOptionPane.showMessageDialog(this, "Selecione um filme para excluir:");
        } else {
            Filme filme = listaFilmes.get(linha);
            String mensagem = "Deseja realmente excluir o filme "+filme.getTitulo()+"?";
            int opcao = JOptionPane.showConfirmDialog(this, mensagem, "Confirme a exclusão",JOptionPane.YES_NO_OPTION);
        
          
          
        if(opcao == JOptionPane.YES_OPTION){
            if (FilmeDAO.excluir(filme.getId())) {
                montarListaFilmes();
                JOptionPane.showMessageDialog(this, "Filme excluido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir o Filme!");
            }
        }
    }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tabFilmesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabFilmesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabFilmesMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaListaFilme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListaFilme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListaFilme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListaFilme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListaFilme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabFilmes;
    // End of variables declaration//GEN-END:variables
}
