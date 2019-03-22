import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './transportadora.reducer';
import { ITransportadora } from 'app/shared/model/transportadora.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ITransportadoraProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ITransportadoraState = IPaginationBaseState;

export class Transportadora extends React.Component<ITransportadoraProps, ITransportadoraState> {
  state: ITransportadoraState = {
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
    const { transportadoraList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="transportadora-heading">
          <Translate contentKey="ecommerceApplicationApp.transportadora.home.title">Transportadoras</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.transportadora.home.createLabel">Create new Transportadora</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idTransportadora')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.idTransportadora">Id Transportadora</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tpDocumentoEnum')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.tpDocumentoEnum">Tp Documento Enum</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nrDocumento')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.nrDocumento">Nr Documento</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xNome')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.xNome">X Nome</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('iE')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.iE">I E</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xEnder')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.xEnder">X Ender</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('uF')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.uF">U F</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xMun')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.xMun">X Mun</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idEmitente')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.idEmitente">Id Emitente</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('versao')}>
                  <Translate contentKey="ecommerceApplicationApp.transportadora.versao">Versao</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {transportadoraList.map((transportadora, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${transportadora.id}`} color="link" size="sm">
                      {transportadora.id}
                    </Button>
                  </td>
                  <td>{transportadora.idTransportadora}</td>
                  <td>{transportadora.tpDocumentoEnum}</td>
                  <td>{transportadora.nrDocumento}</td>
                  <td>{transportadora.xNome}</td>
                  <td>{transportadora.iE}</td>
                  <td>{transportadora.xEnder}</td>
                  <td>{transportadora.uF}</td>
                  <td>{transportadora.xMun}</td>
                  <td>{transportadora.idEmitente}</td>
                  <td>{transportadora.versao}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${transportadora.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${transportadora.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${transportadora.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ transportadora }: IRootState) => ({
  transportadoraList: transportadora.entities,
  totalItems: transportadora.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Transportadora);
