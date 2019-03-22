export interface IGenerator {
  id?: string;
  genKey?: string;
  genValue?: number;
}

export const defaultValue: Readonly<IGenerator> = {};
