import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pesquisa.reducer';
import { IPesquisa } from 'app/shared/model/pesquisa.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPesquisaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Pesquisa extends React.Component<IPesquisaProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { pesquisaList, match } = this.props;
    return (
      <div>
        <h2 id="pesquisa-heading">
          <Translate contentKey="ecommerceApplicationApp.pesquisa.home.title">Pesquisas</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.pesquisa.home.createLabel">Create new Pesquisa</Translate>
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
                  <Translate contentKey="ecommerceApplicationApp.pesquisa.idPesquisa">Id Pesquisa</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.pesquisa.campoEnum">Campo Enum</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.pesquisa.valor">Valor</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.pesquisa.idEmitente">Id Emitente</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.pesquisa.telaEnum">Tela Enum</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pesquisaList.map((pesquisa, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pesquisa.id}`} color="link" size="sm">
                      {pesquisa.id}
                    </Button>
                  </td>
                  <td>{pesquisa.idPesquisa}</td>
                  <td>{pesquisa.campoEnum}</td>
                  <td>{pesquisa.valor}</td>
                  <td>{pesquisa.idEmitente}</td>
                  <td>{pesquisa.telaEnum}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pesquisa.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pesquisa.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pesquisa.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ pesquisa }: IRootState) => ({
  pesquisaList: pesquisa.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Pesquisa);
