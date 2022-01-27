/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.DocGia;

import Code.ThuVien;
import Entities.ChiTietMuonTruoc;
import Entities.DocGia;
import Entities.KeSach;
import Entities.Khu;
import Entities.NganKeSach;
import Entities.NhaXuatBan;
import Entities.NhanVien;
import Entities.PhieuMuonTruoc;
import Entities.Sach;
import Entities.TacGia;
import Entities.TheLoaiSach;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Phat
 */
public class DocGiaDKMuonTruoc extends javax.swing.JPanel {

    private List<Sach> listSach = null;
    private List<Sach> listSachMuonTruoc = null;
    private List<TacGia> listTacGia = null;
    private List<TheLoaiSach> listTheLoaiSach = null;
    private List<Khu> listKhu = null;
    private List<KeSach> listKeSach = null;
    private List<NganKeSach> listNganKeSach = null;
    private List<NhaXuatBan> listNXB = null;
    private Sach sach = new Sach();
    private PhieuMuonTruoc phieuMuonTruoc = new PhieuMuonTruoc();
    private List<ChiTietMuonTruoc> listChiTietMuonTruoc = new ArrayList<>();

    private Khu khu = null;
    private KeSach keSach = null;
    private NganKeSach nganKeSach = null;

    String button = "";

    /**
     * Creates new form PnQlyMuonSach
     */
    public DocGiaDKMuonTruoc() {
        initComponents();

        listSach = Sach.getList();
        updateTable();
        updateRegisterTable();
        updateCombobox();

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
                    listSach = Sach.getList();
                } else {
                    listSach = Sach.search(keyword);
                }

