/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phat
 */
//@Table(name = "Sach")
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
            String query = "SELECT * FROM Sach s JOIN TacGia tg ON s.maTacGia = tg.maTacGia JOIN NhaXuatBan nxb ON s.maNXB = nxb.maNXB JOIN TheLoaiSach tls ON s.maTheLoai = tls.maTheLoai WHERE s.maISBN LIKE N'%" + keyword + "%' OR s.tenSach LIKE N'%" + keyword + "%' OR tg.tenTacGia LIKE N'%" + keyword + "%' OR nxb.tenNXB LIKE N'%" + keyword + "%' OR s.namXB LIKE N'%" + keyword + "%' OR s.giaBia LIKE N'%" + keyword + "%' OR s.soTrang LIKE N'%" + keyword + "%' OR s.moTa LIKE N'%" + keyword + "%' OR tls.tenTheLoai LIKE N'%" + keyword + "%' OR s.soLuong LIKE N'%" + keyword + "%' OR s.soLuongCon LIKE N'%" + keyword + "%'";
            System.out.println(query);
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

    public static List<Sach> search(Sach sach, Khu khu, KeSach keSach, NganKeSach nganKeSach, String keyword) {
        List<Sach> cacSach = new ArrayList<>();

        List<String> conditions = new ArrayList<>();
        if (sach.getTacGia() != null) {
            conditions.add("tg.tenTacGia LIKE N'%" + sach.getTacGia().getTenTacGia() + "%'");
        }
        if (sach.getNhaXuatBan() != null) {
            conditions.add("nxb.tenNXB LIKE N'%" + sach.getNhaXuatBan().getTenNXB() + "%'");
        }
        if (sach.getTheLoaiSach() != null) {
            conditions.add("tls.tenTheLoai LIKE N'%" + sach.getTheLoaiSach().getTenTheLoai() + "%'");
        }
        if (khu != null) {
            conditions.add("k.tenKhu LIKE N'%" + khu.getTenKhu() + "%'");
        }
        if (keSach != null) {
            conditions.add("ks.tenKe LIKE N'%" + keSach.getTenKe() + "%'");
        }
        if (nganKeSach != null) {
            conditions.add("nks.tenNgan LIKE N'%" + nganKeSach.getTenNgan() + "%'");
        }

//        if (keSach != null) {
//            conditions.add("ks.tenKeSach LIKE N'%" + keSach.getTenKe() + "%'");
//        }
        String condition = String.join(" AND ", conditions);
        if (!keyword.isEmpty()) {
            condition += "OR s.maISBN LIKE N'%" + keyword + "%' OR s.tenSach LIKE N'%" + keyword + "%' OR s.giaBia LIKE N'%" + keyword + "%' OR s.soTrang LIKE N'%" + keyword + "%' OR s.moTa LIKE N'%" + keyword + "%' OR s.soLuongCon LIKE N'%" + keyword + "%'";
        }

        try {
            String query = "SELECT * FROM Sach s JOIN TacGia tg ON s.maTacGia = tg.maTacGia JOIN NhaXuatBan nxb ON s.maNXB = nxb.maNXB JOIN TheLoaiSach tls ON s.maTheLoai = tls.maTheLoai JOIN NganKeSach nks ON s.maNganKe = nks.maNganKe JOIN KeSach ks ON ks.maKe = nks.maKe JOIN Khu k ON ks.maKhu = k.maKhu  WHERE " + condition;
            System.out.println(query);
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs != null) {

                while (rs.next()) {
                    Sach s = new Sach();
                    s.setMaISBN(rs.getNString("maISBN"));
                    s.setTenSach(rs.getNString("tenSach"));

                    TacGia tacGia = TacGia.retrieve(rs.getNString("maTacGia"));
                    s.setTacGia(tacGia);
                    NhaXuatBan nxb = NhaXuatBan.retrieve(rs.getNString("maNXB"));
                    s.setNhaXuatBan(nxb);

                    s.setNamXB(rs.getDate("namXB"));
                    s.setGiaBia(rs.getFloat("giaBia"));
                    s.setSoTrang(rs.getInt("soTrang"));
                    s.setMoTa(rs.getNString("moTa"));

                    TheLoaiSach theLoaiSach = TheLoaiSach.retrieve(rs.getNString("maTheLoai"));
                    s.setTheLoaiSach(theLoaiSach);

                    s.setSoLuong(rs.getInt("soLuong"));
                    s.setSoLuongCon(rs.getInt("soLuongCon"));

                    NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                    s.setNganKeSach(nks);

                    cacSach.add(s);
                }
            } else {
                cacSach = Sach.getList();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
            cacSach = Sach.getList();
        }

        return cacSach;
    }

    public static Sach retrieve(String maISBN) {
        Sach sach = null;

        try {
            String query = "SELECT * FROM Sach WHERE maISBN = '" + maISBN + "'";
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
        String query = "UPDATE Sach SET tenSach = N'" + tenSach + "', maTacGia = N'" + maTacGia + "', maNXB = N'" + maNXB + "', namXB = N'" + dateFormat.format(namXB) + "', giaBia = N'" + giaBia + "', soTrang = N'" + soTrang + "', moTa = N'" + moTa + "', maTheLoai = N'" + maTheLoai + "', soLuong = N'" + soLuong + "', soLuongCon = N'" + soLuongCon + "', maNganKe = N'" + maNganKe + "' WHERE maISBN = N'" + maISBN + "'";
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
