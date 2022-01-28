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
public class ViTri {

    private int maVitri;
    private NganKeSach nganKeSach;
    private Sach sach;

    public int getMaVitri() {
        return maVitri;
    }

    public void setMaVitri(int maVitri) {
        this.maVitri = maVitri;
    }

    public NganKeSach getNganKeSach() {
        return nganKeSach;
    }

    public void setNganKeSach(NganKeSach nganKeSach) {
        this.nganKeSach = nganKeSach;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public static List<ViTri> getList() {
        List<ViTri> cacViTri = new ArrayList<>();

        try {
            String query = "SELECT * FROM Vitri";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setMaVitri(rs.getInt("maViTri"));

                Sach sach = Sach.retrieve(rs.getNString("maISBN"));
                viTri.setSach(sach);

                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);

                cacViTri.add(viTri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViTri.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacViTri;
    }

    public static List<ViTri> getList(Sach sach) {
        List<ViTri> cacViTri = new ArrayList<>();

        try {
            String query = "SELECT * FROM Vitri vt JOIN Sach s ON vt.maISBN = s.maISBN WHERE s.maISBN = '" + sach.getMaISBN() + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setMaVitri(rs.getInt("maViTri"));

                Sach sach1 = Sach.retrieve(rs.getNString("maISBN"));
                viTri.setSach(sach1);

                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);

                cacViTri.add(viTri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViTri.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacViTri;
    }

    public static List<ViTri> search(String keyword) {
        List<ViTri> cacViTri = new ArrayList<>();

        try {
            String query = "SELECT * FROM Vitri";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setMaVitri(rs.getInt("maViTri"));

                Sach sach = Sach.retrieve(rs.getNString("maISBN"));
                viTri.setSach(sach);

                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);

                cacViTri.add(viTri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViTri.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacViTri;
    }

    public static boolean insert(ViTri viTri) {
        DBAccess dba = new DBAccess();
        String query = "INSERT INTO Vitri(maNganKe, maISBN) VALUES(N'" + viTri.getNganKeSach().getMaNganKe() + "', N'" + viTri.getSach().getMaISBN() + "')";
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(ViTri viTri) {
        String query = "UPDATE ViTri SET maNganKe = N'" + viTri.getNganKeSach().getMaNganKe() + "', maISBN = '" + viTri.getSach().getMaISBN() + "' WHERE maVitri = N'" + viTri.getMaVitri() + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(ViTri viTri) {
        String query = "DELETE FROM ViTri WHERE maViTri = '" + viTri.getMaVitri() + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
