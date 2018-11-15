package com.privan.bpmps.service.impl;

import com.privan.bpmps.service.WorkerService;
import com.privan.bpmps.domain.Worker;
import com.privan.bpmps.repository.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Worker.
 */
@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

    private final Logger log = LoggerFactory.getLogger(WorkerServiceImpl.class);

    private final WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    /**
     * Save a worker.
     *
     * @param worker the entity to save
     * @return the persisted entity
     */
    @Override
    public Worker save(Worker worker) {
        log.debug("Request to save Worker : {}", worker);
        return workerRepository.save(worker);
    }

    /**
     * Get all the workers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Worker> findAll(Pageable pageable) {
        log.debug("Request to get all Workers");
        return workerRepository.findAll(pageable);
    }


    /**
     * Get one worker by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Worker> findOne(Long id) {
        log.debug("Request to get Worker : {}", id);
        return workerRepository.findById(id);
    }

    /**
     * Delete the worker by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Worker : {}", id);
        workerRepository.deleteById(id);
    }
}
