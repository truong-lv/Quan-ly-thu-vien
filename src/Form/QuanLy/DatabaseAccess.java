/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author t0168
 */
public class DatabaseAccess {

    public List<DoanhThuTheoThoiGian> layDoanhThuTrongThang() throws SQLException {
        List<DoanhThuTheoThoiGian> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        java.util.Date utilDate = new java.util.Date();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 6; i++) {
            localDate = LocalDate.now().minusMonths(i);
            utilDate = java.util.Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
//            System.out.println(utilDate+": util");
            DoanhThuTheoThoiGian dttn = new DoanhThuTheoThoiGian();
            java.sql.Date date = new java.sql.Date(utilDate.getTime());

            dttn.setDate(date);
//            System.out.println(date);

            ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM PhieuMuon WHERE MONTH(ngayMuon)=MONTH(?) AND YEAR(ngayMuon)=YEAR(?)");
            ps.setDate(1, date);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();
            double muon = 0;
            while (rs.next()) {
                muon = rs.getDouble("total");
            }

            dttn.setMuon(muon);

            ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM PhieuThanhToanSach WHERE MONTH(ngayThanhToan)=MONTH(?) AND YEAR(ngayThanhToan)=YEAR(?)");
            ps.setDate(1, date);
            ps.setDate(2, date);

            rs = ps.executeQuery();
            double thanhToan = 0;
            while (rs.next()) {
                thanhToan = rs.getDouble("total");
            }

            dttn.setThanhToan(thanhToan);

            ps = connect.prepareStatement("SELECT SUM(tongTienThanhLy) as total FROM PhieuThanhLy WHERE MONTH(ngayThanhLy)=MONTH(?) AND YEAR(ngayThanhLy)=YEAR(?)");
            ps.setDate(1, date);
            ps.setDate(2, date);
            rs = ps.executeQuery();
            double thanhLy = 0;
            while (rs.next()) {
                thanhLy = rs.getDouble("total");
            }

            dttn.setThanhLy(thanhLy);

            list.add(dttn);
        }

