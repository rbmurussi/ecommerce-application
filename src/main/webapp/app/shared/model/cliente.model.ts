export interface ICliente {
  id?: string;
  tpDocumentoEnum?: number;
  idCliente?: number;
  nrDocumento?: string;
  xNome?: string;
  xLgr?: string;
  nRo?: string;
  xCpl?: string;
  xBairro?: string;
  xMun?: string;
  cMun?: string;
  uF?: string;
  cEP?: string;
  cPais?: string;
  xPais?: string;
  fone?: string;
  email?: string;
  idEmitente?: number;
  iE?: string;
  iESUF?: string;
  versao?: string;
}

export const defaultValue: Readonly<ICliente> = {};
