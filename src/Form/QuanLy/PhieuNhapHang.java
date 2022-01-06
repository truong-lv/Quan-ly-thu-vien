/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author t0168
 */
public class PhieuNhapHang {
    String maPhieuNhapHang;
    String maNCC;
    String maNV_QuanLy;
    String maNV_ThuKho;
    Date ngayNhap;
    int trangThai;

    ArrayList<CT_PhieuNhapHang> listCT_PhieuNhapHang = new ArrayList<>();
    
    public String getMaPhieuNhapHang() {
        return maPhieuNhapHang;
    }

    public void setMaPhieuNhapHang(String maPhieuNhapHang) {
        this.maPhieuNhapHang = maPhieuNhapHang;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaNV_QuanLy() {
        return maNV_QuanLy;
    }

    public void setMaNV_QuanLy(String maNV_QuanLy) {
        this.maNV_QuanLy = maNV_QuanLy;
    }

    public String getMaNV_ThuKho() {
        return maNV_ThuKho;
    }

    public void setMaNV_ThuKho(String maNV_ThuKho) {
        this.maNV_ThuKho = maNV_ThuKho;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "PhieuNhapHang{" + "maPhieuNhapHang=" + maPhieuNhapHang + ", maNCC=" + maNCC + ", maNV_QuanLy=" + maNV_QuanLy + ", maNV_ThuKho=" + maNV_ThuKho + ", ngayNhap=" + ngayNhap + ", trangThai=" + trangThai + ", listCT_PhieuNhapHang=" + listCT_PhieuNhapHang + '}';
    }

    public ArrayList<CT_PhieuNhapHang> getListCT_PhieuNhapHang() {
        return listCT_PhieuNhapHang;
    }

    public void setListCT_PhieuNhapHang(ArrayList<CT_PhieuNhapHang> listCT_PhieuNhapHang) {
        this.listCT_PhieuNhapHang = listCT_PhieuNhapHang;
    }
    
}
