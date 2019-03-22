package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Cancelamento;
import com.rbmurussi.ecommerce.application.repository.CancelamentoRepository;
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
 * REST controller for managing Cancelamento.
 */
@RestController
@RequestMapping("/api")
public class CancelamentoResource {

    private final Logger log = LoggerFactory.getLogger(CancelamentoResource.class);

    private static final String ENTITY_NAME = "cancelamento";

    private final CancelamentoRepository cancelamentoRepository;

    public CancelamentoResource(CancelamentoRepository cancelamentoRepository) {
        this.cancelamentoRepository = cancelamentoRepository;
    }

    /**
     * POST  /cancelamentos : Create a new cancelamento.
     *
     * @param cancelamento the cancelamento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cancelamento, or with status 400 (Bad Request) if the cancelamento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cancelamentos")
    public ResponseEntity<Cancelamento> createCancelamento(@Valid @RequestBody Cancelamento cancelamento) throws URISyntaxException {
        log.debug("REST request to save Cancelamento : {}", cancelamento);
        if (cancelamento.getId() != null) {
            throw new BadRequestAlertException("A new cancelamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cancelamento result = cancelamentoRepository.save(cancelamento);
        return ResponseEntity.created(new URI("/api/cancelamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cancelamentos : Updates an existing cancelamento.
     *
     * @param cancelamento the cancelamento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cancelamento,
     * or with status 400 (Bad Request) if the cancelamento is not valid,
     * or with status 500 (Internal Server Error) if the cancelamento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cancelamentos")
    public ResponseEntity<Cancelamento> updateCancelamento(@Valid @RequestBody Cancelamento cancelamento) throws URISyntaxException {
        log.debug("REST request to update Cancelamento : {}", cancelamento);
        if (cancelamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Cancelamento result = cancelamentoRepository.save(cancelamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cancelamento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cancelamentos : get all the cancelamentos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cancelamentos in body
     */
    @GetMapping("/cancelamentos")
    public List<Cancelamento> getAllCancelamentos() {
        log.debug("REST request to get all Cancelamentos");
        return cancelamentoRepository.findAll();
    }

    /**
     * GET  /cancelamentos/:id : get the "id" cancelamento.
     *
     * @param id the id of the cancelamento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cancelamento, or with status 404 (Not Found)
     */
    @GetMapping("/cancelamentos/{id}")
    public ResponseEntity<Cancelamento> getCancelamento(@PathVariable String id) {
        log.debug("REST request to get Cancelamento : {}", id);
        Optional<Cancelamento> cancelamento = cancelamentoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cancelamento);
    }

    /**
     * DELETE  /cancelamentos/:id : delete the "id" cancelamento.
     *
     * @param id the id of the cancelamento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cancelamentos/{id}")
    public ResponseEntity<Void> deleteCancelamento(@PathVariable String id) {
        log.debug("REST request to delete Cancelamento : {}", id);
        cancelamentoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
