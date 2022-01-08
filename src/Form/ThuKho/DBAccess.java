/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.ThuKho;

import Code.KetNoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phat
 */
public class DBAccess {

    private Connection conn;
    private Statement stmt;

    public DBAccess() {
        try {
            conn = KetNoi.layKetNoi();
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Update(String str) {
        int i = 0;
        try {
            i = stmt.executeUpdate(str);
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return i != 0;
    }

    public ResultSet Query(String str) {
        try {
            ResultSet rs = stmt.executeQuery(str);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String generateId(String type) {
        try {
            //CallableStatement command = connect.prepareCall("{call SP_LOAD_LOGIN (?,?)}");
            PreparedStatement command = conn.prepareStatement("{call SP_LOAD_MA (?)}");
            //cung cap gia tri cho bien
            command.setString(1, type);
            ResultSet rs = command.executeQuery();
            // duyet ket qua
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
