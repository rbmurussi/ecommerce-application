import { Moment } from 'moment';

export interface ILote {
  id?: string;
  idLote?: number;
  cNPJTransmissao?: string;
  dataTransmissao?: Moment;
  numRecibo?: string;
  xmlRetornoContentType?: string;
  xmlRetorno?: any;
  dataProc?: Moment;
}

export const defaultValue: Readonly<ILote> = {};
