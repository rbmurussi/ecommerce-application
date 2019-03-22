package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Inutilizacao;
import com.rbmurussi.ecommerce.application.repository.InutilizacaoRepository;
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
 * Test class for the InutilizacaoResource REST controller.
 *
 * @see InutilizacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class InutilizacaoResourceIntTest {

    private static final Long DEFAULT_ID_INUTILIZACAO = 1L;
    private static final Long UPDATED_ID_INUTILIZACAO = 2L;

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    private static final String DEFAULT_SERIE = "AAA";
    private static final String UPDATED_SERIE = "BBB";

    private static final String DEFAULT_NUMERO_INICIAL = "AAAAAAAAA";
    private static final String UPDATED_NUMERO_INICIAL = "BBBBBBBBB";

    private static final String DEFAULT_NUMERO_FINAL = "AAAAAAAAA";
    private static final String UPDATED_NUMERO_FINAL = "BBBBBBBBB";

    private static final byte[] DEFAULT_PROTOCOLO_XML = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROTOCOLO_XML = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROTOCOLO_XML_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROTOCOLO_XML_CONTENT_TYPE = "image/png";

    private static final ZonedDateTime DEFAULT_DATA_INUTILIZACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_INUTILIZACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private InutilizacaoRepository inutilizacaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restInutilizacaoMockMvc;

    private Inutilizacao inutilizacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InutilizacaoResource inutilizacaoResource = new InutilizacaoResource(inutilizacaoRepository);
        this.restInutilizacaoMockMvc = MockMvcBuilders.standaloneSetup(inutilizacaoResource)
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
    public static Inutilizacao createEntity() {
        Inutilizacao inutilizacao = new Inutilizacao()
            .idInutilizacao(DEFAULT_ID_INUTILIZACAO)
            .idEmitente(DEFAULT_ID_EMITENTE)
            .serie(DEFAULT_SERIE)
            .numeroInicial(DEFAULT_NUMERO_INICIAL)
            .numeroFinal(DEFAULT_NUMERO_FINAL)
            .protocoloXml(DEFAULT_PROTOCOLO_XML)
            .protocoloXmlContentType(DEFAULT_PROTOCOLO_XML_CONTENT_TYPE)
            .dataInutilizacao(DEFAULT_DATA_INUTILIZACAO);
        return inutilizacao;
    }

    @Before
    public void initTest() {
        inutilizacaoRepository.deleteAll();
        inutilizacao = createEntity();
    }

    @Test
    public void createInutilizacao() throws Exception {
        int databaseSizeBeforeCreate = inutilizacaoRepository.findAll().size();

        // Create the Inutilizacao
        restInutilizacaoMockMvc.perform(post("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inutilizacao)))
            .andExpect(status().isCreated());

        // Validate the Inutilizacao in the database
        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeCreate + 1);
        Inutilizacao testInutilizacao = inutilizacaoList.get(inutilizacaoList.size() - 1);
        assertThat(testInutilizacao.getIdInutilizacao()).isEqualTo(DEFAULT_ID_INUTILIZACAO);
        assertThat(testInutilizacao.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
        assertThat(testInutilizacao.getSerie()).isEqualTo(DEFAULT_SERIE);
        assertThat(testInutilizacao.getNumeroInicial()).isEqualTo(DEFAULT_NUMERO_INICIAL);
        assertThat(testInutilizacao.getNumeroFinal()).isEqualTo(DEFAULT_NUMERO_FINAL);
        assertThat(testInutilizacao.getProtocoloXml()).isEqualTo(DEFAULT_PROTOCOLO_XML);
        assertThat(testInutilizacao.getProtocoloXmlContentType()).isEqualTo(DEFAULT_PROTOCOLO_XML_CONTENT_TYPE);
        assertThat(testInutilizacao.getDataInutilizacao()).isEqualTo(DEFAULT_DATA_INUTILIZACAO);
    }

    @Test
    public void createInutilizacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inutilizacaoRepository.findAll().size();

        // Create the Inutilizacao with an existing ID
        inutilizacao.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restInutilizacaoMockMvc.perform(post("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inutilizacao)))
            .andExpect(status().isBadRequest());

        // Validate the Inutilizacao in the database
        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdInutilizacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = inutilizacaoRepository.findAll().size();
        // set the field null
        inutilizacao.setIdInutilizacao(null);

        // Create the Inutilizacao, which fails.

        restInutilizacaoMockMvc.perform(post("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inutilizacao)))
            .andExpect(status().isBadRequest());

        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = inutilizacaoRepository.findAll().size();
        // set the field null
        inutilizacao.setIdEmitente(null);

        // Create the Inutilizacao, which fails.

        restInutilizacaoMockMvc.perform(post("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inutilizacao)))
            .andExpect(status().isBadRequest());

        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSerieIsRequired() throws Exception {
        int databaseSizeBeforeTest = inutilizacaoRepository.findAll().size();
        // set the field null
        inutilizacao.setSerie(null);

        // Create the Inutilizacao, which fails.

        restInutilizacaoMockMvc.perform(post("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inutilizacao)))
            .andExpect(status().isBadRequest());

        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNumeroInicialIsRequired() throws Exception {
        int databaseSizeBeforeTest = inutilizacaoRepository.findAll().size();
        // set the field null
        inutilizacao.setNumeroInicial(null);

        // Create the Inutilizacao, which fails.

        restInutilizacaoMockMvc.perform(post("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inutilizacao)))
            .andExpect(status().isBadRequest());

        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNumeroFinalIsRequired() throws Exception {
        int databaseSizeBeforeTest = inutilizacaoRepository.findAll().size();
        // set the field null
        inutilizacao.setNumeroFinal(null);

        // Create the Inutilizacao, which fails.

        restInutilizacaoMockMvc.perform(post("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inutilizacao)))
            .andExpect(status().isBadRequest());

        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllInutilizacaos() throws Exception {
        // Initialize the database
        inutilizacaoRepository.save(inutilizacao);

        // Get all the inutilizacaoList
        restInutilizacaoMockMvc.perform(get("/api/inutilizacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inutilizacao.getId())))
            .andExpect(jsonPath("$.[*].idInutilizacao").value(hasItem(DEFAULT_ID_INUTILIZACAO.intValue())))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].serie").value(hasItem(DEFAULT_SERIE.toString())))
            .andExpect(jsonPath("$.[*].numeroInicial").value(hasItem(DEFAULT_NUMERO_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].numeroFinal").value(hasItem(DEFAULT_NUMERO_FINAL.toString())))
            .andExpect(jsonPath("$.[*].protocoloXmlContentType").value(hasItem(DEFAULT_PROTOCOLO_XML_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].protocoloXml").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROTOCOLO_XML))))
            .andExpect(jsonPath("$.[*].dataInutilizacao").value(hasItem(sameInstant(DEFAULT_DATA_INUTILIZACAO))));
    }
    
    @Test
    public void getInutilizacao() throws Exception {
        // Initialize the database
        inutilizacaoRepository.save(inutilizacao);

        // Get the inutilizacao
        restInutilizacaoMockMvc.perform(get("/api/inutilizacaos/{id}", inutilizacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(inutilizacao.getId()))
            .andExpect(jsonPath("$.idInutilizacao").value(DEFAULT_ID_INUTILIZACAO.intValue()))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE))
            .andExpect(jsonPath("$.serie").value(DEFAULT_SERIE.toString()))
            .andExpect(jsonPath("$.numeroInicial").value(DEFAULT_NUMERO_INICIAL.toString()))
            .andExpect(jsonPath("$.numeroFinal").value(DEFAULT_NUMERO_FINAL.toString()))
            .andExpect(jsonPath("$.protocoloXmlContentType").value(DEFAULT_PROTOCOLO_XML_CONTENT_TYPE))
            .andExpect(jsonPath("$.protocoloXml").value(Base64Utils.encodeToString(DEFAULT_PROTOCOLO_XML)))
            .andExpect(jsonPath("$.dataInutilizacao").value(sameInstant(DEFAULT_DATA_INUTILIZACAO)));
    }

    @Test
    public void getNonExistingInutilizacao() throws Exception {
        // Get the inutilizacao
        restInutilizacaoMockMvc.perform(get("/api/inutilizacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInutilizacao() throws Exception {
        // Initialize the database
        inutilizacaoRepository.save(inutilizacao);

        int databaseSizeBeforeUpdate = inutilizacaoRepository.findAll().size();

        // Update the inutilizacao
        Inutilizacao updatedInutilizacao = inutilizacaoRepository.findById(inutilizacao.getId()).get();
        updatedInutilizacao
            .idInutilizacao(UPDATED_ID_INUTILIZACAO)
            .idEmitente(UPDATED_ID_EMITENTE)
            .serie(UPDATED_SERIE)
            .numeroInicial(UPDATED_NUMERO_INICIAL)
            .numeroFinal(UPDATED_NUMERO_FINAL)
            .protocoloXml(UPDATED_PROTOCOLO_XML)
            .protocoloXmlContentType(UPDATED_PROTOCOLO_XML_CONTENT_TYPE)
            .dataInutilizacao(UPDATED_DATA_INUTILIZACAO);

        restInutilizacaoMockMvc.perform(put("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInutilizacao)))
            .andExpect(status().isOk());

        // Validate the Inutilizacao in the database
        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeUpdate);
        Inutilizacao testInutilizacao = inutilizacaoList.get(inutilizacaoList.size() - 1);
        assertThat(testInutilizacao.getIdInutilizacao()).isEqualTo(UPDATED_ID_INUTILIZACAO);
        assertThat(testInutilizacao.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
        assertThat(testInutilizacao.getSerie()).isEqualTo(UPDATED_SERIE);
        assertThat(testInutilizacao.getNumeroInicial()).isEqualTo(UPDATED_NUMERO_INICIAL);
        assertThat(testInutilizacao.getNumeroFinal()).isEqualTo(UPDATED_NUMERO_FINAL);
        assertThat(testInutilizacao.getProtocoloXml()).isEqualTo(UPDATED_PROTOCOLO_XML);
        assertThat(testInutilizacao.getProtocoloXmlContentType()).isEqualTo(UPDATED_PROTOCOLO_XML_CONTENT_TYPE);
        assertThat(testInutilizacao.getDataInutilizacao()).isEqualTo(UPDATED_DATA_INUTILIZACAO);
    }

    @Test
    public void updateNonExistingInutilizacao() throws Exception {
        int databaseSizeBeforeUpdate = inutilizacaoRepository.findAll().size();

        // Create the Inutilizacao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInutilizacaoMockMvc.perform(put("/api/inutilizacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inutilizacao)))
            .andExpect(status().isBadRequest());

        // Validate the Inutilizacao in the database
        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteInutilizacao() throws Exception {
        // Initialize the database
        inutilizacaoRepository.save(inutilizacao);

        int databaseSizeBeforeDelete = inutilizacaoRepository.findAll().size();

        // Delete the inutilizacao
        restInutilizacaoMockMvc.perform(delete("/api/inutilizacaos/{id}", inutilizacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Inutilizacao> inutilizacaoList = inutilizacaoRepository.findAll();
        assertThat(inutilizacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Inutilizacao.class);
        Inutilizacao inutilizacao1 = new Inutilizacao();
        inutilizacao1.setId("id1");
        Inutilizacao inutilizacao2 = new Inutilizacao();
        inutilizacao2.setId(inutilizacao1.getId());
        assertThat(inutilizacao1).isEqualTo(inutilizacao2);
        inutilizacao2.setId("id2");
        assertThat(inutilizacao1).isNotEqualTo(inutilizacao2);
        inutilizacao1.setId(null);
        assertThat(inutilizacao1).isNotEqualTo(inutilizacao2);
    }
}
