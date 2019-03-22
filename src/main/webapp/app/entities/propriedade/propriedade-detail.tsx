import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './propriedade.reducer';
import { IPropriedade } from 'app/shared/model/propriedade.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPropriedadeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PropriedadeDetail extends React.Component<IPropriedadeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { propriedadeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.propriedade.detail.title">Propriedade</Translate> [<b>{propriedadeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idPropriedade">
                <Translate contentKey="ecommerceApplicationApp.propriedade.idPropriedade">Id Propriedade</Translate>
              </span>
            </dt>
            <dd>{propriedadeEntity.idPropriedade}</dd>
            <dt>
              <span id="propriedadeEnum">
                <Translate contentKey="ecommerceApplicationApp.propriedade.propriedadeEnum">Propriedade Enum</Translate>
              </span>
            </dt>
            <dd>{propriedadeEntity.propriedadeEnum}</dd>
            <dt>
              <span id="valor">
                <Translate contentKey="ecommerceApplicationApp.propriedade.valor">Valor</Translate>
              </span>
            </dt>
            <dd>{propriedadeEntity.valor}</dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.propriedade.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{propriedadeEntity.idEmitente}</dd>
          </dl>
          <Button tag={Link} to="/entity/propriedade" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/propriedade/${propriedadeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ propriedade }: IRootState) => ({
  propriedadeEntity: propriedade.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PropriedadeDetail);
