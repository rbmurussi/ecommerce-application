package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Propriedade;
import com.rbmurussi.ecommerce.application.repository.PropriedadeRepository;
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
 * REST controller for managing Propriedade.
 */
@RestController
@RequestMapping("/api")
public class PropriedadeResource {

    private final Logger log = LoggerFactory.getLogger(PropriedadeResource.class);

    private static final String ENTITY_NAME = "propriedade";

    private final PropriedadeRepository propriedadeRepository;

    public PropriedadeResource(PropriedadeRepository propriedadeRepository) {
        this.propriedadeRepository = propriedadeRepository;
    }

    /**
     * POST  /propriedades : Create a new propriedade.
     *
     * @param propriedade the propriedade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new propriedade, or with status 400 (Bad Request) if the propriedade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/propriedades")
    public ResponseEntity<Propriedade> createPropriedade(@Valid @RequestBody Propriedade propriedade) throws URISyntaxException {
        log.debug("REST request to save Propriedade : {}", propriedade);
        if (propriedade.getId() != null) {
            throw new BadRequestAlertException("A new propriedade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Propriedade result = propriedadeRepository.save(propriedade);
        return ResponseEntity.created(new URI("/api/propriedades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /propriedades : Updates an existing propriedade.
     *
     * @param propriedade the propriedade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated propriedade,
     * or with status 400 (Bad Request) if the propriedade is not valid,
     * or with status 500 (Internal Server Error) if the propriedade couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/propriedades")
    public ResponseEntity<Propriedade> updatePropriedade(@Valid @RequestBody Propriedade propriedade) throws URISyntaxException {
        log.debug("REST request to update Propriedade : {}", propriedade);
        if (propriedade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Propriedade result = propriedadeRepository.save(propriedade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, propriedade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /propriedades : get all the propriedades.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of propriedades in body
     */
    @GetMapping("/propriedades")
    public List<Propriedade> getAllPropriedades() {
        log.debug("REST request to get all Propriedades");
        return propriedadeRepository.findAll();
    }

    /**
     * GET  /propriedades/:id : get the "id" propriedade.
     *
     * @param id the id of the propriedade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the propriedade, or with status 404 (Not Found)
     */
    @GetMapping("/propriedades/{id}")
    public ResponseEntity<Propriedade> getPropriedade(@PathVariable String id) {
        log.debug("REST request to get Propriedade : {}", id);
        Optional<Propriedade> propriedade = propriedadeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(propriedade);
    }

    /**
     * DELETE  /propriedades/:id : delete the "id" propriedade.
     *
     * @param id the id of the propriedade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/propriedades/{id}")
    public ResponseEntity<Void> deletePropriedade(@PathVariable String id) {
        log.debug("REST request to delete Propriedade : {}", id);
        propriedadeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
