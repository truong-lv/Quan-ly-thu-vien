/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.ThuThu;

import Code.DataBaseAccess;
import Code.HamXuLyBang;
import Code.KetNoi;
import Code.ThuVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author n18dc
 */
public class frmConvertMuonTruocToMuon extends javax.swing.JFrame {

    /**
     * Creates new form frmConvertMuonTruocToMuon
     */
    
    private String maPhieuMuonTruoc="";
    private String ngayMuon="";
    private String maDG="";
    private String tenDG="";
    
    DataBaseAccess dbAccess;
    Connection ketNoi = KetNoi.layKetNoi();
    HamXuLyBang xlbang;
    
    String maPhieuMuon="";
    double tongTien=0;
    public frmConvertMuonTruocToMuon(String maPhieu, String ngay, String ma, String ten) {
        initComponents();
        this.maPhieuMuonTruoc=maPhieu;
        this.ngayMuon=ngay;
        this.maDG=ma;
        this.tenDG=ten;
        
        txtMaPhieu.setText(maPhieu);
        txtNgayLap.setText(ngayMuon);
        txtMaDG.setText(maDG);
        txtTenDG.setText(tenDG);
        
        dbAccess=new DataBaseAccess();
        xlbang=new HamXuLyBang();
        
        btnDuyetPhieu.setEnabled(ktMuon());
        
    }

    
    public String layMaPhieu(String loai){

        try {
           //CallableStatement command = connect.prepareCall("{call SP_LOAD_LOGIN (?,?)}");
           PreparedStatement command=ketNoi.prepareStatement("{call SP_LOAD_MA (?)}");
            //cung cap gia tri cho bien
           command.setString(1, loai);
           ResultSet rs = command.executeQuery();
           // duyet ket qua
           if (rs.next()) {
               return rs.getString(1);
           }
       } catch (Exception e) {
       }
        return "";
    }
    
    public boolean ktMuon(){
        String sql="SELECT maCTPhieuMuonTruoc From CT_PhieuMuonTruoc Where maPhieuMuonTruoc='"+this.maPhieuMuonTruoc+"'";
        ResultSet rs =dbAccess.Query(sql);
        List<String> listCtMuonTruoc=new ArrayList<String>();
        try {
            while(rs.next()){
                listCtMuonTruoc.add(rs.getString(1));
            }
            
            for(int i=0;i<listCtMuonTruoc.size();i++){
                sql="SELECT maCTPhieuMuonTruoc, giaBia, CT.soLuong  From CT_PhieuMuonTruoc CT, Sach S Where maCTPhieuMuonTruoc="+listCtMuonTruoc.get(i)+" and maSach=maISBN and soLuongCon>0";
                rs =dbAccess.Query(sql);
                //tính tổng tiền
                tongTien+=(Double.parseDouble(rs.getString(2))*0.7)*Double.parseDouble(rs.getString(3));
                if(rs.next()){
                    tongTien=0;
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PnQlyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void themPhieuMuon(){
        String maPhieuMuon=layMaPhieu("1");
        String sql = "INSERT INTO PhieuMuon VALUES ('"+maPhieuMuon+"',"
                                                    +tongTien
                                                    +",0,'"+ThuVien.primaryKey+"','"
                                                    +txtNgayMuon.getText()+"','"
                                                    +txtMaDG.getText()+"')";
        dbAccess.Update(sql);
    }
    public void them1CTPhieuMuon(String maSach, String ngaytra,int soluong){
        String sql = "INSERT INTO CT_PhieuMuon(maPhieuMuon,maSach,ngayTra,soLuong) VALUES ('"+maPhieuMuon+"','"+maSach+"','"+ngaytra+"',"+soluong+")";
        String sql2="UPDATE Sach SET soLuongCon=soLuongCon-"+soluong+" WHERE maISBN='"+maSach+"'";
        dbAccess.Update(sql);
        dbAccess.Update(sql2);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHuy = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ngayTra = new com.toedter.calendar.JDateChooser();
        txtNgayMuon = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_CTmuon = new javax.swing.JTable();
        btnDuyetPhieu = new javax.swing.JButton();
        txtMaDG = new javax.swing.JTextField();
        txtTenDG = new javax.swing.JTextField();
        txtMaSach = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Close_32px.png"))); // NOI18N
        btnHuy.setText("Thoát");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("CÁC SÁCH ĐĂNG KÝ MƯỢN TRƯỚC");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("CHI TIẾT PHIẾU MƯỢN TRƯỚC");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã độc giả:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Mã sách:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Số lượng:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Tên sách:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Ngày mượn:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Ngày trả:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Tên độc giả:");

        ngayTra.setDateFormatString("yyyy-MM-dd");
        ngayTra.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtNgayMuon.setEditable(false);
        txtNgayMuon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jTable_CTmuon.setAutoCreateRowSorter(true);
        jTable_CTmuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Giá", "Ngày mượn", "Ngày trả", "Tiền cọc"
            }
        ));
        jTable_CTmuon.setRowHeight(25);
        jTable_CTmuon.setRowMargin(3);
        jTable_CTmuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CTmuonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_CTmuon);

        btnDuyetPhieu.setBackground(new java.awt.Color(51, 255, 51));
        btnDuyetPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/check_file_35px.png"))); // NOI18N
        btnDuyetPhieu.setText("Duyệt phiếu");
        btnDuyetPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuyetPhieuActionPerformed(evt);
            }
        });

        txtMaDG.setEditable(false);
        txtMaDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtTenDG.setEditable(false);
        txtTenDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtMaSach.setEditable(false);
        txtMaSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtTenSach.setEditable(false);
        txtTenSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtSoLuong.setEditable(false);
        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tổng tiền:");

        lbTongTien.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(51, 204, 0));
        lbTongTien.setText("00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Mã Phiếu:");

        txtMaPhieu.setEditable(false);
        txtMaPhieu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Ngày lập:");

        txtNgayLap.setEditable(false);
        txtNgayLap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(9, 9, 9)
                            .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(238, 238, 238)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(btnDuyetPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(168, 168, 168)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(187, 187, 187)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(4, 4, 4)
                                .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel12)
                                .addGap(5, 5, 5)
                                .addComponent(ngayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lbTongTien))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(ngayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDuyetPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnDuyetPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuyetPhieuActionPerformed
        // TODO add your handling code here:
        themPhieuMuon();
        
        for(int i=0;i<jTable_CTmuon.getRowCount();i++){
            them1CTPhieuMuon(xlbang.getRow(jTable_CTmuon, i,0), xlbang.getRow(jTable_CTmuon, i, 4),Integer.parseInt(xlbang.getRow(jTable_CTmuon, i, 5)));
        }
        JOptionPane.showMessageDialog(this, "Mượn sách thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnDuyetPhieuActionPerformed

    private void jTable_CTmuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CTmuonMouseClicked
        // TODO add your handling code here:
        txtMaDG.setText(xlbang.selectRow(jTable_CTmuon, 0));
    }//GEN-LAST:event_jTable_CTmuonMouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmConvertMuonTruocToMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmConvertMuonTruocToMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmConvertMuonTruocToMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmConvertMuonTruocToMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDuyetPhieu;
    private javax.swing.JButton btnHuy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_CTmuon;
    private javax.swing.JLabel lbTongTien;
    private com.toedter.calendar.JDateChooser ngayTra;
    private javax.swing.JTextField txtMaDG;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenDG;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
