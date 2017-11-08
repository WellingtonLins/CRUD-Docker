package br.com.wl.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {

                String url = "jdbc:postgresql://host-banco:5432/pos-cliente";
                String usuario = "postgres";
                String senha = "12345";

                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(url, usuario, senha);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
