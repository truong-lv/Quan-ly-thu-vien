/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Phat
 */
public class PhieuMuonTruoc {

    private String maPhieuMuongTruoc;
    private DocGia docgia;
    private Date ngayMuonTruoc;
    private int trangThai;
    private NhanVien nhanVien;

    public String getMaPhieuMuongTruoc() {
        return maPhieuMuongTruoc;
    }

    public void setMaPhieuMuongTruoc(String maPhieuMuongTruoc) {
        this.maPhieuMuongTruoc = maPhieuMuongTruoc;
    }

    public DocGia getDocgia() {
        return docgia;
    }

    public void setDocgia(DocGia docgia) {
        this.docgia = docgia;
    }

    public Date getNgayMuonTruoc() {
        return ngayMuonTruoc;
    }

    public void setNgayMuonTruoc(Date ngayMuonTruoc) {
        this.ngayMuonTruoc = ngayMuonTruoc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public static boolean insert(String maPhieuMuonTruoc, String maDocGia, Date ngayMuonTruoc, int trangThai, String maNV) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String query = "INSERT INTO PHIEUMUONTRUOC VALUES(N'" + maPhieuMuonTruoc + "', N'" + maDocGia + "', N'" + dateformat.format(ngayMuonTruoc) + "', N'" + trangThai + "', N'" + maNV + "' )";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
