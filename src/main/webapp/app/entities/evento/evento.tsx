import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './evento.reducer';
import { IEvento } from 'app/shared/model/evento.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEventoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Evento extends React.Component<IEventoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { eventoList, match } = this.props;
    return (
      <div>
        <h2 id="evento-heading">
          <Translate contentKey="ecommerceApplicationApp.evento.home.title">Eventos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.evento.home.createLabel">Create new Evento</Translate>
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
                  <Translate contentKey="ecommerceApplicationApp.evento.idEvento">Id Evento</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.evento.idNotalFiscal">Id Notal Fiscal</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.evento.tpEvento">Tp Evento</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.evento.nSeqEvento">N Seq Evento</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.evento.dataEvento">Data Evento</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.evento.numProtocolo">Num Protocolo</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.evento.xmlProc">Xml Proc</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.evento.dataRegEvento">Data Reg Evento</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {eventoList.map((evento, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${evento.id}`} color="link" size="sm">
                      {evento.id}
                    </Button>
                  </td>
                  <td>{evento.idEvento}</td>
                  <td>{evento.idNotalFiscal}</td>
                  <td>{evento.tpEvento}</td>
                  <td>{evento.nSeqEvento}</td>
                  <td>
                    <TextFormat type="date" value={evento.dataEvento} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{evento.numProtocolo}</td>
                  <td>
                    {evento.xmlProc ? (
                      <div>
                        <a onClick={openFile(evento.xmlProcContentType, evento.xmlProc)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                          &nbsp;
                        </a>
                        <span>
                          {evento.xmlProcContentType}, {byteSize(evento.xmlProc)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    <TextFormat type="date" value={evento.dataRegEvento} format={APP_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${evento.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${evento.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${evento.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ evento }: IRootState) => ({
  eventoList: evento.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Evento);
