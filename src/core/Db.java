package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private Connection connection = null;
    private static Db instance = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/patikahotel";
    private final String DB_USER = "postgres";
    private final String DB_PASS = "postgres";

    private Db() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static Connection getInstance()  {
        try {
            if (instance == null ||instance.getConnection().isClosed()) {
                instance = new Db();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }

}
