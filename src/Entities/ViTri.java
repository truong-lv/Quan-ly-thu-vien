/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Form.ThuKho.DBAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phat
 */
public class ViTri {

    private int maVitri;
    private NganKeSach nganKeSach;
    private Sach sach;

    public int getMaVitri() {
        return maVitri;
    }

    public void setMaVitri(int maVitri) {
        this.maVitri = maVitri;
    }

    public NganKeSach getNganKeSach() {
        return nganKeSach;
    }

    public void setNganKeSach(NganKeSach nganKeSach) {
        this.nganKeSach = nganKeSach;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public static List<ViTri> getList() {
        List<ViTri> cacViTri = new ArrayList<>();

        try {
            String query = "SELECT * FROM Vitri";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setMaVitri(rs.getInt("maViTri"));

                Sach sach = Sach.retrieve(rs.getNString("maISBN"));
                viTri.setSach(sach);

                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);

                cacViTri.add(viTri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViTri.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacViTri;
    }

    public static List<ViTri> getList(Sach sach) {
        List<ViTri> cacViTri = new ArrayList<>();

        try {
            String query = "SELECT * FROM Vitri vt JOIN Sach s ON vt.maISBN = s.maISBN WHERE s.maISBN = '" + sach.getMaISBN() + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setMaVitri(rs.getInt("maViTri"));

                Sach sach1 = Sach.retrieve(rs.getNString("maISBN"));
                viTri.setSach(sach1);

                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);

                cacViTri.add(viTri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViTri.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacViTri;
    }

    public static List<ViTri> getList2(Sach sach) {
        List<ViTri> cacViTri = new ArrayList<>();

        try {
            String query = "SELECT * FROM Vitri vt JOIN Sach s ON vt.maISBN = s.maISBN WHERE s.maISBN = '" + sach.getMaISBN() + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setMaVitri(rs.getInt("maViTri"));

//                Sach sach1 = Sach.retrieve(rs.getNString("maISBN"));
//                viTri.setSach(sach1);
                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);

                cacViTri.add(viTri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViTri.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacViTri;
    }

    public static List<ViTri> search(String keyword) {
        List<ViTri> cacViTri = new ArrayList<>();

        try {
            String query = "SELECT * FROM Vitri";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setMaVitri(rs.getInt("maViTri"));

                Sach sach = Sach.retrieve(rs.getNString("maISBN"));
                viTri.setSach(sach);

                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);

                cacViTri.add(viTri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViTri.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cacViTri;
    }

    public static List<ViTri> search(Sach sach, Khu khu, KeSach keSach, NganKeSach nganKeSach, String keyword) {
        List<ViTri> cacViTri = new ArrayList<>();

        List<String> conditions = new ArrayList<>();
        if (sach.getTacGia() != null) {
            conditions.add("tg.tenChiTiet LIKE N'%" + sach.getTacGia().getTenTacGia() + "%'");
        }
        if (sach.getNhaXuatBan() != null) {
            conditions.add("nxb.tenChiTiet LIKE N'%" + sach.getNhaXuatBan().getTenNXB() + "%'");
        }
        if (sach.getTheLoaiSach() != null) {
            conditions.add("tls.tenChiTiet LIKE N'%" + sach.getTheLoaiSach().getTenTheLoai() + "%'");
        }
        if (khu != null) {
            conditions.add("k.tenKhu LIKE N'%" + khu.getTenKhu() + "%'");
        }
        if (keSach != null) {
            conditions.add("ks.tenKe LIKE N'%" + keSach.getTenKe() + "%'");
        }
        if (nganKeSach != null) {
            conditions.add("nks.tenNgan LIKE N'%" + nganKeSach.getTenNgan() + "%'");
        }

//        if (keSach != null) {
//            conditions.add("ks.tenKeSach LIKE N'%" + keSach.getTenKe() + "%'");
//        }
        String condition = String.join(" AND ", conditions);
        if (!keyword.isEmpty()) {
            condition += "OR s.maISBN LIKE N'%" + keyword + "%' OR s.tenSach LIKE N'%" + keyword + "%' OR s.giaBia LIKE N'%" + keyword + "%' OR s.soTrang LIKE N'%" + keyword + "%' OR s.moTa LIKE N'%" + keyword + "%' OR s.soLuongCon LIKE N'%" + keyword + "%'";
        }

        try {
            String query = "SELECT * FROM Sach s JOIN ChiTietSach tg ON s.maTacGia = tg.maChiTiet JOIN ChiTietSach nxb ON s.maNXB = nxb.maChiTiet JOIN ChiTietSach tls ON s.maTheLoai = tls.maChiTiet JOIN ViTri vt ON s.maISBN = vt.maISBN JOIN NganKeSach nks ON vt.maNganKe = nks.maNganKe JOIN KeSach ks ON ks.maKe = nks.maKe JOIN Khu k ON ks.maKhu = k.maKhu  WHERE " + condition;
            //System.out.println(query);
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs != null) {

                while (rs.next()) {
                    Sach s = new Sach();
                    s.setMaISBN(rs.getNString("maISBN"));
                    s.setTenSach(rs.getNString("tenSach"));

                    TacGia tacGia = TacGia.retrieve(rs.getNString("maTacGia"));
                    s.setTacGia(tacGia);
                    NhaXuatBan nxb = NhaXuatBan.retrieve(rs.getNString("maNXB"));
                    s.setNhaXuatBan(nxb);

                    s.setNamXB(rs.getDate("namXB"));
                    s.setGiaBia(rs.getFloat("giaBia"));
                    s.setSoTrang(rs.getInt("soTrang"));
                    s.setMoTa(rs.getNString("moTa"));

                    TheLoaiSach theLoaiSach = TheLoaiSach.retrieve(rs.getNString("maTheLoai"));
                    s.setTheLoaiSach(theLoaiSach);

                    s.setSoLuong(rs.getInt("soLuong"));
                    s.setSoLuongCon(rs.getInt("soLuongCon"));

                    ViTri vitri = new ViTri();
                    vitri.setMaVitri(rs.getInt("maViTri"));
                    vitri.setSach(sach);
                    NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                    vitri.setNganKeSach(nks);

                    cacViTri.add(vitri);
                }
            } else {
                cacViTri = ViTri.getList();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sach.class.getName()).log(Level.SEVERE, null, ex);
            cacViTri = ViTri.getList();
        }

        return cacViTri;
    }

    public static ViTri retrieve(int maViTri) {
        ViTri viTri = null;

        try {
            String query = "SELECT * FROM ViTri WHERE maViTri = '" + maViTri + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                viTri = new ViTri();

                Sach sach = Sach.retrieve(rs.getNString("maISBN"));
                viTri.setSach(sach);

                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viTri;
    }
    
    public static ViTri retrieve2(int maViTri) {
        ViTri viTri = null;

        try {
            String query = "SELECT * FROM ViTri WHERE maViTri = '" + maViTri + "'";
            DBAccess dba = new DBAccess();
            ResultSet rs = dba.Query(query);
            if (rs.next()) {
                viTri = new ViTri();

//                Sach sach = Sach.retrieve(rs.getNString("maISBN"));
//                viTri.setSach(sach);

                NganKeSach nks = NganKeSach.retrieve(rs.getNString("maNganKe"));
                viTri.setNganKeSach(nks);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viTri;
    }

    public static boolean insert(ViTri viTri) {
        DBAccess dba = new DBAccess();
        String query = "INSERT INTO Vitri(maNganKe, maISBN) VALUES(N'" + viTri.getNganKeSach().getMaNganKe() + "', N'" + viTri.getSach().getMaISBN() + "')";
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean update(ViTri viTri) {
        String query = "UPDATE ViTri SET maNganKe = N'" + viTri.getNganKeSach().getMaNganKe() + "', maISBN = '" + viTri.getSach().getMaISBN() + "' WHERE maVitri = N'" + viTri.getMaVitri() + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }

    public static boolean delete(ViTri viTri) {
        String query = "DELETE FROM ViTri WHERE maViTri = '" + viTri.getMaVitri() + "'";
        DBAccess dba = new DBAccess();
        boolean i = dba.Update(query);
        return i;
    }
}
