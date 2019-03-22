import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './cliente.reducer';
import { ICliente } from 'app/shared/model/cliente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IClienteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IClienteState = IPaginationBaseState;

export class Cliente extends React.Component<IClienteProps, IClienteState> {
  state: IClienteState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { clienteList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="cliente-heading">
          <Translate contentKey="ecommerceApplicationApp.cliente.home.title">Clientes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.cliente.home.createLabel">Create new Cliente</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tpDocumentoEnum')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.tpDocumentoEnum">Tp Documento Enum</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idCliente')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.idCliente">Id Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nrDocumento')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.nrDocumento">Nr Documento</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xNome')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.xNome">X Nome</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xLgr')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.xLgr">X Lgr</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nRo')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.nRo">N Ro</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xCpl')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.xCpl">X Cpl</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xBairro')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.xBairro">X Bairro</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xMun')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.xMun">X Mun</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('cMun')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.cMun">C Mun</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('uF')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.uF">U F</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('cEP')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.cEP">C EP</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('cPais')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.cPais">C Pais</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xPais')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.xPais">X Pais</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fone')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.fone">Fone</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('email')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idEmitente')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.idEmitente">Id Emitente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('iE')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.iE">I E</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('iESUF')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.iESUF">I ESUF</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('versao')}>
                  <Translate contentKey="ecommerceApplicationApp.cliente.versao">Versao</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {clienteList.map((cliente, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cliente.id}`} color="link" size="sm">
                      {cliente.id}
                    </Button>
                  </td>
                  <td>{cliente.tpDocumentoEnum}</td>
                  <td>{cliente.idCliente}</td>
                  <td>{cliente.nrDocumento}</td>
                  <td>{cliente.xNome}</td>
                  <td>{cliente.xLgr}</td>
                  <td>{cliente.nRo}</td>
                  <td>{cliente.xCpl}</td>
                  <td>{cliente.xBairro}</td>
                  <td>{cliente.xMun}</td>
                  <td>{cliente.cMun}</td>
                  <td>{cliente.uF}</td>
                  <td>{cliente.cEP}</td>
                  <td>{cliente.cPais}</td>
                  <td>{cliente.xPais}</td>
                  <td>{cliente.fone}</td>
                  <td>{cliente.email}</td>
                  <td>{cliente.idEmitente}</td>
                  <td>{cliente.iE}</td>
                  <td>{cliente.iESUF}</td>
                  <td>{cliente.versao}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cliente.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cliente.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cliente.id}/delete`} color="danger" size="sm">
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
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ cliente }: IRootState) => ({
  clienteList: cliente.entities,
  totalItems: cliente.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Cliente);
