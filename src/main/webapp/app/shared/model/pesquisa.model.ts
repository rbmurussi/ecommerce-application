export interface IPesquisa {
  id?: string;
  idPesquisa?: number;
  campoEnum?: number;
  valor?: string;
  idEmitente?: number;
  telaEnum?: number;
}

export const defaultValue: Readonly<IPesquisa> = {};
