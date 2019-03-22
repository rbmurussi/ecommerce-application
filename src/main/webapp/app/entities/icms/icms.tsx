import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './icms.reducer';
import { IIcms } from 'app/shared/model/icms.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IIcmsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IIcmsState = IPaginationBaseState;

export class Icms extends React.Component<IIcmsProps, IIcmsState> {
  state: IIcmsState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.reset();
  }

  componentDidUpdate() {
    if (this.props.updateSuccess) {
      this.reset();
    }
  }

  reset = () => {
    this.props.reset();
    this.setState({ activePage: 1 }, () => {
      this.getEntities();
    });
  };

  handleLoadMore = () => {
    if (window.pageYOffset > 0) {
      this.setState({ activePage: this.state.activePage + 1 }, () => this.getEntities());
    }
  };

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => {
        this.reset();
      }
    );
  };

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { icmsList, match } = this.props;
    return (
      <div>
        <h2 id="icms-heading">
          <Translate contentKey="ecommerceApplicationApp.icms.home.title">Icms</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="ecommerceApplicationApp.icms.home.createLabel">Create new Icms</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <InfiniteScroll
            pageStart={this.state.activePage}
            loadMore={this.handleLoadMore}
            hasMore={this.state.activePage - 1 < this.props.links.next}
            loader={<div className="loader">Loading ...</div>}
            threshold={0}
            initialLoad={false}
          >
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('idIcms')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.idIcms">Id Icms</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('orig')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.orig">Orig</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('idProduto')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.idProduto">Id Produto</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('cst')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.cst">Cst</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('modBc')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.modBc">Mod Bc</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pREDBC')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.pREDBC">P REDBC</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pICMS')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.pICMS">P ICMS</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('modBCST')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.modBCST">Mod BCST</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pMVAST')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.pMVAST">P MVAST</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pRedBCST')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.pRedBCST">P Red BCST</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pICMSST')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.pICMSST">P ICMSST</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('motDesICMS')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.motDesICMS">Mot Des ICMS</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pBCOP')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.pBCOP">P BCOP</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('uFST')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.uFST">U FST</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pCredSN')}>
                    <Translate contentKey="ecommerceApplicationApp.icms.pCredSN">P Cred SN</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {icmsList.map((icms, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${icms.id}`} color="link" size="sm">
                        {icms.id}
                      </Button>
                    </td>
                    <td>{icms.idIcms}</td>
                    <td>{icms.orig}</td>
                    <td>{icms.idProduto}</td>
                    <td>{icms.cst}</td>
                    <td>{icms.modBc}</td>
                    <td>{icms.pREDBC}</td>
                    <td>{icms.pICMS}</td>
                    <td>{icms.modBCST}</td>
                    <td>{icms.pMVAST}</td>
                    <td>{icms.pRedBCST}</td>
                    <td>{icms.pICMSST}</td>
                    <td>{icms.motDesICMS}</td>
                    <td>{icms.pBCOP}</td>
                    <td>{icms.uFST}</td>
                    <td>{icms.pCredSN}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${icms.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${icms.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${icms.id}/delete`} color="danger" size="sm">
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
          </InfiniteScroll>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ icms }: IRootState) => ({
  icmsList: icms.entities,
  totalItems: icms.totalItems,
  links: icms.links,
  entity: icms.entity,
  updateSuccess: icms.updateSuccess
});

const mapDispatchToProps = {
  getEntities,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Icms);
