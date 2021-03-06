/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.DocGia;

import Entities.KeSach;
import Entities.Khu;
import Entities.NganKeSach;
import Entities.NhaXuatBan;
import Entities.Sach;
import Entities.TacGia;
import Entities.TheLoaiSach;
import Entities.ViTri;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Phat
 */
public class DocGiaTraCuu extends javax.swing.JPanel {

    private List<Sach> listSach = null;
    //private List<ViTri> listVitri = null;
    private List<TacGia> listTacGia = null;
    private List<TheLoaiSach> listTheLoaiSach = null;
    private List<Khu> listKhu = null;
    private List<KeSach> listKeSach = null;
    private List<NganKeSach> listNganKeSach = null;
    private List<NhaXuatBan> listNXB = null;
    private Sach sach = new Sach();

    private Khu khu = null;
    private KeSach keSach = null;
    private NganKeSach nganKeSach = null;

    String button = "";
    boolean f = true;

    /**
     * Creates new form PnQlyMuonSach
     */
    public DocGiaTraCuu() {
        initComponents();
        System.out.println("hello");

        listSach = Sach.getList();
        //listVitri = ViTri.getList();
        updateTable();
        updateCombobox();

    }

    private void updateTable() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);

//        for (Sach sach : listSach) {
//            List<ViTri> cacViTri = ViTri.getList(sach);
//            for (ViTri vt : cacViTri) {
//                //String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), dateFormat.format(sach.getNamXB()), Float.toString(sach.getGiaBia()), Integer.toString(sach.getSoTrang()), sach.getMoTa(), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuong()), Integer.toString(sach.getSoLuongCon()), sach.getNganKeSach().getKeSach().getTenKe(), sach.getNganKeSach().getTenNgan(), sach.getNganKeSach().getKeSach().getKhu().getTenKhu()};
//                NganKeSach nks = vt.getNganKeSach();
//                String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), dateFormat.format(sach.getNamXB()), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuongCon()), nks.getKeSach().getTenKe(), nks.getTenNgan(), nks.getKeSach().getKhu().getTenKhu(), nks.getKeSach().getKhu().getTang()};
//                dtm.addRow(data);
//            }
//
//        }
        if (f == false) {
            for (Sach sach : listSach) {
                if (sach.getCacViTri() != null) {
                    f = true;
                    break;
                }
//            List<ViTri> cacViTri = ViTri.getList(sach);
                //for (ViTri vt : sach.getCacViTri()) {
                //String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), dateFormat.format(sach.getNamXB()), Float.toString(sach.getGiaBia()), Integer.toString(sach.getSoTrang()), sach.getMoTa(), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuong()), Integer.toString(sach.getSoLuongCon()), sach.getNganKeSach().getKeSach().getTenKe(), sach.getNganKeSach().getTenNgan(), sach.getNganKeSach().getKeSach().getKhu().getTenKhu()};
                NganKeSach nks = sach.getViTri().getNganKeSach();
                String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), dateFormat.format(sach.getNamXB()), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuongCon()), nks.getKeSach().getTenKe(), nks.getTenNgan(), nks.getKeSach().getKhu().getTenKhu(), nks.getKeSach().getKhu().getTang()};
                dtm.addRow(data);
                //}
            }
        }

        if (f == true) {
            for (Sach sach : listSach) {
//            List<ViTri> cacViTri = ViTri.getList(sach);
                for (ViTri vt : sach.getCacViTri()) {
                    //String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), dateFormat.format(sach.getNamXB()), Float.toString(sach.getGiaBia()), Integer.toString(sach.getSoTrang()), sach.getMoTa(), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuong()), Integer.toString(sach.getSoLuongCon()), sach.getNganKeSach().getKeSach().getTenKe(), sach.getNganKeSach().getTenNgan(), sach.getNganKeSach().getKeSach().getKhu().getTenKhu()};
                    NganKeSach nks = vt.getNganKeSach();
                    String data[] = {sach.getMaISBN(), sach.getTenSach(), sach.getTacGia().getTenTacGia(), sach.getNhaXuatBan().getTenNXB(), dateFormat.format(sach.getNamXB()), sach.getTheLoaiSach().getTenTheLoai(), Integer.toString(sach.getSoLuongCon()), nks.getKeSach().getTenKe(), nks.getTenNgan(), nks.getKeSach().getKhu().getTenKhu(), nks.getKeSach().getKhu().getTang()};
                    dtm.addRow(data);
                }

            }
        }

    }

    private void updateCombobox() {
        listTacGia = TacGia.getList();
        listNXB = NhaXuatBan.getList();
        listTheLoaiSach = TheLoaiSach.getList();
        listKeSach = KeSach.getList();
        listNganKeSach = NganKeSach.getList();
        listKhu = Khu.getList();

        for (TacGia tacGia : listTacGia) {
            cbxTacGia.addItem(tacGia.getTenTacGia());
        }

        for (NhaXuatBan nxb : listNXB) {
            cbxNXB.addItem(nxb.getTenNXB());
        }

        for (TheLoaiSach theLoaiSach : listTheLoaiSach) {
            cbxTheLoai.addItem(theLoaiSach.getTenTheLoai());
        }

        for (Khu khu : listKhu) {
            cbxKhu.addItem(khu.getTenKhu());
        }

        for (KeSach keSach : listKeSach) {
            cbxKeSach.addItem(keSach.getTenKe());
        }

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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnRefresh = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cbxTacGia = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cbxNXB = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cbxTheLoai = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbxKeSach = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cbxKhu = new javax.swing.JComboBox<>();

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Ng??n k???:");

        cbxNganKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???n ng??n k???" }));
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

        setPreferredSize(new java.awt.Dimension(1155, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Tra c???u s??ch");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? ISBN", "T??n s??ch", "T??c gi???", "NXB", "n??m XB", "Th??? lo???i", "SL c??n", "K??? S??ch", "Ng??n k???", "Khu", "T???ng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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
            table.getColumnModel().getColumn(4).setPreferredWidth(20);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(20);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(9).setResizable(false);
            table.getColumnModel().getColumn(9).setPreferredWidth(120);
            table.getColumnModel().getColumn(10).setResizable(false);
            table.getColumnModel().getColumn(10).setPreferredWidth(20);
        }

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reload.png"))); // NOI18N
        btnRefresh.setText("T???i l???i");
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search-icon.png"))); // NOI18N
        btnSearch.setText("T??m Ki???m");
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
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSearch)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("T??c gi???:");

        cbxTacGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???n t??c gi???" }));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbxTacGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("NXB:");

        cbxNXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???n NXB" }));
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

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Th??? lo???i:");

        cbxTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???n th??? lo???i" }));
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

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("K??? s??ch:");

        cbxKeSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???n k??? s??ch" }));
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

        cbxKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch???n khu" }));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 362, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(514, 514, 514))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
//        int selectedRow = table.getSelectedRow();
//        if (selectedRow < 0) {
//            return;
//        }
//        onTableClick(selectedRow);
    }//GEN-LAST:event_tableMouseClicked

    private void onTableClick(int selectedRow) {
//        Sach sach = listSach.get(selectedRow);
//        txtMaISBN.setText(sach.getMaISBN());
//        txtTenSach.setText(sach.getTenSach());
//        txtGiaBia.setText(Float.toString(sach.getGiaBia()));
//        txtMoTa.setText(sach.getMoTa());
//        txtSoLuong.setText(Integer.toString(sach.getSoLuong()));
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

    private void editState() {
//        txtMaISBN.setEditable(true);
//        txtTenSach.setEditable(true);
//        txtGiaBia.setEditable(true);
//        txtSoLuong.setEditable(true);
//        txtMoTa.setEditable(true);
//        txtSoTrang.setEditable(true);
        //dpNamXB.setEnabled(true);

        cbxTacGia.setEnabled(true);
        cbxTheLoai.setEnabled(true);
        cbxKeSach.setEnabled(true);
        cbxNXB.setEnabled(true);

//        btnSave.setEnabled(true);
//        btnCancel.setEnabled(true);
//        btnAdd.setEnabled(false);
//        btnEdit.setEnabled(false);
//        btnDelete.setEnabled(false);
        btnRefresh.setEnabled(false);
        table.setEnabled(false);
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
        f = true;
        boolean flag = true;
        button = "refresh";

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

        if (cbxKhu.getSelectedIndex() != 0) {
            cbxKhu.setSelectedIndex(0);
            flag = false;
        }
        txtSearch.setText("");
        //Thread t = new Thread(() -> {
        listSach = Sach.getList();
        updateTable();
        //});
        //t.start();

    }//GEN-LAST:event_btnRefreshMouseClicked

    private void cbxKeSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKeSachItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            keSach = getSelectedBookshelf();
//            if (keSach == null) {
//                cbxNganKe.removeAllItems();
//                cbxNganKe.addItem("Ch???n ng??n k???");
//                cbxNganKe.setSelectedIndex(0);
//                cbxNganKe.setEnabled(false);
//            } else {
//                cbxNganKe.setEnabled(true);
//                listNganKeSach = NganKeSach.getList(keSach.getMaKe());
//                cbxNganKe.removeAllItems();
//
//                cbxNganKe.addItem("Ch???n ng??n k???");
//                for (NganKeSach nganKeSach : listNganKeSach) {
//                    cbxNganKe.addItem(nganKeSach.getTenNgan());
//                }
//            } 
            //Thread t = new Thread(() -> {
                f = false;
                listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
                updateTable();
            //});
            //t.start();
        }
    }//GEN-LAST:event_cbxKeSachItemStateChanged

    private void cbxKhuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKhuItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            khu = getSelectedArea();
            if (khu == null) {
                cbxKeSach.removeAllItems();
                cbxKeSach.addItem("Ch???n k??? s??ch");
                cbxKeSach.setSelectedIndex(0);
                cbxKeSach.setEnabled(false);
            } else {
                cbxKeSach.setEnabled(true);
                listKeSach = KeSach.getList(khu.getMaKhu());
                cbxKeSach.removeAllItems();

                cbxKeSach.addItem("Ch???n k??? s??ch");
                for (KeSach keSach : listKeSach) {
                    cbxKeSach.addItem(keSach.getTenKe());
                }
            }

            //Thread t = new Thread(() -> {
                f = false;
                listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
                updateTable();
            //});
            //t.start();
        }
    }//GEN-LAST:event_cbxKhuItemStateChanged

    private void cbxTheLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTheLoaiItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            TheLoaiSach theLoaiSach = getSelectedCategory();
            sach.setTheLoaiSach(theLoaiSach);
            Thread t = new Thread(() -> {
                f = false;
                listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
                updateTable();
            });
            t.start();
        }
    }//GEN-LAST:event_cbxTheLoaiItemStateChanged

    private void cbxTacGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTacGiaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            TacGia tacGia = getSelectedAuthor();
            sach.setTacGia(tacGia);
            Thread t = new Thread(() -> {
                f = false;
                listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
                updateTable();
            });
            t.start();
        }
    }//GEN-LAST:event_cbxTacGiaItemStateChanged

    private void cbxNXBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNXBItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            NhaXuatBan nxb = getSelectedPublishingHouse();
            sach.setNhaXuatBan(nxb);
            Thread t = new Thread(() -> {
                f = false;
                listSach = Sach.search(sach, khu, keSach, nganKeSach, txtSearch.getText());
                updateTable();
            });
            t.start();
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
        f = true;
        if (keyword.isEmpty()) {
            listSach = Sach.getList();
        } else {
            listSach = Sach.search2(keyword);
        }

        updateTable();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
    }

    private void normalState() {
//        btnAdd.setEnabled(true);
//        btnEdit.setEnabled(true);
//        btnDelete.setEnabled(true);
        btnRefresh.setEnabled(true);
        table.setEnabled(true);

//        txtMaISBN.setEditable(false);
//        txtTenSach.setEditable(false);
//        txtGiaBia.setEditable(false);
//        txtSoLuong.setEditable(false);
//        txtMoTa.setEditable(false);
//        txtSoTrang.setEditable(false);
        //dpNamXB.setEnabled(false);
        cbxTacGia.setEnabled(false);
        cbxTheLoai.setEnabled(false);
        cbxKeSach.setEnabled(false);
        cbxNXB.setEnabled(false);
        cbxNganKe.setEnabled(false);

//        btnSave.setEnabled(false);
//        btnCancel.setEnabled(false);
//        listVitri = ViTri.getList();
        listSach = Sach.getList();
        updateTable();
        onTableClick(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnRefresh;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
