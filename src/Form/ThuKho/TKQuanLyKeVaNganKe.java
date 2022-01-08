/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.ThuKho;

import Entities.KeSach;
import Entities.Khu;
import Entities.LoaiCoSoVatChat;
import Entities.NganKeSach;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Phat
 */
public class TKQuanLyKeVaNganKe extends javax.swing.JPanel {

    private List<KeSach> bookshelfList = null;
    private List<NganKeSach> bookshelfCompartmentList = null;
    private List<Khu> areaList = null;
    String button = "";
    KeSach selectedKeSach = null;

    /**
     * Creates new form PnQlyMuonSach
     */
    public TKQuanLyKeVaNganKe() {
        initComponents();

        bookshelfList = KeSach.getList();
        bookshelfCompartmentList = NganKeSach.getList(bookshelfList.get(0).getMaKe());
        areaList = Khu.getList();
        updateBookshelfTable(bookshelfList);
        updateBookshelfCompartmentTable(bookshelfCompartmentList);
        updateAreaCombobox(areaList);
        updateBookshelfCombobox(bookshelfList);

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
                if (keyword.isEmpty()) {
                    bookshelfList = KeSach.getList();
                } else {
                    bookshelfList = KeSach.search(keyword);
                }

                updateBookshelfTable(bookshelfList);
            }
        };
        txtSearch.getDocument().addDocumentListener(dl);

        DocumentListener dl2 = new DocumentListener() {
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
                String keyword = txtSearchNganKe.getText();
                if (keyword.isEmpty()) {
                    bookshelfCompartmentList = NganKeSach.getList();
                } else {
                    bookshelfCompartmentList = NganKeSach.search(keyword);
                }
                updateBookshelfCompartmentTable(bookshelfCompartmentList);
            }
        };

        txtSearchNganKe.getDocument().addDocumentListener(dl2);
    }

    private void updateBookshelfTable(List<KeSach> bookshelfList) {
        DefaultTableModel dtm = (DefaultTableModel) BookShelfTable.getModel();
        dtm.setRowCount(0);

        for (KeSach keSach : bookshelfList) {
            String data[] = {keSach.getMaKe(), keSach.getTenKe(), keSach.getThongTin(), keSach.getKhu().getTenKhu()};
            dtm.addRow(data);
        }
    }

    private void updateBookshelfCompartmentTable(List<NganKeSach> bookshelfCompartmentList) {
        DefaultTableModel dtm2 = (DefaultTableModel) BookshelfCompartmentTable.getModel();
        dtm2.setRowCount(0);

        for (NganKeSach nganKeSach : bookshelfCompartmentList) {
            String data[] = {nganKeSach.getMaNganKe(), nganKeSach.getTenNgan()};
            dtm2.addRow(data);
        }
    }

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

    private Khu getSelectedArea() {
        String selected = cbxKhu.getItemAt(cbxKhu.getSelectedIndex());
        for (Khu khu : areaList) {
            if (khu.getTenKhu().equals(selected)) {
                return khu;
            }
        }
        return null;
    }

    private KeSach getSelectedBookShelf() {
        String selected = cbxKeSach.getItemAt(cbxKeSach.getSelectedIndex());
        for (KeSach keSach : bookshelfList) {
            if (keSach.getTenKe().equals(selected)) {
                return keSach;
            }
        }
        return null;
    }

    private String generateBookshelfId() {
        List<KeSach> bookshelves = KeSach.getList();
        if (bookshelves.size() == 0) {
            return Integer.toString(bookshelves.size());
        } else {
            KeSach theLastOne = bookshelves.get(bookshelves.size() - 1);
            return Integer.toString(Integer.parseInt(theLastOne.getMaKe()) + 1);
        }
    }

    private String generateBookshelfCompartmentId() {
        List<NganKeSach> list = NganKeSach.getList();
        if (list.size() == 0) {
            return Integer.toString(list.size());
        } else {
            NganKeSach theLastOne = list.get(list.size() - 1);
            return Integer.toString(Integer.parseInt(theLastOne.getMaNganKe()) + 1);
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
        BookshelfCompartmentTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTenKe = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtMaKe = new javax.swing.JTextField();
        btnAdd = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRefresh = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtThongTin = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        BookShelfTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMaNganKe = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTenNganKe = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cbxKhu = new javax.swing.JComboBox<>();
        btnDeleteNganKe = new javax.swing.JLabel();
        btnEditNganKe = new javax.swing.JLabel();
        btnAddNganKe = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnSearch1 = new javax.swing.JLabel();
        txtSearchNganKe = new javax.swing.JTextField();
        btnSaveNganKe = new javax.swing.JButton();
        btnCancelNganKe = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbxKeSach = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1155, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Quản lý Kệ sách & Ngăn kệ");

        BookshelfCompartmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ngăn kệ", "Tên ngăn kệ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BookshelfCompartmentTable.setIntercellSpacing(new java.awt.Dimension(1, 3));
        BookshelfCompartmentTable.setRowHeight(25);
        BookshelfCompartmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookshelfCompartmentTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BookshelfCompartmentTable);
        if (BookshelfCompartmentTable.getColumnModel().getColumnCount() > 0) {
            BookshelfCompartmentTable.getColumnModel().getColumn(0).setResizable(false);
            BookshelfCompartmentTable.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Tên kệ:");

        txtTenKe.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTenKe, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8)
                .addComponent(txtTenKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Mã kệ:");

        txtMaKe.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMaKe, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9)
                .addComponent(txtMaKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_add_25px_2.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
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

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_delete_25px_1.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
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
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Thông tin:");

        txtThongTin.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(txtThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(txtThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        BookShelfTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Kệ", "Tên Kệ", "Thông tin", "Khu"
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
        BookShelfTable.setIntercellSpacing(new java.awt.Dimension(1, 3));
        BookShelfTable.setRowHeight(25);
        BookShelfTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookShelfTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(BookShelfTable);
        if (BookShelfTable.getColumnModel().getColumnCount() > 0) {
            BookShelfTable.getColumnModel().getColumn(0).setResizable(false);
            BookShelfTable.getColumnModel().getColumn(1).setResizable(false);
            BookShelfTable.getColumnModel().getColumn(2).setResizable(false);
            BookShelfTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Mã ngăn kệ:");

        txtMaNganKe.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(txtMaNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Tên ngăn kệ:");

        txtTenNganKe.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13)
                .addComponent(txtTenNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Khu:");

        cbxKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Khu" }));
        cbxKhu.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxKhu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnDeleteNganKe.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDeleteNganKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_delete_25px_1.png"))); // NOI18N
        btnDeleteNganKe.setText("Xóa");
        btnDeleteNganKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteNganKeMouseClicked(evt);
            }
        });

        btnEditNganKe.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditNganKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_edit_25px.png"))); // NOI18N
        btnEditNganKe.setText("Sửa");
        btnEditNganKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditNganKeMouseClicked(evt);
            }
        });

        btnAddNganKe.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAddNganKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_add_25px_2.png"))); // NOI18N
        btnAddNganKe.setText("Thêm");
        btnAddNganKe.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnAddNganKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddNganKeMouseClicked(evt);
            }
        });

        btnSearch1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSearch1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtSearchNganKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnSaveNganKe.setBackground(new java.awt.Color(0, 102, 102));
        btnSaveNganKe.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSaveNganKe.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveNganKe.setText("Lưu");
        btnSaveNganKe.setBorderPainted(false);
        btnSaveNganKe.setEnabled(false);
        btnSaveNganKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNganKeActionPerformed(evt);
            }
        });

        btnCancelNganKe.setBackground(new java.awt.Color(204, 0, 51));
        btnCancelNganKe.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelNganKe.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelNganKe.setText("Hủy");
        btnCancelNganKe.setBorderPainted(false);
        btnCancelNganKe.setEnabled(false);
        btnCancelNganKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelNganKeActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Kệ Sách:");

        cbxKeSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Kệ sách" }));
        cbxKeSach.setEnabled(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxKeSach, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbxKeSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel16))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddNganKe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditNganKe)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeleteNganKe)
                                .addGap(43, 43, 43)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 50, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(443, 443, 443))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnSaveNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(btnCancelNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSaveNganKe)
                        .addComponent(btnCancelNganKe))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnAdd)
                                            .addComponent(btnEdit)
                                            .addComponent(btnDelete)
                                            .addComponent(btnRefresh)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnAddNganKe)
                                            .addComponent(btnEditNganKe)
                                            .addComponent(btnDeleteNganKe))
                                        .addGap(1, 1, 1)))
                                .addGap(14, 14, 14)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSave))
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnCancel))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38))
                                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BookshelfCompartmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookshelfCompartmentTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = BookshelfCompartmentTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        handleBookshelfCompartmentTableClick(selectedRow);
    }//GEN-LAST:event_BookshelfCompartmentTableMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        normalState();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        editBookShelfTableState();
        txtMaKe.setText("");
        txtTenKe.setText("");
        txtThongTin.setText("");
        cbxKhu.setSelectedIndex(0);
        button = "add";
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // TODO add your handling code here:
        editBookShelfTableState();
        button = "edit";
    }//GEN-LAST:event_btnEditMouseClicked

    private void editBookShelfTableState() {
        txtTenKe.setEditable(true);
        txtThongTin.setEditable(true);
        cbxKhu.setEnabled(true);

        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);

        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnRefresh.setEnabled(false);
        BookShelfTable.setEnabled(false);
    }

    private void editBookShelfCompartmentTableState() {
        txtTenNganKe.setEditable(true);
        cbxKeSach.setEnabled(true);

        btnSaveNganKe.setEnabled(true);
        btnCancelNganKe.setEnabled(true);

        btnAddNganKe.setEnabled(false);
        btnEditNganKe.setEnabled(false);
        btnDeleteNganKe.setEnabled(false);
        btnRefresh.setEnabled(false);
        BookshelfCompartmentTable.setEnabled(false);
    }

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa kệ sách này?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            boolean i = KeSach.delete(txtMaKe.getText());
            if (i == true) {
                showMessage("Xóa thành công");
                normalState();
            } else {
                showMessage("Xóa thất bại");
            }
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

        if (txtTenKe.getText().isEmpty()) {
            showMessage("Tên khu không được để trống");
        } else if (cbxKhu.getSelectedIndex() == 0) {
            showMessage("Xin hãy chọn Khu");
        } else {
            if (button.equals("add")) {
                boolean i = KeSach.insert(txtTenKe.getText(), txtThongTin.getText(), getSelectedArea().getMaKhu());
                if (i == true) {
                    showMessage("Thêm thành công");
                    normalState();
                } else {
                    showMessage("Thêm thất bại");
                }
            } else if (button.equals("edit")) {
                boolean i = KeSach.update(txtMaKe.getText(), txtTenKe.getText(), txtThongTin.getText(), getSelectedArea().getMaKhu());
                if (i == true) {
                    showMessage("Sửa thành công");
                    normalState();
                } else {
                    showMessage("Sửa thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        // TODO add your handling code here:
        bookshelfList = KeSach.getList();
        bookshelfCompartmentList = NganKeSach.getList(bookshelfList.get(0).getMaKe());
        updateBookshelfTable(bookshelfList);
        updateBookshelfCompartmentTable(bookshelfCompartmentList);
        handleBookshelfTableClick(0);
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void handleBookshelfTableClick(int selectedRow) {
        KeSach keSach = bookshelfList.get(selectedRow);
        selectedKeSach = keSach;
        txtMaKe.setText(keSach.getMaKe());
        txtTenKe.setText(keSach.getTenKe());
        txtThongTin.setText(keSach.getThongTin());

        for (int i = 0; i < cbxKhu.getItemCount(); i++) {
            if (cbxKhu.getItemAt(i).equals(keSach.getKhu().getTenKhu())) {
                cbxKhu.setSelectedIndex(i);
            }
        }

        bookshelfCompartmentList = NganKeSach.getList(keSach.getMaKe());
        updateBookshelfCompartmentTable(bookshelfCompartmentList);
    }

    private void handleBookshelfCompartmentTableClick(int selectedRow) {
        NganKeSach nganKeSach = bookshelfCompartmentList.get(selectedRow);
        txtMaNganKe.setText(nganKeSach.getMaNganKe());
        txtTenNganKe.setText(nganKeSach.getTenNgan());
    }

    private void BookShelfTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookShelfTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = BookShelfTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        handleBookshelfTableClick(selectedRow);
    }//GEN-LAST:event_BookShelfTableMouseClicked

    private void btnDeleteNganKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteNganKeMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa ngăn kệ sách này?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            boolean i = NganKeSach.delete(txtMaNganKe.getText());
            if (i == true) {
                showMessage("Xóa thành công");
                normalState();
            } else {
                showMessage("Xóa thất bại");
            }
        }
    }//GEN-LAST:event_btnDeleteNganKeMouseClicked

    private void btnEditNganKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditNganKeMouseClicked
        // TODO add your handling code here:
        editBookShelfCompartmentTableState();
        button = "editNganKe";
    }//GEN-LAST:event_btnEditNganKeMouseClicked

    private void btnAddNganKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddNganKeMouseClicked
        // TODO add your handling code here:
        txtMaNganKe.setText("");
        txtTenNganKe.setText("");
        cbxKeSach.setSelectedIndex(0);
        editBookShelfCompartmentTableState();
        button = "addNganKe";
    }//GEN-LAST:event_btnAddNganKeMouseClicked

    private void btnSaveNganKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNganKeActionPerformed
        // TODO add your handling code here:
        if (txtTenNganKe.getText().isEmpty()) {
            showMessage("Tên ngăn kệ không được để trống");
        } else if (cbxKeSach.getSelectedIndex() == 0) {
            showMessage("Xin hãy chọn Kệ sách");
        } else {
            if (button.equals("addNganKe")) {
                boolean i = NganKeSach.insert(txtTenNganKe.getText(), getSelectedBookShelf().getMaKe());
                if (i == true) {
                    showMessage("Thêm thành công");
                    normalState();
                } else {
                    showMessage("Thêm thất bại");
                }
            } else if (button.equals("editNganKe")) {
                boolean i = NganKeSach.update(txtMaNganKe.getText(), txtTenNganKe.getText(), getSelectedBookShelf().getMaKe());
                if (i == true) {
                    showMessage("Sửa thành công");
                    normalState();
                } else {
                    showMessage("Sửa thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSaveNganKeActionPerformed

    private void btnCancelNganKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelNganKeActionPerformed
        // TODO add your handling code here:
        normalState();
    }//GEN-LAST:event_btnCancelNganKeActionPerformed

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void normalState() {
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        BookShelfTable.setEnabled(true);

        btnAddNganKe.setEnabled(true);
        btnEditNganKe.setEnabled(true);
        btnDeleteNganKe.setEnabled(true);
        BookshelfCompartmentTable.setEnabled(true);

        txtMaKe.setEditable(false);
        txtTenKe.setEditable(false);
        txtThongTin.setEditable(false);
        cbxKhu.setEnabled(true);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

        txtMaNganKe.setEditable(false);
        txtTenNganKe.setEditable(true);
        cbxKeSach.setEnabled(true);
        btnSaveNganKe.setEnabled(true);
        btnCancelNganKe.setEnabled(true);

        btnRefresh.setEnabled(true);

        bookshelfList = KeSach.getList();
        updateBookshelfTable(bookshelfList);
        handleBookshelfTableClick(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BookShelfTable;
    private javax.swing.JTable BookshelfCompartmentTable;
    private javax.swing.JLabel btnAdd;
    private javax.swing.JLabel btnAddNganKe;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancelNganKe;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnDeleteNganKe;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnEditNganKe;
    private javax.swing.JLabel btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveNganKe;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnSearch1;
    private javax.swing.JComboBox<String> cbxKeSach;
    private javax.swing.JComboBox<String> cbxKhu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtMaKe;
    private javax.swing.JTextField txtMaNganKe;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchNganKe;
    private javax.swing.JTextField txtTenKe;
    private javax.swing.JTextField txtTenNganKe;
    private javax.swing.JTextField txtThongTin;
    // End of variables declaration//GEN-END:variables
}
