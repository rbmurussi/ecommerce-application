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
 * A CertificadoInfo.
 */
@Document(collection = "certificado_info")
public class CertificadoInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_certificado_info")
    private Integer idCertificadoInfo;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    @NotNull
    @Size(max = 255)
    @Field("alias")
    private String alias;

    @NotNull
    @Size(max = 255)
    @Field("nome")
    private String nome;

    @NotNull
    @Size(max = 255)
    @Field("autoridade_certificadora")
    private String autoridadeCertificadora;

    @NotNull
    @Size(max = 14)
    @Field("c_npj")
    private String cNPJ;

    @NotNull
    @Size(max = 255)
    @Field("caminho")
    private String caminho;

    @NotNull
    @Size(max = 2)
    @Field("tipo_certificado")
    private String tipoCertificado;

    @Field("data_utilizacao")
    private ZonedDateTime dataUtilizacao;

    @NotNull
    @Field("data_validade")
    private ZonedDateTime dataValidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdCertificadoInfo() {
        return idCertificadoInfo;
    }

    public CertificadoInfo idCertificadoInfo(Integer idCertificadoInfo) {
        this.idCertificadoInfo = idCertificadoInfo;
        return this;
    }

    public void setIdCertificadoInfo(Integer idCertificadoInfo) {
        this.idCertificadoInfo = idCertificadoInfo;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public CertificadoInfo idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
    }

    public String getAlias() {
        return alias;
    }

    public CertificadoInfo alias(String alias) {
        this.alias = alias;
        return this;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNome() {
        return nome;
    }

    public CertificadoInfo nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutoridadeCertificadora() {
        return autoridadeCertificadora;
    }

    public CertificadoInfo autoridadeCertificadora(String autoridadeCertificadora) {
        this.autoridadeCertificadora = autoridadeCertificadora;
        return this;
    }

    public void setAutoridadeCertificadora(String autoridadeCertificadora) {
        this.autoridadeCertificadora = autoridadeCertificadora;
    }

    public String getcNPJ() {
        return cNPJ;
    }

    public CertificadoInfo cNPJ(String cNPJ) {
        this.cNPJ = cNPJ;
        return this;
    }

    public void setcNPJ(String cNPJ) {
        this.cNPJ = cNPJ;
    }

    public String getCaminho() {
        return caminho;
    }

    public CertificadoInfo caminho(String caminho) {
        this.caminho = caminho;
        return this;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getTipoCertificado() {
        return tipoCertificado;
    }

    public CertificadoInfo tipoCertificado(String tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
        return this;
    }

    public void setTipoCertificado(String tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }

    public ZonedDateTime getDataUtilizacao() {
        return dataUtilizacao;
    }

    public CertificadoInfo dataUtilizacao(ZonedDateTime dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
        return this;
    }

    public void setDataUtilizacao(ZonedDateTime dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
    }

    public ZonedDateTime getDataValidade() {
        return dataValidade;
    }

    public CertificadoInfo dataValidade(ZonedDateTime dataValidade) {
        this.dataValidade = dataValidade;
        return this;
    }

    public void setDataValidade(ZonedDateTime dataValidade) {
        this.dataValidade = dataValidade;
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
        CertificadoInfo certificadoInfo = (CertificadoInfo) o;
        if (certificadoInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificadoInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertificadoInfo{" +
            "id=" + getId() +
            ", idCertificadoInfo=" + getIdCertificadoInfo() +
            ", idEmitente=" + getIdEmitente() +
            ", alias='" + getAlias() + "'" +
            ", nome='" + getNome() + "'" +
            ", autoridadeCertificadora='" + getAutoridadeCertificadora() + "'" +
            ", cNPJ='" + getcNPJ() + "'" +
            ", caminho='" + getCaminho() + "'" +
            ", tipoCertificado='" + getTipoCertificado() + "'" +
            ", dataUtilizacao='" + getDataUtilizacao() + "'" +
            ", dataValidade='" + getDataValidade() + "'" +
            "}";
    }
}
