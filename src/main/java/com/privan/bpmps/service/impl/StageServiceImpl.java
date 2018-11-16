package com.privan.bpmps.service.impl;

import com.privan.bpmps.service.StageService;
import com.privan.bpmps.domain.Stage;
import com.privan.bpmps.repository.StageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Stage.
 */
@Service
@Transactional
public class StageServiceImpl implements StageService {

    private final Logger log = LoggerFactory.getLogger(StageServiceImpl.class);

    private final StageRepository stageRepository;

    public StageServiceImpl(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    /**
     * Save a stage.
     *
     * @param stage the entity to save
     * @return the persisted entity
     */
    @Override
    public Stage save(Stage stage) {
        log.debug("Request to save Stage : {}", stage);
        return stageRepository.save(stage);
    }

    /**
     * Get all the stages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Stage> findAll(Pageable pageable) {
        log.debug("Request to get all Stages");
        return stageRepository.findAll(pageable);
    }


    /**
     * Get one stage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Stage> findOne(Long id) {
        log.debug("Request to get Stage : {}", id);
        return stageRepository.findById(id);
    }

    /**
     * Delete the stage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stage : {}", id);
        stageRepository.deleteById(id);
    }
}
