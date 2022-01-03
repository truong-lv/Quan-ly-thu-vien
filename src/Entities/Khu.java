/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
@Table(name = "Khu")
public class Khu {

    private String maKhu;
    private String tenKhu;
    private String moTa;
    private String tang;

    @OneToMany(mappedBy = "khu", fetch = FetchType.EAGER)
    private List<KeSach> cacKeSach;

    public List<KeSach> getCacKeSach() {
        return cacKeSach;
    }

    public void setCacKeSach(List<KeSach> cacKeSach) {
        this.cacKeSach = cacKeSach;
    }

    public String getMaKhu() {
        return maKhu;
    }

    public void setMaKhu(String maKhu) {
        this.maKhu = maKhu;
    }

    public String getTenKhu() {
        return tenKhu;
    }

    public void setTenKhu(String tenKhu) {
        this.tenKhu = tenKhu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTang() {
        return tang;
    }

    public void setTang(String tang) {
        this.tang = tang;
    }

}
