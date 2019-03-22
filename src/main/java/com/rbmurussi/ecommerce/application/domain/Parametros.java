package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Parametros.
 */
@Document(collection = "parametros")
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("parametros_enum")
    private Integer parametrosEnum;

    @Size(max = 255)
    @Field("valor")
    private String valor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getParametrosEnum() {
        return parametrosEnum;
    }

    public Parametros parametrosEnum(Integer parametrosEnum) {
        this.parametrosEnum = parametrosEnum;
        return this;
    }

    public void setParametrosEnum(Integer parametrosEnum) {
        this.parametrosEnum = parametrosEnum;
    }

    public String getValor() {
        return valor;
    }

    public Parametros valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
        Parametros parametros = (Parametros) o;
        if (parametros.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), parametros.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Parametros{" +
            "id=" + getId() +
            ", parametrosEnum=" + getParametrosEnum() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
