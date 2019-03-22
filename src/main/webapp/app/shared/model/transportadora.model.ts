export interface ITransportadora {
  id?: string;
  idTransportadora?: number;
  tpDocumentoEnum?: number;
  nrDocumento?: string;
  xNome?: string;
  iE?: string;
  xEnder?: string;
  uF?: string;
  xMun?: string;
  idEmitente?: number;
  versao?: string;
}

export const defaultValue: Readonly<ITransportadora> = {};
