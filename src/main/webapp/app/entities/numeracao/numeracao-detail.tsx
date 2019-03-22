import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './numeracao.reducer';
import { INumeracao } from 'app/shared/model/numeracao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INumeracaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NumeracaoDetail extends React.Component<INumeracaoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { numeracaoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.numeracao.detail.title">Numeracao</Translate> [<b>{numeracaoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idNumeracao">
                <Translate contentKey="ecommerceApplicationApp.numeracao.idNumeracao">Id Numeracao</Translate>
              </span>
            </dt>
            <dd>{numeracaoEntity.idNumeracao}</dd>
            <dt>
              <span id="serie">
                <Translate contentKey="ecommerceApplicationApp.numeracao.serie">Serie</Translate>
              </span>
            </dt>
            <dd>{numeracaoEntity.serie}</dd>
            <dt>
              <span id="numero">
                <Translate contentKey="ecommerceApplicationApp.numeracao.numero">Numero</Translate>
              </span>
            </dt>
            <dd>{numeracaoEntity.numero}</dd>
            <dt>
              <span id="ano">
                <Translate contentKey="ecommerceApplicationApp.numeracao.ano">Ano</Translate>
              </span>
            </dt>
            <dd>{numeracaoEntity.ano}</dd>
            <dt>
              <span id="dataSistema">
                <Translate contentKey="ecommerceApplicationApp.numeracao.dataSistema">Data Sistema</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={numeracaoEntity.dataSistema} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.numeracao.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{numeracaoEntity.idEmitente}</dd>
          </dl>
          <Button tag={Link} to="/entity/numeracao" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/numeracao/${numeracaoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ numeracao }: IRootState) => ({
  numeracaoEntity: numeracao.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NumeracaoDetail);
