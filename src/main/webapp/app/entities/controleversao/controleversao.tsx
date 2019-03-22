import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './controleversao.reducer';
import { IControleversao } from 'app/shared/model/controleversao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IControleversaoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Controleversao extends React.Component<IControleversaoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { controleversaoList, match } = this.props;
    return (
      <div>
        <h2 id="controleversao-heading">
          <Translate contentKey="ecommerceApplicationApp.controleversao.home.title">Controleversaos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.controleversao.home.createLabel">Create new Controleversao</Translate>
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
                  <Translate contentKey="ecommerceApplicationApp.controleversao.idControleversao">Id Controleversao</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.controleversao.numversaoEnum">Numversao Enum</Translate>
                </th>
                <th>
                  <Translate contentKey="ecommerceApplicationApp.controleversao.dataversao">Dataversao</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {controleversaoList.map((controleversao, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${controleversao.id}`} color="link" size="sm">
                      {controleversao.id}
                    </Button>
                  </td>
                  <td>{controleversao.idControleversao}</td>
                  <td>{controleversao.numversaoEnum}</td>
                  <td>
                    <TextFormat type="date" value={controleversao.dataversao} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${controleversao.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${controleversao.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${controleversao.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ controleversao }: IRootState) => ({
  controleversaoList: controleversao.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Controleversao);
