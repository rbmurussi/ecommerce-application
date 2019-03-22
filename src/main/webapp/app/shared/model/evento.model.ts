import { Moment } from 'moment';

export interface IEvento {
  id?: string;
  idEvento?: number;
  idNotalFiscal?: number;
  tpEvento?: string;
  nSeqEvento?: number;
  dataEvento?: Moment;
  numProtocolo?: string;
  xmlProcContentType?: string;
  xmlProc?: any;
  dataRegEvento?: Moment;
}

export const defaultValue: Readonly<IEvento> = {};
