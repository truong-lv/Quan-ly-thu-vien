/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.ThuThu;

/**
 *
 * @author n18dc
 */
public class PnQlyMuonTruoc extends javax.swing.JPanel {

    /**
     * Creates new form PnQlyMuonTruoc
     */
    public PnQlyMuonTruoc() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_maDG = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox_maSach = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jSpinner_soLuong = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_CTmuon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel_tenSach = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel_tenDG = new javax.swing.JLabel();
        ngayTra = new com.toedter.calendar.JDateChooser();
        jTextField_ngayMuon = new javax.swing.JTextField();
        jLabel = new javax.swing.JLabel();
        jLabel_slTon = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ĐỘC GIẢ MƯỢN SÁCH TRƯỚC");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nhập mã độc giả:");

        jComboBox_maDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jComboBox_maDG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_maDGItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nhập mã sách:");

        jComboBox_maSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jComboBox_maSach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_maSachItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Số lượng:");

        jSpinner_soLuong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jSpinner_soLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Danh sách mượn:");

        jTable_CTmuon.setAutoCreateRowSorter(true);
        jTable_CTmuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Giá", "Ngày mượn", "Ngày trả", "Số lượng", "Tiền cọc"
            }
        ));
        jTable_CTmuon.setRowHeight(25);
        jTable_CTmuon.setRowMargin(3);
        jScrollPane2.setViewportView(jTable_CTmuon);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tổng tiền:");

        lbTongTien.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(51, 204, 0));
        lbTongTien.setText("00");

        btnThem.setBackground(new java.awt.Color(0, 255, 0));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXacNhan.setBackground(new java.awt.Color(51, 51, 255));
        btnXacNhan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Tên sách:");

        jLabel_tenSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_tenSach.setText("Đắt nhân tâm");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Ngày mượn:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Ngày trả:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Tên độc giả:");

        jLabel_tenDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_tenDG.setText("Lù Vĩnh Trường");

        ngayTra.setDateFormatString("yyyy-MM-dd");
        ngayTra.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jTextField_ngayMuon.setEditable(false);
        jTextField_ngayMuon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel.setText("/");

        jLabel_slTon.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel_slTon.setText("60");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1159, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(449, 449, 449)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(240, 240, 240)
                            .addComponent(jLabel2)
                            .addGap(10, 10, 10)
                            .addComponent(jComboBox_maDG, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(jLabel12)
                            .addGap(6, 6, 6)
                            .addComponent(jLabel_tenDG, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(260, 260, 260)
                            .addComponent(jLabel3)
                            .addGap(8, 8, 8)
                            .addComponent(jComboBox_maSach, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(64, 64, 64)
                            .addComponent(jLabel8)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel_tenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(290, 290, 290)
                            .addComponent(jLabel4)
                            .addGap(3, 3, 3)
                            .addComponent(jSpinner_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(jLabel)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel_slTon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(260, 260, 260)
                            .addComponent(jLabel10)
                            .addGap(4, 4, 4)
                            .addComponent(jTextField_ngayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(51, 51, 51)
                            .addComponent(jLabel11)
                            .addGap(5, 5, 5)
                            .addComponent(ngayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(380, 380, 380)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel5)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(890, 890, 890)
                            .addComponent(jLabel6)
                            .addGap(9, 9, 9)
                            .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(510, 510, 510)
                            .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel2))
                        .addComponent(jComboBox_maDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel_tenDG))
                    .addGap(43, 43, 43)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jComboBox_maSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel_tenSach))
                    .addGap(25, 25, 25)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel4))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jSpinner_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel_slTon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jTextField_ngayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(ngayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(35, 35, 35)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(13, 13, 13)
                    .addComponent(jLabel5)
                    .addGap(13, 13, 13)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(lbTongTien))
                    .addGap(11, 11, 11)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_maDGItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_maDGItemStateChanged
        // TODO add your handling code here:
//        jLabel_tenDG.setText(layTen("1", jComboBox_maDG.getSelectedItem().toString()));
//        DefaultTableModel dtm=(DefaultTableModel)jTable_CTmuon.getModel();
//        dtm.setNumRows(0);
//        jTable_CTmuon.setModel(dtm);
    }//GEN-LAST:event_jComboBox_maDGItemStateChanged

    private void jComboBox_maSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_maSachItemStateChanged
        // TODO add your handling code here:
//        jLabel_tenSach.setText(layTen("2", jComboBox_maSach.getSelectedItem().toString()));
//        DataBaseAccess db=new DataBaseAccess();
//        ResultSet rs0 = db.Query("SELECT soLuongCon FROM Sach WHERE maISBN='"+jComboBox_maSach.getSelectedItem().toString()+"'");
//        try {
//            if (rs0.next()) {
//                jLabel_slTon.setText(rs0.getString(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(PnQlyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        jSpinner_soLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, Integer.parseInt(jLabel_slTon.getText()), 1));
//        //        if(jLabel_slTon.getText().equalsIgnoreCase("0")){
//            //            JOptionPane.showMessageDialog(this, "Sách đã được mượn hết. Vui lòng chọn sách khác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            //        }
    }//GEN-LAST:event_jComboBox_maSachItemStateChanged

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
//        if(((JTextField)ngayTra.getDateEditor().getUiComponent()).getText().isEmpty()){
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày trả", "Thông báo", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if(jSpinner_soLuong.getValue().toString().equalsIgnoreCase("0")){
//            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Thông báo", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        //thêm dữ liệu vào bảng
//        loadSachVaoBang();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
//        if(jTable_CTmuon.getSelectionModel().isSelectionEmpty()){// kiểm tra người dùng đã click chọn dữ liệu trong bảng chưa
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn SÁCH cần xóa trong bảng");
//            return;
//        }
//        double tongCoc=Double.parseDouble(jTable_CTmuon.getValueAt(jTable_CTmuon.getSelectedRow(), 6).toString());
//        ((DefaultTableModel)jTable_CTmuon.getModel()).removeRow(jTable_CTmuon.getSelectedRow());
//        //Tính lại tổng cọc cho tất cả sách ĐG mượn và hiện thị
//        double tongTienCoc=Double.parseDouble(lbTongTien.getText())-tongCoc;
//        lbTongTien.setText(String.valueOf(tongTienCoc).substring(0,String.valueOf(tongTienCoc).indexOf(".")));
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
//        themPhieuMuon();
//
//        for(int i=0;i<jTable_CTmuon.getRowCount();i++){
//            them1CTPhieuMuon(xlbang.getRow(jTable_CTmuon, i,0), xlbang.getRow(jTable_CTmuon, i, 4),Integer.parseInt(xlbang.getRow(jTable_CTmuon, i, 5)));
//        }
//        JOptionPane.showMessageDialog(this, "Mượn sách thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnXacNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> jComboBox_maDG;
    private javax.swing.JComboBox<String> jComboBox_maSach;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_slTon;
    private javax.swing.JLabel jLabel_tenDG;
    private javax.swing.JLabel jLabel_tenSach;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner_soLuong;
    private javax.swing.JTable jTable_CTmuon;
    private javax.swing.JTextField jTextField_ngayMuon;
    private javax.swing.JLabel lbTongTien;
    private com.toedter.calendar.JDateChooser ngayTra;
    // End of variables declaration//GEN-END:variables
}
