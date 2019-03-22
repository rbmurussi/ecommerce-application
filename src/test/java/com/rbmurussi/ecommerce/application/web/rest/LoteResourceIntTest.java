package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Lote;
import com.rbmurussi.ecommerce.application.repository.LoteRepository;
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
import org.springframework.util.Base64Utils;
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
 * Test class for the LoteResource REST controller.
 *
 * @see LoteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class LoteResourceIntTest {

    private static final Long DEFAULT_ID_LOTE = 1L;
    private static final Long UPDATED_ID_LOTE = 2L;

    private static final String DEFAULT_C_NPJ_TRANSMISSAO = "AAAAAAAAAA";
    private static final String UPDATED_C_NPJ_TRANSMISSAO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA_TRANSMISSAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_TRANSMISSAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NUM_RECIBO = "AAAAAAAAAA";
    private static final String UPDATED_NUM_RECIBO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_XML_RETORNO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_XML_RETORNO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_XML_RETORNO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_XML_RETORNO_CONTENT_TYPE = "image/png";

    private static final ZonedDateTime DEFAULT_DATA_PROC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_PROC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restLoteMockMvc;

    private Lote lote;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoteResource loteResource = new LoteResource(loteRepository);
        this.restLoteMockMvc = MockMvcBuilders.standaloneSetup(loteResource)
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
    public static Lote createEntity() {
        Lote lote = new Lote()
            .idLote(DEFAULT_ID_LOTE)
            .cNPJTransmissao(DEFAULT_C_NPJ_TRANSMISSAO)
            .dataTransmissao(DEFAULT_DATA_TRANSMISSAO)
            .numRecibo(DEFAULT_NUM_RECIBO)
            .xmlRetorno(DEFAULT_XML_RETORNO)
            .xmlRetornoContentType(DEFAULT_XML_RETORNO_CONTENT_TYPE)
            .dataProc(DEFAULT_DATA_PROC);
        return lote;
    }

    @Before
    public void initTest() {
        loteRepository.deleteAll();
        lote = createEntity();
    }

    @Test
    public void createLote() throws Exception {
        int databaseSizeBeforeCreate = loteRepository.findAll().size();

        // Create the Lote
        restLoteMockMvc.perform(post("/api/lotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lote)))
            .andExpect(status().isCreated());

        // Validate the Lote in the database
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeCreate + 1);
        Lote testLote = loteList.get(loteList.size() - 1);
        assertThat(testLote.getIdLote()).isEqualTo(DEFAULT_ID_LOTE);
        assertThat(testLote.getcNPJTransmissao()).isEqualTo(DEFAULT_C_NPJ_TRANSMISSAO);
        assertThat(testLote.getDataTransmissao()).isEqualTo(DEFAULT_DATA_TRANSMISSAO);
        assertThat(testLote.getNumRecibo()).isEqualTo(DEFAULT_NUM_RECIBO);
        assertThat(testLote.getXmlRetorno()).isEqualTo(DEFAULT_XML_RETORNO);
        assertThat(testLote.getXmlRetornoContentType()).isEqualTo(DEFAULT_XML_RETORNO_CONTENT_TYPE);
        assertThat(testLote.getDataProc()).isEqualTo(DEFAULT_DATA_PROC);
    }

    @Test
    public void createLoteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loteRepository.findAll().size();

        // Create the Lote with an existing ID
        lote.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoteMockMvc.perform(post("/api/lotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lote)))
            .andExpect(status().isBadRequest());

        // Validate the Lote in the database
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdLoteIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteRepository.findAll().size();
        // set the field null
        lote.setIdLote(null);

        // Create the Lote, which fails.

        restLoteMockMvc.perform(post("/api/lotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lote)))
            .andExpect(status().isBadRequest());

        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkcNPJTransmissaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteRepository.findAll().size();
        // set the field null
        lote.setcNPJTransmissao(null);

        // Create the Lote, which fails.

        restLoteMockMvc.perform(post("/api/lotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lote)))
            .andExpect(status().isBadRequest());

        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDataTransmissaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteRepository.findAll().size();
        // set the field null
        lote.setDataTransmissao(null);

        // Create the Lote, which fails.

        restLoteMockMvc.perform(post("/api/lotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lote)))
            .andExpect(status().isBadRequest());

        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllLotes() throws Exception {
        // Initialize the database
        loteRepository.save(lote);

        // Get all the loteList
        restLoteMockMvc.perform(get("/api/lotes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lote.getId())))
            .andExpect(jsonPath("$.[*].idLote").value(hasItem(DEFAULT_ID_LOTE.intValue())))
            .andExpect(jsonPath("$.[*].cNPJTransmissao").value(hasItem(DEFAULT_C_NPJ_TRANSMISSAO.toString())))
            .andExpect(jsonPath("$.[*].dataTransmissao").value(hasItem(sameInstant(DEFAULT_DATA_TRANSMISSAO))))
            .andExpect(jsonPath("$.[*].numRecibo").value(hasItem(DEFAULT_NUM_RECIBO.toString())))
            .andExpect(jsonPath("$.[*].xmlRetornoContentType").value(hasItem(DEFAULT_XML_RETORNO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].xmlRetorno").value(hasItem(Base64Utils.encodeToString(DEFAULT_XML_RETORNO))))
            .andExpect(jsonPath("$.[*].dataProc").value(hasItem(sameInstant(DEFAULT_DATA_PROC))));
    }
    
    @Test
    public void getLote() throws Exception {
        // Initialize the database
        loteRepository.save(lote);

        // Get the lote
        restLoteMockMvc.perform(get("/api/lotes/{id}", lote.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lote.getId()))
            .andExpect(jsonPath("$.idLote").value(DEFAULT_ID_LOTE.intValue()))
            .andExpect(jsonPath("$.cNPJTransmissao").value(DEFAULT_C_NPJ_TRANSMISSAO.toString()))
            .andExpect(jsonPath("$.dataTransmissao").value(sameInstant(DEFAULT_DATA_TRANSMISSAO)))
            .andExpect(jsonPath("$.numRecibo").value(DEFAULT_NUM_RECIBO.toString()))
            .andExpect(jsonPath("$.xmlRetornoContentType").value(DEFAULT_XML_RETORNO_CONTENT_TYPE))
            .andExpect(jsonPath("$.xmlRetorno").value(Base64Utils.encodeToString(DEFAULT_XML_RETORNO)))
            .andExpect(jsonPath("$.dataProc").value(sameInstant(DEFAULT_DATA_PROC)));
    }

    @Test
    public void getNonExistingLote() throws Exception {
        // Get the lote
        restLoteMockMvc.perform(get("/api/lotes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLote() throws Exception {
        // Initialize the database
        loteRepository.save(lote);

        int databaseSizeBeforeUpdate = loteRepository.findAll().size();

        // Update the lote
        Lote updatedLote = loteRepository.findById(lote.getId()).get();
        updatedLote
            .idLote(UPDATED_ID_LOTE)
            .cNPJTransmissao(UPDATED_C_NPJ_TRANSMISSAO)
            .dataTransmissao(UPDATED_DATA_TRANSMISSAO)
            .numRecibo(UPDATED_NUM_RECIBO)
            .xmlRetorno(UPDATED_XML_RETORNO)
            .xmlRetornoContentType(UPDATED_XML_RETORNO_CONTENT_TYPE)
            .dataProc(UPDATED_DATA_PROC);

        restLoteMockMvc.perform(put("/api/lotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLote)))
            .andExpect(status().isOk());

        // Validate the Lote in the database
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeUpdate);
        Lote testLote = loteList.get(loteList.size() - 1);
        assertThat(testLote.getIdLote()).isEqualTo(UPDATED_ID_LOTE);
        assertThat(testLote.getcNPJTransmissao()).isEqualTo(UPDATED_C_NPJ_TRANSMISSAO);
        assertThat(testLote.getDataTransmissao()).isEqualTo(UPDATED_DATA_TRANSMISSAO);
        assertThat(testLote.getNumRecibo()).isEqualTo(UPDATED_NUM_RECIBO);
        assertThat(testLote.getXmlRetorno()).isEqualTo(UPDATED_XML_RETORNO);
        assertThat(testLote.getXmlRetornoContentType()).isEqualTo(UPDATED_XML_RETORNO_CONTENT_TYPE);
        assertThat(testLote.getDataProc()).isEqualTo(UPDATED_DATA_PROC);
    }

    @Test
    public void updateNonExistingLote() throws Exception {
        int databaseSizeBeforeUpdate = loteRepository.findAll().size();

        // Create the Lote

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoteMockMvc.perform(put("/api/lotes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lote)))
            .andExpect(status().isBadRequest());

        // Validate the Lote in the database
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLote() throws Exception {
        // Initialize the database
        loteRepository.save(lote);

        int databaseSizeBeforeDelete = loteRepository.findAll().size();

        // Delete the lote
        restLoteMockMvc.perform(delete("/api/lotes/{id}", lote.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Lote> loteList = loteRepository.findAll();
        assertThat(loteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lote.class);
        Lote lote1 = new Lote();
        lote1.setId("id1");
        Lote lote2 = new Lote();
        lote2.setId(lote1.getId());
        assertThat(lote1).isEqualTo(lote2);
        lote2.setId("id2");
        assertThat(lote1).isNotEqualTo(lote2);
        lote1.setId(null);
        assertThat(lote1).isNotEqualTo(lote2);
    }
}
