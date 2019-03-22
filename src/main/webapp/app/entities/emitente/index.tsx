import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Emitente from './emitente';
import EmitenteDetail from './emitente-detail';
import EmitenteUpdate from './emitente-update';
import EmitenteDeleteDialog from './emitente-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EmitenteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EmitenteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EmitenteDetail} />
      <ErrorBoundaryRoute path={match.url} component={Emitente} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EmitenteDeleteDialog} />
  </>
);

export default Routes;
