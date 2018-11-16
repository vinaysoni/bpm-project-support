package com.privan.bpmps.service;

import com.privan.bpmps.domain.Delegation;

import java.util.List;
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
     * @return the list of entities
     */
    List<Delegation> findAll();


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
