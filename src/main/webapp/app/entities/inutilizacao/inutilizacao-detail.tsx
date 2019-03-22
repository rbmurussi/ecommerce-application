import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './inutilizacao.reducer';
import { IInutilizacao } from 'app/shared/model/inutilizacao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInutilizacaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InutilizacaoDetail extends React.Component<IInutilizacaoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { inutilizacaoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.inutilizacao.detail.title">Inutilizacao</Translate> [<b>
              {inutilizacaoEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idInutilizacao">
                <Translate contentKey="ecommerceApplicationApp.inutilizacao.idInutilizacao">Id Inutilizacao</Translate>
              </span>
            </dt>
            <dd>{inutilizacaoEntity.idInutilizacao}</dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.inutilizacao.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{inutilizacaoEntity.idEmitente}</dd>
            <dt>
              <span id="serie">
                <Translate contentKey="ecommerceApplicationApp.inutilizacao.serie">Serie</Translate>
              </span>
            </dt>
            <dd>{inutilizacaoEntity.serie}</dd>
            <dt>
              <span id="numeroInicial">
                <Translate contentKey="ecommerceApplicationApp.inutilizacao.numeroInicial">Numero Inicial</Translate>
              </span>
            </dt>
            <dd>{inutilizacaoEntity.numeroInicial}</dd>
            <dt>
              <span id="numeroFinal">
                <Translate contentKey="ecommerceApplicationApp.inutilizacao.numeroFinal">Numero Final</Translate>
              </span>
            </dt>
            <dd>{inutilizacaoEntity.numeroFinal}</dd>
            <dt>
              <span id="protocoloXml">
                <Translate contentKey="ecommerceApplicationApp.inutilizacao.protocoloXml">Protocolo Xml</Translate>
              </span>
            </dt>
            <dd>
              {inutilizacaoEntity.protocoloXml ? (
                <div>
                  <a onClick={openFile(inutilizacaoEntity.protocoloXmlContentType, inutilizacaoEntity.protocoloXml)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {inutilizacaoEntity.protocoloXmlContentType}, {byteSize(inutilizacaoEntity.protocoloXml)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="dataInutilizacao">
                <Translate contentKey="ecommerceApplicationApp.inutilizacao.dataInutilizacao">Data Inutilizacao</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={inutilizacaoEntity.dataInutilizacao} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/inutilizacao" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/inutilizacao/${inutilizacaoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ inutilizacao }: IRootState) => ({
  inutilizacaoEntity: inutilizacao.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InutilizacaoDetail);
