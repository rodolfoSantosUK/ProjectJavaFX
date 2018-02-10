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
public class Saco {
 
    private Long idSaco;
    
    private String cliente;
    
    private String especificacao;
    
    private BigDecimal peso;
    
    private BigDecimal alt;
    
    private BigDecimal larg;
    
    private BigDecimal comp;
    
    private Long quantidade;
    
    private String cor;
    
    private String observacao;

    /**
     * @return the idSaco
     */
    public Long getIdSaco() {
        return idSaco;
    }

    /**
     * @param idSaco the idSaco to set
     */
    public void setIdSaco(Long idSaco) {
        this.idSaco = idSaco;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the especificacao
     */
    public String getEspecificacao() {
        return especificacao;
    }

    /**
     * @param especificacao the especificacao to set
     */
    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    /**
     * @return the peso
     */
    public BigDecimal getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    /**
     * @return the quantidade
     */
    public Long getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
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

    /**
     * @return the alt
     */
    public BigDecimal getAlt() {
        return alt;
    }

    /**
     * @param alt the alt to set
     */
    public void setAlt(BigDecimal alt) {
        this.alt = alt;
    }

    /**
     * @return the larg
     */
    public BigDecimal getLarg() {
        return larg;
    }

    /**
     * @param larg the larg to set
     */
    public void setLarg(BigDecimal larg) {
        this.larg = larg;
    }

    /**
     * @return the comp
     */
    public BigDecimal getComp() {
        return comp;
    }

    /**
     * @param comp the comp to set
     */
    public void setComp(BigDecimal comp) {
        this.comp = comp;
    }
    
    
}