                updateTable();
            }
        };

        txtSearch.getDocument().addDocumentListener(dl);
    }

    private void updateTable() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);

        List<Sach> removedSach = new ArrayList<>();

        for (Sach sach : listSach) {
            if (sach.getSoLuongCon() == 0) {
                String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), sach.getTheLoaiSach().getTenTheLoai(), sach.getNganKeSach().getKeSach().getTenKe(), sach.getNganKeSach().getKeSach().getKhu().getTenKhu()};
                dtm.addRow(data);
            } else {
                removedSach.add(sach);
            }
        }

        for (Sach s : removedSach) {
            listSach.remove(s);
        }
    }

    private void updateRegisterTable() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        DefaultTableModel dtm = (DefaultTableModel) tableMuonTruoc.getModel();
        dtm.setRowCount(0);

        for (ChiTietMuonTruoc chiTietMuonTruoc : listChiTietMuonTruoc) {
            String data[] = {chiTietMuonTruoc.getSach().getMaISBN(), chiTietMuonTruoc.getSach().getTenSach(), Integer.toString(chiTietMuonTruoc.getSoLuong())};
            dtm.addRow(data);
        }

        checkRegisterTable();
    }

    private void updateCombobox() {
        listTacGia = TacGia.getList();
        listNXB = NhaXuatBan.getList();
        listTheLoaiSach = TheLoaiSach.getList();
//        listKeSach = KeSach.getList();
//        listNganKeSach = NganKeSach.getList();
//        listKhu = Khu.getList();

        for (TacGia tacGia : listTacGia) {
            cbxTacGia.addItem(tacGia.getTenTacGia());
        }

        for (NhaXuatBan nxb : listNXB) {
            cbxNXB.addItem(nxb.getTenNXB());
        }

        for (TheLoaiSach theLoaiSach : listTheLoaiSach) {
            cbxTheLoai.addItem(theLoaiSach.getTenTheLoai());
        }

//        for (Khu khu : listKhu) {
//            cbxKhu.addItem(khu.getTenKhu());
//        }
//
//        for (KeSach keSach : listKeSach) {
//            cbxKeSach.addItem(keSach.getTenKe());
//        }
//        for (NganKeSach nganKeSach : listNganKeSach) {
//            cbxNganKe.addItem(nganKeSach.getTenNgan());
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel17 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cbxNganKe = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbxKeSach = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cbxKhu = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnRefresh = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableMuonTruoc = new javax.swing.JTable();
        btnAdd = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        btnFinish = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtMaISBN = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cbxNXB = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cbxTacGia = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cbxTheLoai = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

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
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxNganKe, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxNganKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(cbxKeSach, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxKeSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Khu:");

        cbxKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn khu" }));
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
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxKhu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setPreferredSize(new java.awt.Dimension(1155, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Đăng ký mượn trước");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ISBN", "Tên sách", "Tác giả", "NXB", "Thể loại", "Kệ Sách", "Khu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setIntercellSpacing(new java.awt.Dimension(1, 3));
        table.setRowHeight(25);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(50);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(120);
        }

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
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tableMuonTruoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ISBN", "Tên sách", "SL đặt trước"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMuonTruoc.setIntercellSpacing(new java.awt.Dimension(1, 3));
        tableMuonTruoc.setRowHeight(25);
        tableMuonTruoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMuonTruocMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableMuonTruoc);
        if (tableMuonTruoc.getColumnModel().getColumnCount() > 0) {
            tableMuonTruoc.getColumnModel().getColumn(0).setResizable(false);
            tableMuonTruoc.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableMuonTruoc.getColumnModel().getColumn(1).setResizable(false);
            tableMuonTruoc.getColumnModel().getColumn(2).setResizable(false);
            tableMuonTruoc.getColumnModel().getColumn(2).setPreferredWidth(20);
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

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("SL đặt trước:");

        txtSoLuong.setEditable(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel20)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Tên Sách:");

        txtTenSach.setEditable(false);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel25)
                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnFinish.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnFinish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/check.png"))); // NOI18N
        btnFinish.setText("Hoàn Thành");
        btnFinish.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFinishMouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Mã ISBN:");

        txtMaISBN.setEditable(false);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMaISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel26)
                .addComponent(txtMaISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("NXB:");

        cbxNXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn NXB" }));
        cbxNXB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNXBItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(cbxNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxNXB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Tác giả:");

        cbxTacGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn tác giả" }));
        cbxTacGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTacGiaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(cbxTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxTacGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Thể loại:");

        cbxTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn thể loại" }));
        cbxTheLoai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTheLoaiItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(cbxTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxTheLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel2.setText("Lọc Tìm kiếm");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel3.setText("Chi tiết mượn trước");

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
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnFinish))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(468, 468, 468))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRefresh)
                                .addComponent(btnAdd))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit)
                        .addComponent(btnDelete)
                        .addComponent(btnFinish)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        onBookTableClick(selectedRow);
    }//GEN-LAST:event_tableMouseClicked

    private void onBookTableClick(int selectedRow) {
        Sach sach = listSach.get(selectedRow);
        txtMaISBN.setText(sach.getMaISBN());
        txtTenSach.setText(sach.getTenSach());

        ChiTietMuonTruoc chiTietMuonTruoc = null;
        for (ChiTietMuonTruoc ctmt : listChiTietMuonTruoc) {
            if (ctmt.getSach().equals(sach)) {
                chiTietMuonTruoc = ctmt;
            }
        }

        if (chiTietMuonTruoc == null) {
            txtSoLuong.setText("");
        } else {
            txtSoLuong.setText(Integer.toString(chiTietMuonTruoc.getSoLuong()));
        }
//        txtGiaBia.setText(Float.toString(sach.getGiaBia()));
//        txtMoTa.setText(sach.getMoTa());
        //txtSoLuong.setText(Integer.toString(sach.getSoLuong()));
//        txtSoLuongCon.setText(Integer.toString(sach.getSoLuongCon()));
//        txtSoTrang.setText(Integer.toString(sach.getSoTrang()));

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//        dpNamXB.setYear(Integer.parseInt(dateFormat.format(sach.getNamXB())));
//        for (int i = 0; i < cbxTacGia.getItemCount(); i++) {
//            if (cbxTacGia.getItemAt(i).equals(sach.getTacGia().getTenTacGia())) {
//                cbxTacGia.setSelectedIndex(i);
//            }
//        }
//
//        for (int i = 0; i < cbxNXB.getItemCount(); i++) {
//            if (cbxNXB.getItemAt(i).equals(sach.getNhaXuatBan().getTenNXB())) {
//                cbxNXB.setSelectedIndex(i);
//            }
//        }
//
//        for (int i = 0; i < cbxTheLoai.getItemCount(); i++) {
//            if (cbxTheLoai.getItemAt(i).equals(sach.getTheLoaiSach().getTenTheLoai())) {
//                cbxTheLoai.setSelectedIndex(i);
//            }
//        }
//
//        for (int i = 0; i < cbxNganKe.getItemCount(); i++) {
//            if (cbxNganKe.getItemAt(i).equals(sach.getNganKeSach().getTenNgan())) {
//                cbxNganKe.setSelectedIndex(i);
//            }
//        }
//
//        for (int i = 0; i < cbxKeSach.getItemCount(); i++) {
//            if (cbxKeSach.getItemAt(i).equals(sach.getNganKeSach().getKeSach().getTenKe())) {
//                cbxKeSach.setSelectedIndex(i);
//            }
//        }
    }

    private void onRegisterTableClick(int selectedRow) {
        ChiTietMuonTruoc chiTietMuonTruoc = listChiTietMuonTruoc.get(selectedRow);
        txtMaISBN.setText(chiTietMuonTruoc.getSach().getMaISBN());
        txtTenSach.setText(chiTietMuonTruoc.getSach().getTenSach());
        txtSoLuong.setText(Integer.toString(chiTietMuonTruoc.getSoLuong()));
    }

    private Sach getSelectedBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            return listSach.get(0);
        } else {
            Sach s = listSach.get(selectedRow);
            return s;
        }
    }

    private ChiTietMuonTruoc getSelectedChiTietMuonTruoc() {
        int selectedRow = tableMuonTruoc.getSelectedRow();
        if (selectedRow < 0) {
            return listChiTietMuonTruoc.get(0);
        } else {
            return listChiTietMuonTruoc.get(selectedRow);
        }
    }

    private TacGia getSelectedAuthor() {
        String selectedAuthor = cbxTacGia.getItemAt(cbxTacGia.getSelectedIndex());
        for (TacGia tacGia : listTacGia) {
            if (tacGia.getTenTacGia().equals(selectedAuthor)) {
                return tacGia;
            }
        }
        return null;
    }

    private TheLoaiSach getSelectedCategory() {
        String selectedCategory = cbxTheLoai.getItemAt(cbxTheLoai.getSelectedIndex());
        for (TheLoaiSach theLoaiSach : listTheLoaiSach) {
            if (theLoaiSach.getTenTheLoai().equals(selectedCategory)) {
                return theLoaiSach;
            }
        }
        return null;
    }

    private NhaXuatBan getSelectedPublishingHouse() {
        String selectedPublishingHouse = cbxNXB.getItemAt(cbxNXB.getSelectedIndex());
        for (NhaXuatBan nxb : listNXB) {
            if (nxb.getTenNXB().equals(selectedPublishingHouse)) {
                return nxb;
            }
        }
        return null;
    }

