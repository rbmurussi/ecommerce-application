import { Moment } from 'moment';

export interface ICancelamento {
  id?: string;
  idNotalFiscal?: number;
  numeroProtocolo?: string;
  protocoloContentType?: string;
  protocolo?: any;
  dataProtocolo?: Moment;
  codigoErro?: string;
  mensagemErro?: string;
  justificativa?: string;
}

export const defaultValue: Readonly<ICancelamento> = {};
