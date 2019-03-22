import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Ipi from './ipi';
import IpiDetail from './ipi-detail';
import IpiUpdate from './ipi-update';
import IpiDeleteDialog from './ipi-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IpiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IpiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IpiDetail} />
      <ErrorBoundaryRoute path={match.url} component={Ipi} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={IpiDeleteDialog} />
  </>
);

export default Routes;