//    private Khu getSelectedArea() {
//        String selected = cbxKhu.getItemAt(cbxKhu.getSelectedIndex());
//        for (Khu khu : listKhu) {
//            if (khu.getTenKhu().equals(selected)) {
//                return khu;
//            }
//        }
//        return null;
//    }
//
//    private KeSach getSelectedBookshelf() {
//        String selectedBookshelf = cbxKeSach.getItemAt(cbxKeSach.getSelectedIndex());
//        for (KeSach keSach : listKeSach) {
//            if (keSach.getTenKe().equals(selectedBookshelf)) {
//                return keSach;
//            }
//        }
//        return null;
//    }
//    private NganKeSach getSelectedBookshelfCapartment() {
//        String selected = cbxNganKe.getItemAt(cbxNganKe.getSelectedIndex());
//        for (NganKeSach nganKeSach : listNganKeSach) {
//            if (nganKeSach.getTenNgan().equals(selected)) {
//                return nganKeSach;
//            }
//        }
//        return null;
//    }

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        // TODO add your handling code here:  
        boolean flag = true;

        if (cbxTacGia.getSelectedIndex() != 0) {
            cbxTacGia.setSelectedIndex(0);
            flag = false;
        }

        if (cbxTheLoai.getSelectedIndex() != 0) {
            cbxTheLoai.setSelectedIndex(0);
            flag = false;
        }

        if (cbxNXB.getSelectedIndex() != 0) {
            cbxNXB.setSelectedIndex(0);
            flag = false;
        }

//        if (cbxKhu.getSelectedIndex() != 0) {
//            cbxKhu.setSelectedIndex(0);
//            flag = false;
//        }
        txtSearch.setText("");
        if (flag == true) {
            listSach = Sach.getList();
            updateTable();
        }
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void cbxKeSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKeSachItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            keSach = getSelectedBookshelf();
//            if (keSach == null) {
//                cbxNganKe.removeAllItems();
//                cbxNganKe.addItem("Chọn ngăn kệ");
//                cbxNganKe.setSelectedIndex(0);
//                cbxNganKe.setEnabled(false);
//            } else {
//                cbxNganKe.setEnabled(true);
//                listNganKeSach = NganKeSach.getList(keSach.getMaKe());
//                cbxNganKe.removeAllItems();
//
//                cbxNganKe.addItem("Chọn ngăn kệ");
//                for (NganKeSach nganKeSach : listNganKeSach) {
//                    cbxNganKe.addItem(nganKeSach.getTenNgan());
//                }
//            }
//
//            listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
//            updateTable();
//        }
    }//GEN-LAST:event_cbxKeSachItemStateChanged

    private void cbxTheLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTheLoaiItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            TheLoaiSach theLoaiSach = getSelectedCategory();
            sach.setTheLoaiSach(theLoaiSach);
            listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
            updateTable();
        }
    }//GEN-LAST:event_cbxTheLoaiItemStateChanged

    private void cbxTacGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTacGiaItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            TacGia tacGia = getSelectedAuthor();
