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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author n18dc
 */
public class PnQlyMuonSach extends javax.swing.JPanel {

    /**
     * Creates new form PnQlyMuonSach
     */
    double tiLeCoc=0.7;
    HamXuLyBang xlbang;
    DataBaseAccess dbAccess;
    Connection ketNoi = KetNoi.layKetNoi();
    String maPhieuMuon="";
    public PnQlyMuonSach() {
        initComponents();
        dbAccess=new DataBaseAccess();
        xlbang=new HamXuLyBang();
        ngayTra.setMinSelectableDate( new Date());
        loadData();
    }
    
     public void loadData(){
        try {
            ResultSet rs = dbAccess.Query("select maDocGia from DocGia");
            while (rs.next()) {
                jComboBox_maDG.addItem(rs.getString(1));
            }
            
            rs = dbAccess.Query("select maISBN from Sach");
            while (rs.next()) {
                jComboBox_maSach.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            Logger.getLogger(PnQlyMuonSach.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void layMaPhieu(){

        try {
           //CallableStatement command = connect.prepareCall("{call SP_LOAD_LOGIN (?,?)}");
           PreparedStatement command=ketNoi.prepareStatement("{call SP_LOAD_MA (?)}");
            //cung cap gia tri cho bien
           command.setString(1, "1");
           ResultSet rs = command.executeQuery();
           // duyet ket qua
           if (rs.next()) {
               maPhieuMuon=rs.getString(1);
           }
       } catch (Exception e) {
       }
    }
    
    public String layTen(String loai, String ma){
         
         try {
            //CallableStatement command = connect.prepareCall("{call SP_LOAD_LOGIN (?,?)}");
            PreparedStatement command=ketNoi.prepareStatement("{call SP_LOAD_TEN (?,?)}");
             //cung cap gia tri cho bien
            command.setString(1, loai);
            command.setString(2, ma);
            ResultSet rs = command.executeQuery();
            // duyet ket qua
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
         return "";
     }
     
    public void themPhieuMuon(){
        layMaPhieu();
        String sql = "INSERT INTO PhieuMuon VALUES ('"+maPhieuMuon+"',"
                                                    +Float.parseFloat(lbTongTien.getText())
                                                    +",0,'"+ThuVien.primaryKey+"','"
                                                    +jTextField_ngayMuon.getText()+"','"
                                                    +jComboBox_maDG.getSelectedItem().toString()+"')";
        dbAccess.Update(sql);
    }
    public void them1CTPhieuMuon(String maSach, String ngaytra,int soluong){
        String sql = "INSERT INTO CT_PhieuMuon(maPhieuMuon,maSach,ngayTra,soLuong) VALUES ('"+maPhieuMuon+"','"+maSach+"','"+ngaytra+"',"+soluong+")";
        String sql2="UPDATE Sach SET soLuongCon=soLuongCon-"+soluong+" WHERE maISBN='"+maSach+"'";
        dbAccess.Update(sql);
        dbAccess.Update(sql2);
    }
     
    public void loadSachVaoBang(){
        String sql = "SELECT maISBN, tenSach, giaBia FROM SACH WHERE maISBN='"+jComboBox_maSach.getSelectedItem().toString()+"'";
        ResultSet rs =dbAccess.Query(sql);
        try {
            if(rs.next()){
                DefaultTableModel dtm=(DefaultTableModel)jTable_CTmuon.getModel();
                Vector vt=new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3).substring(0,rs.getString(3).indexOf(".")));
                vt.add(jTextField_ngayMuon.getText());
                vt.add(((JTextField)ngayTra.getDateEditor().getUiComponent()).getText());
                vt.add(jSpinner_soLuong.getValue());
                //t??nh t???ng ti???n c???c/1 s??ch
                double tongCoc=(Double.parseDouble(rs.getString(3))*tiLeCoc)*Double.parseDouble( jSpinner_soLuong.getValue().toString());
                vt.add(String.valueOf(tongCoc).substring(0,String.valueOf(tongCoc).indexOf(".")));
                dtm.addRow(vt);
                jTable_CTmuon.setModel(dtm);
                
                //T??nh t???ng c???c cho t???t c??? s??ch ??G m?????n v?? hi???n th???
                double tongTienCoc=Double.parseDouble(lbTongTien.getText())+tongCoc;
                lbTongTien.setText(String.valueOf(tongTienCoc).substring(0,String.valueOf(tongTienCoc).indexOf(".")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PnQlyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("?????C GI??? M?????N S??CH");
        add(jLabel1);
        jLabel1.setBounds(459, 22, 197, 22);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nh???p m?? ?????c gi???:");
        add(jLabel2);
        jLabel2.setBounds(250, 80, 120, 17);

        jComboBox_maDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jComboBox_maDG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_maDGItemStateChanged(evt);
            }
        });
        add(jComboBox_maDG);
        jComboBox_maDG.setBounds(380, 70, 156, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nh???p m?? s??ch:");
        add(jLabel3);
        jLabel3.setBounds(270, 140, 102, 17);

        jComboBox_maSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jComboBox_maSach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_maSachItemStateChanged(evt);
            }
        });
        add(jComboBox_maSach);
        jComboBox_maSach.setBounds(380, 140, 156, 25);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("S??? l?????ng:");
        add(jLabel4);
        jLabel4.setBounds(300, 210, 67, 17);

        jSpinner_soLuong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jSpinner_soLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        add(jSpinner_soLuong);
        jSpinner_soLuong.setBounds(370, 200, 57, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Danh s??ch m?????n:");
        add(jLabel5);
        jLabel5.setBounds(10, 370, 121, 17);

        jTable_CTmuon.setAutoCreateRowSorter(true);
        jTable_CTmuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? s??ch", "T??n s??ch", "Gi??", "Ng??y m?????n", "Ng??y tr???", "S??? l?????ng", "Ti???n c???c"
            }
        ));
        jTable_CTmuon.setRowHeight(25);
        jTable_CTmuon.setRowMargin(3);
        jScrollPane2.setViewportView(jTable_CTmuon);
        if (jTable_CTmuon.getColumnModel().getColumnCount() > 0) {
            jTable_CTmuon.getColumnModel().getColumn(5).setHeaderValue("S??? l?????ng");
            jTable_CTmuon.getColumnModel().getColumn(6).setHeaderValue("Ti???n c???c");
        }

        add(jScrollPane2);
        jScrollPane2.setBounds(20, 400, 1137, 152);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("T???ng ti???n:");
        add(jLabel6);
        jLabel6.setBounds(900, 560, 71, 17);

        lbTongTien.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(51, 204, 0));
        lbTongTien.setText("00");
        add(lbTongTien);
        lbTongTien.setBounds(980, 560, 160, 19);

