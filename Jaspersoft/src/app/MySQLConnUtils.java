package app;

import java.sql.*;

public class MySQLConnUtils {

    private String IP;
    private String DB;
    private String UserName;
    private String Pass;

    public MySQLConnUtils(String IP, String DB, String UserName, String Pass){
        this.IP = IP;
        this.DB = DB;
        this.UserName = UserName;
        this.Pass = Pass;
    }
    
    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        String hostName = "172.16.26.200";
        String dbName = "employees";
        String userName = "alumne";
        String password = "tofol";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {
        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}