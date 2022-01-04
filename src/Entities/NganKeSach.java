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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
//@Table(name = "NganKeSach")
public class NganKeSach {

    private String maNganKe;
    private String tenNgan;
    private KeSach keSach;

//    @ManyToOne()
//    @JoinColumn(name = "maKe")
//    private KeSach keSach;
//
//    @OneToMany(mappedBy = "sach", fetch = FetchType.EAGER)
//    private List<Sach> cacSach;
    public String getMaNganKe() {
        return maNganKe;
    }

    public void setMaNganKe(String maNganKe) {
        this.maNganKe = maNganKe;
    }

    public String getTenNgan() {
        return tenNgan;
    }

    public void setTenNgan(String tenNgan) {
        this.tenNgan = tenNgan;
    }

    public KeSach getKeSach() {
        return keSach;
    }

    public void setKeSach(KeSach keSach) {
        this.keSach = keSach;
    }

    public static List<NganKeSach> getList() {
        List<NganKeSach> cacNganKeSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM NganKeSach";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                NganKeSach nganKeSach = new NganKeSach();
                nganKeSach.setMaNganKe(rs.getNString("maNganKe"));
                nganKeSach.setTenNgan(rs.getNString("tenNgan"));

                KeSach keSach = KeSach.retrieve(rs.getNString("maKe"));
                nganKeSach.setKeSach(keSach);

                cacNganKeSach.add(nganKeSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NganKeSach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacNganKeSach;
    }

    public static List<NganKeSach> getList(String maKe) {
        List<NganKeSach> cacNganKeSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM KeSach k JOIN NganKeSach nks ON k.maKe = nks.maKe WHERE k.maKe = '" + maKe + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                NganKeSach nganKeSach = new NganKeSach();
                nganKeSach.setMaNganKe(rs.getNString("maNganKe"));
                nganKeSach.setTenNgan(rs.getNString("tenNgan"));

                KeSach keSach = KeSach.retrieve(rs.getNString("maKe"));
                nganKeSach.setKeSach(keSach);

                cacNganKeSach.add(nganKeSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NganKeSach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacNganKeSach;
    }

    public static List<NganKeSach> search(String maKe, String keyword) {
        List<NganKeSach> cacNganKeSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM KeSach k JOIN NganKeSach nks ON k.maKe = nks.maKe WHERE nks.maNganKe LIKE N'%" + keyword + "%' OR nks.tenNgan LIKE N'%" + keyword + "%' OR nks.maKe LIKE N'%" + keyword + "%' AND k.maKe = '" + maKe + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                NganKeSach nganKeSach = new NganKeSach();
                nganKeSach.setMaNganKe(rs.getNString("maNganKe"));
                nganKeSach.setTenNgan(rs.getNString("tenNgan"));

                KeSach keSach = KeSach.retrieve(rs.getNString("maKe"));
                nganKeSach.setKeSach(keSach);

                cacNganKeSach.add(nganKeSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NganKeSach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacNganKeSach;
    }

    public static List<NganKeSach> search(String keyword) {
        List<NganKeSach> cacNganKeSach = new ArrayList<>();

        try {
            String query = "SELECT * FROM KeSach WHERE maNganKe LIKE N'%" + keyword + "%' OR tenNgan LIKE N'%" + keyword + "%' OR maKe LIKE N'%" + keyword + "%'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                NganKeSach nganKeSach = new NganKeSach();
                nganKeSach.setMaNganKe(rs.getNString("maNganKe"));
                nganKeSach.setTenNgan(rs.getNString("tenNgan"));

                KeSach keSach = KeSach.retrieve(rs.getNString("maKe"));
                nganKeSach.setKeSach(keSach);

                cacNganKeSach.add(nganKeSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NganKeSach.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacNganKeSach;
    }

    public static NganKeSach retrieve(String maNganKe) {
        NganKeSach nganKeSach = null;

        try {
            String query = "SELECT * FROM NganKeSach WHERE maNganKe = '" + maNganKe + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                nganKeSach = new NganKeSach();
                nganKeSach.setMaNganKe(rs.getNString("maNganKe"));
                nganKeSach.setTenNgan(rs.getNString("tenNgan"));

                KeSach keSach = KeSach.retrieve(rs.getNString("maKe"));
                nganKeSach.setKeSach(keSach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NganKeSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nganKeSach;
    }

    public static boolean insert(String maNganKe, String tenNgan, String maKe) {
        String query = "INSERT INTO NganKeSach VALUES(N'" + maNganKe + "', N'" + tenNgan + "', N'" + maKe + "')";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maNganKe, String tenNgan, String maKe) {
        String query = "UPDATE NganKeSach SET tenNgan = N'" + tenNgan + "', maKe = N'" + maKe + "' WHERE maNganKe = N'" + maNganKe + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maNganKe) {
        String query = "DELETE FROM NganKeSach WHERE maNganKe = '" + maNganKe + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
