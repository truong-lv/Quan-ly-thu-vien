/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.ThuKho;

import Entities.KeSach;
import Entities.Khu;
import Entities.NganKeSach;
import Entities.Sach;
import Entities.ViTri;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Phat
 */
public class TKQuanLyVitri extends javax.swing.JPanel {

//    private List<KeSach> listKeSach = null;
//    private List<NganKeSach> listNganKeSach = null;
    private List<Sach> listSach = null;
    private List<ViTri> listViTri = null;

    private List<KeSach> listKeSach = null;
    private List<NganKeSach> listNganKeSach = null;
    private List<Khu> listKhu = null;

    String button = "";
    Sach selectedSach = null;
    ViTri selectedViTri = null;

    /**
     * Creates new form PnQlyMuonSach
     */
    public TKQuanLyVitri() {
        initComponents();

        listSach = Sach.getList();
        updateBookTable(listSach);
        listKeSach = KeSach.getList();
        listNganKeSach = NganKeSach.getList(listKeSach.get(0).getMaKe());
        listKhu = Khu.getList();
        updateAreaCombobox(listKhu);
        updateBookshelfCombobox(listKeSach);
        updateBookshelfCompartmentCombobox(listNganKeSach);

        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            protected void updateFieldState() {
                String keyword = txtSearch.getText();
                Thread t = new Thread(() -> {
                    if (keyword.isEmpty()) {
                        listSach = Sach.getList();
                    } else {
                        listSach = Sach.search(keyword);
                    }
                    updateBookTable(listSach);

                });
            }
        };
        txtSearch.getDocument().addDocumentListener(dl);
//
//        DocumentListener dl2 = new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                updateFieldState();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                updateFieldState();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                updateFieldState();
//            }
//
//            protected void updateFieldState() {
//                String keyword = txtSearchViTri.getText();
//                if (keyword.isEmpty()) {
//                    listNganKeSach = NganKeSach.getList();
//                } else {
//                    listNganKeSach = NganKeSach.search(keyword);
//                }
//                //updateBookshelfCompartmentTable(listNganKeSach);
//            }
//        };
//
//        txtSearchViTri.getDocument().addDocumentListener(dl2);
    }

    private void updateBookTable(List<Sach> bookList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
        dtm.setRowCount(0);

        for (Sach sach : listSach) {
            //String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), dateFormat.format(sach.getNamXB()), Float.toString(sach.getGiaBia()), Integer.toString(sach.getSoTrang()), sach.getMoTa(), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuong()), Integer.toString(sach.getSoLuongCon()), sach.getNganKeSach().getKeSach().getTenKe(), sach.getNganKeSach().getTenNgan(), sach.getNganKeSach().getKeSach().getKhu().getTenKhu()};
            String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuong()), Integer.toString(sach.getSoLuongCon())};
            dtm.addRow(data);
        }
    }

    private void updateLocationTable(List<ViTri> locationList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        DefaultTableModel dtm = (DefaultTableModel) locationTable.getModel();
        dtm.setRowCount(0);

        for (ViTri viTri : locationList) {
            //String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), dateFormat.format(sach.getNamXB()), Float.toString(sach.getGiaBia()), Integer.toString(sach.getSoTrang()), sach.getMoTa(), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuong()), Integer.toString(sach.getSoLuongCon()), sach.getNganKeSach().getKeSach().getTenKe(), sach.getNganKeSach().getTenNgan(), sach.getNganKeSach().getKeSach().getKhu().getTenKhu()};
            NganKeSach nks = viTri.getNganKeSach();
            String data[] = {nks.getKeSach().getTenKe(), nks.getTenNgan(), nks.getKeSach().getKhu().getTenKhu(), nks.getKeSach().getKhu().getTang()};
            dtm.addRow(data);
        }
    }

