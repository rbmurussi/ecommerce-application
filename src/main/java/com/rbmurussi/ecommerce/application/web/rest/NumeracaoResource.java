package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Numeracao;
import com.rbmurussi.ecommerce.application.repository.NumeracaoRepository;
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
 * REST controller for managing Numeracao.
 */
@RestController
@RequestMapping("/api")
public class NumeracaoResource {

    private final Logger log = LoggerFactory.getLogger(NumeracaoResource.class);

    private static final String ENTITY_NAME = "numeracao";

    private final NumeracaoRepository numeracaoRepository;

    public NumeracaoResource(NumeracaoRepository numeracaoRepository) {
        this.numeracaoRepository = numeracaoRepository;
    }

    /**
     * POST  /numeracaos : Create a new numeracao.
     *
     * @param numeracao the numeracao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new numeracao, or with status 400 (Bad Request) if the numeracao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/numeracaos")
    public ResponseEntity<Numeracao> createNumeracao(@Valid @RequestBody Numeracao numeracao) throws URISyntaxException {
        log.debug("REST request to save Numeracao : {}", numeracao);
        if (numeracao.getId() != null) {
            throw new BadRequestAlertException("A new numeracao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Numeracao result = numeracaoRepository.save(numeracao);
        return ResponseEntity.created(new URI("/api/numeracaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /numeracaos : Updates an existing numeracao.
     *
     * @param numeracao the numeracao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated numeracao,
     * or with status 400 (Bad Request) if the numeracao is not valid,
     * or with status 500 (Internal Server Error) if the numeracao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/numeracaos")
    public ResponseEntity<Numeracao> updateNumeracao(@Valid @RequestBody Numeracao numeracao) throws URISyntaxException {
        log.debug("REST request to update Numeracao : {}", numeracao);
        if (numeracao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Numeracao result = numeracaoRepository.save(numeracao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, numeracao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /numeracaos : get all the numeracaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of numeracaos in body
     */
    @GetMapping("/numeracaos")
    public List<Numeracao> getAllNumeracaos() {
        log.debug("REST request to get all Numeracaos");
        return numeracaoRepository.findAll();
    }

    /**
     * GET  /numeracaos/:id : get the "id" numeracao.
     *
     * @param id the id of the numeracao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the numeracao, or with status 404 (Not Found)
     */
    @GetMapping("/numeracaos/{id}")
    public ResponseEntity<Numeracao> getNumeracao(@PathVariable String id) {
        log.debug("REST request to get Numeracao : {}", id);
        Optional<Numeracao> numeracao = numeracaoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(numeracao);
    }

    /**
     * DELETE  /numeracaos/:id : delete the "id" numeracao.
     *
     * @param id the id of the numeracao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/numeracaos/{id}")
    public ResponseEntity<Void> deleteNumeracao(@PathVariable String id) {
        log.debug("REST request to delete Numeracao : {}", id);
        numeracaoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
