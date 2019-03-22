import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Transportadora from './transportadora';
import TransportadoraDetail from './transportadora-detail';
import TransportadoraUpdate from './transportadora-update';
import TransportadoraDeleteDialog from './transportadora-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TransportadoraUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TransportadoraUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TransportadoraDetail} />
      <ErrorBoundaryRoute path={match.url} component={Transportadora} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TransportadoraDeleteDialog} />
  </>
);

export default Routes;
