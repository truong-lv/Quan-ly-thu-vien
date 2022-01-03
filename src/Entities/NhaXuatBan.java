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
//@Table(name = "NXB")
public class NhaXuatBan {

    private String maNXB;
    private String tenNXB;

//    @OneToMany(mappedBy = "nxb", fetch = FetchType.EAGER)
//    private List<Sach> cacSach;
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

//    public List<Sach> getCacSach() {
//        return cacSach;
//    }
//
//    public void setCacSach(List<Sach> cacSach) {
//        this.cacSach = cacSach;
//    }
    public static List<NhaXuatBan> getList() {
        List<NhaXuatBan> cacNXB = new ArrayList<>();

        try {
            String query = "SELECT * FROM NhaXuatBan";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getNString("maNXB"));
                nxb.setTenNXB(rs.getNString("tenNXB"));
                cacNXB.add(nxb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaXuatBan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacNXB;
    }

    public static List<NhaXuatBan> search(String keyword) {
        List<NhaXuatBan> cacNXB = new ArrayList<>();

        try {
            String query = "SELECT * FROM NhaXuatBan WHERE maNXB LIKE N'%" + keyword + "%' OR tenNXB LIKE N'%" + keyword + "%'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setMaNXB(rs.getNString("maNXB"));
                nxb.setTenNXB(rs.getNString("tenNXB"));
                cacNXB.add(nxb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaXuatBan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacNXB;
    }

    public static boolean insert(String maNXB, String tenNXB) {
        String query = "INSERT INTO NhaXuatBan VALUES(N'" + maNXB + "', N'" + tenNXB + "')";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maNXB, String tenNXB) {
        String query = "UPDATE NhaXuatBan SET tenNXB = N'" + tenNXB + "' WHERE maNXB = N'" + maNXB + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maNXB) {
        String query = "DELETE FROM NhaXuatBan WHERE maNXB = '" + maNXB + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
