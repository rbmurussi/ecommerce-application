import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Inutilizacao from './inutilizacao';
import InutilizacaoDetail from './inutilizacao-detail';
import InutilizacaoUpdate from './inutilizacao-update';
import InutilizacaoDeleteDialog from './inutilizacao-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InutilizacaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InutilizacaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InutilizacaoDetail} />
      <ErrorBoundaryRoute path={match.url} component={Inutilizacao} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InutilizacaoDeleteDialog} />
  </>
);

export default Routes;
