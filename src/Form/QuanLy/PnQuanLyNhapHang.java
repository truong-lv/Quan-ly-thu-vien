/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author t0168
 */
public class PnQuanLyNhapHang extends javax.swing.JPanel {

    /**
     * Creates new form PnQuanLyNhapHang
     */
    String mode = "";
    DefaultTableModel dtm;
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");

    public PnQuanLyNhapHang() throws SQLException {
        initComponents();
        dtm = (DefaultTableModel) jTable1.getModel();
        jLabel_THem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabel_Sua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabel_Xoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabel_Cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

//        jComboBox_MaNCC.removeAllItems();
//        jComboBox_MaCSVC.removeAllItems();
//        jComboBox_MaSach.removeAllItems();

//        jComboBox_MaCSVC.addItem("Không nhập CSVC");
//        jComboBox_MaSach.addItem("Không nhập Saćh");

        jSpinField1.setMinimum(0);
        jSpinField2.setMinimum(0);

        jComboBox_MaSach.setEnabled(false);
        jComboBox_MaNCC.setEnabled(false);
        jComboBox_MaCSVC.setEnabled(false);
        jSpinField1.setEnabled(false);
        jSpinField2.setEnabled(false);

        for (PhieuNhapHang pnh : new DatabaseAccess().layPhieuNhapHang()) {
            dtm.addRow(new Object[]{pnh.getMaPhieuNhapHang(), pnh.getMaNCC(), pnh.getMaNV_QuanLy(),
                pnh.getMaNV_ThuKho(), dfm.format(pnh.getNgayNhap()), pnh.getTrangThai()});
        }

        for (NhaCungCap ncc : new DatabaseAccess().layNhaCungCap("all")) {
            jComboBox_MaNCC.addItem(ncc.getMaNCC());
        }

        for (CoSoVatChat csvc : new DatabaseAccess().layCoSoVatChat("all")) {
            jComboBox_MaCSVC.addItem(csvc.getMaCSVC());
        }

        for (Sach sach : new DatabaseAccess().laySach("all")) {
            jComboBox_MaSach.addItem(sach.getMaISBN());
        }

        jTable1.setRowSelectionInterval(0, 0);

        PhieuNhapHang pnhDefault = new DatabaseAccess().layPhieuNhapHang((String) jTable1.getValueAt(0, 0));

        jTextField_MaPhieuNhap.setText(pnhDefault.getMaPhieuNhapHang());
        jTextField_TongTien.setText(pnhDefault.getListCT_PhieuNhapHang().get(0).getTongTien() + "");
        if(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaSach()!=null){
            jComboBox_MaSach.setSelectedItem(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaSach() + "");
        }else{
            jComboBox_MaSach.setSelectedIndex(0);
        }
        
        jComboBox_MaNCC.setSelectedItem(pnhDefault.getMaNCC() + "");
        if(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaCSVC()!=null){
            jComboBox_MaCSVC.setSelectedItem(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaCSVC() + "");
        }else{
            jComboBox_MaCSVC.setSelectedIndex(0);
        }
        
        jSpinField1.setValue(pnhDefault.getListCT_PhieuNhapHang().get(0).getSoLuongSach());
        jSpinField2.setValue(pnhDefault.getListCT_PhieuNhapHang().get(0).getSoLuongCSVC());
        jDateChooser2.setDate(pnhDefault.getNgayNhap());
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel_THem = new javax.swing.JLabel();
        jLabel_Sua = new javax.swing.JLabel();
        jLabel_Xoa = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_MaPhieuNhap = new javax.swing.JTextField();
        jComboBox_MaNCC = new javax.swing.JComboBox<>();
        jComboBox_MaCSVC = new javax.swing.JComboBox<>();
        jComboBox_MaSach = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSpinField1 = new com.toedter.components.JSpinField();
        jSpinField2 = new com.toedter.components.JSpinField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_TongTien = new javax.swing.JTextField();
        jLabel_Cancel = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PN", "Mã NCC", "Mã QL", "Mã TK", "Ngày nhập", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel_THem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_THem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_add_25px_2.png"))); // NOI18N
        jLabel_THem.setText("Thêm");
        jLabel_THem.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel_THem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_THemMouseClicked(evt);
            }
        });

        jLabel_Sua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_edit_25px.png"))); // NOI18N
        jLabel_Sua.setText("Sửa");
        jLabel_Sua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_SuaMouseClicked(evt);
            }
        });

        jLabel_Xoa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_delete_25px_1.png"))); // NOI18N
        jLabel_Xoa.setText("Xóa");
        jLabel_Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_XoaMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Ngày nhập");

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Lưu");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Mã NCC");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Mã sách");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("<html><u>Quản lý nhập hàng</u></html>");

        jDateChooser2.setDateFormatString("dd/MM/yyyy");
        jDateChooser2.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Mã CSVC");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Mã phiếu nhập");

        jTextField_MaPhieuNhap.setEnabled(false);
        jTextField_MaPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_MaPhieuNhapActionPerformed(evt);
            }
        });

        jComboBox_MaCSVC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không nhập CSVC" }));
        jComboBox_MaCSVC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_MaCSVCItemStateChanged(evt);
            }
        });

        jComboBox_MaSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không nhập sách" }));
        jComboBox_MaSach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_MaSachItemStateChanged(evt);
            }
        });
        jComboBox_MaSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_MaSachMouseClicked(evt);
            }
        });

        jButton2.setText("Thêm sách");

        jButton3.setText("Thêm CSVC");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Số lượng");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Số lượng");

        jSpinField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinField1PropertyChange(evt);
            }
        });

        jSpinField2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinField2PropertyChange(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Tổng tiền");

        jTextField_TongTien.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_TongTien.setText("0000000");
        jTextField_TongTien.setEnabled(false);

        jLabel_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Cancel.setText("Cancel");
        jLabel_Cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_CancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_MaPhieuNhap)
                                    .addComponent(jComboBox_MaNCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox_MaCSVC, 0, 134, Short.MAX_VALUE)
                                    .addComponent(jComboBox_MaSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jSpinField2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jSpinField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField_TongTien, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_THem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel_Sua)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel_Xoa)
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel_Cancel)))))
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_THem)
                    .addComponent(jLabel_Sua)
                    .addComponent(jLabel_Xoa)
                    .addComponent(jLabel_Cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox_MaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox_MaCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jSpinField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8)
                                            .addComponent(jSpinField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jTextField_MaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox_MaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jButton3)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_MaPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_MaPhieuNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_MaPhieuNhapActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        mode = "none";
        jTable1.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();

        PhieuNhapHang pnhDefault = null;
        try {
            pnhDefault = new DatabaseAccess().layPhieuNhapHang((String) jTable1.getValueAt(selectedRow, 0));
        } catch (SQLException ex) {
            Logger.getLogger(PnQuanLyNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTextField_MaPhieuNhap.setText(pnhDefault.getMaPhieuNhapHang());
        jTextField_TongTien.setText(pnhDefault.getListCT_PhieuNhapHang().get(0).getTongTien() + "");
        if(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaSach()!=null){
            jComboBox_MaSach.setSelectedItem(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaSach() + "");
        }else{
            jComboBox_MaSach.setSelectedIndex(0);
        }
        
        jComboBox_MaNCC.setSelectedItem(pnhDefault.getMaNCC() + "");
        if(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaCSVC()!=null){
            jComboBox_MaCSVC.setSelectedItem(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaCSVC() + "");
        }else{
            jComboBox_MaCSVC.setSelectedIndex(0);
        }
        jSpinField1.setValue(pnhDefault.getListCT_PhieuNhapHang().get(0).getSoLuongSach());
        jSpinField2.setValue(pnhDefault.getListCT_PhieuNhapHang().get(0).getSoLuongCSVC());
        jDateChooser2.setDate(pnhDefault.getNgayNhap());

    }//GEN-LAST:event_jTable1MouseClicked
    private void updateTongTien() throws SQLException {
        double total = 0;
        if (this.jComboBox_MaCSVC.getSelectedIndex() != 0) {
            CoSoVatChat csvc = new DatabaseAccess().layCoSoVatChat((String) this.jComboBox_MaCSVC.getSelectedItem()).get(0);
            total = total + this.jSpinField2.getValue() * csvc.getGia();
        }
        if (this.jComboBox_MaSach.getSelectedIndex() != 0) {
            Sach sach = new DatabaseAccess().laySach((String) this.jComboBox_MaSach.getSelectedItem()).get(0);
            total = total + this.jSpinField1.getValue() * sach.getGiaBia();
        }
        this.jTextField_TongTien.setText(total + "");
    }
    private void jLabel_THemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_THemMouseClicked
        // TODO add your handling code here:
        mode = "add";
        jTable1.setEnabled(false);

        jComboBox_MaSach.setEnabled(true);
        jComboBox_MaNCC.setEnabled(true);
        jComboBox_MaCSVC.setEnabled(true);
        jSpinField1.setEnabled(true);
        jSpinField2.setEnabled(true);

        jTextField_MaPhieuNhap.setText("PN");
        jTextField_TongTien.setText("000000");
        jComboBox_MaSach.setSelectedIndex(0);
        jComboBox_MaNCC.setSelectedIndex(0);
        jComboBox_MaCSVC.setSelectedIndex(0);
        jSpinField1.setValue(0);
        jSpinField2.setValue(0);
        jDateChooser2.setDate(new Date());

    }//GEN-LAST:event_jLabel_THemMouseClicked

    private void jLabel_SuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_SuaMouseClicked
        // TODO add your handling code here:
        mode = "edit";
        jTable1.setEnabled(false);
        jComboBox_MaSach.setEnabled(true);
        jComboBox_MaNCC.setEnabled(true);
        jComboBox_MaCSVC.setEnabled(true);
        jSpinField1.setEnabled(true);
        jSpinField2.setEnabled(true);
    }//GEN-LAST:event_jLabel_SuaMouseClicked

    private void jLabel_XoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_XoaMouseClicked
        // TODO add your handling code here:
        mode = "delete";
    }//GEN-LAST:event_jLabel_XoaMouseClicked

    private void jLabel_CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CancelMouseClicked
        // TODO add your handling code here:
        mode = "none";
        jComboBox_MaSach.setEnabled(false);
        jComboBox_MaNCC.setEnabled(false);
        jComboBox_MaCSVC.setEnabled(false);
        jSpinField1.setEnabled(false);
        jSpinField2.setEnabled(false);
        jTable1.setEnabled(true);
        
        jTable1.setRowSelectionInterval(0, 0);

        PhieuNhapHang pnhDefault=null;
        try {
            pnhDefault = new DatabaseAccess().layPhieuNhapHang((String) jTable1.getValueAt(0, 0));
        } catch (SQLException ex) {
            Logger.getLogger(PnQuanLyNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTextField_MaPhieuNhap.setText(pnhDefault.getMaPhieuNhapHang());
        jTextField_TongTien.setText(pnhDefault.getListCT_PhieuNhapHang().get(0).getTongTien() + "");
        if(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaSach()!=null){
            jComboBox_MaSach.setSelectedItem(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaSach() + "");
        }else{
            jComboBox_MaSach.setSelectedIndex(0);
        }
        
        jComboBox_MaNCC.setSelectedItem(pnhDefault.getMaNCC() + "");
        if(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaCSVC()!=null){
            jComboBox_MaCSVC.setSelectedItem(pnhDefault.getListCT_PhieuNhapHang().get(0).getMaCSVC() + "");
        }else{
            jComboBox_MaCSVC.setSelectedIndex(0);
        }
        
        jSpinField1.setValue(pnhDefault.getListCT_PhieuNhapHang().get(0).getSoLuongSach());
        jSpinField2.setValue(pnhDefault.getListCT_PhieuNhapHang().get(0).getSoLuongCSVC());
        jDateChooser2.setDate(pnhDefault.getNgayNhap());
    }//GEN-LAST:event_jLabel_CancelMouseClicked

    private void jComboBox_MaSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_MaSachItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //this will trigger once only when actually the state is changed
            try {
                // TODO add your handling code here:
                updateTongTien();
            } catch (SQLException ex) {
                Logger.getLogger(PnQuanLyNhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jComboBox_MaSachItemStateChanged

    private void jComboBox_MaCSVCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_MaCSVCItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //this will trigger once only when actually the state is changed
            try {
                // TODO add your handling code here:
                updateTongTien();
            } catch (SQLException ex) {
                Logger.getLogger(PnQuanLyNhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jComboBox_MaCSVCItemStateChanged

    private void jComboBox_MaSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_MaSachMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox_MaSachMouseClicked

    private void jSpinField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinField1PropertyChange
        // TODO add your handling code here:
            //this will trigger once only when actually the state is changed
            try {
                // TODO add your handling code here:
                updateTongTien();
            } catch (SQLException ex) {
                Logger.getLogger(PnQuanLyNhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jSpinField1PropertyChange

    private void jSpinField2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinField2PropertyChange
        // TODO add your handling code here:
        try {
                // TODO add your handling code here:
                updateTongTien();
            } catch (SQLException ex) {
                Logger.getLogger(PnQuanLyNhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jSpinField2PropertyChange

    public static void main(String[] args) throws SQLException {
        JFrame jrame = new JFrame();
        jrame.setSize(1024, 600);
        jrame.add(new PnQuanLyNhapHang());
        jrame.setVisible(true);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox_MaCSVC;
    private javax.swing.JComboBox<String> jComboBox_MaNCC;
    private javax.swing.JComboBox<String> jComboBox_MaSach;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_Cancel;
    private javax.swing.JLabel jLabel_Sua;
    private javax.swing.JLabel jLabel_THem;
    private javax.swing.JLabel jLabel_Xoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.components.JSpinField jSpinField1;
    private com.toedter.components.JSpinField jSpinField2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_MaPhieuNhap;
    private javax.swing.JTextField jTextField_TongTien;
    // End of variables declaration//GEN-END:variables
}