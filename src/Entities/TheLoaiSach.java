/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phat
 */
public class TheLoaiSach {

    private String maTheLoai;
    private String tenTheLoai;

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public static List<TheLoaiSach> getList() {
        List<TheLoaiSach> cacTheLoaiSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM ChiTietSach WHERE loaiChiTiet = 'TheLoaiSach'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                TheLoaiSach theLoaiSach = new TheLoaiSach();
                theLoaiSach.setMaTheLoai(rs.getNString("maChiTiet"));
                theLoaiSach.setTenTheLoai(rs.getNString("tenChiTiet"));
                cacTheLoaiSach.add(theLoaiSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiSach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacTheLoaiSach;
    }

    public static TheLoaiSach retrieve(String maTheLoai) {
        TheLoaiSach theLoaiSach = null;

        try {
            String query = "SELECT * FROM ChiTietSach WHERE maChiTiet = '" + maTheLoai + "' AND loaiChiTiet = 'TheLoaiSach'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                theLoaiSach = new TheLoaiSach();
                theLoaiSach.setMaTheLoai(rs.getNString("maChiTiet"));
                theLoaiSach.setTenTheLoai(rs.getNString("tenChiTiet"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theLoaiSach;
    }

    public static List<TheLoaiSach> search(String keyword) {
        List<TheLoaiSach> cacTheLoaiSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM ChiTietSach WHERE maChiTiet LIKE N'%" + keyword + "%' OR tenChiTiet LIKE N'%" + keyword + "%' AND loaiChiTiet = 'TheLoaiSach'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                TheLoaiSach theLoaiSach = new TheLoaiSach();
                theLoaiSach.setMaTheLoai(rs.getNString("maChiTiet"));
                theLoaiSach.setTenTheLoai(rs.getNString("tenChiTiet"));
                cacTheLoaiSach.add(theLoaiSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiSach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacTheLoaiSach;
    }

    public static boolean insert(String tenTheLoai) {
        DBAccess dba = new DBAccess();
        String query = "INSERT INTO ChiTietSach VALUES(N'" + dba.generateId("5") + "', N'" + tenTheLoai + "', N'TheLoaiSach')";
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maTheLoai, String tenTheLoai) {
        String query = "UPDATE ChiTietSach SET tenChiTiet = N'" + tenTheLoai + "' WHERE maChiTiet = N'" + maTheLoai + "' AND loaiChiTiet = 'TheLoaiSach'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maTheLoai) {
        String query = "DELETE FROM TheLoaiSach WHERE maTheLoai = '" + maTheLoai + "' AND loaiChiTiet = 'TheLoaiSach'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
