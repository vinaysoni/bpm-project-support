package com.privan.bpmps.service;

import com.privan.bpmps.domain.SysProperties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SysProperties.
 */
public interface SysPropertiesService {

    /**
     * Save a sysProperties.
     *
     * @param sysProperties the entity to save
     * @return the persisted entity
     */
    SysProperties save(SysProperties sysProperties);

    /**
     * Get all the sysProperties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SysProperties> findAll(Pageable pageable);


    /**
     * Get the "id" sysProperties.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysProperties> findOne(Long id);

    /**
     * Delete the "id" sysProperties.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
