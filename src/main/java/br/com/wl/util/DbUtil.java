package br.com.wl.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {

                String url = "jdbc:postgresql://localhost:5433/pos-cliente";
                String usuario = "postgres";
                String senha = "123";

                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(url, usuario, senha);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
