package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Inutilizacao;
import com.rbmurussi.ecommerce.application.repository.InutilizacaoRepository;
import com.rbmurussi.ecommerce.application.web.rest.errors.BadRequestAlertException;
import com.rbmurussi.ecommerce.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Inutilizacao.
 */
@RestController
@RequestMapping("/api")
public class InutilizacaoResource {

    private final Logger log = LoggerFactory.getLogger(InutilizacaoResource.class);

    private static final String ENTITY_NAME = "inutilizacao";

    private final InutilizacaoRepository inutilizacaoRepository;

    public InutilizacaoResource(InutilizacaoRepository inutilizacaoRepository) {
        this.inutilizacaoRepository = inutilizacaoRepository;
    }

    /**
     * POST  /inutilizacaos : Create a new inutilizacao.
     *
     * @param inutilizacao the inutilizacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new inutilizacao, or with status 400 (Bad Request) if the inutilizacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/inutilizacaos")
    public ResponseEntity<Inutilizacao> createInutilizacao(@Valid @RequestBody Inutilizacao inutilizacao) throws URISyntaxException {
        log.debug("REST request to save Inutilizacao : {}", inutilizacao);
        if (inutilizacao.getId() != null) {
            throw new BadRequestAlertException("A new inutilizacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Inutilizacao result = inutilizacaoRepository.save(inutilizacao);
        return ResponseEntity.created(new URI("/api/inutilizacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /inutilizacaos : Updates an existing inutilizacao.
     *
     * @param inutilizacao the inutilizacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated inutilizacao,
     * or with status 400 (Bad Request) if the inutilizacao is not valid,
     * or with status 500 (Internal Server Error) if the inutilizacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/inutilizacaos")
    public ResponseEntity<Inutilizacao> updateInutilizacao(@Valid @RequestBody Inutilizacao inutilizacao) throws URISyntaxException {
        log.debug("REST request to update Inutilizacao : {}", inutilizacao);
        if (inutilizacao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Inutilizacao result = inutilizacaoRepository.save(inutilizacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, inutilizacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /inutilizacaos : get all the inutilizacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of inutilizacaos in body
     */
    @GetMapping("/inutilizacaos")
    public List<Inutilizacao> getAllInutilizacaos() {
        log.debug("REST request to get all Inutilizacaos");
        return inutilizacaoRepository.findAll();
    }

    /**
     * GET  /inutilizacaos/:id : get the "id" inutilizacao.
     *
     * @param id the id of the inutilizacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the inutilizacao, or with status 404 (Not Found)
     */
    @GetMapping("/inutilizacaos/{id}")
    public ResponseEntity<Inutilizacao> getInutilizacao(@PathVariable String id) {
        log.debug("REST request to get Inutilizacao : {}", id);
        Optional<Inutilizacao> inutilizacao = inutilizacaoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inutilizacao);
    }

    /**
     * DELETE  /inutilizacaos/:id : delete the "id" inutilizacao.
     *
     * @param id the id of the inutilizacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/inutilizacaos/{id}")
    public ResponseEntity<Void> deleteInutilizacao(@PathVariable String id) {
        log.debug("REST request to delete Inutilizacao : {}", id);
        inutilizacaoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
