package javafx.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL;
import javafx.model.domain.Pigmento;

/**
 *
 * @author rlsantos
 */
public class PigmentoDAO {

    private Connection connection ;

    private final Database databaseMySql = new DatabaseMySQL();
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
        
    public boolean inserir(Pigmento pigmento) {
        String sql = "INSERT INTO PIGMENTO(FORNECEDOR, TIPO, QUANTIDADE, OBSERVACAO) VALUES(?,?,?,?)";
        try {
            
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, pigmento.getFornecedor());
            stmt.setString(2, pigmento.getTipoPigmento());
            stmt.setBigDecimal(3, pigmento.getQuantidade()); 
            stmt.setString(4, pigmento.getObservacao()); 
            stmt.execute();
            stmt.close();
             
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PigmentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public boolean alterar(Pigmento pigmento) {
        String sql = "UPDATE PIGMENTO SET FORNECEDOR = ?,   TIPO = ?,  QUANTIDADE = ?,   OBSERVACAO = ? WHERE IDPIGMENTO = ? ";
        try {
            
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, pigmento.getFornecedor());
            stmt.setString(2, pigmento.getTipoPigmento());
            stmt.setBigDecimal(3, pigmento.getQuantidade()); 
            stmt.setString(4, pigmento.getObservacao());
            stmt.setLong(5, pigmento.getIdPigmento());
            stmt.execute();
            stmt.close();
            
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PigmentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public List<Pigmento> listar() {
        String sql = "SELECT * FROM PIGMENTO";
        List<Pigmento> retorno = new ArrayList<>();
        try {
            
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Pigmento pigmento = new Pigmento();
                pigmento.setIdPigmento(resultado.getLong("IDPIGMENTO"));
                pigmento.setFornecedor(resultado.getString("FORNECEDOR"));  
                pigmento.setQuantidade(resultado.getBigDecimal("QUANTIDADE"));
                pigmento.setTipoPigmento(resultado.getString("TIPO"));
                pigmento.setObservacao(resultado.getString("OBSERVACAO"));
                retorno.add(pigmento);
            }
                resultado.close();
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(GraoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    

    public boolean remover( Pigmento pigmento) {
        String sql = "DELETE FROM PIGMENTO WHERE IDPIGMENTO=?";
        try {
            
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setLong(1, pigmento.getIdPigmento());
            stmt.execute();
            stmt.close();
             
            connection.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }    

    
    public List<Pigmento> pesquisar(Pigmento pigmento) {
        String sql = "SELECT * FROM PIGMENTO where 1 = 1";
        
        if(pigmento.getFornecedor() != null && !pigmento.getFornecedor().equalsIgnoreCase("")) {
            sql += " and FORNECEDOR = '" + pigmento.getFornecedor() + "'";
        }
        
        if(pigmento.getTipoPigmento() != null && !pigmento.getTipoPigmento().equalsIgnoreCase("")) {
            sql += " and TIPO = '" + pigmento.getTipoPigmento() + "'";
        }
           sql +=";";
        List<Pigmento> retorno = new ArrayList<>();
        try {
            
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Pigmento pigmentos = new Pigmento();
                pigmentos.setIdPigmento(resultado.getLong("IDPIGMENTO"));
                pigmentos.setFornecedor(resultado.getString("FORNECEDOR"));  
                pigmentos.setQuantidade(resultado.getBigDecimal("QUANTIDADE"));
                pigmentos.setTipoPigmento(resultado.getString("TIPO"));
                pigmentos.setObservacao(resultado.getString("OBSERVACAO"));
                retorno.add(pigmentos);
            }
                resultado.close();
                stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(GraoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }  
    
    

}