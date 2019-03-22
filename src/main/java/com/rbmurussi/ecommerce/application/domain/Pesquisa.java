package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Pesquisa.
 */
@Document(collection = "pesquisa")
public class Pesquisa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_pesquisa")
    private Integer idPesquisa;

    @NotNull
    @Field("campo_enum")
    private Integer campoEnum;

    @Size(max = 120)
    @Field("valor")
    private String valor;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    @NotNull
    @Field("tela_enum")
    private Integer telaEnum;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdPesquisa() {
        return idPesquisa;
    }

    public Pesquisa idPesquisa(Integer idPesquisa) {
        this.idPesquisa = idPesquisa;
        return this;
    }

    public void setIdPesquisa(Integer idPesquisa) {
        this.idPesquisa = idPesquisa;
    }

    public Integer getCampoEnum() {
        return campoEnum;
    }

    public Pesquisa campoEnum(Integer campoEnum) {
        this.campoEnum = campoEnum;
        return this;
    }

    public void setCampoEnum(Integer campoEnum) {
        this.campoEnum = campoEnum;
    }

    public String getValor() {
        return valor;
    }

    public Pesquisa valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public Pesquisa idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
    }

    public Integer getTelaEnum() {
        return telaEnum;
    }

    public Pesquisa telaEnum(Integer telaEnum) {
        this.telaEnum = telaEnum;
        return this;
    }

    public void setTelaEnum(Integer telaEnum) {
        this.telaEnum = telaEnum;
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
        Pesquisa pesquisa = (Pesquisa) o;
        if (pesquisa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pesquisa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pesquisa{" +
            "id=" + getId() +
            ", idPesquisa=" + getIdPesquisa() +
            ", campoEnum=" + getCampoEnum() +
            ", valor='" + getValor() + "'" +
            ", idEmitente=" + getIdEmitente() +
            ", telaEnum=" + getTelaEnum() +
            "}";
    }
}
