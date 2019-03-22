package com.rbmurussi.ecommerce.application.web.rest;
import com.rbmurussi.ecommerce.application.domain.CertificadoInfo;
import com.rbmurussi.ecommerce.application.repository.CertificadoInfoRepository;
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
 * REST controller for managing CertificadoInfo.
 */
@RestController
@RequestMapping("/api")
public class CertificadoInfoResource {

    private final Logger log = LoggerFactory.getLogger(CertificadoInfoResource.class);

    private static final String ENTITY_NAME = "certificadoInfo";

    private final CertificadoInfoRepository certificadoInfoRepository;

    public CertificadoInfoResource(CertificadoInfoRepository certificadoInfoRepository) {
        this.certificadoInfoRepository = certificadoInfoRepository;
    }

    /**
     * POST  /certificado-infos : Create a new certificadoInfo.
     *
     * @param certificadoInfo the certificadoInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new certificadoInfo, or with status 400 (Bad Request) if the certificadoInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/certificado-infos")
    public ResponseEntity<CertificadoInfo> createCertificadoInfo(@Valid @RequestBody CertificadoInfo certificadoInfo) throws URISyntaxException {
        log.debug("REST request to save CertificadoInfo : {}", certificadoInfo);
        if (certificadoInfo.getId() != null) {
            throw new BadRequestAlertException("A new certificadoInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificadoInfo result = certificadoInfoRepository.save(certificadoInfo);
        return ResponseEntity.created(new URI("/api/certificado-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /certificado-infos : Updates an existing certificadoInfo.
     *
     * @param certificadoInfo the certificadoInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated certificadoInfo,
     * or with status 400 (Bad Request) if the certificadoInfo is not valid,
     * or with status 500 (Internal Server Error) if the certificadoInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/certificado-infos")
    public ResponseEntity<CertificadoInfo> updateCertificadoInfo(@Valid @RequestBody CertificadoInfo certificadoInfo) throws URISyntaxException {
        log.debug("REST request to update CertificadoInfo : {}", certificadoInfo);
        if (certificadoInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificadoInfo result = certificadoInfoRepository.save(certificadoInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, certificadoInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /certificado-infos : get all the certificadoInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of certificadoInfos in body
     */
    @GetMapping("/certificado-infos")
    public ResponseEntity<List<CertificadoInfo>> getAllCertificadoInfos(Pageable pageable) {
        log.debug("REST request to get a page of CertificadoInfos");
        Page<CertificadoInfo> page = certificadoInfoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/certificado-infos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /certificado-infos/:id : get the "id" certificadoInfo.
     *
     * @param id the id of the certificadoInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the certificadoInfo, or with status 404 (Not Found)
     */
    @GetMapping("/certificado-infos/{id}")
    public ResponseEntity<CertificadoInfo> getCertificadoInfo(@PathVariable String id) {
        log.debug("REST request to get CertificadoInfo : {}", id);
        Optional<CertificadoInfo> certificadoInfo = certificadoInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(certificadoInfo);
    }

    /**
     * DELETE  /certificado-infos/:id : delete the "id" certificadoInfo.
     *
     * @param id the id of the certificadoInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/certificado-infos/{id}")
    public ResponseEntity<Void> deleteCertificadoInfo(@PathVariable String id) {
        log.debug("REST request to delete CertificadoInfo : {}", id);
        certificadoInfoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
