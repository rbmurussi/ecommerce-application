package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.Evento;
import com.rbmurussi.ecommerce.application.repository.EventoRepository;
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
 * REST controller for managing Evento.
 */
@RestController
@RequestMapping("/api")
public class EventoResource {

    private final Logger log = LoggerFactory.getLogger(EventoResource.class);

    private static final String ENTITY_NAME = "evento";

    private final EventoRepository eventoRepository;

    public EventoResource(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    /**
     * POST  /eventos : Create a new evento.
     *
     * @param evento the evento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evento, or with status 400 (Bad Request) if the evento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/eventos")
    public ResponseEntity<Evento> createEvento(@Valid @RequestBody Evento evento) throws URISyntaxException {
        log.debug("REST request to save Evento : {}", evento);
        if (evento.getId() != null) {
            throw new BadRequestAlertException("A new evento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Evento result = eventoRepository.save(evento);
        return ResponseEntity.created(new URI("/api/eventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /eventos : Updates an existing evento.
     *
     * @param evento the evento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evento,
     * or with status 400 (Bad Request) if the evento is not valid,
     * or with status 500 (Internal Server Error) if the evento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/eventos")
    public ResponseEntity<Evento> updateEvento(@Valid @RequestBody Evento evento) throws URISyntaxException {
        log.debug("REST request to update Evento : {}", evento);
        if (evento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Evento result = eventoRepository.save(evento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /eventos : get all the eventos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eventos in body
     */
    @GetMapping("/eventos")
    public List<Evento> getAllEventos() {
        log.debug("REST request to get all Eventos");
        return eventoRepository.findAll();
    }

    /**
     * GET  /eventos/:id : get the "id" evento.
     *
     * @param id the id of the evento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evento, or with status 404 (Not Found)
     */
    @GetMapping("/eventos/{id}")
    public ResponseEntity<Evento> getEvento(@PathVariable String id) {
        log.debug("REST request to get Evento : {}", id);
        Optional<Evento> evento = eventoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(evento);
    }

    /**
     * DELETE  /eventos/:id : delete the "id" evento.
     *
     * @param id the id of the evento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable String id) {
        log.debug("REST request to delete Evento : {}", id);
        eventoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
