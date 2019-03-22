import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './pesquisa.reducer';
import { IPesquisa } from 'app/shared/model/pesquisa.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPesquisaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPesquisaUpdateState {
  isNew: boolean;
}

export class PesquisaUpdate extends React.Component<IPesquisaUpdateProps, IPesquisaUpdateState> {
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
      const { pesquisaEntity } = this.props;
      const entity = {
        ...pesquisaEntity,
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
    this.props.history.push('/entity/pesquisa');
  };

  render() {
    const { pesquisaEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.pesquisa.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.pesquisa.home.createOrEditLabel">Create or edit a Pesquisa</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pesquisaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pesquisa-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idPesquisaLabel" for="idPesquisa">
                    <Translate contentKey="ecommerceApplicationApp.pesquisa.idPesquisa">Id Pesquisa</Translate>
                  </Label>
                  <AvField
                    id="pesquisa-idPesquisa"
                    type="string"
                    className="form-control"
                    name="idPesquisa"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="campoEnumLabel" for="campoEnum">
                    <Translate contentKey="ecommerceApplicationApp.pesquisa.campoEnum">Campo Enum</Translate>
                  </Label>
                  <AvField
                    id="pesquisa-campoEnum"
                    type="string"
                    className="form-control"
                    name="campoEnum"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="valorLabel" for="valor">
                    <Translate contentKey="ecommerceApplicationApp.pesquisa.valor">Valor</Translate>
                  </Label>
                  <AvField
                    id="pesquisa-valor"
                    type="text"
                    name="valor"
                    validate={{
                      maxLength: { value: 120, errorMessage: translate('entity.validation.maxlength', { max: 120 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.pesquisa.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="pesquisa-idEmitente"
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
                  <Label id="telaEnumLabel" for="telaEnum">
                    <Translate contentKey="ecommerceApplicationApp.pesquisa.telaEnum">Tela Enum</Translate>
                  </Label>
                  <AvField
                    id="pesquisa-telaEnum"
                    type="string"
                    className="form-control"
                    name="telaEnum"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pesquisa" replace color="info">
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
  pesquisaEntity: storeState.pesquisa.entity,
  loading: storeState.pesquisa.loading,
  updating: storeState.pesquisa.updating,
  updateSuccess: storeState.pesquisa.updateSuccess
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
)(PesquisaUpdate);
