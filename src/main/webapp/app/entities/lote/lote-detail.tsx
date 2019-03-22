import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './lote.reducer';
import { ILote } from 'app/shared/model/lote.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LoteDetail extends React.Component<ILoteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { loteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.lote.detail.title">Lote</Translate> [<b>{loteEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idLote">
                <Translate contentKey="ecommerceApplicationApp.lote.idLote">Id Lote</Translate>
              </span>
            </dt>
            <dd>{loteEntity.idLote}</dd>
            <dt>
              <span id="cNPJTransmissao">
                <Translate contentKey="ecommerceApplicationApp.lote.cNPJTransmissao">C NPJ Transmissao</Translate>
              </span>
            </dt>
            <dd>{loteEntity.cNPJTransmissao}</dd>
            <dt>
              <span id="dataTransmissao">
                <Translate contentKey="ecommerceApplicationApp.lote.dataTransmissao">Data Transmissao</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={loteEntity.dataTransmissao} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="numRecibo">
                <Translate contentKey="ecommerceApplicationApp.lote.numRecibo">Num Recibo</Translate>
              </span>
            </dt>
            <dd>{loteEntity.numRecibo}</dd>
            <dt>
              <span id="xmlRetorno">
                <Translate contentKey="ecommerceApplicationApp.lote.xmlRetorno">Xml Retorno</Translate>
              </span>
            </dt>
            <dd>
              {loteEntity.xmlRetorno ? (
                <div>
                  <a onClick={openFile(loteEntity.xmlRetornoContentType, loteEntity.xmlRetorno)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {loteEntity.xmlRetornoContentType}, {byteSize(loteEntity.xmlRetorno)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="dataProc">
                <Translate contentKey="ecommerceApplicationApp.lote.dataProc">Data Proc</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={loteEntity.dataProc} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/lote" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/lote/${loteEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ lote }: IRootState) => ({
  loteEntity: lote.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoteDetail);
