import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './cancelamento.reducer';
import { ICancelamento } from 'app/shared/model/cancelamento.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICancelamentoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Cancelamento extends React.Component<ICancelamentoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { cancelamentoList, match } = this.props;
    return (
      <div>
        <h2 id="cancelamento-heading">
          <Translate contentKey="ecommerceApplicationApp.cancelamento.home.title">Cancelamentos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.cancelamento.home.createLabel">Create new Cancelamento</Translate>
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
                  <Translate contentKey="ecommerceApplicationApp.cancelamento.idNotalFiscal">Id Notal Fiscal</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.cancelamento.numeroProtocolo">Numero Protocolo</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.cancelamento.protocolo">Protocolo</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.cancelamento.dataProtocolo">Data Protocolo</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.cancelamento.codigoErro">Codigo Erro</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.cancelamento.mensagemErro">Mensagem Erro</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.cancelamento.justificativa">Justificativa</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cancelamentoList.map((cancelamento, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cancelamento.id}`} color="link" size="sm">
                      {cancelamento.id}
                    </Button>
                  </td>
                  <td>{cancelamento.idNotalFiscal}</td>
                  <td>{cancelamento.numeroProtocolo}</td>
                  <td>
                    {cancelamento.protocolo ? (
                      <div>
                        <a onClick={openFile(cancelamento.protocoloContentType, cancelamento.protocolo)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                          &nbsp;
                        </a>
                        <span>
                          {cancelamento.protocoloContentType}, {byteSize(cancelamento.protocolo)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    <TextFormat type="date" value={cancelamento.dataProtocolo} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cancelamento.codigoErro}</td>
                  <td>{cancelamento.mensagemErro}</td>
                  <td>{cancelamento.justificativa}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cancelamento.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cancelamento.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cancelamento.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ cancelamento }: IRootState) => ({
  cancelamentoList: cancelamento.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Cancelamento);
