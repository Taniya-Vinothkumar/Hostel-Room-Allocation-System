import java.sql.Connection;

import db.DBConnection;

public class ConnectionTest {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("JDBC connection test PASSED!");
        } else {
            System.out.println("JDBC connection test FAILED!");
        }
    }
}