import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './generator.reducer';
import { IGenerator } from 'app/shared/model/generator.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGeneratorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class GeneratorDetail extends React.Component<IGeneratorDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { generatorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.generator.detail.title">Generator</Translate> [<b>{generatorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="genKey">
                <Translate contentKey="ecommerceApplicationApp.generator.genKey">Gen Key</Translate>
              </span>
            </dt>
            <dd>{generatorEntity.genKey}</dd>
            <dt>
              <span id="genValue">
                <Translate contentKey="ecommerceApplicationApp.generator.genValue">Gen Value</Translate>
              </span>
            </dt>
            <dd>{generatorEntity.genValue}</dd>
          </dl>
          <Button tag={Link} to="/entity/generator" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/generator/${generatorEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ generator }: IRootState) => ({
  generatorEntity: generator.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GeneratorDetail);