//    private void updateBookshelfTable(List<KeSach> listKeSach) {
//        DefaultTableModel dtm = (DefaultTableModel) BookShelfTable.getModel();
//        dtm.setRowCount(0);
//
//        for (KeSach keSach : listKeSach) {
//            String data[] = {keSach.getMaKe(), keSach.getTenKe(), keSach.getThongTin(), keSach.getKhu().getTenKhu()};
//            dtm.addRow(data);
//        }
//    }
//    private void updateBookshelfCompartmentTable(List<NganKeSach> listNganKeSach) {
//        DefaultTableModel dtm2 = (DefaultTableModel) locationTable.getModel();
//        dtm2.setRowCount(0);
//
//        for (NganKeSach nganKeSach : listNganKeSach) {
//            String data[] = {nganKeSach.getMaNganKe(), nganKeSach.getTenNgan()};
//            dtm2.addRow(data);
//        }
//    }
    private void updateAreaCombobox(List<Khu> areaList) {
        for (Khu khu : areaList) {
            cbxKhu.addItem(khu.getTenKhu());
        }
    }

    private void updateBookshelfCombobox(List<KeSach> bookshelfList) {
        for (KeSach keSach : bookshelfList) {
            cbxKeSach.addItem(keSach.getTenKe());
        }
    }

    private void updateBookshelfCompartmentCombobox(List<NganKeSach> bookshelfCompartmentList) {
        for (NganKeSach nganKeSach : listNganKeSach) {
            cbxNganKe.addItem(nganKeSach.getTenNgan());
        }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        locationTable = new javax.swing.JTable();
        btnAdd = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnRefresh = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnDelete = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cbxKhu = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbxKeSach = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cbxNganKe = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtMaISBN = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1155, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Quản lý Vị trí");

        locationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kệ Sách", "Ngăn Kệ", "Khu", "Tầng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        locationTable.setIntercellSpacing(new java.awt.Dimension(1, 3));
        locationTable.setRowHeight(25);
        locationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                locationTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(locationTable);
        if (locationTable.getColumnModel().getColumnCount() > 0) {
            locationTable.getColumnModel().getColumn(0).setResizable(false);
            locationTable.getColumnModel().getColumn(1).setResizable(false);
            locationTable.getColumnModel().getColumn(2).setResizable(false);
            locationTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            locationTable.getColumnModel().getColumn(3).setResizable(false);
            locationTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_add_25px_2.png"))); // NOI18N
        btnAdd.setText("Chọn");
        btnAdd.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reload.png"))); // NOI18N
        btnRefresh.setText("Tải lại");
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_delete_25px_1.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_edit_25px.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ISBN", "Tên sách", "Tác giả", "NXB", "Thể loại", "SL", "SL còn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookTable.setIntercellSpacing(new java.awt.Dimension(1, 3));
        bookTable.setRowHeight(25);
        bookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(bookTable);
        if (bookTable.getColumnModel().getColumnCount() > 0) {
            bookTable.getColumnModel().getColumn(0).setResizable(false);
            bookTable.getColumnModel().getColumn(1).setResizable(false);
            bookTable.getColumnModel().getColumn(2).setResizable(false);
            bookTable.getColumnModel().getColumn(3).setResizable(false);
            bookTable.getColumnModel().getColumn(4).setResizable(false);
            bookTable.getColumnModel().getColumn(5).setResizable(false);
            bookTable.getColumnModel().getColumn(5).setPreferredWidth(20);
            bookTable.getColumnModel().getColumn(6).setResizable(false);
            bookTable.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Khu:");

        cbxKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn khu" }));
        cbxKhu.setEnabled(false);
        cbxKhu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKhuItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbxKhu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel24))
        );

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Kệ sách:");

        cbxKeSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn kệ sách" }));
        cbxKeSach.setEnabled(false);
        cbxKeSach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKeSachItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxKeSach, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxKeSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Ngăn kệ:");

        cbxNganKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn ngăn kệ" }));
        cbxNganKe.setEnabled(false);
        cbxNganKe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNganKeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(cbxNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbxNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel23))
        );

        btnSave.setBackground(new java.awt.Color(0, 102, 102));
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu");
        btnSave.setBorderPainted(false);
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(204, 0, 51));
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Hủy");
        btnCancel.setBorderPainted(false);
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Mã ISBN:");

        txtMaISBN.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(txtMaISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(txtMaISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Tên sách:");

        txtTenSach.setEditable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel11)
                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(492, 492, 492))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd)
                                .addComponent(btnRefresh)))
                        .addGap(14, 14, 14)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit)
                        .addComponent(btnDelete)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void locationTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_locationTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = locationTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        handleLocationTableClick(selectedRow);
    }//GEN-LAST:event_locationTableMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        //editBookShelfTableState();
