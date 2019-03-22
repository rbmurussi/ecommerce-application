import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  openFile,
  byteSize,
  Translate,
  ICrudGetAllAction,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './emitente.reducer';
import { IEmitente } from 'app/shared/model/emitente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IEmitenteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IEmitenteState = IPaginationBaseState;

export class Emitente extends React.Component<IEmitenteProps, IEmitenteState> {
  state: IEmitenteState = {
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
    const { emitenteList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="emitente-heading">
          <Translate contentKey="ecommerceApplicationApp.emitente.home.title">Emitentes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.emitente.home.createLabel">Create new Emitente</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idEmitente')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.idEmitente">Id Emitente</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xNome')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.xNome">X Nome</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xFant')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.xFant">X Fant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xLgr')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.xLgr">X Lgr</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nRo')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.nRo">N Ro</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xCpl')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.xCpl">X Cpl</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xBairro')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.xBairro">X Bairro</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('cMun')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.cMun">C Mun</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xMun')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.xMun">X Mun</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('uF')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.uF">U F</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('cEP')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.cEP">C EP</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('cPais')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.cPais">C Pais</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xPais')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.xPais">X Pais</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fone')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.fone">Fone</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('iE')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.iE">I E</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('iEST')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.iEST">I EST</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('iM')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.iM">I M</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('cNAE')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.cNAE">C NAE</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('logotipo')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.logotipo">Logotipo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tpDocumentoEnum')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.tpDocumentoEnum">Tp Documento Enum</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nrDocumento')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.nrDocumento">Nr Documento</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('regimeTributario')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.regimeTributario">Regime Tributario</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('versao')}>
                  <Translate contentKey="ecommerceApplicationApp.emitente.versao">Versao</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {emitenteList.map((emitente, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${emitente.id}`} color="link" size="sm">
                      {emitente.id}
                    </Button>
                  </td>
                  <td>{emitente.idEmitente}</td>
                  <td>{emitente.xNome}</td>
                  <td>{emitente.xFant}</td>
                  <td>{emitente.xLgr}</td>
                  <td>{emitente.nRo}</td>
                  <td>{emitente.xCpl}</td>
                  <td>{emitente.xBairro}</td>
                  <td>{emitente.cMun}</td>
                  <td>{emitente.xMun}</td>
                  <td>{emitente.uF}</td>
                  <td>{emitente.cEP}</td>
                  <td>{emitente.cPais}</td>
                  <td>{emitente.xPais}</td>
                  <td>{emitente.fone}</td>
                  <td>{emitente.iE}</td>
                  <td>{emitente.iEST}</td>
                  <td>{emitente.iM}</td>
                  <td>{emitente.cNAE}</td>
                  <td>
                    {emitente.logotipo ? (
                      <div>
                        <a onClick={openFile(emitente.logotipoContentType, emitente.logotipo)}>
                          <img src={`data:${emitente.logotipoContentType};base64,${emitente.logotipo}`} style={{ maxHeight: '30px' }} />
                          &nbsp;
                        </a>
                        <span>
                          {emitente.logotipoContentType}, {byteSize(emitente.logotipo)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{emitente.tpDocumentoEnum}</td>
                  <td>{emitente.nrDocumento}</td>
                  <td>{emitente.regimeTributario}</td>
                  <td>{emitente.versao}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${emitente.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${emitente.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${emitente.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ emitente }: IRootState) => ({
  emitenteList: emitente.entities,
  totalItems: emitente.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Emitente);
