package com.privan.bpmps.service;

import com.privan.bpmps.domain.Stage;

import java.util.List;
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
     * @return the list of entities
     */
    List<Stage> findAll();


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
