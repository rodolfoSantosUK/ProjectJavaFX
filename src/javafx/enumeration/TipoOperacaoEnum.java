/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.enumeration;

/**
 *
 * @author PC-CASA
 */
public enum TipoOperacaoEnum {
    
    CRIACAO("Criacao"),
    ALTERACAO("Alteracao");
    
    
    TipoOperacaoEnum(String tipoOperacao){
             this.tipoOperacao = tipoOperacao;
    }
    
    
    
    private String tipoOperacao;

    /**
     * @return the tipoOperacao
     */
    public String getTipoOperacao() {
        return tipoOperacao;
    }

    /**
     * @param tipoOperacao the tipoOperacao to set
     */
    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }
    
    
    
    
}
