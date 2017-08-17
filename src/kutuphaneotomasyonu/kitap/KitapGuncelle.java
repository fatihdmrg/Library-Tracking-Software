/*
 * Copyright (C) 2017 fxd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package kutuphaneotomasyonu.kitap;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kutuphaneotomasyonu.SqlBaglantisi;
import kutuphaneotomasyonu.uye.Üyeler;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author fxd
 */
public class KitapGuncelle extends javax.swing.JFrame {

    /**
     * Creates new form KitapGuncelle
     */
    SqlBaglantisi baglanti = new SqlBaglantisi();
    int kitapId;

    public KitapGuncelle() {
        initComponents();
        KitapTablo();
        
        try {
            baglanti.Baglan(new SqlBaglantisi().getDbUrl(), new SqlBaglantisi().getdbKullanici(), new SqlBaglantisi().getdbSifre());

            try {
                /*
                    tablonun modelinin alıp seçme olayı ekledim.
                 */
                jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        try {

                            int row = jTable1.getSelectedRow();
                            String tableClick = (jTable1.getModel().getValueAt(row, 0).toString());

                            String sql = "select * from kitaplar where id='" + tableClick + "'";

                            SqlBaglantisi.prs = SqlBaglantisi.con.prepareStatement(sql);
                            SqlBaglantisi.rs = SqlBaglantisi.prs.executeQuery();

                            if (SqlBaglantisi.rs.next()) {
                                kitapAdi.setText(SqlBaglantisi.rs.getString("adi"));
                                stokMiktari.setText(SqlBaglantisi.rs.getInt("stok_miktari") + "");
                                kitapId = SqlBaglantisi.rs.getInt("id");
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Veritabanı Bağlantısında Hata Oluştu", "Veritabanı Bağlantısında Hata Oluştu", HEIGHT);
                e.printStackTrace();
                try {
                    SqlBaglantisi.con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Kitaplar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KitapGuncelle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void KitapTablo() {
        try {
            //Veritabanı bağlantısı
            baglanti.Baglan(new SqlBaglantisi().getDbUrl(), new SqlBaglantisi().getdbKullanici(), new SqlBaglantisi().getdbSifre());

            //String sql="select id,adi,basim_yili,kategori,stok_miktari from kitaplar";
            String sql = "SELECT kitaplar.id,adi,basim_yili,stok_miktari,kategoriler.kategori FROM kitaplar join kategoriler on kitaplar.kategori=kategoriler.id";
            SqlBaglantisi.prs = SqlBaglantisi.con.prepareStatement(sql);
            SqlBaglantisi.rs = SqlBaglantisi.prs.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(SqlBaglantisi.rs));
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);

        } catch (Exception ex) {
            try {
                SqlBaglantisi.prs.close();
                SqlBaglantisi.rs.close();
                SqlBaglantisi.con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(Kitaplar.class.getName()).log(Level.SEVERE, null, ex1);
            }
            ex.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kitapAdi = new javax.swing.JTextField();
        GuncelleButton = new javax.swing.JButton();
        stokMiktari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        GuncelleButton.setText("Güncelle");
        GuncelleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuncelleButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Kitap Adı");

        jLabel4.setText("Stok Miktarı");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GuncelleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stokMiktari, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kitapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(GuncelleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(kitapAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stokMiktari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GuncelleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuncelleButtonActionPerformed
        // TODO add your handling code here:
        try {
            baglanti.Baglan(new SqlBaglantisi().getDbUrl(), new SqlBaglantisi().getdbKullanici(), new SqlBaglantisi().getdbSifre());
            SqlBaglantisi.prs = SqlBaglantisi.con.prepareStatement("update kitaplar set adi='" + kitapAdi.getText() + "',stok_miktari='" + stokMiktari.getText() + "' where id='" + kitapId + "'");
            SqlBaglantisi.prs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kitap Güncellendi", "Kitap Güncellendi", WIDTH);
            SqlBaglantisi.con.close();

            KitapTablo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kitap Güncellenemedi", "Kitap Güncellenemedi", HEIGHT);
            e.printStackTrace();
        } finally {
            try {
                SqlBaglantisi.con.close();
                SqlBaglantisi.prs.close();
                SqlBaglantisi.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Üyeler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_GuncelleButtonActionPerformed

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
            java.util.logging.Logger.getLogger(KitapGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KitapGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KitapGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KitapGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KitapGuncelle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GuncelleButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kitapAdi;
    private javax.swing.JTextField stokMiktari;
    // End of variables declaration//GEN-END:variables
}
