package com.privan.bpmps.service;

import com.privan.bpmps.domain.Properties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Properties.
 */
public interface PropertiesService {

    /**
     * Save a properties.
     *
     * @param properties the entity to save
     * @return the persisted entity
     */
    Properties save(Properties properties);

    /**
     * Get all the properties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Properties> findAll(Pageable pageable);


    /**
     * Get the "id" properties.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Properties> findOne(Long id);

    /**
     * Delete the "id" properties.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
