/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author YAN DAFFA
 */
public class Buku {

    private int idBuku;
    private Kategori kategori = new Kategori();
    private String Judul;
    private String penerbit;
    private String Penulis;

    public Buku() {
    }

    public Buku(Kategori kategori, String Judul, String Penerbit, String Penulis) {
        this.kategori = kategori;
        this.Judul = Judul;
        this.penerbit = penerbit;
        this.Penulis = Penulis;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String Judul) {
        this.Judul = Judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPenulis() {
        return Penulis;
    }

    public void setPenulis(String Penulis) {
        this.Penulis = Penulis;
    }

    public Buku getById(int id) {
        Buku buku = new Buku();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "b.idbuku AS idbuku,"
                + "b.judul AS judul, "
                + "b.penerbit AS penerbit,"
                + "b.penulis AS penulis, "
                + "k.idkategori AS idkategori,"
                + "k.nama AS nama, "
                + "k.keterangan AS keterangan"
                + "FROM buku b"
                + "LEFT JOIN kategori k ON b.idkategori k.idkategori "
                + "WHERE b.idbuku = " + id + "'");

        try {
            while (rs.next()) {
                buku = new Buku();
                buku.setIdBuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("nama"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buku;
    }

    public ArrayList<Buku> getAll() {
        ArrayList<Buku> ListBuku = new ArrayList();
        ResultSet rs = DBHelper.selectQuery(
                "SELECT"
                + "b.idbuku AS idbuku,"
                + "b.judul AS judul,"
                + "b.penerbit AS penerbit,"
                + "b.penulis AS penulis,"
                + "k.idkategori AS idkategori'"
                + "k.nama AS nama,"
                + "k.keterangan AS keterangan "
                + "FROM buku b"
                + "LEFT JOIN kategori k ON b.idkategori = k.idkategori");
        try {
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setIdBuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("name"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                ListBuku.add(buku);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBuku;

    }

    public ArrayList<Buku> search(String keyword) {
        ArrayList<Buku> ListBuku = new ArrayList();
        ResultSet rs = DBHelper.selectQuery(
                "SELECT"
                + "b.idbuku AS idbuku,"
                + "b.judul AS judul,"
                + "b.penerbit AS penerbit,"
                + "b.penulis AS penulis,"
                + "k.idkategori AS idkategori'"
                + "k.nama AS nama,"
                + "k.keterangan AS keterangan "
                + "FROM buku b"
                + "LEFT JOIN kategori k ON b.idkategori = k.idkategori"
                + "WHERE b.judul LIKE '%" + keyword + "%'"
                + "OR b.penerbit LIke '%" + keyword + "%'"
                + "OR b.penulis LIke '%" + keyword + "%'");

        try {
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setIdBuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("name"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                ListBuku.add(buku);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBuku;
    }

    public void save() {
        if (getById(idBuku).getIdBuku() == 0) {
            String SQL = "INSERT INTO buku (judul, idkategori, penulis, penerbit) VALUES ("
                    + "     '" + this.Judul + "',"
                    + " '" + this.getKategori().getIdkategori() + "', "
                    + " '" + this.Penulis + "',"
                    + " '" + this.penerbit + "'"
                    + "     )";
            this.idBuku = DBHelper.insertQueryGetId(SQL);

        } else {
            String SQL = "UPDATE buku SET "
                    + " judul ='" + this.Judul + "', "
                    + " idkategori ='" + this.getKategori().getIdkategori() + "' ,"
                    + " penulis ='" + this.Penulis + "',"
                    + "penerbit = '" + this.penerbit + "'"
                    + " WHERE idbuku= '" + this.idBuku + "'";

            DBHelper.executeQuery(SQL);
        }
    }

    public void delete() {
        String SQL = "DELETE FROM buku WHERE idbuku = '" + this.idBuku + "'";

        DBHelper.executeQuery(SQL);
    }
}
