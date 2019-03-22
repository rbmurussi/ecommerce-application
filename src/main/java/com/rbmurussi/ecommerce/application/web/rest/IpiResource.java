package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Ipi;
import com.rbmurussi.ecommerce.application.repository.IpiRepository;
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
 * REST controller for managing Ipi.
 */
@RestController
@RequestMapping("/api")
public class IpiResource {

    private final Logger log = LoggerFactory.getLogger(IpiResource.class);

    private static final String ENTITY_NAME = "ipi";

    private final IpiRepository ipiRepository;

    public IpiResource(IpiRepository ipiRepository) {
        this.ipiRepository = ipiRepository;
    }

    /**
     * POST  /ipis : Create a new ipi.
     *
     * @param ipi the ipi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ipi, or with status 400 (Bad Request) if the ipi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ipis")
    public ResponseEntity<Ipi> createIpi(@Valid @RequestBody Ipi ipi) throws URISyntaxException {
        log.debug("REST request to save Ipi : {}", ipi);
        if (ipi.getId() != null) {
            throw new BadRequestAlertException("A new ipi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ipi result = ipiRepository.save(ipi);
        return ResponseEntity.created(new URI("/api/ipis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ipis : Updates an existing ipi.
     *
     * @param ipi the ipi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ipi,
     * or with status 400 (Bad Request) if the ipi is not valid,
     * or with status 500 (Internal Server Error) if the ipi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ipis")
    public ResponseEntity<Ipi> updateIpi(@Valid @RequestBody Ipi ipi) throws URISyntaxException {
        log.debug("REST request to update Ipi : {}", ipi);
        if (ipi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ipi result = ipiRepository.save(ipi);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ipi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ipis : get all the ipis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ipis in body
     */
    @GetMapping("/ipis")
    public ResponseEntity<List<Ipi>> getAllIpis(Pageable pageable) {
        log.debug("REST request to get a page of Ipis");
        Page<Ipi> page = ipiRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ipis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ipis/:id : get the "id" ipi.
     *
     * @param id the id of the ipi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ipi, or with status 404 (Not Found)
     */
    @GetMapping("/ipis/{id}")
    public ResponseEntity<Ipi> getIpi(@PathVariable String id) {
        log.debug("REST request to get Ipi : {}", id);
        Optional<Ipi> ipi = ipiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ipi);
    }

    /**
     * DELETE  /ipis/:id : delete the "id" ipi.
     *
     * @param id the id of the ipi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ipis/{id}")
    public ResponseEntity<Void> deleteIpi(@PathVariable String id) {
        log.debug("REST request to delete Ipi : {}", id);
        ipiRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
