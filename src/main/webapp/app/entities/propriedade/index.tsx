import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Propriedade from './propriedade';
import PropriedadeDetail from './propriedade-detail';
import PropriedadeUpdate from './propriedade-update';
import PropriedadeDeleteDialog from './propriedade-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PropriedadeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PropriedadeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PropriedadeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Propriedade} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PropriedadeDeleteDialog} />
  </>
);

export default Routes;