        btnThem.setBackground(new java.awt.Color(0, 255, 0));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setText("Th??m");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem);
        btnThem.setBounds(390, 310, 115, 47);

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setText("H???y");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        add(btnHuy);
        btnHuy.setBounds(590, 310, 115, 47);

        btnXacNhan.setBackground(new java.awt.Color(51, 51, 255));
        btnXacNhan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXacNhan.setText("X??c nh???n");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        add(btnXacNhan);
        btnXacNhan.setBounds(520, 590, 115, 47);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("T??n s??ch:");
        add(jLabel8);
        jLabel8.setBounds(600, 140, 66, 17);

        jLabel_tenSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_tenSach.setText("       ");
        add(jLabel_tenSach);
        jLabel_tenSach.setBounds(670, 140, 170, 19);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Ng??y m?????n:");
        add(jLabel10);
        jLabel10.setBounds(270, 250, 86, 17);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Ng??y tr???:");
        add(jLabel11);
        jLabel11.setBounds(570, 250, 65, 17);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("T??n ?????c gi???:");
        add(jLabel12);
        jLabel12.setBounds(570, 70, 84, 17);

        jLabel_tenDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_tenDG.setText("      ");
        add(jLabel_tenDG);
        jLabel_tenDG.setBounds(660, 70, 170, 19);

        ngayTra.setDateFormatString("yyyy-MM-dd");
        ngayTra.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        add(ngayTra);
        ngayTra.setBounds(640, 250, 171, 22);

        jTextField_ngayMuon.setEditable(false);
        jTextField_ngayMuon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        add(jTextField_ngayMuon);
        jTextField_ngayMuon.setBounds(360, 250, 159, 25);

        jLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel.setText("/");
        add(jLabel);
        jLabel.setBounds(430, 190, 10, 50);

        jLabel_slTon.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel_slTon.setText("60");
        add(jLabel_slTon);
        jLabel_slTon.setBounds(440, 200, 70, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");// /MM/uuuu
        LocalDate localDate = LocalDate.now();
        jTextField_ngayMuon.setText(dtf.format(localDate));
    }//GEN-LAST:event_formHierarchyChanged

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        themPhieuMuon();
        
        for(int i=0;i<jTable_CTmuon.getRowCount();i++){
            them1CTPhieuMuon(xlbang.getRow(jTable_CTmuon, i,0), xlbang.getRow(jTable_CTmuon, i, 4),Integer.parseInt(xlbang.getRow(jTable_CTmuon, i, 5)));
        }
        JOptionPane.showMessageDialog(this, "M?????n s??ch th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if(jTable_CTmuon.getSelectionModel().isSelectionEmpty()){// ki???m tra ng?????i d??ng ???? click ch???n d??? li???u trong b???ng ch??a
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n S??CH c???n x??a trong b???ng");
            return;
        }
        double tongCoc=Double.parseDouble(jTable_CTmuon.getValueAt(jTable_CTmuon.getSelectedRow(), 6).toString());
        ((DefaultTableModel)jTable_CTmuon.getModel()).removeRow(jTable_CTmuon.getSelectedRow());
        //T??nh l???i t???ng c???c cho t???t c??? s??ch ??G m?????n v?? hi???n th???
        double tongTienCoc=Double.parseDouble(lbTongTien.getText())-tongCoc;
        lbTongTien.setText(String.valueOf(tongTienCoc).substring(0,String.valueOf(tongTienCoc).indexOf(".")));
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(((JTextField)ngayTra.getDateEditor().getUiComponent()).getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n ng??y tr???", "Th??ng b??o", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(jSpinner_soLuong.getValue().toString().equalsIgnoreCase("0")){
            JOptionPane.showMessageDialog(this, "S??? l?????ng kh??ng h???p l???", "Th??ng b??o", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //th??m d??? li???u v??o b???ng
        loadSachVaoBang();
        
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void jComboBox_maSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_maSachItemStateChanged
        // TODO add your handling code here:
        jLabel_tenSach.setText(layTen("2", jComboBox_maSach.getSelectedItem().toString()));
        DataBaseAccess db=new DataBaseAccess();
        ResultSet rs0 = db.Query("SELECT soLuongCon FROM Sach WHERE maISBN='"+jComboBox_maSach.getSelectedItem().toString()+"'");
        try {
            if (rs0.next()) {
                jLabel_slTon.setText(rs0.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PnQlyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        jSpinner_soLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, Integer.parseInt(jLabel_slTon.getText()), 1));
//        if(jLabel_slTon.getText().equalsIgnoreCase("0")){
//            JOptionPane.showMessageDialog(this, "S??ch ???? ???????c m?????n h???t. Vui l??ng ch???n s??ch kh??c", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
//        }
    }//GEN-LAST:event_jComboBox_maSachItemStateChanged

    private void jComboBox_maDGItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_maDGItemStateChanged
        // TODO add your handling code here:
        jLabel_tenDG.setText(layTen("1", jComboBox_maDG.getSelectedItem().toString()));
        DefaultTableModel dtm=(DefaultTableModel)jTable_CTmuon.getModel();
        dtm.setNumRows(0);
        jTable_CTmuon.setModel(dtm);
        lbTongTien.setText("00");
    }//GEN-LAST:event_jComboBox_maDGItemStateChanged


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
