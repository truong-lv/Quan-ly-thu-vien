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
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
//@Table(name = "KeSach")
public class KeSach {

    private String maKe;
    private String tenKe;
    private String thongTin;
    private Khu khu;
    private List<NganKeSach> cacNganKeSach = new ArrayList<>();

//    @ManyToOne()
//    @JoinColumn(name = "maKhu")
//    private Khu keSach;
//
//    @OneToMany(mappedBy = "keSach", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<NganKeSach> cacNganKeSach;
//    public List<NganKeSach> getCacNganKeSach() {
//        return cacNganKeSach;
//    }
//
//    public void setCacNganKeSach(List<NganKeSach> cacNganKeSach) {
//        this.cacNganKeSach = cacNganKeSach;
//    }
    public String getMaKe() {
        return maKe;
    }

    public void setMaKe(String maKe) {
        this.maKe = maKe;
    }

    public String getTenKe() {
        return tenKe;
    }

    public void setTenKe(String tenKe) {
        this.tenKe = tenKe;
    }

    public String getThongTin() {
        return thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }

    public Khu getKhu() {
        return khu;
    }

    public void setKhu(Khu khu) {
        this.khu = khu;
    }
    
    

    public static List<KeSach> getList() {
        List<KeSach> cacKeSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM KeSach";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                KeSach keSach = new KeSach();
                keSach.setMaKe(rs.getNString("maKe"));
                keSach.setTenKe(rs.getNString("tenKe"));
                keSach.setThongTin(rs.getNString("thongTin"));

                Khu khu = Khu.retrieve(rs.getNString("maKhu"));
                keSach.setKhu(khu);

                cacKeSach.add(keSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Khu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacKeSach;
    }

    public static List<KeSach> search(String keyword) {
        List<KeSach> cacKeSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM KeSach WHERE maKe LIKE N'%" + keyword + "%' OR tenKe LIKE N'%" + keyword + "%' OR thongTin LIKE N'%" + keyword + "%' OR maKhu LIKE N'%" + keyword + "%'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                KeSach keSach = new KeSach();
                keSach.setMaKe(rs.getNString("maKe"));
                keSach.setTenKe(rs.getNString("tenKe"));
                keSach.setThongTin(rs.getNString("thongTin"));

                Khu khu = Khu.retrieve(rs.getNString("maKhu"));
                keSach.setKhu(khu);

                cacKeSach.add(keSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Khu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacKeSach;
    }

    public static KeSach retrieve(String maKe) {
        KeSach keSach = new KeSach();

        try {
            String query = "SELECT * FROM KeSach WHERE maKe = '" + maKe + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                keSach.setMaKe(rs.getNString("maKe"));
                keSach.setTenKe(rs.getNString("tenKe"));
                keSach.setThongTin(rs.getNString("thongTin"));

                Khu khu = Khu.retrieve(rs.getNString("maKhu"));
                keSach.setKhu(khu);
                
                
            }
            return keSach;
        } catch (SQLException ex) {
            Logger.getLogger(KeSach.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean insert(String maKe, String tenKe, String thongTin, String maKhu) {
        String query = "INSERT INTO KeSach VALUES(N'" + maKe + "', N'" + tenKe + "', N'" + thongTin + "', N'" + maKhu + "')";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maKe, String tenKe, String thongTin, String maKhu) {
        String query = "UPDATE KeSach SET tenKe = N'" + tenKe + "', moTa = N'" + thongTin + "', tang = N'" + maKhu + "' WHERE maKe = N'" + maKe + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maKe) {
        String query = "DELETE FROM KeSach WHERE maKe = N'" + maKe + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
