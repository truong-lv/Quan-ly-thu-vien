/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

/**
 *
 * @author t0168
 */
public class CT_PhieuNhapHang {
    int maCTPhieuNhapHang;
    String maPhieuNhapHang;
    String maCSVC;
    int soLuongCSVC;
    String maSach;
    int soLuongSach;
    double tongTien;

    public int getMaCTPhieuNhapHang() {
        return maCTPhieuNhapHang;
    }

    public void setMaCTPhieuNhapHang(int maCTPhieuNhapHang) {
        this.maCTPhieuNhapHang = maCTPhieuNhapHang;
    }

    public String getMaPhieuNhapHang() {
        return maPhieuNhapHang;
    }

    public void setMaPhieuNhapHang(String maPhieuNhapHang) {
        this.maPhieuNhapHang = maPhieuNhapHang;
    }

    public String getMaCSVC() {
        return maCSVC;
    }

    public void setMaCSVC(String maCSVC) {
        this.maCSVC = maCSVC;
    }

    public int getSoLuongCSVC() {
        return soLuongCSVC;
    }

    public void setSoLuongCSVC(int soLuongCSVC) {
        this.soLuongCSVC = soLuongCSVC;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "CT_PhieuNhapHang{" + "maCTPhieuNhapHang=" + maCTPhieuNhapHang + ", maPhieuNhapHang=" + maPhieuNhapHang + ", maCSVC=" + maCSVC + ", soLuongCSVC=" + soLuongCSVC + ", maSach=" + maSach + ", soLuongSach=" + soLuongSach + ", tongTien=" + tongTien + '}';
    }
    
    
}
