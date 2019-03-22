import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './numeracao.reducer';
import { INumeracao } from 'app/shared/model/numeracao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INumeracaoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Numeracao extends React.Component<INumeracaoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { numeracaoList, match } = this.props;
    return (
      <div>
        <h2 id="numeracao-heading">
          <Translate contentKey="ecommerceApplicationApp.numeracao.home.title">Numeracaos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.numeracao.home.createLabel">Create new Numeracao</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.numeracao.idNumeracao">Id Numeracao</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.numeracao.serie">Serie</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.numeracao.numero">Numero</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.numeracao.ano">Ano</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.numeracao.dataSistema">Data Sistema</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.numeracao.idEmitente">Id Emitente</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {numeracaoList.map((numeracao, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${numeracao.id}`} color="link" size="sm">
                      {numeracao.id}
                    </Button>
                  </td>
                  <td>{numeracao.idNumeracao}</td>
                  <td>{numeracao.serie}</td>
                  <td>{numeracao.numero}</td>
                  <td>{numeracao.ano}</td>
                  <td>
                    <TextFormat type="date" value={numeracao.dataSistema} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{numeracao.idEmitente}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${numeracao.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${numeracao.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${numeracao.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ numeracao }: IRootState) => ({
  numeracaoList: numeracao.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Numeracao);
