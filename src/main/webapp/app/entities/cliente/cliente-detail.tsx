import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cliente.reducer';
import { ICliente } from 'app/shared/model/cliente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClienteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ClienteDetail extends React.Component<IClienteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { clienteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.cliente.detail.title">Cliente</Translate> [<b>{clienteEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tpDocumentoEnum">
                <Translate contentKey="ecommerceApplicationApp.cliente.tpDocumentoEnum">Tp Documento Enum</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.tpDocumentoEnum}</dd>
            <dt>
              <span id="idCliente">
                <Translate contentKey="ecommerceApplicationApp.cliente.idCliente">Id Cliente</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.idCliente}</dd>
            <dt>
              <span id="nrDocumento">
                <Translate contentKey="ecommerceApplicationApp.cliente.nrDocumento">Nr Documento</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.nrDocumento}</dd>
            <dt>
              <span id="xNome">
                <Translate contentKey="ecommerceApplicationApp.cliente.xNome">X Nome</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.xNome}</dd>
            <dt>
              <span id="xLgr">
                <Translate contentKey="ecommerceApplicationApp.cliente.xLgr">X Lgr</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.xLgr}</dd>
            <dt>
              <span id="nRo">
                <Translate contentKey="ecommerceApplicationApp.cliente.nRo">N Ro</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.nRo}</dd>
            <dt>
              <span id="xCpl">
                <Translate contentKey="ecommerceApplicationApp.cliente.xCpl">X Cpl</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.xCpl}</dd>
            <dt>
              <span id="xBairro">
                <Translate contentKey="ecommerceApplicationApp.cliente.xBairro">X Bairro</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.xBairro}</dd>
            <dt>
              <span id="xMun">
                <Translate contentKey="ecommerceApplicationApp.cliente.xMun">X Mun</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.xMun}</dd>
            <dt>
              <span id="cMun">
                <Translate contentKey="ecommerceApplicationApp.cliente.cMun">C Mun</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.cMun}</dd>
            <dt>
              <span id="uF">
                <Translate contentKey="ecommerceApplicationApp.cliente.uF">U F</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.uF}</dd>
            <dt>
              <span id="cEP">
                <Translate contentKey="ecommerceApplicationApp.cliente.cEP">C EP</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.cEP}</dd>
            <dt>
              <span id="cPais">
                <Translate contentKey="ecommerceApplicationApp.cliente.cPais">C Pais</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.cPais}</dd>
            <dt>
              <span id="xPais">
                <Translate contentKey="ecommerceApplicationApp.cliente.xPais">X Pais</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.xPais}</dd>
            <dt>
              <span id="fone">
                <Translate contentKey="ecommerceApplicationApp.cliente.fone">Fone</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.fone}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="ecommerceApplicationApp.cliente.email">Email</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.email}</dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.cliente.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.idEmitente}</dd>
            <dt>
              <span id="iE">
                <Translate contentKey="ecommerceApplicationApp.cliente.iE">I E</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.iE}</dd>
            <dt>
              <span id="iESUF">
                <Translate contentKey="ecommerceApplicationApp.cliente.iESUF">I ESUF</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.iESUF}</dd>
            <dt>
              <span id="versao">
                <Translate contentKey="ecommerceApplicationApp.cliente.versao">Versao</Translate>
              </span>
            </dt>
            <dd>{clienteEntity.versao}</dd>
          </dl>
          <Button tag={Link} to="/entity/cliente" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/cliente/${clienteEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ cliente }: IRootState) => ({
  clienteEntity: cliente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ClienteDetail);
