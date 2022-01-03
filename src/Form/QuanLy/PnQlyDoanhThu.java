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
    String mode = "";

    DefaultTableModel dtm;

    public PnQlyDoanhThu() throws SQLException {
        initComponents();
        jRadioButton_HomNay.setSelected(true);
        this.jDateChooser1.setDate(new Date());
        dtm = (DefaultTableModel) this.jTable.getModel();
        loadIncome(new Date());
    }

    public void loadIncome(Date date) throws SQLException {
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
        barChartData.setValue(new DatabaseAccess().layDoanhThuTheoNgay("muon", date), "Doanh Thu", "Độc giả mượn sách");
        barChartData.setValue(new DatabaseAccess().layDoanhThuTheoNgay("thanh-ly", date), "Doanh Thu", "Thanh lý sách");
        barChartData.setValue(new DatabaseAccess().layDoanhThuTheoNgay("thanh-toan", date), "Doanh Thu", "Độc giả thanh toán sách");

        JFreeChart barChart = ChartFactory.createBarChart("Tổng doanh thu trong ngày " + new SimpleDateFormat("dd/MM/yyyy").format(date), "", "", barChartData);

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
        List<DoanhThuTheoThoiGian> list = new ArrayList<>();
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
            list = new DatabaseAccess().layDoanhThuTrongThang();

            sdf = new SimpleDateFormat("MM/yyyy");

            title = "Doanh thu 6 tháng gần nhất";
//            range = "Tháng";
        }

        DefaultCategoryDataset barChatData = new DefaultCategoryDataset();
        Collections.reverse(list);
        String date = "";

        for (DoanhThuTheoThoiGian dttn : list) {

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
        jRadioButton_HomNay = new javax.swing.JRadioButton();
        jRadioButton_TuanNay = new javax.swing.JRadioButton();
        jRadioButton_6Thang = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton_Xem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        buttonGroup1.add(jRadioButton_HomNay);
        jRadioButton_HomNay.setSelected(true);
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jButton_Xem.setText("Xem");
        jButton_Xem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XemActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton_HomNay)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton_TuanNay)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton_6Thang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Xem, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton_Xem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_HomNay)
                        .addComponent(jRadioButton_TuanNay)
                        .addComponent(jRadioButton_6Thang)))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton_HomNayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_HomNayMouseClicked
        // TODO add your handling code here:
        if (!mode.equalsIgnoreCase("today")) {
            mode = "today";

            try {
                loadIncome(new Date());
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
            mode = "day";
            try {
                loadIncome(this.jDateChooser1.getDate());
                // refresh jFrame
                this.invalidate();
                this.validate();
                this.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(PnQlyDoanhThu.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton_XemActionPerformed
    public static void main(String[] args) throws SQLException {
        JFrame f = new JFrame();
        f.setSize(1200, 640);
        f.add(new PnQlyDoanhThu());
        f.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_Xem;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton_6Thang;
    private javax.swing.JRadioButton jRadioButton_HomNay;
    private javax.swing.JRadioButton jRadioButton_TuanNay;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
