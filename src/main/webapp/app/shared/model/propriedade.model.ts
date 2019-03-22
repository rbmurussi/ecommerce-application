export interface IPropriedade {
  id?: string;
  idPropriedade?: number;
  propriedadeEnum?: number;
  valor?: string;
  idEmitente?: number;
}

export const defaultValue: Readonly<IPropriedade> = {};
