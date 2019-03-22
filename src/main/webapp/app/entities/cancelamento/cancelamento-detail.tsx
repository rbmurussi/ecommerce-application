import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cancelamento.reducer';
import { ICancelamento } from 'app/shared/model/cancelamento.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICancelamentoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CancelamentoDetail extends React.Component<ICancelamentoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { cancelamentoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.cancelamento.detail.title">Cancelamento</Translate> [<b>
              {cancelamentoEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idNotalFiscal">
                <Translate contentKey="ecommerceApplicationApp.cancelamento.idNotalFiscal">Id Notal Fiscal</Translate>
              </span>
            </dt>
            <dd>{cancelamentoEntity.idNotalFiscal}</dd>
            <dt>
              <span id="numeroProtocolo">
                <Translate contentKey="ecommerceApplicationApp.cancelamento.numeroProtocolo">Numero Protocolo</Translate>
              </span>
            </dt>
            <dd>{cancelamentoEntity.numeroProtocolo}</dd>
            <dt>
              <span id="protocolo">
                <Translate contentKey="ecommerceApplicationApp.cancelamento.protocolo">Protocolo</Translate>
              </span>
            </dt>
            <dd>
              {cancelamentoEntity.protocolo ? (
                <div>
                  <a onClick={openFile(cancelamentoEntity.protocoloContentType, cancelamentoEntity.protocolo)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {cancelamentoEntity.protocoloContentType}, {byteSize(cancelamentoEntity.protocolo)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="dataProtocolo">
                <Translate contentKey="ecommerceApplicationApp.cancelamento.dataProtocolo">Data Protocolo</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={cancelamentoEntity.dataProtocolo} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="codigoErro">
                <Translate contentKey="ecommerceApplicationApp.cancelamento.codigoErro">Codigo Erro</Translate>
              </span>
            </dt>
            <dd>{cancelamentoEntity.codigoErro}</dd>
            <dt>
              <span id="mensagemErro">
                <Translate contentKey="ecommerceApplicationApp.cancelamento.mensagemErro">Mensagem Erro</Translate>
              </span>
            </dt>
            <dd>{cancelamentoEntity.mensagemErro}</dd>
            <dt>
              <span id="justificativa">
                <Translate contentKey="ecommerceApplicationApp.cancelamento.justificativa">Justificativa</Translate>
              </span>
            </dt>
            <dd>{cancelamentoEntity.justificativa}</dd>
          </dl>
          <Button tag={Link} to="/entity/cancelamento" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/cancelamento/${cancelamentoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ cancelamento }: IRootState) => ({
  cancelamentoEntity: cancelamento.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CancelamentoDetail);
