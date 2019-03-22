package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Emitente;
import com.rbmurussi.ecommerce.application.repository.EmitenteRepository;
import com.rbmurussi.ecommerce.application.web.rest.errors.BadRequestAlertException;
import com.rbmurussi.ecommerce.application.web.rest.util.HeaderUtil;
import com.rbmurussi.ecommerce.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Emitente.
 */
@RestController
@RequestMapping("/api")
public class EmitenteResource {

    private final Logger log = LoggerFactory.getLogger(EmitenteResource.class);

    private static final String ENTITY_NAME = "emitente";

    private final EmitenteRepository emitenteRepository;

    public EmitenteResource(EmitenteRepository emitenteRepository) {
        this.emitenteRepository = emitenteRepository;
    }

    /**
     * POST  /emitentes : Create a new emitente.
     *
     * @param emitente the emitente to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emitente, or with status 400 (Bad Request) if the emitente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emitentes")
    public ResponseEntity<Emitente> createEmitente(@Valid @RequestBody Emitente emitente) throws URISyntaxException {
        log.debug("REST request to save Emitente : {}", emitente);
        if (emitente.getId() != null) {
            throw new BadRequestAlertException("A new emitente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Emitente result = emitenteRepository.save(emitente);
        return ResponseEntity.created(new URI("/api/emitentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emitentes : Updates an existing emitente.
     *
     * @param emitente the emitente to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emitente,
     * or with status 400 (Bad Request) if the emitente is not valid,
     * or with status 500 (Internal Server Error) if the emitente couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emitentes")
    public ResponseEntity<Emitente> updateEmitente(@Valid @RequestBody Emitente emitente) throws URISyntaxException {
        log.debug("REST request to update Emitente : {}", emitente);
        if (emitente.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Emitente result = emitenteRepository.save(emitente);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emitente.getId().toString()))
            .body(result);
    }

    /**
     * GET  /emitentes : get all the emitentes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of emitentes in body
     */
    @GetMapping("/emitentes")
    public ResponseEntity<List<Emitente>> getAllEmitentes(Pageable pageable) {
        log.debug("REST request to get a page of Emitentes");
        Page<Emitente> page = emitenteRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/emitentes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /emitentes/:id : get the "id" emitente.
     *
     * @param id the id of the emitente to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emitente, or with status 404 (Not Found)
     */
    @GetMapping("/emitentes/{id}")
    public ResponseEntity<Emitente> getEmitente(@PathVariable String id) {
        log.debug("REST request to get Emitente : {}", id);
        Optional<Emitente> emitente = emitenteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(emitente);
    }

    /**
     * DELETE  /emitentes/:id : delete the "id" emitente.
     *
     * @param id the id of the emitente to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emitentes/{id}")
    public ResponseEntity<Void> deleteEmitente(@PathVariable String id) {
        log.debug("REST request to delete Emitente : {}", id);
        emitenteRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
