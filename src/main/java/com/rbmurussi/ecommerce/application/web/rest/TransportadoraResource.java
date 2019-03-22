package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Transportadora;
import com.rbmurussi.ecommerce.application.repository.TransportadoraRepository;
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
 * REST controller for managing Transportadora.
 */
@RestController
@RequestMapping("/api")
public class TransportadoraResource {

    private final Logger log = LoggerFactory.getLogger(TransportadoraResource.class);

    private static final String ENTITY_NAME = "transportadora";

    private final TransportadoraRepository transportadoraRepository;

    public TransportadoraResource(TransportadoraRepository transportadoraRepository) {
        this.transportadoraRepository = transportadoraRepository;
    }

    /**
     * POST  /transportadoras : Create a new transportadora.
     *
     * @param transportadora the transportadora to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transportadora, or with status 400 (Bad Request) if the transportadora has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transportadoras")
    public ResponseEntity<Transportadora> createTransportadora(@Valid @RequestBody Transportadora transportadora) throws URISyntaxException {
        log.debug("REST request to save Transportadora : {}", transportadora);
        if (transportadora.getId() != null) {
            throw new BadRequestAlertException("A new transportadora cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transportadora result = transportadoraRepository.save(transportadora);
        return ResponseEntity.created(new URI("/api/transportadoras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transportadoras : Updates an existing transportadora.
     *
     * @param transportadora the transportadora to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transportadora,
     * or with status 400 (Bad Request) if the transportadora is not valid,
     * or with status 500 (Internal Server Error) if the transportadora couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transportadoras")
    public ResponseEntity<Transportadora> updateTransportadora(@Valid @RequestBody Transportadora transportadora) throws URISyntaxException {
        log.debug("REST request to update Transportadora : {}", transportadora);
        if (transportadora.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Transportadora result = transportadoraRepository.save(transportadora);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transportadora.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transportadoras : get all the transportadoras.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of transportadoras in body
     */
    @GetMapping("/transportadoras")
    public ResponseEntity<List<Transportadora>> getAllTransportadoras(Pageable pageable) {
        log.debug("REST request to get a page of Transportadoras");
        Page<Transportadora> page = transportadoraRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transportadoras");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /transportadoras/:id : get the "id" transportadora.
     *
     * @param id the id of the transportadora to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transportadora, or with status 404 (Not Found)
     */
    @GetMapping("/transportadoras/{id}")
    public ResponseEntity<Transportadora> getTransportadora(@PathVariable String id) {
        log.debug("REST request to get Transportadora : {}", id);
        Optional<Transportadora> transportadora = transportadoraRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transportadora);
    }

    /**
     * DELETE  /transportadoras/:id : delete the "id" transportadora.
     *
     * @param id the id of the transportadora to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transportadoras/{id}")
    public ResponseEntity<Void> deleteTransportadora(@PathVariable String id) {
        log.debug("REST request to delete Transportadora : {}", id);
        transportadoraRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
