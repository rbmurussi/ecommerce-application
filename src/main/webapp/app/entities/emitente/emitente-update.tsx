import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './emitente.reducer';
import { IEmitente } from 'app/shared/model/emitente.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmitenteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEmitenteUpdateState {
  isNew: boolean;
}

export class EmitenteUpdate extends React.Component<IEmitenteUpdateProps, IEmitenteUpdateState> {
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
    if (errors.length === 0) {
      const { emitenteEntity } = this.props;
      const entity = {
        ...emitenteEntity,
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
    this.props.history.push('/entity/emitente');
  };

  render() {
    const { emitenteEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const { logotipo, logotipoContentType } = emitenteEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.emitente.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.emitente.home.createOrEditLabel">Create or edit a Emitente</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : emitenteEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="emitente-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.emitente.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="emitente-idEmitente"
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
                  <Label id="xNomeLabel" for="xNome">
                    <Translate contentKey="ecommerceApplicationApp.emitente.xNome">X Nome</Translate>
                  </Label>
                  <AvField
                    id="emitente-xNome"
                    type="text"
                    name="xNome"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xFantLabel" for="xFant">
                    <Translate contentKey="ecommerceApplicationApp.emitente.xFant">X Fant</Translate>
                  </Label>
                  <AvField
                    id="emitente-xFant"
                    type="text"
                    name="xFant"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xLgrLabel" for="xLgr">
                    <Translate contentKey="ecommerceApplicationApp.emitente.xLgr">X Lgr</Translate>
                  </Label>
                  <AvField
                    id="emitente-xLgr"
                    type="text"
                    name="xLgr"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nRoLabel" for="nRo">
                    <Translate contentKey="ecommerceApplicationApp.emitente.nRo">N Ro</Translate>
                  </Label>
                  <AvField
                    id="emitente-nRo"
                    type="text"
                    name="nRo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xCplLabel" for="xCpl">
                    <Translate contentKey="ecommerceApplicationApp.emitente.xCpl">X Cpl</Translate>
                  </Label>
                  <AvField
                    id="emitente-xCpl"
                    type="text"
                    name="xCpl"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xBairroLabel" for="xBairro">
                    <Translate contentKey="ecommerceApplicationApp.emitente.xBairro">X Bairro</Translate>
                  </Label>
                  <AvField
                    id="emitente-xBairro"
                    type="text"
                    name="xBairro"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cMunLabel" for="cMun">
                    <Translate contentKey="ecommerceApplicationApp.emitente.cMun">C Mun</Translate>
                  </Label>
                  <AvField
                    id="emitente-cMun"
                    type="text"
                    name="cMun"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 7, errorMessage: translate('entity.validation.maxlength', { max: 7 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xMunLabel" for="xMun">
                    <Translate contentKey="ecommerceApplicationApp.emitente.xMun">X Mun</Translate>
                  </Label>
                  <AvField
                    id="emitente-xMun"
                    type="text"
                    name="xMun"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="uFLabel" for="uF">
                    <Translate contentKey="ecommerceApplicationApp.emitente.uF">U F</Translate>
                  </Label>
                  <AvField
                    id="emitente-uF"
                    type="text"
                    name="uF"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cEPLabel" for="cEP">
                    <Translate contentKey="ecommerceApplicationApp.emitente.cEP">C EP</Translate>
                  </Label>
                  <AvField
                    id="emitente-cEP"
                    type="text"
                    name="cEP"
                    validate={{
                      maxLength: { value: 8, errorMessage: translate('entity.validation.maxlength', { max: 8 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cPaisLabel" for="cPais">
                    <Translate contentKey="ecommerceApplicationApp.emitente.cPais">C Pais</Translate>
                  </Label>
                  <AvField
                    id="emitente-cPais"
                    type="text"
                    name="cPais"
                    validate={{
                      maxLength: { value: 4, errorMessage: translate('entity.validation.maxlength', { max: 4 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xPaisLabel" for="xPais">
                    <Translate contentKey="ecommerceApplicationApp.emitente.xPais">X Pais</Translate>
                  </Label>
                  <AvField
                    id="emitente-xPais"
                    type="text"
                    name="xPais"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="foneLabel" for="fone">
                    <Translate contentKey="ecommerceApplicationApp.emitente.fone">Fone</Translate>
                  </Label>
                  <AvField
                    id="emitente-fone"
                    type="text"
                    name="fone"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="iELabel" for="iE">
                    <Translate contentKey="ecommerceApplicationApp.emitente.iE">I E</Translate>
                  </Label>
                  <AvField
                    id="emitente-iE"
                    type="text"
                    name="iE"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="iESTLabel" for="iEST">
                    <Translate contentKey="ecommerceApplicationApp.emitente.iEST">I EST</Translate>
                  </Label>
                  <AvField
                    id="emitente-iEST"
                    type="text"
                    name="iEST"
                    validate={{
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="iMLabel" for="iM">
                    <Translate contentKey="ecommerceApplicationApp.emitente.iM">I M</Translate>
                  </Label>
                  <AvField
                    id="emitente-iM"
                    type="text"
                    name="iM"
                    validate={{
                      maxLength: { value: 15, errorMessage: translate('entity.validation.maxlength', { max: 15 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cNAELabel" for="cNAE">
                    <Translate contentKey="ecommerceApplicationApp.emitente.cNAE">C NAE</Translate>
                  </Label>
                  <AvField
                    id="emitente-cNAE"
                    type="text"
                    name="cNAE"
                    validate={{
                      maxLength: { value: 7, errorMessage: translate('entity.validation.maxlength', { max: 7 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="logotipoLabel" for="logotipo">
                      <Translate contentKey="ecommerceApplicationApp.emitente.logotipo">Logotipo</Translate>
                    </Label>
                    <br />
                    {logotipo ? (
                      <div>
                        <a onClick={openFile(logotipoContentType, logotipo)}>
                          <img src={`data:${logotipoContentType};base64,${logotipo}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {logotipoContentType}, {byteSize(logotipo)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('logotipo')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_logotipo" type="file" onChange={this.onBlobChange(true, 'logotipo')} accept="image/*" />
                    <AvInput type="hidden" name="logotipo" value={logotipo} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="tpDocumentoEnumLabel" for="tpDocumentoEnum">
                    <Translate contentKey="ecommerceApplicationApp.emitente.tpDocumentoEnum">Tp Documento Enum</Translate>
                  </Label>
                  <AvField
                    id="emitente-tpDocumentoEnum"
                    type="string"
                    className="form-control"
                    name="tpDocumentoEnum"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nrDocumentoLabel" for="nrDocumento">
                    <Translate contentKey="ecommerceApplicationApp.emitente.nrDocumento">Nr Documento</Translate>
                  </Label>
                  <AvField
                    id="emitente-nrDocumento"
                    type="text"
                    name="nrDocumento"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="regimeTributarioLabel" for="regimeTributario">
                    <Translate contentKey="ecommerceApplicationApp.emitente.regimeTributario">Regime Tributario</Translate>
                  </Label>
                  <AvField
                    id="emitente-regimeTributario"
                    type="text"
                    name="regimeTributario"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 4, errorMessage: translate('entity.validation.maxlength', { max: 4 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="versaoLabel" for="versao">
                    <Translate contentKey="ecommerceApplicationApp.emitente.versao">Versao</Translate>
                  </Label>
                  <AvField
                    id="emitente-versao"
                    type="text"
                    name="versao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/emitente" replace color="info">
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
  emitenteEntity: storeState.emitente.entity,
  loading: storeState.emitente.loading,
  updating: storeState.emitente.updating,
  updateSuccess: storeState.emitente.updateSuccess
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
)(EmitenteUpdate);
