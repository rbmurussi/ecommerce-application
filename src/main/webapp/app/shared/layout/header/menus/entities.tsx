import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <DropdownItem tag={Link} to="/entity/controleversao">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.controleversao" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/emitente">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.emitente" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/certificado-info">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.certificadoInfo" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/cliente">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.cliente" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/generator">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.generator" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/inutilizacao">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.inutilizacao" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lote">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.lote" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/nota-fiscal">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.notaFiscal" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/cancelamento">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.cancelamento" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/evento">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.evento" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/numeracao">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.numeracao" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/parametros">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.parametros" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/pesquisa">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.pesquisa" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/produto">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.produto" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/icms">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.icms" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/ipi">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.ipi" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/propriedade">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.propriedade" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/transportadora">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.transportadora" />
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
