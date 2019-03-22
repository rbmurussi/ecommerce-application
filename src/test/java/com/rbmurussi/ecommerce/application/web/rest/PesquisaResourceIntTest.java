package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Pesquisa;
import com.rbmurussi.ecommerce.application.repository.PesquisaRepository;
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
 * Test class for the PesquisaResource REST controller.
 *
 * @see PesquisaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class PesquisaResourceIntTest {

    private static final Integer DEFAULT_ID_PESQUISA = 1;
    private static final Integer UPDATED_ID_PESQUISA = 2;

    private static final Integer DEFAULT_CAMPO_ENUM = 1;
    private static final Integer UPDATED_CAMPO_ENUM = 2;

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    private static final Integer DEFAULT_TELA_ENUM = 1;
    private static final Integer UPDATED_TELA_ENUM = 2;

    @Autowired
    private PesquisaRepository pesquisaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restPesquisaMockMvc;

    private Pesquisa pesquisa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PesquisaResource pesquisaResource = new PesquisaResource(pesquisaRepository);
        this.restPesquisaMockMvc = MockMvcBuilders.standaloneSetup(pesquisaResource)
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
    public static Pesquisa createEntity() {
        Pesquisa pesquisa = new Pesquisa()
            .idPesquisa(DEFAULT_ID_PESQUISA)
            .campoEnum(DEFAULT_CAMPO_ENUM)
            .valor(DEFAULT_VALOR)
            .idEmitente(DEFAULT_ID_EMITENTE)
            .telaEnum(DEFAULT_TELA_ENUM);
        return pesquisa;
    }

    @Before
    public void initTest() {
        pesquisaRepository.deleteAll();
        pesquisa = createEntity();
    }

    @Test
    public void createPesquisa() throws Exception {
        int databaseSizeBeforeCreate = pesquisaRepository.findAll().size();

        // Create the Pesquisa
        restPesquisaMockMvc.perform(post("/api/pesquisas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pesquisa)))
            .andExpect(status().isCreated());

        // Validate the Pesquisa in the database
        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeCreate + 1);
        Pesquisa testPesquisa = pesquisaList.get(pesquisaList.size() - 1);
        assertThat(testPesquisa.getIdPesquisa()).isEqualTo(DEFAULT_ID_PESQUISA);
        assertThat(testPesquisa.getCampoEnum()).isEqualTo(DEFAULT_CAMPO_ENUM);
        assertThat(testPesquisa.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testPesquisa.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
        assertThat(testPesquisa.getTelaEnum()).isEqualTo(DEFAULT_TELA_ENUM);
    }

    @Test
    public void createPesquisaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pesquisaRepository.findAll().size();

        // Create the Pesquisa with an existing ID
        pesquisa.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restPesquisaMockMvc.perform(post("/api/pesquisas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pesquisa)))
            .andExpect(status().isBadRequest());

        // Validate the Pesquisa in the database
        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdPesquisaIsRequired() throws Exception {
        int databaseSizeBeforeTest = pesquisaRepository.findAll().size();
        // set the field null
        pesquisa.setIdPesquisa(null);

        // Create the Pesquisa, which fails.

        restPesquisaMockMvc.perform(post("/api/pesquisas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pesquisa)))
            .andExpect(status().isBadRequest());

        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCampoEnumIsRequired() throws Exception {
        int databaseSizeBeforeTest = pesquisaRepository.findAll().size();
        // set the field null
        pesquisa.setCampoEnum(null);

        // Create the Pesquisa, which fails.

        restPesquisaMockMvc.perform(post("/api/pesquisas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pesquisa)))
            .andExpect(status().isBadRequest());

        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = pesquisaRepository.findAll().size();
        // set the field null
        pesquisa.setIdEmitente(null);

        // Create the Pesquisa, which fails.

        restPesquisaMockMvc.perform(post("/api/pesquisas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pesquisa)))
            .andExpect(status().isBadRequest());

        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTelaEnumIsRequired() throws Exception {
        int databaseSizeBeforeTest = pesquisaRepository.findAll().size();
        // set the field null
        pesquisa.setTelaEnum(null);

        // Create the Pesquisa, which fails.

        restPesquisaMockMvc.perform(post("/api/pesquisas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pesquisa)))
            .andExpect(status().isBadRequest());

        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllPesquisas() throws Exception {
        // Initialize the database
        pesquisaRepository.save(pesquisa);

        // Get all the pesquisaList
        restPesquisaMockMvc.perform(get("/api/pesquisas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pesquisa.getId())))
            .andExpect(jsonPath("$.[*].idPesquisa").value(hasItem(DEFAULT_ID_PESQUISA)))
            .andExpect(jsonPath("$.[*].campoEnum").value(hasItem(DEFAULT_CAMPO_ENUM)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.toString())))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].telaEnum").value(hasItem(DEFAULT_TELA_ENUM)));
    }
    
    @Test
    public void getPesquisa() throws Exception {
        // Initialize the database
        pesquisaRepository.save(pesquisa);

        // Get the pesquisa
        restPesquisaMockMvc.perform(get("/api/pesquisas/{id}", pesquisa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pesquisa.getId()))
            .andExpect(jsonPath("$.idPesquisa").value(DEFAULT_ID_PESQUISA))
            .andExpect(jsonPath("$.campoEnum").value(DEFAULT_CAMPO_ENUM))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.toString()))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE))
            .andExpect(jsonPath("$.telaEnum").value(DEFAULT_TELA_ENUM));
    }

    @Test
    public void getNonExistingPesquisa() throws Exception {
        // Get the pesquisa
        restPesquisaMockMvc.perform(get("/api/pesquisas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePesquisa() throws Exception {
        // Initialize the database
        pesquisaRepository.save(pesquisa);

        int databaseSizeBeforeUpdate = pesquisaRepository.findAll().size();

        // Update the pesquisa
        Pesquisa updatedPesquisa = pesquisaRepository.findById(pesquisa.getId()).get();
        updatedPesquisa
            .idPesquisa(UPDATED_ID_PESQUISA)
            .campoEnum(UPDATED_CAMPO_ENUM)
            .valor(UPDATED_VALOR)
            .idEmitente(UPDATED_ID_EMITENTE)
            .telaEnum(UPDATED_TELA_ENUM);

        restPesquisaMockMvc.perform(put("/api/pesquisas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPesquisa)))
            .andExpect(status().isOk());

        // Validate the Pesquisa in the database
        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeUpdate);
        Pesquisa testPesquisa = pesquisaList.get(pesquisaList.size() - 1);
        assertThat(testPesquisa.getIdPesquisa()).isEqualTo(UPDATED_ID_PESQUISA);
        assertThat(testPesquisa.getCampoEnum()).isEqualTo(UPDATED_CAMPO_ENUM);
        assertThat(testPesquisa.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testPesquisa.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
        assertThat(testPesquisa.getTelaEnum()).isEqualTo(UPDATED_TELA_ENUM);
    }

    @Test
    public void updateNonExistingPesquisa() throws Exception {
        int databaseSizeBeforeUpdate = pesquisaRepository.findAll().size();

        // Create the Pesquisa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPesquisaMockMvc.perform(put("/api/pesquisas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pesquisa)))
            .andExpect(status().isBadRequest());

        // Validate the Pesquisa in the database
        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePesquisa() throws Exception {
        // Initialize the database
        pesquisaRepository.save(pesquisa);

        int databaseSizeBeforeDelete = pesquisaRepository.findAll().size();

        // Delete the pesquisa
        restPesquisaMockMvc.perform(delete("/api/pesquisas/{id}", pesquisa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pesquisa> pesquisaList = pesquisaRepository.findAll();
        assertThat(pesquisaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pesquisa.class);
        Pesquisa pesquisa1 = new Pesquisa();
        pesquisa1.setId("id1");
        Pesquisa pesquisa2 = new Pesquisa();
        pesquisa2.setId(pesquisa1.getId());
        assertThat(pesquisa1).isEqualTo(pesquisa2);
        pesquisa2.setId("id2");
        assertThat(pesquisa1).isNotEqualTo(pesquisa2);
        pesquisa1.setId(null);
        assertThat(pesquisa1).isNotEqualTo(pesquisa2);
    }
}
