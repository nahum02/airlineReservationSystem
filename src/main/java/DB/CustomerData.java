package DB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerData extends FlightsData {


    public boolean pass(String user, String pass) throws SQLException, ClassNotFoundException {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String sql = "select * from customer where username = ? and password = ? ";

        try {
            myConn = DriverManager.getConnection
                        ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270", "nahum7332", "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, user);
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
                myConn.close();
            }
            return false;
        }

        public boolean checkUserName(String user, String securityAnswer)
                throws SQLException, ClassNotFoundException {

            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            String sql = "select * from customer where username = ? and securityAnswer = ? ";

            try {
                myConn = DriverManager.getConnection
                        ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
                myStmt = myConn.prepareStatement(sql);
                myStmt.setString(1, user);
                myStmt.setString(2, securityAnswer);
                myRs = myStmt.executeQuery();

                if (myRs.next()) {
                    return true;
                }
                else
                    return false;
            }

            catch(Exception ex) {
                ex.printStackTrace();
            }
            finally {
                myConn.close();
            }

            return false;
        }
    public String getPass(String user)
            throws SQLException, ClassNotFoundException {

        String pass = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String sql = "select * from customer where username = ? ";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, user);
            myRs = myStmt.executeQuery();

            while(myRs.next()) {

                pass = myRs.getString("password");
            }

        }

        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            myConn.close();
        }

        return pass;

    }

    public void register(String firstName, String lastName, String email,
                         String address, String username,
                         String password, String ssn,
                         String securityAnswer, String zipcode, String state, String birthday)
            throws SQLException, ClassNotFoundException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        String sql = "insert into customer (firstName, lastName, email, address, username, password, ssn, securityAnswer, zipcode, state, birthday) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);

            //myStmt.setInt( 1,custID(username));
            myStmt.setString(1, firstName);
            myStmt.setString(2, lastName);
            myStmt.setString(3, email);
            myStmt.setString(4, address);
            myStmt.setString(5, username);
            myStmt.setString(6, password);
            myStmt.setString(7, ssn);
            myStmt.setString(8, securityAnswer);
            myStmt.setString(9, zipcode);
            myStmt.setString(10, state);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(birthday, formatter);
            myStmt.setString(11, date.toString());



            myStmt.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            myConn.close();
        }

    }

    public boolean uniqueUser(String username) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String sql = "select * from customer where username = ?";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, username);

            myRs = myStmt.executeQuery();

            if (myRs.next()) {

                return false;
            }
            else {

                return true;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        finally {
            myConn.close();
        }
        return false;
    }

    public int custID(String user) throws Exception {

        int custID = 0;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String sql = "select * from customer where username = ?";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, user);
            myRs = myStmt.executeQuery();

            while(myRs.next()) {
                custID = myRs.getInt("customerID");
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            myConn.close();
        }
        return custID;
    }
}
