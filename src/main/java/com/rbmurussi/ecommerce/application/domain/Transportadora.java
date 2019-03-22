package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Transportadora.
 */
@Document(collection = "transportadora")
public class Transportadora implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_transportadora")
    private Integer idTransportadora;

    @Field("tp_documento_enum")
    private Integer tpDocumentoEnum;

    @Size(max = 15)
    @Field("nr_documento")
    private String nrDocumento;

    @NotNull
    @Size(max = 60)
    @Field("x_nome")
    private String xNome;

    @Size(max = 14)
    @Field("i_e")
    private String iE;

    @Size(max = 60)
    @Field("x_ender")
    private String xEnder;

    @Size(max = 2)
    @Field("u_f")
    private String uF;

    @Size(max = 60)
    @Field("x_mun")
    private String xMun;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    @NotNull
    @Size(max = 60)
    @Field("versao")
    private String versao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdTransportadora() {
        return idTransportadora;
    }

    public Transportadora idTransportadora(Integer idTransportadora) {
        this.idTransportadora = idTransportadora;
        return this;
    }

    public void setIdTransportadora(Integer idTransportadora) {
        this.idTransportadora = idTransportadora;
    }

    public Integer getTpDocumentoEnum() {
        return tpDocumentoEnum;
    }

    public Transportadora tpDocumentoEnum(Integer tpDocumentoEnum) {
        this.tpDocumentoEnum = tpDocumentoEnum;
        return this;
    }

    public void setTpDocumentoEnum(Integer tpDocumentoEnum) {
        this.tpDocumentoEnum = tpDocumentoEnum;
    }

    public String getNrDocumento() {
        return nrDocumento;
    }

    public Transportadora nrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
        return this;
    }

    public void setNrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
    }

    public String getxNome() {
        return xNome;
    }

    public Transportadora xNome(String xNome) {
        this.xNome = xNome;
        return this;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getiE() {
        return iE;
    }

    public Transportadora iE(String iE) {
        this.iE = iE;
        return this;
    }

    public void setiE(String iE) {
        this.iE = iE;
    }

    public String getxEnder() {
        return xEnder;
    }

    public Transportadora xEnder(String xEnder) {
        this.xEnder = xEnder;
        return this;
    }

    public void setxEnder(String xEnder) {
        this.xEnder = xEnder;
    }

    public String getuF() {
        return uF;
    }

    public Transportadora uF(String uF) {
        this.uF = uF;
        return this;
    }

    public void setuF(String uF) {
        this.uF = uF;
    }

    public String getxMun() {
        return xMun;
    }

    public Transportadora xMun(String xMun) {
        this.xMun = xMun;
        return this;
    }

    public void setxMun(String xMun) {
        this.xMun = xMun;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public Transportadora idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
    }

    public String getVersao() {
        return versao;
    }

    public Transportadora versao(String versao) {
        this.versao = versao;
        return this;
    }

    public void setVersao(String versao) {
        this.versao = versao;
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
        Transportadora transportadora = (Transportadora) o;
        if (transportadora.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transportadora.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transportadora{" +
            "id=" + getId() +
            ", idTransportadora=" + getIdTransportadora() +
            ", tpDocumentoEnum=" + getTpDocumentoEnum() +
            ", nrDocumento='" + getNrDocumento() + "'" +
            ", xNome='" + getxNome() + "'" +
            ", iE='" + getiE() + "'" +
            ", xEnder='" + getxEnder() + "'" +
            ", uF='" + getuF() + "'" +
            ", xMun='" + getxMun() + "'" +
            ", idEmitente=" + getIdEmitente() +
            ", versao='" + getVersao() + "'" +
            "}";
    }
}