        return list;
    }

    public List<DoanhThuTheoThoiGian> layDoanhThuTrongTuan() throws SQLException {
        List<DoanhThuTheoThoiGian> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        java.util.Date utilDate = new java.util.Date();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            localDate = LocalDate.now().minusDays(i);
            utilDate = java.util.Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
//            System.out.println(utilDate+": util");
            DoanhThuTheoThoiGian dttn = new DoanhThuTheoThoiGian();
            java.sql.Date date = new java.sql.Date(utilDate.getTime());

            dttn.setDate(date);
//            System.out.println(date);

            ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM PhieuMuon WHERE ngayMuon=?");
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            double muon = 0;
            while (rs.next()) {
                muon = rs.getDouble("total");
            }

            dttn.setMuon(muon);

            ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM PhieuThanhToanSach WHERE ngayThanhToan=?");
            ps.setDate(1, date);
            rs = ps.executeQuery();
            double thanhToan = 0;
            while (rs.next()) {
                thanhToan = rs.getDouble("total");
            }

            dttn.setThanhToan(thanhToan);

            ps = connect.prepareStatement("SELECT SUM(tongTienThanhLy) as total FROM PhieuThanhLy WHERE ngayThanhLy=?");
            ps.setDate(1, date);
            rs = ps.executeQuery();
            double thanhLy = 0;
            while (rs.next()) {
                thanhLy = rs.getDouble("total");
            }

            dttn.setThanhLy(thanhLy);

            list.add(dttn);
        }

        return list;
    }

    public double layDoanhThuTheoNgay(String loai, java.util.Date dateToCheck) throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        java.sql.Date date = new Date(dateToCheck.getTime());
        switch (loai) {
            case "muon":
                ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM PhieuMuon WHERE ngayMuon=?");
                ps.setDate(1, date);
                ResultSet rs = ps.executeQuery();
                double sum = 0;
                while (rs.next()) {
                    sum = rs.getDouble("total");
                }
                return sum;
            case "thanh-toan":
                ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM PhieuThanhToanSach WHERE ngayThanhToan=?");
                ps.setDate(1, date);
                rs = ps.executeQuery();
                sum = 0;
                while (rs.next()) {
                    sum = rs.getDouble("total");
                }
                return sum;
            case "thanh-ly":
                ps = connect.prepareStatement("SELECT SUM(tongTienThanhLy) as total FROM PhieuThanhLy WHERE ngayThanhLy=?");
                ps.setDate(1, date);
                rs = ps.executeQuery();
                sum = 0;
                while (rs.next()) {
                    sum = rs.getDouble("total");
                }
                return sum;
        }
        return 0;
    }

    public List<ChiTietDoanhThuTheoThoiGian> layCTDoanhThuTheoNgay(java.util.Date dateToCheck) throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        java.sql.Date date = new Date(dateToCheck.getTime());
        List<ChiTietDoanhThuTheoThoiGian> list = new ArrayList<>();
        ChiTietDoanhThuTheoThoiGian ct_dttg = new ChiTietDoanhThuTheoThoiGian();
        ps = connect.prepareStatement("SELECT maPhieuMuon, tongTien FROM PhieuMuon WHERE ngayMuon=?");
        ps.setDate(1, date);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ct_dttg = new ChiTietDoanhThuTheoThoiGian();
            ct_dttg.setDate(dateToCheck);
            ct_dttg.setTongThu(rs.getDouble("tongTien"));
            ct_dttg.setLoai("Phiếu mượn");
            ct_dttg.setNguon(rs.getString("maPhieuMuon").trim());
            list.add(ct_dttg);
        }

        ps = connect.prepareStatement("SELECT maPhieuThanhToanSach, tongTien FROM PhieuThanhToanSach WHERE ngayThanhToan=?");
        ps.setDate(1, date);
        rs = ps.executeQuery();
        while (rs.next()) {
            ct_dttg = new ChiTietDoanhThuTheoThoiGian();
            ct_dttg.setDate(dateToCheck);
            ct_dttg.setTongThu(rs.getDouble("tongTien"));
            ct_dttg.setLoai("Phiếu thanh toán");
            ct_dttg.setNguon(rs.getString("maPhieuThanhToanSach").trim());
            list.add(ct_dttg);
        }
        ps = connect.prepareStatement("SELECT maPhieuThanhLy, tongTienThanhLy FROM PhieuThanhLy WHERE ngayThanhLy=?");
        ps.setDate(1, date);
        rs = ps.executeQuery();
        while (rs.next()) {
            ct_dttg = new ChiTietDoanhThuTheoThoiGian();
            ct_dttg.setDate(dateToCheck);
            ct_dttg.setTongThu(rs.getDouble("tongTienThanhLy"));
            ct_dttg.setLoai("Phiếu thanh lý");
            ct_dttg.setNguon(rs.getString("maPhieuThanhLy").trim());
            list.add(ct_dttg);
        }
        return list;
    }

    public List<ChiTietDoanhThuTheoThoiGian> layCTDoanhThuTheoTuanGanNhat() throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        List<ChiTietDoanhThuTheoThoiGian> list = new ArrayList<>();
        ChiTietDoanhThuTheoThoiGian ct_dttg = new ChiTietDoanhThuTheoThoiGian();
        java.util.Date utilDate = new java.util.Date();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            localDate = LocalDate.now().minusDays(i);
            utilDate = java.util.Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
            java.sql.Date date = new java.sql.Date(utilDate.getTime());

            ps = connect.prepareStatement("SELECT maPhieuMuon, tongTien FROM PhieuMuon WHERE ngayMuon=?");
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ct_dttg = new ChiTietDoanhThuTheoThoiGian();
                ct_dttg.setDate(utilDate);
                ct_dttg.setTongThu(rs.getDouble("tongTien"));
                ct_dttg.setLoai("Phiếu mượn");
                ct_dttg.setNguon(rs.getString("maPhieuMuon").trim());
                list.add(ct_dttg);
            }

            ps = connect.prepareStatement("SELECT maPhieuThanhToanSach, tongTien FROM PhieuThanhToanSach WHERE ngayThanhToan=?");
            ps.setDate(1, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                ct_dttg = new ChiTietDoanhThuTheoThoiGian();
                ct_dttg.setDate(utilDate);
                ct_dttg.setTongThu(rs.getDouble("tongTien"));
                ct_dttg.setLoai("Phiếu thanh toán");
                ct_dttg.setNguon(rs.getString("maPhieuThanhToanSach").trim());
                list.add(ct_dttg);
            }
            ps = connect.prepareStatement("SELECT maPhieuThanhLy, tongTienThanhLy FROM PhieuThanhLy WHERE ngayThanhLy=?");
            ps.setDate(1, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                ct_dttg = new ChiTietDoanhThuTheoThoiGian();
                ct_dttg.setDate(utilDate);
                ct_dttg.setTongThu(rs.getDouble("tongTienThanhLy"));
                ct_dttg.setLoai("Phiếu thanh lý");
                ct_dttg.setNguon(rs.getString("maPhieuThanhLy").trim());
                list.add(ct_dttg);
            }
        }
        return list;
    }

    ////
    public List<ChiTietDoanhThuTheoThoiGian> layCTDoanhThuTheo6ThangGanNhat() throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        List<ChiTietDoanhThuTheoThoiGian> list = new ArrayList<>();
        ChiTietDoanhThuTheoThoiGian ct_dttg = new ChiTietDoanhThuTheoThoiGian();
        java.util.Date utilDate = new java.util.Date();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 6; i++) {
            localDate = LocalDate.now().minusMonths(i);
            utilDate = java.util.Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
            java.sql.Date date = new java.sql.Date(utilDate.getTime());

            ps = connect.prepareStatement("SELECT maPhieuMuon, tongTien FROM PhieuMuon WHERE MONTH(ngayMuon)=MONTH(?) AND YEAR(ngayMuon)=YEAR(?)");
            ps.setDate(1, date);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ct_dttg = new ChiTietDoanhThuTheoThoiGian();
                ct_dttg.setDate(utilDate);
                ct_dttg.setTongThu(rs.getDouble("tongTien"));
                ct_dttg.setLoai("Phiếu mượn");
                ct_dttg.setNguon(rs.getString("maPhieuMuon").trim());
                list.add(ct_dttg);
            }

            ps = connect.prepareStatement("SELECT maPhieuThanhToanSach, tongTien FROM PhieuThanhToanSach WHERE MONTH(ngayThanhToan)=MONTH(?) AND YEAR(ngayThanhToan)=YEAR(?)");
            ps.setDate(1, date);
            ps.setDate(2, date);

            rs = ps.executeQuery();
            while (rs.next()) {
                ct_dttg = new ChiTietDoanhThuTheoThoiGian();
                ct_dttg.setDate(utilDate);
                ct_dttg.setTongThu(rs.getDouble("tongTien"));
                ct_dttg.setLoai("Phiếu thanh toán");
                ct_dttg.setNguon(rs.getString("maPhieuThanhToanSach").trim());
                list.add(ct_dttg);
            }
            ps = connect.prepareStatement("SELECT maPhieuThanhLy, tongTienThanhLy FROM PhieuThanhLy WHERE MONTH(ngayThanhLy)=MONTH(?) AND YEAR(ngayThanhLy)=YEAR(?)");
            ps.setDate(1, date);
            ps.setDate(2, date);

            rs = ps.executeQuery();
            while (rs.next()) {
                ct_dttg = new ChiTietDoanhThuTheoThoiGian();
                ct_dttg.setDate(utilDate);
                ct_dttg.setTongThu(rs.getDouble("tongTienThanhLy"));
                ct_dttg.setLoai("Phiếu thanh lý");
                ct_dttg.setNguon(rs.getString("maPhieuThanhLy").trim());
                list.add(ct_dttg);
            }
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
//        System.out.println(new DatabaseAccess().layDoanhThuTrongNgay("thanh-ly"));
//        System.out.println(new DatabaseAccess().layDoanhThuTrongTuan());
        System.out.println(new DatabaseAccess().layDoanhThuTrongThang());
    }
}
