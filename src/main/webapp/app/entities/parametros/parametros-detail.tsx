import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './parametros.reducer';
import { IParametros } from 'app/shared/model/parametros.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IParametrosDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ParametrosDetail extends React.Component<IParametrosDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { parametrosEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.parametros.detail.title">Parametros</Translate> [<b>{parametrosEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="parametrosEnum">
                <Translate contentKey="ecommerceApplicationApp.parametros.parametrosEnum">Parametros Enum</Translate>
              </span>
            </dt>
            <dd>{parametrosEntity.parametrosEnum}</dd>
            <dt>
              <span id="valor">
                <Translate contentKey="ecommerceApplicationApp.parametros.valor">Valor</Translate>
              </span>
            </dt>
            <dd>{parametrosEntity.valor}</dd>
          </dl>
          <Button tag={Link} to="/entity/parametros" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/parametros/${parametrosEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ parametros }: IRootState) => ({
  parametrosEntity: parametros.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ParametrosDetail);
