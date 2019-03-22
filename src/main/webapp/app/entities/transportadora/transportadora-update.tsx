import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './transportadora.reducer';
import { ITransportadora } from 'app/shared/model/transportadora.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITransportadoraUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITransportadoraUpdateState {
  isNew: boolean;
}

export class TransportadoraUpdate extends React.Component<ITransportadoraUpdateProps, ITransportadoraUpdateState> {
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

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { transportadoraEntity } = this.props;
      const entity = {
        ...transportadoraEntity,
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
    this.props.history.push('/entity/transportadora');
  };

  render() {
    const { transportadoraEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.transportadora.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.transportadora.home.createOrEditLabel">
                Create or edit a Transportadora
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : transportadoraEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="transportadora-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idTransportadoraLabel" for="idTransportadora">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.idTransportadora">Id Transportadora</Translate>
                  </Label>
                  <AvField
                    id="transportadora-idTransportadora"
                    type="string"
                    className="form-control"
                    name="idTransportadora"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tpDocumentoEnumLabel" for="tpDocumentoEnum">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.tpDocumentoEnum">Tp Documento Enum</Translate>
                  </Label>
                  <AvField id="transportadora-tpDocumentoEnum" type="string" className="form-control" name="tpDocumentoEnum" />
                </AvGroup>
                <AvGroup>
                  <Label id="nrDocumentoLabel" for="nrDocumento">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.nrDocumento">Nr Documento</Translate>
                  </Label>
                  <AvField
                    id="transportadora-nrDocumento"
                    type="text"
                    name="nrDocumento"
                    validate={{
                      maxLength: { value: 15, errorMessage: translate('entity.validation.maxlength', { max: 15 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xNomeLabel" for="xNome">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.xNome">X Nome</Translate>
                  </Label>
                  <AvField
                    id="transportadora-xNome"
                    type="text"
                    name="xNome"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="iELabel" for="iE">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.iE">I E</Translate>
                  </Label>
                  <AvField
                    id="transportadora-iE"
                    type="text"
                    name="iE"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xEnderLabel" for="xEnder">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.xEnder">X Ender</Translate>
                  </Label>
                  <AvField
                    id="transportadora-xEnder"
                    type="text"
                    name="xEnder"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="uFLabel" for="uF">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.uF">U F</Translate>
                  </Label>
                  <AvField
                    id="transportadora-uF"
                    type="text"
                    name="uF"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xMunLabel" for="xMun">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.xMun">X Mun</Translate>
                  </Label>
                  <AvField
                    id="transportadora-xMun"
                    type="text"
                    name="xMun"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="transportadora-idEmitente"
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
                  <Label id="versaoLabel" for="versao">
                    <Translate contentKey="ecommerceApplicationApp.transportadora.versao">Versao</Translate>
                  </Label>
                  <AvField
                    id="transportadora-versao"
                    type="text"
                    name="versao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/transportadora" replace color="info">
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
  transportadoraEntity: storeState.transportadora.entity,
  loading: storeState.transportadora.loading,
  updating: storeState.transportadora.updating,
  updateSuccess: storeState.transportadora.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TransportadoraUpdate);
