package javafx.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.model.domain.Grao;

public class GraoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
    public boolean inserir(Grao grao) {
        String sql = "INSERT INTO GRAO(descricao) VALUES(?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
          //  stmt.setString(1, categoria.getDescricao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GraoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
public List<Grao> listar() {
        String sql = "SELECT * FROM GRAO";
        List<Grao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Grao grao = new Grao();
                grao.setTipo(resultado.getString("tipo"));
                grao.setQuantidade(resultado.getBigDecimal("quantidade"));
                retorno.add(grao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    

    
}
