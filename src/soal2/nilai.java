package soal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class nilai extends javax.swing.JFrame {

    public nilai() {
        initComponents();
        setLocationRelativeTo(null);
        loadMatkul();
        tampilData();
    }

    private String getGrade(double nilai) {
        if (nilai >= 85) {
            return "A";
        } else if (nilai >= 76) {
            return "B";
        } else if (nilai >= 66) {
            return "C";
        } else if (nilai >= 51) {
            return "D";
        } else {
            return "E";
        }
    }

    private String getStatus(String grade) {
        if (grade.equals("D") || grade.equals("E")) {
            return "Tidak Lulus";
        } else {
            return "Lulus";
        }
    }

    private void loadMatkul() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "SELECT id_matkul, nama_matkul FROM tb_matakuliah";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            cbm.removeAllItems();

            while (rs.next()) {
                cbm.addItem(rs.getString("nama_matkul"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void cariData(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tbn.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tbn.setRowSorter(sorter);

        sorter.setRowFilter(RowFilter.regexFilter(keyword));
    }

    private void tampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NIM");
        model.addColumn("Nama Mahasiswa");
        model.addColumn("Mata Kuliah");
        model.addColumn("Nilai");
        model.addColumn("Grade");
        model.addColumn("Status");

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "SELECT n.idn, m.nim, m.nama_mahasiswa, mk.nama_matkul, n.nakhir, n.grade, n.status_akhir "
                    + "FROM tb_nilai n "
                    + "JOIN tb_mahasiswa m ON n.idm = m.idm " 
                    + "JOIN tb_matakuliah mk ON n.idmk = mk.idmk";

            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("idn"),
                    rs.getString("nim"),
                    rs.getString("nama_mahasiswa"),
                    rs.getString("nama_matkul"),
                    rs.getDouble("nakhir"),
                    rs.getString("grade"),
                    rs.getString("status_akhir")
                });
            }

            tbn.setModel(model);
            tbn.getColumnModel().getColumn(0).setMinWidth(0);
            tbn.getColumnModel().getColumn(0).setMaxWidth(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void clearInput() {
        txtNim.setText("");
        txtNilai.setText("");
        cbm.setSelectedIndex(0);
        txtNim.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbm = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNilai = new javax.swing.JTextField();
        btncl = new javax.swing.JButton();
        btnfind = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbn = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();
        btndl = new javax.swing.JButton();
        btnup = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Operator - Nilai");

        jPanel1.setBackground(new java.awt.Color(65, 22, 233));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/nilai.png"))); // NOI18N
        jLabel4.setText("Form Nilai");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("NIM");

        txtNim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNimActionPerformed(evt);
            }
        });

        jLabel2.setText("Mata Kuliah");

        cbm.setBackground(new java.awt.Color(250, 250, 210));
        cbm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel3.setText("Nilai Akhir");

        btncl.setBackground(new java.awt.Color(250, 250, 210));
        btncl.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btncl.setText("close");
        btncl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclActionPerformed(evt);
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

        tbn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NIM", "Nama Mahasiswa", "Mata Kuliah", "Nilai", "Grade", "Status"
            }
        ));
        tbn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbn);

        btnadd.setBackground(new java.awt.Color(250, 250, 210));
        btnadd.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnadd.setText("add");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
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

        btnup.setBackground(new java.awt.Color(250, 250, 210));
        btnup.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnup.setText("update");
        btnup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnfind, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(26, 26, 26)
                                .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cbm, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtNilai, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncl, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(btndl, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(btnup, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncl)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(btnfind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btndl)
                    .addComponent(btnup))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/tr.png"))); // NOI18N
        jMenu1.setText("transaction");

        jMenuItem2.setText("KRS");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Nilai");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/setting.png"))); // NOI18N
        jMenu2.setText("setting");

        jMenuItem1.setText("Change Password");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new krs().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new nilai().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new changeop().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtNimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNimActionPerformed

    }//GEN-LAST:event_txtNimActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        try {
            String nim = txtNim.getText();
            String namaMatkul = cbm.getSelectedItem().toString();
            String nilaiStr = txtNilai.getText();

            // (Validasi input kosong tetap sama...)
            double nilaiVal = Double.parseDouble(nilaiStr);
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");

            // 1. Ambil IDM berdasarkan NIM (karena input user adalah NIM)
            String sqlMhs = "SELECT idm FROM tb_mahasiswa WHERE nim=?";
            PreparedStatement psMhs = cn.prepareStatement(sqlMhs);
            psMhs.setString(1, nim);
            ResultSet rsMhs = psMhs.executeQuery();

            if (!rsMhs.next()) {
                JOptionPane.showMessageDialog(null, "NIM tidak terdaftar!");
                return;
            }
            String idm = rsMhs.getString("idm");

            // 2. Ambil IDMK berdasarkan Nama Matkul
            String sqlMk = "SELECT idmk FROM tb_matakuliah WHERE nama_matkul=?";
            PreparedStatement psMk = cn.prepareStatement(sqlMk);
            psMk.setString(1, namaMatkul);
            ResultSet rsMk = psMk.executeQuery();

            String idmk = "";
            if (rsMk.next()) {
                idmk = rsMk.getString("idmk");
            }

            // 3. Cek Duplikat Nilai (Gunakan idm dan idmk)
            String sqlCek = "SELECT * FROM tb_nilai WHERE idm=? AND idmk=?";
            PreparedStatement psCek = cn.prepareStatement(sqlCek);
            psCek.setString(1, idm);
            psCek.setString(2, idmk);
            if (psCek.executeQuery().next()) {
                JOptionPane.showMessageDialog(null, "Nilai untuk mata kuliah ini sudah ada!");
                return;
            }

            String grade = getGrade(nilaiVal);
            String status = getStatus(grade);
            String sqlIn = "INSERT INTO tb_nilai(idm, idmk, nakhir, grade, status_akhir) VALUES (?,?,?,?,?)";
            PreparedStatement psIn = cn.prepareStatement(sqlIn);
            psIn.setString(1, idm);
            psIn.setString(2, idmk);
            psIn.setDouble(3, nilaiVal);
            psIn.setString(4, grade);
            psIn.setString(5, status);
            psIn.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            tampilData();
            clearInput();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Simpan: " + e.getMessage());
        }

    }//GEN-LAST:event_btnaddActionPerformed

    private void btndlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndlActionPerformed
        int row = tbn.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Apakah anda yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String id = tbn.getValueAt(row, 0).toString();

                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                String sql = "DELETE FROM tb_nilai WHERE idn=?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, id);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                tampilData();
                loadMatkul();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_btndlActionPerformed

    private void btnupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupActionPerformed
        int row = tbn.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data di tabel!");
            return;
        }

        try {
            String idn = tbn.getValueAt(row, 0).toString(); 
            String nimBaru = txtNim.getText();
            String matkulBaru = cbm.getSelectedItem().toString();
            double nilaiBaru = Double.parseDouble(txtNilai.getText());

            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sqlMhs = "SELECT idm FROM tb_mahasiswa WHERE nim=?";
            PreparedStatement psMhs = cn.prepareStatement(sqlMhs);
            psMhs.setString(1, nimBaru);
            ResultSet rsMhs = psMhs.executeQuery();
            if (!rsMhs.next()) {
                JOptionPane.showMessageDialog(null, "NIM tidak ditemukan!");
                return;
            }
            String idm = rsMhs.getString("idm");
            String sqlMk = "SELECT idmk FROM tb_matakuliah WHERE nama_matkul=?";
            PreparedStatement psMk = cn.prepareStatement(sqlMk);
            psMk.setString(1, matkulBaru);
            ResultSet rsMk = psMk.executeQuery();
            if (!rsMk.next()) {
                return;
            }
            String idmk = rsMk.getString("idmk");
            String grade = getGrade(nilaiBaru);
            String status = getStatus(grade);

            String sqlUpdate = "UPDATE tb_nilai SET idm=?, idmk=?, nakhir=?, grade=?, status_akhir=? WHERE idn=?";
            PreparedStatement psUp = cn.prepareStatement(sqlUpdate);
            psUp.setString(1, idm);
            psUp.setString(2, idmk);
            psUp.setDouble(3, nilaiBaru);
            psUp.setString(4, grade);
            psUp.setString(5, status);
            psUp.setString(6, idn);

            psUp.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");

            tampilData(); 
            clearInput();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Update: " + e.getMessage());
        }
    }//GEN-LAST:event_btnupActionPerformed

    private void btnfindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfindActionPerformed
        String keyword = txtNim.getText().trim();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Masukkan NIM untuk mencari!");
            tampilData(); 
        } else {
            cariData(keyword);
        }
    }//GEN-LAST:event_btnfindActionPerformed

    private void tbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnMouseClicked
        int row = tbn.getSelectedRow();

        txtNim.setText(tbn.getValueAt(row, 1).toString());
        String matkul = tbn.getValueAt(row, 3).toString();
        cbm.setSelectedItem(matkul);
        txtNilai.setText(tbn.getValueAt(row, 4).toString());       
    }//GEN-LAST:event_tbnMouseClicked

    private void btnclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclActionPerformed
        new menuoperator().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnclActionPerformed

     public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nilai().setVisible(true);
            }
        });
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btncl;
    private javax.swing.JButton btndl;
    private javax.swing.JButton btnfind;
    private javax.swing.JButton btnup;
    private javax.swing.JComboBox<String> cbm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbn;
    private javax.swing.JTextField txtNilai;
    private javax.swing.JTextField txtNim;
    // End of variables declaration//GEN-END:variables
}
