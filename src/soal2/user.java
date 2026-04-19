package soal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class user extends javax.swing.JFrame {

    DefaultTableModel model;

    public user() {
        initComponents();
        String[] judul = {"ID User", "Username", "Password", "Role"};
        model = new DefaultTableModel(judul, 0);
        tabelUser.setModel(model);
        setLocationRelativeTo(null);
        tambahListenerPencarian();
    }

    private void tambahListenerPencarian() {
        txtcari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                aksiCari();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                aksiCari();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                aksiCari();
            }

            private void aksiCari() {
                String teks = txtcari.getText();
                if (teks.isEmpty()) {
                    int row = tabelUser.getRowCount();
                    for (int a = 0; a < row; a++) {
                        model.removeRow(0);
                    }
                } else {
                    tampilkanData(teks);
                }
            }
        });
    }

    private void tampilkanData(String cari) {
        int row = tabelUser.getRowCount();
        for (int a = 0; a < row; a++) {
            model.removeRow(0);
        }
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            ResultSet rs = cn.createStatement().executeQuery("SELECT * FROM tb_user WHERE username LIKE '%" + cari + "%'");
            while (rs.next()) {
                String data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                model.addRow(data);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal tampil data: " + ex.getMessage());
        }
    }

    private void hapusData() {
        int i = tabelUser.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
            return;
        }
        String id = model.getValueAt(i, 0).toString();
        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin hapus user ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                cn.createStatement().executeUpdate("DELETE FROM tb_user WHERE id_user='" + id + "'");
                tampilkanData("");
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal hapus: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        btnfind = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelUser = new javax.swing.JTable();
        btncl = new javax.swing.JButton();
        btndl = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuuser = new javax.swing.JMenuItem();
        menumhs = new javax.swing.JMenuItem();
        menumk = new javax.swing.JMenuItem();
        menudsn = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuadd = new javax.swing.JMenuItem();
        menupw = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin - User");

        jPanel1.setBackground(new java.awt.Color(65, 22, 233));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/iconktp.jpg"))); // NOI18N
        jLabel2.setText("User Form");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/src.png"))); // NOI18N
        jLabel1.setText("Cari Username :");

        btnfind.setBackground(new java.awt.Color(250, 250, 210));
        btnfind.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnfind.setText("find");
        btnfind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfindActionPerformed(evt);
            }
        });

        tabelUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id_user", "username", "password", "role"
            }
        ));
        tabelUser.setFillsViewportHeight(true);
        jScrollPane2.setViewportView(tabelUser);

        btncl.setBackground(new java.awt.Color(250, 250, 210));
        btncl.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btncl.setText("close");
        btncl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclActionPerformed(evt);
            }
        });

        btndl.setBackground(new java.awt.Color(250, 250, 210));
        btndl.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btndl.setText("delete");
        btndl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnfind, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(btndl, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncl, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcari)
                    .addComponent(btndl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnfind)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncl)
                .addGap(17, 17, 17))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/file1.png"))); // NOI18N
        jMenu1.setText("File Master");

        menuuser.setText("User");
        menuuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuuserActionPerformed(evt);
            }
        });
        jMenu1.add(menuuser);

        menumhs.setText("Mahasiswa");
        menumhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menumhsActionPerformed(evt);
            }
        });
        jMenu1.add(menumhs);

        menumk.setText("Mata Kuliah");
        menumk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menumkActionPerformed(evt);
            }
        });
        jMenu1.add(menumk);

        menudsn.setText("Dosen");
        menudsn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menudsnActionPerformed(evt);
            }
        });
        jMenu1.add(menudsn);

        jMenuBar1.add(jMenu1);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/setting.png"))); // NOI18N
        jMenu3.setText("Setting");

        menuadd.setText("Add User");
        menuadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuaddActionPerformed(evt);
            }
        });
        jMenu3.add(menuadd);

        menupw.setText("Change Password");
        menupw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menupwActionPerformed(evt);
            }
        });
        jMenu3.add(menupw);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuuserActionPerformed
        new user().setVisible(true);
        dispose();
    }//GEN-LAST:event_menuuserActionPerformed

    private void menumhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menumhsActionPerformed
        new mahasiswa().setVisible(true);
        dispose();
    }//GEN-LAST:event_menumhsActionPerformed

    private void menumkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menumkActionPerformed
        new matkul().setVisible(true);
        dispose();
    }//GEN-LAST:event_menumkActionPerformed

    private void menudsnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menudsnActionPerformed
        new dosen().setVisible(true);
        dispose();
    }//GEN-LAST:event_menudsnActionPerformed

    private void menuaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuaddActionPerformed
        new adduser().setVisible(true);
        dispose();
    }//GEN-LAST:event_menuaddActionPerformed

    private void menupwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menupwActionPerformed
        new changeadmin().setVisible(true);
        dispose();
    }//GEN-LAST:event_menupwActionPerformed

    private void btndlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndlActionPerformed
        hapusData();
    }//GEN-LAST:event_btndlActionPerformed

    private void btnclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclActionPerformed
        new menuadmin().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnclActionPerformed

    private void btnfindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfindActionPerformed
        String cari = txtcari.getText().trim();

        if (cari.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan isi kolom cari terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            txtcari.requestFocus();
            return;
        }

        tampilkanData(cari);
        if (tabelUser.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            txtcari.setText("");
            txtcari.requestFocus();
        }
    }//GEN-LAST:event_btnfindActionPerformed

    /* public static void main(String args[]) {
       

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user().setVisible(true);
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncl;
    private javax.swing.JButton btndl;
    private javax.swing.JButton btnfind;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuItem menuadd;
    private javax.swing.JMenuItem menudsn;
    private javax.swing.JMenuItem menumhs;
    private javax.swing.JMenuItem menumk;
    private javax.swing.JMenuItem menupw;
    private javax.swing.JMenuItem menuuser;
    private javax.swing.JTable tabelUser;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
