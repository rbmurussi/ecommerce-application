package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Generator.
 */
@Document(collection = "generator")
public class Generator implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Size(max = 50)
    @Field("gen_key")
    private String genKey;

    @NotNull
    @Field("gen_value")
    private Integer genValue;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenKey() {
        return genKey;
    }

    public Generator genKey(String genKey) {
        this.genKey = genKey;
        return this;
    }

    public void setGenKey(String genKey) {
        this.genKey = genKey;
    }

    public Integer getGenValue() {
        return genValue;
    }

    public Generator genValue(Integer genValue) {
        this.genValue = genValue;
        return this;
    }

    public void setGenValue(Integer genValue) {
        this.genValue = genValue;
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
        Generator generator = (Generator) o;
        if (generator.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), generator.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Generator{" +
            "id=" + getId() +
            ", genKey='" + getGenKey() + "'" +
            ", genValue=" + getGenValue() +
            "}";
    }
}
