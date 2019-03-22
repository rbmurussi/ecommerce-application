import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './controleversao.reducer';
import { IControleversao } from 'app/shared/model/controleversao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IControleversaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ControleversaoDetail extends React.Component<IControleversaoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { controleversaoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.controleversao.detail.title">Controleversao</Translate> [<b>
              {controleversaoEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idControleversao">
                <Translate contentKey="ecommerceApplicationApp.controleversao.idControleversao">Id Controleversao</Translate>
              </span>
            </dt>
            <dd>{controleversaoEntity.idControleversao}</dd>
            <dt>
              <span id="numversaoEnum">
                <Translate contentKey="ecommerceApplicationApp.controleversao.numversaoEnum">Numversao Enum</Translate>
              </span>
            </dt>
            <dd>{controleversaoEntity.numversaoEnum}</dd>
            <dt>
              <span id="dataversao">
                <Translate contentKey="ecommerceApplicationApp.controleversao.dataversao">Dataversao</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={controleversaoEntity.dataversao} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/controleversao" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/controleversao/${controleversaoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ controleversao }: IRootState) => ({
  controleversaoEntity: controleversao.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ControleversaoDetail);
