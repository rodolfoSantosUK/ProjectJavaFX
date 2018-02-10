/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.model.domain;

import java.math.BigDecimal;

/**
 *
 * @author rlsantos
 */
public class Pigmento {
    
    private Long idPigmento;
    
    private String fornecedor;
    
    private String tipoPigmento;
    
    private BigDecimal quantidade; 

    private String observacao;
    
    /**
     * @return the idFornecedor
     */
    public Long getIdPigmento() {
        return idPigmento;
    }

    public void setIdPigmento(Long idPigmento) {
        this.idPigmento = idPigmento;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the tipoPigmento
     */
    public String getTipoPigmento() {
        return tipoPigmento;
    }

    /**
     * @param tipoPigmento the tipoPigmento to set
     */
    public void setTipoPigmento(String tipoPigmento) {
        this.tipoPigmento = tipoPigmento;
    }

    /**
     * @return the quantidade
     */
    public BigDecimal getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
    
    
}
