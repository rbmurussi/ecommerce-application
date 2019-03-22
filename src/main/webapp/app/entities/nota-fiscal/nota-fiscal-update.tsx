import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './nota-fiscal.reducer';
import { INotaFiscal } from 'app/shared/model/nota-fiscal.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INotaFiscalUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface INotaFiscalUpdateState {
  isNew: boolean;
}

export class NotaFiscalUpdate extends React.Component<INotaFiscalUpdateProps, INotaFiscalUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.dataEmissao = convertDateTimeToServer(values.dataEmissao);
    values.dataAutorizacao = convertDateTimeToServer(values.dataAutorizacao);
    values.dataSistema = convertDateTimeToServer(values.dataSistema);
    values.dataProtocolo = convertDateTimeToServer(values.dataProtocolo);

    if (errors.length === 0) {
      const { notaFiscalEntity } = this.props;
      const entity = {
        ...notaFiscalEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/nota-fiscal');
  };

  render() {
    const { notaFiscalEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { docXML, docXMLContentType, protocolo, protocoloContentType } = notaFiscalEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.notaFiscal.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.notaFiscal.home.createOrEditLabel">Create or edit a NotaFiscal</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : notaFiscalEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="nota-fiscal-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idNotalFiscalLabel" for="idNotalFiscal">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.idNotalFiscal">Id Notal Fiscal</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-idNotalFiscal"
                    type="string"
                    className="form-control"
                    name="idNotalFiscal"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numeroLabel" for="numero">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.numero">Numero</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-numero"
                    type="text"
                    name="numero"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 9, errorMessage: translate('entity.validation.maxlength', { max: 9 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="serieLabel" for="serie">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.serie">Serie</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-serie"
                    type="text"
                    name="serie"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 3, errorMessage: translate('entity.validation.maxlength', { max: 3 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modeloLabel" for="modelo">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.modelo">Modelo</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-modelo"
                    type="text"
                    name="modelo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="situacaoLabel" for="situacao">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.situacao">Situacao</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-situacao"
                    type="text"
                    name="situacao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="mesLabel" for="mes">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.mes">Mes</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-mes"
                    type="text"
                    name="mes"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="anoLabel" for="ano">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.ano">Ano</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-ano"
                    type="text"
                    name="ano"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tipoEmissaoLabel" for="tipoEmissao">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.tipoEmissao">Tipo Emissao</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-tipoEmissao"
                    type="text"
                    name="tipoEmissao"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dataEmissaoLabel" for="dataEmissao">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataEmissao">Data Emissao</Translate>
                  </Label>
                  <AvInput
                    id="nota-fiscal-dataEmissao"
                    type="datetime-local"
                    className="form-control"
                    name="dataEmissao"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.notaFiscalEntity.dataEmissao)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dataAutorizacaoLabel" for="dataAutorizacao">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataAutorizacao">Data Autorizacao</Translate>
                  </Label>
                  <AvInput
                    id="nota-fiscal-dataAutorizacao"
                    type="datetime-local"
                    className="form-control"
                    name="dataAutorizacao"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.notaFiscalEntity.dataAutorizacao)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="codigoNumericoChaveAcessoLabel" for="codigoNumericoChaveAcesso">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoNumericoChaveAcesso">
                      Codigo Numerico Chave Acesso
                    </Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-codigoNumericoChaveAcesso"
                    type="text"
                    name="codigoNumericoChaveAcesso"
                    validate={{
                      maxLength: { value: 9, errorMessage: translate('entity.validation.maxlength', { max: 9 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="digitoChaveAcessoLabel" for="digitoChaveAcesso">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.digitoChaveAcesso">Digito Chave Acesso</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-digitoChaveAcesso"
                    type="text"
                    name="digitoChaveAcesso"
                    validate={{
                      maxLength: { value: 1, errorMessage: translate('entity.validation.maxlength', { max: 1 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="autorizacaoExportadaXMLLabel" for="autorizacaoExportadaXML">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.autorizacaoExportadaXML">Autorizacao Exportada XML</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-autorizacaoExportadaXML"
                    type="text"
                    name="autorizacaoExportadaXML"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 1, errorMessage: translate('entity.validation.maxlength', { max: 1 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="documentoDestLabel" for="documentoDest">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.documentoDest">Documento Dest</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-documentoDest"
                    type="text"
                    name="documentoDest"
                    validate={{
                      maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="uFDestLabel" for="uFDest">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.uFDest">U F Dest</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-uFDest"
                    type="text"
                    name="uFDest"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numeroReciboLabel" for="numeroRecibo">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.numeroRecibo">Numero Recibo</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-numeroRecibo"
                    type="text"
                    name="numeroRecibo"
                    validate={{
                      maxLength: { value: 15, errorMessage: translate('entity.validation.maxlength', { max: 15 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="danfeImpressoLabel" for="danfeImpresso">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.danfeImpresso">Danfe Impresso</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-danfeImpresso"
                    type="text"
                    name="danfeImpresso"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 1, errorMessage: translate('entity.validation.maxlength', { max: 1 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="docXMLLabel" for="docXML">
                      <Translate contentKey="ecommerceApplicationApp.notaFiscal.docXML">Doc XML</Translate>
                    </Label>
                    <br />
                    {docXML ? (
                      <div>
                        <a onClick={openFile(docXMLContentType, docXML)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {docXMLContentType}, {byteSize(docXML)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('docXML')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_docXML" type="file" onChange={this.onBlobChange(false, 'docXML')} />
                    <AvInput type="hidden" name="docXML" value={docXML} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="dataSistemaLabel" for="dataSistema">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataSistema">Data Sistema</Translate>
                  </Label>
                  <AvInput
                    id="nota-fiscal-dataSistema"
                    type="datetime-local"
                    className="form-control"
                    name="dataSistema"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.notaFiscalEntity.dataSistema)}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="protocoloLabel" for="protocolo">
                      <Translate contentKey="ecommerceApplicationApp.notaFiscal.protocolo">Protocolo</Translate>
                    </Label>
                    <br />
                    {protocolo ? (
                      <div>
                        <a onClick={openFile(protocoloContentType, protocolo)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {protocoloContentType}, {byteSize(protocolo)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('protocolo')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_protocolo" type="file" onChange={this.onBlobChange(false, 'protocolo')} />
                    <AvInput type="hidden" name="protocolo" value={protocolo} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="numeroProtocoloLabel" for="numeroProtocolo">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.numeroProtocolo">Numero Protocolo</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-numeroProtocolo"
                    type="text"
                    name="numeroProtocolo"
                    validate={{
                      maxLength: { value: 15, errorMessage: translate('entity.validation.maxlength', { max: 15 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dataProtocoloLabel" for="dataProtocolo">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataProtocolo">Data Protocolo</Translate>
                  </Label>
                  <AvInput
                    id="nota-fiscal-dataProtocolo"
                    type="datetime-local"
                    className="form-control"
                    name="dataProtocolo"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.notaFiscalEntity.dataProtocolo)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="codigoUFEmitenteLabel" for="codigoUFEmitente">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoUFEmitente">Codigo UF Emitente</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-codigoUFEmitente"
                    type="text"
                    name="codigoUFEmitente"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-idEmitente"
                    type="string"
                    className="form-control"
                    name="idEmitente"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idLoteLabel" for="idLote">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.idLote">Id Lote</Translate>
                  </Label>
                  <AvField id="nota-fiscal-idLote" type="string" className="form-control" name="idLote" />
                </AvGroup>
                <AvGroup>
                  <Label id="codigoErroLabel" for="codigoErro">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoErro">Codigo Erro</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-codigoErro"
                    type="text"
                    name="codigoErro"
                    validate={{
                      maxLength: { value: 3, errorMessage: translate('entity.validation.maxlength', { max: 3 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="mensagemErroLabel" for="mensagemErro">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.mensagemErro">Mensagem Erro</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-mensagemErro"
                    type="text"
                    name="mensagemErro"
                    validate={{
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="versaoLabel" for="versao">
                    <Translate contentKey="ecommerceApplicationApp.notaFiscal.versao">Versao</Translate>
                  </Label>
                  <AvField
                    id="nota-fiscal-versao"
                    type="text"
                    name="versao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/nota-fiscal" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  notaFiscalEntity: storeState.notaFiscal.entity,
  loading: storeState.notaFiscal.loading,
  updating: storeState.notaFiscal.updating,
  updateSuccess: storeState.notaFiscal.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NotaFiscalUpdate);
