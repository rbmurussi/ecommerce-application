import { Moment } from 'moment';

export interface IControleversao {
  id?: string;
  idControleversao?: number;
  numversaoEnum?: number;
  dataversao?: Moment;
}

export const defaultValue: Readonly<IControleversao> = {};
