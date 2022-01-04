/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Phat
 */
@Table(name = "Sach")
public class Sach {

    private String maISBN;
    private String tenSach;
    private LocalDate namXB;
    private float giaBia;
    private int soTrang;
    private String moTa;
    private int soLuong;
    private int soLuongCon;

    public void setMaISBN(String maISBN) {
        this.maISBN = maISBN;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setNamXB(LocalDate namXB) {
        this.namXB = namXB;
    }

    public void setGiaBia(float giaBia) {
        this.giaBia = giaBia;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

//    @ManyToOne()
//    @JoinColumn(name = "maNganKe")
//    private NganKeSach nganKeSach;
//
//    @ManyToOne()
//    @JoinColumn(name = "maTacGia")
//    private TacGia tacGia;
//
//    @ManyToOne()
//    @JoinColumn(name = "maNXb")
//    private NXB nxb;
//
//    @ManyToOne()
//    @JoinColumn(name = "maTheLoai")
//    private TheLoaiSach theLoaiSach;
//    public TacGia getTacGia() {
//        return tacGia;
//    }
//
//    public void setTacGia(TacGia tacGia) {
//        this.tacGia = tacGia;
//    }
//
//    public NXB getNxb() {
//        return nxb;
//    }
//
//    public void setNxb(NXB nxb) {
//        this.nxb = nxb;
//    }
//
//    public TheLoaiSach getTheLoaiSach() {
//        return theLoaiSach;
//    }
//
//    public void setTheLoaiSach(TheLoaiSach theLoaiSach) {
//        this.theLoaiSach = theLoaiSach;
//    }
//
//    public NganKeSach getNganKeSach() {
//        return nganKeSach;
//    }
//
//    public void setNganKeSach(NganKeSach nganKeSach) {
//        this.nganKeSach = nganKeSach;
//    }
    
    
    public String getMaISBN() {
        return maISBN;
    }

    public String getTenSach() {
        return tenSach;
    }

    public LocalDate getNamXB() {
        return namXB;
    }

    public float getGiaBia() {
        return giaBia;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getSoLuongCon() {
        return soLuongCon;
    }
    
    public static List<Sach> getList() {
        List<Sach> cacSach = null;
        
        try {
            String query = "SELECT * FROM Sach";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while(rs.next()) {
                Sach Sach = new Sach();
                Sach.setMaISBN(rs.getNString("maISBN"));
                
                cacSach.add(Sach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cacSach;
    }

}
