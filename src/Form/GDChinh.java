package Form;

import Code.HamXuLyBang;
import Code.LoadThoiGian;
import Code.ThuVien;
import Form.DocGia.DocGiaDKMuonTruoc;
import Form.DocGia.DocGiaPhanHoi;
import Form.DocGia.DocGiaTraCuu;
import Form.QuanLy.PnQlyDoanhThu;
import Form.QuanLy.PnQuanLyNhapHang;
import Form.ThuKho.TKQuanLyCSVC;
import Form.ThuKho.TKQuanLyKeVaNganKe;
import Form.ThuKho.TKQuanLyKhu;
import Form.ThuKho.TKQuanLyLoaiCSVC;
import Form.ThuKho.TKQuanLyNXB;
import Form.ThuKho.TKQuanLySach;
import Form.ThuKho.TKQuanLyTacGia;
import Form.ThuKho.TKQuanLyTheLoaiSach;
import Form.ThuKho.TKQuanLyVitri;
import Form.ThuThu.LamTheTV;
import Form.ThuThu.PnQlyDocGiaQuaHan;
import Form.ThuThu.PnQlyMuonSach;
import Form.ThuThu.PnQlyMuonTruoc;
import Form.ThuThu.ThanhToanSach;
import Form.ThuThu.Trasach;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GDChinh extends javax.swing.JFrame {

    HamXuLyBang xLBang = new HamXuLyBang();

    public GDChinh() throws SQLException {
        initComponents();

        this.setLocationRelativeTo(null);
        if (ThuVien.Account.isEmpty()) {
            jBtnCancelGDNhanVien.setText("Đăng nhập");
        }

        LbUserAcc.setText(ThuVien.Account);
        LbUserName.setText(ThuVien.hoTen);

        loadGiaoDien();

        LoadThoiGian tg = new LoadThoiGian(lbClock, lbDate);
        tg.start();

    }

    // LOAD TỪNG PANEL CHỨC NĂNG  VÀO GIAO DIỆN CHÍNH DỰA THEO QUYỀN TRUY CẬP CỦA TỪNG ĐỐI TƯỢNG
    public void loadGiaoDien() throws SQLException {

        //Load chức năng Tra cứu (dùng chung)
        DocGiaTraCuu docGiaTraCuu2 = new DocGiaTraCuu();
        addPanel(docGiaTraCuu2, "Tra Cứu", "/image/search_30px.png");

        // CHỨC NĂNG CHO KHÁCHS
        switch (ThuVien.quyen) {

            case "Độc giả":
                DocGiaDKMuonTruoc docGiaDKMuonTruoc = new DocGiaDKMuonTruoc();
                addPanel(docGiaDKMuonTruoc, "Đăng ký mượn trước", "/image/icons8_edit_25px.png");
                
                DocGiaPhanHoi docGiaPhanHoi = new DocGiaPhanHoi();
                addPanel(docGiaPhanHoi, "Độc giả phản hồi", "/image/feedback.png");
                break;

            case "Quản lý":
//            PnQLyNhanVien qlyNV =new PnQLyNhanVien();
//            addPanel(qlyNV, "QLÝ NHÂN VIÊN", "/image/user-icon11.png");
                PnQlyDoanhThu qlyDT = new PnQlyDoanhThu();
                addPanel(qlyDT, "QLÝ DOANH THU", "/image/user-icon11.png");

                PnQuanLyNhapHang qlyNH = new PnQuanLyNhapHang();
                addPanel(qlyNH, "QLÝ Nhập hàng", "/image/user-icon11.png");
                break;

            case "Thủ thư":
                PnQlyMuonSach qlyMS = new PnQlyMuonSach();
                addPanel(qlyMS, "QLÝ Mượn sách", "/image/ticket_purchase_30px.png");

                PnQlyMuonTruoc qlyMST = new PnQlyMuonTruoc();
                addPanel(qlyMST, "QLÝ Mượn trước", "/image/elections_30px.png");

                PnQlyDocGiaQuaHan qlyDGQH = new PnQlyDocGiaQuaHan();
                addPanel(qlyDGQH, "QLÝ Độc giả quá hạn", "/image/man_window_lock_30px.png");

                LamTheTV lt = new LamTheTV();
                addPanel(lt, "Làm Thẻ Thư viện", "/image/users_30px.png");

                ThanhToanSach tts = new ThanhToanSach();
                addPanel(tts, "Thanh toán sách", "/image/refund_30px.png");

                Trasach ts = new Trasach();
                addPanel(ts, "Trả sách", "/image/read_online_30px.png");

                break;

            case "Thủ kho":
//                DocGiaTraCuu docGiaTraCuu2 = new DocGiaTraCuu();
//                addPanel(docGiaTraCuu2, "Tra Cứu Sách", "/image/user-icon11.png");

                TKQuanLySach tKQuanLySach = new TKQuanLySach();
                addPanel(tKQuanLySach, "QLÝ Sách", "/image/book_30px.png");
                
                TKQuanLyVitri tKQuanLyVitri = new TKQuanLyVitri();
                addPanel(tKQuanLyVitri, "QLÝ Vị trí", "/image/placeholder.png");
                
                TKQuanLyTheLoaiSach tKQuanLyTheLoaiSach = new TKQuanLyTheLoaiSach();
                addPanel(tKQuanLyTheLoaiSach, "QLÝ Thể Loại Sách", "/image/books_30px.png");
                
                 TKQuanLyTacGia tKQuanLyTacGia = new TKQuanLyTacGia();
                addPanel(tKQuanLyTacGia, "QLÝ Tác giả", "/image/user_typing_using_typewriter_30px.png");
                
                TKQuanLyNXB tKQuanLyNXB = new TKQuanLyNXB();
                addPanel(tKQuanLyNXB, "QLÝ NXB", "/image/company_30px.png");
                
                TKQuanLyCSVC tKQuanLyCSVC = new TKQuanLyCSVC();
                addPanel(tKQuanLyCSVC, "QLÝ CSVC", "/image/administrative_tools_30px.png");

                TKQuanLyLoaiCSVC tKQuanLyLoaiCSVC = new TKQuanLyLoaiCSVC();
                addPanel(tKQuanLyLoaiCSVC, "QLÝ Loại CSVC", "/image/window_tools_30px.png");
                
                TKQuanLyKhu tKQuanLyKhu = new TKQuanLyKhu();
                addPanel(tKQuanLyKhu, "QLÝ Khu", "/image/book_shelf_30px.png"); 
                
                TKQuanLyKeVaNganKe tKQuanLyKeVaNganKe = new TKQuanLyKeVaNganKe();
                addPanel(tKQuanLyKeVaNganKe, "QLÝ Kệ và Ngăn Kệ", "/image/book_shelfs_30px.png");
                break;

            default:
                break;
        }
    }

    private void addPanel(JPanel pn, String tieuDe, String icon) {
        tbPnMenu.add(tieuDe, pn);
        tbPnMenu.setIconAt(tbPnMenu.getTabCount() - 1, new javax.swing.ImageIcon(getClass().getResource(icon)));
    }

    //=================================HÀM LẤY THÔNG TIN NHÂN VIÊN====================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbPnMenu = new javax.swing.JTabbedPane();
        pnInfor = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LbUserAcc = new javax.swing.JLabel();
        jBtnCancelGDNhanVien = new javax.swing.JButton();
        lbImgCalendar = new javax.swing.JLabel();
        lbClock = new javax.swing.JLabel();
        lbImgCalendar1 = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        lbTopBanner = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LbUserName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ THƯ VIỆN");
        setLocation(new java.awt.Point(500, 40));
        setResizable(false);

        tbPnMenu.setBackground(new java.awt.Color(102, 102, 255));
        tbPnMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbPnMenu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        pnInfor.setBackground(new java.awt.Color(19, 101, 175));
        pnInfor.setLayout(null);

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        pnInfor.add(jLabel3);
        jLabel3.setBounds(370, 10, 2, 100);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User-blue-icon.png"))); // NOI18N
        jLabel1.setText(":");
        pnInfor.add(jLabel1);
        jLabel1.setBounds(410, 10, 50, 40);

        LbUserAcc.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        LbUserAcc.setForeground(new java.awt.Color(51, 255, 0));
        LbUserAcc.setText("Chưa xác định");
        pnInfor.add(LbUserAcc);
        LbUserAcc.setBounds(460, 10, 180, 40);

        jBtnCancelGDNhanVien.setBackground(new java.awt.Color(19, 101, 175));
        jBtnCancelGDNhanVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBtnCancelGDNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout-icon.png"))); // NOI18N
        jBtnCancelGDNhanVien.setText("Đăng Xuất");
        jBtnCancelGDNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelGDNhanVienActionPerformed(evt);
            }
        });
        pnInfor.add(jBtnCancelGDNhanVien);
        jBtnCancelGDNhanVien.setBounds(950, 20, 180, 80);

        lbImgCalendar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbImgCalendar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImgCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clock-icon.png"))); // NOI18N
        lbImgCalendar.setText(":");
        pnInfor.add(lbImgCalendar);
        lbImgCalendar.setBounds(680, 60, 60, 50);

        lbClock.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbClock.setForeground(new java.awt.Color(255, 255, 255));
        lbClock.setText("Giờ");
        pnInfor.add(lbClock);
        lbClock.setBounds(740, 70, 170, 30);

        lbImgCalendar1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbImgCalendar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Calendar-icon.png"))); // NOI18N
        lbImgCalendar1.setText(":");
        pnInfor.add(lbImgCalendar1);
        lbImgCalendar1.setBounds(690, 10, 50, 40);

        lbDate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbDate.setForeground(new java.awt.Color(255, 255, 255));
        lbDate.setText("Ngày");
        pnInfor.add(lbDate);
        lbDate.setBounds(740, 20, 170, 30);

        lbTopBanner.setForeground(new java.awt.Color(19, 101, 175));
        lbTopBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/book-icon.png"))); // NOI18N
        pnInfor.add(lbTopBanner);
        lbTopBanner.setBounds(0, 0, 130, 110);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/name-card-icon.png"))); // NOI18N
        jLabel2.setText(":");
        pnInfor.add(jLabel2);
        jLabel2.setBounds(410, 60, 50, 40);

        LbUserName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        LbUserName.setForeground(new java.awt.Color(51, 255, 0));
        LbUserName.setText("Chưa xác định");
        pnInfor.add(LbUserName);
        LbUserName.setBounds(460, 60, 180, 40);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 32)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("THƯ VIỆN 10Đ");
        pnInfor.add(jLabel4);
        jLabel4.setBounds(140, 10, 230, 100);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnInfor, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
            .addComponent(tbPnMenu)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbPnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
        );

        tbPnMenu.getAccessibleContext().setAccessibleName("QLÝ VÉ XE");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCancelGDNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelGDNhanVienActionPerformed
        // TODO add your handling code here:

        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jBtnCancelGDNhanVienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GDChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GDChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GDChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GDChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GDChinh().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(GDChinh.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbUserAcc;
    private javax.swing.JLabel LbUserName;
    private javax.swing.JButton jBtnCancelGDNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbClock;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbImgCalendar;
    private javax.swing.JLabel lbImgCalendar1;
    private javax.swing.JLabel lbTopBanner;
    private javax.swing.JPanel pnInfor;
    private javax.swing.JTabbedPane tbPnMenu;
    // End of variables declaration//GEN-END:variables
}
