import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './parametros.reducer';
import { IParametros } from 'app/shared/model/parametros.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IParametrosProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Parametros extends React.Component<IParametrosProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { parametrosList, match } = this.props;
    return (
      <div>
        <h2 id="parametros-heading">
          <Translate contentKey="ecommerceApplicationApp.parametros.home.title">Parametros</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.parametros.home.createLabel">Create new Parametros</Translate>
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
                  <Translate contentKey="ecommerceApplicationApp.parametros.parametrosEnum">Parametros Enum</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.parametros.valor">Valor</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {parametrosList.map((parametros, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${parametros.id}`} color="link" size="sm">
                      {parametros.id}
                    </Button>
                  </td>
                  <td>{parametros.parametrosEnum}</td>
                  <td>{parametros.valor}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${parametros.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${parametros.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${parametros.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ parametros }: IRootState) => ({
  parametrosList: parametros.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Parametros);