//            sach.setTacGia(tacGia);
//            listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
//            updateTable();
//        }
    }//GEN-LAST:event_cbxTacGiaItemStateChanged

    private void cbxNXBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNXBItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            NhaXuatBan nxb = getSelectedPublishingHouse();
            sach.setNhaXuatBan(nxb);
            listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
            updateTable();
        }
    }//GEN-LAST:event_cbxNXBItemStateChanged

    private void cbxNganKeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNganKeItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            nganKeSach = getSelectedBookshelfCapartment();
//            listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
//            updateTable();
//        }
    }//GEN-LAST:event_cbxNganKeItemStateChanged

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        String keyword = txtSearch.getText();
        if (keyword.isEmpty()) {
            listSach = Sach.getList();
        } else {
            listSach = Sach.search(keyword);
        }

        updateTable();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void cbxKhuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKhuItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            khu = getSelectedArea();
//            if (khu == null) {
//                cbxKeSach.removeAllItems();
//                cbxKeSach.addItem("Chọn kệ sách");
//                cbxKeSach.setSelectedIndex(0);
//                cbxKeSach.setEnabled(false);
//            } else {
//                cbxKeSach.setEnabled(true);
//                listKeSach = KeSach.getList(khu.getMaKhu());
//                cbxKeSach.removeAllItems();
//
//                cbxKeSach.addItem("Chọn kệ sách");
//                for (KeSach keSach : listKeSach) {
//                    cbxKeSach.addItem(keSach.getTenKe());
//                }
//            }
//
//            listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
//            updateTable();
//        }
    }//GEN-LAST:event_cbxKhuItemStateChanged

    private void tableMuonTruocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMuonTruocMouseClicked
        // TODO add your handling code here:
        int selectedRow = tableMuonTruoc.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        onRegisterTableClick(selectedRow);
    }//GEN-LAST:event_tableMuonTruocMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        if (table.getRowCount() == 0) {
            showMessage("Hiện không có sách nào cho phép mượn trước");
        } else {
            editState();
            txtSoLuong.setText("");
            button = "add";
        }

