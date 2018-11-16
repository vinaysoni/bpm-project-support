package com.privan.bpmps.service.impl;

import com.privan.bpmps.service.PropertiesService;
import com.privan.bpmps.domain.Properties;
import com.privan.bpmps.repository.PropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Properties.
 */
@Service
@Transactional
public class PropertiesServiceImpl implements PropertiesService {

    private final Logger log = LoggerFactory.getLogger(PropertiesServiceImpl.class);

    private final PropertiesRepository propertiesRepository;

    public PropertiesServiceImpl(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    /**
     * Save a properties.
     *
     * @param properties the entity to save
     * @return the persisted entity
     */
    @Override
    public Properties save(Properties properties) {
        log.debug("Request to save Properties : {}", properties);
        return propertiesRepository.save(properties);
    }

    /**
     * Get all the properties.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Properties> findAll() {
        log.debug("Request to get all Properties");
        return propertiesRepository.findAll();
    }


    /**
     * Get one properties by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Properties> findOne(Long id) {
        log.debug("Request to get Properties : {}", id);
        return propertiesRepository.findById(id);
    }

    /**
     * Delete the properties by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Properties : {}", id);
        propertiesRepository.deleteById(id);
    }
}
