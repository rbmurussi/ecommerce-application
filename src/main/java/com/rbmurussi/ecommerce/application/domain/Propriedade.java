package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Propriedade.
 */
@Document(collection = "propriedade")
public class Propriedade implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_propriedade")
    private Integer idPropriedade;

    @NotNull
    @Field("propriedade_enum")
    private Integer propriedadeEnum;

    @Size(max = 255)
    @Field("valor")
    private String valor;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdPropriedade() {
        return idPropriedade;
    }

    public Propriedade idPropriedade(Integer idPropriedade) {
        this.idPropriedade = idPropriedade;
        return this;
    }

    public void setIdPropriedade(Integer idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public Integer getPropriedadeEnum() {
        return propriedadeEnum;
    }

    public Propriedade propriedadeEnum(Integer propriedadeEnum) {
        this.propriedadeEnum = propriedadeEnum;
        return this;
    }

    public void setPropriedadeEnum(Integer propriedadeEnum) {
        this.propriedadeEnum = propriedadeEnum;
    }

    public String getValor() {
        return valor;
    }

    public Propriedade valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public Propriedade idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
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
        Propriedade propriedade = (Propriedade) o;
        if (propriedade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), propriedade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Propriedade{" +
            "id=" + getId() +
            ", idPropriedade=" + getIdPropriedade() +
            ", propriedadeEnum=" + getPropriedadeEnum() +
            ", valor='" + getValor() + "'" +
            ", idEmitente=" + getIdEmitente() +
            "}";
    }
}
