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
 * A NotaFiscal.
 */
@Document(collection = "nota_fiscal")
public class NotaFiscal implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("id_notal_fiscal")
    private Long idNotalFiscal;

    @NotNull
    @Size(max = 9)
    @Field("numero")
    private String numero;

    @NotNull
    @Size(max = 3)
    @Field("serie")
    private String serie;

    @NotNull
    @Size(max = 2)
    @Field("modelo")
    private String modelo;

    @NotNull
    @Size(max = 100)
    @Field("situacao")
    private String situacao;

    @NotNull
    @Size(max = 2)
    @Field("mes")
    private String mes;

    @NotNull
    @Size(max = 2)
    @Field("ano")
    private String ano;

    @Size(max = 100)
    @Field("tipo_emissao")
    private String tipoEmissao;

    @Field("data_emissao")
    private ZonedDateTime dataEmissao;

    @Field("data_autorizacao")
    private ZonedDateTime dataAutorizacao;

    @Size(max = 9)
    @Field("codigo_numerico_chave_acesso")
    private String codigoNumericoChaveAcesso;

    @Size(max = 1)
    @Field("digito_chave_acesso")
    private String digitoChaveAcesso;

    @NotNull
    @Size(max = 1)
    @Field("autorizacao_exportada_xml")
    private String autorizacaoExportadaXML;

    @Size(max = 20)
    @Field("documento_dest")
    private String documentoDest;

    @Size(max = 2)
    @Field("u_f_dest")
    private String uFDest;

    @Size(max = 15)
    @Field("numero_recibo")
    private String numeroRecibo;

    @NotNull
    @Size(max = 1)
    @Field("danfe_impresso")
    private String danfeImpresso;

    @Field("doc_xml")
    private byte[] docXML;

    @Field("doc_xml_content_type")
    private String docXMLContentType;

    @Field("data_sistema")
    private ZonedDateTime dataSistema;

    @Field("protocolo")
    private byte[] protocolo;

    @Field("protocolo_content_type")
    private String protocoloContentType;

    @Size(max = 15)
    @Field("numero_protocolo")
    private String numeroProtocolo;

    @Field("data_protocolo")
    private ZonedDateTime dataProtocolo;

    @NotNull
    @Size(max = 2)
    @Field("codigo_uf_emitente")
    private String codigoUFEmitente;

    @NotNull
    @Field("id_emitente")
    private Integer idEmitente;

    @Field("id_lote")
    private Long idLote;

    @Size(max = 3)
    @Field("codigo_erro")
    private String codigoErro;

    @Size(max = 255)
    @Field("mensagem_erro")
    private String mensagemErro;

    @NotNull
    @Size(max = 10)
    @Field("versao")
    private String versao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdNotalFiscal() {
        return idNotalFiscal;
    }

    public NotaFiscal idNotalFiscal(Long idNotalFiscal) {
        this.idNotalFiscal = idNotalFiscal;
        return this;
    }

    public void setIdNotalFiscal(Long idNotalFiscal) {
        this.idNotalFiscal = idNotalFiscal;
    }

    public String getNumero() {
        return numero;
    }

    public NotaFiscal numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public NotaFiscal serie(String serie) {
        this.serie = serie;
        return this;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getModelo() {
        return modelo;
    }

    public NotaFiscal modelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSituacao() {
        return situacao;
    }

    public NotaFiscal situacao(String situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getMes() {
        return mes;
    }

    public NotaFiscal mes(String mes) {
        this.mes = mes;
        return this;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public NotaFiscal ano(String ano) {
        this.ano = ano;
        return this;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTipoEmissao() {
        return tipoEmissao;
    }

    public NotaFiscal tipoEmissao(String tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
        return this;
    }

    public void setTipoEmissao(String tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
    }

    public ZonedDateTime getDataEmissao() {
        return dataEmissao;
    }

    public NotaFiscal dataEmissao(ZonedDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
        return this;
    }

    public void setDataEmissao(ZonedDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public ZonedDateTime getDataAutorizacao() {
        return dataAutorizacao;
    }

    public NotaFiscal dataAutorizacao(ZonedDateTime dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
        return this;
    }

    public void setDataAutorizacao(ZonedDateTime dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public String getCodigoNumericoChaveAcesso() {
        return codigoNumericoChaveAcesso;
    }

    public NotaFiscal codigoNumericoChaveAcesso(String codigoNumericoChaveAcesso) {
        this.codigoNumericoChaveAcesso = codigoNumericoChaveAcesso;
        return this;
    }

    public void setCodigoNumericoChaveAcesso(String codigoNumericoChaveAcesso) {
        this.codigoNumericoChaveAcesso = codigoNumericoChaveAcesso;
    }

    public String getDigitoChaveAcesso() {
        return digitoChaveAcesso;
    }

    public NotaFiscal digitoChaveAcesso(String digitoChaveAcesso) {
        this.digitoChaveAcesso = digitoChaveAcesso;
        return this;
    }

    public void setDigitoChaveAcesso(String digitoChaveAcesso) {
        this.digitoChaveAcesso = digitoChaveAcesso;
    }

    public String getAutorizacaoExportadaXML() {
        return autorizacaoExportadaXML;
    }

    public NotaFiscal autorizacaoExportadaXML(String autorizacaoExportadaXML) {
        this.autorizacaoExportadaXML = autorizacaoExportadaXML;
        return this;
    }

    public void setAutorizacaoExportadaXML(String autorizacaoExportadaXML) {
        this.autorizacaoExportadaXML = autorizacaoExportadaXML;
    }

    public String getDocumentoDest() {
        return documentoDest;
    }

    public NotaFiscal documentoDest(String documentoDest) {
        this.documentoDest = documentoDest;
        return this;
    }

    public void setDocumentoDest(String documentoDest) {
        this.documentoDest = documentoDest;
    }

    public String getuFDest() {
        return uFDest;
    }

    public NotaFiscal uFDest(String uFDest) {
        this.uFDest = uFDest;
        return this;
    }

    public void setuFDest(String uFDest) {
        this.uFDest = uFDest;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public NotaFiscal numeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
        return this;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getDanfeImpresso() {
        return danfeImpresso;
    }

    public NotaFiscal danfeImpresso(String danfeImpresso) {
        this.danfeImpresso = danfeImpresso;
        return this;
    }

    public void setDanfeImpresso(String danfeImpresso) {
        this.danfeImpresso = danfeImpresso;
    }

    public byte[] getDocXML() {
        return docXML;
    }

    public NotaFiscal docXML(byte[] docXML) {
        this.docXML = docXML;
        return this;
    }

    public void setDocXML(byte[] docXML) {
        this.docXML = docXML;
    }

    public String getDocXMLContentType() {
        return docXMLContentType;
    }

    public NotaFiscal docXMLContentType(String docXMLContentType) {
        this.docXMLContentType = docXMLContentType;
        return this;
    }

    public void setDocXMLContentType(String docXMLContentType) {
        this.docXMLContentType = docXMLContentType;
    }

    public ZonedDateTime getDataSistema() {
        return dataSistema;
    }

    public NotaFiscal dataSistema(ZonedDateTime dataSistema) {
        this.dataSistema = dataSistema;
        return this;
    }

    public void setDataSistema(ZonedDateTime dataSistema) {
        this.dataSistema = dataSistema;
    }

    public byte[] getProtocolo() {
        return protocolo;
    }

    public NotaFiscal protocolo(byte[] protocolo) {
        this.protocolo = protocolo;
        return this;
    }

    public void setProtocolo(byte[] protocolo) {
        this.protocolo = protocolo;
    }

    public String getProtocoloContentType() {
        return protocoloContentType;
    }

    public NotaFiscal protocoloContentType(String protocoloContentType) {
        this.protocoloContentType = protocoloContentType;
        return this;
    }

    public void setProtocoloContentType(String protocoloContentType) {
        this.protocoloContentType = protocoloContentType;
    }

    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public NotaFiscal numeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
        return this;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public ZonedDateTime getDataProtocolo() {
        return dataProtocolo;
    }

    public NotaFiscal dataProtocolo(ZonedDateTime dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
        return this;
    }

    public void setDataProtocolo(ZonedDateTime dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }

    public String getCodigoUFEmitente() {
        return codigoUFEmitente;
    }

    public NotaFiscal codigoUFEmitente(String codigoUFEmitente) {
        this.codigoUFEmitente = codigoUFEmitente;
        return this;
    }

    public void setCodigoUFEmitente(String codigoUFEmitente) {
        this.codigoUFEmitente = codigoUFEmitente;
    }

    public Integer getIdEmitente() {
        return idEmitente;
    }

    public NotaFiscal idEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
        return this;
    }

    public void setIdEmitente(Integer idEmitente) {
        this.idEmitente = idEmitente;
    }

    public Long getIdLote() {
        return idLote;
    }

    public NotaFiscal idLote(Long idLote) {
        this.idLote = idLote;
        return this;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

    public NotaFiscal codigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
        return this;
    }

    public void setCodigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public NotaFiscal mensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
        return this;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getVersao() {
        return versao;
    }

    public NotaFiscal versao(String versao) {
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
        NotaFiscal notaFiscal = (NotaFiscal) o;
        if (notaFiscal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notaFiscal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotaFiscal{" +
            "id=" + getId() +
            ", idNotalFiscal=" + getIdNotalFiscal() +
            ", numero='" + getNumero() + "'" +
            ", serie='" + getSerie() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", mes='" + getMes() + "'" +
            ", ano='" + getAno() + "'" +
            ", tipoEmissao='" + getTipoEmissao() + "'" +
            ", dataEmissao='" + getDataEmissao() + "'" +
            ", dataAutorizacao='" + getDataAutorizacao() + "'" +
            ", codigoNumericoChaveAcesso='" + getCodigoNumericoChaveAcesso() + "'" +
            ", digitoChaveAcesso='" + getDigitoChaveAcesso() + "'" +
            ", autorizacaoExportadaXML='" + getAutorizacaoExportadaXML() + "'" +
            ", documentoDest='" + getDocumentoDest() + "'" +
            ", uFDest='" + getuFDest() + "'" +
            ", numeroRecibo='" + getNumeroRecibo() + "'" +
            ", danfeImpresso='" + getDanfeImpresso() + "'" +
            ", docXML='" + getDocXML() + "'" +
            ", docXMLContentType='" + getDocXMLContentType() + "'" +
            ", dataSistema='" + getDataSistema() + "'" +
            ", protocolo='" + getProtocolo() + "'" +
            ", protocoloContentType='" + getProtocoloContentType() + "'" +
            ", numeroProtocolo='" + getNumeroProtocolo() + "'" +
            ", dataProtocolo='" + getDataProtocolo() + "'" +
            ", codigoUFEmitente='" + getCodigoUFEmitente() + "'" +
            ", idEmitente=" + getIdEmitente() +
            ", idLote=" + getIdLote() +
            ", codigoErro='" + getCodigoErro() + "'" +
            ", mensagemErro='" + getMensagemErro() + "'" +
            ", versao='" + getVersao() + "'" +
            "}";
    }
}
