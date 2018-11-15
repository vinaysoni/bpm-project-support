package com.privan.bpmps.service;

import com.privan.bpmps.domain.Worker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Worker> findAll(Pageable pageable);


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
