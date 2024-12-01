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
import java.util.ArrayList;

public class Peminjaman {

    private int idpeminjaman;
    private int idanggota;
    private int idbuku;
    private String tanggalpinjam;
    private String tanggalkembali;

    public Peminjaman() {
    }

    public int getId() {
        return idpeminjaman;
    }

    public void setId(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public int getIdAnggota() {
        return idanggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idanggota = idAnggota;
    }

    public int getIdBuku() {
        return idbuku;
    }

    public void setIdBuku(int idBuku) {
        this.idbuku = idBuku;
    }

    public String getTanggalPinjam() {
        return tanggalpinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalpinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalkembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalkembali = tanggalKembali;
    }

    public void save() {
        if (this.idpeminjaman == 0) {
            String sql = "INSERT INTO peminjaman (idanggota, idbuku, tanggalpinjam, tanggalkembali) VALUES ('" + this.idanggota + "', '" + this.idbuku + "', '" + this.tanggalpinjam + "', '" + this.tanggalkembali + "')";
            this.idpeminjaman = DBHelper.insertQueryGetId(sql);
        } else {
            String sql = "UPDATE peminjaman SET idAnggota = '" + this.idanggota + "', idBuku = '" + this.idbuku + "', tanggalPinjam = '" + this.tanggalpinjam + "', tanggalKembali = '" + this.tanggalkembali + "' WHERE id = '" + this.idpeminjaman + "'";
            DBHelper.executeQuery(sql);
        }
    }

    public void delete() {
        String sql = "DELETE FROM peminjaman WHERE idpeminjaman = '" + this.idpeminjaman + "'";
        DBHelper.executeQuery(sql);
    }

    public static Peminjaman getById(int id) {
        Peminjaman peminjaman = null;
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM peminjaman WHERE idpeminjaman = '" + id + "'");
        try {
            if (rs.next()) {
                peminjaman = new Peminjaman();
                peminjaman.setId(rs.getInt("idpeminjaman"));
                peminjaman.setIdAnggota(rs.getInt("idanggota"));
                peminjaman.setIdBuku(rs.getInt("idbuku"));
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peminjaman;
    }

    public static ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> list = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM peminjaman");
        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setId(rs.getInt("idpeminjaman"));
                peminjaman.setIdAnggota(rs.getInt("idanggota"));
                peminjaman.setIdBuku(rs.getInt("idbuku"));
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));
                list.add(peminjaman);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
