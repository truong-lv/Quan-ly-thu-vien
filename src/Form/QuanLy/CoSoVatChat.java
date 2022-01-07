/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import java.util.Date;

/**
 *
 * @author t0168
 */
public class CoSoVatChat {
    String maCSVC;
    String tenCSVC;
    double gia;
    String maLoaiCSVC;
    Date ngaySanXuat;
    int trangThai;

    public String getMaCSVC() {
        return maCSVC;
    }

    public void setMaCSVC(String maCSVC) {
        this.maCSVC = maCSVC;
    }

    public String getTenCSVC() {
        return tenCSVC;
    }

    public void setTenCSVC(String tenCSVC) {
        this.tenCSVC = tenCSVC;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getMaLoaiCSVC() {
        return maLoaiCSVC;
    }

    public void setMaLoaiCSVC(String maLoaiCSVC) {
        this.maLoaiCSVC = maLoaiCSVC;
    }

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "CoSoVatChat{" + "maCSVC=" + maCSVC + ", tenCSVC=" + tenCSVC + ", gia=" + gia + ", maLoaiCSVC=" + maLoaiCSVC + ", ngaySanXuat=" + ngaySanXuat + ", trangThai=" + trangThai + '}';
    }
    
    
}
