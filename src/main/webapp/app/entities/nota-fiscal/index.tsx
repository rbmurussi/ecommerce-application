import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NotaFiscal from './nota-fiscal';
import NotaFiscalDetail from './nota-fiscal-detail';
import NotaFiscalUpdate from './nota-fiscal-update';
import NotaFiscalDeleteDialog from './nota-fiscal-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NotaFiscalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NotaFiscalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NotaFiscalDetail} />
      <ErrorBoundaryRoute path={match.url} component={NotaFiscal} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={NotaFiscalDeleteDialog} />
  </>
);

export default Routes;
