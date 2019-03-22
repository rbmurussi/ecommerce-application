import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './cancelamento.reducer';
import { ICancelamento } from 'app/shared/model/cancelamento.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICancelamentoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICancelamentoUpdateState {
  isNew: boolean;
}

export class CancelamentoUpdate extends React.Component<ICancelamentoUpdateProps, ICancelamentoUpdateState> {
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
    values.dataProtocolo = convertDateTimeToServer(values.dataProtocolo);

    if (errors.length === 0) {
      const { cancelamentoEntity } = this.props;
      const entity = {
        ...cancelamentoEntity,
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
    this.props.history.push('/entity/cancelamento');
  };

  render() {
    const { cancelamentoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { protocolo, protocoloContentType } = cancelamentoEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.cancelamento.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.cancelamento.home.createOrEditLabel">Create or edit a Cancelamento</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : cancelamentoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="cancelamento-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idNotalFiscalLabel" for="idNotalFiscal">
                    <Translate contentKey="ecommerceApplicationApp.cancelamento.idNotalFiscal">Id Notal Fiscal</Translate>
                  </Label>
                  <AvField
                    id="cancelamento-idNotalFiscal"
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
                  <Label id="numeroProtocoloLabel" for="numeroProtocolo">
                    <Translate contentKey="ecommerceApplicationApp.cancelamento.numeroProtocolo">Numero Protocolo</Translate>
                  </Label>
                  <AvField
                    id="cancelamento-numeroProtocolo"
                    type="text"
                    name="numeroProtocolo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 15, errorMessage: translate('entity.validation.maxlength', { max: 15 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="protocoloLabel" for="protocolo">
                      <Translate contentKey="ecommerceApplicationApp.cancelamento.protocolo">Protocolo</Translate>
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
                    <AvInput
                      type="hidden"
                      name="protocolo"
                      value={protocolo}
                      validate={{
                        required: { value: true, errorMessage: translate('entity.validation.required') }
                      }}
                    />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="dataProtocoloLabel" for="dataProtocolo">
                    <Translate contentKey="ecommerceApplicationApp.cancelamento.dataProtocolo">Data Protocolo</Translate>
                  </Label>
                  <AvInput
                    id="cancelamento-dataProtocolo"
                    type="datetime-local"
                    className="form-control"
                    name="dataProtocolo"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.cancelamentoEntity.dataProtocolo)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="codigoErroLabel" for="codigoErro">
                    <Translate contentKey="ecommerceApplicationApp.cancelamento.codigoErro">Codigo Erro</Translate>
                  </Label>
                  <AvField
                    id="cancelamento-codigoErro"
                    type="text"
                    name="codigoErro"
                    validate={{
                      maxLength: { value: 3, errorMessage: translate('entity.validation.maxlength', { max: 3 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="mensagemErroLabel" for="mensagemErro">
                    <Translate contentKey="ecommerceApplicationApp.cancelamento.mensagemErro">Mensagem Erro</Translate>
                  </Label>
                  <AvField
                    id="cancelamento-mensagemErro"
                    type="text"
                    name="mensagemErro"
                    validate={{
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="justificativaLabel" for="justificativa">
                    <Translate contentKey="ecommerceApplicationApp.cancelamento.justificativa">Justificativa</Translate>
                  </Label>
                  <AvField
                    id="cancelamento-justificativa"
                    type="text"
                    name="justificativa"
                    validate={{
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/cancelamento" replace color="info">
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
  cancelamentoEntity: storeState.cancelamento.entity,
  loading: storeState.cancelamento.loading,
  updating: storeState.cancelamento.updating,
  updateSuccess: storeState.cancelamento.updateSuccess
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
)(CancelamentoUpdate);