//        txtMaISBN.setText("");
//        txtTenSach.setText("");
//        txtMoTa.setText("");
//        txtGiaBia.setText("");
//        txtSoLuongCon.setText("");
//        txtSoTrang.setText("");
//        dpNamXB.setYear(2022);
//        cbxTacGia.setSelectedIndex(0);
//        cbxTheLoai.setSelectedIndex(0);
//        cbxKeSach.setSelectedIndex(0);
//        cbxNganKe.setSelectedIndex(0);
//        cbxNXB.setSelectedIndex(0);

    }//GEN-LAST:event_btnAddMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // TODO add your handling code here:
        editState();
        //cbxNganKe.setEnabled(true);
        button = "edit";
    }//GEN-LAST:event_btnEditMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Sách này?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            ChiTietMuonTruoc chiTietMuonTruoc = getSelectedChiTietMuonTruoc();
            listChiTietMuonTruoc.remove(chiTietMuonTruoc);
        }
        updateRegisterTable();
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnFinishMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinishMouseClicked
        // TODO add your handling code here:
        DocGia docGia = new DocGia();
        docGia.setMaDocGia(ThuVien.Account);
        phieuMuonTruoc.setDocgia(docGia);
        phieuMuonTruoc.setNgayMuonTruoc(new Date());
        phieuMuonTruoc.setTrangThai(1);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien("NV146");
        phieuMuonTruoc.setNhanVien(nhanVien);
        boolean i = PhieuMuonTruoc.insert(phieuMuonTruoc, listChiTietMuonTruoc);
        if (i == true) {
            showMessage("Đăng ký mượn trước thành công");
            listChiTietMuonTruoc.clear();
            updateRegisterTable();
        } else {
            showMessage("Đăng ký mượn trước thất bại");
        }

    }//GEN-LAST:event_btnFinishMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (txtSoLuong.getText().isEmpty()) {
            showMessage("Xin hãy nhập số lượng muốn đặt trước!");
        } else {
            if (button.equals("add")) {
                ChiTietMuonTruoc chiTietMuonTruoc = new ChiTietMuonTruoc();
                chiTietMuonTruoc.setSach(getSelectedBook());
                chiTietMuonTruoc.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                listChiTietMuonTruoc.add(chiTietMuonTruoc);
            } else if (button.equals("edit")) {
                ChiTietMuonTruoc chiTietMuonTruoc = getSelectedChiTietMuonTruoc();
                for (ChiTietMuonTruoc ctmt : listChiTietMuonTruoc) {
                    if (chiTietMuonTruoc.equals(ctmt)) {
                        ctmt.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                    }
                }
//                    boolean i = Sach.update(txtMaISBN.getText(), txtTenSach.getText(), getSelectedAuthor().getMaTacGia(), getSelectedPublishingHouse().getMaNXB(),
//                            d, Float.parseFloat(txtGiaBia.getText()), Integer.parseInt(txtSoTrang.getText()), txtMoTa.getText(),
//                            getSelectedCategory().getMaTheLoai(), Integer.parseInt(txtSoLuong.getText()), Integer.parseInt(txtSoLuongCon.getText()), getSelectedBookshelfCapartment().getMaNganKe()
//                    );
//                    if (i == true) {
//                        showMessage("Sửa thành công");
//                        normalState();
//                    } else {
//                        showMessage("Sửa thất bại");
//                    }
            }
            updateRegisterTable();
            normalState();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        normalState();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        if (!ThuVien.quyen.equals("Độc giả")) {
            showMessage("Xin hãy đăng nhập để thực hiện chức năng này");
            lock();
        }
    }//GEN-LAST:event_formComponentShown

    private void lock() {
        listSach.clear();
        txtSoLuong.setEditable(false);
        cbxTacGia.setEnabled(false);
        cbxTheLoai.setEnabled(false);
        cbxNXB.setEnabled(false);
        
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

        txtSearch.setEnabled(false);
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnFinish.setEnabled(false);
        btnRefresh.setEnabled(false);

        table.setEnabled(false);
        tableMuonTruoc.setEnabled(false);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkRegisterTable() {
        if (tableMuonTruoc.getRowCount() == 0) {
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            btnFinish.setEnabled(false);
        } else {
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
            btnFinish.setEnabled(true);
        }
    }

    private void normalState() {
        btnAdd.setEnabled(true);
        btnRefresh.setEnabled(true);
        txtSearch.setEnabled(true);
        checkRegisterTable();

        table.setEnabled(true);
        tableMuonTruoc.setEnabled(true);

//        txtMaISBN.setEditable(false);
//        txtTenSach.setEditable(false);
//        txtGiaBia.setEditable(false);
        txtSoLuong.setEditable(false);
//        txtMoTa.setEditable(false);
//        txtSoTrang.setEditable(false);
        //dpNamXB.setEnabled(false);
        cbxTacGia.setEnabled(true);
        cbxTheLoai.setEnabled(true);
        //cbxKeSach.setEnabled(true);
        cbxNXB.setEnabled(true);
        //cbxNganKe.setEnabled(true);

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);

//        listSach = Sach.getList();
//        updateTable();
//        onBookTableClick(0);
    }

    private void editState() {
//        txtMaISBN.setEditable(true);
//        txtTenSach.setEditable(true);
//        txtGiaBia.setEditable(true);
        txtSoLuong.setEditable(true);
//        txtMoTa.setEditable(true);
//        txtSoTrang.setEditable(true);
        //dpNamXB.setEnabled(true);

        cbxTacGia.setEnabled(false);
        cbxTheLoai.setEnabled(false);
        //cbxKeSach.setEnabled(false);
        cbxNXB.setEnabled(false);

        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);

        txtSearch.setEnabled(false);
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnFinish.setEnabled(false);
        btnRefresh.setEnabled(false);

        table.setEnabled(false);
        tableMuonTruoc.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnFinish;
    private javax.swing.JLabel btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JComboBox<String> cbxKeSach;
    private javax.swing.JComboBox<String> cbxKhu;
    private javax.swing.JComboBox<String> cbxNXB;
    private javax.swing.JComboBox<String> cbxNganKe;
    private javax.swing.JComboBox<String> cbxTacGia;
    private javax.swing.JComboBox<String> cbxTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable table;
    private javax.swing.JTable tableMuonTruoc;
    private javax.swing.JTextField txtMaISBN;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
