import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ipi.reducer';
import { IIpi } from 'app/shared/model/ipi.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIpiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class IpiDetail extends React.Component<IIpiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { ipiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.ipi.detail.title">Ipi</Translate> [<b>{ipiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idIpi">
                <Translate contentKey="ecommerceApplicationApp.ipi.idIpi">Id Ipi</Translate>
              </span>
            </dt>
            <dd>{ipiEntity.idIpi}</dd>
            <dt>
              <span id="clEnq">
                <Translate contentKey="ecommerceApplicationApp.ipi.clEnq">Cl Enq</Translate>
              </span>
            </dt>
            <dd>{ipiEntity.clEnq}</dd>
            <dt>
              <span id="cNPJProd">
                <Translate contentKey="ecommerceApplicationApp.ipi.cNPJProd">C NPJ Prod</Translate>
              </span>
            </dt>
            <dd>{ipiEntity.cNPJProd}</dd>
            <dt>
              <span id="cEnq">
                <Translate contentKey="ecommerceApplicationApp.ipi.cEnq">C Enq</Translate>
              </span>
            </dt>
            <dd>{ipiEntity.cEnq}</dd>
            <dt>
              <span id="idProduto">
                <Translate contentKey="ecommerceApplicationApp.ipi.idProduto">Id Produto</Translate>
              </span>
            </dt>
            <dd>{ipiEntity.idProduto}</dd>
          </dl>
          <Button tag={Link} to="/entity/ipi" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ipi/${ipiEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ ipi }: IRootState) => ({
  ipiEntity: ipi.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IpiDetail);
