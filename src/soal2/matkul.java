package soal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class matkul extends javax.swing.JFrame {

    DefaultTableModel model;

    public matkul() {
    initComponents();

        tabelMatkul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMatkulMouseClicked(evt);
            }
        });

        String[] judul = {"Kode MatKul", "Nama Matkul", "Jumlah SKS", "Semester"};
        model = new DefaultTableModel(judul, 0);
        tabelMatkul.setModel(model);

        setLocationRelativeTo(null);
        pencarianRealTime();
    }

    private String getKodeBaru() {
        String kode = "";
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "SELECT CONCAT('MK', YEAR(CURDATE()), LPAD(IFNULL(MAX(RIGHT(id_matkul, 3)), 0) + 1, 3, '0')) AS kode_baru "
                    + "FROM tb_matakuliah WHERE id_matkul LIKE CONCAT('MK', YEAR(CURDATE()), '%')";
            ResultSet rs = cn.createStatement().executeQuery(sql);
            if (rs.next()) {
                kode = rs.getString("kode_baru");
            }
        } catch (SQLException e) {
            System.out.println("Error Auto Number: " + e.getMessage());
        }
        return kode;
    }

    private void tampilkanData(String cari) {
        model.setRowCount(0);
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "SELECT * FROM tb_matakuliah WHERE nama_matkul LIKE ? OR id_matkul LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + cari + "%");
            ps.setString(2, "%" + cari + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_matkul"),
                    rs.getString("nama_matkul"),
                    rs.getString("jumlah_sks"),
                    rs.getString("semester")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal tampil data: " + ex.getMessage());
        }
    }

    private void pencarianRealTime() {
        tffind.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                tampilkanData(tffind.getText());
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                tampilkanData(tffind.getText());
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                tampilkanData(tffind.getText());
            }
        });
    }

    private void bersihForm() {
        tfmk.setText("");
        tfsks.setText("");
        cbs.setSelectedIndex(0);
        tfmk.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btncl = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfmk = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfsks = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbs = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMatkul = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tffind = new javax.swing.JTextField();
        btnfind = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnup = new javax.swing.JButton();
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
        setTitle("Admin - MataKuliah");

        jPanel1.setBackground(new java.awt.Color(65, 22, 233));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/bb.png"))); // NOI18N
        jLabel5.setText("Mata Kuliah Form");

        btncl.setBackground(new java.awt.Color(250, 250, 210));
        btncl.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btncl.setText("close");
        btncl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncl, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btncl))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Nama Mata Kuliah");

        tfmk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfmkActionPerformed(evt);
            }
        });

        jLabel3.setText("Jumlah SKS");

        jLabel4.setText("Semester");

        cbs.setBackground(new java.awt.Color(250, 250, 210));
        cbs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
        cbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsActionPerformed(evt);
            }
        });

        tabelMatkul.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Mata Kuliah", "Nama Mata Kuliah", "Jumlah SKS", "Semester"
            }
        ));
        tabelMatkul.setFillsViewportHeight(true);
        tabelMatkul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMatkulMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelMatkul);

        jLabel1.setText("Cari");

        btnfind.setBackground(new java.awt.Color(250, 250, 210));
        btnfind.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnfind.setText("find");
        btnfind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfindActionPerformed(evt);
            }
        });

        btnadd.setBackground(new java.awt.Color(250, 250, 210));
        btnadd.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnadd.setText("add");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnup.setBackground(new java.awt.Color(250, 250, 210));
        btnup.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnup.setText("update");
        btnup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupActionPerformed(evt);
            }
        });

        btndl.setBackground(new java.awt.Color(250, 250, 210));
        btndl.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
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
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfmk)
                            .addComponent(tfsks)
                            .addComponent(cbs, 0, 127, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnup)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(tffind, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnfind, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btndl, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tffind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnfind)
                    .addComponent(btndl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfsks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnadd)
                            .addComponent(btnup))
                        .addContainerGap(150, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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

    private void tfmkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfmkActionPerformed

    }//GEN-LAST:event_tfmkActionPerformed

    private void cbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsActionPerformed

    }//GEN-LAST:event_cbsActionPerformed

    private void btnfindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfindActionPerformed
        String cari = tffind.getText();
        if (cari.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Input nama matkul/id_matkul pada text field!");
        } else {
            tampilkanData(cari);
            if (tabelMatkul.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!");
            } else {
                JOptionPane.showMessageDialog(this, "Data ditemukan!");
            }
        }
    }//GEN-LAST:event_btnfindActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        String namaMatkul = tfmk.getText().trim();
        String sks = tfsks.getText().trim();

        if (namaMatkul.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Mata Kuliah belum diisi!");
        } else if (sks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jumlah SKS belum diisi!");
        } else {
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                String sqlCek = "SELECT COUNT(*) FROM tb_matakuliah WHERE nama_matkul = ?";
                PreparedStatement psCek = cn.prepareStatement(sqlCek);
                psCek.setString(1, namaMatkul);
                ResultSet rs = psCek.executeQuery();

                rs.next();
                if (rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Mata Kuliah '" + namaMatkul + "' sudah ada!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {

                    String idBaru = getKodeBaru();
                    String sql = "INSERT INTO tb_matakuliah (id_matkul, nama_matkul, jumlah_sks, semester) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = cn.prepareStatement(sql);
                    ps.setString(1, idBaru);
                    ps.setString(2, namaMatkul);
                    ps.setString(3, sks);
                    ps.setString(4, cbs.getSelectedItem().toString());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan dengan Kode: " + idBaru);
                   
                    tampilkanData(""); 
                    bersihForm();      
                }

                cn.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btndlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndlActionPerformed
        int row = tabelMatkul.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
        } else {
            int konf = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapusnya?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (konf == JOptionPane.YES_OPTION) {
                try {
                    String id = model.getValueAt(row, 0).toString();
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                    cn.createStatement().executeUpdate("DELETE FROM tb_matakuliah WHERE id_matkul='" + id + "'");
                    tampilkanData("");
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                   
                    bersihForm();
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btndlActionPerformed

    private void btnclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclActionPerformed
        new menuadmin().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnclActionPerformed

    private void btnupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupActionPerformed
        int row = tabelMatkul.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate!");
            return;
        }

        String namaLama = model.getValueAt(row, 1).toString();
        String sksLama = model.getValueAt(row, 2).toString();
        String semesterLama = model.getValueAt(row, 3).toString();
        String namaBaru = tfmk.getText();
        String sksBaru = tfsks.getText();
        String semesterBaru = cbs.getSelectedItem().toString();

        if (namaLama.equals(namaBaru)
                && sksLama.equals(sksBaru)
                && semesterLama.equals(semesterBaru)) {

            JOptionPane.showMessageDialog(this, "Tidak ada perubahan data! Pilih data untuk diupdate.");
            return;
        }

        int konf = JOptionPane.showConfirmDialog(this, "Yakin update data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (konf == JOptionPane.YES_OPTION) {
            try {
                String id = model.getValueAt(row, 0).toString();

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                String sql = "UPDATE tb_matakuliah SET nama_matkul=?, jumlah_sks=?, semester=? WHERE id_matkul=?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, namaBaru);
                ps.setString(2, sksBaru);
                ps.setString(3, semesterBaru);
                ps.setString(4, id);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
               
                tampilkanData("");
                bersihForm();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnupActionPerformed

    private void tabelMatkulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMatkulMouseClicked
        int row = tabelMatkul.getSelectedRow();
        tfmk.setText(model.getValueAt(row, 1).toString());
        tfsks.setText(model.getValueAt(row, 2).toString());
        cbs.setSelectedItem(model.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tabelMatkulMouseClicked

   /* public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(matkul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(matkul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(matkul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(matkul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new matkul().setVisible(true);
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btncl;
    private javax.swing.JButton btndl;
    private javax.swing.JButton btnfind;
    private javax.swing.JButton btnup;
    private javax.swing.JComboBox<String> cbs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuadd;
    private javax.swing.JMenuItem menudsn;
    private javax.swing.JMenuItem menumhs;
    private javax.swing.JMenuItem menumk;
    private javax.swing.JMenuItem menupw;
    private javax.swing.JMenuItem menuuser;
    private javax.swing.JTable tabelMatkul;
    private javax.swing.JTextField tffind;
    private javax.swing.JTextField tfmk;
    private javax.swing.JTextField tfsks;
    // End of variables declaration//GEN-END:variables
}
