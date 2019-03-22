import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
import sessions, { SessionsState } from 'app/modules/account/sessions/sessions.reducer';
// prettier-ignore
import controleversao, {
  ControleversaoState
} from 'app/entities/controleversao/controleversao.reducer';
// prettier-ignore
import emitente, {
  EmitenteState
} from 'app/entities/emitente/emitente.reducer';
// prettier-ignore
import certificadoInfo, {
  CertificadoInfoState
} from 'app/entities/certificado-info/certificado-info.reducer';
// prettier-ignore
import cliente, {
  ClienteState
} from 'app/entities/cliente/cliente.reducer';
// prettier-ignore
import generator, {
  GeneratorState
} from 'app/entities/generator/generator.reducer';
// prettier-ignore
import inutilizacao, {
  InutilizacaoState
} from 'app/entities/inutilizacao/inutilizacao.reducer';
// prettier-ignore
import lote, {
  LoteState
} from 'app/entities/lote/lote.reducer';
// prettier-ignore
import notaFiscal, {
  NotaFiscalState
} from 'app/entities/nota-fiscal/nota-fiscal.reducer';
// prettier-ignore
import cancelamento, {
  CancelamentoState
} from 'app/entities/cancelamento/cancelamento.reducer';
// prettier-ignore
import evento, {
  EventoState
} from 'app/entities/evento/evento.reducer';
// prettier-ignore
import numeracao, {
  NumeracaoState
} from 'app/entities/numeracao/numeracao.reducer';
// prettier-ignore
import parametros, {
  ParametrosState
} from 'app/entities/parametros/parametros.reducer';
// prettier-ignore
import pesquisa, {
  PesquisaState
} from 'app/entities/pesquisa/pesquisa.reducer';
// prettier-ignore
import produto, {
  ProdutoState
} from 'app/entities/produto/produto.reducer';
// prettier-ignore
import icms, {
  IcmsState
} from 'app/entities/icms/icms.reducer';
// prettier-ignore
import ipi, {
  IpiState
} from 'app/entities/ipi/ipi.reducer';
// prettier-ignore
import propriedade, {
  PropriedadeState
} from 'app/entities/propriedade/propriedade.reducer';
// prettier-ignore
import transportadora, {
  TransportadoraState
} from 'app/entities/transportadora/transportadora.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly sessions: SessionsState;
  readonly controleversao: ControleversaoState;
  readonly emitente: EmitenteState;
  readonly certificadoInfo: CertificadoInfoState;
  readonly cliente: ClienteState;
  readonly generator: GeneratorState;
  readonly inutilizacao: InutilizacaoState;
  readonly lote: LoteState;
  readonly notaFiscal: NotaFiscalState;
  readonly cancelamento: CancelamentoState;
  readonly evento: EventoState;
  readonly numeracao: NumeracaoState;
  readonly parametros: ParametrosState;
  readonly pesquisa: PesquisaState;
  readonly produto: ProdutoState;
  readonly icms: IcmsState;
  readonly ipi: IpiState;
  readonly propriedade: PropriedadeState;
  readonly transportadora: TransportadoraState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  sessions,
  controleversao,
  emitente,
  certificadoInfo,
  cliente,
  generator,
  inutilizacao,
  lote,
  notaFiscal,
  cancelamento,
  evento,
  numeracao,
  parametros,
  pesquisa,
  produto,
  icms,
  ipi,
  propriedade,
  transportadora,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
