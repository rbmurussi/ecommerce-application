import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './lote.reducer';
import { ILote } from 'app/shared/model/lote.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILoteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILoteUpdateState {
  isNew: boolean;
}

export class LoteUpdate extends React.Component<ILoteUpdateProps, ILoteUpdateState> {
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
    values.dataTransmissao = convertDateTimeToServer(values.dataTransmissao);
    values.dataProc = convertDateTimeToServer(values.dataProc);

    if (errors.length === 0) {
      const { loteEntity } = this.props;
      const entity = {
        ...loteEntity,
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
    this.props.history.push('/entity/lote');
  };

  render() {
    const { loteEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { xmlRetorno, xmlRetornoContentType } = loteEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.lote.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.lote.home.createOrEditLabel">Create or edit a Lote</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : loteEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="lote-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idLoteLabel" for="idLote">
                    <Translate contentKey="ecommerceApplicationApp.lote.idLote">Id Lote</Translate>
                  </Label>
                  <AvField
                    id="lote-idLote"
                    type="string"
                    className="form-control"
                    name="idLote"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cNPJTransmissaoLabel" for="cNPJTransmissao">
                    <Translate contentKey="ecommerceApplicationApp.lote.cNPJTransmissao">C NPJ Transmissao</Translate>
                  </Label>
                  <AvField
                    id="lote-cNPJTransmissao"
                    type="text"
                    name="cNPJTransmissao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dataTransmissaoLabel" for="dataTransmissao">
                    <Translate contentKey="ecommerceApplicationApp.lote.dataTransmissao">Data Transmissao</Translate>
                  </Label>
                  <AvInput
                    id="lote-dataTransmissao"
                    type="datetime-local"
                    className="form-control"
                    name="dataTransmissao"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.loteEntity.dataTransmissao)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numReciboLabel" for="numRecibo">
                    <Translate contentKey="ecommerceApplicationApp.lote.numRecibo">Num Recibo</Translate>
                  </Label>
                  <AvField
                    id="lote-numRecibo"
                    type="text"
                    name="numRecibo"
                    validate={{
                      maxLength: { value: 15, errorMessage: translate('entity.validation.maxlength', { max: 15 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="xmlRetornoLabel" for="xmlRetorno">
                      <Translate contentKey="ecommerceApplicationApp.lote.xmlRetorno">Xml Retorno</Translate>
                    </Label>
                    <br />
                    {xmlRetorno ? (
                      <div>
                        <a onClick={openFile(xmlRetornoContentType, xmlRetorno)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {xmlRetornoContentType}, {byteSize(xmlRetorno)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('xmlRetorno')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_xmlRetorno" type="file" onChange={this.onBlobChange(false, 'xmlRetorno')} />
                    <AvInput type="hidden" name="xmlRetorno" value={xmlRetorno} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="dataProcLabel" for="dataProc">
                    <Translate contentKey="ecommerceApplicationApp.lote.dataProc">Data Proc</Translate>
                  </Label>
                  <AvInput
                    id="lote-dataProc"
                    type="datetime-local"
                    className="form-control"
                    name="dataProc"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.loteEntity.dataProc)}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/lote" replace color="info">
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
  loteEntity: storeState.lote.entity,
  loading: storeState.lote.loading,
  updating: storeState.lote.updating,
  updateSuccess: storeState.lote.updateSuccess
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
)(LoteUpdate);
