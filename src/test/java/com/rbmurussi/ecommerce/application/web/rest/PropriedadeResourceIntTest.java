package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Propriedade;
import com.rbmurussi.ecommerce.application.repository.PropriedadeRepository;
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
 * Test class for the PropriedadeResource REST controller.
 *
 * @see PropriedadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class PropriedadeResourceIntTest {

    private static final Integer DEFAULT_ID_PROPRIEDADE = 1;
    private static final Integer UPDATED_ID_PROPRIEDADE = 2;

    private static final Integer DEFAULT_PROPRIEDADE_ENUM = 1;
    private static final Integer UPDATED_PROPRIEDADE_ENUM = 2;

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restPropriedadeMockMvc;

    private Propriedade propriedade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropriedadeResource propriedadeResource = new PropriedadeResource(propriedadeRepository);
        this.restPropriedadeMockMvc = MockMvcBuilders.standaloneSetup(propriedadeResource)
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
    public static Propriedade createEntity() {
        Propriedade propriedade = new Propriedade()
            .idPropriedade(DEFAULT_ID_PROPRIEDADE)
            .propriedadeEnum(DEFAULT_PROPRIEDADE_ENUM)
            .valor(DEFAULT_VALOR)
            .idEmitente(DEFAULT_ID_EMITENTE);
        return propriedade;
    }

    @Before
    public void initTest() {
        propriedadeRepository.deleteAll();
        propriedade = createEntity();
    }

    @Test
    public void createPropriedade() throws Exception {
        int databaseSizeBeforeCreate = propriedadeRepository.findAll().size();

        // Create the Propriedade
        restPropriedadeMockMvc.perform(post("/api/propriedades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propriedade)))
            .andExpect(status().isCreated());

        // Validate the Propriedade in the database
        List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        assertThat(propriedadeList).hasSize(databaseSizeBeforeCreate + 1);
        Propriedade testPropriedade = propriedadeList.get(propriedadeList.size() - 1);
        assertThat(testPropriedade.getIdPropriedade()).isEqualTo(DEFAULT_ID_PROPRIEDADE);
        assertThat(testPropriedade.getPropriedadeEnum()).isEqualTo(DEFAULT_PROPRIEDADE_ENUM);
        assertThat(testPropriedade.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testPropriedade.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
    }

    @Test
    public void createPropriedadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propriedadeRepository.findAll().size();

        // Create the Propriedade with an existing ID
        propriedade.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropriedadeMockMvc.perform(post("/api/propriedades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propriedade)))
            .andExpect(status().isBadRequest());

        // Validate the Propriedade in the database
        List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        assertThat(propriedadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdPropriedadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = propriedadeRepository.findAll().size();
        // set the field null
        propriedade.setIdPropriedade(null);

        // Create the Propriedade, which fails.

        restPropriedadeMockMvc.perform(post("/api/propriedades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propriedade)))
            .andExpect(status().isBadRequest());

        List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        assertThat(propriedadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPropriedadeEnumIsRequired() throws Exception {
        int databaseSizeBeforeTest = propriedadeRepository.findAll().size();
        // set the field null
        propriedade.setPropriedadeEnum(null);

        // Create the Propriedade, which fails.

        restPropriedadeMockMvc.perform(post("/api/propriedades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propriedade)))
            .andExpect(status().isBadRequest());

        List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        assertThat(propriedadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = propriedadeRepository.findAll().size();
        // set the field null
        propriedade.setIdEmitente(null);

        // Create the Propriedade, which fails.

        restPropriedadeMockMvc.perform(post("/api/propriedades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propriedade)))
            .andExpect(status().isBadRequest());

        List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        assertThat(propriedadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllPropriedades() throws Exception {
        // Initialize the database
        propriedadeRepository.save(propriedade);

        // Get all the propriedadeList
        restPropriedadeMockMvc.perform(get("/api/propriedades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(propriedade.getId())))
            .andExpect(jsonPath("$.[*].idPropriedade").value(hasItem(DEFAULT_ID_PROPRIEDADE)))
            .andExpect(jsonPath("$.[*].propriedadeEnum").value(hasItem(DEFAULT_PROPRIEDADE_ENUM)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.toString())))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)));
    }
    
    @Test
    public void getPropriedade() throws Exception {
        // Initialize the database
        propriedadeRepository.save(propriedade);

        // Get the propriedade
        restPropriedadeMockMvc.perform(get("/api/propriedades/{id}", propriedade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(propriedade.getId()))
            .andExpect(jsonPath("$.idPropriedade").value(DEFAULT_ID_PROPRIEDADE))
            .andExpect(jsonPath("$.propriedadeEnum").value(DEFAULT_PROPRIEDADE_ENUM))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.toString()))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE));
    }

    @Test
    public void getNonExistingPropriedade() throws Exception {
        // Get the propriedade
        restPropriedadeMockMvc.perform(get("/api/propriedades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePropriedade() throws Exception {
        // Initialize the database
        propriedadeRepository.save(propriedade);

        int databaseSizeBeforeUpdate = propriedadeRepository.findAll().size();

        // Update the propriedade
        Propriedade updatedPropriedade = propriedadeRepository.findById(propriedade.getId()).get();
        updatedPropriedade
            .idPropriedade(UPDATED_ID_PROPRIEDADE)
            .propriedadeEnum(UPDATED_PROPRIEDADE_ENUM)
            .valor(UPDATED_VALOR)
            .idEmitente(UPDATED_ID_EMITENTE);

        restPropriedadeMockMvc.perform(put("/api/propriedades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPropriedade)))
            .andExpect(status().isOk());

        // Validate the Propriedade in the database
        List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        assertThat(propriedadeList).hasSize(databaseSizeBeforeUpdate);
        Propriedade testPropriedade = propriedadeList.get(propriedadeList.size() - 1);
        assertThat(testPropriedade.getIdPropriedade()).isEqualTo(UPDATED_ID_PROPRIEDADE);
        assertThat(testPropriedade.getPropriedadeEnum()).isEqualTo(UPDATED_PROPRIEDADE_ENUM);
        assertThat(testPropriedade.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testPropriedade.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
    }

    @Test
    public void updateNonExistingPropriedade() throws Exception {
        int databaseSizeBeforeUpdate = propriedadeRepository.findAll().size();

        // Create the Propriedade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPropriedadeMockMvc.perform(put("/api/propriedades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propriedade)))
            .andExpect(status().isBadRequest());

        // Validate the Propriedade in the database
        List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        assertThat(propriedadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePropriedade() throws Exception {
        // Initialize the database
        propriedadeRepository.save(propriedade);

        int databaseSizeBeforeDelete = propriedadeRepository.findAll().size();

        // Delete the propriedade
        restPropriedadeMockMvc.perform(delete("/api/propriedades/{id}", propriedade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        assertThat(propriedadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Propriedade.class);
        Propriedade propriedade1 = new Propriedade();
        propriedade1.setId("id1");
        Propriedade propriedade2 = new Propriedade();
        propriedade2.setId(propriedade1.getId());
        assertThat(propriedade1).isEqualTo(propriedade2);
        propriedade2.setId("id2");
        assertThat(propriedade1).isNotEqualTo(propriedade2);
        propriedade1.setId(null);
        assertThat(propriedade1).isNotEqualTo(propriedade2);
    }
}
