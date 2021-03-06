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
import java.util.ArrayList;
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
public class PnQlyMuonTruoc extends javax.swing.JPanel {

    /**
     * Creates new form PnQlyMuon
     */
    DataBaseAccess dbAccess;
    HamXuLyBang xlbang;
    Connection ketNoi = KetNoi.layKetNoi();
    String maPhieuMuon="";
    double tongTien=0;
    public PnQlyMuonTruoc() {
        initComponents();
        
        dbAccess=new DataBaseAccess();
        xlbang=new HamXuLyBang();
        
    }

    
    public void loadDGMT(){
        String sql="SELECT PMT.maPhieuMuon, DG.maDocGia, DG.tenDG, DG.SDT, PMT.ngayMuon FROM DocGia DG, (SELECT * FROM PhieuMuon WHERE  (trangThai=1 OR trangThai=2)) PMT, CT_PhieuMuon CTPMT "
                                + "WHERE DG.maDocGia=PMT.maDocGia AND PMT.maPhieuMuon=CTPMT.maPhieuMuon ORDER BY PMT.ngayMuon";
        xlbang.loadDuLieuVaoBang(jTable_CTmuon1, sql);
    }
    
    public void loadData(){
        jComboBox_maDG.removeAllItems();
        jComboBox_maSach.removeAllItems();
        try {
            ResultSet rs = dbAccess.Query("select maDocGia from DocGia");
            while (rs.next()) {
                jComboBox_maDG.addItem(rs.getString(1));
            }
            
            rs = dbAccess.Query("select maISBN from Sach Where soLuongCon=0");
            while (rs.next()) {
                jComboBox_maSach.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            Logger.getLogger(PnQlyMuonTruoc.class.getName()).log(Level.SEVERE, null, e);
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
    public void themPhieuMuonTruoc(){
        maPhieuMuon= layMaPhieu("1");
        System.out.println("ma: "+maPhieuMuon);
        String sql = "INSERT INTO PhieuMuon(maPhieuMuon,maDocGia,ngayMuon,trangThai,maNV) VALUES ('"+maPhieuMuon+"','"
                                                    +jComboBox_maDG.getSelectedItem().toString()+"','"
                                                    +jTextField_ngayMuon.getText()+"',"
                                                    +"1,'"+ThuVien.primaryKey+"')";
        dbAccess.Update(sql);
    }
    public void them1CTPhieuMuonTruoc(String maSach,int soluong){
        String sql = "INSERT INTO CT_PhieuMuon(maPhieuMuon,maSach,soLuong) VALUES ('"+maPhieuMuon+"','"+maSach+"',"+soluong+")";
        //String sql2="UPDATE Sach SET soLuongCon=soLuongCon-"+soluong+" WHERE maISBN='"+maSach+"'";
        dbAccess.Update(sql);
        //dbAccess.Update(sql2);
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
                vt.add(jSpinner_soLuong.getValue());
                
                dtm.addRow(vt);
                jTable_CTmuon.setModel(dtm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PnQlyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_CTmuon1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_maDG = new javax.swing.JComboBox<>();
        jLabel_tenDG = new javax.swing.JLabel();
        jLabel_tenSach = new javax.swing.JLabel();
        jComboBox_maSach = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_CTmuon = new javax.swing.JTable();
        btnHuy = new javax.swing.JButton();
        jTextField_ngayMuon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSpinner_soLuong = new javax.swing.JSpinner();
        jLabel_slTon = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnXemCT = new javax.swing.JButton();
        btnHuyPhieu = new javax.swing.JButton();
        txtMaPhieu = new javax.swing.JTextField();
        txtMaDG = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTenDG = new javax.swing.JTextField();
        txtNgayMuon = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnHuyPhieu1 = new javax.swing.JButton();

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("?????C GI??? M?????N S??CH TR?????C");
        add(jLabel1);
        jLabel1.setBounds(450, 10, 267, 22);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Danh s??ch ????ng k?? m?????n tr?????c:");
        add(jLabel7);
        jLabel7.setBounds(20, 50, 260, 17);

        jTable_CTmuon1.setAutoCreateRowSorter(true);
        jTable_CTmuon1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? phi???u", "M?? ?????c gi???", "T??n ?????c gi???", "S??T", "Ng??y m?????n tr?????c"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_CTmuon1.setRowHeight(25);
        jTable_CTmuon1.setRowMargin(3);
        jTable_CTmuon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CTmuon1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_CTmuon1);

        add(jScrollPane3);
        jScrollPane3.setBounds(10, 80, 660, 170);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "?????T M?????N TR?????C", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 15))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nh???p m?? ?????c gi???:");

        jComboBox_maDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jComboBox_maDG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_maDGItemStateChanged(evt);
            }
        });

        jLabel_tenDG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_tenDG.setText("L?? V??nh Tr?????ng");

        jLabel_tenSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_tenSach.setText("?????t nh??n t??m");

        jComboBox_maSach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jComboBox_maSach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_maSachItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nh???p m?? s??ch:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("T??n s??ch:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("T??n ?????c gi???:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Danh s??ch s??ch m?????n tr?????c:");

        jTable_CTmuon.setAutoCreateRowSorter(true);
        jTable_CTmuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? s??ch", "T??n s??ch", "Gi??", "Ng??y m?????n", "S??? l?????ng"
            }
        ));
        jTable_CTmuon.setRowHeight(25);
        jTable_CTmuon.setRowMargin(3);
        jScrollPane2.setViewportView(jTable_CTmuon);

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Close_32px.png"))); // NOI18N
        btnHuy.setText("H???y");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jTextField_ngayMuon.setEditable(false);
        jTextField_ngayMuon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Ng??y m?????n:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("S??? l?????ng:");

        jSpinner_soLuong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jSpinner_soLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel_slTon.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel_slTon.setText("60");

        jLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel.setText("/");

        btnXacNhan.setBackground(new java.awt.Color(51, 51, 255));
        btnXacNhan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Check Circle_35px.png"))); // NOI18N
        btnXacNhan.setText("X??c nh???n");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 255, 0));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add_35px.png"))); // NOI18N
        btnThem.setText("Th??m");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jComboBox_maDG, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel_tenDG, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSpinner_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel_slTon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(4, 4, 4)
                                .addComponent(jTextField_ngayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel3)
                            .addGap(8, 8, 8)
                            .addComponent(jComboBox_maSach, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(24, 24, 24)
                    .addComponent(jLabel8)
                    .addGap(4, 4, 4)
                    .addComponent(jLabel_tenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24)
                    .addComponent(jLabel5)
                    .addGap(0, 377, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel_tenDG)
                    .addComponent(jComboBox_maDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSpinner_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jTextField_ngayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_slTon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 6, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel2)
                            .addGap(43, 43, 43)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jComboBox_maSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(80, 80, 80)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel_tenSach))))
                    .addGap(0, 251, Short.MAX_VALUE)))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 280, 1160, 390);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("M?? phi???u:");
        add(jLabel15);
        jLabel15.setBounds(690, 80, 70, 17);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("M?? ?????c gi???:");
        add(jLabel9);
        jLabel9.setBounds(690, 130, 78, 17);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("T??n ?????c gi???:");
        add(jLabel13);
        jLabel13.setBounds(920, 130, 84, 17);

        btnXemCT.setBackground(new java.awt.Color(255, 153, 51));
        btnXemCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/more_details_35px.png"))); // NOI18N
        btnXemCT.setText("Xem Chi ti???t");
        btnXemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemCTActionPerformed(evt);
            }
        });
        add(btnXemCT);
        btnXemCT.setBounds(690, 220, 150, 40);

        btnHuyPhieu.setBackground(new java.awt.Color(255, 51, 51));
        btnHuyPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel_subscription_35px.png"))); // NOI18N
        btnHuyPhieu.setText("H???y Phi???u");
        btnHuyPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyPhieuActionPerformed(evt);
            }
        });
        add(btnHuyPhieu);
        btnHuyPhieu.setBounds(1020, 220, 130, 40);

        txtMaPhieu.setEditable(false);
        add(txtMaPhieu);
        txtMaPhieu.setBounds(780, 70, 130, 30);

        txtMaDG.setEditable(false);
        add(txtMaDG);
        txtMaDG.setBounds(780, 120, 130, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Ng??y m?????n:");
        add(jLabel16);
        jLabel16.setBounds(920, 80, 90, 17);

        txtTenDG.setEditable(false);
        add(txtTenDG);
        txtTenDG.setBounds(1010, 120, 130, 30);

        txtNgayMuon.setEditable(false);
        add(txtNgayMuon);
        txtNgayMuon.setBounds(1010, 70, 130, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("S??T:");
        add(jLabel11);
        jLabel11.setBounds(690, 180, 34, 17);

        txtSDT.setEditable(false);
        add(txtSDT);
        txtSDT.setBounds(780, 170, 130, 30);

        btnHuyPhieu1.setBackground(new java.awt.Color(51, 255, 255));
        btnHuyPhieu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reload.png"))); // NOI18N
        btnHuyPhieu1.setText("T???i l???i");
        btnHuyPhieu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyPhieu1ActionPerformed(evt);
            }
        });
        add(btnHuyPhieu1);
        btnHuyPhieu1.setBounds(870, 220, 130, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_maDGItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_maDGItemStateChanged
        // TODO add your handling code here:
        if(jComboBox_maDG.getItemCount()==0) return;
        jLabel_tenDG.setText(layTen("1", jComboBox_maDG.getSelectedItem().toString()));
        DefaultTableModel dtm=(DefaultTableModel)jTable_CTmuon.getModel();
        dtm.setNumRows(0);
        jTable_CTmuon.setModel(dtm);
    }//GEN-LAST:event_jComboBox_maDGItemStateChanged

    private void jComboBox_maSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_maSachItemStateChanged
        // TODO add your handling code here:
        if(jComboBox_maSach.getItemCount()==0) return;
        jLabel_tenSach.setText(layTen("2", jComboBox_maSach.getSelectedItem().toString()));
        DataBaseAccess db=new DataBaseAccess();
        ResultSet rs0 = db.Query("SELECT soLuong FROM Sach WHERE maISBN='"+jComboBox_maSach.getSelectedItem().toString()+"'");
        try {
            if (rs0.next()) {
                jLabel_slTon.setText(rs0.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PnQlyMuonTruoc.class.getName()).log(Level.SEVERE, null, ex);
        }
        jSpinner_soLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, Integer.parseInt(jLabel_slTon.getText()), 1));
    }//GEN-LAST:event_jComboBox_maSachItemStateChanged

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(jSpinner_soLuong.getValue().toString().equalsIgnoreCase("0")){
            JOptionPane.showMessageDialog(this, "S??? l?????ng kh??ng h???p l???", "Th??ng b??o", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //th??m d??? li???u v??o b???ng
        loadSachVaoBang();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if(jTable_CTmuon.getSelectionModel().isSelectionEmpty()){// ki???m tra ng?????i d??ng ???? click ch???n d??? li???u trong b???ng ch??a
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n chi ti???t s??ch c???n x??a trong b???ng");
            return;
        }
        ((DefaultTableModel)jTable_CTmuon.getModel()).removeRow(jTable_CTmuon.getSelectedRow());
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        themPhieuMuonTruoc();

        for(int i=0;i<jTable_CTmuon.getRowCount();i++){
            them1CTPhieuMuonTruoc(xlbang.getRow(jTable_CTmuon, i,0), Integer.parseInt(xlbang.getRow(jTable_CTmuon, i, 4)));
        }
        JOptionPane.showMessageDialog(this, "????ng k?? m?????n tr?????c th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
        loadDGMT();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
        loadData();
        loadDGMT();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");// /MM/uuuu
        LocalDate localDate = LocalDate.now();
        jTextField_ngayMuon.setText(dtf.format(localDate));
    }//GEN-LAST:event_formHierarchyChanged

    private void jTable_CTmuon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CTmuon1MouseClicked
        // TODO add your handling code here
        txtMaPhieu.setText(xlbang.selectRow(jTable_CTmuon1, 0));
        txtMaDG.setText(xlbang.selectRow(jTable_CTmuon1, 1));
        txtTenDG.setText(xlbang.selectRow(jTable_CTmuon1, 2));
        txtSDT.setText(xlbang.selectRow(jTable_CTmuon1, 3));
        txtNgayMuon.setText(xlbang.selectRow(jTable_CTmuon1, 3));
        
    }//GEN-LAST:event_jTable_CTmuon1MouseClicked

    private void btnXemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemCTActionPerformed
        // TODO add your handling code here:
         if(jTable_CTmuon1.getSelectionModel().isSelectionEmpty()){// ki???m tra ng?????i d??ng ???? click ch???n d??? li???u trong b???ng ch??a
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n Phi???u c???n xem chi ti???t trong b???ng");
            return;
        }
        new frmConvertMuonTruocToMuon(txtMaPhieu.getText(), txtNgayMuon.getText(), txtMaDG.getText(), txtTenDG.getText(), txtSDT.getText()).setVisible(true);
    }//GEN-LAST:event_btnXemCTActionPerformed

    private void btnHuyPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyPhieuActionPerformed
        // TODO add your handling code here:
        if(jTable_CTmuon1.getSelectionModel().isSelectionEmpty()){// ki???m tra ng?????i d??ng ???? click ch???n d??? li???u trong b???ng ch??a
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n Phi???u c???n H???Y trong b???ng");
            return;
        }
        String maPhieu=xlbang.selectRow(jTable_CTmuon1, 0);
        int chon=JOptionPane.showConfirmDialog(this, "X??c nh???n H???y Phi???u: "+maPhieu, "Th??ng B??o",0);
        if(chon==JOptionPane.OK_OPTION){
            String sql="UPDATE PhieuMuon SET trangThai=3 WHERE maPhieuMuon='"+maPhieu+"'";
            dbAccess.Update(sql);
            loadDGMT();
        }
    }//GEN-LAST:event_btnHuyPhieuActionPerformed

    private void btnHuyPhieu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyPhieu1ActionPerformed
        // TODO add your handling code here:
        loadDGMT();
    }//GEN-LAST:event_btnHuyPhieu1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuyPhieu;
    private javax.swing.JButton btnHuyPhieu1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXemCT;
    private javax.swing.JComboBox<String> jComboBox_maDG;
    private javax.swing.JComboBox<String> jComboBox_maSach;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_slTon;
    private javax.swing.JLabel jLabel_tenDG;
    private javax.swing.JLabel jLabel_tenSach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner_soLuong;
    private javax.swing.JTable jTable_CTmuon;
    private javax.swing.JTable jTable_CTmuon1;
    private javax.swing.JTextField jTextField_ngayMuon;
    private javax.swing.JTextField txtMaDG;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenDG;
    // End of variables declaration//GEN-END:variables
}
