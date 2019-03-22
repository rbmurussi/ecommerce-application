import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Cancelamento from './cancelamento';
import CancelamentoDetail from './cancelamento-detail';
import CancelamentoUpdate from './cancelamento-update';
import CancelamentoDeleteDialog from './cancelamento-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CancelamentoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CancelamentoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CancelamentoDetail} />
      <ErrorBoundaryRoute path={match.url} component={Cancelamento} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CancelamentoDeleteDialog} />
  </>
);

export default Routes;
