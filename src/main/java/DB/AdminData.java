package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminData {
    public boolean adminPass(String user, String pass) throws SQLException, ClassNotFoundException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ? ";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270", "nahum7332", "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1,user);
            myStmt.setString(2, pass);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (myRs != null) myRs.close();
            if (myStmt != null) myStmt.close();
            if (myStmt != null) myConn.close();
        }

        return false;
    }
    public void deleteFlight(String flightNum) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        String sql = "delete from flights where flightnum = ? ";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, flightNum);
            myStmt.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            myConn.close();
        }
    }

    public boolean checkCustomerBookings(String flightNum) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String sql = "select * from bookings where flightnum = ? ";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, flightNum);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            myConn.close();
        }

        return true;
    }

    public void deleteFlightBooks(String flightNum) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        String sql = "delete from bookings where flightNum = " + "'" + flightNum + "'";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            myConn.close();
        }
    }
    public void addAdminUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String checkSql = "SELECT * FROM admin WHERE username = ?";
        String insertSql = "INSERT INTO admin (username, password) VALUES (?, ?)";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270", "nahum7332", "Shigute1329!");


            myStmt = myConn.prepareStatement(checkSql);
            myStmt.setString(1, username);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                System.out.println("Username already exists: " + username);
                return;
            }


            myStmt = myConn.prepareStatement(insertSql);
            myStmt.setString(1, username);
            myStmt.setString(2, password);
            myStmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (myRs != null) myRs.close();
            if (myStmt != null) myStmt.close();
            if (myConn != null) myConn.close();
        }
    }

}

    

