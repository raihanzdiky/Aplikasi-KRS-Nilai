package soal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CetakDialog extends javax.swing.JDialog {

   private String nimTarget;
    public CetakDialog(java.awt.Frame parent, boolean modal, String nim) {
        super(parent, modal);
        this.nimTarget = nim;
        initComponents();
        loadDataCetak();
        setLocationRelativeTo(parent);
    }
    
   private void loadDataCetak() {
    DefaultTableModel model = (DefaultTableModel) tableCetak.getModel();
    model.setRowCount(0);

    double totalBobotSks = 0;
    int totalSksSemua = 0;

    try {
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db_uts", "root", "");
        String sqlHeader = "SELECT m.nama_mahasiswa, m.nim, m.departemen, m.angkatan, " +
                           "IFNULL((SELECT mk.semester FROM tb_nilai n " +
                           " JOIN tb_matakuliah mk ON n.idmk = mk.idmk " +
                           " WHERE n.idm = m.idm GROUP BY mk.semester " +
                           " ORDER BY COUNT(*) DESC LIMIT 1), '-') as semester_aktif " +
                           "FROM tb_mahasiswa m WHERE m.nim = ?";
        
        PreparedStatement psHeader = cn.prepareStatement(sqlHeader);
        psHeader.setString(1, nimTarget);
        ResultSet rsHeader = psHeader.executeQuery();

        if (rsHeader.next()) {
            lblNama.setText(" " + rsHeader.getString("nama_mahasiswa"));
            lblNim.setText(" " + rsHeader.getString("nim"));
            lblDept.setText(" " + rsHeader.getString("departemen"));
            lblAngkatan.setText(" " + rsHeader.getString("angkatan"));
            lblSemester.setText("Semester " + rsHeader.getString("semester_aktif"));
        }

        String sqlTabel = "SELECT mk.id_matkul, mk.nama_matkul, mk.jumlah_sks, n.nakhir, n.grade " +
                          "FROM tb_nilai n " +
                          "JOIN tb_mahasiswa m ON n.idm = m.idm " +
                          "JOIN tb_matakuliah mk ON n.idmk = mk.idmk " +
                          "WHERE m.nim = ?";
        
        PreparedStatement psTabel = cn.prepareStatement(sqlTabel);
        psTabel.setString(1, nimTarget);
        ResultSet rsTabel = psTabel.executeQuery();

        while (rsTabel.next()) {
            String grade = rsTabel.getString("grade");
            int sks = rsTabel.getInt("jumlah_sks");
            int bobot = 0;
            switch (grade) {
                case "A": bobot = 4; break;
                case "B": bobot = 3; break;
                case "C": bobot = 2; break;
                case "D": bobot = 1; break;
                case "E": bobot = 0; break;
                default:  bobot = 0; break;
            }

            totalBobotSks += (bobot * sks);
            totalSksSemua += sks;

            model.addRow(new Object[]{
                rsTabel.getString("id_matkul"),
                rsTabel.getString("nama_matkul"),
                sks,
                rsTabel.getDouble("nakhir"),
                grade
            });
        }

        if (totalSksSemua > 0) {
            double ips = totalBobotSks / totalSksSemua;
            jLabel8.setText(String.format("%.2f", ips)); 
        } else {
            jLabel8.setText("0.00");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
    }
}

  


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCetak = new javax.swing.JTable();
        lblNama = new javax.swing.JLabel();
        lblNim = new javax.swing.JLabel();
        lblDept = new javax.swing.JLabel();
        lblAngkatan = new javax.swing.JLabel();
        lblSemester = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/UTB70.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("DAFTAR NILAI MAHASISWA");

        tableCetak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Matkul", "Mata Kuliah", "Jumlah SKS", "Score", "Grade"
            }
        ));
        tableCetak.setFillsViewportHeight(true);
        tableCetak.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tableCetak.setShowHorizontalLines(true);
        tableCetak.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tableCetak);

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNama.setText("NAMA");

        lblNim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNim.setText("NIM");

        lblDept.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDept.setText("Dept");

        lblAngkatan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAngkatan.setText("Angkatan");

        lblSemester.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSemester.setText("Semester");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nama :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("NIM    :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Program Studi :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Angkatan :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("IPS           :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("IPS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(195, 195, 195)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblDept)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblNim)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblNama)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                                        .addComponent(jLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAngkatan)
                                    .addComponent(jLabel8))
                                .addGap(184, 184, 184))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(lblSemester)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblAngkatan)
                    .addComponent(lblNama)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNim)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDept)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addComponent(lblSemester)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
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

   /* public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CetakDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Untuk pengetesan mandiri (Gunakan NIM dummy yang ada di DB-mu)
                CetakDialog dialog = new CetakDialog(new javax.swing.JFrame(), true, "2023001");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        }); 
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAngkatan;
    private javax.swing.JLabel lblDept;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNim;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JTable tableCetak;
    // End of variables declaration//GEN-END:variables
}
