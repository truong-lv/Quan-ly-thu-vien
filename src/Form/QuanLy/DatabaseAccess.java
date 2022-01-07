/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import Code.ThuVien;
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
    
public boolean xoaPhieuNhap(String maPhieuNhapHang) throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        ps = connect.prepareStatement("DELETE FROM CT_PhieuNhapHang WHERE maPhieuNhapHang=?");
        ps.setString(1, maPhieuNhapHang);
        
        if (ps.executeUpdate() > 0) {
            ps = connect.prepareStatement("DELETE FROM PhieuNhapHang WHERE maPhieuNhapHang=?");
            ps.setString(1, maPhieuNhapHang);
            return ps.executeUpdate() > 0;
        }
        return false;
    }

    public boolean suaPhieuNhap(PhieuNhapHang pnh) throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        ps = connect.prepareStatement("UPDATE PhieuNhapHang SET maNCC=?, trangThai=? WHERE maPhieuNhapHang=?");
        ps.setString(1, pnh.getMaNCC());
        ps.setInt(2, pnh.getTrangThai());
        ps.setString(3, pnh.getMaPhieuNhapHang());

        if (ps.executeUpdate() > 0) {
            ps = connect.prepareStatement("UPDATE CT_PhieuNhapHang SET maCSVC=?, soLuongCSVC=?, maSach=?, soLuongSach=?, tongTien=? WHERE maPhieuNhapHang=?");
            ps.setString(1, pnh.getListCT_PhieuNhapHang().get(0).getMaCSVC());
            ps.setInt(2, pnh.getListCT_PhieuNhapHang().get(0).getSoLuongCSVC());
            ps.setString(3, pnh.getListCT_PhieuNhapHang().get(0).getMaSach());
            ps.setInt(4, pnh.getListCT_PhieuNhapHang().get(0).getSoLuongSach());
            ps.setDouble(5, pnh.getListCT_PhieuNhapHang().get(0).getTongTien());
            ps.setString(6, pnh.getMaPhieuNhapHang());
            return ps.executeUpdate() > 0;
        }

        return false;
    }

    public boolean themPhieuNhap(PhieuNhapHang pnh) throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        ps = connect.prepareStatement("INSERT INTO PhieuNhapHang VALUES(?,?,?,?,?,?)");
        ps.setString(1, pnh.getMaPhieuNhapHang());
        ps.setString(2, pnh.getMaNCC());
        ps.setString(3, pnh.getMaNV_QuanLy());
        ps.setString(4, pnh.getMaNV_ThuKho());
        ps.setDate(5, new java.sql.Date(pnh.getNgayNhap().getTime()));
        ps.setInt(6, pnh.getTrangThai());

        if (ps.executeUpdate() > 0) {
            ps = connect.prepareStatement("INSERT INTO CT_PhieuNhapHang(maPhieuNhapHang, maCSVC, soLuongCSVC, maSach, soLuongSach, tongTien) VALUES(?,?,?,?,?,?)");
//            ps.setInt(1, pnh.getListCT_PhieuNhapHang().get(0).getMaCTPhieuNhapHang());
            ps.setString(1, pnh.getMaPhieuNhapHang());
            ps.setString(2, pnh.getListCT_PhieuNhapHang().get(0).getMaCSVC());
            ps.setInt(3, pnh.getListCT_PhieuNhapHang().get(0).getSoLuongCSVC());
            ps.setString(4, pnh.getListCT_PhieuNhapHang().get(0).getMaSach());
            ps.setInt(5, pnh.getListCT_PhieuNhapHang().get(0).getSoLuongSach());
            ps.setDouble(6, pnh.getListCT_PhieuNhapHang().get(0).getTongTien());

            return ps.executeUpdate() > 0;
        }

        return false;
    }

    public String layMaPhieuNhapKeTiep() throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        ps = connect.prepareStatement("SELECT MAX(MaPhieuNhapHang) as M FROM PhieuNhapHang");

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int currentMax = Integer.parseInt(rs.getString("M").substring(2));
            if (currentMax < 9) {
                return "NH0" + (currentMax + 1);
            }
            return "NH" + (currentMax + 1);
        } else {
            return "NH01";
        }
    }

    public List<Sach> laySach(String maISBN) throws SQLException {
        List<Sach> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        ps = connect.prepareStatement("SELECT * FROM Sach");
        if (!maISBN.equalsIgnoreCase("all")) {
            ps = connect.prepareStatement("SELECT * FROM Sach WHERE maISBN=?");
            ps.setString(1, maISBN);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Sach sach = new Sach();
            sach.setMaISBN(rs.getString("maISBN"));
            sach.setTenSach(rs.getString("tenSach"));
            sach.setGiaBia(rs.getDouble("giaBia"));

            list.add(sach);
        }
        return list;
    }

    public List<NhaCungCap> layNhaCungCap(String maNCC) throws SQLException {
        List<NhaCungCap> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        ps = connect.prepareStatement("SELECT * FROM NhaCungCap");
        if (!maNCC.equalsIgnoreCase("all")) {
            ps = connect.prepareStatement("SELECT * FROM NhaCungCap WHERE maNCC=?");
            ps.setString(1, maNCC);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNCC(rs.getString("maNCC"));
            ncc.setTenNhaCungCap(rs.getString("tenNhaCungCap"));

            list.add(ncc);
        }
        return list;
    }

    public List<CoSoVatChat> layCoSoVatChat(String maCSVC) throws SQLException {
        List<CoSoVatChat> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        ps = connect.prepareStatement("SELECT * FROM CoSoVatChat");
        if (!maCSVC.equalsIgnoreCase("all")) {
            ps = connect.prepareStatement("SELECT * FROM CoSoVatChat WHERE maCSVC=?");
            ps.setString(1, maCSVC);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CoSoVatChat csvc = new CoSoVatChat();
            csvc.setMaCSVC(rs.getString("maCSVC"));
            csvc.setTenCSVC(rs.getString("tenCSVC"));
            csvc.setGia(rs.getDouble("gia"));
            csvc.setMaLoaiCSVC(rs.getString("maLoaiCSVC"));
            csvc.setNgaySanXuat(rs.getDate("ngaySanXuat"));
            csvc.setTrangThai(rs.getInt("trangThai"));

            list.add(csvc);
        }
        return list;
    }

    public List<PhieuNhapHang> layPhieuNhapHang() throws SQLException {
        List<PhieuNhapHang> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ps = connect.prepareStatement("SELECT * FROM PhieuNhapHang");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PhieuNhapHang pnh = new PhieuNhapHang();
            pnh.setMaPhieuNhapHang(rs.getString("maPhieuNhapHang"));
            pnh.setMaNCC(rs.getString("maNCC"));
            pnh.setMaNV_QuanLy(rs.getString("maNV_QuanLy"));
            pnh.setMaNV_ThuKho(rs.getString("maNV_ThuKho"));
            pnh.setNgayNhap(rs.getDate("ngayNhap"));
            pnh.setTrangThai(rs.getInt("trangThai"));

            ps2 = connect.prepareStatement("SELECT * FROM CT_PhieuNhapHang WHERE maPhieuNhapHang = ?");
            ps2.setString(1, pnh.getMaPhieuNhapHang());

            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                CT_PhieuNhapHang ct_pnh = new CT_PhieuNhapHang();
                ct_pnh.setMaCTPhieuNhapHang(rs2.getInt("maCTPhieuNhapHang"));
                ct_pnh.setMaPhieuNhapHang(rs2.getString("maPhieuNhapHang"));
                ct_pnh.setMaCSVC(rs2.getString("maCSVC"));
                ct_pnh.setSoLuongCSVC(rs2.getInt("soLuongCSVC"));
                ct_pnh.setMaSach(rs2.getString("maSach"));
                ct_pnh.setSoLuongSach(rs2.getInt("soLuongSach"));
                ct_pnh.setTongTien(rs2.getDouble("tongTien"));

                pnh.getListCT_PhieuNhapHang().add(ct_pnh);
            }

            list.add(pnh);
        }
        return list;
    }

    public PhieuNhapHang layPhieuNhapHang(String maPhieuNhapHang) throws SQLException {
        PhieuNhapHang pnh = new PhieuNhapHang();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ps = connect.prepareStatement("SELECT * FROM PhieuNhapHang WHERE maPhieuNhapHang=?");
        ps.setString(1, maPhieuNhapHang);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pnh.setMaPhieuNhapHang(rs.getString("maPhieuNhapHang"));
            pnh.setMaNCC(rs.getString("maNCC"));
            pnh.setMaNV_QuanLy(rs.getString("maNV_QuanLy"));
            pnh.setMaNV_ThuKho(rs.getString("maNV_ThuKho"));
            pnh.setNgayNhap(rs.getDate("ngayNhap"));
            pnh.setTrangThai(rs.getInt("trangThai"));

            ps2 = connect.prepareStatement("SELECT * FROM CT_PhieuNhapHang WHERE maPhieuNhapHang = ?");
            ps2.setString(1, pnh.getMaPhieuNhapHang());

            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                CT_PhieuNhapHang ct_pnh = new CT_PhieuNhapHang();
                ct_pnh.setMaCTPhieuNhapHang(rs2.getInt("maCTPhieuNhapHang"));
                ct_pnh.setMaPhieuNhapHang(rs2.getString("maPhieuNhapHang"));
                ct_pnh.setMaCSVC(rs2.getString("maCSVC"));
                ct_pnh.setSoLuongCSVC(rs2.getInt("soLuongCSVC"));
                ct_pnh.setMaSach(rs2.getString("maSach"));
                ct_pnh.setSoLuongSach(rs2.getInt("soLuongSach"));
                ct_pnh.setTongTien(rs2.getDouble("tongTien"));

                pnh.getListCT_PhieuNhapHang().add(ct_pnh);
            }

        }
        return pnh;
    }

    public List<TongChiPhiTheoThoiGian> layChiPhiTheoNgay(java.util.Date dateToCheck) throws SQLException {
        List<TongChiPhiTheoThoiGian> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        java.sql.Date date = new Date(dateToCheck.getTime());
        ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM CT_PhieuNhapHang INNER JOIN PhieuNhapHang ON CT_PhieuNhapHang.maPhieuNhapHang = PhieuNhapHang.maPhieuNhapHang WHERE ngayNhap=?");
        ps.setDate(1, date);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TongChiPhiTheoThoiGian chiPhi = new TongChiPhiTheoThoiGian();
            chiPhi.setDate(dateToCheck);
            chiPhi.setNhapHang(rs.getDouble("total"));
            list.add(chiPhi);
        }
        return list;
    }

    public List<TongChiPhiTheoThoiGian> layChiPhiTheoTuanGanNhat() throws SQLException {
        List<TongChiPhiTheoThoiGian> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        java.util.Date utilDate = new java.util.Date();
        LocalDate localDate = LocalDate.now();
        double sum = -1;
        for (int i = 0; i < 7; i++) {
            localDate = LocalDate.now().minusDays(i);
            utilDate = java.util.Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
            java.sql.Date date = new java.sql.Date(utilDate.getTime());
            ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM CT_PhieuNhapHang INNER JOIN PhieuNhapHang ON CT_PhieuNhapHang.maPhieuNhapHang = PhieuNhapHang.maPhieuNhapHang WHERE ngayNhap=?");
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TongChiPhiTheoThoiGian chiPhi = new TongChiPhiTheoThoiGian();
                chiPhi.setDate(date);
                chiPhi.setNhapHang(rs.getDouble("total"));
                list.add(chiPhi);
            }
        }
        return list;
    }

    public List<TongChiPhiTheoThoiGian> layChiPhiTheo6ThangGanNhat() throws SQLException {
        List<TongChiPhiTheoThoiGian> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        PreparedStatement ps_layNgayNhap = null;
        java.util.Date utilDate = new java.util.Date();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 6; i++) {
            localDate = LocalDate.now().minusMonths(i);
            utilDate = java.util.Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
            java.sql.Date date = new java.sql.Date(utilDate.getTime());
            ps = connect.prepareStatement("SELECT SUM(tongTien) as total FROM CT_PhieuNhapHang INNER JOIN PhieuNhapHang ON CT_PhieuNhapHang.maPhieuNhapHang = PhieuNhapHang.maPhieuNhapHang WHERE MONTH(ngayNhap)=MONTH(?) AND YEAR(ngayNhap)=YEAR(?)");
            ps.setDate(1, date);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TongChiPhiTheoThoiGian chiPhi = new TongChiPhiTheoThoiGian();
                chiPhi.setDate(date);
                chiPhi.setNhapHang(rs.getDouble("total"));
                list.add(chiPhi);
            }

        }
        return list;
    }

    public List<TongDoanhThuTheoThoiGian> layDoanhThuTrong6Thang() throws SQLException {
        List<TongDoanhThuTheoThoiGian> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        java.util.Date utilDate = new java.util.Date();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 6; i++) {
            localDate = LocalDate.now().minusMonths(i);
            utilDate = java.util.Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
//            System.out.println(utilDate+": util");
            TongDoanhThuTheoThoiGian dttn = new TongDoanhThuTheoThoiGian();
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

    public List<TongDoanhThuTheoThoiGian> layDoanhThuTrongTuan() throws SQLException {
        List<TongDoanhThuTheoThoiGian> list = new ArrayList<>();
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        java.util.Date utilDate = new java.util.Date();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            localDate = LocalDate.now().minusDays(i);
            utilDate = java.util.Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
//            System.out.println(utilDate+": util");
            TongDoanhThuTheoThoiGian dttn = new TongDoanhThuTheoThoiGian();
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

    public double layDoanhThu(String loai, java.util.Date dateToCheck, String searchMode) throws SQLException {
        Connection connect = Code.KetNoi.layKetNoi();
        PreparedStatement ps = null;
        String queryDoanhThuMuon = "", queryDoanhThuThanhToan = "", queryDoanhThuThanhLy = "";

        if (searchMode == "theo-ngay") {
            queryDoanhThuMuon = "SELECT SUM(tongTien) as total FROM PhieuMuon WHERE ngayMuon=?";
            queryDoanhThuThanhToan = "SELECT SUM(tongTien) as total FROM PhieuThanhToanSach WHERE ngayThanhToan=?";
            queryDoanhThuThanhLy = "SELECT SUM(tongTienThanhLy) as total FROM PhieuThanhLy WHERE ngayThanhLy=?";
        } else if (searchMode == "theo-thang") {
            queryDoanhThuMuon = "SELECT SUM(tongTien) as total FROM PhieuMuon WHERE MONTH(ngayMuon)= MONTH(?) AND YEAR(ngayMuon)= YEAR(?)";
            queryDoanhThuThanhToan = "SELECT SUM(tongTien) as total FROM PhieuThanhToanSach WHERE MONTH(ngayThanhToan)= MONTH(?) AND YEAR(ngayThanhToan)= YEAR(?)";
            queryDoanhThuThanhLy = "SELECT SUM(tongTienThanhLy) as total FROM PhieuThanhLy WHERE MONTH(ngayThanhLy)= MONTH(?) AND YEAR(ngayThanhLy)= YEAR(?)";
        } else if (searchMode == "theo-nam") {
            queryDoanhThuMuon = "SELECT SUM(tongTien) as total FROM PhieuMuon WHERE YEAR(ngayMuon)= YEAR(?)";
            queryDoanhThuThanhToan = "SELECT SUM(tongTien) as total FROM PhieuThanhToanSach WHERE YEAR(ngayThanhToan)= YEAR(?)";
            queryDoanhThuThanhLy = "SELECT SUM(tongTienThanhLy) as total FROM PhieuThanhLy WHERE YEAR(ngayThanhLy)= YEAR(?)";
        }

        java.sql.Date date = new Date(dateToCheck.getTime());
        switch (loai) {
            case "muon":
                ps = connect.prepareStatement(queryDoanhThuMuon);
                ps.setDate(1, date);
                if (searchMode.equalsIgnoreCase("theo-thang")) {
                    ps.setDate(2, date);
                }
                ResultSet rs = ps.executeQuery();
                double sum = 0;
                while (rs.next()) {
                    sum = rs.getDouble("total");
                }
                return sum;
            case "thanh-toan":
                ps = connect.prepareStatement(queryDoanhThuThanhToan);
                ps.setDate(1, date);
                if (searchMode.equalsIgnoreCase("theo-thang")) {
                    ps.setDate(2, date);
                }
                rs = ps.executeQuery();
                sum = 0;
                while (rs.next()) {
                    sum = rs.getDouble("total");
                }
                return sum;
            case "thanh-ly":
                ps = connect.prepareStatement(queryDoanhThuThanhLy);
                ps.setDate(1, date);
                if (searchMode.equalsIgnoreCase("theo-thang")) {
                    ps.setDate(2, date);
                }
                rs = ps.executeQuery();
                sum = 0;
                while (rs.next()) {
                    sum = rs.getDouble("total");
                }
                return sum;
        }
        return 0;
    }

    public static void main(String[] args) throws SQLException {
//        System.out.println(new DatabaseAccess().layDoanhThuTrongNgay("thanh-ly"));
//        System.out.println(new DatabaseAccess().layDoanhThuTrongTuan());
//        System.out.println(new DatabaseAccess().layDoanhThuTrongThang());
        System.out.println(new DatabaseAccess().layMaPhieuNhapKeTiep());

    }
}
