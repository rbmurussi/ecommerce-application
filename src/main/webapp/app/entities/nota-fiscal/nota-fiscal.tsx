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
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './nota-fiscal.reducer';
import { INotaFiscal } from 'app/shared/model/nota-fiscal.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface INotaFiscalProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type INotaFiscalState = IPaginationBaseState;

export class NotaFiscal extends React.Component<INotaFiscalProps, INotaFiscalState> {
  state: INotaFiscalState = {
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
    const { notaFiscalList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="nota-fiscal-heading">
          <Translate contentKey="ecommerceApplicationApp.notaFiscal.home.title">Nota Fiscals</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.notaFiscal.home.createLabel">Create new Nota Fiscal</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idNotalFiscal')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.idNotalFiscal">Id Notal Fiscal</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('numero')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.numero">Numero</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('serie')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.serie">Serie</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('modelo')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.modelo">Modelo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('situacao')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.situacao">Situacao</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('mes')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.mes">Mes</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ano')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.ano">Ano</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tipoEmissao')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.tipoEmissao">Tipo Emissao</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dataEmissao')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataEmissao">Data Emissao</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dataAutorizacao')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataAutorizacao">Data Autorizacao</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('codigoNumericoChaveAcesso')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoNumericoChaveAcesso">
                    Codigo Numerico Chave Acesso
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('digitoChaveAcesso')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.digitoChaveAcesso">Digito Chave Acesso</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('autorizacaoExportadaXML')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.autorizacaoExportadaXML">Autorizacao Exportada XML</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('documentoDest')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.documentoDest">Documento Dest</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('uFDest')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.uFDest">U F Dest</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('numeroRecibo')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.numeroRecibo">Numero Recibo</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('danfeImpresso')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.danfeImpresso">Danfe Impresso</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('docXML')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.docXML">Doc XML</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dataSistema')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataSistema">Data Sistema</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('protocolo')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.protocolo">Protocolo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('numeroProtocolo')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.numeroProtocolo">Numero Protocolo</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dataProtocolo')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataProtocolo">Data Protocolo</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('codigoUFEmitente')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoUFEmitente">Codigo UF Emitente</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idEmitente')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.idEmitente">Id Emitente</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('idLote')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.idLote">Id Lote</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('codigoErro')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoErro">Codigo Erro</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('mensagemErro')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.mensagemErro">Mensagem Erro</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('versao')}>
                  <Translate contentKey="ecommerceApplicationApp.notaFiscal.versao">Versao</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {notaFiscalList.map((notaFiscal, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${notaFiscal.id}`} color="link" size="sm">
                      {notaFiscal.id}
                    </Button>
                  </td>
                  <td>{notaFiscal.idNotalFiscal}</td>
                  <td>{notaFiscal.numero}</td>
                  <td>{notaFiscal.serie}</td>
                  <td>{notaFiscal.modelo}</td>
                  <td>{notaFiscal.situacao}</td>
                  <td>{notaFiscal.mes}</td>
                  <td>{notaFiscal.ano}</td>
                  <td>{notaFiscal.tipoEmissao}</td>
                  <td>
                    <TextFormat type="date" value={notaFiscal.dataEmissao} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={notaFiscal.dataAutorizacao} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{notaFiscal.codigoNumericoChaveAcesso}</td>
                  <td>{notaFiscal.digitoChaveAcesso}</td>
                  <td>{notaFiscal.autorizacaoExportadaXML}</td>
                  <td>{notaFiscal.documentoDest}</td>
                  <td>{notaFiscal.uFDest}</td>
                  <td>{notaFiscal.numeroRecibo}</td>
                  <td>{notaFiscal.danfeImpresso}</td>
                  <td>
                    {notaFiscal.docXML ? (
                      <div>
                        <a onClick={openFile(notaFiscal.docXMLContentType, notaFiscal.docXML)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                          &nbsp;
                        </a>
                        <span>
                          {notaFiscal.docXMLContentType}, {byteSize(notaFiscal.docXML)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    <TextFormat type="date" value={notaFiscal.dataSistema} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    {notaFiscal.protocolo ? (
                      <div>
                        <a onClick={openFile(notaFiscal.protocoloContentType, notaFiscal.protocolo)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                          &nbsp;
                        </a>
                        <span>
                          {notaFiscal.protocoloContentType}, {byteSize(notaFiscal.protocolo)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{notaFiscal.numeroProtocolo}</td>
                  <td>
                    <TextFormat type="date" value={notaFiscal.dataProtocolo} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{notaFiscal.codigoUFEmitente}</td>
                  <td>{notaFiscal.idEmitente}</td>
                  <td>{notaFiscal.idLote}</td>
                  <td>{notaFiscal.codigoErro}</td>
                  <td>{notaFiscal.mensagemErro}</td>
                  <td>{notaFiscal.versao}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${notaFiscal.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${notaFiscal.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${notaFiscal.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ notaFiscal }: IRootState) => ({
  notaFiscalList: notaFiscal.entities,
  totalItems: notaFiscal.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NotaFiscal);
