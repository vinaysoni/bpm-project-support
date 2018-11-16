package com.privan.bpmps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.privan.bpmps.domain.SysProperties;
import com.privan.bpmps.service.SysPropertiesService;
import com.privan.bpmps.web.rest.errors.BadRequestAlertException;
import com.privan.bpmps.web.rest.util.HeaderUtil;
import com.privan.bpmps.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SysProperties.
 */
@RestController
@RequestMapping("/api")
public class SysPropertiesResource {

    private final Logger log = LoggerFactory.getLogger(SysPropertiesResource.class);

    private static final String ENTITY_NAME = "bpmProjectSupportSysProperties";

    private final SysPropertiesService sysPropertiesService;

    public SysPropertiesResource(SysPropertiesService sysPropertiesService) {
        this.sysPropertiesService = sysPropertiesService;
    }

    /**
     * POST  /sys-properties : Create a new sysProperties.
     *
     * @param sysProperties the sysProperties to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysProperties, or with status 400 (Bad Request) if the sysProperties has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-properties")
    @Timed
    public ResponseEntity<SysProperties> createSysProperties(@RequestBody SysProperties sysProperties) throws URISyntaxException {
        log.debug("REST request to save SysProperties : {}", sysProperties);
        if (sysProperties.getId() != null) {
            throw new BadRequestAlertException("A new sysProperties cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysProperties result = sysPropertiesService.save(sysProperties);
        return ResponseEntity.created(new URI("/api/sys-properties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-properties : Updates an existing sysProperties.
     *
     * @param sysProperties the sysProperties to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysProperties,
     * or with status 400 (Bad Request) if the sysProperties is not valid,
     * or with status 500 (Internal Server Error) if the sysProperties couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-properties")
    @Timed
    public ResponseEntity<SysProperties> updateSysProperties(@RequestBody SysProperties sysProperties) throws URISyntaxException {
        log.debug("REST request to update SysProperties : {}", sysProperties);
        if (sysProperties.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysProperties result = sysPropertiesService.save(sysProperties);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysProperties.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-properties : get all the sysProperties.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sysProperties in body
     */
    @GetMapping("/sys-properties")
    @Timed
    public ResponseEntity<List<SysProperties>> getAllSysProperties(Pageable pageable) {
        log.debug("REST request to get a page of SysProperties");
        Page<SysProperties> page = sysPropertiesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sys-properties");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /sys-properties/:id : get the "id" sysProperties.
     *
     * @param id the id of the sysProperties to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysProperties, or with status 404 (Not Found)
     */
    @GetMapping("/sys-properties/{id}")
    @Timed
    public ResponseEntity<SysProperties> getSysProperties(@PathVariable Long id) {
        log.debug("REST request to get SysProperties : {}", id);
        Optional<SysProperties> sysProperties = sysPropertiesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysProperties);
    }

    /**
     * DELETE  /sys-properties/:id : delete the "id" sysProperties.
     *
     * @param id the id of the sysProperties to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-properties/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysProperties(@PathVariable Long id) {
        log.debug("REST request to delete SysProperties : {}", id);
        sysPropertiesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
