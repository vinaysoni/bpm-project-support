package com.privan.bpmps.service.impl;

import com.privan.bpmps.service.SysPropertiesService;
import com.privan.bpmps.domain.SysProperties;
import com.privan.bpmps.repository.SysPropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysProperties.
 */
@Service
@Transactional
public class SysPropertiesServiceImpl implements SysPropertiesService {

    private final Logger log = LoggerFactory.getLogger(SysPropertiesServiceImpl.class);

    private final SysPropertiesRepository sysPropertiesRepository;

    public SysPropertiesServiceImpl(SysPropertiesRepository sysPropertiesRepository) {
        this.sysPropertiesRepository = sysPropertiesRepository;
    }

    /**
     * Save a sysProperties.
     *
     * @param sysProperties the entity to save
     * @return the persisted entity
     */
    @Override
    public SysProperties save(SysProperties sysProperties) {
        log.debug("Request to save SysProperties : {}", sysProperties);
        return sysPropertiesRepository.save(sysProperties);
    }

    /**
     * Get all the sysProperties.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysProperties> findAll() {
        log.debug("Request to get all SysProperties");
        return sysPropertiesRepository.findAll();
    }


    /**
     * Get one sysProperties by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysProperties> findOne(Long id) {
        log.debug("Request to get SysProperties : {}", id);
        return sysPropertiesRepository.findById(id);
    }

    /**
     * Delete the sysProperties by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysProperties : {}", id);
        sysPropertiesRepository.deleteById(id);
    }
}
