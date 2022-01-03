/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
@Table(name = "KeSach")
public class KeSach {

    private String maKe;
    private String tenKe;
    private String thongKe;

    @ManyToOne()
    @JoinColumn(name = "maKhu")
    private Khu khu;

    @OneToMany(mappedBy = "keSach", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NganKeSach> cacNganKeSach;

    public List<NganKeSach> getCacNganKeSach() {
        return cacNganKeSach;
    }

    public void setCacNganKeSach(List<NganKeSach> cacNganKeSach) {
        this.cacNganKeSach = cacNganKeSach;
    }

    public String getMaKe() {
        return maKe;
    }

    public void setMaKe(String maKe) {
        this.maKe = maKe;
    }

    public String getTenKe() {
        return tenKe;
    }

    public void setTenKe(String tenKe) {
        this.tenKe = tenKe;
    }

    public String getThongKe() {
        return thongKe;
    }

    public void setThongKe(String thongKe) {
        this.thongKe = thongKe;
    }

    public Khu getKhu() {
        return khu;
    }

    public void setKhu(Khu khu) {
        this.khu = khu;
    }

}
