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
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
//@Table(name = "Khu")
public class Khu {

    private String maKhu;
    private String tenKhu;
    private String moTa;
    private String tang;

//    @OneToMany(mappedBy = "khu", fetch = FetchType.EAGER)
//    private List<KeSach> cacKeSach;
//
//    public List<KeSach> getCacKeSach() {
//        return cacKeSach;
//    }
//
//    public void setCacKeSach(List<KeSach> cacKeSach) {
//        this.cacKeSach = cacKeSach;
//    }
    public String getMaKhu() {
        return maKhu;
    }

    public void setMaKhu(String maKhu) {
        this.maKhu = maKhu;
    }

    public String getTenKhu() {
        return tenKhu;
    }

    public void setTenKhu(String tenKhu) {
        this.tenKhu = tenKhu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTang() {
        return tang;
    }

    public void setTang(String tang) {
        this.tang = tang;
    }

    public static List<Khu> getList() {
        List<Khu> cacKhu = new ArrayList<>();

        try {
            String query = "SELECT * FROM Khu";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                Khu khu = new Khu();
                khu.setMaKhu(rs.getNString("maKhu"));
                khu.setTenKhu(rs.getNString("tenKhu"));
                khu.setMoTa(rs.getNString("moTa"));
                khu.setTang(rs.getNString("tang"));
                cacKhu.add(khu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Khu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacKhu;
    }

    public static List<Khu> search(String keyword) {
        List<Khu> cacKhu = new ArrayList<>();

        try {
            String query = "SELECT * FROM Khu WHERE maKhu LIKE N'%" + keyword + "%' OR tenKhu LIKE N'%" + keyword + "%' OR moTa LIKE N'%" + keyword + "%' OR tang LIKE N'%" + keyword + "%'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                Khu khu = new Khu();
                khu.setMaKhu(rs.getNString("maKhu"));
                khu.setTenKhu(rs.getNString("tenKhu"));
                khu.setMoTa(rs.getNString("moTa"));
                khu.setTang(rs.getNString("tang"));
                cacKhu.add(khu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Khu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacKhu;
    }

    public static boolean insert(String maKhu, String tenKhu, String moTa, String tang) {
        String query = "INSERT INTO Khu VALUES(N'" + maKhu + "', N'" + tenKhu + "', N'" + moTa + "', N'" + tang + "')";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maKhu, String tenKhu, String moTa, String tang) {
        String query = "UPDATE Khu SET tenKhu = N'" + tenKhu + "', moTa = N'" + moTa + "', tang = N'" + tang + "' WHERE maKhu = N'" + maKhu + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maKhu) {
        String query = "DELETE FROM Khu WHERE maKhu = N'" + maKhu + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
