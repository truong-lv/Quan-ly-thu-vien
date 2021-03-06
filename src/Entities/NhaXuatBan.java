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
public class NhaXuatBan {

    private String maNXB;
    private String tenNXB;

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public static List<NhaXuatBan> getList() {
        List<NhaXuatBan> cacNXB = new ArrayList<>();

        try {
            String query = "SELECT * FROM ChiTietSach WHERE loaiChiTiet = 'NXB'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getNString("maChiTiet"));
                nxb.setTenNXB(rs.getNString("tenChiTiet"));
                cacNXB.add(nxb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaXuatBan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacNXB;
    }

    public static NhaXuatBan retrieve(String maNXB) {
        NhaXuatBan nxb = new NhaXuatBan();

        try {
            String query = "SELECT * FROM ChiTietSach WHERE maChiTiet = '" + maNXB + "' AND loaiChiTiet = 'NXB'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                nxb.setMaNXB(rs.getNString("maChiTiet"));
                nxb.setTenNXB(rs.getNString("tenChiTiet"));
            }
            return nxb;
        } catch (SQLException ex) {
            Logger.getLogger(NhaXuatBan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<NhaXuatBan> search(String keyword) {
        List<NhaXuatBan> cacNXB = new ArrayList<>();

        try {
            String query = "SELECT * FROM ChiTiet WHERE maChiTiet LIKE N'%" + keyword + "%' OR tenChiTiet LIKE N'%" + keyword + "%' AND loaiChiTiet = 'NXB'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getNString("maChiTiet"));
                nxb.setTenNXB(rs.getNString("tenChiTiet"));
                cacNXB.add(nxb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaXuatBan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacNXB;
    }

    public static boolean insert(String tenNXB) {
        DBAccess dba = new DBAccess();
        String query = "INSERT INTO ChiTietSach VALUES(N'" + dba.generateId("4") + "', N'" + tenNXB + "', N'NXB')";
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maNXB, String tenNXB) {
        String query = "UPDATE ChiTietSach SET tenChiTiet = N'" + tenNXB + "' WHERE maChiTiet = N'" + maNXB + "' AND loaiChiTiet = 'NXB'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maNXB) {
        String query = "DELETE FROM ChiTietSach WHERE maChiTiet = '" + maNXB + "' AND loaiChiTiet = 'NXB'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
