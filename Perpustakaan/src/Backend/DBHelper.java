/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author YAN DAFFA
 */
import java.sql.*;

public class DBHelper {

    private static Connection koneksi;

    public static void bukaKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/dbperpus";
                String user = "root";
                String password = "";
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil!");
            } catch (SQLException e) {
                System.out.println("Error koneksi: " + e.getMessage());
            }
        }
    }

    public static int insertQueryGetId(String query) {
        bukaKoneksi();
        int result = -1;

        try (Statement stmt = koneksi.createStatement()) {
            int num = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean executeQuery(String query) {
        bukaKoneksi();
        boolean result = false;

        try (Statement stmt = koneksi.createStatement()) {
            stmt.executeUpdate(query);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ResultSet selectQuery(String query) {
        bukaKoneksi();
        ResultSet rs = null;

        try {
            Statement stmt = koneksi.createStatement();
            rs = stmt.executeQuery(query);
            // Pastikan untuk menutup Statement setelah selesai
            // Anda dapat mengembalikan Statement jika Anda ingin menutupnya di luar
        } catch (SQLException e) {
            e.printStackTrace();
            // Jika ada kesalahan, rs akan tetap null
        }

        return rs;
    }

    static Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
