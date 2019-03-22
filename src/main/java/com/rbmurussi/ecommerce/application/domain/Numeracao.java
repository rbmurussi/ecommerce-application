package com.rbmurussi.ecommerce.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Numeracao.
 */
@Document(collection = "numeracao")
public class Numeracao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_numeracao")
    private Integer idNumeracao;

    @NotNull
    @Size(max = 3)
    @Field("serie")
    private String serie;

    @NotNull
    @Size(max = 9)
    @Field("numero")
    private String numero;

    @NotNull
    @Size(max = 2)
    @Field("ano")
    private String ano;

    @Field("data_sistema")
    private ZonedDateTime dataSistema;

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

    public Integer getIdNumeracao() {
        return idNumeracao;
    }

    public Numeracao idNumeracao(Integer idNumeracao) {
        this.idNumeracao = idNumeracao;
        return this;
    }

    public void setIdNumeracao(Integer idNumeracao) {
        this.idNumeracao = idNumeracao;
    }

    public String getSerie() {
        return serie;
    }

    public Numeracao serie(String serie) {
        this.serie = serie;
        return this;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public Numeracao numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAno() {
        return ano;
    }

    public Numeracao ano(String ano) {
        this.ano = ano;
        return this;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public ZonedDateTime getDataSistema() {
        return dataSistema;
    }

    public Numeracao dataSistema(ZonedDateTime dataSistema) {
        this.dataSistema = dataSistema;
        return this;
    }

    public void setDataSistema(ZonedDateTime dataSistema) {
        this.dataSistema = dataSistema;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public Numeracao idEmitente(Integer idEmitente) {
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
        Numeracao numeracao = (Numeracao) o;
        if (numeracao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), numeracao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Numeracao{" +
            "id=" + getId() +
            ", idNumeracao=" + getIdNumeracao() +
            ", serie='" + getSerie() + "'" +
            ", numero='" + getNumero() + "'" +
            ", ano='" + getAno() + "'" +
            ", dataSistema='" + getDataSistema() + "'" +
            ", idEmitente=" + getIdEmitente() +
            "}";
    }
}
