/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phat
 */
public class CoSoVatChat {

    private String maCSVC;
    private String tenCSVC;
    private float gia;
    private Date ngaySanXuat;
    private int trangThai;
    private LoaiCoSoVatChat loaiCSVC;

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

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
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

    public LoaiCoSoVatChat getLoaiCSVC() {
        return loaiCSVC;
    }

    public void setLoaiCSVC(LoaiCoSoVatChat loaiCSVC) {
        this.loaiCSVC = loaiCSVC;
    }

    public static List<CoSoVatChat> getList() {
        List<CoSoVatChat> cacCoSoVatChat = new ArrayList<>();

        try {
            String query = "SELECT * FROM CoSoVatChat";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                CoSoVatChat coSoVatChat = new CoSoVatChat();
                coSoVatChat.setMaCSVC(rs.getNString("maCSVC"));
                coSoVatChat.setTenCSVC(rs.getNString("tenCSVC"));
                coSoVatChat.setGia(rs.getFloat("gia"));
                coSoVatChat.setNgaySanXuat(rs.getDate("ngaySanXuat"));
                coSoVatChat.setTrangThai(rs.getInt("trangThai"));

                LoaiCoSoVatChat loaiCSVC = LoaiCoSoVatChat.retrieve(rs.getNString("maLoaiCSVC"));
                coSoVatChat.setLoaiCSVC(loaiCSVC);

                cacCoSoVatChat.add(coSoVatChat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoSoVatChat.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacCoSoVatChat;
    }

    public static List<CoSoVatChat> search(String keyword) {
        List<CoSoVatChat> cacCoSoVatChat = new ArrayList<>();

        try {
            String query = "SELECT * FROM CoSoVatChat WHERE maCSVC LIKE N'%" + keyword + "%' OR tenCSVC LIKE N'%" + keyword + "%' OR gia LIKE N'%" + keyword + "%' OR trangThai LIKE N'%" + keyword + "%' OR ngaySanXuat LIKE N'%" + keyword + "%' OR maLoaiCSVC LIKE N'%" + keyword + "%'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                CoSoVatChat coSoVatChat = new CoSoVatChat();
                coSoVatChat.setMaCSVC(rs.getNString("maCSVC"));
                coSoVatChat.setTenCSVC(rs.getNString("tenCSVC"));
                coSoVatChat.setGia(rs.getFloat("gia"));
                coSoVatChat.setNgaySanXuat(rs.getDate("ngaySanXuat"));
                coSoVatChat.setTrangThai(rs.getInt("trangThai"));

                LoaiCoSoVatChat loaiCSVC = LoaiCoSoVatChat.retrieve(rs.getNString("maLoaiCSVC"));
                coSoVatChat.setLoaiCSVC(loaiCSVC);

                cacCoSoVatChat.add(coSoVatChat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoSoVatChat.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacCoSoVatChat;
    }

    public static boolean insert(String maCSVC, String tenCSVC, float gia, String maLoaiCSVC, Date ngaySanXuat, int trangThai) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String query = "INSERT INTO CoSoVatChat VALUES(N'" + maCSVC + "', N'" + tenCSVC + "', N'" + gia + "', N'" + maLoaiCSVC + "', N'" + dateFormat.format(ngaySanXuat) + "', N'" + trangThai + "')";
        System.out.println(query);
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(String maCSVC, String tenCSVC, float gia, String maLoaiCSVC, Date ngaySanXuat, int trangThai) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String query = "UPDATE CoSoVatChat SET tenCSVC = N'" + tenCSVC + "', gia = N'" + gia + "', maLoaiCSVC = N'" + maLoaiCSVC + "', ngaySanXuat = N'" + dateFormat.format(ngaySanXuat) + "', trangThai = N'" + trangThai + "' WHERE maCSVC = N'" + maCSVC + "'";
//        System.out.println(query);
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(String maCSVC) {
        String query = "DELETE FROM CoSoVatChat WHERE maCSVC = N'" + maCSVC + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

}
