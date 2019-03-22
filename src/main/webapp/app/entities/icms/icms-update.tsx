import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './icms.reducer';
import { IIcms } from 'app/shared/model/icms.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IIcmsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IIcmsUpdateState {
  isNew: boolean;
}

export class IcmsUpdate extends React.Component<IIcmsUpdateProps, IIcmsUpdateState> {
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
      const { icmsEntity } = this.props;
      const entity = {
        ...icmsEntity,
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
    this.props.history.push('/entity/icms');
  };

  render() {
    const { icmsEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.icms.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.icms.home.createOrEditLabel">Create or edit a Icms</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : icmsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="icms-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idIcmsLabel" for="idIcms">
                    <Translate contentKey="ecommerceApplicationApp.icms.idIcms">Id Icms</Translate>
                  </Label>
                  <AvField
                    id="icms-idIcms"
                    type="string"
                    className="form-control"
                    name="idIcms"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="origLabel" for="orig">
                    <Translate contentKey="ecommerceApplicationApp.icms.orig">Orig</Translate>
                  </Label>
                  <AvField
                    id="icms-orig"
                    type="text"
                    name="orig"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idProdutoLabel" for="idProduto">
                    <Translate contentKey="ecommerceApplicationApp.icms.idProduto">Id Produto</Translate>
                  </Label>
                  <AvField
                    id="icms-idProduto"
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
                  <Label id="cstLabel" for="cst">
                    <Translate contentKey="ecommerceApplicationApp.icms.cst">Cst</Translate>
                  </Label>
                  <AvField
                    id="icms-cst"
                    type="text"
                    name="cst"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 7, errorMessage: translate('entity.validation.maxlength', { max: 7 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modBcLabel" for="modBc">
                    <Translate contentKey="ecommerceApplicationApp.icms.modBc">Mod Bc</Translate>
                  </Label>
                  <AvField
                    id="icms-modBc"
                    type="text"
                    name="modBc"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pREDBCLabel" for="pREDBC">
                    <Translate contentKey="ecommerceApplicationApp.icms.pREDBC">P REDBC</Translate>
                  </Label>
                  <AvField
                    id="icms-pREDBC"
                    type="text"
                    name="pREDBC"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pICMSLabel" for="pICMS">
                    <Translate contentKey="ecommerceApplicationApp.icms.pICMS">P ICMS</Translate>
                  </Label>
                  <AvField
                    id="icms-pICMS"
                    type="text"
                    name="pICMS"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modBCSTLabel" for="modBCST">
                    <Translate contentKey="ecommerceApplicationApp.icms.modBCST">Mod BCST</Translate>
                  </Label>
                  <AvField
                    id="icms-modBCST"
                    type="text"
                    name="modBCST"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pMVASTLabel" for="pMVAST">
                    <Translate contentKey="ecommerceApplicationApp.icms.pMVAST">P MVAST</Translate>
                  </Label>
                  <AvField
                    id="icms-pMVAST"
                    type="text"
                    name="pMVAST"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pRedBCSTLabel" for="pRedBCST">
                    <Translate contentKey="ecommerceApplicationApp.icms.pRedBCST">P Red BCST</Translate>
                  </Label>
                  <AvField
                    id="icms-pRedBCST"
                    type="text"
                    name="pRedBCST"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pICMSSTLabel" for="pICMSST">
                    <Translate contentKey="ecommerceApplicationApp.icms.pICMSST">P ICMSST</Translate>
                  </Label>
                  <AvField
                    id="icms-pICMSST"
                    type="text"
                    name="pICMSST"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="motDesICMSLabel" for="motDesICMS">
                    <Translate contentKey="ecommerceApplicationApp.icms.motDesICMS">Mot Des ICMS</Translate>
                  </Label>
                  <AvField
                    id="icms-motDesICMS"
                    type="text"
                    name="motDesICMS"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pBCOPLabel" for="pBCOP">
                    <Translate contentKey="ecommerceApplicationApp.icms.pBCOP">P BCOP</Translate>
                  </Label>
                  <AvField
                    id="icms-pBCOP"
                    type="text"
                    name="pBCOP"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="uFSTLabel" for="uFST">
                    <Translate contentKey="ecommerceApplicationApp.icms.uFST">U FST</Translate>
                  </Label>
                  <AvField
                    id="icms-uFST"
                    type="text"
                    name="uFST"
                    validate={{
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pCredSNLabel" for="pCredSN">
                    <Translate contentKey="ecommerceApplicationApp.icms.pCredSN">P Cred SN</Translate>
                  </Label>
                  <AvField
                    id="icms-pCredSN"
                    type="text"
                    name="pCredSN"
                    validate={{
                      maxLength: { value: 6, errorMessage: translate('entity.validation.maxlength', { max: 6 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/icms" replace color="info">
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
  icmsEntity: storeState.icms.entity,
  loading: storeState.icms.loading,
  updating: storeState.icms.updating,
  updateSuccess: storeState.icms.updateSuccess
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
)(IcmsUpdate);