//        txtMaKe.setText("");
//        txtTenKe.setText("");
//        txtThongTin.setText("");
        editState();
        cbxKhu.setSelectedIndex(0);
        button = "add";
    }//GEN-LAST:event_btnAddMouseClicked

    private Khu getSelectedArea() {
        String selected = cbxKhu.getItemAt(cbxKhu.getSelectedIndex());
        for (Khu khu : listKhu) {
            if (khu.getTenKhu().equals(selected)) {
                return khu;
            }
        }
        return null;
    }

    private KeSach getSelectedBookshelf() {
        String selectedBookshelf = cbxKeSach.getItemAt(cbxKeSach.getSelectedIndex());
        for (KeSach keSach : listKeSach) {
            if (keSach.getTenKe().equals(selectedBookshelf)) {
                return keSach;
            }
        }
        return null;
    }

    private NganKeSach getSelectedBookshelfCapartment() {
        String selected = cbxNganKe.getItemAt(cbxNganKe.getSelectedIndex());
        for (NganKeSach nganKeSach : listNganKeSach) {
            if (nganKeSach.getTenNgan().equals(selected)) {
                return nganKeSach;
            }
        }
        return null;
    }

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        // TODO add your handling code here:
        listSach = Sach.getList();
        updateBookTable(listSach);
