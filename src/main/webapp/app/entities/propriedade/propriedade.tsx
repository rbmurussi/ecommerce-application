import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './propriedade.reducer';
import { IPropriedade } from 'app/shared/model/propriedade.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPropriedadeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Propriedade extends React.Component<IPropriedadeProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { propriedadeList, match } = this.props;
    return (
      <div>
        <h2 id="propriedade-heading">
          <Translate contentKey="ecommerceApplicationApp.propriedade.home.title">Propriedades</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.propriedade.home.createLabel">Create new Propriedade</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.propriedade.idPropriedade">Id Propriedade</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.propriedade.propriedadeEnum">Propriedade Enum</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.propriedade.valor">Valor</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.propriedade.idEmitente">Id Emitente</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {propriedadeList.map((propriedade, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${propriedade.id}`} color="link" size="sm">
                      {propriedade.id}
                    </Button>
                  </td>
                  <td>{propriedade.idPropriedade}</td>
                  <td>{propriedade.propriedadeEnum}</td>
                  <td>{propriedade.valor}</td>
                  <td>{propriedade.idEmitente}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${propriedade.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${propriedade.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${propriedade.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ propriedade }: IRootState) => ({
  propriedadeList: propriedade.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Propriedade);
