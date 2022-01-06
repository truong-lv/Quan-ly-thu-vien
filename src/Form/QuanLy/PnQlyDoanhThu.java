/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author t0168
 */
public final class PnQlyDoanhThu extends javax.swing.JPanel {

    /**
     * Creates new form PnQlyDoanhThu
     */
    String mode = "today";
    String searchMode = "theo-ngay";
    DefaultTableModel dtm;

    public PnQlyDoanhThu() throws SQLException {
        initComponents();
        jRadioButton_HomNay.setSelected(true);
        jRadioButton_TheoNgay.setSelected(true);
        this.jDateChooser1.setDate(new Date());
        dtm = (DefaultTableModel) this.jTable.getModel();
        loadIncome(new Date(), searchMode);
    }

    public void loadCompareIncomeAndOutcome(String mode) throws SQLException {
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
        double tongMuon = -1;
        double tongThanhLy = -1;
        double tongThanhToan = -1;
        List<TongChiPhiTheoThoiGian> list = new ArrayList<>();
        List<TongDoanhThuTheoThoiGian> listDoanhThu = new ArrayList<>();
        if (mode.equalsIgnoreCase("today")) {
            tongMuon = new DatabaseAccess().layDoanhThu("muon", new Date(), "theo-ngay");
            tongThanhLy = new DatabaseAccess().layDoanhThu("thanh-ly", new Date(), "theo-ngay");
            tongThanhToan = new DatabaseAccess().layDoanhThu("thanh-toan", new Date(), "theo-ngay");

            barChartData.setValue(tongMuon + tongThanhLy + tongThanhToan, "Doanh Thu", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            
            list = new DatabaseAccess().layChiPhiTheoNgay(new Date());
            Collections.reverse(list);
            
            for (TongChiPhiTheoThoiGian tcp : list) {
                barChartData.setValue(tcp.getNhapHang(), "Chi Phí", new SimpleDateFormat("dd/MM/yyyy").format(tcp.getDate()));
            }
            
        } else if (mode.equalsIgnoreCase("lastweek")) {
            
            listDoanhThu = new DatabaseAccess().layDoanhThuTrongTuan();
            Collections.reverse(listDoanhThu);
            for (TongDoanhThuTheoThoiGian tdt : listDoanhThu) {
                barChartData.setValue(tdt.getMuon() + tdt.getThanhLy() + tdt.getThanhToan(), "Doanh Thu", new SimpleDateFormat("dd/MM/yyyy").format(tdt.getDate()));
            }
            
            list = new DatabaseAccess().layChiPhiTheoTuanGanNhat();
            Collections.reverse(list);
            
            for (TongChiPhiTheoThoiGian tcp : list) {
                barChartData.setValue(tcp.getNhapHang(), "Chi Phí", new SimpleDateFormat("dd/MM/yyyy").format(tcp.getDate()));
            }
        } else if (mode.equalsIgnoreCase("last6month")) {
            
            listDoanhThu = new DatabaseAccess().layDoanhThuTrong6Thang();
            Collections.reverse(listDoanhThu);
            
            for (TongDoanhThuTheoThoiGian tdt : listDoanhThu) {
                barChartData.setValue(tdt.getMuon() + tdt.getThanhLy() + tdt.getThanhToan(), "Doanh Thu", new SimpleDateFormat("MM/yyyy").format(tdt.getDate()));
            }
            
            list = new DatabaseAccess().layChiPhiTheo6ThangGanNhat();
            Collections.reverse(list);
            
            for (TongChiPhiTheoThoiGian tcp : list) {
                barChartData.setValue(tcp.getNhapHang(), "Chi Phí", new SimpleDateFormat("MM/yyyy").format(tcp.getDate()));
            }
        }

        String chartTitle = "";
        if (mode.equalsIgnoreCase("today")) {
            chartTitle = "Doanh thu và chi phí trong ngày " + new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        } else if (mode.equalsIgnoreCase("lastweek")) {
            chartTitle = "Doanh thu và chi phí trong tuần ";
        } else if (mode.equalsIgnoreCase("last6month")) {
            chartTitle = "Doanh thu và chi phí  trong 6 tháng gần nhất";
        }

        JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "", "", barChartData, PlotOrientation.VERTICAL, true, true, false);

        //nếu k có doanh thu thì cột y từ 0-500000, nếu có thì từ 0 đến auto
        if (tongMuon == 0 && tongThanhLy == 0 && tongThanhToan == 0) {
            barChart.getCategoryPlot().getRangeAxis().setRange(0, 700000);
        }
        barChart.setPadding(new RectangleInsets(8, 8, 8, 8));

        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        ((BarRenderer) categoryPlot.getRenderer()).setBarPainter(new StandardBarPainter());
        categoryPlot.setRangeGridlinePaint(Color.red);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setItemMargin(0);
        ChartPanel barPanel = new ChartPanel(barChart);

        jPanel1.removeAll();

        jPanel1.add(barPanel, BorderLayout.CENTER);

        dtm.setRowCount(0);
        if (mode.equalsIgnoreCase("today")) {

            for (ChiTietDoanhThuTheoThoiGian ct_dtttg : new DatabaseAccess().layCTDoanhThuTheoNgay(new Date())) {
                dtm.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(ct_dtttg.getDate()),
                    ct_dtttg.getNguon(), ct_dtttg.getLoai(), ct_dtttg.getTongThu()});
            }
        } else if (mode.equalsIgnoreCase("lastweek")) {
            for (ChiTietDoanhThuTheoThoiGian ct_dtttg : new DatabaseAccess().layCTDoanhThuTheoTuanGanNhat()) {
                dtm.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(ct_dtttg.getDate()),
                    ct_dtttg.getNguon(), ct_dtttg.getLoai(), ct_dtttg.getTongThu()});
            }
        } else if (mode.equalsIgnoreCase("last6month")) {
            for (ChiTietDoanhThuTheoThoiGian ct_dtttg : new DatabaseAccess().layCTDoanhThuTheo6ThangGanNhat()) {
                dtm.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(ct_dtttg.getDate()),
                    ct_dtttg.getNguon(), ct_dtttg.getLoai(), ct_dtttg.getTongThu()});
            }
        }

    }

    public void loadIncome(Date date, String searchMode) throws SQLException {
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
        double tongMuon = new DatabaseAccess().layDoanhThu("muon", date, searchMode);
        double tongThanhLy = new DatabaseAccess().layDoanhThu("thanh-ly", date, searchMode);
        double tongThanhToan = new DatabaseAccess().layDoanhThu("thanh-toan", date, searchMode);
        barChartData.setValue(tongMuon, "Doanh Thu", "Độc giả mượn sách");
        barChartData.setValue(tongThanhLy, "Doanh Thu", "Thanh lý sách");
        barChartData.setValue(tongThanhToan, "Doanh Thu", "Độc giả thanh toán sách");
        String chartTitle = "";
        if (searchMode.equalsIgnoreCase("theo-ngay")) {
            chartTitle = "Tổng doanh thu trong ngày " + new SimpleDateFormat("dd/MM/yyyy").format(date);
        } else if (searchMode.equalsIgnoreCase("theo-thang")) {
            chartTitle = "Tổng doanh thu trong tháng " + new SimpleDateFormat("MM/yyyy").format(date);
        } else if (searchMode.equalsIgnoreCase("theo-nam")) {
            chartTitle = "Tổng doanh thu trong năm " + new SimpleDateFormat("yyyy").format(date);
        }

        JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "", "", barChartData);

        //nếu k có doanh thu thì cột y từ 0-500000, nếu có thì từ 0 đến auto
        if (tongMuon == 0 && tongThanhLy == 0 && tongThanhToan == 0) {
            barChart.getCategoryPlot().getRangeAxis().setRange(0, 500000);
        }
        barChart.setPadding(new RectangleInsets(8, 8, 8, 8));

        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        ((BarRenderer) categoryPlot.getRenderer()).setBarPainter(new StandardBarPainter());
        categoryPlot.setRangeGridlinePaint(Color.red);

        ChartPanel barPanel = new ChartPanel(barChart);

        jPanel1.removeAll();

        jPanel1.add(barPanel, BorderLayout.CENTER);

        dtm.setRowCount(0);

        for (ChiTietDoanhThuTheoThoiGian ct_dtttg : new DatabaseAccess().layCTDoanhThuTheoNgay(date)) {
            dtm.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(ct_dtttg.getDate()),
                ct_dtttg.getNguon(), ct_dtttg.getLoai(), ct_dtttg.getTongThu()});
        }
    }

    public void loadIncome(String mode) throws SQLException {
        List<TongDoanhThuTheoThoiGian> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String title = "";
        String range = "";
        if (mode.equalsIgnoreCase("lastweek")) {
            list = new DatabaseAccess().layDoanhThuTrongTuan();

            sdf = new SimpleDateFormat("dd/MM/yyyy");

            title = "Doanh thu 7 ngày gần nhất";
//            range = "Ngày";

        }
        if (mode.equalsIgnoreCase("last6month")) {
            list = new DatabaseAccess().layDoanhThuTrong6Thang();

            sdf = new SimpleDateFormat("MM/yyyy");

            title = "Doanh thu 6 tháng gần nhất";
//            range = "Tháng";
        }

        DefaultCategoryDataset barChatData = new DefaultCategoryDataset();
        Collections.reverse(list);
        String date = "";

        for (TongDoanhThuTheoThoiGian dttn : list) {

            date = sdf.format(dttn.getDate());
            barChatData.addValue(dttn.getMuon(), "Mượn", date);
            barChatData.addValue(dttn.getThanhToan(), "Thanh Toán", date);
            barChatData.addValue(dttn.getThanhLy(), "Thanh Lý", date);
        }

        JFreeChart barChart = ChartFactory.createStackedBarChart(title, range, "", barChatData, PlotOrientation.VERTICAL, true, true, true);

        barChart.setPadding(new RectangleInsets(8, 8, 8, 8));

        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.lightGray);
        categoryPlot.setRangeGridlinePaint(Color.red);
        ((BarRenderer) categoryPlot.getRenderer()).setBarPainter(new StandardBarPainter());
        StackedBarRenderer stackedbarrenderer = (StackedBarRenderer) categoryPlot.getRenderer();
        stackedbarrenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        stackedbarrenderer.setItemLabelsVisible(true);

        ChartPanel barPanel = new ChartPanel(barChart);
        jPanel1.removeAll();
        jPanel1.add(barPanel, BorderLayout.CENTER);

        if (mode.equalsIgnoreCase("lastweek")) {
            dtm.setRowCount(0);

            for (ChiTietDoanhThuTheoThoiGian ct_dtttg : new DatabaseAccess().layCTDoanhThuTheoTuanGanNhat()) {
                dtm.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(ct_dtttg.getDate()),
                    ct_dtttg.getNguon(), ct_dtttg.getLoai(), ct_dtttg.getTongThu()});
            }

        } else if (mode.equalsIgnoreCase("last6month")) {
            dtm.setRowCount(0);

            for (ChiTietDoanhThuTheoThoiGian ct_dtttg : new DatabaseAccess().layCTDoanhThuTheo6ThangGanNhat()) {
                dtm.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(ct_dtttg.getDate()),
                    ct_dtttg.getNguon(), ct_dtttg.getLoai(), ct_dtttg.getTongThu()});
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton_HomNay = new javax.swing.JRadioButton();
        jRadioButton_TuanNay = new javax.swing.JRadioButton();
        jRadioButton_6Thang = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jButton_SoSanhNhanh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton_Xem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton_TheoNam = new javax.swing.JRadioButton();
        jRadioButton_TheoNgay = new javax.swing.JRadioButton();
        jRadioButton_TheoThang = new javax.swing.JRadioButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ngày", "Nguồn", "Loại", "Tổng Thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
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
        jScrollPane1.setViewportView(jTable);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(jRadioButton_HomNay);
        jRadioButton_HomNay.setText("Hôm nay");
        jRadioButton_HomNay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_HomNayMouseClicked(evt);
            }
        });

        buttonGroup1.add(jRadioButton_TuanNay);
        jRadioButton_TuanNay.setText("Tuần này");
        jRadioButton_TuanNay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_TuanNayMouseClicked(evt);
            }
        });
        jRadioButton_TuanNay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_TuanNayActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_6Thang);
        jRadioButton_6Thang.setText("6 tháng gần nhất");
        jRadioButton_6Thang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_6ThangMouseClicked(evt);
            }
        });
        jRadioButton_6Thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_6ThangActionPerformed(evt);
            }
        });

        jLabel1.setText("Thống kê nhanh");

        jButton_SoSanhNhanh.setText("So sánh với chi phí");
        jButton_SoSanhNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SoSanhNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_HomNay)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_TuanNay)
                            .addComponent(jRadioButton_6Thang))
                        .addGap(92, 92, 92)
                        .addComponent(jButton_SoSanhNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jRadioButton_HomNay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButton_TuanNay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_6Thang)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_SoSanhNhanh)
                        .addGap(21, 21, 21))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jButton_Xem.setText("Xem");
        jButton_Xem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XemActionPerformed(evt);
            }
        });

        jLabel2.setText("Tìm kiếm ");

        buttonGroup2.add(jRadioButton_TheoNam);
        jRadioButton_TheoNam.setText("Theo năm");
        jRadioButton_TheoNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_TheoNamMouseClicked(evt);
            }
        });
        jRadioButton_TheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_TheoNamActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton_TheoNgay);
        jRadioButton_TheoNgay.setSelected(true);
        jRadioButton_TheoNgay.setText("Theo ngày");
        jRadioButton_TheoNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_TheoNgayMouseClicked(evt);
            }
        });

        buttonGroup2.add(jRadioButton_TheoThang);
        jRadioButton_TheoThang.setText("Theo tháng");
        jRadioButton_TheoThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_TheoThangMouseClicked(evt);
            }
        });
        jRadioButton_TheoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_TheoThangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_TheoThang)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButton_TheoNgay)
                    .addComponent(jRadioButton_TheoNam))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jButton_Xem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton_TheoNgay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_TheoThang))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Xem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton_TheoNam)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton_HomNayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_HomNayMouseClicked
        // TODO add your handling code here:
        if (!mode.equalsIgnoreCase("today")) {
            mode = "today";
            searchMode = "theo-ngay";
            try {
                loadIncome(new Date(), searchMode);
                this.invalidate();
                this.validate();
                this.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(PnQlyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButton_HomNayMouseClicked

    private void jRadioButton_TuanNayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_TuanNayActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jRadioButton_TuanNayActionPerformed

    private void jRadioButton_6ThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_6ThangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jRadioButton_6ThangActionPerformed

    private void jRadioButton_TuanNayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_TuanNayMouseClicked
        // TODO add your handling code here:
        if (!mode.equalsIgnoreCase("lastweek")) {
            mode = "lastweek";
            try {
                loadIncome(mode);
                this.invalidate();
                this.validate();
                this.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(PnQlyDoanhThu.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButton_TuanNayMouseClicked

    private void jRadioButton_6ThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_6ThangMouseClicked
        // TODO add your handling code here:
        if (!mode.equalsIgnoreCase("last6month")) {
            mode = "last6month";
            try {

                loadIncome(mode);
                // refresh jFrame
                this.invalidate();
                this.validate();
                this.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(PnQlyDoanhThu.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButton_6ThangMouseClicked

    private void jButton_XemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XemActionPerformed
        // TODO add your handling code here:
//        mode = "day";
        try {
            loadIncome(this.jDateChooser1.getDate(), searchMode);
            // refresh jFrame
            this.invalidate();
            this.validate();
            this.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(PnQlyDoanhThu.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_XemActionPerformed

    private void jRadioButton_TheoThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_TheoThangMouseClicked
        // TODO add your handling code here:
        searchMode = "theo-thang";
    }//GEN-LAST:event_jRadioButton_TheoThangMouseClicked

    private void jRadioButton_TheoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_TheoThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_TheoThangActionPerformed

    private void jRadioButton_TheoNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_TheoNamMouseClicked
        // TODO add your handling code here:
        searchMode = "theo-nam";
    }//GEN-LAST:event_jRadioButton_TheoNamMouseClicked

    private void jRadioButton_TheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_TheoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_TheoNamActionPerformed

    private void jRadioButton_TheoNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_TheoNgayMouseClicked
        // TODO add your handling code here:
        searchMode = "theo-ngay";
    }//GEN-LAST:event_jRadioButton_TheoNgayMouseClicked

    private void jButton_SoSanhNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SoSanhNhanhActionPerformed
        // TODO add your handling code here:
        try {
            loadCompareIncomeAndOutcome(mode);
            this.invalidate();
            this.validate();
            this.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(PnQlyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_SoSanhNhanhActionPerformed
    public static void main(String[] args) throws SQLException {
        JFrame f = new JFrame();
        f.setSize(1200, 600);
        f.add(new PnQlyDoanhThu());
        f.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton_SoSanhNhanh;
    private javax.swing.JButton jButton_Xem;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton_6Thang;
    private javax.swing.JRadioButton jRadioButton_HomNay;
    private javax.swing.JRadioButton jRadioButton_TheoNam;
    private javax.swing.JRadioButton jRadioButton_TheoNgay;
    private javax.swing.JRadioButton jRadioButton_TheoThang;
    private javax.swing.JRadioButton jRadioButton_TuanNay;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
