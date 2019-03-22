import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './produto.reducer';
import { IProduto } from 'app/shared/model/produto.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProdutoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ProdutoDetail extends React.Component<IProdutoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { produtoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.produto.detail.title">Produto</Translate> [<b>{produtoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="cProd">
                <Translate contentKey="ecommerceApplicationApp.produto.cProd">C Prod</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.cProd}</dd>
            <dt>
              <span id="idProduto">
                <Translate contentKey="ecommerceApplicationApp.produto.idProduto">Id Produto</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.idProduto}</dd>
            <dt>
              <span id="xProd">
                <Translate contentKey="ecommerceApplicationApp.produto.xProd">X Prod</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.xProd}</dd>
            <dt>
              <span id="cEAN">
                <Translate contentKey="ecommerceApplicationApp.produto.cEAN">C EAN</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.cEAN}</dd>
            <dt>
              <span id="nCM">
                <Translate contentKey="ecommerceApplicationApp.produto.nCM">N CM</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.nCM}</dd>
            <dt>
              <span id="exTipi">
                <Translate contentKey="ecommerceApplicationApp.produto.exTipi">Ex Tipi</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.exTipi}</dd>
            <dt>
              <span id="genero">
                <Translate contentKey="ecommerceApplicationApp.produto.genero">Genero</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.genero}</dd>
            <dt>
              <span id="uCom">
                <Translate contentKey="ecommerceApplicationApp.produto.uCom">U Com</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.uCom}</dd>
            <dt>
              <span id="cEANTrib">
                <Translate contentKey="ecommerceApplicationApp.produto.cEANTrib">C EAN Trib</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.cEANTrib}</dd>
            <dt>
              <span id="uTrib">
                <Translate contentKey="ecommerceApplicationApp.produto.uTrib">U Trib</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.uTrib}</dd>
            <dt>
              <span id="vUNCom">
                <Translate contentKey="ecommerceApplicationApp.produto.vUNCom">V UN Com</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.vUNCom}</dd>
            <dt>
              <span id="vUNTrib">
                <Translate contentKey="ecommerceApplicationApp.produto.vUNTrib">V UN Trib</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.vUNTrib}</dd>
            <dt>
              <span id="qTrib">
                <Translate contentKey="ecommerceApplicationApp.produto.qTrib">Q Trib</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.qTrib}</dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.produto.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.idEmitente}</dd>
            <dt>
              <span id="versao">
                <Translate contentKey="ecommerceApplicationApp.produto.versao">Versao</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.versao}</dd>
            <dt>
              <span id="cest">
                <Translate contentKey="ecommerceApplicationApp.produto.cest">Cest</Translate>
              </span>
            </dt>
            <dd>{produtoEntity.cest}</dd>
          </dl>
          <Button tag={Link} to="/entity/produto" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/produto/${produtoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ produto }: IRootState) => ({
  produtoEntity: produto.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProdutoDetail);
