package com.privan.bpmps.service.impl;

import com.privan.bpmps.service.DelegationService;
import com.privan.bpmps.domain.Delegation;
import com.privan.bpmps.repository.DelegationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Delegation.
 */
@Service
@Transactional
public class DelegationServiceImpl implements DelegationService {

    private final Logger log = LoggerFactory.getLogger(DelegationServiceImpl.class);

    private final DelegationRepository delegationRepository;

    public DelegationServiceImpl(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }

    /**
     * Save a delegation.
     *
     * @param delegation the entity to save
     * @return the persisted entity
     */
    @Override
    public Delegation save(Delegation delegation) {
        log.debug("Request to save Delegation : {}", delegation);
        return delegationRepository.save(delegation);
    }

    /**
     * Get all the delegations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Delegation> findAll(Pageable pageable) {
        log.debug("Request to get all Delegations");
        return delegationRepository.findAll(pageable);
    }


    /**
     * Get one delegation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Delegation> findOne(Long id) {
        log.debug("Request to get Delegation : {}", id);
        return delegationRepository.findById(id);
    }

    /**
     * Delete the delegation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Delegation : {}", id);
        delegationRepository.deleteById(id);
    }
}
