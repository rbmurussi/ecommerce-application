package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Generator;
import com.rbmurussi.ecommerce.application.repository.GeneratorRepository;
import com.rbmurussi.ecommerce.application.web.rest.errors.ExceptionTranslator;

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
import org.springframework.validation.Validator;

import java.util.List;


import static com.rbmurussi.ecommerce.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GeneratorResource REST controller.
 *
 * @see GeneratorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class GeneratorResourceIntTest {

    private static final String DEFAULT_GEN_KEY = "AAAAAAAAAA";
    private static final String UPDATED_GEN_KEY = "BBBBBBBBBB";

    private static final Integer DEFAULT_GEN_VALUE = 1;
    private static final Integer UPDATED_GEN_VALUE = 2;

    @Autowired
    private GeneratorRepository generatorRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restGeneratorMockMvc;

    private Generator generator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GeneratorResource generatorResource = new GeneratorResource(generatorRepository);
        this.restGeneratorMockMvc = MockMvcBuilders.standaloneSetup(generatorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Generator createEntity() {
        Generator generator = new Generator()
            .genKey(DEFAULT_GEN_KEY)
            .genValue(DEFAULT_GEN_VALUE);
        return generator;
    }

    @Before
    public void initTest() {
        generatorRepository.deleteAll();
        generator = createEntity();
    }

    @Test
    public void createGenerator() throws Exception {
        int databaseSizeBeforeCreate = generatorRepository.findAll().size();

        // Create the Generator
        restGeneratorMockMvc.perform(post("/api/generators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generator)))
            .andExpect(status().isCreated());

        // Validate the Generator in the database
        List<Generator> generatorList = generatorRepository.findAll();
        assertThat(generatorList).hasSize(databaseSizeBeforeCreate + 1);
        Generator testGenerator = generatorList.get(generatorList.size() - 1);
        assertThat(testGenerator.getGenKey()).isEqualTo(DEFAULT_GEN_KEY);
        assertThat(testGenerator.getGenValue()).isEqualTo(DEFAULT_GEN_VALUE);
    }

    @Test
    public void createGeneratorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = generatorRepository.findAll().size();

        // Create the Generator with an existing ID
        generator.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeneratorMockMvc.perform(post("/api/generators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generator)))
            .andExpect(status().isBadRequest());

        // Validate the Generator in the database
        List<Generator> generatorList = generatorRepository.findAll();
        assertThat(generatorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkGenKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = generatorRepository.findAll().size();
        // set the field null
        generator.setGenKey(null);

        // Create the Generator, which fails.

        restGeneratorMockMvc.perform(post("/api/generators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generator)))
            .andExpect(status().isBadRequest());

        List<Generator> generatorList = generatorRepository.findAll();
        assertThat(generatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkGenValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = generatorRepository.findAll().size();
        // set the field null
        generator.setGenValue(null);

        // Create the Generator, which fails.

        restGeneratorMockMvc.perform(post("/api/generators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generator)))
            .andExpect(status().isBadRequest());

        List<Generator> generatorList = generatorRepository.findAll();
        assertThat(generatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllGenerators() throws Exception {
        // Initialize the database
        generatorRepository.save(generator);

        // Get all the generatorList
        restGeneratorMockMvc.perform(get("/api/generators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(generator.getId())))
            .andExpect(jsonPath("$.[*].genKey").value(hasItem(DEFAULT_GEN_KEY.toString())))
            .andExpect(jsonPath("$.[*].genValue").value(hasItem(DEFAULT_GEN_VALUE)));
    }
    
    @Test
    public void getGenerator() throws Exception {
        // Initialize the database
        generatorRepository.save(generator);

        // Get the generator
        restGeneratorMockMvc.perform(get("/api/generators/{id}", generator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(generator.getId()))
            .andExpect(jsonPath("$.genKey").value(DEFAULT_GEN_KEY.toString()))
            .andExpect(jsonPath("$.genValue").value(DEFAULT_GEN_VALUE));
    }

    @Test
    public void getNonExistingGenerator() throws Exception {
        // Get the generator
        restGeneratorMockMvc.perform(get("/api/generators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGenerator() throws Exception {
        // Initialize the database
        generatorRepository.save(generator);

        int databaseSizeBeforeUpdate = generatorRepository.findAll().size();

        // Update the generator
        Generator updatedGenerator = generatorRepository.findById(generator.getId()).get();
        updatedGenerator
            .genKey(UPDATED_GEN_KEY)
            .genValue(UPDATED_GEN_VALUE);

        restGeneratorMockMvc.perform(put("/api/generators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGenerator)))
            .andExpect(status().isOk());

        // Validate the Generator in the database
        List<Generator> generatorList = generatorRepository.findAll();
        assertThat(generatorList).hasSize(databaseSizeBeforeUpdate);
        Generator testGenerator = generatorList.get(generatorList.size() - 1);
        assertThat(testGenerator.getGenKey()).isEqualTo(UPDATED_GEN_KEY);
        assertThat(testGenerator.getGenValue()).isEqualTo(UPDATED_GEN_VALUE);
    }

    @Test
    public void updateNonExistingGenerator() throws Exception {
        int databaseSizeBeforeUpdate = generatorRepository.findAll().size();

        // Create the Generator

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeneratorMockMvc.perform(put("/api/generators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generator)))
            .andExpect(status().isBadRequest());

        // Validate the Generator in the database
        List<Generator> generatorList = generatorRepository.findAll();
        assertThat(generatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGenerator() throws Exception {
        // Initialize the database
        generatorRepository.save(generator);

        int databaseSizeBeforeDelete = generatorRepository.findAll().size();

        // Delete the generator
        restGeneratorMockMvc.perform(delete("/api/generators/{id}", generator.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Generator> generatorList = generatorRepository.findAll();
        assertThat(generatorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Generator.class);
        Generator generator1 = new Generator();
        generator1.setId("id1");
        Generator generator2 = new Generator();
        generator2.setId(generator1.getId());
        assertThat(generator1).isEqualTo(generator2);
        generator2.setId("id2");
        assertThat(generator1).isNotEqualTo(generator2);
        generator1.setId(null);
        assertThat(generator1).isNotEqualTo(generator2);
    }
}
