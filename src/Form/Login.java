/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;


import Code.ThuVien;
import Code.MaHoa;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author n18dc
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        txtTK.setText(ThuVien.Account);
        
    }
    public  void loadAccount(){
        boolean checkLogin=false;
        Connection connect=Code.KetNoi.layKetNoi();
        String tk=txtTK.getText();
        //String mk=new MaHoa(psMatkhau.getText()).maHoa();
        String mk=psMatkhau.getText();
        //=========================LOAD TÀI KHOẢN TỪ DATABASE============================
        try {
            //CallableStatement command = connect.prepareCall("{call SP_LOAD_LOGIN (?,?)}");
            PreparedStatement command=connect.prepareStatement("{call SP_LOAD_LOGIN (?,?)}");
             //cung cap gia tri cho bien
            command.setString(1, tk);
            command.setString(2, mk);
            ResultSet rs = command.executeQuery();
            
            // duyet ket qua
            while (rs.next()) {
                ThuVien.Account=tk;
                ThuVien.pass=mk;
                ThuVien.primaryKey=rs.getString(1);
                ThuVien.hoTen=rs.getString(2);
                ThuVien.quyen=rs.getString(3);
                checkLogin= true;
            }
            // dong ket noi
            rs.close();
            command.close();
            connect.close();
        } catch (Exception e) {
        }
        //====================KT NẾU LOAD ĐƯỢC TÀI KHOẢN THÌ CHUYỂN GIAO DIỆN=========================
        if(checkLogin){
                new GDChinh().setVisible(true);
                this.dispose();
  
        }else {
            lbErorrLogin.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLogin = new javax.swing.JPanel();
        lbErorrLogin = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        psMatkhau = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BackgroundLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM BÁN VÉ XE");
        setBackground(new java.awt.Color(4, 25, 45));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setLocation(new java.awt.Point(600, 200));

        pnLogin.setOpaque(false);
        pnLogin.setLayout(null);

        lbErorrLogin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbErorrLogin.setForeground(new java.awt.Color(255, 0, 0));
        lbErorrLogin.setText("Tài hoặc mật khẩu không hợp lệ !!Vui lòng nhập lại");
        lbErorrLogin.setVisible(false);
        pnLogin.add(lbErorrLogin);
        lbErorrLogin.setBounds(170, 400, 330, 33);

        btnDangNhap.setBackground(new java.awt.Color(51, 51, 255));
        btnDangNhap.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        btnDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDangNhapKeyPressed(evt);
            }
        });
        pnLogin.add(btnDangNhap);
        btnDangNhap.setBounds(160, 340, 350, 50);

        psMatkhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                psMatkhauKeyPressed(evt);
            }
        });
        pnLogin.add(psMatkhau);
        psMatkhau.setBounds(200, 290, 310, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 0));
        jLabel1.setText("Đăng Nhập Tài Khoản");
        pnLogin.add(jLabel1);
        jLabel1.setBounds(220, 20, 300, 32);
        pnLogin.add(txtTK);
        txtTK.setBounds(200, 230, 310, 42);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLabel2.setText("Đồng hành cùng tri thức Việt");
        pnLogin.add(jLabel2);
        jLabel2.setBounds(260, 180, 220, 16);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tài khoản:");
        pnLogin.add(jLabel3);
        jLabel3.setBounds(120, 240, 80, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Mật khẩu:");
        pnLogin.add(jLabel4);
        jLabel4.setBounds(120, 300, 70, 17);

        BackgroundLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background-frmlogin.jpg"))); // NOI18N
        pnLogin.add(BackgroundLogin);
        BackgroundLogin.setBounds(0, 0, 700, 470);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void psMatkhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psMatkhauKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loadAccount();
        }
    }//GEN-LAST:event_psMatkhauKeyPressed

    private void btnDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDangNhapKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loadAccount();
        }
    }//GEN-LAST:event_btnDangNhapKeyPressed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        loadAccount();

    }//GEN-LAST:event_btnDangNhapActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundLogin;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbErorrLogin;
    private javax.swing.JPanel pnLogin;
    private javax.swing.JPasswordField psMatkhau;
    private javax.swing.JTextField txtTK;
    // End of variables declaration//GEN-END:variables

}
