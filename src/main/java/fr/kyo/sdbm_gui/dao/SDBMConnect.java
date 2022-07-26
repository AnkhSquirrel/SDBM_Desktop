package fr.kyo.sdbm_gui.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SDBMConnect {
    // Declare the JDBC objects.
    private static Connection connexion;

    private SDBMConnect() {

    }

    public static Connection getInstance() {
        if (connexion == null) {
            try {
                String dbURL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=SDBM;encrypt=false";
                String user = "sa";
                String pass = "azerty@123456";
                connexion = DriverManager.getConnection(dbURL, user, pass);
            }

            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connexion;
    }
}
