package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Controleversao.
 */
@Document(collection = "controleversao")
public class Controleversao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_controleversao")
    private Integer idControleversao;

    @NotNull
    @Field("numversao_enum")
    private Integer numversaoEnum;

    @NotNull
    @Field("dataversao")
    private LocalDate dataversao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdControleversao() {
        return idControleversao;
    }

    public Controleversao idControleversao(Integer idControleversao) {
        this.idControleversao = idControleversao;
        return this;
    }

    public void setIdControleversao(Integer idControleversao) {
        this.idControleversao = idControleversao;
    }

    public Integer getNumversaoEnum() {
        return numversaoEnum;
    }

    public Controleversao numversaoEnum(Integer numversaoEnum) {
        this.numversaoEnum = numversaoEnum;
        return this;
    }

    public void setNumversaoEnum(Integer numversaoEnum) {
        this.numversaoEnum = numversaoEnum;
    }

    public LocalDate getDataversao() {
        return dataversao;
    }

    public Controleversao dataversao(LocalDate dataversao) {
        this.dataversao = dataversao;
        return this;
    }

    public void setDataversao(LocalDate dataversao) {
        this.dataversao = dataversao;
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
        Controleversao controleversao = (Controleversao) o;
        if (controleversao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), controleversao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Controleversao{" +
            "id=" + getId() +
            ", idControleversao=" + getIdControleversao() +
            ", numversaoEnum=" + getNumversaoEnum() +
            ", dataversao='" + getDataversao() + "'" +
            "}";
    }
}
