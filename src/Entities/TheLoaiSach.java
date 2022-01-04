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
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
//@Table(name = "TheLoaiSach")
public class TheLoaiSach {

    private String maTheLoai;
    private String tenTheLoai;

//    @OneToMany(mappedBy = "theLoaiSach", fetch = FetchType.EAGER)
//    private List<Sach> cacSach;
//
//    public List<Sach> getCacSach() {
//        return cacSach;
//    }
//
//    public void setCacSach(List<Sach> cacSach) {
//        this.cacSach = cacSach;
//    }
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
            String query = "SELECT * FROM TheLoaiSach";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                TheLoaiSach theLoaiSach = new TheLoaiSach();
                theLoaiSach.setMaTheLoai(rs.getNString("maTheLoai"));
                theLoaiSach.setTenTheLoai(rs.getNString("tenTheLoai"));
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
            String query = "SELECT * FROM TheLoaiSach WHERE maTheLoai = '" + maTheLoai + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                theLoaiSach = new TheLoaiSach();
                theLoaiSach.setMaTheLoai(rs.getNString("maTheLoai"));
                theLoaiSach.setTenTheLoai(rs.getNString("tenTheLoai"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theLoaiSach;
    }

    public static List<TheLoaiSach> search(String keyword) {
        List<TheLoaiSach> cacTheLoaiSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM TheLoaiSach WHERE maTheLoai LIKE N'%" + keyword + "%' OR tenTheLoai LIKE N'%" + keyword + "%'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                TheLoaiSach theLoaiSach = new TheLoaiSach();
                theLoaiSach.setMaTheLoai(rs.getNString("maTheLoai"));
                theLoaiSach.setTenTheLoai(rs.getNString("tenTheLoai"));
                cacTheLoaiSach.add(theLoaiSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiSach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacTheLoaiSach;
    }

    public static boolean insert(String maTheLoai, String tenTheLoai) {
        String query = "INSERT INTO TheLoaiSach VALUES(N'" + maTheLoai + "', N'" + tenTheLoai + "')";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maTheLoai, String tenTheLoai) {
        String query = "UPDATE TheLoaiSach SET tenTheLoai = N'" + tenTheLoai + "' WHERE maTheLoai = N'" + maTheLoai + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maTheLoai) {
        String query = "DELETE FROM TheLoaiSach WHERE maTheLoai = '" + maTheLoai + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
