import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Controleversao from './controleversao';
import Emitente from './emitente';
import CertificadoInfo from './certificado-info';
import Cliente from './cliente';
import Generator from './generator';
import Inutilizacao from './inutilizacao';
import Lote from './lote';
import NotaFiscal from './nota-fiscal';
import Cancelamento from './cancelamento';
import Evento from './evento';
import Numeracao from './numeracao';
import Parametros from './parametros';
import Pesquisa from './pesquisa';
import Produto from './produto';
import Icms from './icms';
import Ipi from './ipi';
import Propriedade from './propriedade';
import Transportadora from './transportadora';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/controleversao`} component={Controleversao} />
      <ErrorBoundaryRoute path={`${match.url}/emitente`} component={Emitente} />
      <ErrorBoundaryRoute path={`${match.url}/certificado-info`} component={CertificadoInfo} />
      <ErrorBoundaryRoute path={`${match.url}/cliente`} component={Cliente} />
      <ErrorBoundaryRoute path={`${match.url}/generator`} component={Generator} />
      <ErrorBoundaryRoute path={`${match.url}/inutilizacao`} component={Inutilizacao} />
      <ErrorBoundaryRoute path={`${match.url}/lote`} component={Lote} />
      <ErrorBoundaryRoute path={`${match.url}/nota-fiscal`} component={NotaFiscal} />
      <ErrorBoundaryRoute path={`${match.url}/cancelamento`} component={Cancelamento} />
      <ErrorBoundaryRoute path={`${match.url}/evento`} component={Evento} />
      <ErrorBoundaryRoute path={`${match.url}/numeracao`} component={Numeracao} />
      <ErrorBoundaryRoute path={`${match.url}/parametros`} component={Parametros} />
      <ErrorBoundaryRoute path={`${match.url}/pesquisa`} component={Pesquisa} />
      <ErrorBoundaryRoute path={`${match.url}/produto`} component={Produto} />
      <ErrorBoundaryRoute path={`${match.url}/icms`} component={Icms} />
      <ErrorBoundaryRoute path={`${match.url}/ipi`} component={Ipi} />
      <ErrorBoundaryRoute path={`${match.url}/propriedade`} component={Propriedade} />
      <ErrorBoundaryRoute path={`${match.url}/transportadora`} component={Transportadora} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
