package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Cancelamento;
import com.rbmurussi.ecommerce.application.repository.CancelamentoRepository;
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
 * Test class for the CancelamentoResource REST controller.
 *
 * @see CancelamentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class CancelamentoResourceIntTest {

    private static final Long DEFAULT_ID_NOTAL_FISCAL = 1L;
    private static final Long UPDATED_ID_NOTAL_FISCAL = 2L;

    private static final String DEFAULT_NUMERO_PROTOCOLO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PROTOCOLO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PROTOCOLO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROTOCOLO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROTOCOLO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROTOCOLO_CONTENT_TYPE = "image/png";

    private static final ZonedDateTime DEFAULT_DATA_PROTOCOLO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_PROTOCOLO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CODIGO_ERRO = "AAA";
    private static final String UPDATED_CODIGO_ERRO = "BBB";

    private static final String DEFAULT_MENSAGEM_ERRO = "AAAAAAAAAA";
    private static final String UPDATED_MENSAGEM_ERRO = "BBBBBBBBBB";

    private static final String DEFAULT_JUSTIFICATIVA = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICATIVA = "BBBBBBBBBB";

    @Autowired
    private CancelamentoRepository cancelamentoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCancelamentoMockMvc;

    private Cancelamento cancelamento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CancelamentoResource cancelamentoResource = new CancelamentoResource(cancelamentoRepository);
        this.restCancelamentoMockMvc = MockMvcBuilders.standaloneSetup(cancelamentoResource)
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
    public static Cancelamento createEntity() {
        Cancelamento cancelamento = new Cancelamento()
            .idNotalFiscal(DEFAULT_ID_NOTAL_FISCAL)
            .numeroProtocolo(DEFAULT_NUMERO_PROTOCOLO)
            .protocolo(DEFAULT_PROTOCOLO)
            .protocoloContentType(DEFAULT_PROTOCOLO_CONTENT_TYPE)
            .dataProtocolo(DEFAULT_DATA_PROTOCOLO)
            .codigoErro(DEFAULT_CODIGO_ERRO)
            .mensagemErro(DEFAULT_MENSAGEM_ERRO)
            .justificativa(DEFAULT_JUSTIFICATIVA);
        return cancelamento;
    }

    @Before
    public void initTest() {
        cancelamentoRepository.deleteAll();
        cancelamento = createEntity();
    }

    @Test
    public void createCancelamento() throws Exception {
        int databaseSizeBeforeCreate = cancelamentoRepository.findAll().size();

        // Create the Cancelamento
        restCancelamentoMockMvc.perform(post("/api/cancelamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cancelamento)))
            .andExpect(status().isCreated());

        // Validate the Cancelamento in the database
        List<Cancelamento> cancelamentoList = cancelamentoRepository.findAll();
        assertThat(cancelamentoList).hasSize(databaseSizeBeforeCreate + 1);
        Cancelamento testCancelamento = cancelamentoList.get(cancelamentoList.size() - 1);
        assertThat(testCancelamento.getIdNotalFiscal()).isEqualTo(DEFAULT_ID_NOTAL_FISCAL);
        assertThat(testCancelamento.getNumeroProtocolo()).isEqualTo(DEFAULT_NUMERO_PROTOCOLO);
        assertThat(testCancelamento.getProtocolo()).isEqualTo(DEFAULT_PROTOCOLO);
        assertThat(testCancelamento.getProtocoloContentType()).isEqualTo(DEFAULT_PROTOCOLO_CONTENT_TYPE);
        assertThat(testCancelamento.getDataProtocolo()).isEqualTo(DEFAULT_DATA_PROTOCOLO);
        assertThat(testCancelamento.getCodigoErro()).isEqualTo(DEFAULT_CODIGO_ERRO);
        assertThat(testCancelamento.getMensagemErro()).isEqualTo(DEFAULT_MENSAGEM_ERRO);
        assertThat(testCancelamento.getJustificativa()).isEqualTo(DEFAULT_JUSTIFICATIVA);
    }

    @Test
    public void createCancelamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cancelamentoRepository.findAll().size();

        // Create the Cancelamento with an existing ID
        cancelamento.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCancelamentoMockMvc.perform(post("/api/cancelamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cancelamento)))
            .andExpect(status().isBadRequest());

        // Validate the Cancelamento in the database
        List<Cancelamento> cancelamentoList = cancelamentoRepository.findAll();
        assertThat(cancelamentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdNotalFiscalIsRequired() throws Exception {
        int databaseSizeBeforeTest = cancelamentoRepository.findAll().size();
        // set the field null
        cancelamento.setIdNotalFiscal(null);

        // Create the Cancelamento, which fails.

        restCancelamentoMockMvc.perform(post("/api/cancelamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cancelamento)))
            .andExpect(status().isBadRequest());

        List<Cancelamento> cancelamentoList = cancelamentoRepository.findAll();
        assertThat(cancelamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNumeroProtocoloIsRequired() throws Exception {
        int databaseSizeBeforeTest = cancelamentoRepository.findAll().size();
        // set the field null
        cancelamento.setNumeroProtocolo(null);

        // Create the Cancelamento, which fails.

        restCancelamentoMockMvc.perform(post("/api/cancelamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cancelamento)))
            .andExpect(status().isBadRequest());

        List<Cancelamento> cancelamentoList = cancelamentoRepository.findAll();
        assertThat(cancelamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDataProtocoloIsRequired() throws Exception {
        int databaseSizeBeforeTest = cancelamentoRepository.findAll().size();
        // set the field null
        cancelamento.setDataProtocolo(null);

        // Create the Cancelamento, which fails.

        restCancelamentoMockMvc.perform(post("/api/cancelamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cancelamento)))
            .andExpect(status().isBadRequest());

        List<Cancelamento> cancelamentoList = cancelamentoRepository.findAll();
        assertThat(cancelamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCancelamentos() throws Exception {
        // Initialize the database
        cancelamentoRepository.save(cancelamento);

        // Get all the cancelamentoList
        restCancelamentoMockMvc.perform(get("/api/cancelamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cancelamento.getId())))
            .andExpect(jsonPath("$.[*].idNotalFiscal").value(hasItem(DEFAULT_ID_NOTAL_FISCAL.intValue())))
            .andExpect(jsonPath("$.[*].numeroProtocolo").value(hasItem(DEFAULT_NUMERO_PROTOCOLO.toString())))
            .andExpect(jsonPath("$.[*].protocoloContentType").value(hasItem(DEFAULT_PROTOCOLO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].protocolo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROTOCOLO))))
            .andExpect(jsonPath("$.[*].dataProtocolo").value(hasItem(sameInstant(DEFAULT_DATA_PROTOCOLO))))
            .andExpect(jsonPath("$.[*].codigoErro").value(hasItem(DEFAULT_CODIGO_ERRO.toString())))
            .andExpect(jsonPath("$.[*].mensagemErro").value(hasItem(DEFAULT_MENSAGEM_ERRO.toString())))
            .andExpect(jsonPath("$.[*].justificativa").value(hasItem(DEFAULT_JUSTIFICATIVA.toString())));
    }
    
    @Test
    public void getCancelamento() throws Exception {
        // Initialize the database
        cancelamentoRepository.save(cancelamento);

        // Get the cancelamento
        restCancelamentoMockMvc.perform(get("/api/cancelamentos/{id}", cancelamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cancelamento.getId()))
            .andExpect(jsonPath("$.idNotalFiscal").value(DEFAULT_ID_NOTAL_FISCAL.intValue()))
            .andExpect(jsonPath("$.numeroProtocolo").value(DEFAULT_NUMERO_PROTOCOLO.toString()))
            .andExpect(jsonPath("$.protocoloContentType").value(DEFAULT_PROTOCOLO_CONTENT_TYPE))
            .andExpect(jsonPath("$.protocolo").value(Base64Utils.encodeToString(DEFAULT_PROTOCOLO)))
            .andExpect(jsonPath("$.dataProtocolo").value(sameInstant(DEFAULT_DATA_PROTOCOLO)))
            .andExpect(jsonPath("$.codigoErro").value(DEFAULT_CODIGO_ERRO.toString()))
            .andExpect(jsonPath("$.mensagemErro").value(DEFAULT_MENSAGEM_ERRO.toString()))
            .andExpect(jsonPath("$.justificativa").value(DEFAULT_JUSTIFICATIVA.toString()));
    }

    @Test
    public void getNonExistingCancelamento() throws Exception {
        // Get the cancelamento
        restCancelamentoMockMvc.perform(get("/api/cancelamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCancelamento() throws Exception {
        // Initialize the database
        cancelamentoRepository.save(cancelamento);

        int databaseSizeBeforeUpdate = cancelamentoRepository.findAll().size();

        // Update the cancelamento
        Cancelamento updatedCancelamento = cancelamentoRepository.findById(cancelamento.getId()).get();
        updatedCancelamento
            .idNotalFiscal(UPDATED_ID_NOTAL_FISCAL)
            .numeroProtocolo(UPDATED_NUMERO_PROTOCOLO)
            .protocolo(UPDATED_PROTOCOLO)
            .protocoloContentType(UPDATED_PROTOCOLO_CONTENT_TYPE)
            .dataProtocolo(UPDATED_DATA_PROTOCOLO)
            .codigoErro(UPDATED_CODIGO_ERRO)
            .mensagemErro(UPDATED_MENSAGEM_ERRO)
            .justificativa(UPDATED_JUSTIFICATIVA);

        restCancelamentoMockMvc.perform(put("/api/cancelamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCancelamento)))
            .andExpect(status().isOk());

        // Validate the Cancelamento in the database
        List<Cancelamento> cancelamentoList = cancelamentoRepository.findAll();
        assertThat(cancelamentoList).hasSize(databaseSizeBeforeUpdate);
        Cancelamento testCancelamento = cancelamentoList.get(cancelamentoList.size() - 1);
        assertThat(testCancelamento.getIdNotalFiscal()).isEqualTo(UPDATED_ID_NOTAL_FISCAL);
        assertThat(testCancelamento.getNumeroProtocolo()).isEqualTo(UPDATED_NUMERO_PROTOCOLO);
        assertThat(testCancelamento.getProtocolo()).isEqualTo(UPDATED_PROTOCOLO);
        assertThat(testCancelamento.getProtocoloContentType()).isEqualTo(UPDATED_PROTOCOLO_CONTENT_TYPE);
        assertThat(testCancelamento.getDataProtocolo()).isEqualTo(UPDATED_DATA_PROTOCOLO);
        assertThat(testCancelamento.getCodigoErro()).isEqualTo(UPDATED_CODIGO_ERRO);
        assertThat(testCancelamento.getMensagemErro()).isEqualTo(UPDATED_MENSAGEM_ERRO);
        assertThat(testCancelamento.getJustificativa()).isEqualTo(UPDATED_JUSTIFICATIVA);
    }

    @Test
    public void updateNonExistingCancelamento() throws Exception {
        int databaseSizeBeforeUpdate = cancelamentoRepository.findAll().size();

        // Create the Cancelamento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCancelamentoMockMvc.perform(put("/api/cancelamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cancelamento)))
            .andExpect(status().isBadRequest());

        // Validate the Cancelamento in the database
        List<Cancelamento> cancelamentoList = cancelamentoRepository.findAll();
        assertThat(cancelamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCancelamento() throws Exception {
        // Initialize the database
        cancelamentoRepository.save(cancelamento);

        int databaseSizeBeforeDelete = cancelamentoRepository.findAll().size();

        // Delete the cancelamento
        restCancelamentoMockMvc.perform(delete("/api/cancelamentos/{id}", cancelamento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cancelamento> cancelamentoList = cancelamentoRepository.findAll();
        assertThat(cancelamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cancelamento.class);
        Cancelamento cancelamento1 = new Cancelamento();
        cancelamento1.setId("id1");
        Cancelamento cancelamento2 = new Cancelamento();
        cancelamento2.setId(cancelamento1.getId());
        assertThat(cancelamento1).isEqualTo(cancelamento2);
        cancelamento2.setId("id2");
        assertThat(cancelamento1).isNotEqualTo(cancelamento2);
        cancelamento1.setId(null);
        assertThat(cancelamento1).isNotEqualTo(cancelamento2);
    }
}
