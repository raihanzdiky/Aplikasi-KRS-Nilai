package soal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dosen extends javax.swing.JFrame {

    DefaultTableModel model;

    public dosen() {
        initComponents();

        String[] judul = {"NIDN", "Nama Dosen", "Jabatan", "Jenis Kelamin"};
        model = new DefaultTableModel(judul, 0);
        tabelDosen.setModel(model);

        setLocationRelativeTo(null);
        pencarianRealTime();
    }

    private String getKodeDosen() {
        String kode = "";
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "SELECT CONCAT('DSN', YEAR(CURDATE()), LPAD(IFNULL(MAX(RIGHT(nidn, 3)), 0) + 1, 3, '0')) AS kode_dosen "
                    + "FROM tb_dosen WHERE nidn LIKE CONCAT('DSN', YEAR(CURDATE()), '%')";
            ResultSet rs = cn.createStatement().executeQuery(sql);
            if (rs.next()) {
                kode = rs.getString("kode_dosen");
            }
        } catch (SQLException e) {
            System.out.println("Error Auto NIDN: " + e.getMessage());
        }
        return kode;
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
        tfdsn.setText("");
        cbj.setSelectedIndex(0);
        tfdsn.requestFocus();
    }

    private void tampilkanData(String cari) {
        model.setRowCount(0);
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "SELECT * FROM tb_dosen WHERE nama_dosen LIKE ? OR nidn LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + cari + "%");
            ps.setString(2, "%" + cari + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nidn"),
                    rs.getString("nama_dosen"),
                    rs.getString("jabatan"),
                    rs.getString("jenis_kelamin")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal tampil data: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdsn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbj = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        rl = new javax.swing.JRadioButton();
        rp = new javax.swing.JRadioButton();
        btnfind = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tffind = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelDosen = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();
        btnup = new javax.swing.JButton();
        btndel = new javax.swing.JButton();
        btncl = new javax.swing.JButton();
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
        setTitle("Admin - Dosen");

        jPanel1.setBackground(new java.awt.Color(65, 22, 233));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/dns.png"))); // NOI18N
        jLabel5.setText("Dosen Form");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Nama Dosen");

        jLabel2.setText("Jabatan");

        cbj.setBackground(new java.awt.Color(250, 250, 210));
        cbj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kaprodi", "Wali", "Pengajar" }));
        cbj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjActionPerformed(evt);
            }
        });

        jLabel3.setText("Jenis Kelamin");

        rl.setText("Laki-laki");
        rl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rlActionPerformed(evt);
            }
        });

        rp.setText("Prempuan");
        rp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rpActionPerformed(evt);
            }
        });

        btnfind.setBackground(new java.awt.Color(250, 250, 210));
        btnfind.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnfind.setText("find");
        btnfind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfindActionPerformed(evt);
            }
        });

        jLabel4.setText("Cari");

        tabelDosen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIDN", "Nama Dosen", "Jabatan", "Title 4"
            }
        ));
        tabelDosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDosenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelDosen);

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

        btndel.setBackground(new java.awt.Color(250, 250, 210));
        btndel.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btndel.setText("delete");
        btndel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelActionPerformed(evt);
            }
        });

        btncl.setBackground(new java.awt.Color(250, 250, 210));
        btncl.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btncl.setText("close");
        btncl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfdsn)
                            .addComponent(cbj, 0, 135, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rp))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnup)
                        .addGap(18, 18, 18)
                        .addComponent(btndel)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tffind, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnfind, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncl, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfdsn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tffind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnfind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(rl)
                            .addComponent(rp))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnadd)
                            .addComponent(btnup)
                            .addComponent(btndel)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btncl)
                .addContainerGap(95, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void rpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rpActionPerformed

    }//GEN-LAST:event_rpActionPerformed

    private void btnfindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfindActionPerformed
        String cari = tffind.getText();
        if (cari.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Input nama dosen/nidn pada text field!");
        } else {
            tampilkanData(cari);
            if (tabelDosen.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!");
            } else {
                JOptionPane.showMessageDialog(this, "Data ditemukan!");
            }
        }
    }//GEN-LAST:event_btnfindActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        if (tfdsn.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Dosen belum diisi!");
        } else if (!rl.isSelected() && !rp.isSelected()) {
            JOptionPane.showMessageDialog(this, "Jenis Kelamin belum dipilih!");
        } else {
            try {
                String nidnBaru = getKodeDosen();
                String jk = rl.isSelected() ? "Laki-laki" : "Prempuan";
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                String sql = "INSERT INTO tb_dosen (nidn, nama_dosen, jabatan, jenis_kelamin) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, nidnBaru);
                ps.setString(2, tfdsn.getText());
                ps.setString(3, cbj.getSelectedItem().toString());
                ps.setString(4, jk);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data berhasil disimpan dengan NIDN: " + nidnBaru);
                tampilkanData("");
                bersihForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupActionPerformed
        int row = tabelDosen.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate!");
            return;
        }

        String namaLama = model.getValueAt(row, 1).toString();
        String jabatanLama = model.getValueAt(row, 2).toString();
        String jkLama = model.getValueAt(row, 3).toString();
        String namaBaru = tfdsn.getText();
        String jabatanBaru = cbj.getSelectedItem().toString();
        String jkBaru = rl.isSelected() ? "Laki-laki" : "Prempuan";

        if (namaLama.equals(namaBaru)
                && jabatanLama.equals(jabatanBaru)
                && jkLama.equalsIgnoreCase(jkBaru)) {

            JOptionPane.showMessageDialog(this, "Tidak ada perubahan data! Pilih data untuk diupdate.");
            return;
        }

        int konf = JOptionPane.showConfirmDialog(this, "Yakin update data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konf == JOptionPane.YES_OPTION) {
            try {
                String nidn = model.getValueAt(row, 0).toString();

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                String sql = "UPDATE tb_dosen SET nama_dosen=?, jabatan=?, jenis_kelamin=? WHERE nidn=?";
                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setString(1, namaBaru);
                ps.setString(2, jabatanBaru);
                ps.setString(3, jkBaru);
                ps.setString(4, nidn);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
                tampilkanData("");
                bersihForm();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnupActionPerformed

    private void btndelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelActionPerformed
        int row = tabelDosen.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
        } else {
            int konf = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapusnya?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (konf == JOptionPane.YES_OPTION) {
                try {
                    String nidn = model.getValueAt(row, 0).toString();
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                    cn.createStatement().executeUpdate("DELETE FROM tb_dosen WHERE nidn='" + nidn + "'");
                    tampilkanData("");
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                    bersihForm();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btndelActionPerformed

    private void btnclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclActionPerformed
        new menuadmin().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnclActionPerformed

    private void tabelDosenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDosenMouseClicked
        int row = tabelDosen.getSelectedRow();
        tfdsn.setText(model.getValueAt(row, 1).toString());
        cbj.setSelectedItem(model.getValueAt(row, 2).toString());

        String jk = model.getValueAt(row, 3).toString();
        if (jk.equalsIgnoreCase("Laki-laki")) {
            rl.setSelected(true);
        } else {
            rp.setSelected(true);
        }
    }//GEN-LAST:event_tabelDosenMouseClicked

    private void cbjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjActionPerformed
       
    }//GEN-LAST:event_cbjActionPerformed

    private void rlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rlActionPerformed
      
    }//GEN-LAST:event_rlActionPerformed

  /*  public static void main(String args[]) {
       
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dosen().setVisible(true);
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btncl;
    private javax.swing.JButton btndel;
    private javax.swing.JButton btnfind;
    private javax.swing.JButton btnup;
    private javax.swing.JComboBox<String> cbj;
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
    private javax.swing.JRadioButton rl;
    private javax.swing.JRadioButton rp;
    private javax.swing.JTable tabelDosen;
    private javax.swing.JTextField tfdsn;
    private javax.swing.JTextField tffind;
    // End of variables declaration//GEN-END:variables
}
