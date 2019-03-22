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
 * A Cancelamento.
 */
@Document(collection = "cancelamento")
public class Cancelamento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_notal_fiscal")
    private Long idNotalFiscal;

    @NotNull
    @Size(max = 15)
    @Field("numero_protocolo")
    private String numeroProtocolo;

    
    @Field("protocolo")
    private byte[] protocolo;

    @Field("protocolo_content_type")
    private String protocoloContentType;

    @NotNull
    @Field("data_protocolo")
    private ZonedDateTime dataProtocolo;

    @Size(max = 3)
    @Field("codigo_erro")
    private String codigoErro;

    @Size(max = 255)
    @Field("mensagem_erro")
    private String mensagemErro;

    @Size(max = 255)
    @Field("justificativa")
    private String justificativa;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdNotalFiscal() {
        return idNotalFiscal;
    }

    public Cancelamento idNotalFiscal(Long idNotalFiscal) {
        this.idNotalFiscal = idNotalFiscal;
        return this;
    }

    public void setIdNotalFiscal(Long idNotalFiscal) {
        this.idNotalFiscal = idNotalFiscal;
    }

    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public Cancelamento numeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
        return this;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public byte[] getProtocolo() {
        return protocolo;
    }

    public Cancelamento protocolo(byte[] protocolo) {
        this.protocolo = protocolo;
        return this;
    }

    public void setProtocolo(byte[] protocolo) {
        this.protocolo = protocolo;
    }

    public String getProtocoloContentType() {
        return protocoloContentType;
    }

    public Cancelamento protocoloContentType(String protocoloContentType) {
        this.protocoloContentType = protocoloContentType;
        return this;
    }

    public void setProtocoloContentType(String protocoloContentType) {
        this.protocoloContentType = protocoloContentType;
    }

    public ZonedDateTime getDataProtocolo() {
        return dataProtocolo;
    }

    public Cancelamento dataProtocolo(ZonedDateTime dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
        return this;
    }

    public void setDataProtocolo(ZonedDateTime dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

    public Cancelamento codigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
        return this;
    }

    public void setCodigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public Cancelamento mensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
        return this;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public Cancelamento justificativa(String justificativa) {
        this.justificativa = justificativa;
        return this;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
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
        Cancelamento cancelamento = (Cancelamento) o;
        if (cancelamento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cancelamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cancelamento{" +
            "id=" + getId() +
            ", idNotalFiscal=" + getIdNotalFiscal() +
            ", numeroProtocolo='" + getNumeroProtocolo() + "'" +
            ", protocolo='" + getProtocolo() + "'" +
            ", protocoloContentType='" + getProtocoloContentType() + "'" +
            ", dataProtocolo='" + getDataProtocolo() + "'" +
            ", codigoErro='" + getCodigoErro() + "'" +
            ", mensagemErro='" + getMensagemErro() + "'" +
            ", justificativa='" + getJustificativa() + "'" +
            "}";
    }
}
