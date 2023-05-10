package edu.fpdual.web.fpdualweb.connector;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase responsable de la conexion con la base de datos MySQL
 */
public class MySQLConnector {

    @Setter
    @Getter
    Properties prop = new Properties();

    public MySQLConnector() {
        try {
            //Loads all the properties of file "config.properties".
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the connection object for a MySQL DDBB
     * @return a {@link Connection}
     */
    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        try {

            //Indicates which driver is going to be used.
            Class.forName(prop.getProperty(MySQLConstants.DRIVER));

            //Creates the connection based on the obtained URL.
            return  DriverManager.getConnection(getURL());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Obtains the URL to connect to a MySQL DDBB.
     * @return an URL
     */
    private String getURL() {
        //jdbc:mysql://localhost:3306/world?user=sa&password=12345678&useSSL=false; + other attributes required by the DB.
        return prop.getProperty(MySQLConstants.URL_PREFIX) +
                prop.getProperty(MySQLConstants.URL_HOST) + ":" +
                prop.getProperty(MySQLConstants.URL_PORT) + "/" +
                prop.getProperty(MySQLConstants.URL_SCHEMA) + "?user=" +
                prop.getProperty(MySQLConstants.USER) + "&password=" +
                prop.getProperty(MySQLConstants.PASSWD) + "&useSSL=" +
                prop.getProperty(MySQLConstants.URL_SSL) + ("&allowPublicKeyRetrieval=") +
                prop.getProperty(MySQLConstants.ALLOW_PUBLIC_KEY_RETRIEVAL) + ("&useJDBCCompliantTimezoneShift=") +
                prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT) + ("&useLegacyDatetimeCode=") +
                prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE) + ("&serverTimezone=") +
                prop.getProperty(MySQLConstants.SERVER_TIMEZONE);
    }

}
