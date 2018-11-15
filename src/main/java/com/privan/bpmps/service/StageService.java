package com.privan.bpmps.service;

import com.privan.bpmps.domain.Stage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Stage.
 */
public interface StageService {

    /**
     * Save a stage.
     *
     * @param stage the entity to save
     * @return the persisted entity
     */
    Stage save(Stage stage);

    /**
     * Get all the stages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Stage> findAll(Pageable pageable);


    /**
     * Get the "id" stage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Stage> findOne(Long id);

    /**
     * Delete the "id" stage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
