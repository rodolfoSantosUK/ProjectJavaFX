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
import javafx.model.domain.Saco;


public class EstoqueDAO {
    
    private Connection connection;

    private final Database databaseMySql = new DatabaseMySQL();
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
     public boolean inserir(Saco saco) {
        String sql = "INSERT INTO SACO(CLIENTE, ESPECIFICACAO, PESO, QUANTIDADE , COR , OBSERVACAO, ALT, LARG, COMP) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, saco.getCliente());
            stmt.setString(2, saco.getEspecificacao());
            stmt.setBigDecimal(3, saco.getPeso()); 
            stmt.setLong(4, saco.getQuantidade()); 
            stmt.setString(5, saco.getCor());
            stmt.setString(6, saco.getObservacao());
            stmt.setBigDecimal(7, saco.getAlt());
            stmt.setBigDecimal(8, saco.getLarg());
            stmt.setBigDecimal(9, saco.getComp());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PigmentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean atualizarQuantidadeSaco(Saco saco) {
        String sql = "UPDATE SACO SET QUANTIDADE = ? WHERE IDSACO = ? ";
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, saco.getQuantidade()); 
            stmt.setLong(2, saco.getIdSaco());
            stmt.execute();
            
			return true;
        } catch (SQLException ex) {
            Logger.getLogger(PigmentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    public boolean alterar(Saco saco) {
        String sql = "UPDATE SACO SET CLIENTE= ?, ESPECIFICACAO = ?, PESO = ? , QUANTIDADE = ? , COR = ? , OBSERVACAO = ?, ALT = ? , LARG = ? , COMP = ? WHERE IDSACO = ? ";
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, saco.getCliente());
            stmt.setString(2, saco.getEspecificacao());
            stmt.setBigDecimal(3, saco.getPeso()); 
            stmt.setLong(4, saco.getQuantidade()); 
            stmt.setString(5, saco.getCor());
            stmt.setString(6, saco.getObservacao());
            stmt.setBigDecimal(7, saco.getAlt());
            stmt.setBigDecimal(8, saco.getLarg());
            stmt.setBigDecimal(9, saco.getComp());
            stmt.setLong(10, saco.getIdSaco());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PigmentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public List<Saco> listar() {
        String sql = "SELECT * FROM SACO";
        List<Saco> retorno = new ArrayList<>();
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Saco saco = new Saco();
                saco.setIdSaco(resultado.getLong("IDSACO"));
                saco.setEspecificacao(resultado.getString("ESPECIFICACAO"));
                saco.setCliente(resultado.getString("CLIENTE"));
                saco.setCor(resultado.getString("COR"));
                saco.setPeso(resultado.getBigDecimal("PESO"));
                saco.setQuantidade(resultado.getLong("QUANTIDADE"));
                saco.setObservacao(resultado.getString("OBSERVACAO"));
                saco.setAlt(resultado.getBigDecimal("ALT"));
                saco.setLarg(resultado.getBigDecimal("LARG"));
                saco.setComp(resultado.getBigDecimal("COMP"));
                
                retorno.add(saco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    

    public boolean remover(Saco saco) {
        String sql = "DELETE FROM SACO WHERE IDSACO = ?";
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, saco.getIdSaco());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }    

    
    public List<Saco> pesquisar(Saco saco) {
        String sql = "SELECT * FROM SACO where 1 = 1";
        
        if(saco.getEspecificacao()!= null && !saco.getEspecificacao().equalsIgnoreCase("")) {
            sql += " and ESPECIFICACAO = '" + saco.getEspecificacao() + "'";
        }
        
        if(saco.getCor()!= null && !saco.getCor().equalsIgnoreCase("")) {
            sql += " and COR = '" + saco.getCor() + "'";
        }
           sql +=";";
        List<Saco> retorno = new ArrayList<>();
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Saco sacos = new Saco();
                sacos.setIdSaco(resultado.getLong("IDSACO"));
                sacos.setEspecificacao(resultado.getString("ESPECIFICACAO"));
                sacos.setCliente(resultado.getString("CLIENTE"));
                sacos.setCor(resultado.getString("COR"));
                sacos.setPeso(resultado.getBigDecimal("PESO"));
                sacos.setQuantidade(resultado.getLong("QUANTIDADE"));
                sacos.setObservacao(resultado.getString("OBSERVACAO"));
                sacos.setAlt(resultado.getBigDecimal("ALT"));
                sacos.setLarg(resultado.getBigDecimal("LARG"));
                sacos.setComp(resultado.getBigDecimal("COMP"));
                retorno.add(sacos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }  
    
    
    public  Saco pesquisarSacoEspecifico(Saco saco) {
        
       // System.out.print("Método pesquisarSacoEspecifico ");
        
     //   System.out.print(" especificação " + saco.getEspecificacao());
      //  System.out.print(" cor " + saco.getCor());
    //    System.out.print(" peso " + saco.getPeso());
        
        String sql = "SELECT * FROM SACO where 1 = 1";
        
        if(saco.getEspecificacao()!= null && !saco.getEspecificacao().equalsIgnoreCase("")) {
            sql += " and ESPECIFICACAO = '" + saco.getEspecificacao() + "'";
        }
        
        if(saco.getCor()!= null && !saco.getCor().equalsIgnoreCase("")) {
            sql += " and COR = '" + saco.getCor() + "'";
        }
	
        if(saco.getAlt()!= null) {
            sql += " and ALT = " + saco.getAlt();
        }
        
        
        if(saco.getLarg()!= null) {
            sql += " and LARG = " + saco.getLarg();
        }
        
        
        if(saco.getComp()!= null) {
            sql += " and COMP = " + saco.getComp();
        }
        
	    sql +=";";
        
            System.out.print(" sql " + sql);
            
		Saco retorno = new Saco();
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Saco result = new Saco();
                result.setIdSaco(resultado.getLong("IDSACO"));
                result.setEspecificacao(resultado.getString("ESPECIFICACAO"));
                result.setCliente(resultado.getString("CLIENTE"));
                result.setCor(resultado.getString("COR"));
                result.setPeso(resultado.getBigDecimal("PESO"));
                result.setQuantidade(resultado.getLong("QUANTIDADE"));
                result.setObservacao(resultado.getString("OBSERVACAO"));
                result.setAlt(resultado.getBigDecimal("ALT"));
                result.setLarg(resultado.getBigDecimal("LARG"));
                result.setComp(resultado.getBigDecimal("COMP"));
                
                
                retorno = result ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    } 
    
    public  Saco pesquisarSacoById(Saco saco) {
        
        
        String sql = "SELECT * FROM SACO where 1 = 1";
        
        if(saco.getEspecificacao()!= null && !saco.getEspecificacao().equalsIgnoreCase("")) {
            sql += " and IDSACO = '" + saco.getIdSaco() + "'";
        }
        
	    sql +=";";
        
            System.out.print(" sql " + sql);
            
		Saco retorno = new Saco();
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Saco result = new Saco();
                result.setIdSaco(resultado.getLong("IDSACO"));
                result.setEspecificacao(resultado.getString("ESPECIFICACAO"));
                result.setCliente(resultado.getString("CLIENTE"));
                result.setCor(resultado.getString("COR"));
                result.setPeso(resultado.getBigDecimal("PESO"));
                result.setQuantidade(resultado.getLong("QUANTIDADE"));
                result.setObservacao(resultado.getString("OBSERVACAO"));
                result.setAlt(resultado.getBigDecimal("ALT"));
                result.setLarg(resultado.getBigDecimal("LARG"));
                result.setComp(resultado.getBigDecimal("COMP"));
                
                retorno = result ;
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return retorno;
    }
    
    
}
