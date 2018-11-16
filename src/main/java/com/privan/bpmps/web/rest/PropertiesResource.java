package com.privan.bpmps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.privan.bpmps.domain.Properties;
import com.privan.bpmps.service.PropertiesService;
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
 * REST controller for managing Properties.
 */
@RestController
@RequestMapping("/api")
public class PropertiesResource {

    private final Logger log = LoggerFactory.getLogger(PropertiesResource.class);

    private static final String ENTITY_NAME = "bpmProjectSupportProperties";

    private final PropertiesService propertiesService;

    public PropertiesResource(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    /**
     * POST  /properties : Create a new properties.
     *
     * @param properties the properties to create
     * @return the ResponseEntity with status 201 (Created) and with body the new properties, or with status 400 (Bad Request) if the properties has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/properties")
    @Timed
    public ResponseEntity<Properties> createProperties(@RequestBody Properties properties) throws URISyntaxException {
        log.debug("REST request to save Properties : {}", properties);
        if (properties.getId() != null) {
            throw new BadRequestAlertException("A new properties cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Properties result = propertiesService.save(properties);
        return ResponseEntity.created(new URI("/api/properties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /properties : Updates an existing properties.
     *
     * @param properties the properties to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated properties,
     * or with status 400 (Bad Request) if the properties is not valid,
     * or with status 500 (Internal Server Error) if the properties couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/properties")
    @Timed
    public ResponseEntity<Properties> updateProperties(@RequestBody Properties properties) throws URISyntaxException {
        log.debug("REST request to update Properties : {}", properties);
        if (properties.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Properties result = propertiesService.save(properties);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, properties.getId().toString()))
            .body(result);
    }

    /**
     * GET  /properties : get all the properties.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of properties in body
     */
    @GetMapping("/properties")
    @Timed
    public ResponseEntity<List<Properties>> getAllProperties(Pageable pageable) {
        log.debug("REST request to get a page of Properties");
        Page<Properties> page = propertiesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/properties");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /properties/:id : get the "id" properties.
     *
     * @param id the id of the properties to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the properties, or with status 404 (Not Found)
     */
    @GetMapping("/properties/{id}")
    @Timed
    public ResponseEntity<Properties> getProperties(@PathVariable Long id) {
        log.debug("REST request to get Properties : {}", id);
        Optional<Properties> properties = propertiesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(properties);
    }

    /**
     * DELETE  /properties/:id : delete the "id" properties.
     *
     * @param id the id of the properties to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/properties/{id}")
    @Timed
    public ResponseEntity<Void> deleteProperties(@PathVariable Long id) {
        log.debug("REST request to delete Properties : {}", id);
        propertiesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
