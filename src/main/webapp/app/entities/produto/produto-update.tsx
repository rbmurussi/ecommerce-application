import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './produto.reducer';
import { IProduto } from 'app/shared/model/produto.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProdutoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IProdutoUpdateState {
  isNew: boolean;
}

export class ProdutoUpdate extends React.Component<IProdutoUpdateProps, IProdutoUpdateState> {
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
    if (!this.state.isNew) {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { produtoEntity } = this.props;
      const entity = {
        ...produtoEntity,
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
    this.props.history.push('/entity/produto');
  };

  render() {
    const { produtoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.produto.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.produto.home.createOrEditLabel">Create or edit a Produto</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : produtoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="produto-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="cProdLabel" for="cProd">
                    <Translate contentKey="ecommerceApplicationApp.produto.cProd">C Prod</Translate>
                  </Label>
                  <AvField
                    id="produto-cProd"
                    type="text"
                    name="cProd"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idProdutoLabel" for="idProduto">
                    <Translate contentKey="ecommerceApplicationApp.produto.idProduto">Id Produto</Translate>
                  </Label>
                  <AvField
                    id="produto-idProduto"
                    type="string"
                    className="form-control"
                    name="idProduto"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xProdLabel" for="xProd">
                    <Translate contentKey="ecommerceApplicationApp.produto.xProd">X Prod</Translate>
                  </Label>
                  <AvField
                    id="produto-xProd"
                    type="text"
                    name="xProd"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 120, errorMessage: translate('entity.validation.maxlength', { max: 120 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cEANLabel" for="cEAN">
                    <Translate contentKey="ecommerceApplicationApp.produto.cEAN">C EAN</Translate>
                  </Label>
                  <AvField
                    id="produto-cEAN"
                    type="text"
                    name="cEAN"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nCMLabel" for="nCM">
                    <Translate contentKey="ecommerceApplicationApp.produto.nCM">N CM</Translate>
                  </Label>
                  <AvField
                    id="produto-nCM"
                    type="text"
                    name="nCM"
                    validate={{
                      maxLength: { value: 8, errorMessage: translate('entity.validation.maxlength', { max: 8 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="exTipiLabel" for="exTipi">
                    <Translate contentKey="ecommerceApplicationApp.produto.exTipi">Ex Tipi</Translate>
                  </Label>
                  <AvField
                    id="produto-exTipi"
                    type="text"
                    name="exTipi"
                    validate={{
                      maxLength: { value: 3, errorMessage: translate('entity.validation.maxlength', { max: 3 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="generoLabel" for="genero">
                    <Translate contentKey="ecommerceApplicationApp.produto.genero">Genero</Translate>
                  </Label>
                  <AvField
                    id="produto-genero"
                    type="text"
                    name="genero"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="uComLabel" for="uCom">
                    <Translate contentKey="ecommerceApplicationApp.produto.uCom">U Com</Translate>
                  </Label>
                  <AvField
                    id="produto-uCom"
                    type="text"
                    name="uCom"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cEANTribLabel" for="cEANTrib">
                    <Translate contentKey="ecommerceApplicationApp.produto.cEANTrib">C EAN Trib</Translate>
                  </Label>
                  <AvField
                    id="produto-cEANTrib"
                    type="text"
                    name="cEANTrib"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="uTribLabel" for="uTrib">
                    <Translate contentKey="ecommerceApplicationApp.produto.uTrib">U Trib</Translate>
                  </Label>
                  <AvField
                    id="produto-uTrib"
                    type="text"
                    name="uTrib"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="vUNComLabel" for="vUNCom">
                    <Translate contentKey="ecommerceApplicationApp.produto.vUNCom">V UN Com</Translate>
                  </Label>
                  <AvField
                    id="produto-vUNCom"
                    type="text"
                    name="vUNCom"
                    validate={{
                      maxLength: { value: 17, errorMessage: translate('entity.validation.maxlength', { max: 17 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="vUNTribLabel" for="vUNTrib">
                    <Translate contentKey="ecommerceApplicationApp.produto.vUNTrib">V UN Trib</Translate>
                  </Label>
                  <AvField
                    id="produto-vUNTrib"
                    type="text"
                    name="vUNTrib"
                    validate={{
                      maxLength: { value: 17, errorMessage: translate('entity.validation.maxlength', { max: 17 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="qTribLabel" for="qTrib">
                    <Translate contentKey="ecommerceApplicationApp.produto.qTrib">Q Trib</Translate>
                  </Label>
                  <AvField
                    id="produto-qTrib"
                    type="text"
                    name="qTrib"
                    validate={{
                      maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.produto.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="produto-idEmitente"
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
                    <Translate contentKey="ecommerceApplicationApp.produto.versao">Versao</Translate>
                  </Label>
                  <AvField
                    id="produto-versao"
                    type="text"
                    name="versao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cestLabel" for="cest">
                    <Translate contentKey="ecommerceApplicationApp.produto.cest">Cest</Translate>
                  </Label>
                  <AvField
                    id="produto-cest"
                    type="text"
                    name="cest"
                    validate={{
                      maxLength: { value: 7, errorMessage: translate('entity.validation.maxlength', { max: 7 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/produto" replace color="info">
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
  produtoEntity: storeState.produto.entity,
  loading: storeState.produto.loading,
  updating: storeState.produto.updating,
  updateSuccess: storeState.produto.updateSuccess
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
)(ProdutoUpdate);
