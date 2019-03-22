import { Moment } from 'moment';

export interface ICertificadoInfo {
  id?: string;
  idCertificadoInfo?: number;
  idEmitente?: number;
  alias?: string;
  nome?: string;
  autoridadeCertificadora?: string;
  cNPJ?: string;
  caminho?: string;
  tipoCertificado?: string;
  dataUtilizacao?: Moment;
  dataValidade?: Moment;
}

export const defaultValue: Readonly<ICertificadoInfo> = {};
