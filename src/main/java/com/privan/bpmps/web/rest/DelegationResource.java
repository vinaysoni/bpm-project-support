package com.privan.bpmps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.privan.bpmps.domain.Delegation;
import com.privan.bpmps.service.DelegationService;
import com.privan.bpmps.web.rest.errors.BadRequestAlertException;
import com.privan.bpmps.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Delegation.
 */
@RestController
@RequestMapping("/api")
public class DelegationResource {

    private final Logger log = LoggerFactory.getLogger(DelegationResource.class);

    private static final String ENTITY_NAME = "bpmProjectSupportDelegation";

    private final DelegationService delegationService;

    public DelegationResource(DelegationService delegationService) {
        this.delegationService = delegationService;
    }

    /**
     * POST  /delegations : Create a new delegation.
     *
     * @param delegation the delegation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new delegation, or with status 400 (Bad Request) if the delegation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/delegations")
    @Timed
    public ResponseEntity<Delegation> createDelegation(@RequestBody Delegation delegation) throws URISyntaxException {
        log.debug("REST request to save Delegation : {}", delegation);
        if (delegation.getId() != null) {
            throw new BadRequestAlertException("A new delegation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Delegation result = delegationService.save(delegation);
        return ResponseEntity.created(new URI("/api/delegations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /delegations : Updates an existing delegation.
     *
     * @param delegation the delegation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated delegation,
     * or with status 400 (Bad Request) if the delegation is not valid,
     * or with status 500 (Internal Server Error) if the delegation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/delegations")
    @Timed
    public ResponseEntity<Delegation> updateDelegation(@RequestBody Delegation delegation) throws URISyntaxException {
        log.debug("REST request to update Delegation : {}", delegation);
        if (delegation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Delegation result = delegationService.save(delegation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, delegation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /delegations : get all the delegations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of delegations in body
     */
    @GetMapping("/delegations")
    @Timed
    public List<Delegation> getAllDelegations() {
        log.debug("REST request to get all Delegations");
        return delegationService.findAll();
    }

    /**
     * GET  /delegations/:id : get the "id" delegation.
     *
     * @param id the id of the delegation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the delegation, or with status 404 (Not Found)
     */
    @GetMapping("/delegations/{id}")
    @Timed
    public ResponseEntity<Delegation> getDelegation(@PathVariable Long id) {
        log.debug("REST request to get Delegation : {}", id);
        Optional<Delegation> delegation = delegationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(delegation);
    }

    /**
     * DELETE  /delegations/:id : delete the "id" delegation.
     *
     * @param id the id of the delegation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delegations/{id}")
    @Timed
    public ResponseEntity<Void> deleteDelegation(@PathVariable Long id) {
        log.debug("REST request to delete Delegation : {}", id);
        delegationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
