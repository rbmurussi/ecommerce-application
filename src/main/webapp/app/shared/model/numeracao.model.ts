import { Moment } from 'moment';

export interface INumeracao {
  id?: string;
  idNumeracao?: number;
  serie?: string;
  numero?: string;
  ano?: string;
  dataSistema?: Moment;
  idEmitente?: number;
}

export const defaultValue: Readonly<INumeracao> = {};
