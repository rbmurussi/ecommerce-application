package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Numeracao;
import com.rbmurussi.ecommerce.application.repository.NumeracaoRepository;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.rbmurussi.ecommerce.application.web.rest.TestUtil.sameInstant;
import static com.rbmurussi.ecommerce.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the NumeracaoResource REST controller.
 *
 * @see NumeracaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class NumeracaoResourceIntTest {

    private static final Integer DEFAULT_ID_NUMERACAO = 1;
    private static final Integer UPDATED_ID_NUMERACAO = 2;

    private static final String DEFAULT_SERIE = "AAA";
    private static final String UPDATED_SERIE = "BBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBB";

    private static final String DEFAULT_ANO = "AA";
    private static final String UPDATED_ANO = "BB";

    private static final ZonedDateTime DEFAULT_DATA_SISTEMA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_SISTEMA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    @Autowired
    private NumeracaoRepository numeracaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restNumeracaoMockMvc;

    private Numeracao numeracao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NumeracaoResource numeracaoResource = new NumeracaoResource(numeracaoRepository);
        this.restNumeracaoMockMvc = MockMvcBuilders.standaloneSetup(numeracaoResource)
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
    public static Numeracao createEntity() {
        Numeracao numeracao = new Numeracao()
            .idNumeracao(DEFAULT_ID_NUMERACAO)
            .serie(DEFAULT_SERIE)
            .numero(DEFAULT_NUMERO)
            .ano(DEFAULT_ANO)
            .dataSistema(DEFAULT_DATA_SISTEMA)
            .idEmitente(DEFAULT_ID_EMITENTE);
        return numeracao;
    }

    @Before
    public void initTest() {
        numeracaoRepository.deleteAll();
        numeracao = createEntity();
    }

    @Test
    public void createNumeracao() throws Exception {
        int databaseSizeBeforeCreate = numeracaoRepository.findAll().size();

        // Create the Numeracao
        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isCreated());

        // Validate the Numeracao in the database
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeCreate + 1);
        Numeracao testNumeracao = numeracaoList.get(numeracaoList.size() - 1);
        assertThat(testNumeracao.getIdNumeracao()).isEqualTo(DEFAULT_ID_NUMERACAO);
        assertThat(testNumeracao.getSerie()).isEqualTo(DEFAULT_SERIE);
        assertThat(testNumeracao.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testNumeracao.getAno()).isEqualTo(DEFAULT_ANO);
        assertThat(testNumeracao.getDataSistema()).isEqualTo(DEFAULT_DATA_SISTEMA);
        assertThat(testNumeracao.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
    }

    @Test
    public void createNumeracaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = numeracaoRepository.findAll().size();

        // Create the Numeracao with an existing ID
        numeracao.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        // Validate the Numeracao in the database
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdNumeracaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeracaoRepository.findAll().size();
        // set the field null
        numeracao.setIdNumeracao(null);

        // Create the Numeracao, which fails.

        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSerieIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeracaoRepository.findAll().size();
        // set the field null
        numeracao.setSerie(null);

        // Create the Numeracao, which fails.

        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeracaoRepository.findAll().size();
        // set the field null
        numeracao.setNumero(null);

        // Create the Numeracao, which fails.

        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeracaoRepository.findAll().size();
        // set the field null
        numeracao.setAno(null);

        // Create the Numeracao, which fails.

        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeracaoRepository.findAll().size();
        // set the field null
        numeracao.setIdEmitente(null);

        // Create the Numeracao, which fails.

        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllNumeracaos() throws Exception {
        // Initialize the database
        numeracaoRepository.save(numeracao);

        // Get all the numeracaoList
        restNumeracaoMockMvc.perform(get("/api/numeracaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(numeracao.getId())))
            .andExpect(jsonPath("$.[*].idNumeracao").value(hasItem(DEFAULT_ID_NUMERACAO)))
            .andExpect(jsonPath("$.[*].serie").value(hasItem(DEFAULT_SERIE.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].ano").value(hasItem(DEFAULT_ANO.toString())))
            .andExpect(jsonPath("$.[*].dataSistema").value(hasItem(sameInstant(DEFAULT_DATA_SISTEMA))))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)));
    }
    
    @Test
    public void getNumeracao() throws Exception {
        // Initialize the database
        numeracaoRepository.save(numeracao);

        // Get the numeracao
        restNumeracaoMockMvc.perform(get("/api/numeracaos/{id}", numeracao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(numeracao.getId()))
            .andExpect(jsonPath("$.idNumeracao").value(DEFAULT_ID_NUMERACAO))
            .andExpect(jsonPath("$.serie").value(DEFAULT_SERIE.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.ano").value(DEFAULT_ANO.toString()))
            .andExpect(jsonPath("$.dataSistema").value(sameInstant(DEFAULT_DATA_SISTEMA)))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE));
    }

    @Test
    public void getNonExistingNumeracao() throws Exception {
        // Get the numeracao
        restNumeracaoMockMvc.perform(get("/api/numeracaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNumeracao() throws Exception {
        // Initialize the database
        numeracaoRepository.save(numeracao);

        int databaseSizeBeforeUpdate = numeracaoRepository.findAll().size();

        // Update the numeracao
        Numeracao updatedNumeracao = numeracaoRepository.findById(numeracao.getId()).get();
        updatedNumeracao
            .idNumeracao(UPDATED_ID_NUMERACAO)
            .serie(UPDATED_SERIE)
            .numero(UPDATED_NUMERO)
            .ano(UPDATED_ANO)
            .dataSistema(UPDATED_DATA_SISTEMA)
            .idEmitente(UPDATED_ID_EMITENTE);

        restNumeracaoMockMvc.perform(put("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNumeracao)))
            .andExpect(status().isOk());

        // Validate the Numeracao in the database
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeUpdate);
        Numeracao testNumeracao = numeracaoList.get(numeracaoList.size() - 1);
        assertThat(testNumeracao.getIdNumeracao()).isEqualTo(UPDATED_ID_NUMERACAO);
        assertThat(testNumeracao.getSerie()).isEqualTo(UPDATED_SERIE);
        assertThat(testNumeracao.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testNumeracao.getAno()).isEqualTo(UPDATED_ANO);
        assertThat(testNumeracao.getDataSistema()).isEqualTo(UPDATED_DATA_SISTEMA);
        assertThat(testNumeracao.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
    }

    @Test
    public void updateNonExistingNumeracao() throws Exception {
        int databaseSizeBeforeUpdate = numeracaoRepository.findAll().size();

        // Create the Numeracao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNumeracaoMockMvc.perform(put("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        // Validate the Numeracao in the database
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteNumeracao() throws Exception {
        // Initialize the database
        numeracaoRepository.save(numeracao);

        int databaseSizeBeforeDelete = numeracaoRepository.findAll().size();

        // Delete the numeracao
        restNumeracaoMockMvc.perform(delete("/api/numeracaos/{id}", numeracao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Numeracao.class);
        Numeracao numeracao1 = new Numeracao();
        numeracao1.setId("id1");
        Numeracao numeracao2 = new Numeracao();
        numeracao2.setId(numeracao1.getId());
        assertThat(numeracao1).isEqualTo(numeracao2);
        numeracao2.setId("id2");
        assertThat(numeracao1).isNotEqualTo(numeracao2);
        numeracao1.setId(null);
        assertThat(numeracao1).isNotEqualTo(numeracao2);
    }
}
