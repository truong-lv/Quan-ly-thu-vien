/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
@Table(name = "Sach")
public class Sach {

    private String maISBN;
    private String tenSach;
    private TacGia tacGia;
    private NhaXuatBan nhaXuatBan;
    private Date namXB;
    private float giaBia;
    private int soTrang;
    private String moTa;
    private TheLoaiSach theLoaiSach;
    private int soLuong;
    private int soLuongCon;
    private NganKeSach nganKeSach;

    public String getMaISBN() {
        return maISBN;
    }

    public void setMaISBN(String maISBN) {
        this.maISBN = maISBN;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public TacGia getTacGia() {
        return tacGia;
    }

    public void setTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }

    public NhaXuatBan getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public Date getNamXB() {
        return namXB;
    }

    public void setNamXB(Date namXB) {
        this.namXB = namXB;
    }

    public float getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(float giaBia) {
        this.giaBia = giaBia;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public TheLoaiSach getTheLoaiSach() {
        return theLoaiSach;
    }

    public void setTheLoaiSach(TheLoaiSach theLoaiSach) {
        this.theLoaiSach = theLoaiSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuongCon() {
        return soLuongCon;
    }

    public void setSoLuongCon(int soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public NganKeSach getNganKeSach() {
        return nganKeSach;
    }

    public void setNganKeSach(NganKeSach nganKeSach) {
        this.nganKeSach = nganKeSach;
    }

    public static List<Sach> getList() {
        List<Sach> cacSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM Sach";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setMaISBN(rs.getNString("maISBN"));
                sach.setTenSach(rs.getNString("tenSach"));

                TacGia tacGia = TacGia.retrieve(rs.getNString("maTacGia"));
                sach.setTacGia(tacGia);
                NhaXuatBan nxb = NhaXuatBan.retrieve(rs.getNString("maNXB"));
                sach.setNhaXuatBan(nxb);

                sach.setNamXB(rs.getDate("namXB"));
                sach.setGiaBia(rs.getFloat("giaBia"));
                sach.setSoTrang(rs.getInt("soTrang"));
                sach.setMoTa(rs.getNString("moTa"));

                TheLoaiSach theLoaiSach = TheLoaiSach.retrieve(rs.getNString("maTheLoai"));
                sach.setTheLoaiSach(theLoaiSach);

                sach.setSoLuong(rs.getInt("soLuong"));
                sach.setSoLuongCon(rs.getInt("soLuongCon"));

                NganKeSach nganKeSach = NganKeSach.retrieve(rs.getNString("maNganKe"));
                sach.setNganKeSach(nganKeSach);

                cacSach.add(sach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacSach;
    }

    public static List<Sach> search(String keyword) {
        List<Sach> cacSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM Sach";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setMaISBN(rs.getNString("maISBN"));
                sach.setTenSach(rs.getNString("tenSach"));

                TacGia tacGia = TacGia.retrieve(rs.getNString("maTacGia"));
                sach.setTacGia(tacGia);
                NhaXuatBan nxb = NhaXuatBan.retrieve(rs.getNString("maNXB"));
                sach.setNhaXuatBan(nxb);

                sach.setNamXB(rs.getDate("namXB"));
                sach.setGiaBia(rs.getFloat("giaBia"));
                sach.setSoTrang(rs.getInt("soTrang"));
                sach.setMoTa(rs.getNString("moTa"));

                TheLoaiSach theLoaiSach = TheLoaiSach.retrieve(rs.getNString("maTheLoai"));
                sach.setTheLoaiSach(theLoaiSach);

                sach.setSoLuong(rs.getInt("soLuong"));
                sach.setSoLuongCon(rs.getInt("soLuongCon"));

                NganKeSach nganKeSach = NganKeSach.retrieve(rs.getNString("maNganKe"));
                sach.setNganKeSach(nganKeSach);

                cacSach.add(sach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacSach;
    }

    public static Sach retrieve(String maSach) {
        Sach sach = null;

        try {
            String query = "SELECT * FROM Sach WHERE maSach = '" + maSach + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                sach = new Sach();
                sach.setMaISBN(rs.getNString("maISBN"));
                sach.setTenSach(rs.getNString("tenSach"));

                TacGia tacGia = TacGia.retrieve(rs.getNString("maTacGia"));
                sach.setTacGia(tacGia);
                NhaXuatBan nxb = NhaXuatBan.retrieve(rs.getNString("maNXB"));
                sach.setNhaXuatBan(nxb);

                sach.setNamXB(rs.getDate("namXB"));
                sach.setGiaBia(rs.getFloat("giaBia"));
                sach.setSoTrang(rs.getInt("soTrang"));
                sach.setMoTa(rs.getNString("moTa"));

                TheLoaiSach theLoaiSach = TheLoaiSach.retrieve(rs.getNString("maTheLoai"));
                sach.setTheLoaiSach(theLoaiSach);

                sach.setSoLuong(rs.getInt("soLuong"));
                sach.setSoLuongCon(rs.getInt("soLuongCon"));

                NganKeSach nganKeSach = NganKeSach.retrieve(rs.getNString("maNganKe"));
                sach.setNganKeSach(nganKeSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sach;
    }

    public static boolean insert(String maISBN, String tenSach, String maTacGia, String maNXB, Date namXB, float giaBia, int soTrang, String moTa, String maTheLoai, int soLuong, int soLuongCon, String maNganKe) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String query = "INSERT INTO Sach VALUES(N'" + maISBN + "', N'" + tenSach + "', N'" + maTacGia + "', N'" + maNXB + "',  N'" + dateFormat.format(namXB) + "', N'" + giaBia + "', N'" + soTrang + "', N'" + moTa + "', N'" + maTheLoai + "', N'" + soLuong + "', N'" + soLuongCon + "', N'" + maNganKe + "')";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maISBN, String tenSach, String maTacGia, String maNXB, Date namXB, float giaBia, int soTrang, String moTa, String maTheLoai, int soLuong, int soLuongCon, String maNganKe) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String query = "UPDATE maSach SET tenSach = N'" + tenSach + "', maTacGia = N'" + maTacGia + "', maNXB = N'" + dateFormat.format(namXB) + "', giaBia = N'" + giaBia + "', soTrang = N'" + soTrang + "', moTa = N'" + moTa + "', maTheLoai = N'" + maTheLoai + "', soLuong = N'" + soLuong + "', soLuongCon = N'" + soLuongCon + "', maNganKe = N'" + maNganKe + "' WHERE maISBN = N'" + maISBN + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maSach) {
        String query = "DELETE FROM Sach WHERE maSach = '" + maSach + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

}
