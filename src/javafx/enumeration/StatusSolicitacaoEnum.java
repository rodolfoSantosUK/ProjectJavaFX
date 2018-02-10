/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.enumeration;

/**
 *
 * @author Giselle
 */
public enum StatusSolicitacaoEnum {

    CONCLUIDO("CONC","Concluído"),
    ANDAMENTO("AND","Andamento"),
    CANCELADO("CANC","Cancelado");
    
    private String cod;
    private String descricao;

    
    StatusSolicitacaoEnum(String cod,   String descricao ) {
        this.cod = cod;
        this.descricao = descricao;
                
    }
    
    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    
    
}
