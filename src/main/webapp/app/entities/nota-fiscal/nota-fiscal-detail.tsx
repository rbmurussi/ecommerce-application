import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nota-fiscal.reducer';
import { INotaFiscal } from 'app/shared/model/nota-fiscal.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INotaFiscalDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NotaFiscalDetail extends React.Component<INotaFiscalDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { notaFiscalEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ecommerceApplicationApp.notaFiscal.detail.title">NotaFiscal</Translate> [<b>{notaFiscalEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idNotalFiscal">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.idNotalFiscal">Id Notal Fiscal</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.idNotalFiscal}</dd>
            <dt>
              <span id="numero">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.numero">Numero</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.numero}</dd>
            <dt>
              <span id="serie">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.serie">Serie</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.serie}</dd>
            <dt>
              <span id="modelo">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.modelo">Modelo</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.modelo}</dd>
            <dt>
              <span id="situacao">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.situacao">Situacao</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.situacao}</dd>
            <dt>
              <span id="mes">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.mes">Mes</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.mes}</dd>
            <dt>
              <span id="ano">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.ano">Ano</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.ano}</dd>
            <dt>
              <span id="tipoEmissao">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.tipoEmissao">Tipo Emissao</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.tipoEmissao}</dd>
            <dt>
              <span id="dataEmissao">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataEmissao">Data Emissao</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={notaFiscalEntity.dataEmissao} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dataAutorizacao">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataAutorizacao">Data Autorizacao</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={notaFiscalEntity.dataAutorizacao} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="codigoNumericoChaveAcesso">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoNumericoChaveAcesso">
                  Codigo Numerico Chave Acesso
                </Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.codigoNumericoChaveAcesso}</dd>
            <dt>
              <span id="digitoChaveAcesso">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.digitoChaveAcesso">Digito Chave Acesso</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.digitoChaveAcesso}</dd>
            <dt>
              <span id="autorizacaoExportadaXML">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.autorizacaoExportadaXML">Autorizacao Exportada XML</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.autorizacaoExportadaXML}</dd>
            <dt>
              <span id="documentoDest">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.documentoDest">Documento Dest</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.documentoDest}</dd>
            <dt>
              <span id="uFDest">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.uFDest">U F Dest</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.uFDest}</dd>
            <dt>
              <span id="numeroRecibo">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.numeroRecibo">Numero Recibo</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.numeroRecibo}</dd>
            <dt>
              <span id="danfeImpresso">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.danfeImpresso">Danfe Impresso</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.danfeImpresso}</dd>
            <dt>
              <span id="docXML">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.docXML">Doc XML</Translate>
              </span>
            </dt>
            <dd>
              {notaFiscalEntity.docXML ? (
                <div>
                  <a onClick={openFile(notaFiscalEntity.docXMLContentType, notaFiscalEntity.docXML)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {notaFiscalEntity.docXMLContentType}, {byteSize(notaFiscalEntity.docXML)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="dataSistema">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataSistema">Data Sistema</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={notaFiscalEntity.dataSistema} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="protocolo">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.protocolo">Protocolo</Translate>
              </span>
            </dt>
            <dd>
              {notaFiscalEntity.protocolo ? (
                <div>
                  <a onClick={openFile(notaFiscalEntity.protocoloContentType, notaFiscalEntity.protocolo)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {notaFiscalEntity.protocoloContentType}, {byteSize(notaFiscalEntity.protocolo)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="numeroProtocolo">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.numeroProtocolo">Numero Protocolo</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.numeroProtocolo}</dd>
            <dt>
              <span id="dataProtocolo">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.dataProtocolo">Data Protocolo</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={notaFiscalEntity.dataProtocolo} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="codigoUFEmitente">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoUFEmitente">Codigo UF Emitente</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.codigoUFEmitente}</dd>
            <dt>
              <span id="idEmitente">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.idEmitente">Id Emitente</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.idEmitente}</dd>
            <dt>
              <span id="idLote">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.idLote">Id Lote</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.idLote}</dd>
            <dt>
              <span id="codigoErro">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.codigoErro">Codigo Erro</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.codigoErro}</dd>
            <dt>
              <span id="mensagemErro">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.mensagemErro">Mensagem Erro</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.mensagemErro}</dd>
            <dt>
              <span id="versao">
                <Translate contentKey="ecommerceApplicationApp.notaFiscal.versao">Versao</Translate>
              </span>
            </dt>
            <dd>{notaFiscalEntity.versao}</dd>
          </dl>
          <Button tag={Link} to="/entity/nota-fiscal" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/nota-fiscal/${notaFiscalEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ notaFiscal }: IRootState) => ({
  notaFiscalEntity: notaFiscal.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NotaFiscalDetail);
