import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './cliente.reducer';
import { ICliente } from 'app/shared/model/cliente.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IClienteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IClienteUpdateState {
  isNew: boolean;
}

export class ClienteUpdate extends React.Component<IClienteUpdateProps, IClienteUpdateState> {
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
      const { clienteEntity } = this.props;
      const entity = {
        ...clienteEntity,
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
    this.props.history.push('/entity/cliente');
  };

  render() {
    const { clienteEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.cliente.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.cliente.home.createOrEditLabel">Create or edit a Cliente</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : clienteEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="cliente-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tpDocumentoEnumLabel" for="tpDocumentoEnum">
                    <Translate contentKey="ecommerceApplicationApp.cliente.tpDocumentoEnum">Tp Documento Enum</Translate>
                  </Label>
                  <AvField id="cliente-tpDocumentoEnum" type="string" className="form-control" name="tpDocumentoEnum" />
                </AvGroup>
                <AvGroup>
                  <Label id="idClienteLabel" for="idCliente">
                    <Translate contentKey="ecommerceApplicationApp.cliente.idCliente">Id Cliente</Translate>
                  </Label>
                  <AvField
                    id="cliente-idCliente"
                    type="string"
                    className="form-control"
                    name="idCliente"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nrDocumentoLabel" for="nrDocumento">
                    <Translate contentKey="ecommerceApplicationApp.cliente.nrDocumento">Nr Documento</Translate>
                  </Label>
                  <AvField
                    id="cliente-nrDocumento"
                    type="text"
                    name="nrDocumento"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xNomeLabel" for="xNome">
                    <Translate contentKey="ecommerceApplicationApp.cliente.xNome">X Nome</Translate>
                  </Label>
                  <AvField
                    id="cliente-xNome"
                    type="text"
                    name="xNome"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xLgrLabel" for="xLgr">
                    <Translate contentKey="ecommerceApplicationApp.cliente.xLgr">X Lgr</Translate>
                  </Label>
                  <AvField
                    id="cliente-xLgr"
                    type="text"
                    name="xLgr"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nRoLabel" for="nRo">
                    <Translate contentKey="ecommerceApplicationApp.cliente.nRo">N Ro</Translate>
                  </Label>
                  <AvField
                    id="cliente-nRo"
                    type="text"
                    name="nRo"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xCplLabel" for="xCpl">
                    <Translate contentKey="ecommerceApplicationApp.cliente.xCpl">X Cpl</Translate>
                  </Label>
                  <AvField
                    id="cliente-xCpl"
                    type="text"
                    name="xCpl"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xBairroLabel" for="xBairro">
                    <Translate contentKey="ecommerceApplicationApp.cliente.xBairro">X Bairro</Translate>
                  </Label>
                  <AvField
                    id="cliente-xBairro"
                    type="text"
                    name="xBairro"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xMunLabel" for="xMun">
                    <Translate contentKey="ecommerceApplicationApp.cliente.xMun">X Mun</Translate>
                  </Label>
                  <AvField
                    id="cliente-xMun"
                    type="text"
                    name="xMun"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cMunLabel" for="cMun">
                    <Translate contentKey="ecommerceApplicationApp.cliente.cMun">C Mun</Translate>
                  </Label>
                  <AvField
                    id="cliente-cMun"
                    type="text"
                    name="cMun"
                    validate={{
                      maxLength: { value: 7, errorMessage: translate('entity.validation.maxlength', { max: 7 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="uFLabel" for="uF">
                    <Translate contentKey="ecommerceApplicationApp.cliente.uF">U F</Translate>
                  </Label>
                  <AvField
                    id="cliente-uF"
                    type="text"
                    name="uF"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cEPLabel" for="cEP">
                    <Translate contentKey="ecommerceApplicationApp.cliente.cEP">C EP</Translate>
                  </Label>
                  <AvField
                    id="cliente-cEP"
                    type="text"
                    name="cEP"
                    validate={{
                      maxLength: { value: 8, errorMessage: translate('entity.validation.maxlength', { max: 8 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cPaisLabel" for="cPais">
                    <Translate contentKey="ecommerceApplicationApp.cliente.cPais">C Pais</Translate>
                  </Label>
                  <AvField
                    id="cliente-cPais"
                    type="text"
                    name="cPais"
                    validate={{
                      maxLength: { value: 4, errorMessage: translate('entity.validation.maxlength', { max: 4 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xPaisLabel" for="xPais">
                    <Translate contentKey="ecommerceApplicationApp.cliente.xPais">X Pais</Translate>
                  </Label>
                  <AvField
                    id="cliente-xPais"
                    type="text"
                    name="xPais"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="foneLabel" for="fone">
                    <Translate contentKey="ecommerceApplicationApp.cliente.fone">Fone</Translate>
                  </Label>
                  <AvField
                    id="cliente-fone"
                    type="text"
                    name="fone"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    <Translate contentKey="ecommerceApplicationApp.cliente.email">Email</Translate>
                  </Label>
                  <AvField
                    id="cliente-email"
                    type="text"
                    name="email"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.cliente.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="cliente-idEmitente"
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
                  <Label id="iELabel" for="iE">
                    <Translate contentKey="ecommerceApplicationApp.cliente.iE">I E</Translate>
                  </Label>
                  <AvField
                    id="cliente-iE"
                    type="text"
                    name="iE"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="iESUFLabel" for="iESUF">
                    <Translate contentKey="ecommerceApplicationApp.cliente.iESUF">I ESUF</Translate>
                  </Label>
                  <AvField
                    id="cliente-iESUF"
                    type="text"
                    name="iESUF"
                    validate={{
                      maxLength: { value: 9, errorMessage: translate('entity.validation.maxlength', { max: 9 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="versaoLabel" for="versao">
                    <Translate contentKey="ecommerceApplicationApp.cliente.versao">Versao</Translate>
                  </Label>
                  <AvField
                    id="cliente-versao"
                    type="text"
                    name="versao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/cliente" replace color="info">
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
  clienteEntity: storeState.cliente.entity,
  loading: storeState.cliente.loading,
  updating: storeState.cliente.updating,
  updateSuccess: storeState.cliente.updateSuccess
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
)(ClienteUpdate);
