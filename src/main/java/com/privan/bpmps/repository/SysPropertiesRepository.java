package com.privan.bpmps.repository;

import com.privan.bpmps.domain.SysProperties;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysProperties entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPropertiesRepository extends JpaRepository<SysProperties, Long> {

}
