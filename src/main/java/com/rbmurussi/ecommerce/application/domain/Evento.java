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
 * A Evento.
 */
@Document(collection = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_evento")
    private Long idEvento;

    @NotNull
    @Field("id_notal_fiscal")
    private Long idNotalFiscal;

    @NotNull
    @Size(max = 7)
    @Field("tp_evento")
    private String tpEvento;

    @NotNull
    @Field("n_seq_evento")
    private Integer nSeqEvento;

    @Field("data_evento")
    private ZonedDateTime dataEvento;

    @Size(max = 15)
    @Field("num_protocolo")
    private String numProtocolo;

    
    @Field("xml_proc")
    private byte[] xmlProc;

    @Field("xml_proc_content_type")
    private String xmlProcContentType;

    @Field("data_reg_evento")
    private ZonedDateTime dataRegEvento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public Evento idEvento(Long idEvento) {
        this.idEvento = idEvento;
        return this;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Long getIdNotalFiscal() {
        return idNotalFiscal;
    }

    public Evento idNotalFiscal(Long idNotalFiscal) {
        this.idNotalFiscal = idNotalFiscal;
        return this;
    }

    public void setIdNotalFiscal(Long idNotalFiscal) {
        this.idNotalFiscal = idNotalFiscal;
    }

    public String getTpEvento() {
        return tpEvento;
    }

    public Evento tpEvento(String tpEvento) {
        this.tpEvento = tpEvento;
        return this;
    }

    public void setTpEvento(String tpEvento) {
        this.tpEvento = tpEvento;
    }

    public Integer getnSeqEvento() {
        return nSeqEvento;
    }

    public Evento nSeqEvento(Integer nSeqEvento) {
        this.nSeqEvento = nSeqEvento;
        return this;
    }

    public void setnSeqEvento(Integer nSeqEvento) {
        this.nSeqEvento = nSeqEvento;
    }

    public ZonedDateTime getDataEvento() {
        return dataEvento;
    }

    public Evento dataEvento(ZonedDateTime dataEvento) {
        this.dataEvento = dataEvento;
        return this;
    }

    public void setDataEvento(ZonedDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getNumProtocolo() {
        return numProtocolo;
    }

    public Evento numProtocolo(String numProtocolo) {
        this.numProtocolo = numProtocolo;
        return this;
    }

    public void setNumProtocolo(String numProtocolo) {
        this.numProtocolo = numProtocolo;
    }

    public byte[] getXmlProc() {
        return xmlProc;
    }

    public Evento xmlProc(byte[] xmlProc) {
        this.xmlProc = xmlProc;
        return this;
    }

    public void setXmlProc(byte[] xmlProc) {
        this.xmlProc = xmlProc;
    }

    public String getXmlProcContentType() {
        return xmlProcContentType;
    }

    public Evento xmlProcContentType(String xmlProcContentType) {
        this.xmlProcContentType = xmlProcContentType;
        return this;
    }

    public void setXmlProcContentType(String xmlProcContentType) {
        this.xmlProcContentType = xmlProcContentType;
    }

    public ZonedDateTime getDataRegEvento() {
        return dataRegEvento;
    }

    public Evento dataRegEvento(ZonedDateTime dataRegEvento) {
        this.dataRegEvento = dataRegEvento;
        return this;
    }

    public void setDataRegEvento(ZonedDateTime dataRegEvento) {
        this.dataRegEvento = dataRegEvento;
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
        Evento evento = (Evento) o;
        if (evento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + getId() +
            ", idEvento=" + getIdEvento() +
            ", idNotalFiscal=" + getIdNotalFiscal() +
            ", tpEvento='" + getTpEvento() + "'" +
            ", nSeqEvento=" + getnSeqEvento() +
            ", dataEvento='" + getDataEvento() + "'" +
            ", numProtocolo='" + getNumProtocolo() + "'" +
            ", xmlProc='" + getXmlProc() + "'" +
            ", xmlProcContentType='" + getXmlProcContentType() + "'" +
            ", dataRegEvento='" + getDataRegEvento() + "'" +
            "}";
    }
}
