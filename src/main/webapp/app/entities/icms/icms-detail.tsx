import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './icms.reducer';
import { IIcms } from 'app/shared/model/icms.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIcmsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class IcmsDetail extends React.Component<IIcmsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { icmsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.icms.detail.title">Icms</Translate> [<b>{icmsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idIcms">
                <Translate contentKey="ecommerceApplicationApp.icms.idIcms">Id Icms</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.idIcms}</dd>
            <dt>
              <span id="orig">
                <Translate contentKey="ecommerceApplicationApp.icms.orig">Orig</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.orig}</dd>
            <dt>
              <span id="idProduto">
                <Translate contentKey="ecommerceApplicationApp.icms.idProduto">Id Produto</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.idProduto}</dd>
            <dt>
              <span id="cst">
                <Translate contentKey="ecommerceApplicationApp.icms.cst">Cst</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.cst}</dd>
            <dt>
              <span id="modBc">
                <Translate contentKey="ecommerceApplicationApp.icms.modBc">Mod Bc</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.modBc}</dd>
            <dt>
              <span id="pREDBC">
                <Translate contentKey="ecommerceApplicationApp.icms.pREDBC">P REDBC</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.pREDBC}</dd>
            <dt>
              <span id="pICMS">
                <Translate contentKey="ecommerceApplicationApp.icms.pICMS">P ICMS</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.pICMS}</dd>
            <dt>
              <span id="modBCST">
                <Translate contentKey="ecommerceApplicationApp.icms.modBCST">Mod BCST</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.modBCST}</dd>
            <dt>
              <span id="pMVAST">
                <Translate contentKey="ecommerceApplicationApp.icms.pMVAST">P MVAST</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.pMVAST}</dd>
            <dt>
              <span id="pRedBCST">
                <Translate contentKey="ecommerceApplicationApp.icms.pRedBCST">P Red BCST</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.pRedBCST}</dd>
            <dt>
              <span id="pICMSST">
                <Translate contentKey="ecommerceApplicationApp.icms.pICMSST">P ICMSST</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.pICMSST}</dd>
            <dt>
              <span id="motDesICMS">
                <Translate contentKey="ecommerceApplicationApp.icms.motDesICMS">Mot Des ICMS</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.motDesICMS}</dd>
            <dt>
              <span id="pBCOP">
                <Translate contentKey="ecommerceApplicationApp.icms.pBCOP">P BCOP</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.pBCOP}</dd>
            <dt>
              <span id="uFST">
                <Translate contentKey="ecommerceApplicationApp.icms.uFST">U FST</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.uFST}</dd>
            <dt>
              <span id="pCredSN">
                <Translate contentKey="ecommerceApplicationApp.icms.pCredSN">P Cred SN</Translate>
              </span>
            </dt>
            <dd>{icmsEntity.pCredSN}</dd>
          </dl>
          <Button tag={Link} to="/entity/icms" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/icms/${icmsEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ icms }: IRootState) => ({
  icmsEntity: icms.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IcmsDetail);
