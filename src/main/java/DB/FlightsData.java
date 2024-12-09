package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import bizlogic.Customer;


public class FlightsData extends AdminData {
    public static Connection getConnection() throws SQLException {

        Connection myConn = DriverManager.getConnection
                ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270", "nahum7332", "Shigute1329!");

        return myConn;
    }


    public void addFlight(String flightNums, String flightDate, String departTime,
                          String departLocation, String arrivalLocation, String airline, String seatPrice)
            throws SQLException, ClassNotFoundException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        String sql = "INSERT INTO flights (flightNum, date, departureTime, departureLocation, arrivalLocation, airline, seatPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(flightDate);
            String formattedDate = outputFormat.format(date);

            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270", "nahum7332", "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, flightNums);
            myStmt.setString(2, formattedDate);
            myStmt.setString(3, departTime);
            myStmt.setString(4, departLocation);
            myStmt.setString(5, arrivalLocation);
            myStmt.setString(6, airline);
            myStmt.setString(7, seatPrice);

            myStmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            myConn.close();
        }

    }
    public boolean unique(int customerID, String flightNum) throws SQLException, ClassNotFoundException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String sql = "select * from bookings where customerID = ? and flightNum = ? ";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, customerID);
            myStmt.setString(2, flightNum);
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

    public void book(int customerID, String flightNum) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        String sql = "insert into bookings values (?, ?, ?)";


        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, 0);
            myStmt.setInt(2, customerID);
            myStmt.setString(3, flightNum);
            myStmt.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            myConn.close();
        }
    }
    public boolean flightFull(String flightNum) throws SQLException {

        int count = 1;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        String sql = "select * from bookings where flightNum = ? ";

        try {
            myConn = DriverManager.getConnection
                    ("jdbc:mysql://cis3270.mysql.database.azure.com:3306/cis3270" , "nahum7332" , "Shigute1329!");
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, flightNum);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                count++;
            }

            if (count == 6) {
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
        return true;
    }

    public boolean flightTimeConflict(String date, String time) {

        Customer customer = new Customer();
        int id = customer.getCustomerID();

        PreparedStatement myStmt = null;
        ResultSet rs = null;
        String sql = "SELECT flights.date, flights.departureTime FROM flights " +
                "INNER JOIN bookings ON flights.flightNum = bookings.flightNum and customerID = "
                + "'" + id + "'" + "and flights.date = " + "'" + date + "'" +
                "and flights.departureTime =" + "'" + time + "'";

        try {
            Connection con = FlightsData.getConnection();
            myStmt = con.prepareStatement(sql);
            rs = myStmt.executeQuery();

            while(rs.next()) {
                return true;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void deleteFlight(String flightNum) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        String sql = "delete from flights where flightNum = ? ";

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

    public void deleteBook(int customerID, String flightNum) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        String sql = "delete from bookings where customerID = " + "'" + customerID + "'"
                + "and flightNum = " + "'" + flightNum + "'";

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
}
