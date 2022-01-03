/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
@Table(name = "TheLoaiSach")
public class TheLoaiSach {

    public String maTheLoai;
    public String tenTheLoai;

    @OneToMany(mappedBy = "theLoaiSach", fetch = FetchType.EAGER)
    private List<Sach> cacSach;

    public List<Sach> getCacSach() {
        return cacSach;
    }

    public void setCacSach(List<Sach> cacSach) {
        this.cacSach = cacSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

}
