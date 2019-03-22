import { Moment } from 'moment';

export interface IInutilizacao {
  id?: string;
  idInutilizacao?: number;
  idEmitente?: number;
  serie?: string;
  numeroInicial?: string;
  numeroFinal?: string;
  protocoloXmlContentType?: string;
  protocoloXml?: any;
  dataInutilizacao?: Moment;
}

export const defaultValue: Readonly<IInutilizacao> = {};
