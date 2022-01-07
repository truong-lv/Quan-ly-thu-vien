/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.util.List;

/**
 *
 * @author Phat
 */
public class ChiTietMuonTruoc {

    private int maCTPhieuMuon;
    private PhieuMuonTruoc phieuMuonTruoc;
    private Sach sach;
    private int soLuong;

    public int getMaCTPhieuMuon() {
        return maCTPhieuMuon;
    }

    public void setMaCTPhieuMuon(int maCTPhieuMuon) {
        this.maCTPhieuMuon = maCTPhieuMuon;
    }

    public PhieuMuonTruoc getPhieuMuonTruoc() {
        return phieuMuonTruoc;
    }

    public void setPhieuMuonTruoc(PhieuMuonTruoc phieuMuonTruoc) {
        this.phieuMuonTruoc = phieuMuonTruoc;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public static boolean insert(List<ChiTietMuonTruoc> listChiTietMuonTruoc) {
        boolean result = true;
        int i = 0;
        for (; i < listChiTietMuonTruoc.size(); i++) {
            ChiTietMuonTruoc ctmt = listChiTietMuonTruoc.get(i);
            String query = "INSERT INTO CHITIETMUONTRUOC(maPhieuMuonTruoc, maSach, soLuong) VALUES(N'" + ctmt.getPhieuMuonTruoc().getMaPhieuMuongTruoc() + "', N'" + ctmt.getSach().getMaISBN() + "', N'" + ctmt.getSoLuong() + "')";
            DBAccess dba = new DBAccess();
            result = dba.Update(query);
            if (result == false) {
                break;
            }
        }

        if (result == false) {
            ChiTietMuonTruoc ctmt = listChiTietMuonTruoc.get(0);
            String query = "DELETE FROM PHIEUMUONTRUOC WHERE maPhieuMuonTruoc = '" + ctmt.getPhieuMuonTruoc().getMaPhieuMuongTruoc() + "'";
            DBAccess dba = new DBAccess();
            dba.Update(query);
        }

        return result;
    }
}
