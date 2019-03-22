package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Parametros;
import com.rbmurussi.ecommerce.application.repository.ParametrosRepository;
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
 * REST controller for managing Parametros.
 */
@RestController
@RequestMapping("/api")
public class ParametrosResource {

    private final Logger log = LoggerFactory.getLogger(ParametrosResource.class);

    private static final String ENTITY_NAME = "parametros";

    private final ParametrosRepository parametrosRepository;

    public ParametrosResource(ParametrosRepository parametrosRepository) {
        this.parametrosRepository = parametrosRepository;
    }

    /**
     * POST  /parametros : Create a new parametros.
     *
     * @param parametros the parametros to create
     * @return the ResponseEntity with status 201 (Created) and with body the new parametros, or with status 400 (Bad Request) if the parametros has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/parametros")
    public ResponseEntity<Parametros> createParametros(@Valid @RequestBody Parametros parametros) throws URISyntaxException {
        log.debug("REST request to save Parametros : {}", parametros);
        if (parametros.getId() != null) {
            throw new BadRequestAlertException("A new parametros cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Parametros result = parametrosRepository.save(parametros);
        return ResponseEntity.created(new URI("/api/parametros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /parametros : Updates an existing parametros.
     *
     * @param parametros the parametros to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated parametros,
     * or with status 400 (Bad Request) if the parametros is not valid,
     * or with status 500 (Internal Server Error) if the parametros couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/parametros")
    public ResponseEntity<Parametros> updateParametros(@Valid @RequestBody Parametros parametros) throws URISyntaxException {
        log.debug("REST request to update Parametros : {}", parametros);
        if (parametros.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Parametros result = parametrosRepository.save(parametros);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, parametros.getId().toString()))
            .body(result);
    }

    /**
     * GET  /parametros : get all the parametros.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of parametros in body
     */
    @GetMapping("/parametros")
    public List<Parametros> getAllParametros() {
        log.debug("REST request to get all Parametros");
        return parametrosRepository.findAll();
    }

    /**
     * GET  /parametros/:id : get the "id" parametros.
     *
     * @param id the id of the parametros to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the parametros, or with status 404 (Not Found)
     */
    @GetMapping("/parametros/{id}")
    public ResponseEntity<Parametros> getParametros(@PathVariable String id) {
        log.debug("REST request to get Parametros : {}", id);
        Optional<Parametros> parametros = parametrosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(parametros);
    }

    /**
     * DELETE  /parametros/:id : delete the "id" parametros.
     *
     * @param id the id of the parametros to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/parametros/{id}")
    public ResponseEntity<Void> deleteParametros(@PathVariable String id) {
        log.debug("REST request to delete Parametros : {}", id);
        parametrosRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
