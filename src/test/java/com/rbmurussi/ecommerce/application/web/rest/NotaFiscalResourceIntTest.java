package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.NotaFiscal;
import com.rbmurussi.ecommerce.application.repository.NotaFiscalRepository;
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
 * Test class for the NotaFiscalResource REST controller.
 *
 * @see NotaFiscalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class NotaFiscalResourceIntTest {

    private static final Long DEFAULT_ID_NOTAL_FISCAL = 1L;
    private static final Long UPDATED_ID_NOTAL_FISCAL = 2L;

    private static final String DEFAULT_NUMERO = "AAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBB";

    private static final String DEFAULT_SERIE = "AAA";
    private static final String UPDATED_SERIE = "BBB";

    private static final String DEFAULT_MODELO = "AA";
    private static final String UPDATED_MODELO = "BB";

    private static final String DEFAULT_SITUACAO = "AAAAAAAAAA";
    private static final String UPDATED_SITUACAO = "BBBBBBBBBB";

    private static final String DEFAULT_MES = "AA";
    private static final String UPDATED_MES = "BB";

    private static final String DEFAULT_ANO = "AA";
    private static final String UPDATED_ANO = "BB";

    private static final String DEFAULT_TIPO_EMISSAO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_EMISSAO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA_EMISSAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_EMISSAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATA_AUTORIZACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_AUTORIZACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CODIGO_NUMERICO_CHAVE_ACESSO = "AAAAAAAAA";
    private static final String UPDATED_CODIGO_NUMERICO_CHAVE_ACESSO = "BBBBBBBBB";

    private static final String DEFAULT_DIGITO_CHAVE_ACESSO = "A";
    private static final String UPDATED_DIGITO_CHAVE_ACESSO = "B";

    private static final String DEFAULT_AUTORIZACAO_EXPORTADA_XML = "A";
    private static final String UPDATED_AUTORIZACAO_EXPORTADA_XML = "B";

    private static final String DEFAULT_DOCUMENTO_DEST = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTO_DEST = "BBBBBBBBBB";

    private static final String DEFAULT_U_F_DEST = "AA";
    private static final String UPDATED_U_F_DEST = "BB";

    private static final String DEFAULT_NUMERO_RECIBO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_RECIBO = "BBBBBBBBBB";

    private static final String DEFAULT_DANFE_IMPRESSO = "A";
    private static final String UPDATED_DANFE_IMPRESSO = "B";

    private static final byte[] DEFAULT_DOC_XML = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DOC_XML = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DOC_XML_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DOC_XML_CONTENT_TYPE = "image/png";

    private static final ZonedDateTime DEFAULT_DATA_SISTEMA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_SISTEMA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final byte[] DEFAULT_PROTOCOLO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROTOCOLO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROTOCOLO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROTOCOLO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NUMERO_PROTOCOLO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PROTOCOLO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA_PROTOCOLO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_PROTOCOLO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CODIGO_UF_EMITENTE = "AA";
    private static final String UPDATED_CODIGO_UF_EMITENTE = "BB";

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    private static final Long DEFAULT_ID_LOTE = 1L;
    private static final Long UPDATED_ID_LOTE = 2L;

    private static final String DEFAULT_CODIGO_ERRO = "AAA";
    private static final String UPDATED_CODIGO_ERRO = "BBB";

    private static final String DEFAULT_MENSAGEM_ERRO = "AAAAAAAAAA";
    private static final String UPDATED_MENSAGEM_ERRO = "BBBBBBBBBB";

    private static final String DEFAULT_VERSAO = "AAAAAAAAAA";
    private static final String UPDATED_VERSAO = "BBBBBBBBBB";

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restNotaFiscalMockMvc;

    private NotaFiscal notaFiscal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotaFiscalResource notaFiscalResource = new NotaFiscalResource(notaFiscalRepository);
        this.restNotaFiscalMockMvc = MockMvcBuilders.standaloneSetup(notaFiscalResource)
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
    public static NotaFiscal createEntity() {
        NotaFiscal notaFiscal = new NotaFiscal()
            .idNotalFiscal(DEFAULT_ID_NOTAL_FISCAL)
            .numero(DEFAULT_NUMERO)
            .serie(DEFAULT_SERIE)
            .modelo(DEFAULT_MODELO)
            .situacao(DEFAULT_SITUACAO)
            .mes(DEFAULT_MES)
            .ano(DEFAULT_ANO)
            .tipoEmissao(DEFAULT_TIPO_EMISSAO)
            .dataEmissao(DEFAULT_DATA_EMISSAO)
            .dataAutorizacao(DEFAULT_DATA_AUTORIZACAO)
            .codigoNumericoChaveAcesso(DEFAULT_CODIGO_NUMERICO_CHAVE_ACESSO)
            .digitoChaveAcesso(DEFAULT_DIGITO_CHAVE_ACESSO)
            .autorizacaoExportadaXML(DEFAULT_AUTORIZACAO_EXPORTADA_XML)
            .documentoDest(DEFAULT_DOCUMENTO_DEST)
            .uFDest(DEFAULT_U_F_DEST)
            .numeroRecibo(DEFAULT_NUMERO_RECIBO)
            .danfeImpresso(DEFAULT_DANFE_IMPRESSO)
            .docXML(DEFAULT_DOC_XML)
            .docXMLContentType(DEFAULT_DOC_XML_CONTENT_TYPE)
            .dataSistema(DEFAULT_DATA_SISTEMA)
            .protocolo(DEFAULT_PROTOCOLO)
            .protocoloContentType(DEFAULT_PROTOCOLO_CONTENT_TYPE)
            .numeroProtocolo(DEFAULT_NUMERO_PROTOCOLO)
            .dataProtocolo(DEFAULT_DATA_PROTOCOLO)
            .codigoUFEmitente(DEFAULT_CODIGO_UF_EMITENTE)
            .idEmitente(DEFAULT_ID_EMITENTE)
            .idLote(DEFAULT_ID_LOTE)
            .codigoErro(DEFAULT_CODIGO_ERRO)
            .mensagemErro(DEFAULT_MENSAGEM_ERRO)
            .versao(DEFAULT_VERSAO);
        return notaFiscal;
    }

    @Before
    public void initTest() {
        notaFiscalRepository.deleteAll();
        notaFiscal = createEntity();
    }

    @Test
    public void createNotaFiscal() throws Exception {
        int databaseSizeBeforeCreate = notaFiscalRepository.findAll().size();

        // Create the NotaFiscal
        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isCreated());

        // Validate the NotaFiscal in the database
        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeCreate + 1);
        NotaFiscal testNotaFiscal = notaFiscalList.get(notaFiscalList.size() - 1);
        assertThat(testNotaFiscal.getIdNotalFiscal()).isEqualTo(DEFAULT_ID_NOTAL_FISCAL);
        assertThat(testNotaFiscal.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testNotaFiscal.getSerie()).isEqualTo(DEFAULT_SERIE);
        assertThat(testNotaFiscal.getModelo()).isEqualTo(DEFAULT_MODELO);
        assertThat(testNotaFiscal.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testNotaFiscal.getMes()).isEqualTo(DEFAULT_MES);
        assertThat(testNotaFiscal.getAno()).isEqualTo(DEFAULT_ANO);
        assertThat(testNotaFiscal.getTipoEmissao()).isEqualTo(DEFAULT_TIPO_EMISSAO);
        assertThat(testNotaFiscal.getDataEmissao()).isEqualTo(DEFAULT_DATA_EMISSAO);
        assertThat(testNotaFiscal.getDataAutorizacao()).isEqualTo(DEFAULT_DATA_AUTORIZACAO);
        assertThat(testNotaFiscal.getCodigoNumericoChaveAcesso()).isEqualTo(DEFAULT_CODIGO_NUMERICO_CHAVE_ACESSO);
        assertThat(testNotaFiscal.getDigitoChaveAcesso()).isEqualTo(DEFAULT_DIGITO_CHAVE_ACESSO);
        assertThat(testNotaFiscal.getAutorizacaoExportadaXML()).isEqualTo(DEFAULT_AUTORIZACAO_EXPORTADA_XML);
        assertThat(testNotaFiscal.getDocumentoDest()).isEqualTo(DEFAULT_DOCUMENTO_DEST);
        assertThat(testNotaFiscal.getuFDest()).isEqualTo(DEFAULT_U_F_DEST);
        assertThat(testNotaFiscal.getNumeroRecibo()).isEqualTo(DEFAULT_NUMERO_RECIBO);
        assertThat(testNotaFiscal.getDanfeImpresso()).isEqualTo(DEFAULT_DANFE_IMPRESSO);
        assertThat(testNotaFiscal.getDocXML()).isEqualTo(DEFAULT_DOC_XML);
        assertThat(testNotaFiscal.getDocXMLContentType()).isEqualTo(DEFAULT_DOC_XML_CONTENT_TYPE);
        assertThat(testNotaFiscal.getDataSistema()).isEqualTo(DEFAULT_DATA_SISTEMA);
        assertThat(testNotaFiscal.getProtocolo()).isEqualTo(DEFAULT_PROTOCOLO);
        assertThat(testNotaFiscal.getProtocoloContentType()).isEqualTo(DEFAULT_PROTOCOLO_CONTENT_TYPE);
        assertThat(testNotaFiscal.getNumeroProtocolo()).isEqualTo(DEFAULT_NUMERO_PROTOCOLO);
        assertThat(testNotaFiscal.getDataProtocolo()).isEqualTo(DEFAULT_DATA_PROTOCOLO);
        assertThat(testNotaFiscal.getCodigoUFEmitente()).isEqualTo(DEFAULT_CODIGO_UF_EMITENTE);
        assertThat(testNotaFiscal.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
        assertThat(testNotaFiscal.getIdLote()).isEqualTo(DEFAULT_ID_LOTE);
        assertThat(testNotaFiscal.getCodigoErro()).isEqualTo(DEFAULT_CODIGO_ERRO);
        assertThat(testNotaFiscal.getMensagemErro()).isEqualTo(DEFAULT_MENSAGEM_ERRO);
        assertThat(testNotaFiscal.getVersao()).isEqualTo(DEFAULT_VERSAO);
    }

    @Test
    public void createNotaFiscalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notaFiscalRepository.findAll().size();

        // Create the NotaFiscal with an existing ID
        notaFiscal.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        // Validate the NotaFiscal in the database
        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdNotalFiscalIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setIdNotalFiscal(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setNumero(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSerieIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setSerie(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkModeloIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setModelo(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSituacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setSituacao(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setMes(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setAno(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAutorizacaoExportadaXMLIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setAutorizacaoExportadaXML(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDanfeImpressoIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setDanfeImpresso(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCodigoUFEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setCodigoUFEmitente(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setIdEmitente(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVersaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaFiscalRepository.findAll().size();
        // set the field null
        notaFiscal.setVersao(null);

        // Create the NotaFiscal, which fails.

        restNotaFiscalMockMvc.perform(post("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllNotaFiscals() throws Exception {
        // Initialize the database
        notaFiscalRepository.save(notaFiscal);

        // Get all the notaFiscalList
        restNotaFiscalMockMvc.perform(get("/api/nota-fiscals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notaFiscal.getId())))
            .andExpect(jsonPath("$.[*].idNotalFiscal").value(hasItem(DEFAULT_ID_NOTAL_FISCAL.intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].serie").value(hasItem(DEFAULT_SERIE.toString())))
            .andExpect(jsonPath("$.[*].modelo").value(hasItem(DEFAULT_MODELO.toString())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.toString())))
            .andExpect(jsonPath("$.[*].mes").value(hasItem(DEFAULT_MES.toString())))
            .andExpect(jsonPath("$.[*].ano").value(hasItem(DEFAULT_ANO.toString())))
            .andExpect(jsonPath("$.[*].tipoEmissao").value(hasItem(DEFAULT_TIPO_EMISSAO.toString())))
            .andExpect(jsonPath("$.[*].dataEmissao").value(hasItem(sameInstant(DEFAULT_DATA_EMISSAO))))
            .andExpect(jsonPath("$.[*].dataAutorizacao").value(hasItem(sameInstant(DEFAULT_DATA_AUTORIZACAO))))
            .andExpect(jsonPath("$.[*].codigoNumericoChaveAcesso").value(hasItem(DEFAULT_CODIGO_NUMERICO_CHAVE_ACESSO.toString())))
            .andExpect(jsonPath("$.[*].digitoChaveAcesso").value(hasItem(DEFAULT_DIGITO_CHAVE_ACESSO.toString())))
            .andExpect(jsonPath("$.[*].autorizacaoExportadaXML").value(hasItem(DEFAULT_AUTORIZACAO_EXPORTADA_XML.toString())))
            .andExpect(jsonPath("$.[*].documentoDest").value(hasItem(DEFAULT_DOCUMENTO_DEST.toString())))
            .andExpect(jsonPath("$.[*].uFDest").value(hasItem(DEFAULT_U_F_DEST.toString())))
            .andExpect(jsonPath("$.[*].numeroRecibo").value(hasItem(DEFAULT_NUMERO_RECIBO.toString())))
            .andExpect(jsonPath("$.[*].danfeImpresso").value(hasItem(DEFAULT_DANFE_IMPRESSO.toString())))
            .andExpect(jsonPath("$.[*].docXMLContentType").value(hasItem(DEFAULT_DOC_XML_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].docXML").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOC_XML))))
            .andExpect(jsonPath("$.[*].dataSistema").value(hasItem(sameInstant(DEFAULT_DATA_SISTEMA))))
            .andExpect(jsonPath("$.[*].protocoloContentType").value(hasItem(DEFAULT_PROTOCOLO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].protocolo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROTOCOLO))))
            .andExpect(jsonPath("$.[*].numeroProtocolo").value(hasItem(DEFAULT_NUMERO_PROTOCOLO.toString())))
            .andExpect(jsonPath("$.[*].dataProtocolo").value(hasItem(sameInstant(DEFAULT_DATA_PROTOCOLO))))
            .andExpect(jsonPath("$.[*].codigoUFEmitente").value(hasItem(DEFAULT_CODIGO_UF_EMITENTE.toString())))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].idLote").value(hasItem(DEFAULT_ID_LOTE.intValue())))
            .andExpect(jsonPath("$.[*].codigoErro").value(hasItem(DEFAULT_CODIGO_ERRO.toString())))
            .andExpect(jsonPath("$.[*].mensagemErro").value(hasItem(DEFAULT_MENSAGEM_ERRO.toString())))
            .andExpect(jsonPath("$.[*].versao").value(hasItem(DEFAULT_VERSAO.toString())));
    }
    
    @Test
    public void getNotaFiscal() throws Exception {
        // Initialize the database
        notaFiscalRepository.save(notaFiscal);

        // Get the notaFiscal
        restNotaFiscalMockMvc.perform(get("/api/nota-fiscals/{id}", notaFiscal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notaFiscal.getId()))
            .andExpect(jsonPath("$.idNotalFiscal").value(DEFAULT_ID_NOTAL_FISCAL.intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.serie").value(DEFAULT_SERIE.toString()))
            .andExpect(jsonPath("$.modelo").value(DEFAULT_MODELO.toString()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.toString()))
            .andExpect(jsonPath("$.mes").value(DEFAULT_MES.toString()))
            .andExpect(jsonPath("$.ano").value(DEFAULT_ANO.toString()))
            .andExpect(jsonPath("$.tipoEmissao").value(DEFAULT_TIPO_EMISSAO.toString()))
            .andExpect(jsonPath("$.dataEmissao").value(sameInstant(DEFAULT_DATA_EMISSAO)))
            .andExpect(jsonPath("$.dataAutorizacao").value(sameInstant(DEFAULT_DATA_AUTORIZACAO)))
            .andExpect(jsonPath("$.codigoNumericoChaveAcesso").value(DEFAULT_CODIGO_NUMERICO_CHAVE_ACESSO.toString()))
            .andExpect(jsonPath("$.digitoChaveAcesso").value(DEFAULT_DIGITO_CHAVE_ACESSO.toString()))
            .andExpect(jsonPath("$.autorizacaoExportadaXML").value(DEFAULT_AUTORIZACAO_EXPORTADA_XML.toString()))
            .andExpect(jsonPath("$.documentoDest").value(DEFAULT_DOCUMENTO_DEST.toString()))
            .andExpect(jsonPath("$.uFDest").value(DEFAULT_U_F_DEST.toString()))
            .andExpect(jsonPath("$.numeroRecibo").value(DEFAULT_NUMERO_RECIBO.toString()))
            .andExpect(jsonPath("$.danfeImpresso").value(DEFAULT_DANFE_IMPRESSO.toString()))
            .andExpect(jsonPath("$.docXMLContentType").value(DEFAULT_DOC_XML_CONTENT_TYPE))
            .andExpect(jsonPath("$.docXML").value(Base64Utils.encodeToString(DEFAULT_DOC_XML)))
            .andExpect(jsonPath("$.dataSistema").value(sameInstant(DEFAULT_DATA_SISTEMA)))
            .andExpect(jsonPath("$.protocoloContentType").value(DEFAULT_PROTOCOLO_CONTENT_TYPE))
            .andExpect(jsonPath("$.protocolo").value(Base64Utils.encodeToString(DEFAULT_PROTOCOLO)))
            .andExpect(jsonPath("$.numeroProtocolo").value(DEFAULT_NUMERO_PROTOCOLO.toString()))
            .andExpect(jsonPath("$.dataProtocolo").value(sameInstant(DEFAULT_DATA_PROTOCOLO)))
            .andExpect(jsonPath("$.codigoUFEmitente").value(DEFAULT_CODIGO_UF_EMITENTE.toString()))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE))
            .andExpect(jsonPath("$.idLote").value(DEFAULT_ID_LOTE.intValue()))
            .andExpect(jsonPath("$.codigoErro").value(DEFAULT_CODIGO_ERRO.toString()))
            .andExpect(jsonPath("$.mensagemErro").value(DEFAULT_MENSAGEM_ERRO.toString()))
            .andExpect(jsonPath("$.versao").value(DEFAULT_VERSAO.toString()));
    }

    @Test
    public void getNonExistingNotaFiscal() throws Exception {
        // Get the notaFiscal
        restNotaFiscalMockMvc.perform(get("/api/nota-fiscals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNotaFiscal() throws Exception {
        // Initialize the database
        notaFiscalRepository.save(notaFiscal);

        int databaseSizeBeforeUpdate = notaFiscalRepository.findAll().size();

        // Update the notaFiscal
        NotaFiscal updatedNotaFiscal = notaFiscalRepository.findById(notaFiscal.getId()).get();
        updatedNotaFiscal
            .idNotalFiscal(UPDATED_ID_NOTAL_FISCAL)
            .numero(UPDATED_NUMERO)
            .serie(UPDATED_SERIE)
            .modelo(UPDATED_MODELO)
            .situacao(UPDATED_SITUACAO)
            .mes(UPDATED_MES)
            .ano(UPDATED_ANO)
            .tipoEmissao(UPDATED_TIPO_EMISSAO)
            .dataEmissao(UPDATED_DATA_EMISSAO)
            .dataAutorizacao(UPDATED_DATA_AUTORIZACAO)
            .codigoNumericoChaveAcesso(UPDATED_CODIGO_NUMERICO_CHAVE_ACESSO)
            .digitoChaveAcesso(UPDATED_DIGITO_CHAVE_ACESSO)
            .autorizacaoExportadaXML(UPDATED_AUTORIZACAO_EXPORTADA_XML)
            .documentoDest(UPDATED_DOCUMENTO_DEST)
            .uFDest(UPDATED_U_F_DEST)
            .numeroRecibo(UPDATED_NUMERO_RECIBO)
            .danfeImpresso(UPDATED_DANFE_IMPRESSO)
            .docXML(UPDATED_DOC_XML)
            .docXMLContentType(UPDATED_DOC_XML_CONTENT_TYPE)
            .dataSistema(UPDATED_DATA_SISTEMA)
            .protocolo(UPDATED_PROTOCOLO)
            .protocoloContentType(UPDATED_PROTOCOLO_CONTENT_TYPE)
            .numeroProtocolo(UPDATED_NUMERO_PROTOCOLO)
            .dataProtocolo(UPDATED_DATA_PROTOCOLO)
            .codigoUFEmitente(UPDATED_CODIGO_UF_EMITENTE)
            .idEmitente(UPDATED_ID_EMITENTE)
            .idLote(UPDATED_ID_LOTE)
            .codigoErro(UPDATED_CODIGO_ERRO)
            .mensagemErro(UPDATED_MENSAGEM_ERRO)
            .versao(UPDATED_VERSAO);

        restNotaFiscalMockMvc.perform(put("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNotaFiscal)))
            .andExpect(status().isOk());

        // Validate the NotaFiscal in the database
        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeUpdate);
        NotaFiscal testNotaFiscal = notaFiscalList.get(notaFiscalList.size() - 1);
        assertThat(testNotaFiscal.getIdNotalFiscal()).isEqualTo(UPDATED_ID_NOTAL_FISCAL);
        assertThat(testNotaFiscal.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testNotaFiscal.getSerie()).isEqualTo(UPDATED_SERIE);
        assertThat(testNotaFiscal.getModelo()).isEqualTo(UPDATED_MODELO);
        assertThat(testNotaFiscal.getSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testNotaFiscal.getMes()).isEqualTo(UPDATED_MES);
        assertThat(testNotaFiscal.getAno()).isEqualTo(UPDATED_ANO);
        assertThat(testNotaFiscal.getTipoEmissao()).isEqualTo(UPDATED_TIPO_EMISSAO);
        assertThat(testNotaFiscal.getDataEmissao()).isEqualTo(UPDATED_DATA_EMISSAO);
        assertThat(testNotaFiscal.getDataAutorizacao()).isEqualTo(UPDATED_DATA_AUTORIZACAO);
        assertThat(testNotaFiscal.getCodigoNumericoChaveAcesso()).isEqualTo(UPDATED_CODIGO_NUMERICO_CHAVE_ACESSO);
        assertThat(testNotaFiscal.getDigitoChaveAcesso()).isEqualTo(UPDATED_DIGITO_CHAVE_ACESSO);
        assertThat(testNotaFiscal.getAutorizacaoExportadaXML()).isEqualTo(UPDATED_AUTORIZACAO_EXPORTADA_XML);
        assertThat(testNotaFiscal.getDocumentoDest()).isEqualTo(UPDATED_DOCUMENTO_DEST);
        assertThat(testNotaFiscal.getuFDest()).isEqualTo(UPDATED_U_F_DEST);
        assertThat(testNotaFiscal.getNumeroRecibo()).isEqualTo(UPDATED_NUMERO_RECIBO);
        assertThat(testNotaFiscal.getDanfeImpresso()).isEqualTo(UPDATED_DANFE_IMPRESSO);
        assertThat(testNotaFiscal.getDocXML()).isEqualTo(UPDATED_DOC_XML);
        assertThat(testNotaFiscal.getDocXMLContentType()).isEqualTo(UPDATED_DOC_XML_CONTENT_TYPE);
        assertThat(testNotaFiscal.getDataSistema()).isEqualTo(UPDATED_DATA_SISTEMA);
        assertThat(testNotaFiscal.getProtocolo()).isEqualTo(UPDATED_PROTOCOLO);
        assertThat(testNotaFiscal.getProtocoloContentType()).isEqualTo(UPDATED_PROTOCOLO_CONTENT_TYPE);
        assertThat(testNotaFiscal.getNumeroProtocolo()).isEqualTo(UPDATED_NUMERO_PROTOCOLO);
        assertThat(testNotaFiscal.getDataProtocolo()).isEqualTo(UPDATED_DATA_PROTOCOLO);
        assertThat(testNotaFiscal.getCodigoUFEmitente()).isEqualTo(UPDATED_CODIGO_UF_EMITENTE);
        assertThat(testNotaFiscal.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
        assertThat(testNotaFiscal.getIdLote()).isEqualTo(UPDATED_ID_LOTE);
        assertThat(testNotaFiscal.getCodigoErro()).isEqualTo(UPDATED_CODIGO_ERRO);
        assertThat(testNotaFiscal.getMensagemErro()).isEqualTo(UPDATED_MENSAGEM_ERRO);
        assertThat(testNotaFiscal.getVersao()).isEqualTo(UPDATED_VERSAO);
    }

    @Test
    public void updateNonExistingNotaFiscal() throws Exception {
        int databaseSizeBeforeUpdate = notaFiscalRepository.findAll().size();

        // Create the NotaFiscal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotaFiscalMockMvc.perform(put("/api/nota-fiscals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notaFiscal)))
            .andExpect(status().isBadRequest());

        // Validate the NotaFiscal in the database
        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteNotaFiscal() throws Exception {
        // Initialize the database
        notaFiscalRepository.save(notaFiscal);

        int databaseSizeBeforeDelete = notaFiscalRepository.findAll().size();

        // Delete the notaFiscal
        restNotaFiscalMockMvc.perform(delete("/api/nota-fiscals/{id}", notaFiscal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NotaFiscal> notaFiscalList = notaFiscalRepository.findAll();
        assertThat(notaFiscalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotaFiscal.class);
        NotaFiscal notaFiscal1 = new NotaFiscal();
        notaFiscal1.setId("id1");
        NotaFiscal notaFiscal2 = new NotaFiscal();
        notaFiscal2.setId(notaFiscal1.getId());
        assertThat(notaFiscal1).isEqualTo(notaFiscal2);
        notaFiscal2.setId("id2");
        assertThat(notaFiscal1).isNotEqualTo(notaFiscal2);
        notaFiscal1.setId(null);
        assertThat(notaFiscal1).isNotEqualTo(notaFiscal2);
    }
}
