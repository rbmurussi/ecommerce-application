import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './evento.reducer';
import { IEvento } from 'app/shared/model/evento.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEventoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEventoUpdateState {
  isNew: boolean;
}

export class EventoUpdate extends React.Component<IEventoUpdateProps, IEventoUpdateState> {
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
    values.dataEvento = convertDateTimeToServer(values.dataEvento);
    values.dataRegEvento = convertDateTimeToServer(values.dataRegEvento);

    if (errors.length === 0) {
      const { eventoEntity } = this.props;
      const entity = {
        ...eventoEntity,
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
    this.props.history.push('/entity/evento');
  };

  render() {
    const { eventoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { xmlProc, xmlProcContentType } = eventoEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.evento.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.evento.home.createOrEditLabel">Create or edit a Evento</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : eventoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="evento-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idEventoLabel" for="idEvento">
                    <Translate contentKey="ecommerceApplicationApp.evento.idEvento">Id Evento</Translate>
                  </Label>
                  <AvField
                    id="evento-idEvento"
                    type="string"
                    className="form-control"
                    name="idEvento"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idNotalFiscalLabel" for="idNotalFiscal">
                    <Translate contentKey="ecommerceApplicationApp.evento.idNotalFiscal">Id Notal Fiscal</Translate>
                  </Label>
                  <AvField
                    id="evento-idNotalFiscal"
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
                  <Label id="tpEventoLabel" for="tpEvento">
                    <Translate contentKey="ecommerceApplicationApp.evento.tpEvento">Tp Evento</Translate>
                  </Label>
                  <AvField
                    id="evento-tpEvento"
                    type="text"
                    name="tpEvento"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 7, errorMessage: translate('entity.validation.maxlength', { max: 7 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nSeqEventoLabel" for="nSeqEvento">
                    <Translate contentKey="ecommerceApplicationApp.evento.nSeqEvento">N Seq Evento</Translate>
                  </Label>
                  <AvField
                    id="evento-nSeqEvento"
                    type="string"
                    className="form-control"
                    name="nSeqEvento"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dataEventoLabel" for="dataEvento">
                    <Translate contentKey="ecommerceApplicationApp.evento.dataEvento">Data Evento</Translate>
                  </Label>
                  <AvInput
                    id="evento-dataEvento"
                    type="datetime-local"
                    className="form-control"
                    name="dataEvento"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.eventoEntity.dataEvento)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numProtocoloLabel" for="numProtocolo">
                    <Translate contentKey="ecommerceApplicationApp.evento.numProtocolo">Num Protocolo</Translate>
                  </Label>
                  <AvField
                    id="evento-numProtocolo"
                    type="text"
                    name="numProtocolo"
                    validate={{
                      maxLength: { value: 15, errorMessage: translate('entity.validation.maxlength', { max: 15 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="xmlProcLabel" for="xmlProc">
                      <Translate contentKey="ecommerceApplicationApp.evento.xmlProc">Xml Proc</Translate>
                    </Label>
                    <br />
                    {xmlProc ? (
                      <div>
                        <a onClick={openFile(xmlProcContentType, xmlProc)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {xmlProcContentType}, {byteSize(xmlProc)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('xmlProc')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_xmlProc" type="file" onChange={this.onBlobChange(false, 'xmlProc')} />
                    <AvInput
                      type="hidden"
                      name="xmlProc"
                      value={xmlProc}
                      validate={{
                        required: { value: true, errorMessage: translate('entity.validation.required') }
                      }}
                    />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="dataRegEventoLabel" for="dataRegEvento">
                    <Translate contentKey="ecommerceApplicationApp.evento.dataRegEvento">Data Reg Evento</Translate>
                  </Label>
                  <AvInput
                    id="evento-dataRegEvento"
                    type="datetime-local"
                    className="form-control"
                    name="dataRegEvento"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.eventoEntity.dataRegEvento)}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/evento" replace color="info">
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
  eventoEntity: storeState.evento.entity,
  loading: storeState.evento.loading,
  updating: storeState.evento.updating,
  updateSuccess: storeState.evento.updateSuccess
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
)(EventoUpdate);
