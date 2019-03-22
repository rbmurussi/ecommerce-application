import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './transportadora.reducer';
import { ITransportadora } from 'app/shared/model/transportadora.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITransportadoraDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TransportadoraDetail extends React.Component<ITransportadoraDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { transportadoraEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.transportadora.detail.title">Transportadora</Translate> [<b>
              {transportadoraEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idTransportadora">
                <Translate contentKey="ecommerceApplicationApp.transportadora.idTransportadora">Id Transportadora</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.idTransportadora}</dd>
            <dt>
              <span id="tpDocumentoEnum">
                <Translate contentKey="ecommerceApplicationApp.transportadora.tpDocumentoEnum">Tp Documento Enum</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.tpDocumentoEnum}</dd>
            <dt>
              <span id="nrDocumento">
                <Translate contentKey="ecommerceApplicationApp.transportadora.nrDocumento">Nr Documento</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.nrDocumento}</dd>
            <dt>
              <span id="xNome">
                <Translate contentKey="ecommerceApplicationApp.transportadora.xNome">X Nome</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.xNome}</dd>
            <dt>
              <span id="iE">
                <Translate contentKey="ecommerceApplicationApp.transportadora.iE">I E</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.iE}</dd>
            <dt>
              <span id="xEnder">
                <Translate contentKey="ecommerceApplicationApp.transportadora.xEnder">X Ender</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.xEnder}</dd>
            <dt>
              <span id="uF">
                <Translate contentKey="ecommerceApplicationApp.transportadora.uF">U F</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.uF}</dd>
            <dt>
              <span id="xMun">
                <Translate contentKey="ecommerceApplicationApp.transportadora.xMun">X Mun</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.xMun}</dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.transportadora.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.idEmitente}</dd>
            <dt>
              <span id="versao">
                <Translate contentKey="ecommerceApplicationApp.transportadora.versao">Versao</Translate>
              </span>
            </dt>
            <dd>{transportadoraEntity.versao}</dd>
          </dl>
          <Button tag={Link} to="/entity/transportadora" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/transportadora/${transportadoraEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ transportadora }: IRootState) => ({
  transportadoraEntity: transportadora.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TransportadoraDetail);
