package javafx.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL;
import javafx.model.domain.Entrega; 


public class EntregaDAO {
    
    private Connection connection;

    private final Database databaseMySql = new DatabaseMySQL();
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
     public boolean inserir(Entrega entrega) {
        String sql = "INSERT INTO ENTREGA(CLIENTE, ESPECIFICACAO, PESO, QUANTIDADE , COR , OBSERVACAO, ALT, LARG, COMP) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entrega.getCliente());
            stmt.setString(2, entrega.getEspecificacao());
            stmt.setBigDecimal(3, entrega.getPeso()); 
            stmt.setLong(4, entrega.getQuantidade()); 
            stmt.setString(5, entrega.getCor());
            stmt.setString(6, entrega.getObservacao());
            stmt.setBigDecimal(7, entrega.getAlt());
            stmt.setBigDecimal(8, entrega.getLarg());
            stmt.setBigDecimal(9, entrega.getComp());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    public boolean alterar(Entrega entrega) {
        String sql = "UPDATE ENTREGA SET CLIENTE= ?, ESPECIFICACAO = ?, PESO = ? , QUANTIDADE = ? , COR = ? , OBSERVACAO = ? , ALT = ? , LARG = ? , COMP = ?  WHERE IDENTREGA = ? ";
        try {
            if (connection == null ||  connection.isClosed()) {
                
               setConnection(databaseMySql.conectar());
            }
            
            System.out.print("Cliente da entrega" + entrega.getCliente());
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entrega.getCliente());
            stmt.setString(2, entrega.getEspecificacao());
            stmt.setBigDecimal(3, entrega.getPeso()); 
            stmt.setLong(4, entrega.getQuantidade()); 
            stmt.setString(5, entrega.getCor());
            stmt.setString(6, entrega.getObservacao());
            stmt.setBigDecimal(7, entrega.getAlt());
            stmt.setBigDecimal(8, entrega.getLarg());
            stmt.setBigDecimal(9, entrega.getComp());
            stmt.setLong(10, entrega.getIdEntrega());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PigmentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    
    
    public List<Entrega> listar() {
        String sql = "SELECT * FROM ENTREGA";
        List<Entrega> retorno = new ArrayList<>();
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Entrega entrega = new Entrega();
                entrega.setIdEntrega(resultado.getLong("IDENTREGA"));
                entrega.setEspecificacao(resultado.getString("ESPECIFICACAO"));
                entrega.setCliente(resultado.getString("CLIENTE"));
                entrega.setCor(resultado.getString("COR"));
                entrega.setPeso(resultado.getBigDecimal("PESO"));
                entrega.setQuantidade(resultado.getLong("QUANTIDADE"));
                entrega.setObservacao(resultado.getString("OBSERVACAO"));
                entrega.setAlt(resultado.getBigDecimal("ALT"));
                entrega.setLarg(resultado.getBigDecimal("LARG"));
                entrega.setComp(resultado.getBigDecimal("COMP"));
                
                
                retorno.add(entrega);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    

    public boolean remover(Entrega entrega) {
        String sql = "DELETE FROM ENTREGA WHERE IDENTREGA=?";
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entrega.getIdEntrega());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }    

    
    public List<Entrega> pesquisar(Entrega entrega) {
        String sql = "SELECT * FROM ENTREGA where 1 = 1";
        
        if(entrega.getEspecificacao()!= null && !entrega.getEspecificacao().equalsIgnoreCase("")) {
            sql += " and ESPECIFICACAO = '" + entrega.getEspecificacao() + "'";
        }
        
        if(entrega.getCor()!= null && !entrega.getCor().equalsIgnoreCase("")) {
            sql += " and COR = '" + entrega.getCor() + "'";
        }
           sql +=";";
        List<Entrega> retorno = new ArrayList<>();
        try {
            if (connection == null ||  connection.isClosed()) {
               System.out.print("Conexão fechada");
               setConnection(databaseMySql.conectar());
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Entrega entregas = new Entrega();
                entregas.setIdEntrega(resultado.getLong("IDENTREGA"));
                entregas.setEspecificacao(resultado.getString("ESPECIFICACAO"));
                entregas.setCliente(resultado.getString("CLIENTE"));
                entregas.setCor(resultado.getString("COR"));
                entregas.setPeso(resultado.getBigDecimal("PESO"));
                entregas.setQuantidade(resultado.getLong("QUANTIDADE"));
                entregas.setObservacao(resultado.getString("OBSERVACAO"));
                entregas.setAlt(resultado.getBigDecimal("ALT"));
                entregas.setLarg(resultado.getBigDecimal("LARG"));
                entregas.setComp(resultado.getBigDecimal("COMP"));
                retorno.add(entregas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }  
    
    
        	
private static void imprimeMetaPercentualPosDias(double percentualDiario, int quantidadeDias) {
		
		double valorBase  = 100;
		double percentual = percentualDiario;
		int dias = quantidadeDias ;
		for(int i=0; i < dias; i++) {
			valorBase = valorBase + ( valorBase * percentual /100);
		}
		double totalJuros = valorBase - 100;
		System.out.println("Total de juros = " + totalJuros );
	}
	
private static void imprimeLucroFinalDosDias(double aporte, double percentualDiario, int quantidadeDias) {
		
		double valorBase  = aporte;
		double percentual = percentualDiario;
		int dias = quantidadeDias ;
		for(int i=0; i < dias; i++) {
			valorBase = valorBase + ( valorBase * percentual /100);
		}
		
		System.out.println("Lucro final com aporte = " + valorBase);
		System.out.println("Lucro final sem aporte = " + (valorBase - aporte));
		
	}
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
     // percentualDiario, quantidadeDias
	imprimeMetaPercentualPosDias(7.5, 21);
     //aporte, percentualDiario, quantidadeDias
        imprimeLucroFinalDosDias(251, 3.71, 14);
    }
    
    
}
