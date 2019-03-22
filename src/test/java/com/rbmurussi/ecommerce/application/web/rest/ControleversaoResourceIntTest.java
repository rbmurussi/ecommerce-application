package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Controleversao;
import com.rbmurussi.ecommerce.application.repository.ControleversaoRepository;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.rbmurussi.ecommerce.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ControleversaoResource REST controller.
 *
 * @see ControleversaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class ControleversaoResourceIntTest {

    private static final Integer DEFAULT_ID_CONTROLEVERSAO = 1;
    private static final Integer UPDATED_ID_CONTROLEVERSAO = 2;

    private static final Integer DEFAULT_NUMVERSAO_ENUM = 1;
    private static final Integer UPDATED_NUMVERSAO_ENUM = 2;

    private static final LocalDate DEFAULT_DATAVERSAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATAVERSAO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ControleversaoRepository controleversaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restControleversaoMockMvc;

    private Controleversao controleversao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ControleversaoResource controleversaoResource = new ControleversaoResource(controleversaoRepository);
        this.restControleversaoMockMvc = MockMvcBuilders.standaloneSetup(controleversaoResource)
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
    public static Controleversao createEntity() {
        Controleversao controleversao = new Controleversao()
            .idControleversao(DEFAULT_ID_CONTROLEVERSAO)
            .numversaoEnum(DEFAULT_NUMVERSAO_ENUM)
            .dataversao(DEFAULT_DATAVERSAO);
        return controleversao;
    }

    @Before
    public void initTest() {
        controleversaoRepository.deleteAll();
        controleversao = createEntity();
    }

    @Test
    public void createControleversao() throws Exception {
        int databaseSizeBeforeCreate = controleversaoRepository.findAll().size();

        // Create the Controleversao
        restControleversaoMockMvc.perform(post("/api/controleversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(controleversao)))
            .andExpect(status().isCreated());

        // Validate the Controleversao in the database
        List<Controleversao> controleversaoList = controleversaoRepository.findAll();
        assertThat(controleversaoList).hasSize(databaseSizeBeforeCreate + 1);
        Controleversao testControleversao = controleversaoList.get(controleversaoList.size() - 1);
        assertThat(testControleversao.getIdControleversao()).isEqualTo(DEFAULT_ID_CONTROLEVERSAO);
        assertThat(testControleversao.getNumversaoEnum()).isEqualTo(DEFAULT_NUMVERSAO_ENUM);
        assertThat(testControleversao.getDataversao()).isEqualTo(DEFAULT_DATAVERSAO);
    }

    @Test
    public void createControleversaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = controleversaoRepository.findAll().size();

        // Create the Controleversao with an existing ID
        controleversao.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restControleversaoMockMvc.perform(post("/api/controleversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(controleversao)))
            .andExpect(status().isBadRequest());

        // Validate the Controleversao in the database
        List<Controleversao> controleversaoList = controleversaoRepository.findAll();
        assertThat(controleversaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdControleversaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = controleversaoRepository.findAll().size();
        // set the field null
        controleversao.setIdControleversao(null);

        // Create the Controleversao, which fails.

        restControleversaoMockMvc.perform(post("/api/controleversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(controleversao)))
            .andExpect(status().isBadRequest());

        List<Controleversao> controleversaoList = controleversaoRepository.findAll();
        assertThat(controleversaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNumversaoEnumIsRequired() throws Exception {
        int databaseSizeBeforeTest = controleversaoRepository.findAll().size();
        // set the field null
        controleversao.setNumversaoEnum(null);

        // Create the Controleversao, which fails.

        restControleversaoMockMvc.perform(post("/api/controleversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(controleversao)))
            .andExpect(status().isBadRequest());

        List<Controleversao> controleversaoList = controleversaoRepository.findAll();
        assertThat(controleversaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDataversaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = controleversaoRepository.findAll().size();
        // set the field null
        controleversao.setDataversao(null);

        // Create the Controleversao, which fails.

        restControleversaoMockMvc.perform(post("/api/controleversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(controleversao)))
            .andExpect(status().isBadRequest());

        List<Controleversao> controleversaoList = controleversaoRepository.findAll();
        assertThat(controleversaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllControleversaos() throws Exception {
        // Initialize the database
        controleversaoRepository.save(controleversao);

        // Get all the controleversaoList
        restControleversaoMockMvc.perform(get("/api/controleversaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(controleversao.getId())))
            .andExpect(jsonPath("$.[*].idControleversao").value(hasItem(DEFAULT_ID_CONTROLEVERSAO)))
            .andExpect(jsonPath("$.[*].numversaoEnum").value(hasItem(DEFAULT_NUMVERSAO_ENUM)))
            .andExpect(jsonPath("$.[*].dataversao").value(hasItem(DEFAULT_DATAVERSAO.toString())));
    }
    
    @Test
    public void getControleversao() throws Exception {
        // Initialize the database
        controleversaoRepository.save(controleversao);

        // Get the controleversao
        restControleversaoMockMvc.perform(get("/api/controleversaos/{id}", controleversao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(controleversao.getId()))
            .andExpect(jsonPath("$.idControleversao").value(DEFAULT_ID_CONTROLEVERSAO))
            .andExpect(jsonPath("$.numversaoEnum").value(DEFAULT_NUMVERSAO_ENUM))
            .andExpect(jsonPath("$.dataversao").value(DEFAULT_DATAVERSAO.toString()));
    }

    @Test
    public void getNonExistingControleversao() throws Exception {
        // Get the controleversao
        restControleversaoMockMvc.perform(get("/api/controleversaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateControleversao() throws Exception {
        // Initialize the database
        controleversaoRepository.save(controleversao);

        int databaseSizeBeforeUpdate = controleversaoRepository.findAll().size();

        // Update the controleversao
        Controleversao updatedControleversao = controleversaoRepository.findById(controleversao.getId()).get();
        updatedControleversao
            .idControleversao(UPDATED_ID_CONTROLEVERSAO)
            .numversaoEnum(UPDATED_NUMVERSAO_ENUM)
            .dataversao(UPDATED_DATAVERSAO);

        restControleversaoMockMvc.perform(put("/api/controleversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedControleversao)))
            .andExpect(status().isOk());

        // Validate the Controleversao in the database
        List<Controleversao> controleversaoList = controleversaoRepository.findAll();
        assertThat(controleversaoList).hasSize(databaseSizeBeforeUpdate);
        Controleversao testControleversao = controleversaoList.get(controleversaoList.size() - 1);
        assertThat(testControleversao.getIdControleversao()).isEqualTo(UPDATED_ID_CONTROLEVERSAO);
        assertThat(testControleversao.getNumversaoEnum()).isEqualTo(UPDATED_NUMVERSAO_ENUM);
        assertThat(testControleversao.getDataversao()).isEqualTo(UPDATED_DATAVERSAO);
    }

    @Test
    public void updateNonExistingControleversao() throws Exception {
        int databaseSizeBeforeUpdate = controleversaoRepository.findAll().size();

        // Create the Controleversao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restControleversaoMockMvc.perform(put("/api/controleversaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(controleversao)))
            .andExpect(status().isBadRequest());

        // Validate the Controleversao in the database
        List<Controleversao> controleversaoList = controleversaoRepository.findAll();
        assertThat(controleversaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteControleversao() throws Exception {
        // Initialize the database
        controleversaoRepository.save(controleversao);

        int databaseSizeBeforeDelete = controleversaoRepository.findAll().size();

        // Delete the controleversao
        restControleversaoMockMvc.perform(delete("/api/controleversaos/{id}", controleversao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Controleversao> controleversaoList = controleversaoRepository.findAll();
        assertThat(controleversaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Controleversao.class);
        Controleversao controleversao1 = new Controleversao();
        controleversao1.setId("id1");
        Controleversao controleversao2 = new Controleversao();
        controleversao2.setId(controleversao1.getId());
        assertThat(controleversao1).isEqualTo(controleversao2);
        controleversao2.setId("id2");
        assertThat(controleversao1).isNotEqualTo(controleversao2);
        controleversao1.setId(null);
        assertThat(controleversao1).isNotEqualTo(controleversao2);
    }
}
