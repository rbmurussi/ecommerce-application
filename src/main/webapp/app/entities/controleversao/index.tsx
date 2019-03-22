import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Controleversao from './controleversao';
import ControleversaoDetail from './controleversao-detail';
import ControleversaoUpdate from './controleversao-update';
import ControleversaoDeleteDialog from './controleversao-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ControleversaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ControleversaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ControleversaoDetail} />
      <ErrorBoundaryRoute path={match.url} component={Controleversao} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ControleversaoDeleteDialog} />
  </>
);

export default Routes;
