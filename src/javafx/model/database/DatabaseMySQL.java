package javafx.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class DatabaseMySQL implements Database  {
    
    private Connection connection;
    
    @Override
    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
             this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cdpidata", "root","welcome1"); // HOME
         //  this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cdpidata", "root","welcome1"); // WORK
         //  this.connection = DriverManager.getConnection("jdbc:mysql://200.147.61.80/cdpidata?autoReconnect=true", "admincdpi","@welcome1"); // CLIENT
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
