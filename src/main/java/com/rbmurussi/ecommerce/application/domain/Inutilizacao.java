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
 * A Inutilizacao.
 */
@Document(collection = "inutilizacao")
public class Inutilizacao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_inutilizacao")
    private Long idInutilizacao;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    @NotNull
    @Size(max = 3)
    @Field("serie")
    private String serie;

    @NotNull
    @Size(max = 9)
    @Field("numero_inicial")
    private String numeroInicial;

    @NotNull
    @Size(max = 9)
    @Field("numero_final")
    private String numeroFinal;

    @Field("protocolo_xml")
    private byte[] protocoloXml;

    @Field("protocolo_xml_content_type")
    private String protocoloXmlContentType;

    @Field("data_inutilizacao")
    private ZonedDateTime dataInutilizacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdInutilizacao() {
        return idInutilizacao;
    }

    public Inutilizacao idInutilizacao(Long idInutilizacao) {
        this.idInutilizacao = idInutilizacao;
        return this;
    }

    public void setIdInutilizacao(Long idInutilizacao) {
        this.idInutilizacao = idInutilizacao;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public Inutilizacao idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
    }

    public String getSerie() {
        return serie;
    }

    public Inutilizacao serie(String serie) {
        this.serie = serie;
        return this;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumeroInicial() {
        return numeroInicial;
    }

    public Inutilizacao numeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
        return this;
    }

    public void setNumeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public String getNumeroFinal() {
        return numeroFinal;
    }

    public Inutilizacao numeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
        return this;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public byte[] getProtocoloXml() {
        return protocoloXml;
    }

    public Inutilizacao protocoloXml(byte[] protocoloXml) {
        this.protocoloXml = protocoloXml;
        return this;
    }

    public void setProtocoloXml(byte[] protocoloXml) {
        this.protocoloXml = protocoloXml;
    }

    public String getProtocoloXmlContentType() {
        return protocoloXmlContentType;
    }

    public Inutilizacao protocoloXmlContentType(String protocoloXmlContentType) {
        this.protocoloXmlContentType = protocoloXmlContentType;
        return this;
    }

    public void setProtocoloXmlContentType(String protocoloXmlContentType) {
        this.protocoloXmlContentType = protocoloXmlContentType;
    }

    public ZonedDateTime getDataInutilizacao() {
        return dataInutilizacao;
    }

    public Inutilizacao dataInutilizacao(ZonedDateTime dataInutilizacao) {
        this.dataInutilizacao = dataInutilizacao;
        return this;
    }

    public void setDataInutilizacao(ZonedDateTime dataInutilizacao) {
        this.dataInutilizacao = dataInutilizacao;
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
        Inutilizacao inutilizacao = (Inutilizacao) o;
        if (inutilizacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inutilizacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Inutilizacao{" +
            "id=" + getId() +
            ", idInutilizacao=" + getIdInutilizacao() +
            ", idEmitente=" + getIdEmitente() +
            ", serie='" + getSerie() + "'" +
            ", numeroInicial='" + getNumeroInicial() + "'" +
            ", numeroFinal='" + getNumeroFinal() + "'" +
            ", protocoloXml='" + getProtocoloXml() + "'" +
            ", protocoloXmlContentType='" + getProtocoloXmlContentType() + "'" +
            ", dataInutilizacao='" + getDataInutilizacao() + "'" +
            "}";
    }
}
