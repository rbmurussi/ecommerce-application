package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Parametros;
import com.rbmurussi.ecommerce.application.repository.ParametrosRepository;
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
 * Test class for the ParametrosResource REST controller.
 *
 * @see ParametrosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class ParametrosResourceIntTest {

    private static final Integer DEFAULT_PARAMETROS_ENUM = 1;
    private static final Integer UPDATED_PARAMETROS_ENUM = 2;

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    @Autowired
    private ParametrosRepository parametrosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restParametrosMockMvc;

    private Parametros parametros;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParametrosResource parametrosResource = new ParametrosResource(parametrosRepository);
        this.restParametrosMockMvc = MockMvcBuilders.standaloneSetup(parametrosResource)
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
    public static Parametros createEntity() {
        Parametros parametros = new Parametros()
            .parametrosEnum(DEFAULT_PARAMETROS_ENUM)
            .valor(DEFAULT_VALOR);
        return parametros;
    }

    @Before
    public void initTest() {
        parametrosRepository.deleteAll();
        parametros = createEntity();
    }

    @Test
    public void createParametros() throws Exception {
        int databaseSizeBeforeCreate = parametrosRepository.findAll().size();

        // Create the Parametros
        restParametrosMockMvc.perform(post("/api/parametros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isCreated());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeCreate + 1);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getParametrosEnum()).isEqualTo(DEFAULT_PARAMETROS_ENUM);
        assertThat(testParametros.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    public void createParametrosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parametrosRepository.findAll().size();

        // Create the Parametros with an existing ID
        parametros.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restParametrosMockMvc.perform(post("/api/parametros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkParametrosEnumIsRequired() throws Exception {
        int databaseSizeBeforeTest = parametrosRepository.findAll().size();
        // set the field null
        parametros.setParametrosEnum(null);

        // Create the Parametros, which fails.

        restParametrosMockMvc.perform(post("/api/parametros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isBadRequest());

        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllParametros() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        // Get all the parametrosList
        restParametrosMockMvc.perform(get("/api/parametros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parametros.getId())))
            .andExpect(jsonPath("$.[*].parametrosEnum").value(hasItem(DEFAULT_PARAMETROS_ENUM)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.toString())));
    }
    
    @Test
    public void getParametros() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        // Get the parametros
        restParametrosMockMvc.perform(get("/api/parametros/{id}", parametros.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(parametros.getId()))
            .andExpect(jsonPath("$.parametrosEnum").value(DEFAULT_PARAMETROS_ENUM))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.toString()));
    }

    @Test
    public void getNonExistingParametros() throws Exception {
        // Get the parametros
        restParametrosMockMvc.perform(get("/api/parametros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateParametros() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();

        // Update the parametros
        Parametros updatedParametros = parametrosRepository.findById(parametros.getId()).get();
        updatedParametros
            .parametrosEnum(UPDATED_PARAMETROS_ENUM)
            .valor(UPDATED_VALOR);

        restParametrosMockMvc.perform(put("/api/parametros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedParametros)))
            .andExpect(status().isOk());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getParametrosEnum()).isEqualTo(UPDATED_PARAMETROS_ENUM);
        assertThat(testParametros.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    public void updateNonExistingParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();

        // Create the Parametros

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParametrosMockMvc.perform(put("/api/parametros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteParametros() throws Exception {
        // Initialize the database
        parametrosRepository.save(parametros);

        int databaseSizeBeforeDelete = parametrosRepository.findAll().size();

        // Delete the parametros
        restParametrosMockMvc.perform(delete("/api/parametros/{id}", parametros.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Parametros.class);
        Parametros parametros1 = new Parametros();
        parametros1.setId("id1");
        Parametros parametros2 = new Parametros();
        parametros2.setId(parametros1.getId());
        assertThat(parametros1).isEqualTo(parametros2);
        parametros2.setId("id2");
        assertThat(parametros1).isNotEqualTo(parametros2);
        parametros1.setId(null);
        assertThat(parametros1).isNotEqualTo(parametros2);
    }
}
