import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pesquisa.reducer';
import { IPesquisa } from 'app/shared/model/pesquisa.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPesquisaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PesquisaDetail extends React.Component<IPesquisaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pesquisaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.pesquisa.detail.title">Pesquisa</Translate> [<b>{pesquisaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idPesquisa">
                <Translate contentKey="ecommerceApplicationApp.pesquisa.idPesquisa">Id Pesquisa</Translate>
              </span>
            </dt>
            <dd>{pesquisaEntity.idPesquisa}</dd>
            <dt>
              <span id="campoEnum">
                <Translate contentKey="ecommerceApplicationApp.pesquisa.campoEnum">Campo Enum</Translate>
              </span>
            </dt>
            <dd>{pesquisaEntity.campoEnum}</dd>
            <dt>
              <span id="valor">
                <Translate contentKey="ecommerceApplicationApp.pesquisa.valor">Valor</Translate>
              </span>
            </dt>
            <dd>{pesquisaEntity.valor}</dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.pesquisa.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{pesquisaEntity.idEmitente}</dd>
            <dt>
              <span id="telaEnum">
                <Translate contentKey="ecommerceApplicationApp.pesquisa.telaEnum">Tela Enum</Translate>
              </span>
            </dt>
            <dd>{pesquisaEntity.telaEnum}</dd>
          </dl>
          <Button tag={Link} to="/entity/pesquisa" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pesquisa/${pesquisaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ pesquisa }: IRootState) => ({
  pesquisaEntity: pesquisa.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PesquisaDetail);
