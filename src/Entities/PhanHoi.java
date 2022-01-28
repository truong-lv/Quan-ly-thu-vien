/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Form.ThuKho.DBAccess;

/**
 *
 * @author Phat
 */
public class PhanHoi {

    private String maDocGia;
    private String noiDung;

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public static boolean insert(PhanHoi phanhoi) {
        DBAccess dba = new DBAccess();
        String query = "INSERT INTO PhanHoi VALUES(N'1', N'" + phanhoi.noiDung + "')";
        boolean i = dba.Update(query);
        return i;
    }
}
