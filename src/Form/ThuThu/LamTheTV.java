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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class LamTheTV extends javax.swing.JPanel {

    HamXuLyBang xlyBang;
    DataBaseAccess dbAccess;
    String regexSDT = "0\\d{9}";
    String regexEmail = "\\w+@\\w+(\\.\\w+){1,2}";
    public LamTheTV() {
        initComponents();
        xlyBang = new HamXuLyBang();
        dbAccess=new DataBaseAccess();
        layThongTinDG();
    }
    
    public void layThongTinDG()
    {
        String sql = "select maDocGia, tenDG, SDT, email, diaChi from DocGia";
        
        xlyBang.loadDuLieuVaoBang(t_docgia, sql); 
    }
    
    public boolean checkMa(String ma)
    {
        Connection kn = KetNoi.layKetNoi();
        boolean kqua = false;
        String sql = "select * from DocGia where maDocGia = '" + ma + "'";
        try {
            PreparedStatement ps = kn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                kqua = true;
            }
            kn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kqua;
    }
    
    public void insertDocGia(String maDG, String tenDG, String matKhau, String SDT, String email, String diaChi)
    {
        String sql = "insert into DocGia(maDocGia, tenDG, maTK, SDT, email, diaChi)\n" +
                "values('"+ maDG + "',N'" + tenDG + "','" + maDG + "','" + SDT + "','" + email + "','" + diaChi + "')";
        dbAccess.Update(sql);
        String sql2 = "insert into TaiKhoan(maTK, matKhau)\n"
                + "values('" + maDG + "','" + matKhau + "')";
        dbAccess.Update(sql2);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtmadg = new javax.swing.JTextField();
        txtten = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdiachi = new javax.swing.JTextArea();
        txtdienthoai = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_docgia = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtmatkhau = new javax.swing.JPasswordField();
        btnReload = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1155, 675));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/card_payment_60px.png"))); // NOI18N
        jLabel1.setText("LÀM THẺ THƯ VIỆN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã độc giả");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Họ tên");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Địa chỉ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Điện thoại");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Email");

        txtmadg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtdiachi.setColumns(20);
        txtdiachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdiachi.setRows(5);
        jScrollPane1.setViewportView(txtdiachi);

        txtdienthoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtemail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ok_32px.png"))); // NOI18N
        jButton1.setText("Hoàn tất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/undo_32px.png"))); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        t_docgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã độc giả", "Tên độc giả", "Số điện thoại", "Email", "Địa chỉ"
            }
        ));
        jScrollPane2.setViewportView(t_docgia);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Mật khẩu");

        btnReload.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reload.png"))); // NOI18N
        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8))
                            .addGap(70, 70, 70)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtdienthoai)
                                .addComponent(txtemail)
                                .addComponent(txtmatkhau, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                            .addGap(69, 69, 69))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtmadg, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(444, 444, 444))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(227, 227, 227))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtmadg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtdienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String maDG, hoTen, diaChi, matKhau, dienThoai, email;
        boolean ktra = true;
        maDG = txtmadg.getText();
        hoTen = txtten.getText();
        diaChi = txtdiachi.getText();
        matKhau = txtmatkhau.getText();
        dienThoai = txtdienthoai.getText();
        email = txtemail.getText();
        
        if(maDG.equals("") || hoTen.equals("") || diaChi.equals("") || dienThoai.equals("") || email.equals("") || matKhau.equals(""))
        {
            ktra = false;
        }
        if(ktra == false)
        {
            JOptionPane.showMessageDialog(this, "Không được để trống thông tin!!");
            return;
        }
        
        if(checkMa(maDG) == true)
        {
            JOptionPane.showMessageDialog(this, "Mã độc giả đã tồn tại, vui lòng nhập mã khác!!");
            return;
        }
        
        if(!dienThoai.matches(regexSDT))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng SĐT!!");
            return;
        }
        
        if(!email.matches(regexEmail))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng Email!!");
            return;
        }
        
        int rett = JOptionPane.showConfirmDialog(this, "Xác nhận thêm độc giả?");
        if(rett == JOptionPane.OK_OPTION)
        {
            insertDocGia(maDG, hoTen, matKhau, dienThoai, email, diaChi);
            JOptionPane.showMessageDialog(this, "Thêm thông tin độc giả thành công!");
            layThongTinDG();
            txtmadg.setText("");
            txtten.setText("");
            txtdiachi.setText("");
            txtmatkhau.setText("");
            txtdienthoai.setText("");
            txtemail.setText("");
        }
        else
        {
            return;
        }
        
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txtmadg.setText("");
        txtten.setText("");
        txtdiachi.setText("");
        txtmatkhau.setText("");
        txtdienthoai.setText("");
        txtemail.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
       layThongTinDG();
    }//GEN-LAST:event_btnReloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReload;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable t_docgia;
    private javax.swing.JTextArea txtdiachi;
    private javax.swing.JTextField txtdienthoai;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmadg;
    private javax.swing.JPasswordField txtmatkhau;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
