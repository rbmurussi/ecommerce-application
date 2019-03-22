import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './certificado-info.reducer';
import { ICertificadoInfo } from 'app/shared/model/certificado-info.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICertificadoInfoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICertificadoInfoUpdateState {
  isNew: boolean;
}

export class CertificadoInfoUpdate extends React.Component<ICertificadoInfoUpdateProps, ICertificadoInfoUpdateState> {
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
    values.dataUtilizacao = convertDateTimeToServer(values.dataUtilizacao);
    values.dataValidade = convertDateTimeToServer(values.dataValidade);

    if (errors.length === 0) {
      const { certificadoInfoEntity } = this.props;
      const entity = {
        ...certificadoInfoEntity,
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
    this.props.history.push('/entity/certificado-info');
  };

  render() {
    const { certificadoInfoEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="ecommerceApplicationApp.certificadoInfo.home.createOrEditLabel">
              <Translate contentKey="ecommerceApplicationApp.certificadoInfo.home.createOrEditLabel">
                Create or edit a CertificadoInfo
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : certificadoInfoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="certificado-info-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idCertificadoInfoLabel" for="idCertificadoInfo">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.idCertificadoInfo">Id Certificado Info</Translate>
                  </Label>
                  <AvField
                    id="certificado-info-idCertificadoInfo"
                    type="string"
                    className="form-control"
                    name="idCertificadoInfo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="idEmitenteLabel" for="idEmitente">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.idEmitente">Id Emitente</Translate>
                  </Label>
                  <AvField
                    id="certificado-info-idEmitente"
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
                  <Label id="aliasLabel" for="alias">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.alias">Alias</Translate>
                  </Label>
                  <AvField
                    id="certificado-info-alias"
                    type="text"
                    name="alias"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nomeLabel" for="nome">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.nome">Nome</Translate>
                  </Label>
                  <AvField
                    id="certificado-info-nome"
                    type="text"
                    name="nome"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="autoridadeCertificadoraLabel" for="autoridadeCertificadora">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.autoridadeCertificadora">
                      Autoridade Certificadora
                    </Translate>
                  </Label>
                  <AvField
                    id="certificado-info-autoridadeCertificadora"
                    type="text"
                    name="autoridadeCertificadora"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cNPJLabel" for="cNPJ">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.cNPJ">C NPJ</Translate>
                  </Label>
                  <AvField
                    id="certificado-info-cNPJ"
                    type="text"
                    name="cNPJ"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="caminhoLabel" for="caminho">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.caminho">Caminho</Translate>
                  </Label>
                  <AvField
                    id="certificado-info-caminho"
                    type="text"
                    name="caminho"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tipoCertificadoLabel" for="tipoCertificado">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.tipoCertificado">Tipo Certificado</Translate>
                  </Label>
                  <AvField
                    id="certificado-info-tipoCertificado"
                    type="text"
                    name="tipoCertificado"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 2, errorMessage: translate('entity.validation.maxlength', { max: 2 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dataUtilizacaoLabel" for="dataUtilizacao">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.dataUtilizacao">Data Utilizacao</Translate>
                  </Label>
                  <AvInput
                    id="certificado-info-dataUtilizacao"
                    type="datetime-local"
                    className="form-control"
                    name="dataUtilizacao"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.certificadoInfoEntity.dataUtilizacao)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dataValidadeLabel" for="dataValidade">
                    <Translate contentKey="ecommerceApplicationApp.certificadoInfo.dataValidade">Data Validade</Translate>
                  </Label>
                  <AvInput
                    id="certificado-info-dataValidade"
                    type="datetime-local"
                    className="form-control"
                    name="dataValidade"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.certificadoInfoEntity.dataValidade)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/certificado-info" replace color="info">
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
  certificadoInfoEntity: storeState.certificadoInfo.entity,
  loading: storeState.certificadoInfo.loading,
  updating: storeState.certificadoInfo.updating,
  updateSuccess: storeState.certificadoInfo.updateSuccess
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
)(CertificadoInfoUpdate);
