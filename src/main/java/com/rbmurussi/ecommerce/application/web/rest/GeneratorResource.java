package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Generator;
import com.rbmurussi.ecommerce.application.repository.GeneratorRepository;
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
 * REST controller for managing Generator.
 */
@RestController
@RequestMapping("/api")
public class GeneratorResource {

    private final Logger log = LoggerFactory.getLogger(GeneratorResource.class);

    private static final String ENTITY_NAME = "generator";

    private final GeneratorRepository generatorRepository;

    public GeneratorResource(GeneratorRepository generatorRepository) {
        this.generatorRepository = generatorRepository;
    }

    /**
     * POST  /generators : Create a new generator.
     *
     * @param generator the generator to create
     * @return the ResponseEntity with status 201 (Created) and with body the new generator, or with status 400 (Bad Request) if the generator has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/generators")
    public ResponseEntity<Generator> createGenerator(@Valid @RequestBody Generator generator) throws URISyntaxException {
        log.debug("REST request to save Generator : {}", generator);
        if (generator.getId() != null) {
            throw new BadRequestAlertException("A new generator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Generator result = generatorRepository.save(generator);
        return ResponseEntity.created(new URI("/api/generators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /generators : Updates an existing generator.
     *
     * @param generator the generator to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated generator,
     * or with status 400 (Bad Request) if the generator is not valid,
     * or with status 500 (Internal Server Error) if the generator couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/generators")
    public ResponseEntity<Generator> updateGenerator(@Valid @RequestBody Generator generator) throws URISyntaxException {
        log.debug("REST request to update Generator : {}", generator);
        if (generator.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Generator result = generatorRepository.save(generator);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, generator.getId().toString()))
            .body(result);
    }

    /**
     * GET  /generators : get all the generators.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of generators in body
     */
    @GetMapping("/generators")
    public List<Generator> getAllGenerators() {
        log.debug("REST request to get all Generators");
        return generatorRepository.findAll();
    }

    /**
     * GET  /generators/:id : get the "id" generator.
     *
     * @param id the id of the generator to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the generator, or with status 404 (Not Found)
     */
    @GetMapping("/generators/{id}")
    public ResponseEntity<Generator> getGenerator(@PathVariable String id) {
        log.debug("REST request to get Generator : {}", id);
        Optional<Generator> generator = generatorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(generator);
    }

    /**
     * DELETE  /generators/:id : delete the "id" generator.
     *
     * @param id the id of the generator to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/generators/{id}")
    public ResponseEntity<Void> deleteGenerator(@PathVariable String id) {
        log.debug("REST request to delete Generator : {}", id);
        generatorRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
