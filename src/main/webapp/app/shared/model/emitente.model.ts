export interface IEmitente {
  id?: string;
  idEmitente?: number;
  xNome?: string;
  xFant?: string;
  xLgr?: string;
  nRo?: string;
  xCpl?: string;
  xBairro?: string;
  cMun?: string;
  xMun?: string;
  uF?: string;
  cEP?: string;
  cPais?: string;
  xPais?: string;
  fone?: string;
  iE?: string;
  iEST?: string;
  iM?: string;
  cNAE?: string;
  logotipoContentType?: string;
  logotipo?: any;
  tpDocumentoEnum?: number;
  nrDocumento?: string;
  regimeTributario?: string;
  versao?: string;
}

export const defaultValue: Readonly<IEmitente> = {};
