package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Emitente.
 */
@Document(collection = "emitente")
public class Emitente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    @NotNull
    @Size(max = 60)
    @Field("x_nome")
    private String xNome;

    @Size(max = 60)
    @Field("x_fant")
    private String xFant;

    @NotNull
    @Size(max = 60)
    @Field("x_lgr")
    private String xLgr;

    @NotNull
    @Size(max = 60)
    @Field("n_ro")
    private String nRo;

    @Size(max = 60)
    @Field("x_cpl")
    private String xCpl;

    @NotNull
    @Size(max = 60)
    @Field("x_bairro")
    private String xBairro;

    @NotNull
    @Size(max = 7)
    @Field("c_mun")
    private String cMun;

    @NotNull
    @Size(max = 60)
    @Field("x_mun")
    private String xMun;

    @NotNull
    @Size(max = 2)
    @Field("u_f")
    private String uF;

    @Size(max = 8)
    @Field("c_ep")
    private String cEP;

    @Size(max = 4)
    @Field("c_pais")
    private String cPais;

    @Size(max = 60)
    @Field("x_pais")
    private String xPais;

    @Size(max = 14)
    @Field("fone")
    private String fone;

    @NotNull
    @Size(max = 14)
    @Field("i_e")
    private String iE;

    @Size(max = 14)
    @Field("i_est")
    private String iEST;

    @Size(max = 15)
    @Field("i_m")
    private String iM;

    @Size(max = 7)
    @Field("c_nae")
    private String cNAE;

    @Field("logotipo")
    private byte[] logotipo;

    @Field("logotipo_content_type")
    private String logotipoContentType;

    @NotNull
    @Field("tp_documento_enum")
    private Integer tpDocumentoEnum;

    @NotNull
    @Size(max = 14)
    @Field("nr_documento")
    private String nrDocumento;

    @NotNull
    @Size(max = 4)
    @Field("regime_tributario")
    private String regimeTributario;

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

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public Emitente idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
    }

    public String getxNome() {
        return xNome;
    }

    public Emitente xNome(String xNome) {
        this.xNome = xNome;
        return this;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getxFant() {
        return xFant;
    }

    public Emitente xFant(String xFant) {
        this.xFant = xFant;
        return this;
    }

    public void setxFant(String xFant) {
        this.xFant = xFant;
    }

    public String getxLgr() {
        return xLgr;
    }

    public Emitente xLgr(String xLgr) {
        this.xLgr = xLgr;
        return this;
    }

    public void setxLgr(String xLgr) {
        this.xLgr = xLgr;
    }

    public String getnRo() {
        return nRo;
    }

    public Emitente nRo(String nRo) {
        this.nRo = nRo;
        return this;
    }

    public void setnRo(String nRo) {
        this.nRo = nRo;
    }

    public String getxCpl() {
        return xCpl;
    }

    public Emitente xCpl(String xCpl) {
        this.xCpl = xCpl;
        return this;
    }

    public void setxCpl(String xCpl) {
        this.xCpl = xCpl;
    }

    public String getxBairro() {
        return xBairro;
    }

    public Emitente xBairro(String xBairro) {
        this.xBairro = xBairro;
        return this;
    }

    public void setxBairro(String xBairro) {
        this.xBairro = xBairro;
    }

    public String getcMun() {
        return cMun;
    }

    public Emitente cMun(String cMun) {
        this.cMun = cMun;
        return this;
    }

    public void setcMun(String cMun) {
        this.cMun = cMun;
    }

    public String getxMun() {
        return xMun;
    }

    public Emitente xMun(String xMun) {
        this.xMun = xMun;
        return this;
    }

    public void setxMun(String xMun) {
        this.xMun = xMun;
    }

    public String getuF() {
        return uF;
    }

    public Emitente uF(String uF) {
        this.uF = uF;
        return this;
    }

    public void setuF(String uF) {
        this.uF = uF;
    }

    public String getcEP() {
        return cEP;
    }

    public Emitente cEP(String cEP) {
        this.cEP = cEP;
        return this;
    }

    public void setcEP(String cEP) {
        this.cEP = cEP;
    }

    public String getcPais() {
        return cPais;
    }

    public Emitente cPais(String cPais) {
        this.cPais = cPais;
        return this;
    }

    public void setcPais(String cPais) {
        this.cPais = cPais;
    }

    public String getxPais() {
        return xPais;
    }

    public Emitente xPais(String xPais) {
        this.xPais = xPais;
        return this;
    }

    public void setxPais(String xPais) {
        this.xPais = xPais;
    }

    public String getFone() {
        return fone;
    }

    public Emitente fone(String fone) {
        this.fone = fone;
        return this;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getiE() {
        return iE;
    }

    public Emitente iE(String iE) {
        this.iE = iE;
        return this;
    }

    public void setiE(String iE) {
        this.iE = iE;
    }

    public String getiEST() {
        return iEST;
    }

    public Emitente iEST(String iEST) {
        this.iEST = iEST;
        return this;
    }

    public void setiEST(String iEST) {
        this.iEST = iEST;
    }

    public String getiM() {
        return iM;
    }

    public Emitente iM(String iM) {
        this.iM = iM;
        return this;
    }

    public void setiM(String iM) {
        this.iM = iM;
    }

    public String getcNAE() {
        return cNAE;
    }

    public Emitente cNAE(String cNAE) {
        this.cNAE = cNAE;
        return this;
    }

    public void setcNAE(String cNAE) {
        this.cNAE = cNAE;
    }

    public byte[] getLogotipo() {
        return logotipo;
    }

    public Emitente logotipo(byte[] logotipo) {
        this.logotipo = logotipo;
        return this;
    }

    public void setLogotipo(byte[] logotipo) {
        this.logotipo = logotipo;
    }

    public String getLogotipoContentType() {
        return logotipoContentType;
    }

    public Emitente logotipoContentType(String logotipoContentType) {
        this.logotipoContentType = logotipoContentType;
        return this;
    }

    public void setLogotipoContentType(String logotipoContentType) {
        this.logotipoContentType = logotipoContentType;
    }

    public Integer getTpDocumentoEnum() {
        return tpDocumentoEnum;
    }

    public Emitente tpDocumentoEnum(Integer tpDocumentoEnum) {
        this.tpDocumentoEnum = tpDocumentoEnum;
        return this;
    }

    public void setTpDocumentoEnum(Integer tpDocumentoEnum) {
        this.tpDocumentoEnum = tpDocumentoEnum;
    }

    public String getNrDocumento() {
        return nrDocumento;
    }

    public Emitente nrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
        return this;
    }

    public void setNrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
    }

    public String getRegimeTributario() {
        return regimeTributario;
    }

    public Emitente regimeTributario(String regimeTributario) {
        this.regimeTributario = regimeTributario;
        return this;
    }

    public void setRegimeTributario(String regimeTributario) {
        this.regimeTributario = regimeTributario;
    }

    public String getVersao() {
        return versao;
    }

    public Emitente versao(String versao) {
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
        Emitente emitente = (Emitente) o;
        if (emitente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emitente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Emitente{" +
            "id=" + getId() +
            ", idEmitente=" + getIdEmitente() +
            ", xNome='" + getxNome() + "'" +
            ", xFant='" + getxFant() + "'" +
            ", xLgr='" + getxLgr() + "'" +
            ", nRo='" + getnRo() + "'" +
            ", xCpl='" + getxCpl() + "'" +
            ", xBairro='" + getxBairro() + "'" +
            ", cMun='" + getcMun() + "'" +
            ", xMun='" + getxMun() + "'" +
            ", uF='" + getuF() + "'" +
            ", cEP='" + getcEP() + "'" +
            ", cPais='" + getcPais() + "'" +
            ", xPais='" + getxPais() + "'" +
            ", fone='" + getFone() + "'" +
            ", iE='" + getiE() + "'" +
            ", iEST='" + getiEST() + "'" +
            ", iM='" + getiM() + "'" +
            ", cNAE='" + getcNAE() + "'" +
            ", logotipo='" + getLogotipo() + "'" +
            ", logotipoContentType='" + getLogotipoContentType() + "'" +
            ", tpDocumentoEnum=" + getTpDocumentoEnum() +
            ", nrDocumento='" + getNrDocumento() + "'" +
            ", regimeTributario='" + getRegimeTributario() + "'" +
            ", versao='" + getVersao() + "'" +
            "}";
    }
}
