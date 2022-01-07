/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public static boolean insert(PhieuMuonTruoc pmt, List<ChiTietMuonTruoc> list) {
        DBAccess dba = new DBAccess();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String maPhieuMuonTruoc = dba.generateId("2");
        String query = "INSERT INTO PHIEUMUONTRUOC VALUES(N'" + maPhieuMuonTruoc + "', N'" + pmt.getDocgia().getMaDocGia() + "', N'" + dateformat.format(pmt.getNgayMuonTruoc()) + "', N'" + pmt.getTrangThai() + "', N'" + pmt.getNhanVien().getMaNhanVien() + "' )";
        boolean i = dba.Update(query);
        if (i == true) {
            i = ChiTietMuonTruoc.insert(maPhieuMuonTruoc, list);
        }
        return i;
    }
}
