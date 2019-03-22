package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Lote.
 */
@Document(collection = "lote")
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_lote")
    private Long idLote;

    @NotNull
    @Size(max = 20)
    @Field("c_npj_transmissao")
    private String cNPJTransmissao;

    @NotNull
    @Field("data_transmissao")
    private ZonedDateTime dataTransmissao;

    @Size(max = 15)
    @Field("num_recibo")
    private String numRecibo;

    @Field("xml_retorno")
    private byte[] xmlRetorno;

    @Field("xml_retorno_content_type")
    private String xmlRetornoContentType;

    @Field("data_proc")
    private ZonedDateTime dataProc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdLote() {
        return idLote;
    }

    public Lote idLote(Long idLote) {
        this.idLote = idLote;
        return this;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    public String getcNPJTransmissao() {
        return cNPJTransmissao;
    }

    public Lote cNPJTransmissao(String cNPJTransmissao) {
        this.cNPJTransmissao = cNPJTransmissao;
        return this;
    }

    public void setcNPJTransmissao(String cNPJTransmissao) {
        this.cNPJTransmissao = cNPJTransmissao;
    }

    public ZonedDateTime getDataTransmissao() {
        return dataTransmissao;
    }

    public Lote dataTransmissao(ZonedDateTime dataTransmissao) {
        this.dataTransmissao = dataTransmissao;
        return this;
    }

    public void setDataTransmissao(ZonedDateTime dataTransmissao) {
        this.dataTransmissao = dataTransmissao;
    }

    public String getNumRecibo() {
        return numRecibo;
    }

    public Lote numRecibo(String numRecibo) {
        this.numRecibo = numRecibo;
        return this;
    }

    public void setNumRecibo(String numRecibo) {
        this.numRecibo = numRecibo;
    }

    public byte[] getXmlRetorno() {
        return xmlRetorno;
    }

    public Lote xmlRetorno(byte[] xmlRetorno) {
        this.xmlRetorno = xmlRetorno;
        return this;
    }

    public void setXmlRetorno(byte[] xmlRetorno) {
        this.xmlRetorno = xmlRetorno;
    }

    public String getXmlRetornoContentType() {
        return xmlRetornoContentType;
    }

    public Lote xmlRetornoContentType(String xmlRetornoContentType) {
        this.xmlRetornoContentType = xmlRetornoContentType;
        return this;
    }

    public void setXmlRetornoContentType(String xmlRetornoContentType) {
        this.xmlRetornoContentType = xmlRetornoContentType;
    }

    public ZonedDateTime getDataProc() {
        return dataProc;
    }

    public Lote dataProc(ZonedDateTime dataProc) {
        this.dataProc = dataProc;
        return this;
    }

    public void setDataProc(ZonedDateTime dataProc) {
        this.dataProc = dataProc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lote lote = (Lote) o;
        if (lote.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lote.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Lote{" +
            "id=" + getId() +
            ", idLote=" + getIdLote() +
            ", cNPJTransmissao='" + getcNPJTransmissao() + "'" +
            ", dataTransmissao='" + getDataTransmissao() + "'" +
            ", numRecibo='" + getNumRecibo() + "'" +
            ", xmlRetorno='" + getXmlRetorno() + "'" +
            ", xmlRetornoContentType='" + getXmlRetornoContentType() + "'" +
            ", dataProc='" + getDataProc() + "'" +
            "}";
    }
}
