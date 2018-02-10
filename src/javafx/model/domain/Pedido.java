/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.model.domain;

import java.math.BigDecimal;

/**
 *
 * @author Rodolfo
 */
public class Pedido {
 
   private String codigo,
           tipo, dataPedido,
           status, solicitante, 
           prazo,telSolicitante,
           emailSolicitante, endSolicitante;

   private BigDecimal qtdGrao, qtdPigmento;
   
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the dataPedido
     */
    public String getDataPedido() {
        return dataPedido;
    }

    /**
     * @param dataPedido the dataPedido to set
     */
    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the solicitante
     */
    public String getSolicitante() {
        return solicitante;
    }

    /**
     * @param solicitante the solicitante to set
     */
    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    /**
     * @return the prazo
     */
    public String getPrazo() {
        return prazo;
    }

    /**
     * @param prazo the prazo to set
     */
    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    /**
     * @return the telSolicitante
     */
    public String getTelSolicitante() {
        return telSolicitante;
    }

    /**
     * @param telSolicitante the telSolicitante to set
     */
    public void setTelSolicitante(String telSolicitante) {
        this.telSolicitante = telSolicitante;
    }

    /**
     * @return the emailSolicitante
     */
    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    /**
     * @param emailSolicitante the emailSolicitante to set
     */
    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    /**
     * @return the endSolicitante
     */
    public String getEndSolicitante() {
        return endSolicitante;
    }

    /**
     * @param endSolicitante the endSolicitante to set
     */
    public void setEndSolicitante(String endSolicitante) {
        this.endSolicitante = endSolicitante;
    }

    /**
     * @return the qtdGrao
     */
    public BigDecimal getQtdGrao() {
        return qtdGrao;
    }

    /**
     * @param qtdGrao the qtdGrao to set
     */
    public void setQtdGrao(BigDecimal qtdGrao) {
        this.qtdGrao = qtdGrao;
    }

    /**
     * @return the qtdPigmento
     */
    public BigDecimal getQtdPigmento() {
        return qtdPigmento;
    }

    /**
     * @param qtdPigmento the qtdPigmento to set
     */
    public void setQtdPigmento(BigDecimal qtdPigmento) {
        this.qtdPigmento = qtdPigmento;
    }
   
   
   
   
    
    
}

