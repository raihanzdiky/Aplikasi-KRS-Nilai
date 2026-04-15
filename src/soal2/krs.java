package soal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class krs extends javax.swing.JFrame {

    HashMap<String, String> mapMhs = new HashMap<>();
    HashMap<String, String> mapMk = new HashMap<>();
    HashMap<String, String> mapDosen = new HashMap<>();

    public krs() {
        initComponents();
        setLocationRelativeTo(null);
        loadComboBox();
        tampilDataKRS();
        tabelKrs.getColumnModel().getColumn(0).setMinWidth(0);
        tabelKrs.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelKrs.getColumnModel().getColumn(0).setWidth(0);

    }

    private void loadComboBox() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");

            // 1. Load Mahasiswa (Menampilkan NIM - Nama, Menyimpan IDM)
            ResultSet rsMhs = cn.createStatement().executeQuery("SELECT idm, nim, nama_mahasiswa FROM tb_mahasiswa");
            cbNim.removeAllItems();
            mapMhs.clear();
            while (rsMhs.next()) {
                String id = rsMhs.getString("idm");
                String tampil = rsMhs.getString("nim") + " - " + rsMhs.getString("nama_mahasiswa");
                cbNim.addItem(tampil);
                mapMhs.put(tampil, id);
            }

            // 2. Load Mata Kuliah (Menampilkan IDMK - Nama, Menyimpan IDMK)
            // (Asumsi kolom kode matkul di database adalah 'id_matkul' atau 'kode_mk')
            ResultSet rsMk = cn.createStatement().executeQuery("SELECT idmk, id_matkul, nama_matkul FROM tb_matakuliah");
            cbMk.removeAllItems();
            mapMk.clear();
            while (rsMk.next()) {
                String id = rsMk.getString("idmk");
                String tampil = rsMk.getString("id_matkul") + " - " + rsMk.getString("nama_matkul");
                cbMk.addItem(tampil);
                mapMk.put(tampil, id);
            }

            // 3. Load Dosen (Menampilkan NIDN - Nama, Menyimpan IDS)
            ResultSet rsDosen = cn.createStatement().executeQuery("SELECT ids, nidn, nama_dosen FROM tb_dosen");
            cbNidn.removeAllItems();
            mapDosen.clear();
            while (rsDosen.next()) {
                String id = rsDosen.getString("ids");
                String tampil = rsDosen.getString("nidn") + " - " + rsDosen.getString("nama_dosen");
                cbNidn.addItem(tampil);
                mapDosen.put(tampil, id);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Load Combo: " + e.getMessage());
        }
    }

    private void clearFormKRS() {
        cbNim.setSelectedIndex(0);
        cbMk.setSelectedIndex(0);
        cbNidn.setSelectedIndex(0);
        txtTanggal.setDate(null);
        buttonGroup1.clearSelection(); // Membersihkan pilihan RadioButton
    }

    private void tampilDataKRS() {
        DefaultTableModel modelKrs = new DefaultTableModel();
        modelKrs.addColumn("ID KRS");
        modelKrs.addColumn("NIM");        
        modelKrs.addColumn("Nama Mhs");   
        modelKrs.addColumn("Mata Kuliah"); 
        modelKrs.addColumn("Dosen");       
        modelKrs.addColumn("Tanggal");     
        modelKrs.addColumn("Status");

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "SELECT k.id_krs, m.nim, m.nama_mahasiswa, mk.nama_matkul, d.nama_dosen, k.tanggal_isi, k.status "
                    + "FROM tb_krs k "
                    + "JOIN tb_mahasiswa m ON k.idm = m.idm "
                    + "JOIN tb_matakuliah mk ON k.idmk = mk.idmk "
                    + "JOIN tb_dosen d ON k.ids = d.ids";

            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelKrs.addRow(new Object[]{
                    rs.getString("id_krs"),
                    rs.getString("nim"),
                    rs.getString("nama_mahasiswa"),
                    rs.getString("nama_matkul"),
                    rs.getString("nama_dosen"),
                    rs.getString("tanggal_isi"),
                    rs.getString("status")
                });
            }

            tabelKrs.setModel(modelKrs);
            tabelKrs.getColumnModel().getColumn(0).setMinWidth(0);
            tabelKrs.getColumnModel().getColumn(0).setMaxWidth(0);
            tabelKrs.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil tabel: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbNim = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbMk = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbNidn = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        rbDiterima = new javax.swing.JRadioButton();
        rbDitolak = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKrs = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Operator - KRS");

        jPanel1.setBackground(new java.awt.Color(65, 22, 233));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cl.png"))); // NOI18N
        jLabel6.setText("KRS Form");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        cbNim.setBackground(new java.awt.Color(250, 250, 210));
        cbNim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel1.setText("NIM");

        cbMk.setBackground(new java.awt.Color(250, 250, 210));
        cbMk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel2.setText("Kode Matkul");

        cbNidn.setBackground(new java.awt.Color(250, 250, 210));
        cbNidn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNidn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNidnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel3.setText("NIDN");

        rbDiterima.setText("DiTerima");

        rbDitolak.setText("DiTolak");

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setText("Status");

        tabelKrs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "nim", "nama mahasiswa", "mata kuliah", "dosen", "tanggal", "status", "Krs"
            }
        ));
        tabelKrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKrsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKrs);

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel4.setText("tanggal");

        jButton2.setBackground(new java.awt.Color(250, 250, 210));
        jButton2.setText("add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(250, 250, 210));
        jButton3.setText("delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(250, 250, 210));
        jButton4.setText("close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(rbDiterima)
                        .addGap(18, 18, 18)
                        .addComponent(rbDitolak)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cbNim, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbMk, 0, 120, Short.MAX_VALUE)
                    .addComponent(cbNidn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addComponent(jLabel4)
                .addGap(44, 44, 44)
                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(cbMk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDiterima)
                    .addComponent(rbDitolak)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(cbNidn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(127, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3)
                            .addComponent(jButton2))
                        .addGap(75, 75, 75))))
        );

        jMenuBar1.setToolTipText("");

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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String tampilMhs = cbNim.getSelectedItem().toString();
            String tampilMk = cbMk.getSelectedItem().toString();
            String tampilDsn = cbNidn.getSelectedItem().toString();
            String idm = mapMhs.get(tampilMhs);
            String idmk = mapMk.get(tampilMk);
            String ids = mapDosen.get(tampilDsn);

            java.util.Date tgl = txtTanggal.getDate();
            String status = rbDiterima.isSelected() ? "DiTerima" : (rbDitolak.isSelected() ? "DiTolak" : "");

            if (tgl == null || status.equals("")) {
                JOptionPane.showMessageDialog(null, "Tanggal dan Status harus diisi!");
                return;
            }

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String formatTgl = sdf.format(tgl);

            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sqlCek = "SELECT COUNT(*) FROM tb_krs WHERE idm = ? AND idmk = ?";
            PreparedStatement psCek = cn.prepareStatement(sqlCek);
            psCek.setString(1, idm);
            psCek.setString(2, idmk);
            ResultSet rsCek = psCek.executeQuery();

            rsCek.next();
            if (rsCek.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this,
                        "Gagal! Mahasiswa tersebut sudah mengambil mata kuliah ini.",
                        "Data Duplikat", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            int yakin = JOptionPane.showConfirmDialog(null, "Simpan data KRS ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (yakin == JOptionPane.YES_OPTION) {
                String sqlAdd = "INSERT INTO tb_krs (idm, idmk, ids, tanggal_isi, status) VALUES (?,?,?,?,?)";
                PreparedStatement psAdd = cn.prepareStatement(sqlAdd);
                psAdd.setString(1, idm);
                psAdd.setString(2, idmk);
                psAdd.setString(3, ids);
                psAdd.setString(4, formatTgl);
                psAdd.setString(5, status);

                psAdd.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan!");
                tampilDataKRS();
                clearFormKRS();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = tabelKrs.getSelectedRow(); 
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel yang ingin dihapus!");
            return;
        }

        int konf = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data KRS ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (konf == JOptionPane.YES_OPTION) {
            try {

                String idKrs = tabelKrs.getValueAt(row, 0).toString();
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
                String sql = "DELETE FROM tb_krs WHERE id_krs = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, idKrs);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data KRS berhasil dihapus!");
                tampilDataKRS();
                clearFormKRS();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabelKrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKrsMouseClicked
        int row = tabelKrs.getSelectedRow();
        if (row != -1) {
            
            String nimMhs = tabelKrs.getValueAt(row, 1).toString();
            String namaMhs = tabelKrs.getValueAt(row, 2).toString();
            cbNim.setSelectedItem(nimMhs + " - " + namaMhs);
            String matkul = tabelKrs.getValueAt(row, 3).toString();
            for (int i = 0; i < cbMk.getItemCount(); i++) {
                if (cbMk.getItemAt(i).toString().contains(matkul)) {
                    cbMk.setSelectedIndex(i);
                    break;
                }
            }

            String status = tabelKrs.getValueAt(row, 6).toString();
            if (status.equalsIgnoreCase("DiTerima")) {
                rbDiterima.setSelected(true);
            } else {
                rbDitolak.setSelected(true);
            }

            try {
                String sTgl = tabelKrs.getValueAt(row, 5).toString();
                java.util.Date tgl = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(sTgl);
                txtTanggal.setDate(tgl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tabelKrsMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new menuoperator().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbNidnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNidnActionPerformed

    }//GEN-LAST:event_cbNidnActionPerformed


   /* public static void main(String args[]) {


        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(krs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(krs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(krs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(krs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }



        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new krs().setVisible(true);
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbMk;
    private javax.swing.JComboBox<String> cbNidn;
    private javax.swing.JComboBox<String> cbNim;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbDiterima;
    private javax.swing.JRadioButton rbDitolak;
    private javax.swing.JTable tabelKrs;
    private com.toedter.calendar.JDateChooser txtTanggal;
    // End of variables declaration//GEN-END:variables
}
