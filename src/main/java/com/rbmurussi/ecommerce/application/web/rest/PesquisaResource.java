package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Pesquisa;
import com.rbmurussi.ecommerce.application.repository.PesquisaRepository;
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
 * REST controller for managing Pesquisa.
 */
@RestController
@RequestMapping("/api")
public class PesquisaResource {

    private final Logger log = LoggerFactory.getLogger(PesquisaResource.class);

    private static final String ENTITY_NAME = "pesquisa";

    private final PesquisaRepository pesquisaRepository;

    public PesquisaResource(PesquisaRepository pesquisaRepository) {
        this.pesquisaRepository = pesquisaRepository;
    }

    /**
     * POST  /pesquisas : Create a new pesquisa.
     *
     * @param pesquisa the pesquisa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pesquisa, or with status 400 (Bad Request) if the pesquisa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pesquisas")
    public ResponseEntity<Pesquisa> createPesquisa(@Valid @RequestBody Pesquisa pesquisa) throws URISyntaxException {
        log.debug("REST request to save Pesquisa : {}", pesquisa);
        if (pesquisa.getId() != null) {
            throw new BadRequestAlertException("A new pesquisa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Pesquisa result = pesquisaRepository.save(pesquisa);
        return ResponseEntity.created(new URI("/api/pesquisas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pesquisas : Updates an existing pesquisa.
     *
     * @param pesquisa the pesquisa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pesquisa,
     * or with status 400 (Bad Request) if the pesquisa is not valid,
     * or with status 500 (Internal Server Error) if the pesquisa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pesquisas")
    public ResponseEntity<Pesquisa> updatePesquisa(@Valid @RequestBody Pesquisa pesquisa) throws URISyntaxException {
        log.debug("REST request to update Pesquisa : {}", pesquisa);
        if (pesquisa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Pesquisa result = pesquisaRepository.save(pesquisa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pesquisa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pesquisas : get all the pesquisas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pesquisas in body
     */
    @GetMapping("/pesquisas")
    public List<Pesquisa> getAllPesquisas() {
        log.debug("REST request to get all Pesquisas");
        return pesquisaRepository.findAll();
    }

    /**
     * GET  /pesquisas/:id : get the "id" pesquisa.
     *
     * @param id the id of the pesquisa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pesquisa, or with status 404 (Not Found)
     */
    @GetMapping("/pesquisas/{id}")
    public ResponseEntity<Pesquisa> getPesquisa(@PathVariable String id) {
        log.debug("REST request to get Pesquisa : {}", id);
        Optional<Pesquisa> pesquisa = pesquisaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pesquisa);
    }

    /**
     * DELETE  /pesquisas/:id : delete the "id" pesquisa.
     *
     * @param id the id of the pesquisa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pesquisas/{id}")
    public ResponseEntity<Void> deletePesquisa(@PathVariable String id) {
        log.debug("REST request to delete Pesquisa : {}", id);
        pesquisaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
