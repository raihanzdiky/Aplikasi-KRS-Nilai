package soal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class config {

    private static Connection mysqlconfig;

    public static Connection configDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/db_uts";
            String user = "root";
            String pass = ""; // Ganti jika pakai password
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
        return mysqlconfig;
    }

  /*  public static void main(String[] args) {
        try {
            Connection kon = config.configDB();
            if (kon != null) {
                System.out.println("Sip! Koneksi ke database BERHASIL.");
            } else {
                System.out.println("Wah, koneksi gagal (null).");
            }
        } catch (Exception e) {
            System.out.println("Error saat testing: " + e.getMessage());
        }
    } */
}
