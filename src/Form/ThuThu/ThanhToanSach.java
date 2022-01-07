/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.ThuThu;

import Code.DataBaseAccess;
import Code.HamXuLyBang;
import Code.KetNoi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ThanhToanSach extends javax.swing.JPanel {

    HamXuLyBang xlyBang;
    DataBaseAccess dbAccess;
    int SLCon = -1;
    String maSach = "";
    Connection ketNoi = KetNoi.layKetNoi();
    public ThanhToanSach() {
        initComponents();
        xlyBang = new HamXuLyBang();
        dbAccess=new DataBaseAccess();
        laydulieuSach();
        loadDaTa();
        txtNgayHT.setText(layNgayHT());
    }
    
    public void loadDaTa()
    {
        try {
            ResultSet rs = dbAccess.Query("select maNV from ThuThu");
            while (rs.next()) {
                cbbNV.addItem(rs.getString(1));
            }
            
            rs = dbAccess.Query("select maDocGia from DocGia");
            while (rs.next()) {
                cbbDG.addItem(rs.getString(1));
            }
            
        } catch (Exception e) {
            Logger.getLogger(PnQlyMuonSach.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void laydulieuSach(){
        String sql = "select S.maISBN, S.tenSach, S.maTacGia, TL.tenTheLoai, S.giaBia, S.soLuongCon " +
                        "from SACH S, TacGia TG, TheLoaiSach TL\n" +
                        "where S.maTacGia = TG.maTacGia\n" +
                        "and S.maTheLoai = TL.maTheLoai\n" +
                        "and S.soLuongCon > 0";
        
        xlyBang.loadDuLieuVaoBang(t_sach, sql); 
        
    }
    
    public String layNgayHT()
    {
        long millis=System.currentTimeMillis();   
        Date date1 = new java.sql.Date(millis);
        String ngayTra = date1.toString();
        return ngayTra;
    }
    
    public String layMaPhieuThanhToanMax()
    {
        int maPTT = 0;
        try {
            PreparedStatement command = ketNoi.prepareStatement("select maPhieuThanhToanSach from PhieuThanhToanSach");
            ResultSet rs = command.executeQuery();
            while(rs.next())
            {
                if(Integer.parseInt(rs.getString(1)) > maPTT)
                {
                    maPTT = Integer.parseInt(rs.getString(1));
                }
            }
        } catch (Exception e) {
        }
        return String.valueOf(maPTT);
    }
    
    public void insertPhieuThanhToanSach(String maPTT, String ngayTT, String maDG, String maNV, String tongTien)
    {
        String sql = "insert into PhieuThanhToanSach(maPhieuThanhToanSach, ngayThanhToan, maDocGia, maNV, tongTien, trangThai)\n" +
                    "values('" + maPTT + "','" + ngayTT + "','" + maDG + "','" + maNV + "','" + tongTien + "','1')";
        dbAccess.Update(sql);
    }
    
    public void insertCTPhieuThanhToanSach(String maPTT, String maSach, int SL)
    {
        String sql = "insert into CT_PhieuThanhToanSach(maPhieuThanhToanSach, maSach, soLuong)\n" +
                    "values('" + maPTT + "','" + maSach + "'," + SL + ")";
        dbAccess.Update(sql);
    }
    
    public void updateSoLuong(String maSach, int SL)
    {
        String sql = "update Sach set soLuongCon = soLuongCon - " + SL +
                "where maISBN = '" + maSach + "'";
        dbAccess.Update(sql);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_sach = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSpinner_SL = new javax.swing.JSpinner();
        txtTenSach = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayHT = new javax.swing.JTextField();
        cbbNV = new javax.swing.JComboBox<>();
        cbbDG = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/money_60px.png"))); // NOI18N
        jLabel1.setText("THANH TOÁN SÁCH");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã ĐG");

        t_sach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Giá", "Số lượng hiện có"
            }
        ));
        t_sach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_sachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_sach);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/money_circulation_32px.png"))); // NOI18N
        jButton1.setText("THANH TOÁN SÁCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reload.png"))); // NOI18N
        jButton2.setText("Reload");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Mã NV");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Số lượng");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Ngày thanh toán");

        jSpinner_SL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jSpinner_SL.setModel(new javax.swing.SpinnerNumberModel());

        txtTenSach.setEditable(false);
        txtTenSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSachActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tên sách");

        txtGia.setEditable(false);
        txtGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Giá");

        txtNgayHT.setEditable(false);
        txtNgayHT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbbNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbbDG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(457, 457, 457))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(172, 172, 172)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(324, 324, 324))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtNgayHT, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSpinner_SL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(4, 4, 4)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbbDG, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbbNV, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(60, 60, 60)
                                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbbDG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbbNV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jSpinner_SL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNgayHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSachActionPerformed

    private void t_sachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_sachMouseClicked
        int selectedRow = t_sach.getSelectedRow();
        if(selectedRow < 0)
        {
            return;
        }
        else
        {
            String tenSach, gia;
            maSach = (String) t_sach.getValueAt(selectedRow, 0);
            tenSach = (String) t_sach.getValueAt(selectedRow, 1);
            gia = (String) t_sach.getValueAt(selectedRow, 4);
            String SoLuongCon = (String) t_sach.getValueAt(selectedRow, 5);
            SLCon = Integer.parseInt(SoLuongCon);
            txtTenSach.setText(tenSach);
            txtGia.setText(gia);
        }
    }//GEN-LAST:event_t_sachMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(jSpinner_SL.getValue().toString().equalsIgnoreCase("0")){
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
       }
       if(txtTenSach.getText().equals(""))
       {
           JOptionPane.showMessageDialog(this, "Vui lòng chọn sách muốn thanh toán", "Thông báo", JOptionPane.ERROR_MESSAGE);
           return;
       }
       if(Integer.parseInt(jSpinner_SL.getValue().toString()) > SLCon)
       {
           JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
           return;
       }
       int rett = JOptionPane.showConfirmDialog(this, "Bạn có muốn thanh toán sách này?");
       if(rett == JOptionPane.OK_OPTION)
       {
           int maPhieuTT = Integer.parseInt(layMaPhieuThanhToanMax()) + 1;
           String maPTT = String.valueOf(maPhieuTT);
           String ngayTT = txtNgayHT.getText();
           String maDG = cbbDG.getSelectedItem().toString();
           String maNV = cbbNV.getSelectedItem().toString();
           String gia = txtGia.getText();
           String soLuong = jSpinner_SL.getValue().toString();
           int SL = Integer.parseInt(soLuong);
           insertPhieuThanhToanSach(maPTT, ngayTT, maDG, maNV, gia);
           insertCTPhieuThanhToanSach(maPTT, maSach, SL);
           updateSoLuong(maSach, SL);
           JOptionPane.showMessageDialog(this, "Thanh toán sách thành công");
           laydulieuSach();
       }
       else
       {
           return;
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        laydulieuSach();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbDG;
    private javax.swing.JComboBox<String> cbbNV;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_SL;
    private javax.swing.JTable t_sach;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtNgayHT;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
