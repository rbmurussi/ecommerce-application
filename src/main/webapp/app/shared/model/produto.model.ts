export interface IProduto {
  id?: string;
  cProd?: string;
  idProduto?: number;
  xProd?: string;
  cEAN?: string;
  nCM?: string;
  exTipi?: string;
  genero?: string;
  uCom?: string;
  cEANTrib?: string;
  uTrib?: string;
  vUNCom?: string;
  vUNTrib?: string;
  qTrib?: string;
  idEmitente?: number;
  versao?: string;
  cest?: string;
}

export const defaultValue: Readonly<IProduto> = {};
