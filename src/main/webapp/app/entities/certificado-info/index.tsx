import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CertificadoInfo from './certificado-info';
import CertificadoInfoDetail from './certificado-info-detail';
import CertificadoInfoUpdate from './certificado-info-update';
import CertificadoInfoDeleteDialog from './certificado-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CertificadoInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CertificadoInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CertificadoInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={CertificadoInfo} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CertificadoInfoDeleteDialog} />
  </>
);

export default Routes;
