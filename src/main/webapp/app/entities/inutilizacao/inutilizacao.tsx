import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './inutilizacao.reducer';
import { IInutilizacao } from 'app/shared/model/inutilizacao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInutilizacaoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Inutilizacao extends React.Component<IInutilizacaoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { inutilizacaoList, match } = this.props;
    return (
      <div>
        <h2 id="inutilizacao-heading">
          <Translate contentKey="ecommerceApplicationApp.inutilizacao.home.title">Inutilizacaos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.inutilizacao.home.createLabel">Create new Inutilizacao</Translate>
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
                  <Translate contentKey="ecommerceApplicationApp.inutilizacao.idInutilizacao">Id Inutilizacao</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.inutilizacao.idEmitente">Id Emitente</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.inutilizacao.serie">Serie</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.inutilizacao.numeroInicial">Numero Inicial</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.inutilizacao.numeroFinal">Numero Final</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.inutilizacao.protocoloXml">Protocolo Xml</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.inutilizacao.dataInutilizacao">Data Inutilizacao</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {inutilizacaoList.map((inutilizacao, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${inutilizacao.id}`} color="link" size="sm">
                      {inutilizacao.id}
                    </Button>
                  </td>
                  <td>{inutilizacao.idInutilizacao}</td>
                  <td>{inutilizacao.idEmitente}</td>
                  <td>{inutilizacao.serie}</td>
                  <td>{inutilizacao.numeroInicial}</td>
                  <td>{inutilizacao.numeroFinal}</td>
                  <td>
                    {inutilizacao.protocoloXml ? (
                      <div>
                        <a onClick={openFile(inutilizacao.protocoloXmlContentType, inutilizacao.protocoloXml)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                          &nbsp;
                        </a>
                        <span>
                          {inutilizacao.protocoloXmlContentType}, {byteSize(inutilizacao.protocoloXml)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    <TextFormat type="date" value={inutilizacao.dataInutilizacao} format={APP_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${inutilizacao.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${inutilizacao.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${inutilizacao.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ inutilizacao }: IRootState) => ({
  inutilizacaoList: inutilizacao.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Inutilizacao);
