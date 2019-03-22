package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Controleversao;
import com.rbmurussi.ecommerce.application.repository.ControleversaoRepository;
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
 * REST controller for managing Controleversao.
 */
@RestController
@RequestMapping("/api")
public class ControleversaoResource {

    private final Logger log = LoggerFactory.getLogger(ControleversaoResource.class);

    private static final String ENTITY_NAME = "controleversao";

    private final ControleversaoRepository controleversaoRepository;

    public ControleversaoResource(ControleversaoRepository controleversaoRepository) {
        this.controleversaoRepository = controleversaoRepository;
    }

    /**
     * POST  /controleversaos : Create a new controleversao.
     *
     * @param controleversao the controleversao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new controleversao, or with status 400 (Bad Request) if the controleversao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/controleversaos")
    public ResponseEntity<Controleversao> createControleversao(@Valid @RequestBody Controleversao controleversao) throws URISyntaxException {
        log.debug("REST request to save Controleversao : {}", controleversao);
        if (controleversao.getId() != null) {
            throw new BadRequestAlertException("A new controleversao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Controleversao result = controleversaoRepository.save(controleversao);
        return ResponseEntity.created(new URI("/api/controleversaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /controleversaos : Updates an existing controleversao.
     *
     * @param controleversao the controleversao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated controleversao,
     * or with status 400 (Bad Request) if the controleversao is not valid,
     * or with status 500 (Internal Server Error) if the controleversao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/controleversaos")
    public ResponseEntity<Controleversao> updateControleversao(@Valid @RequestBody Controleversao controleversao) throws URISyntaxException {
        log.debug("REST request to update Controleversao : {}", controleversao);
        if (controleversao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Controleversao result = controleversaoRepository.save(controleversao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, controleversao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /controleversaos : get all the controleversaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of controleversaos in body
     */
    @GetMapping("/controleversaos")
    public List<Controleversao> getAllControleversaos() {
        log.debug("REST request to get all Controleversaos");
        return controleversaoRepository.findAll();
    }

    /**
     * GET  /controleversaos/:id : get the "id" controleversao.
     *
     * @param id the id of the controleversao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the controleversao, or with status 404 (Not Found)
     */
    @GetMapping("/controleversaos/{id}")
    public ResponseEntity<Controleversao> getControleversao(@PathVariable String id) {
        log.debug("REST request to get Controleversao : {}", id);
        Optional<Controleversao> controleversao = controleversaoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(controleversao);
    }

    /**
     * DELETE  /controleversaos/:id : delete the "id" controleversao.
     *
     * @param id the id of the controleversao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/controleversaos/{id}")
    public ResponseEntity<Void> deleteControleversao(@PathVariable String id) {
        log.debug("REST request to delete Controleversao : {}", id);
        controleversaoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
