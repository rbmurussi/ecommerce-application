export interface IIcms {
  id?: string;
  idIcms?: number;
  orig?: string;
  idProduto?: number;
  cst?: string;
  modBc?: string;
  pREDBC?: string;
  pICMS?: string;
  modBCST?: string;
  pMVAST?: string;
  pRedBCST?: string;
  pICMSST?: string;
  motDesICMS?: string;
  pBCOP?: string;
  uFST?: string;
  pCredSN?: string;
}

export const defaultValue: Readonly<IIcms> = {};
