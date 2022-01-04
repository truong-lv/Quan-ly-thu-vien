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
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
//@Table(name = "TacGia")
public class TacGia {

    private String maTacGia;
    private String tenTacGia;

//    @OneToMany(mappedBy = "tacGia", fetch = FetchType.EAGER)
//    private List<Sach> cacSach;
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
            String query = "SELECT * FROM TacGia";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                TacGia tacGia = new TacGia();
                tacGia.setMaTacGia(rs.getNString("maTacGia"));
                tacGia.setTenTacGia(rs.getNString("tenTacGia"));
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
            String query = "SELECT * FROM TacGia WHERE maTacGia LIKE N'%" + keyword + "%' OR tenTacGia LIKE N'%" + keyword + "%'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                TacGia tacGia = new TacGia();
                tacGia.setMaTacGia(rs.getNString("maTacGia"));
                tacGia.setTenTacGia(rs.getNString("tenTacGia"));
                cacTacGia.add(tacGia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacGia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacTacGia;
    }

    public static boolean insert(String maTacGia, String tenTacGia) {
        String query = "INSERT INTO TACGIA VALUES(N'" + maTacGia + "', N'" + tenTacGia + "')";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maTacGia, String tenTacGia) {
        String query = "UPDATE TACGIA SET tenTacGia = N'" + tenTacGia + "' WHERE maTacGia = N'" + maTacGia + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maTacGia) {
        String query = "DELETE FROM TACGIA WHERE maTacGia = '" + maTacGia + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

}
