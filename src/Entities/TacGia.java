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
public class TacGia {

    private String maTacGia;
    private String tenTacGia;

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public static List<TacGia> getList() {
        List<TacGia> cacTacGia = new ArrayList<>();

        try {
            String query = "SELECT * FROM ChiTietSach WHERE loaiChiTiet = 'TacGia'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                TacGia tacGia = new TacGia();
                tacGia.setMaTacGia(rs.getNString("maChiTiet"));
                tacGia.setTenTacGia(rs.getNString("tenChiTiet"));
                cacTacGia.add(tacGia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacGia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacTacGia;
    }

    public static List<TacGia> search(String keyword) {
        List<TacGia> cacTacGia = new ArrayList<>();

        try {
            String query = "SELECT * FROM ChiTietSach WHERE maChiTiet LIKE N'%" + keyword + "%' OR tenChiTiet LIKE N'%" + keyword + "%' AND loaiChiTiet = 'TacGia'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                TacGia tacGia = new TacGia();
                tacGia.setMaTacGia(rs.getNString("maChiTiet"));
                tacGia.setTenTacGia(rs.getNString("tenChiTiet"));
                cacTacGia.add(tacGia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacGia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacTacGia;
    }

    public static TacGia retrieve(String maTacGia) {
        TacGia tacGia = null;

        try {
            String query = "SELECT * FROM ChiTietSach WHERE maChiTiet = '" + maTacGia + "' AND loaiChiTiet = 'TacGia'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                tacGia = new TacGia();
                tacGia.setMaTacGia(rs.getNString("maChiTiet"));
                tacGia.setTenTacGia(rs.getNString("tenChiTiet"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tacGia;
    }

    public static boolean insert(String tenTacGia) {
        DBAccess dba = new DBAccess();
        String query = "INSERT INTO ChiTietSach VALUES(N'" + dba.generateId("3") + "', N'" + tenTacGia + "', N'TacGia')";
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maTacGia, String tenTacGia) {
        String query = "UPDATE ChiTietSach SET tenChiTiet = N'" + tenTacGia + "' WHERE maChiTiet = N'" + maTacGia + "' AND loaiChiTiet = 'TacGia'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maTacGia) {
        String query = "DELETE FROM ChiTietSach WHERE maChiTiet = '" + maTacGia + "' AND loaiChiTiet = 'TacGia'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
