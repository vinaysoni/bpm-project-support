package com.privan.bpmps.web.rest;

import com.privan.bpmps.BpmProjectSupportApp;

import com.privan.bpmps.domain.SysProperties;
import com.privan.bpmps.repository.SysPropertiesRepository;
import com.privan.bpmps.service.SysPropertiesService;
import com.privan.bpmps.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.privan.bpmps.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SysPropertiesResource REST controller.
 *
 * @see SysPropertiesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BpmProjectSupportApp.class)
public class SysPropertiesResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    @Autowired
    private SysPropertiesRepository sysPropertiesRepository;

    @Autowired
    private SysPropertiesService sysPropertiesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSysPropertiesMockMvc;

    private SysProperties sysProperties;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysPropertiesResource sysPropertiesResource = new SysPropertiesResource(sysPropertiesService);
        this.restSysPropertiesMockMvc = MockMvcBuilders.standaloneSetup(sysPropertiesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysProperties createEntity(EntityManager em) {
        SysProperties sysProperties = new SysProperties()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .value(DEFAULT_VALUE)
            .location(DEFAULT_LOCATION);
        return sysProperties;
    }

    @Before
    public void initTest() {
        sysProperties = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysProperties() throws Exception {
        int databaseSizeBeforeCreate = sysPropertiesRepository.findAll().size();

        // Create the SysProperties
        restSysPropertiesMockMvc.perform(post("/api/sys-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProperties)))
            .andExpect(status().isCreated());

        // Validate the SysProperties in the database
        List<SysProperties> sysPropertiesList = sysPropertiesRepository.findAll();
        assertThat(sysPropertiesList).hasSize(databaseSizeBeforeCreate + 1);
        SysProperties testSysProperties = sysPropertiesList.get(sysPropertiesList.size() - 1);
        assertThat(testSysProperties.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysProperties.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSysProperties.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testSysProperties.getLocation()).isEqualTo(DEFAULT_LOCATION);
    }

    @Test
    @Transactional
    public void createSysPropertiesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysPropertiesRepository.findAll().size();

        // Create the SysProperties with an existing ID
        sysProperties.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysPropertiesMockMvc.perform(post("/api/sys-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProperties)))
            .andExpect(status().isBadRequest());

        // Validate the SysProperties in the database
        List<SysProperties> sysPropertiesList = sysPropertiesRepository.findAll();
        assertThat(sysPropertiesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysProperties() throws Exception {
        // Initialize the database
        sysPropertiesRepository.saveAndFlush(sysProperties);

        // Get all the sysPropertiesList
        restSysPropertiesMockMvc.perform(get("/api/sys-properties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysProperties.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())));
    }
    
    @Test
    @Transactional
    public void getSysProperties() throws Exception {
        // Initialize the database
        sysPropertiesRepository.saveAndFlush(sysProperties);

        // Get the sysProperties
        restSysPropertiesMockMvc.perform(get("/api/sys-properties/{id}", sysProperties.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysProperties.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSysProperties() throws Exception {
        // Get the sysProperties
        restSysPropertiesMockMvc.perform(get("/api/sys-properties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysProperties() throws Exception {
        // Initialize the database
        sysPropertiesService.save(sysProperties);

        int databaseSizeBeforeUpdate = sysPropertiesRepository.findAll().size();

        // Update the sysProperties
        SysProperties updatedSysProperties = sysPropertiesRepository.findById(sysProperties.getId()).get();
        // Disconnect from session so that the updates on updatedSysProperties are not directly saved in db
        em.detach(updatedSysProperties);
        updatedSysProperties
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE)
            .location(UPDATED_LOCATION);

        restSysPropertiesMockMvc.perform(put("/api/sys-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysProperties)))
            .andExpect(status().isOk());

        // Validate the SysProperties in the database
        List<SysProperties> sysPropertiesList = sysPropertiesRepository.findAll();
        assertThat(sysPropertiesList).hasSize(databaseSizeBeforeUpdate);
        SysProperties testSysProperties = sysPropertiesList.get(sysPropertiesList.size() - 1);
        assertThat(testSysProperties.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysProperties.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSysProperties.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSysProperties.getLocation()).isEqualTo(UPDATED_LOCATION);
    }

    @Test
    @Transactional
    public void updateNonExistingSysProperties() throws Exception {
        int databaseSizeBeforeUpdate = sysPropertiesRepository.findAll().size();

        // Create the SysProperties

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPropertiesMockMvc.perform(put("/api/sys-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProperties)))
            .andExpect(status().isBadRequest());

        // Validate the SysProperties in the database
        List<SysProperties> sysPropertiesList = sysPropertiesRepository.findAll();
        assertThat(sysPropertiesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysProperties() throws Exception {
        // Initialize the database
        sysPropertiesService.save(sysProperties);

        int databaseSizeBeforeDelete = sysPropertiesRepository.findAll().size();

        // Get the sysProperties
        restSysPropertiesMockMvc.perform(delete("/api/sys-properties/{id}", sysProperties.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysProperties> sysPropertiesList = sysPropertiesRepository.findAll();
        assertThat(sysPropertiesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysProperties.class);
        SysProperties sysProperties1 = new SysProperties();
        sysProperties1.setId(1L);
        SysProperties sysProperties2 = new SysProperties();
        sysProperties2.setId(sysProperties1.getId());
        assertThat(sysProperties1).isEqualTo(sysProperties2);
        sysProperties2.setId(2L);
        assertThat(sysProperties1).isNotEqualTo(sysProperties2);
        sysProperties1.setId(null);
        assertThat(sysProperties1).isNotEqualTo(sysProperties2);
    }
}
