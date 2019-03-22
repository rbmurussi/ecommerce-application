import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './numeracao.reducer';
import { INumeracao } from 'app/shared/model/numeracao.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INumeracaoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface INumeracaoUpdateState {
  isNew: boolean;
}

export class NumeracaoUpdate extends React.Component<INumeracaoUpdateProps, INumeracaoUpdateState> {
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
    values.dataSistema = convertDateTimeToServer(values.dataSistema);

    if (errors.length === 0) {
      const { numeracaoEntity } = this.props;
      const entity = {
        ...numeracaoEntity,
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
    this.props.history.push('/entity/numeracao');
  };

  render() {
    const { numeracaoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.numeracao.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.numeracao.home.createOrEditLabel">Create or edit a Numeracao</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : numeracaoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="numeracao-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idNumeracaoLabel" for="idNumeracao">
                    <Translate contentKey="ecommerceApplicationApp.numeracao.idNumeracao">Id Numeracao</Translate>
                  </Label>
                  <AvField
                    id="numeracao-idNumeracao"
                    type="string"
                    className="form-control"
                    name="idNumeracao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="serieLabel" for="serie">
                    <Translate contentKey="ecommerceApplicationApp.numeracao.serie">Serie</Translate>
                  </Label>
                  <AvField
                    id="numeracao-serie"
                    type="text"
                    name="serie"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 3, errorMessage: translate('entity.validation.maxlength', { max: 3 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numeroLabel" for="numero">
                    <Translate contentKey="ecommerceApplicationApp.numeracao.numero">Numero</Translate>
                  </Label>
                  <AvField
                    id="numeracao-numero"
                    type="text"
                    name="numero"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 9, errorMessage: translate('entity.validation.maxlength', { max: 9 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="anoLabel" for="ano">
                    <Translate contentKey="ecommerceApplicationApp.numeracao.ano">Ano</Translate>
                  </Label>
                  <AvField
                    id="numeracao-ano"
                    type="text"
                    name="ano"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dataSistemaLabel" for="dataSistema">
                    <Translate contentKey="ecommerceApplicationApp.numeracao.dataSistema">Data Sistema</Translate>
                  </Label>
                  <AvInput
                    id="numeracao-dataSistema"
                    type="datetime-local"
                    className="form-control"
                    name="dataSistema"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.numeracaoEntity.dataSistema)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.numeracao.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="numeracao-idEmitente"
                    type="string"
                    className="form-control"
                    name="idEmitente"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/numeracao" replace color="info">
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
  numeracaoEntity: storeState.numeracao.entity,
  loading: storeState.numeracao.loading,
  updating: storeState.numeracao.updating,
  updateSuccess: storeState.numeracao.updateSuccess
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
)(NumeracaoUpdate);
