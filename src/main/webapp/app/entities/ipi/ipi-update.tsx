import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './ipi.reducer';
import { IIpi } from 'app/shared/model/ipi.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IIpiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IIpiUpdateState {
  isNew: boolean;
}

export class IpiUpdate extends React.Component<IIpiUpdateProps, IIpiUpdateState> {
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
      const { ipiEntity } = this.props;
      const entity = {
        ...ipiEntity,
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
    this.props.history.push('/entity/ipi');
  };

  render() {
    const { ipiEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.ipi.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.ipi.home.createOrEditLabel">Create or edit a Ipi</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : ipiEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ipi-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idIpiLabel" for="idIpi">
                    <Translate contentKey="ecommerceApplicationApp.ipi.idIpi">Id Ipi</Translate>
                  </Label>
                  <AvField
                    id="ipi-idIpi"
                    type="string"
                    className="form-control"
                    name="idIpi"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="clEnqLabel" for="clEnq">
                    <Translate contentKey="ecommerceApplicationApp.ipi.clEnq">Cl Enq</Translate>
                  </Label>
                  <AvField
                    id="ipi-clEnq"
                    type="text"
                    name="clEnq"
                    validate={{
                      maxLength: { value: 5, errorMessage: translate('entity.validation.maxlength', { max: 5 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cNPJProdLabel" for="cNPJProd">
                    <Translate contentKey="ecommerceApplicationApp.ipi.cNPJProd">C NPJ Prod</Translate>
                  </Label>
                  <AvField
                    id="ipi-cNPJProd"
                    type="text"
                    name="cNPJProd"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cEnqLabel" for="cEnq">
                    <Translate contentKey="ecommerceApplicationApp.ipi.cEnq">C Enq</Translate>
                  </Label>
                  <AvField
                    id="ipi-cEnq"
                    type="text"
                    name="cEnq"
                    validate={{
                      maxLength: { value: 3, errorMessage: translate('entity.validation.maxlength', { max: 3 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idProdutoLabel" for="idProduto">
                    <Translate contentKey="ecommerceApplicationApp.ipi.idProduto">Id Produto</Translate>
                  </Label>
                  <AvField
                    id="ipi-idProduto"
                    type="string"
                    className="form-control"
                    name="idProduto"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ipi" replace color="info">
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
  ipiEntity: storeState.ipi.entity,
  loading: storeState.ipi.loading,
  updating: storeState.ipi.updating,
  updateSuccess: storeState.ipi.updateSuccess
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
)(IpiUpdate);
