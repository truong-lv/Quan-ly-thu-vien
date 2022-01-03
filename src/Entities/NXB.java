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
@Table(name = "NXB")
public class NXB {

    private String maNXB;
    private String tenNXB;

    @OneToMany(mappedBy = "nxb", fetch = FetchType.EAGER)
    private List<Sach> cacSach;

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public List<Sach> getCacSach() {
        return cacSach;
    }

    public void setCacSach(List<Sach> cacSach) {
        this.cacSach = cacSach;
    }

}
