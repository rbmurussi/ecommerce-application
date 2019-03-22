import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Icms from './icms';
import IcmsDetail from './icms-detail';
import IcmsUpdate from './icms-update';
import IcmsDeleteDialog from './icms-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IcmsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IcmsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IcmsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Icms} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={IcmsDeleteDialog} />
  </>
);

export default Routes;
