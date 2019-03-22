import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './certificado-info.reducer';
import { ICertificadoInfo } from 'app/shared/model/certificado-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICertificadoInfoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ICertificadoInfoState = IPaginationBaseState;

export class CertificadoInfo extends React.Component<ICertificadoInfoProps, ICertificadoInfoState> {
  state: ICertificadoInfoState = {
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
    const { certificadoInfoList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="certificado-info-heading">
          <Translate contentKey="ecommerceApplicationApp.certificadoInfo.home.title">Certificado Infos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.certificadoInfo.home.createLabel">Create new Certificado Info</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idCertificadoInfo')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.idCertificadoInfo">Id Certificado Info</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idEmitente')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.idEmitente">Id Emitente</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('alias')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.alias">Alias</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nome')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.nome">Nome</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('autoridadeCertificadora')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.autoridadeCertificadora">
                    Autoridade Certificadora
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('cNPJ')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.cNPJ">C NPJ</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('caminho')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.caminho">Caminho</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tipoCertificado')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.tipoCertificado">Tipo Certificado</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dataUtilizacao')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.dataUtilizacao">Data Utilizacao</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dataValidade')}>
                  <Translate contentKey="ecommerceApplicationApp.certificadoInfo.dataValidade">Data Validade</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {certificadoInfoList.map((certificadoInfo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${certificadoInfo.id}`} color="link" size="sm">
                      {certificadoInfo.id}
                    </Button>
                  </td>
                  <td>{certificadoInfo.idCertificadoInfo}</td>
                  <td>{certificadoInfo.idEmitente}</td>
                  <td>{certificadoInfo.alias}</td>
                  <td>{certificadoInfo.nome}</td>
                  <td>{certificadoInfo.autoridadeCertificadora}</td>
                  <td>{certificadoInfo.cNPJ}</td>
                  <td>{certificadoInfo.caminho}</td>
                  <td>{certificadoInfo.tipoCertificado}</td>
                  <td>
                    <TextFormat type="date" value={certificadoInfo.dataUtilizacao} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={certificadoInfo.dataValidade} format={APP_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${certificadoInfo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${certificadoInfo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${certificadoInfo.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ certificadoInfo }: IRootState) => ({
  certificadoInfoList: certificadoInfo.entities,
  totalItems: certificadoInfo.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CertificadoInfo);
