/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class LoaiCoSoVatChat {

    private String maLoaiCSVC;
    private String tenLoaiCSVC;

    public String getMaLoaiCSVC() {
        return maLoaiCSVC;
    }

    public void setMaLoaiCSVC(String maLoaiCSVC) {
        this.maLoaiCSVC = maLoaiCSVC;
    }

    public String getTenLoaiCSVC() {
        return tenLoaiCSVC;
    }

    public void setTenLoaiCSVC(String tenLoaiCSVC) {
        this.tenLoaiCSVC = tenLoaiCSVC;
    }

    public static List<LoaiCoSoVatChat> getList() {
        List<LoaiCoSoVatChat> cacLoaiCSVC = new ArrayList<>();

        try {
            String query = "SELECT * FROM LoaiCoSoVatChat";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                LoaiCoSoVatChat loaiCSVC = new LoaiCoSoVatChat();
                loaiCSVC.setMaLoaiCSVC(rs.getNString("maLoaiCSVC"));
                loaiCSVC.setTenLoaiCSVC(rs.getNString("tenLoaiCSVC"));
                cacLoaiCSVC.add(loaiCSVC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiCoSoVatChat.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacLoaiCSVC;
    }

    public static List<LoaiCoSoVatChat> search(String keyword) {
        List<LoaiCoSoVatChat> cacLoaiCSVC = new ArrayList<>();

        try {
            String query = "SELECT * FROM LoaiCoSoVatChat WHERE maLoaiCSVC LIKE N'%" + keyword + "%' OR tenLoaiCSVC LIKE N'%" + keyword + "%'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                LoaiCoSoVatChat loaiCSVC = new LoaiCoSoVatChat();
                loaiCSVC.setMaLoaiCSVC(rs.getNString("maLoaiCSVC"));
                loaiCSVC.setTenLoaiCSVC(rs.getNString("tenLoaiCSVC"));
                cacLoaiCSVC.add(loaiCSVC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiCoSoVatChat.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacLoaiCSVC;
    }

    public static boolean insert(String maLoaiCSVC, String tenLoaiCSVC) {
        String query = "INSERT INTO LoaiCoSoVatChat VALUES(N'" + maLoaiCSVC + "', N'" + tenLoaiCSVC + "')";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maLoaiCSVC, String tenLoaiCSVC) {
        String query = "UPDATE LoaiCoSoVatChat SET tenLoaiCSVC = N'" + tenLoaiCSVC + "' WHERE maLoaiCSVC = N'" + maLoaiCSVC + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maLoaiCSVC) {
        String query = "DELETE FROM LoaiCoSoVatChat WHERE maLoaiCSVC = '" + maLoaiCSVC + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
