package com.privan.bpmps.web.rest;

import com.privan.bpmps.BpmProjectSupportApp;

import com.privan.bpmps.domain.Properties;
import com.privan.bpmps.repository.PropertiesRepository;
import com.privan.bpmps.service.PropertiesService;
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
 * Test class for the PropertiesResource REST controller.
 *
 * @see PropertiesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BpmProjectSupportApp.class)
public class PropertiesResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPropertiesMockMvc;

    private Properties properties;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropertiesResource propertiesResource = new PropertiesResource(propertiesService);
        this.restPropertiesMockMvc = MockMvcBuilders.standaloneSetup(propertiesResource)
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
    public static Properties createEntity(EntityManager em) {
        Properties properties = new Properties()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .value(DEFAULT_VALUE)
            .location(DEFAULT_LOCATION);
        return properties;
    }

    @Before
    public void initTest() {
        properties = createEntity(em);
    }

    @Test
    @Transactional
    public void createProperties() throws Exception {
        int databaseSizeBeforeCreate = propertiesRepository.findAll().size();

        // Create the Properties
        restPropertiesMockMvc.perform(post("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(properties)))
            .andExpect(status().isCreated());

        // Validate the Properties in the database
        List<Properties> propertiesList = propertiesRepository.findAll();
        assertThat(propertiesList).hasSize(databaseSizeBeforeCreate + 1);
        Properties testProperties = propertiesList.get(propertiesList.size() - 1);
        assertThat(testProperties.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProperties.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProperties.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testProperties.getLocation()).isEqualTo(DEFAULT_LOCATION);
    }

    @Test
    @Transactional
    public void createPropertiesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propertiesRepository.findAll().size();

        // Create the Properties with an existing ID
        properties.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropertiesMockMvc.perform(post("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(properties)))
            .andExpect(status().isBadRequest());

        // Validate the Properties in the database
        List<Properties> propertiesList = propertiesRepository.findAll();
        assertThat(propertiesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProperties() throws Exception {
        // Initialize the database
        propertiesRepository.saveAndFlush(properties);

        // Get all the propertiesList
        restPropertiesMockMvc.perform(get("/api/properties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(properties.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())));
    }
    
    @Test
    @Transactional
    public void getProperties() throws Exception {
        // Initialize the database
        propertiesRepository.saveAndFlush(properties);

        // Get the properties
        restPropertiesMockMvc.perform(get("/api/properties/{id}", properties.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(properties.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProperties() throws Exception {
        // Get the properties
        restPropertiesMockMvc.perform(get("/api/properties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProperties() throws Exception {
        // Initialize the database
        propertiesService.save(properties);

        int databaseSizeBeforeUpdate = propertiesRepository.findAll().size();

        // Update the properties
        Properties updatedProperties = propertiesRepository.findById(properties.getId()).get();
        // Disconnect from session so that the updates on updatedProperties are not directly saved in db
        em.detach(updatedProperties);
        updatedProperties
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE)
            .location(UPDATED_LOCATION);

        restPropertiesMockMvc.perform(put("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProperties)))
            .andExpect(status().isOk());

        // Validate the Properties in the database
        List<Properties> propertiesList = propertiesRepository.findAll();
        assertThat(propertiesList).hasSize(databaseSizeBeforeUpdate);
        Properties testProperties = propertiesList.get(propertiesList.size() - 1);
        assertThat(testProperties.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProperties.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProperties.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testProperties.getLocation()).isEqualTo(UPDATED_LOCATION);
    }

    @Test
    @Transactional
    public void updateNonExistingProperties() throws Exception {
        int databaseSizeBeforeUpdate = propertiesRepository.findAll().size();

        // Create the Properties

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPropertiesMockMvc.perform(put("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(properties)))
            .andExpect(status().isBadRequest());

        // Validate the Properties in the database
        List<Properties> propertiesList = propertiesRepository.findAll();
        assertThat(propertiesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProperties() throws Exception {
        // Initialize the database
        propertiesService.save(properties);

        int databaseSizeBeforeDelete = propertiesRepository.findAll().size();

        // Get the properties
        restPropertiesMockMvc.perform(delete("/api/properties/{id}", properties.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Properties> propertiesList = propertiesRepository.findAll();
        assertThat(propertiesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Properties.class);
        Properties properties1 = new Properties();
        properties1.setId(1L);
        Properties properties2 = new Properties();
        properties2.setId(properties1.getId());
        assertThat(properties1).isEqualTo(properties2);
        properties2.setId(2L);
        assertThat(properties1).isNotEqualTo(properties2);
        properties1.setId(null);
        assertThat(properties1).isNotEqualTo(properties2);
    }
}
