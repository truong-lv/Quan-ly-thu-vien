/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
@Table(name = "NganKeSach")
public class NganKeSach {

    private String maNganKe;
    private String tenNgan;
    private String maKe;

    @ManyToOne()
    @JoinColumn(name = "maKe")
    private KeSach keSach;

    @OneToMany(mappedBy = "sach", fetch = FetchType.EAGER)
    private List<Sach> cacSach;

    public String getMaNganKe() {
        return maNganKe;
    }

    public void setMaNganKe(String maNganKe) {
        this.maNganKe = maNganKe;
    }

    public String getTenNgan() {
        return tenNgan;
    }

    public void setTenNgan(String tenNgan) {
        this.tenNgan = tenNgan;
    }

    public String getMaKe() {
        return maKe;
    }

    public void setMaKe(String maKe) {
        this.maKe = maKe;
    }

    public KeSach getKeSach() {
        return keSach;
    }

    public void setKeSach(KeSach keSach) {
        this.keSach = keSach;
    }

}
