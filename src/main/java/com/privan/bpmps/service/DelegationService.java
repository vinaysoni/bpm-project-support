package com.privan.bpmps.service;

import com.privan.bpmps.domain.Delegation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Delegation.
 */
public interface DelegationService {

    /**
     * Save a delegation.
     *
     * @param delegation the entity to save
     * @return the persisted entity
     */
    Delegation save(Delegation delegation);

    /**
     * Get all the delegations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Delegation> findAll(Pageable pageable);


    /**
     * Get the "id" delegation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Delegation> findOne(Long id);

    /**
     * Delete the "id" delegation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
