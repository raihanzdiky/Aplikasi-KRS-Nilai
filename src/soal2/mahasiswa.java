package soal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class mahasiswa extends javax.swing.JFrame {

    String idTerpilih;

    public mahasiswa() {
        initComponents();
        tabelMhs.getColumnModel().getColumn(0).setMinWidth(0);
        tabelMhs.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelMhs.getColumnModel().getColumn(0).setWidth(0);
        setLocationRelativeTo(null);
        tabelMhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMhsMouseClicked(evt);
            }
        });
        txtCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                tampilkanData(txtCari.getText());
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                tampilkanData(txtCari.getText());
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMhsMouseClicked(evt);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                tampilkanData(txtCari.getText());
            }
        });
    }

    private void tampilkanData(String cari) {
        DefaultTableModel model = (DefaultTableModel) tabelMhs.getModel();
        model.setRowCount(0);
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "SELECT * FROM tb_mahasiswa WHERE nama_mahasiswa LIKE ? OR nim LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + cari + "%");
            ps.setString(2, "%" + cari + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("idm"),
                    rs.getString("nim"),
                    rs.getString("nama_mahasiswa"),
                    rs.getString("angkatan"),
                    rs.getString("departemen"),
                    rs.getString("jenis_kelamin")
                });
            }
        } catch (Exception e) {
            System.out.println("Error saat mencari: " + e.getMessage());
        }
    }

    private void tambahData() {
        String jk = rl.isSelected() ? "laki-laki" : "prempuan";

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sqlCheck = "SELECT COUNT(*) FROM tb_mahasiswa WHERE nim = ?";
            PreparedStatement psCheck = cn.prepareStatement(sqlCheck);
            psCheck.setString(1, txtnim.getText());
            ResultSet rs = psCheck.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "NIM " + txtnim.getText() + " sudah terdaftar!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            String sql = "INSERT INTO tb_mahasiswa (nim, nama_mahasiswa, angkatan, departemen, jenis_kelamin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, txtnim.getText());
            ps.setString(2, txtnama.getText());
            ps.setString(3, txta.getText());
            ps.setString(4, cbd.getSelectedItem().toString());
            ps.setString(5, jk);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambah!");
           
            tampilkanData("");
            clearForm();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal: " + e.getMessage());
        }
    }

    private void editData() {
        String jk = rl.isSelected() ? "laki-laki" : "prempuan";
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "UPDATE tb_mahasiswa SET nim=?, nama_mahasiswa=?, angkatan=?, departemen=?, jenis_kelamin=? WHERE idm=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, txtnim.getText());    
            ps.setString(2, txtnama.getText());   
            ps.setString(3, txta.getText());      
            ps.setString(4, cbd.getSelectedItem().toString()); 
            ps.setString(5, jk);                  
            ps.setString(6, idTerpilih);          
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui!");
           
            tampilkanData(""); 
            clearForm();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Gagal: " + e.getMessage());
        }
    }

    private void hapusData() {
        if (idTerpilih == null) {
            return;
        }
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
            String sql = "DELETE FROM tb_mahasiswa WHERE idm=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, idTerpilih);
            ps.executeUpdate();
            
            tampilkanData("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hapus Gagal: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtnim.setText("");
        txtnama.setText("");
        txta.setText("");
        cbd.setSelectedIndex(0);
        rl.setSelected(false);
        rp.setSelected(false);
        txtnim.requestFocus();
    }

    private void tabelMhsMouseClicked(java.awt.event.MouseEvent evt) {

        int row = tabelMhs.getSelectedRow();
        if (row != -1) {
 
            idTerpilih = tabelMhs.getValueAt(row, 0).toString();
            txtnim.setText(tabelMhs.getValueAt(row, 1).toString());    
            txtnama.setText(tabelMhs.getValueAt(row, 2).toString());   
            txta.setText(tabelMhs.getValueAt(row, 3).toString());       
            cbd.setSelectedItem(tabelMhs.getValueAt(row, 4).toString());
            String jk = tabelMhs.getValueAt(row, 5).toString();
            if (jk.equals("laki-laki")) {
                rl.setSelected(true);
            } else {
                rp.setSelected(true);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnfind = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtnama = new javax.swing.JTextField();
        txta = new javax.swing.JTextField();
        cbd = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnim = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rl = new javax.swing.JRadioButton();
        rp = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMhs = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();
        btndel = new javax.swing.JButton();
        btnup = new javax.swing.JButton();
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

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin - Mahasiswa");

        jPanel1.setBackground(new java.awt.Color(65, 22, 233));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/mhs.png"))); // NOI18N
        jLabel8.setText("Mahasiswa Form");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/src.png"))); // NOI18N
        jLabel7.setText("Cari ");

        btnfind.setBackground(new java.awt.Color(250, 250, 210));
        btnfind.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnfind.setText("find");
        btnfind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnfind, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnfind, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        cbd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TIF", "TI", "DKV", "MR", "BD" }));

        jLabel2.setText("NIM");

        jLabel3.setText("Nama Lengkap ");

        jLabel4.setText("Angkatan");

        jLabel6.setText("Departement");

        txtnim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnimActionPerformed(evt);
            }
        });

        jLabel5.setText("Jenis Kelamin");

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

        tabelMhs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "", null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "idm", "NIM", "Nama Mahasiswa", "Angkatan", "Departement", "Jenis Kelamin"
            }
        ));
        jScrollPane1.setViewportView(tabelMhs);

        btnadd.setBackground(new java.awt.Color(250, 250, 210));
        btnadd.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnadd.setText("add");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
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

        btnup.setBackground(new java.awt.Color(250, 250, 210));
        btnup.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnup.setText("Update");
        btnup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupActionPerformed(evt);
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
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnup)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(rl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rp)
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnim, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txta, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btndel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btncl)
                                .addGap(69, 69, 69))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtnim, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(cbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(rp)
                    .addComponent(rl))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnadd)
                            .addComponent(btnup)
                            .addComponent(btndel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btncl)
                        .addGap(56, 56, 56))))
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
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel1))
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

    private void rlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rlActionPerformed

    }//GEN-LAST:event_rlActionPerformed

    private void rpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rpActionPerformed

    }//GEN-LAST:event_rpActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        if (txtnim.getText().isEmpty() || txtnama.getText().isEmpty() || txta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!rl.isSelected() && !rp.isSelected()) {
            JOptionPane.showMessageDialog(this, "Pilih Jenis Kelamin!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Long.parseLong(txtnim.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "NIM harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tambahData();
        clearForm();
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnfindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfindActionPerformed
        String cari = txtCari.getText().trim();
        if (cari.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan NIM atau Nama untuk mencari!", "Peringatan", JOptionPane.INFORMATION_MESSAGE);
        }
        if (tabelMhs.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Nama/Nim : '" + cari + "' tidak ditemukan.",
                    "Hasil Kosong", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnfindActionPerformed

    private void btndelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelActionPerformed
        int row = tabelMhs.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            hapusData();
            clearForm();
        }
    }//GEN-LAST:event_btndelActionPerformed

    private void btnupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupActionPerformed
        int row = tabelMhs.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel terlebih dahulu!");
            return;
        }

        String nimTabel = tabelMhs.getValueAt(row, 1).toString();
        String namaTabel = tabelMhs.getValueAt(row, 2).toString();
        String angkatanTabel = tabelMhs.getValueAt(row, 3).toString();
        String deptTabel = tabelMhs.getValueAt(row, 4).toString();
        String jkTabel = tabelMhs.getValueAt(row, 5).toString();
        String jkForm = rl.isSelected() ? "laki-laki" : "prempuan";
        String deptForm = cbd.getSelectedItem().toString();

        if (txtnim.getText().equals(nimTabel)
                && txtnama.getText().equals(namaTabel)
                && txta.getText().equals(angkatanTabel)
                && deptForm.equals(deptTabel)
                && jkForm.equals(jkTabel)) {

            JOptionPane.showMessageDialog(this, "Silahkan ubah data terlebih dahulu sebelum menekan Update!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }
 
        if (txtnim.getText().isEmpty() || txtnama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM dan Nama tidak boleh kosong!");
            return;
        }

        editData();
    }//GEN-LAST:event_btnupActionPerformed

    private void btnclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclActionPerformed
        new menuadmin().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnclActionPerformed

    private void txtnimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnimActionPerformed

    }//GEN-LAST:event_txtnimActionPerformed

    /* public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mahasiswa().setVisible(true);
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btncl;
    private javax.swing.JButton btndel;
    private javax.swing.JButton btnfind;
    private javax.swing.JButton btnup;
    private javax.swing.JComboBox<String> cbd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JMenuItem menuadd;
    private javax.swing.JMenuItem menudsn;
    private javax.swing.JMenuItem menumhs;
    private javax.swing.JMenuItem menumk;
    private javax.swing.JMenuItem menupw;
    private javax.swing.JMenuItem menuuser;
    private javax.swing.JRadioButton rl;
    private javax.swing.JRadioButton rp;
    private javax.swing.JTable tabelMhs;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txta;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnim;
    // End of variables declaration//GEN-END:variables
}