//        updateBookTable();
        handleBookTableClick(0);
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void handleBookTableClick(int selectedRow) {
        Sach sach = listSach.get(selectedRow);
        selectedSach = sach;
        txtMaISBN.setText(sach.getMaISBN());
        txtTenSach.setText(sach.getTenSach());
//        txtMaKe.setText(keSach.getMaKe());
//        txtTenKe.setText(keSach.getTenKe());
//        txtThongTin.setText(keSach.getThongTin());

//        for (int i = 0; i < cbxKhu.getItemCount(); i++) {
//            if (cbxKhu.getItemAt(i).equals(keSach.getKhu().getTenKhu())) {
//                cbxKhu.setSelectedIndex(i);
//            }
//        }
//        listNganKeSach = NganKeSach.getList(keSach.getMaKe());
//        updateBookshelfCompartmentTable(listNganKeSach);
        listViTri = ViTri.getList(selectedSach);
        updateLocationTable(listViTri);
        handleLocationTableClick(0);
    }

    private void handleLocationTableClick(int selectedRow) {
//        NganKeSach nganKeSach = listNganKeSach.get(selectedRow);
//        txtMaNganKe.setText(nganKeSach.getMaNganKe());
//        txtTenNganKe.setText(nganKeSach.getTenNgan());

        ViTri vitri = listViTri.get(selectedRow);

        for (int i = 0; i < cbxKhu.getItemCount(); i++) {
            if (cbxKhu.getItemAt(i).equals(vitri.getNganKeSach().getKeSach().getKhu().getTenKhu())) {
                cbxKhu.setSelectedIndex(i);
            }
        }

        for (int i = 0; i < cbxKeSach.getItemCount(); i++) {
            if (cbxKeSach.getItemAt(i).equals(vitri.getNganKeSach().getKeSach().getTenKe())) {
                cbxKeSach.setSelectedIndex(i);
            }
        }

        for (int i = 0; i < cbxNganKe.getItemCount(); i++) {
            if (cbxNganKe.getItemAt(i).equals(vitri.getNganKeSach().getTenNgan())) {
                cbxNganKe.setSelectedIndex(i);
            }
        }
        cbxKeSach.setEnabled(false);
        cbxNganKe.setEnabled(false);
    }

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa vị trí này?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            boolean i = ViTri.delete(selectedViTri);
            if (i == true) {
                showMessage("Xóa thành công");
                normalState();
            } else {
                showMessage("Xóa thất bại");
            }
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // TODO add your handling code here:
        //editBookShelfCompartmentTableState();
        int selectedRow = locationTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        } else {
            selectedViTri = listViTri.get(selectedRow);
            editState();
            button = "edit";
        }
    }//GEN-LAST:event_btnEditMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (cbxKhu.getSelectedIndex() == 0) {
            showMessage("Xin hãy chọn khu");
        } else if (cbxKeSach.getSelectedIndex() == 0) {
            showMessage("Xin hãy chọn kệ sách");
        } else if (cbxNganKe.getSelectedIndex() == 0) {
            showMessage("Xin hãy chọn ngăn kệ");
        } else {
            if (button.equals("add")) {
                ViTri vitri = new ViTri();
                vitri.setNganKeSach(getSelectedBookshelfCapartment());
                vitri.setSach(selectedSach);

                boolean i = ViTri.insert(vitri);
                if (i == true) {
                    showMessage("Thêm thành công");
                    normalState();
                } else {
                    showMessage("Thêm thất bại");
                }
            } else if (button.equals("edit")) {
                ViTri newViTri = new ViTri();
                newViTri.setNganKeSach(getSelectedBookshelfCapartment());
                newViTri.setSach(selectedSach);
                newViTri.setMaVitri(selectedViTri.getMaVitri());

                boolean i = ViTri.update(newViTri);
                if (i == true) {
                    showMessage("Sửa thành công");
                    normalState();
                } else {
                    showMessage("Sửa thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        normalState();
        handleLocationTableClick(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        handleBookTableClick(selectedRow);
        //onBookTableClick(selectedRow);
    }//GEN-LAST:event_bookTableMouseClicked

    private void cbxKeSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKeSachItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            KeSach keSach = getSelectedBookshelf();
            if (keSach == null) {
                cbxNganKe.removeAllItems();
                cbxNganKe.addItem("Chọn ngăn kệ");
                cbxNganKe.setSelectedIndex(0);
                cbxNganKe.setEnabled(false);
            } else {
                cbxNganKe.setEnabled(true);
                listNganKeSach = NganKeSach.getList(keSach.getMaKe());
                cbxNganKe.removeAllItems();

                cbxNganKe.addItem("Chọn ngăn kệ");
                for (NganKeSach nganKeSach : listNganKeSach) {
                    cbxNganKe.addItem(nganKeSach.getTenNgan());
                }
            }
        }
    }//GEN-LAST:event_cbxKeSachItemStateChanged

    private void cbxKhuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKhuItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Khu khu = getSelectedArea();
            if (khu == null) {
                cbxKeSach.removeAllItems();
                cbxKeSach.addItem("Chọn kệ sách");
                cbxKeSach.setSelectedIndex(0);
                cbxKeSach.setEnabled(false);
            } else {
                cbxKeSach.setEnabled(true);
                listKeSach = KeSach.getList(khu.getMaKhu());
                cbxKeSach.removeAllItems();

                cbxKeSach.addItem("Chọn kệ sách");
                for (KeSach keSach : listKeSach) {
                    cbxKeSach.addItem(keSach.getTenKe());
                }
            }
        }
    }//GEN-LAST:event_cbxKhuItemStateChanged

    private void cbxNganKeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNganKeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxNganKeItemStateChanged

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editState() {
        cbxKhu.setEnabled(true);
        cbxKeSach.setEnabled(true);
        cbxNganKe.setEnabled(true);

        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);

        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnRefresh.setEnabled(false);

        locationTable.setEnabled(false);
        bookTable.setEnabled(false);

        txtSearch.setEnabled(false);
        //txtSearchViTri.setEnabled(false);
    }

    private void normalState() {
        cbxKhu.setEnabled(false);
        cbxKeSach.setEnabled(false);
        cbxNganKe.setEnabled(false);

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnRefresh.setEnabled(true);

        locationTable.setEnabled(true);
        bookTable.setEnabled(true);

        txtSearch.setEnabled(true);
        //txtSearchViTri.setEnabled(true);

        Thread t = new Thread(() -> {
            listSach = Sach.getList();
            updateBookTable(listSach);
            handleBookTableClick(0);
        });
        t.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookTable;
    private javax.swing.JLabel btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JComboBox<String> cbxKeSach;
    private javax.swing.JComboBox<String> cbxKhu;
    private javax.swing.JComboBox<String> cbxNganKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable locationTable;
    private javax.swing.JTextField txtMaISBN;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
