import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './inutilizacao.reducer';
import { IInutilizacao } from 'app/shared/model/inutilizacao.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInutilizacaoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInutilizacaoUpdateState {
  isNew: boolean;
}

export class InutilizacaoUpdate extends React.Component<IInutilizacaoUpdateProps, IInutilizacaoUpdateState> {
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
    values.dataInutilizacao = convertDateTimeToServer(values.dataInutilizacao);

    if (errors.length === 0) {
      const { inutilizacaoEntity } = this.props;
      const entity = {
        ...inutilizacaoEntity,
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
    this.props.history.push('/entity/inutilizacao');
  };

  render() {
    const { inutilizacaoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { protocoloXml, protocoloXmlContentType } = inutilizacaoEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.inutilizacao.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.inutilizacao.home.createOrEditLabel">Create or edit a Inutilizacao</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : inutilizacaoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="inutilizacao-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idInutilizacaoLabel" for="idInutilizacao">
                    <Translate contentKey="ecommerceApplicationApp.inutilizacao.idInutilizacao">Id Inutilizacao</Translate>
                  </Label>
                  <AvField
                    id="inutilizacao-idInutilizacao"
                    type="string"
                    className="form-control"
                    name="idInutilizacao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.inutilizacao.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="inutilizacao-idEmitente"
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
                  <Label id="serieLabel" for="serie">
                    <Translate contentKey="ecommerceApplicationApp.inutilizacao.serie">Serie</Translate>
                  </Label>
                  <AvField
                    id="inutilizacao-serie"
                    type="text"
                    name="serie"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 3, errorMessage: translate('entity.validation.maxlength', { max: 3 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numeroInicialLabel" for="numeroInicial">
                    <Translate contentKey="ecommerceApplicationApp.inutilizacao.numeroInicial">Numero Inicial</Translate>
                  </Label>
                  <AvField
                    id="inutilizacao-numeroInicial"
                    type="text"
                    name="numeroInicial"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 9, errorMessage: translate('entity.validation.maxlength', { max: 9 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numeroFinalLabel" for="numeroFinal">
                    <Translate contentKey="ecommerceApplicationApp.inutilizacao.numeroFinal">Numero Final</Translate>
                  </Label>
                  <AvField
                    id="inutilizacao-numeroFinal"
                    type="text"
                    name="numeroFinal"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 9, errorMessage: translate('entity.validation.maxlength', { max: 9 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="protocoloXmlLabel" for="protocoloXml">
                      <Translate contentKey="ecommerceApplicationApp.inutilizacao.protocoloXml">Protocolo Xml</Translate>
                    </Label>
                    <br />
                    {protocoloXml ? (
                      <div>
                        <a onClick={openFile(protocoloXmlContentType, protocoloXml)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {protocoloXmlContentType}, {byteSize(protocoloXml)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('protocoloXml')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_protocoloXml" type="file" onChange={this.onBlobChange(false, 'protocoloXml')} />
                    <AvInput type="hidden" name="protocoloXml" value={protocoloXml} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="dataInutilizacaoLabel" for="dataInutilizacao">
                    <Translate contentKey="ecommerceApplicationApp.inutilizacao.dataInutilizacao">Data Inutilizacao</Translate>
                  </Label>
                  <AvInput
                    id="inutilizacao-dataInutilizacao"
                    type="datetime-local"
                    className="form-control"
                    name="dataInutilizacao"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.inutilizacaoEntity.dataInutilizacao)}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/inutilizacao" replace color="info">
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
  inutilizacaoEntity: storeState.inutilizacao.entity,
  loading: storeState.inutilizacao.loading,
  updating: storeState.inutilizacao.updating,
  updateSuccess: storeState.inutilizacao.updateSuccess
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
)(InutilizacaoUpdate);
