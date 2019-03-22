import { Moment } from 'moment';

export interface INotaFiscal {
  id?: string;
  idNotalFiscal?: number;
  numero?: string;
  serie?: string;
  modelo?: string;
  situacao?: string;
  mes?: string;
  ano?: string;
  tipoEmissao?: string;
  dataEmissao?: Moment;
  dataAutorizacao?: Moment;
  codigoNumericoChaveAcesso?: string;
  digitoChaveAcesso?: string;
  autorizacaoExportadaXML?: string;
  documentoDest?: string;
  uFDest?: string;
  numeroRecibo?: string;
  danfeImpresso?: string;
  docXMLContentType?: string;
  docXML?: any;
  dataSistema?: Moment;
  protocoloContentType?: string;
  protocolo?: any;
  numeroProtocolo?: string;
  dataProtocolo?: Moment;
  codigoUFEmitente?: string;
  idEmitente?: number;
  idLote?: number;
  codigoErro?: string;
  mensagemErro?: string;
  versao?: string;
}

export const defaultValue: Readonly<INotaFiscal> = {};
