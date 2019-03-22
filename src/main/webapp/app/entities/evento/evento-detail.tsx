import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './evento.reducer';
import { IEvento } from 'app/shared/model/evento.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEventoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EventoDetail extends React.Component<IEventoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { eventoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.evento.detail.title">Evento</Translate> [<b>{eventoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idEvento">
                <Translate contentKey="ecommerceApplicationApp.evento.idEvento">Id Evento</Translate>
              </span>
            </dt>
            <dd>{eventoEntity.idEvento}</dd>
            <dt>
              <span id="idNotalFiscal">
                <Translate contentKey="ecommerceApplicationApp.evento.idNotalFiscal">Id Notal Fiscal</Translate>
              </span>
            </dt>
            <dd>{eventoEntity.idNotalFiscal}</dd>
            <dt>
              <span id="tpEvento">
                <Translate contentKey="ecommerceApplicationApp.evento.tpEvento">Tp Evento</Translate>
              </span>
            </dt>
            <dd>{eventoEntity.tpEvento}</dd>
            <dt>
              <span id="nSeqEvento">
                <Translate contentKey="ecommerceApplicationApp.evento.nSeqEvento">N Seq Evento</Translate>
              </span>
            </dt>
            <dd>{eventoEntity.nSeqEvento}</dd>
            <dt>
              <span id="dataEvento">
                <Translate contentKey="ecommerceApplicationApp.evento.dataEvento">Data Evento</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={eventoEntity.dataEvento} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="numProtocolo">
                <Translate contentKey="ecommerceApplicationApp.evento.numProtocolo">Num Protocolo</Translate>
              </span>
            </dt>
            <dd>{eventoEntity.numProtocolo}</dd>
            <dt>
              <span id="xmlProc">
                <Translate contentKey="ecommerceApplicationApp.evento.xmlProc">Xml Proc</Translate>
              </span>
            </dt>
            <dd>
              {eventoEntity.xmlProc ? (
                <div>
                  <a onClick={openFile(eventoEntity.xmlProcContentType, eventoEntity.xmlProc)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {eventoEntity.xmlProcContentType}, {byteSize(eventoEntity.xmlProc)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="dataRegEvento">
                <Translate contentKey="ecommerceApplicationApp.evento.dataRegEvento">Data Reg Evento</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={eventoEntity.dataRegEvento} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/evento" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/evento/${eventoEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ evento }: IRootState) => ({
  eventoEntity: evento.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EventoDetail);
