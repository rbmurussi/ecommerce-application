import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './emitente.reducer';
import { IEmitente } from 'app/shared/model/emitente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmitenteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EmitenteDetail extends React.Component<IEmitenteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { emitenteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.emitente.detail.title">Emitente</Translate> [<b>{emitenteEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.emitente.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.idEmitente}</dd>
            <dt>
              <span id="xNome">
                <Translate contentKey="ecommerceApplicationApp.emitente.xNome">X Nome</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.xNome}</dd>
            <dt>
              <span id="xFant">
                <Translate contentKey="ecommerceApplicationApp.emitente.xFant">X Fant</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.xFant}</dd>
            <dt>
              <span id="xLgr">
                <Translate contentKey="ecommerceApplicationApp.emitente.xLgr">X Lgr</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.xLgr}</dd>
            <dt>
              <span id="nRo">
                <Translate contentKey="ecommerceApplicationApp.emitente.nRo">N Ro</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.nRo}</dd>
            <dt>
              <span id="xCpl">
                <Translate contentKey="ecommerceApplicationApp.emitente.xCpl">X Cpl</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.xCpl}</dd>
            <dt>
              <span id="xBairro">
                <Translate contentKey="ecommerceApplicationApp.emitente.xBairro">X Bairro</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.xBairro}</dd>
            <dt>
              <span id="cMun">
                <Translate contentKey="ecommerceApplicationApp.emitente.cMun">C Mun</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.cMun}</dd>
            <dt>
              <span id="xMun">
                <Translate contentKey="ecommerceApplicationApp.emitente.xMun">X Mun</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.xMun}</dd>
            <dt>
              <span id="uF">
                <Translate contentKey="ecommerceApplicationApp.emitente.uF">U F</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.uF}</dd>
            <dt>
              <span id="cEP">
                <Translate contentKey="ecommerceApplicationApp.emitente.cEP">C EP</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.cEP}</dd>
            <dt>
              <span id="cPais">
                <Translate contentKey="ecommerceApplicationApp.emitente.cPais">C Pais</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.cPais}</dd>
            <dt>
              <span id="xPais">
                <Translate contentKey="ecommerceApplicationApp.emitente.xPais">X Pais</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.xPais}</dd>
            <dt>
              <span id="fone">
                <Translate contentKey="ecommerceApplicationApp.emitente.fone">Fone</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.fone}</dd>
            <dt>
              <span id="iE">
                <Translate contentKey="ecommerceApplicationApp.emitente.iE">I E</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.iE}</dd>
            <dt>
              <span id="iEST">
                <Translate contentKey="ecommerceApplicationApp.emitente.iEST">I EST</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.iEST}</dd>
            <dt>
              <span id="iM">
                <Translate contentKey="ecommerceApplicationApp.emitente.iM">I M</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.iM}</dd>
            <dt>
              <span id="cNAE">
                <Translate contentKey="ecommerceApplicationApp.emitente.cNAE">C NAE</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.cNAE}</dd>
            <dt>
              <span id="logotipo">
                <Translate contentKey="ecommerceApplicationApp.emitente.logotipo">Logotipo</Translate>
              </span>
            </dt>
            <dd>
              {emitenteEntity.logotipo ? (
                <div>
                  <a onClick={openFile(emitenteEntity.logotipoContentType, emitenteEntity.logotipo)}>
                    <img
                      src={`data:${emitenteEntity.logotipoContentType};base64,${emitenteEntity.logotipo}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {emitenteEntity.logotipoContentType}, {byteSize(emitenteEntity.logotipo)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="tpDocumentoEnum">
                <Translate contentKey="ecommerceApplicationApp.emitente.tpDocumentoEnum">Tp Documento Enum</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.tpDocumentoEnum}</dd>
            <dt>
              <span id="nrDocumento">
                <Translate contentKey="ecommerceApplicationApp.emitente.nrDocumento">Nr Documento</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.nrDocumento}</dd>
            <dt>
              <span id="regimeTributario">
                <Translate contentKey="ecommerceApplicationApp.emitente.regimeTributario">Regime Tributario</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.regimeTributario}</dd>
            <dt>
              <span id="versao">
                <Translate contentKey="ecommerceApplicationApp.emitente.versao">Versao</Translate>
              </span>
            </dt>
            <dd>{emitenteEntity.versao}</dd>
          </dl>
          <Button tag={Link} to="/entity/emitente" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/emitente/${emitenteEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ emitente }: IRootState) => ({
  emitenteEntity: emitente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmitenteDetail);
