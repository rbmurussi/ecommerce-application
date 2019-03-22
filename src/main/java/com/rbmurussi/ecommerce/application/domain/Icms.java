package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Icms.
 */
@Document(collection = "icms")
public class Icms implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_icms")
    private Integer idIcms;

    @Size(max = 2)
    @Field("orig")
    private String orig;

    @NotNull
    @Field("id_produto")
    private Integer idProduto;

    @NotNull
    @Size(max = 7)
    @Field("cst")
    private String cst;

    @Size(max = 2)
    @Field("mod_bc")
    private String modBc;

    @Size(max = 6)
    @Field("p_redbc")
    private String pREDBC;

    @Size(max = 6)
    @Field("p_icms")
    private String pICMS;

    @Size(max = 2)
    @Field("mod_bcst")
    private String modBCST;

    @Size(max = 6)
    @Field("p_mvast")
    private String pMVAST;

    @Size(max = 6)
    @Field("p_red_bcst")
    private String pRedBCST;

    @Size(max = 6)
    @Field("p_icmsst")
    private String pICMSST;

    @Size(max = 2)
    @Field("mot_des_icms")
    private String motDesICMS;

    @Size(max = 6)
    @Field("p_bcop")
    private String pBCOP;

    @Size(max = 2)
    @Field("u_fst")
    private String uFST;

    @Size(max = 6)
    @Field("p_cred_sn")
    private String pCredSN;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdIcms() {
        return idIcms;
    }

    public Icms idIcms(Integer idIcms) {
        this.idIcms = idIcms;
        return this;
    }

    public void setIdIcms(Integer idIcms) {
        this.idIcms = idIcms;
    }

    public String getOrig() {
        return orig;
    }

    public Icms orig(String orig) {
        this.orig = orig;
        return this;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public Icms idProduto(Integer idProduto) {
        this.idProduto = idProduto;
        return this;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getCst() {
        return cst;
    }

    public Icms cst(String cst) {
        this.cst = cst;
        return this;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getModBc() {
        return modBc;
    }

    public Icms modBc(String modBc) {
        this.modBc = modBc;
        return this;
    }

    public void setModBc(String modBc) {
        this.modBc = modBc;
    }

    public String getpREDBC() {
        return pREDBC;
    }

    public Icms pREDBC(String pREDBC) {
        this.pREDBC = pREDBC;
        return this;
    }

    public void setpREDBC(String pREDBC) {
        this.pREDBC = pREDBC;
    }

    public String getpICMS() {
        return pICMS;
    }

    public Icms pICMS(String pICMS) {
        this.pICMS = pICMS;
        return this;
    }

    public void setpICMS(String pICMS) {
        this.pICMS = pICMS;
    }

    public String getModBCST() {
        return modBCST;
    }

    public Icms modBCST(String modBCST) {
        this.modBCST = modBCST;
        return this;
    }

    public void setModBCST(String modBCST) {
        this.modBCST = modBCST;
    }

    public String getpMVAST() {
        return pMVAST;
    }

    public Icms pMVAST(String pMVAST) {
        this.pMVAST = pMVAST;
        return this;
    }

    public void setpMVAST(String pMVAST) {
        this.pMVAST = pMVAST;
    }

    public String getpRedBCST() {
        return pRedBCST;
    }

    public Icms pRedBCST(String pRedBCST) {
        this.pRedBCST = pRedBCST;
        return this;
    }

    public void setpRedBCST(String pRedBCST) {
        this.pRedBCST = pRedBCST;
    }

    public String getpICMSST() {
        return pICMSST;
    }

    public Icms pICMSST(String pICMSST) {
        this.pICMSST = pICMSST;
        return this;
    }

    public void setpICMSST(String pICMSST) {
        this.pICMSST = pICMSST;
    }

    public String getMotDesICMS() {
        return motDesICMS;
    }

    public Icms motDesICMS(String motDesICMS) {
        this.motDesICMS = motDesICMS;
        return this;
    }

    public void setMotDesICMS(String motDesICMS) {
        this.motDesICMS = motDesICMS;
    }

    public String getpBCOP() {
        return pBCOP;
    }

    public Icms pBCOP(String pBCOP) {
        this.pBCOP = pBCOP;
        return this;
    }

    public void setpBCOP(String pBCOP) {
        this.pBCOP = pBCOP;
    }

    public String getuFST() {
        return uFST;
    }

    public Icms uFST(String uFST) {
        this.uFST = uFST;
        return this;
    }

    public void setuFST(String uFST) {
        this.uFST = uFST;
    }

    public String getpCredSN() {
        return pCredSN;
    }

    public Icms pCredSN(String pCredSN) {
        this.pCredSN = pCredSN;
        return this;
    }

    public void setpCredSN(String pCredSN) {
        this.pCredSN = pCredSN;
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
        Icms icms = (Icms) o;
        if (icms.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), icms.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Icms{" +
            "id=" + getId() +
            ", idIcms=" + getIdIcms() +
            ", orig='" + getOrig() + "'" +
            ", idProduto=" + getIdProduto() +
            ", cst='" + getCst() + "'" +
            ", modBc='" + getModBc() + "'" +
            ", pREDBC='" + getpREDBC() + "'" +
            ", pICMS='" + getpICMS() + "'" +
            ", modBCST='" + getModBCST() + "'" +
            ", pMVAST='" + getpMVAST() + "'" +
            ", pRedBCST='" + getpRedBCST() + "'" +
            ", pICMSST='" + getpICMSST() + "'" +
            ", motDesICMS='" + getMotDesICMS() + "'" +
            ", pBCOP='" + getpBCOP() + "'" +
            ", uFST='" + getuFST() + "'" +
            ", pCredSN='" + getpCredSN() + "'" +
            "}";
    }
}
