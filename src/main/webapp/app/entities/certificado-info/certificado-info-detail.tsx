import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './certificado-info.reducer';
import { ICertificadoInfo } from 'app/shared/model/certificado-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICertificadoInfoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CertificadoInfoDetail extends React.Component<ICertificadoInfoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { certificadoInfoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.certificadoInfo.detail.title">CertificadoInfo</Translate> [<b>
              {certificadoInfoEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idCertificadoInfo">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.idCertificadoInfo">Id Certificado Info</Translate>
              </span>
            </dt>
            <dd>{certificadoInfoEntity.idCertificadoInfo}</dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{certificadoInfoEntity.idEmitente}</dd>
            <dt>
              <span id="alias">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.alias">Alias</Translate>
              </span>
            </dt>
            <dd>{certificadoInfoEntity.alias}</dd>
            <dt>
              <span id="nome">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.nome">Nome</Translate>
              </span>
            </dt>
            <dd>{certificadoInfoEntity.nome}</dd>
            <dt>
              <span id="autoridadeCertificadora">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.autoridadeCertificadora">Autoridade Certificadora</Translate>
              </span>
            </dt>
            <dd>{certificadoInfoEntity.autoridadeCertificadora}</dd>
            <dt>
              <span id="cNPJ">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.cNPJ">C NPJ</Translate>
              </span>
            </dt>
            <dd>{certificadoInfoEntity.cNPJ}</dd>
            <dt>
              <span id="caminho">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.caminho">Caminho</Translate>
              </span>
            </dt>
            <dd>{certificadoInfoEntity.caminho}</dd>
            <dt>
              <span id="tipoCertificado">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.tipoCertificado">Tipo Certificado</Translate>
              </span>
            </dt>
            <dd>{certificadoInfoEntity.tipoCertificado}</dd>
            <dt>
              <span id="dataUtilizacao">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.dataUtilizacao">Data Utilizacao</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={certificadoInfoEntity.dataUtilizacao} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dataValidade">
                <Translate contentKey="ecommerceApplicationApp.certificadoInfo.dataValidade">Data Validade</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={certificadoInfoEntity.dataValidade} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/certificado-info" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/certificado-info/${certificadoInfoEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ certificadoInfo }: IRootState) => ({
  certificadoInfoEntity: certificadoInfo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CertificadoInfoDetail);
