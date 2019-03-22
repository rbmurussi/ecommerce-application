package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Cliente.
 */
@Document(collection = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("tp_documento_enum")
    private Integer tpDocumentoEnum;

    @NotNull
    @Field("id_cliente")
    private Integer idCliente;

    @Size(max = 14)
    @Field("nr_documento")
    private String nrDocumento;

    @NotNull
    @Size(max = 60)
    @Field("x_nome")
    private String xNome;

    @Size(max = 60)
    @Field("x_lgr")
    private String xLgr;

    @Size(max = 60)
    @Field("n_ro")
    private String nRo;

    @Size(max = 60)
    @Field("x_cpl")
    private String xCpl;

    @Size(max = 60)
    @Field("x_bairro")
    private String xBairro;

    @Size(max = 60)
    @Field("x_mun")
    private String xMun;

    @Size(max = 7)
    @Field("c_mun")
    private String cMun;

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

    @Size(max = 60)
    @Field("email")
    private String email;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    @Size(max = 14)
    @Field("i_e")
    private String iE;

    @Size(max = 9)
    @Field("i_esuf")
    private String iESUF;

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

    public Integer getTpDocumentoEnum() {
        return tpDocumentoEnum;
    }

    public Cliente tpDocumentoEnum(Integer tpDocumentoEnum) {
        this.tpDocumentoEnum = tpDocumentoEnum;
        return this;
    }

    public void setTpDocumentoEnum(Integer tpDocumentoEnum) {
        this.tpDocumentoEnum = tpDocumentoEnum;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Cliente idCliente(Integer idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNrDocumento() {
        return nrDocumento;
    }

    public Cliente nrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
        return this;
    }

    public void setNrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
    }

    public String getxNome() {
        return xNome;
    }

    public Cliente xNome(String xNome) {
        this.xNome = xNome;
        return this;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getxLgr() {
        return xLgr;
    }

    public Cliente xLgr(String xLgr) {
        this.xLgr = xLgr;
        return this;
    }

    public void setxLgr(String xLgr) {
        this.xLgr = xLgr;
    }

    public String getnRo() {
        return nRo;
    }

    public Cliente nRo(String nRo) {
        this.nRo = nRo;
        return this;
    }

    public void setnRo(String nRo) {
        this.nRo = nRo;
    }

    public String getxCpl() {
        return xCpl;
    }

    public Cliente xCpl(String xCpl) {
        this.xCpl = xCpl;
        return this;
    }

    public void setxCpl(String xCpl) {
        this.xCpl = xCpl;
    }

    public String getxBairro() {
        return xBairro;
    }

    public Cliente xBairro(String xBairro) {
        this.xBairro = xBairro;
        return this;
    }

    public void setxBairro(String xBairro) {
        this.xBairro = xBairro;
    }

    public String getxMun() {
        return xMun;
    }

    public Cliente xMun(String xMun) {
        this.xMun = xMun;
        return this;
    }

    public void setxMun(String xMun) {
        this.xMun = xMun;
    }

    public String getcMun() {
        return cMun;
    }

    public Cliente cMun(String cMun) {
        this.cMun = cMun;
        return this;
    }

    public void setcMun(String cMun) {
        this.cMun = cMun;
    }

    public String getuF() {
        return uF;
    }

    public Cliente uF(String uF) {
        this.uF = uF;
        return this;
    }

    public void setuF(String uF) {
        this.uF = uF;
    }

    public String getcEP() {
        return cEP;
    }

    public Cliente cEP(String cEP) {
        this.cEP = cEP;
        return this;
    }

    public void setcEP(String cEP) {
        this.cEP = cEP;
    }

    public String getcPais() {
        return cPais;
    }

    public Cliente cPais(String cPais) {
        this.cPais = cPais;
        return this;
    }

    public void setcPais(String cPais) {
        this.cPais = cPais;
    }

    public String getxPais() {
        return xPais;
    }

    public Cliente xPais(String xPais) {
        this.xPais = xPais;
        return this;
    }

    public void setxPais(String xPais) {
        this.xPais = xPais;
    }

    public String getFone() {
        return fone;
    }

    public Cliente fone(String fone) {
        this.fone = fone;
        return this;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public Cliente email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public Cliente idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
    }

    public String getiE() {
        return iE;
    }

    public Cliente iE(String iE) {
        this.iE = iE;
        return this;
    }

    public void setiE(String iE) {
        this.iE = iE;
    }

    public String getiESUF() {
        return iESUF;
    }

    public Cliente iESUF(String iESUF) {
        this.iESUF = iESUF;
        return this;
    }

    public void setiESUF(String iESUF) {
        this.iESUF = iESUF;
    }

    public String getVersao() {
        return versao;
    }

    public Cliente versao(String versao) {
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
        Cliente cliente = (Cliente) o;
        if (cliente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + getId() +
            ", tpDocumentoEnum=" + getTpDocumentoEnum() +
            ", idCliente=" + getIdCliente() +
            ", nrDocumento='" + getNrDocumento() + "'" +
            ", xNome='" + getxNome() + "'" +
            ", xLgr='" + getxLgr() + "'" +
            ", nRo='" + getnRo() + "'" +
            ", xCpl='" + getxCpl() + "'" +
            ", xBairro='" + getxBairro() + "'" +
            ", xMun='" + getxMun() + "'" +
            ", cMun='" + getcMun() + "'" +
            ", uF='" + getuF() + "'" +
            ", cEP='" + getcEP() + "'" +
            ", cPais='" + getcPais() + "'" +
            ", xPais='" + getxPais() + "'" +
            ", fone='" + getFone() + "'" +
            ", email='" + getEmail() + "'" +
            ", idEmitente=" + getIdEmitente() +
            ", iE='" + getiE() + "'" +
            ", iESUF='" + getiESUF() + "'" +
            ", versao='" + getVersao() + "'" +
            "}";
    }
}
