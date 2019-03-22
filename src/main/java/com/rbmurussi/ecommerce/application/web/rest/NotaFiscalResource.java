package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.NotaFiscal;
import com.rbmurussi.ecommerce.application.repository.NotaFiscalRepository;
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
 * REST controller for managing NotaFiscal.
 */
@RestController
@RequestMapping("/api")
public class NotaFiscalResource {

    private final Logger log = LoggerFactory.getLogger(NotaFiscalResource.class);

    private static final String ENTITY_NAME = "notaFiscal";

    private final NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalResource(NotaFiscalRepository notaFiscalRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
    }

    /**
     * POST  /nota-fiscals : Create a new notaFiscal.
     *
     * @param notaFiscal the notaFiscal to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notaFiscal, or with status 400 (Bad Request) if the notaFiscal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nota-fiscals")
    public ResponseEntity<NotaFiscal> createNotaFiscal(@Valid @RequestBody NotaFiscal notaFiscal) throws URISyntaxException {
        log.debug("REST request to save NotaFiscal : {}", notaFiscal);
        if (notaFiscal.getId() != null) {
            throw new BadRequestAlertException("A new notaFiscal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotaFiscal result = notaFiscalRepository.save(notaFiscal);
        return ResponseEntity.created(new URI("/api/nota-fiscals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nota-fiscals : Updates an existing notaFiscal.
     *
     * @param notaFiscal the notaFiscal to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notaFiscal,
     * or with status 400 (Bad Request) if the notaFiscal is not valid,
     * or with status 500 (Internal Server Error) if the notaFiscal couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nota-fiscals")
    public ResponseEntity<NotaFiscal> updateNotaFiscal(@Valid @RequestBody NotaFiscal notaFiscal) throws URISyntaxException {
        log.debug("REST request to update NotaFiscal : {}", notaFiscal);
        if (notaFiscal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotaFiscal result = notaFiscalRepository.save(notaFiscal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notaFiscal.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nota-fiscals : get all the notaFiscals.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of notaFiscals in body
     */
    @GetMapping("/nota-fiscals")
    public ResponseEntity<List<NotaFiscal>> getAllNotaFiscals(Pageable pageable) {
        log.debug("REST request to get a page of NotaFiscals");
        Page<NotaFiscal> page = notaFiscalRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nota-fiscals");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /nota-fiscals/:id : get the "id" notaFiscal.
     *
     * @param id the id of the notaFiscal to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notaFiscal, or with status 404 (Not Found)
     */
    @GetMapping("/nota-fiscals/{id}")
    public ResponseEntity<NotaFiscal> getNotaFiscal(@PathVariable String id) {
        log.debug("REST request to get NotaFiscal : {}", id);
        Optional<NotaFiscal> notaFiscal = notaFiscalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(notaFiscal);
    }

    /**
     * DELETE  /nota-fiscals/:id : delete the "id" notaFiscal.
     *
     * @param id the id of the notaFiscal to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nota-fiscals/{id}")
    public ResponseEntity<Void> deleteNotaFiscal(@PathVariable String id) {
        log.debug("REST request to delete NotaFiscal : {}", id);
        notaFiscalRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
