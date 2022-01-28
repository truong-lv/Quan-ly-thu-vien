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

}
