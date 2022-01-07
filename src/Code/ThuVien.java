
package Code;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;


public class ThuVien {
    public static String Account="";
    public static String pass="";
    public static String primaryKey="";
    public static String hoTen="";
    public static String quyen="";
    public static String maNV;
    
    public ThuVien() {
    }

    public static String getAccount() {
        return Account;
    }

    public static String getQuyen() {
        return quyen;
    }

    public static void setAccount(String Account) {
        ThuVien.Account = Account;
    }

    public static void setQuyen(String quyen) {
        ThuVien.quyen = quyen;
    }
    
   public static void setBanVeXe(String Account, String pass, String hoTen, String quyen) {
       ThuVien.Account = Account; 
       ThuVien.pass = pass;
       ThuVien.hoTen = hoTen;
       ThuVien.quyen = quyen;
    }
}
