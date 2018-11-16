package com.privan.bpmps.service;

import com.privan.bpmps.domain.Worker;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Worker.
 */
public interface WorkerService {

    /**
     * Save a worker.
     *
     * @param worker the entity to save
     * @return the persisted entity
     */
    Worker save(Worker worker);

    /**
     * Get all the workers.
     *
     * @return the list of entities
     */
    List<Worker> findAll();


    /**
     * Get the "id" worker.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Worker> findOne(Long id);

    /**
     * Delete the "id" worker.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
