/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.ThuThu;

import Code.DataBaseAccess;
import Code.HamXuLyBang;
import Code.KetNoi;
import Code.Sender;
import Code.ThuVien;
import java.awt.HeadlessException;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

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
    private String sdt="";
    
    DataBaseAccess dbAccess;
    Connection ketNoi = KetNoi.layKetNoi();
    HamXuLyBang xlbang;
    
    String maPhieuMuon="";
    double tongTien=0;

    frmConvertMuonTruocToMuon _this=this;
    
    public frmConvertMuonTruocToMuon(String maPhieu, String ngay, String ma, String ten,String dienThoai) {
        initComponents();
        this.maPhieuMuonTruoc=maPhieu;
        this.ngayMuon=ngay;
        this.maDG=ma;
        this.tenDG=ten;
        this.sdt=dienThoai;
        
        txtMaPhieu.setText(maPhieu);
        txtNgayLap.setText(ngayMuon);
        txtMaDG.setText(maDG);
        txtTenDG.setText(tenDG);
        txtSDT.setText(sdt);
        
        dbAccess=new DataBaseAccess();
        xlbang=new HamXuLyBang();
        loadTrangThai();
        loadSachVaoBang();
        
        if(!ktMuon()){
            btnDuyetPhieu.setEnabled(false);
            btnNhanTin.setEnabled(false);
            ngayTra.setEnabled(false);
        }
        else {
            if(txtTrangThai.getText().equalsIgnoreCase("Đang chờ mượn")){
                btnDuyetPhieu.setEnabled(false);
                txtInfor.setText("Sách đã đáp ứng đủ. Hãy nhắn tin xác nhận cho đọc giả");
            }else{
                btnNhanTin.setEnabled(false);
                txtInfor.setText("Nhấn duyệt phiếu để xác nhận mượn cho độc giả");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");// /MM/uuuu
                LocalDate localDate = LocalDate.now();
                txtNgayMuon.setText(dtf.format(localDate));
            }
            
            
        }
        
        //Adđ Event change ngayTra input
        ((JTextField)ngayTra.getDateEditor().getUiComponent()).getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            public void removeUpdate(DocumentEvent e) {
              warn();
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }

            public void warn() {
                if(jTable_CTmuon.getSelectionModel().isSelectionEmpty()){// kiểm tra người dùng đã click chọn dữ liệu trong bảng chưa
                    JOptionPane.showMessageDialog(_this, "Vui lòng Sách cần nhập trong bảng");
                    //((JTextField)ngayTra.getDateEditor().getUiComponent()).setText(null);
                    return;
                }
                jTable_CTmuon.setValueAt(((JTextField)ngayTra.getDateEditor().getUiComponent()).getText(), jTable_CTmuon.getSelectedRow(), 3);
            }
          }); 
    }
    public void loadTrangThai(){
        String sql = "SELECT trangThai FROM PhieuMuon Where maPhieuMuon='"+this.maPhieuMuonTruoc+"'";
        ResultSet rs =dbAccess.Query(sql);
        try {
            if(rs.next()){
                String trangThai=(rs.getString(1).equalsIgnoreCase("1"))?"Đang chờ mượn":"Đã gửi tin nhắn";
                txtTrangThai.setText(trangThai);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmConvertMuonTruocToMuon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadSachVaoBang(){
        String sql = "SELECT maISBN, tenSach, giaBia, tienCoc=giaBia*0.7, PMT.soLuong "
                + "FROM (SELECT * FROM CT_PhieuMuon Where maPhieuMuon='"+this.maPhieuMuonTruoc+"') PMT, SACH "
                + "WHERE PMT.maSach=maISBN";
        ResultSet rs =dbAccess.Query(sql);
        try {
            while(rs.next()){
                DefaultTableModel dtm=(DefaultTableModel)jTable_CTmuon.getModel();
                Vector vt=new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3).substring(0,rs.getString(3).indexOf(".")));
                vt.add("");
                vt.add(rs.getString(4).substring(0,rs.getString(4).indexOf(".")));
                vt.add(rs.getString(5));
                
                tongTien+=(Double.parseDouble(rs.getString(4)));
                dtm.addRow(vt);
                jTable_CTmuon.setModel(dtm);
            }
            lbTongTien.setText(String.valueOf(tongTien).substring(0,String.valueOf(tongTien).indexOf(".")));
        } catch (SQLException ex) {
            Logger.getLogger(PnQlyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String sql="SELECT maCTPhieuMuon From CT_PhieuMuon Where maPhieuMuon='"+this.maPhieuMuonTruoc+"'";
        ResultSet rs =dbAccess.Query(sql);
        List<String> listCtMuonTruoc=new ArrayList<String>();
        try {
            while(rs.next()){
                listCtMuonTruoc.add(rs.getString(1));
            }
            String sql2="";
            for(int i=0;i<listCtMuonTruoc.size();i++){
                sql2="SELECT maCTPhieuMuon From CT_PhieuMuon CT, Sach S Where maCTPhieuMuon="+listCtMuonTruoc.get(i)+" and maSach=maISBN and soLuongCon>=CT.soLuong";
                rs =dbAccess.Query(sql2);
                //tính tổng tiền
                
                if(!rs.next()){
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
        String sql = "UPDATE PhieuMuon SET trangThai=0, tongTien="+tongTien+" ,ngayMuon='"+txtNgayMuon.getText()+"' WHERE maPhieuMuon=N'"+maPhieuMuonTruoc+"'";
        dbAccess.Update(sql);
    }
    public void them1CTPhieuMuon(String maSach, String ngaytra){
        String sql = "UPDATE CT_PhieuMuon SET ngayTra='"+ngaytra+"' WHERE maPhieuMuon='"+maPhieuMuonTruoc+"'";
        String sql2="UPDATE Sach SET soLuongCon=soLuongCon-1 WHERE maISBN='"+maSach+"'";
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
        jLabel6 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        txtInfor = new javax.swing.JLabel();
        btnNhanTin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        ngayTra.setMinSelectableDate(new Date());

        txtNgayMuon.setEditable(false);
        txtNgayMuon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jTable_CTmuon.setAutoCreateRowSorter(true);
        jTable_CTmuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Giá", "Ngày trả", "Tổng cọc", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        txtInfor.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtInfor.setForeground(new java.awt.Color(255, 102, 102));
        txtInfor.setText("(Hiện tại không thể duyệt đơn do không đủ sách để đáp ứng !!!)");

        btnNhanTin.setBackground(new java.awt.Color(255, 204, 51));
        btnNhanTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/received_32px.png"))); // NOI18N
        btnNhanTin.setText("Nhắn tin xác nhận");
        btnNhanTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanTinActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("SĐT:");

        txtSDT.setEditable(false);
        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Trạng thái:");

        txtTrangThai.setEditable(false);
        txtTrangThai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(9, 9, 9)
                                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenSach))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jLabel11)
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtInfor)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel12)
                                        .addGap(5, 5, 5)
                                        .addComponent(ngayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnNhanTin)
                        .addGap(26, 26, 26)
                        .addComponent(btnDuyetPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lbTongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(ngayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(txtInfor)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDuyetPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhanTin, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnDuyetPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuyetPhieuActionPerformed
        
        //Kiểm tra đã nhập đầy đủ ngày trả chưa
        for(int i=0;i<jTable_CTmuon.getRowCount();i++){
            if(xlbang.selectRow(jTable_CTmuon, 3).isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày trả đầy đủ cho các sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        
        //okie thì tiến hành thêm phiếu mượn
        themPhieuMuon();
        for(int i=0;i<jTable_CTmuon.getRowCount();i++){
            them1CTPhieuMuon(xlbang.getRow(jTable_CTmuon, i,0), xlbang.getRow(jTable_CTmuon, i, 3));
        }
        
        //thông báo thành công
        JOptionPane.showMessageDialog(this, "Mượn sách thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }//GEN-LAST:event_btnDuyetPhieuActionPerformed

    private void jTable_CTmuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CTmuonMouseClicked
        // TODO add your handling code here:
        txtMaSach.setText(xlbang.selectRow(jTable_CTmuon, 0));
        txtTenSach.setText(xlbang.selectRow(jTable_CTmuon, 1));
        ((JTextField)ngayTra.getDateEditor().getUiComponent()).setText(xlbang.selectRow(jTable_CTmuon, 3));
        
    }//GEN-LAST:event_jTable_CTmuonMouseClicked

    private void btnNhanTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanTinActionPerformed
        // TODO add your handling code here:
        try {
            //Gửi tin nhắn
            Sender sender=new Sender();
            String content="Xin chào "+this.tenDG+ ", Hiện tại hệ thống THƯ VIỆN 10Đ đã có đủ Sách để đáp ứng Phiếu mượn trước của bạn có mã là "+this.maPhieuMuonTruoc+". Vui lòng đến quầy để làm thủ tục mượn sách";
            sender.sendSms(this.sdt, content);
            
            //Cập nhập trạng thái
            dbAccess.Update("UPDATE PhieuMuon Set trangThai=2 WHERE maPhieuMuon='"+maPhieuMuonTruoc+"'");
            
            //Thông báo thành công
            JOptionPane.showMessageDialog(this, "Gửi tin nhắn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
            //Mở chức năng duyệt
            txtInfor.setText("Nhấn duyệt phiếu để xác nhận mượn cho độc giả");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");// /MM/uuuu
            LocalDate localDate = LocalDate.now();
            txtTrangThai.setText("Đã gửi tin nhắn");
            txtNgayMuon.setText(dtf.format(localDate));
            ngayTra.setEnabled(true);
            btnNhanTin.setEnabled(false);
            btnDuyetPhieu.setEnabled(true);
            txtInfor.setText("Nhấn duyệt phiếu để xác nhận mượn cho độc giả");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gửi tin nhắn thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnNhanTinActionPerformed

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
    private javax.swing.JButton btnNhanTin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel txtInfor;
    private javax.swing.JTextField txtMaDG;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenDG;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
