package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Ipi.
 */
@Document(collection = "ipi")
public class Ipi implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_ipi")
    private Integer idIpi;

    @Size(max = 5)
    @Field("cl_enq")
    private String clEnq;

    @Size(max = 14)
    @Field("c_npj_prod")
    private String cNPJProd;

    @Size(max = 3)
    @Field("c_enq")
    private String cEnq;

    @NotNull
    @Field("id_produto")
    private Integer idProduto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdIpi() {
        return idIpi;
    }

    public Ipi idIpi(Integer idIpi) {
        this.idIpi = idIpi;
        return this;
    }

    public void setIdIpi(Integer idIpi) {
        this.idIpi = idIpi;
    }

    public String getClEnq() {
        return clEnq;
    }

    public Ipi clEnq(String clEnq) {
        this.clEnq = clEnq;
        return this;
    }

    public void setClEnq(String clEnq) {
        this.clEnq = clEnq;
    }

    public String getcNPJProd() {
        return cNPJProd;
    }

    public Ipi cNPJProd(String cNPJProd) {
        this.cNPJProd = cNPJProd;
        return this;
    }

    public void setcNPJProd(String cNPJProd) {
        this.cNPJProd = cNPJProd;
    }

    public String getcEnq() {
        return cEnq;
    }

    public Ipi cEnq(String cEnq) {
        this.cEnq = cEnq;
        return this;
    }

    public void setcEnq(String cEnq) {
        this.cEnq = cEnq;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public Ipi idProduto(Integer idProduto) {
        this.idProduto = idProduto;
        return this;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
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
        Ipi ipi = (Ipi) o;
        if (ipi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ipi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ipi{" +
            "id=" + getId() +
            ", idIpi=" + getIdIpi() +
            ", clEnq='" + getClEnq() + "'" +
            ", cNPJProd='" + getcNPJProd() + "'" +
            ", cEnq='" + getcEnq() + "'" +
            ", idProduto=" + getIdProduto() +
            "}";
    }
}
