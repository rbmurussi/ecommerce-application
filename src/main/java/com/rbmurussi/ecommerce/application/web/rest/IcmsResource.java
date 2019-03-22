package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Icms;
import com.rbmurussi.ecommerce.application.repository.IcmsRepository;
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
 * REST controller for managing Icms.
 */
@RestController
@RequestMapping("/api")
public class IcmsResource {

    private final Logger log = LoggerFactory.getLogger(IcmsResource.class);

    private static final String ENTITY_NAME = "icms";

    private final IcmsRepository icmsRepository;

    public IcmsResource(IcmsRepository icmsRepository) {
        this.icmsRepository = icmsRepository;
    }

    /**
     * POST  /icms : Create a new icms.
     *
     * @param icms the icms to create
     * @return the ResponseEntity with status 201 (Created) and with body the new icms, or with status 400 (Bad Request) if the icms has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/icms")
    public ResponseEntity<Icms> createIcms(@Valid @RequestBody Icms icms) throws URISyntaxException {
        log.debug("REST request to save Icms : {}", icms);
        if (icms.getId() != null) {
            throw new BadRequestAlertException("A new icms cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Icms result = icmsRepository.save(icms);
        return ResponseEntity.created(new URI("/api/icms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /icms : Updates an existing icms.
     *
     * @param icms the icms to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated icms,
     * or with status 400 (Bad Request) if the icms is not valid,
     * or with status 500 (Internal Server Error) if the icms couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/icms")
    public ResponseEntity<Icms> updateIcms(@Valid @RequestBody Icms icms) throws URISyntaxException {
        log.debug("REST request to update Icms : {}", icms);
        if (icms.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Icms result = icmsRepository.save(icms);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, icms.getId().toString()))
            .body(result);
    }

    /**
     * GET  /icms : get all the icms.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of icms in body
     */
    @GetMapping("/icms")
    public ResponseEntity<List<Icms>> getAllIcms(Pageable pageable) {
        log.debug("REST request to get a page of Icms");
        Page<Icms> page = icmsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/icms");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /icms/:id : get the "id" icms.
     *
     * @param id the id of the icms to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the icms, or with status 404 (Not Found)
     */
    @GetMapping("/icms/{id}")
    public ResponseEntity<Icms> getIcms(@PathVariable String id) {
        log.debug("REST request to get Icms : {}", id);
        Optional<Icms> icms = icmsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(icms);
    }

    /**
     * DELETE  /icms/:id : delete the "id" icms.
     *
     * @param id the id of the icms to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/icms/{id}")
    public ResponseEntity<Void> deleteIcms(@PathVariable String id) {
        log.debug("REST request to delete Icms : {}", id);
        icmsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
