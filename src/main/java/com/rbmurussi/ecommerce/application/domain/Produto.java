package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Produto.
 */
@Document(collection = "produto")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Size(max = 60)
    @Field("c_prod")
    private String cProd;

    @NotNull
    @Field("id_produto")
    private Integer idProduto;

    @NotNull
    @Size(max = 120)
    @Field("x_prod")
    private String xProd;

    @Size(max = 14)
    @Field("c_ean")
    private String cEAN;

    @Size(max = 8)
    @Field("n_cm")
    private String nCM;

    @Size(max = 3)
    @Field("ex_tipi")
    private String exTipi;

    @Size(max = 2)
    @Field("genero")
    private String genero;

    @Size(max = 6)
    @Field("u_com")
    private String uCom;

    @Size(max = 14)
    @Field("c_ean_trib")
    private String cEANTrib;

    @Size(max = 6)
    @Field("u_trib")
    private String uTrib;

    @Size(max = 17)
    @Field("v_un_com")
    private String vUNCom;

    @Size(max = 17)
    @Field("v_un_trib")
    private String vUNTrib;

    @Size(max = 20)
    @Field("q_trib")
    private String qTrib;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    @NotNull
    @Size(max = 60)
    @Field("versao")
    private String versao;

    @Size(max = 7)
    @Field("cest")
    private String cest;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcProd() {
        return cProd;
    }

    public Produto cProd(String cProd) {
        this.cProd = cProd;
        return this;
    }

    public void setcProd(String cProd) {
        this.cProd = cProd;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public Produto idProduto(Integer idProduto) {
        this.idProduto = idProduto;
        return this;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getxProd() {
        return xProd;
    }

    public Produto xProd(String xProd) {
        this.xProd = xProd;
        return this;
    }

    public void setxProd(String xProd) {
        this.xProd = xProd;
    }

    public String getcEAN() {
        return cEAN;
    }

    public Produto cEAN(String cEAN) {
        this.cEAN = cEAN;
        return this;
    }

    public void setcEAN(String cEAN) {
        this.cEAN = cEAN;
    }

    public String getnCM() {
        return nCM;
    }

    public Produto nCM(String nCM) {
        this.nCM = nCM;
        return this;
    }

    public void setnCM(String nCM) {
        this.nCM = nCM;
    }

    public String getExTipi() {
        return exTipi;
    }

    public Produto exTipi(String exTipi) {
        this.exTipi = exTipi;
        return this;
    }

    public void setExTipi(String exTipi) {
        this.exTipi = exTipi;
    }

    public String getGenero() {
        return genero;
    }

    public Produto genero(String genero) {
        this.genero = genero;
        return this;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getuCom() {
        return uCom;
    }

    public Produto uCom(String uCom) {
        this.uCom = uCom;
        return this;
    }

    public void setuCom(String uCom) {
        this.uCom = uCom;
    }

    public String getcEANTrib() {
        return cEANTrib;
    }

    public Produto cEANTrib(String cEANTrib) {
        this.cEANTrib = cEANTrib;
        return this;
    }

    public void setcEANTrib(String cEANTrib) {
        this.cEANTrib = cEANTrib;
    }

    public String getuTrib() {
        return uTrib;
    }

    public Produto uTrib(String uTrib) {
        this.uTrib = uTrib;
        return this;
    }

    public void setuTrib(String uTrib) {
        this.uTrib = uTrib;
    }

    public String getvUNCom() {
        return vUNCom;
    }

    public Produto vUNCom(String vUNCom) {
        this.vUNCom = vUNCom;
        return this;
    }

    public void setvUNCom(String vUNCom) {
        this.vUNCom = vUNCom;
    }

    public String getvUNTrib() {
        return vUNTrib;
    }

    public Produto vUNTrib(String vUNTrib) {
        this.vUNTrib = vUNTrib;
        return this;
    }

    public void setvUNTrib(String vUNTrib) {
        this.vUNTrib = vUNTrib;
    }

    public String getqTrib() {
        return qTrib;
    }

    public Produto qTrib(String qTrib) {
        this.qTrib = qTrib;
        return this;
    }

    public void setqTrib(String qTrib) {
        this.qTrib = qTrib;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public Produto idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
    }

    public String getVersao() {
        return versao;
    }

    public Produto versao(String versao) {
        this.versao = versao;
        return this;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getCest() {
        return cest;
    }

    public Produto cest(String cest) {
        this.cest = cest;
        return this;
    }

    public void setCest(String cest) {
        this.cest = cest;
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
        Produto produto = (Produto) o;
        if (produto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Produto{" +
            "id=" + getId() +
            ", cProd='" + getcProd() + "'" +
            ", idProduto=" + getIdProduto() +
            ", xProd='" + getxProd() + "'" +
            ", cEAN='" + getcEAN() + "'" +
            ", nCM='" + getnCM() + "'" +
            ", exTipi='" + getExTipi() + "'" +
            ", genero='" + getGenero() + "'" +
            ", uCom='" + getuCom() + "'" +
            ", cEANTrib='" + getcEANTrib() + "'" +
            ", uTrib='" + getuTrib() + "'" +
            ", vUNCom='" + getvUNCom() + "'" +
            ", vUNTrib='" + getvUNTrib() + "'" +
            ", qTrib='" + getqTrib() + "'" +
            ", idEmitente=" + getIdEmitente() +
            ", versao='" + getVersao() + "'" +
            ", cest='" + getCest() + "'" +
            "}";
    }
}
