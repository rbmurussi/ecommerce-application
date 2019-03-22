package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Emitente;
import com.rbmurussi.ecommerce.application.repository.EmitenteRepository;
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

import java.util.List;


import static com.rbmurussi.ecommerce.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EmitenteResource REST controller.
 *
 * @see EmitenteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class EmitenteResourceIntTest {

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    private static final String DEFAULT_X_NOME = "AAAAAAAAAA";
    private static final String UPDATED_X_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_X_FANT = "AAAAAAAAAA";
    private static final String UPDATED_X_FANT = "BBBBBBBBBB";

    private static final String DEFAULT_X_LGR = "AAAAAAAAAA";
    private static final String UPDATED_X_LGR = "BBBBBBBBBB";

    private static final String DEFAULT_N_RO = "AAAAAAAAAA";
    private static final String UPDATED_N_RO = "BBBBBBBBBB";

    private static final String DEFAULT_X_CPL = "AAAAAAAAAA";
    private static final String UPDATED_X_CPL = "BBBBBBBBBB";

    private static final String DEFAULT_X_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_X_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_C_MUN = "AAAAAAA";
    private static final String UPDATED_C_MUN = "BBBBBBB";

    private static final String DEFAULT_X_MUN = "AAAAAAAAAA";
    private static final String UPDATED_X_MUN = "BBBBBBBBBB";

    private static final String DEFAULT_U_F = "AA";
    private static final String UPDATED_U_F = "BB";

    private static final String DEFAULT_C_EP = "AAAAAAAA";
    private static final String UPDATED_C_EP = "BBBBBBBB";

    private static final String DEFAULT_C_PAIS = "AAAA";
    private static final String UPDATED_C_PAIS = "BBBB";

    private static final String DEFAULT_X_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_X_PAIS = "BBBBBBBBBB";

    private static final String DEFAULT_FONE = "AAAAAAAAAA";
    private static final String UPDATED_FONE = "BBBBBBBBBB";

    private static final String DEFAULT_I_E = "AAAAAAAAAA";
    private static final String UPDATED_I_E = "BBBBBBBBBB";

    private static final String DEFAULT_I_EST = "AAAAAAAAAA";
    private static final String UPDATED_I_EST = "BBBBBBBBBB";

    private static final String DEFAULT_I_M = "AAAAAAAAAA";
    private static final String UPDATED_I_M = "BBBBBBBBBB";

    private static final String DEFAULT_C_NAE = "AAAAAAA";
    private static final String UPDATED_C_NAE = "BBBBBBB";

    private static final byte[] DEFAULT_LOGOTIPO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGOTIPO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGOTIPO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGOTIPO_CONTENT_TYPE = "image/png";

    private static final Integer DEFAULT_TP_DOCUMENTO_ENUM = 1;
    private static final Integer UPDATED_TP_DOCUMENTO_ENUM = 2;

    private static final String DEFAULT_NR_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NR_DOCUMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_REGIME_TRIBUTARIO = "AAAA";
    private static final String UPDATED_REGIME_TRIBUTARIO = "BBBB";

    private static final String DEFAULT_VERSAO = "AAAAAAAAAA";
    private static final String UPDATED_VERSAO = "BBBBBBBBBB";

    @Autowired
    private EmitenteRepository emitenteRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restEmitenteMockMvc;

    private Emitente emitente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmitenteResource emitenteResource = new EmitenteResource(emitenteRepository);
        this.restEmitenteMockMvc = MockMvcBuilders.standaloneSetup(emitenteResource)
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
    public static Emitente createEntity() {
        Emitente emitente = new Emitente()
            .idEmitente(DEFAULT_ID_EMITENTE)
            .xNome(DEFAULT_X_NOME)
            .xFant(DEFAULT_X_FANT)
            .xLgr(DEFAULT_X_LGR)
            .nRo(DEFAULT_N_RO)
            .xCpl(DEFAULT_X_CPL)
            .xBairro(DEFAULT_X_BAIRRO)
            .cMun(DEFAULT_C_MUN)
            .xMun(DEFAULT_X_MUN)
            .uF(DEFAULT_U_F)
            .cEP(DEFAULT_C_EP)
            .cPais(DEFAULT_C_PAIS)
            .xPais(DEFAULT_X_PAIS)
            .fone(DEFAULT_FONE)
            .iE(DEFAULT_I_E)
            .iEST(DEFAULT_I_EST)
            .iM(DEFAULT_I_M)
            .cNAE(DEFAULT_C_NAE)
            .logotipo(DEFAULT_LOGOTIPO)
            .logotipoContentType(DEFAULT_LOGOTIPO_CONTENT_TYPE)
            .tpDocumentoEnum(DEFAULT_TP_DOCUMENTO_ENUM)
            .nrDocumento(DEFAULT_NR_DOCUMENTO)
            .regimeTributario(DEFAULT_REGIME_TRIBUTARIO)
            .versao(DEFAULT_VERSAO);
        return emitente;
    }

    @Before
    public void initTest() {
        emitenteRepository.deleteAll();
        emitente = createEntity();
    }

    @Test
    public void createEmitente() throws Exception {
        int databaseSizeBeforeCreate = emitenteRepository.findAll().size();

        // Create the Emitente
        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isCreated());

        // Validate the Emitente in the database
        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeCreate + 1);
        Emitente testEmitente = emitenteList.get(emitenteList.size() - 1);
        assertThat(testEmitente.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
        assertThat(testEmitente.getxNome()).isEqualTo(DEFAULT_X_NOME);
        assertThat(testEmitente.getxFant()).isEqualTo(DEFAULT_X_FANT);
        assertThat(testEmitente.getxLgr()).isEqualTo(DEFAULT_X_LGR);
        assertThat(testEmitente.getnRo()).isEqualTo(DEFAULT_N_RO);
        assertThat(testEmitente.getxCpl()).isEqualTo(DEFAULT_X_CPL);
        assertThat(testEmitente.getxBairro()).isEqualTo(DEFAULT_X_BAIRRO);
        assertThat(testEmitente.getcMun()).isEqualTo(DEFAULT_C_MUN);
        assertThat(testEmitente.getxMun()).isEqualTo(DEFAULT_X_MUN);
        assertThat(testEmitente.getuF()).isEqualTo(DEFAULT_U_F);
        assertThat(testEmitente.getcEP()).isEqualTo(DEFAULT_C_EP);
        assertThat(testEmitente.getcPais()).isEqualTo(DEFAULT_C_PAIS);
        assertThat(testEmitente.getxPais()).isEqualTo(DEFAULT_X_PAIS);
        assertThat(testEmitente.getFone()).isEqualTo(DEFAULT_FONE);
        assertThat(testEmitente.getiE()).isEqualTo(DEFAULT_I_E);
        assertThat(testEmitente.getiEST()).isEqualTo(DEFAULT_I_EST);
        assertThat(testEmitente.getiM()).isEqualTo(DEFAULT_I_M);
        assertThat(testEmitente.getcNAE()).isEqualTo(DEFAULT_C_NAE);
        assertThat(testEmitente.getLogotipo()).isEqualTo(DEFAULT_LOGOTIPO);
        assertThat(testEmitente.getLogotipoContentType()).isEqualTo(DEFAULT_LOGOTIPO_CONTENT_TYPE);
        assertThat(testEmitente.getTpDocumentoEnum()).isEqualTo(DEFAULT_TP_DOCUMENTO_ENUM);
        assertThat(testEmitente.getNrDocumento()).isEqualTo(DEFAULT_NR_DOCUMENTO);
        assertThat(testEmitente.getRegimeTributario()).isEqualTo(DEFAULT_REGIME_TRIBUTARIO);
        assertThat(testEmitente.getVersao()).isEqualTo(DEFAULT_VERSAO);
    }

    @Test
    public void createEmitenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emitenteRepository.findAll().size();

        // Create the Emitente with an existing ID
        emitente.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        // Validate the Emitente in the database
        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setIdEmitente(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkxNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setxNome(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkxLgrIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setxLgr(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checknRoIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setnRo(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkxBairroIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setxBairro(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkcMunIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setcMun(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkxMunIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setxMun(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkuFIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setuF(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkiEIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setiE(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTpDocumentoEnumIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setTpDocumentoEnum(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNrDocumentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setNrDocumento(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRegimeTributarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setRegimeTributario(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVersaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = emitenteRepository.findAll().size();
        // set the field null
        emitente.setVersao(null);

        // Create the Emitente, which fails.

        restEmitenteMockMvc.perform(post("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllEmitentes() throws Exception {
        // Initialize the database
        emitenteRepository.save(emitente);

        // Get all the emitenteList
        restEmitenteMockMvc.perform(get("/api/emitentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emitente.getId())))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].xNome").value(hasItem(DEFAULT_X_NOME.toString())))
            .andExpect(jsonPath("$.[*].xFant").value(hasItem(DEFAULT_X_FANT.toString())))
            .andExpect(jsonPath("$.[*].xLgr").value(hasItem(DEFAULT_X_LGR.toString())))
            .andExpect(jsonPath("$.[*].nRo").value(hasItem(DEFAULT_N_RO.toString())))
            .andExpect(jsonPath("$.[*].xCpl").value(hasItem(DEFAULT_X_CPL.toString())))
            .andExpect(jsonPath("$.[*].xBairro").value(hasItem(DEFAULT_X_BAIRRO.toString())))
            .andExpect(jsonPath("$.[*].cMun").value(hasItem(DEFAULT_C_MUN.toString())))
            .andExpect(jsonPath("$.[*].xMun").value(hasItem(DEFAULT_X_MUN.toString())))
            .andExpect(jsonPath("$.[*].uF").value(hasItem(DEFAULT_U_F.toString())))
            .andExpect(jsonPath("$.[*].cEP").value(hasItem(DEFAULT_C_EP.toString())))
            .andExpect(jsonPath("$.[*].cPais").value(hasItem(DEFAULT_C_PAIS.toString())))
            .andExpect(jsonPath("$.[*].xPais").value(hasItem(DEFAULT_X_PAIS.toString())))
            .andExpect(jsonPath("$.[*].fone").value(hasItem(DEFAULT_FONE.toString())))
            .andExpect(jsonPath("$.[*].iE").value(hasItem(DEFAULT_I_E.toString())))
            .andExpect(jsonPath("$.[*].iEST").value(hasItem(DEFAULT_I_EST.toString())))
            .andExpect(jsonPath("$.[*].iM").value(hasItem(DEFAULT_I_M.toString())))
            .andExpect(jsonPath("$.[*].cNAE").value(hasItem(DEFAULT_C_NAE.toString())))
            .andExpect(jsonPath("$.[*].logotipoContentType").value(hasItem(DEFAULT_LOGOTIPO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logotipo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGOTIPO))))
            .andExpect(jsonPath("$.[*].tpDocumentoEnum").value(hasItem(DEFAULT_TP_DOCUMENTO_ENUM)))
            .andExpect(jsonPath("$.[*].nrDocumento").value(hasItem(DEFAULT_NR_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].regimeTributario").value(hasItem(DEFAULT_REGIME_TRIBUTARIO.toString())))
            .andExpect(jsonPath("$.[*].versao").value(hasItem(DEFAULT_VERSAO.toString())));
    }
    
    @Test
    public void getEmitente() throws Exception {
        // Initialize the database
        emitenteRepository.save(emitente);

        // Get the emitente
        restEmitenteMockMvc.perform(get("/api/emitentes/{id}", emitente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emitente.getId()))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE))
            .andExpect(jsonPath("$.xNome").value(DEFAULT_X_NOME.toString()))
            .andExpect(jsonPath("$.xFant").value(DEFAULT_X_FANT.toString()))
            .andExpect(jsonPath("$.xLgr").value(DEFAULT_X_LGR.toString()))
            .andExpect(jsonPath("$.nRo").value(DEFAULT_N_RO.toString()))
            .andExpect(jsonPath("$.xCpl").value(DEFAULT_X_CPL.toString()))
            .andExpect(jsonPath("$.xBairro").value(DEFAULT_X_BAIRRO.toString()))
            .andExpect(jsonPath("$.cMun").value(DEFAULT_C_MUN.toString()))
            .andExpect(jsonPath("$.xMun").value(DEFAULT_X_MUN.toString()))
            .andExpect(jsonPath("$.uF").value(DEFAULT_U_F.toString()))
            .andExpect(jsonPath("$.cEP").value(DEFAULT_C_EP.toString()))
            .andExpect(jsonPath("$.cPais").value(DEFAULT_C_PAIS.toString()))
            .andExpect(jsonPath("$.xPais").value(DEFAULT_X_PAIS.toString()))
            .andExpect(jsonPath("$.fone").value(DEFAULT_FONE.toString()))
            .andExpect(jsonPath("$.iE").value(DEFAULT_I_E.toString()))
            .andExpect(jsonPath("$.iEST").value(DEFAULT_I_EST.toString()))
            .andExpect(jsonPath("$.iM").value(DEFAULT_I_M.toString()))
            .andExpect(jsonPath("$.cNAE").value(DEFAULT_C_NAE.toString()))
            .andExpect(jsonPath("$.logotipoContentType").value(DEFAULT_LOGOTIPO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logotipo").value(Base64Utils.encodeToString(DEFAULT_LOGOTIPO)))
            .andExpect(jsonPath("$.tpDocumentoEnum").value(DEFAULT_TP_DOCUMENTO_ENUM))
            .andExpect(jsonPath("$.nrDocumento").value(DEFAULT_NR_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.regimeTributario").value(DEFAULT_REGIME_TRIBUTARIO.toString()))
            .andExpect(jsonPath("$.versao").value(DEFAULT_VERSAO.toString()));
    }

    @Test
    public void getNonExistingEmitente() throws Exception {
        // Get the emitente
        restEmitenteMockMvc.perform(get("/api/emitentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmitente() throws Exception {
        // Initialize the database
        emitenteRepository.save(emitente);

        int databaseSizeBeforeUpdate = emitenteRepository.findAll().size();

        // Update the emitente
        Emitente updatedEmitente = emitenteRepository.findById(emitente.getId()).get();
        updatedEmitente
            .idEmitente(UPDATED_ID_EMITENTE)
            .xNome(UPDATED_X_NOME)
            .xFant(UPDATED_X_FANT)
            .xLgr(UPDATED_X_LGR)
            .nRo(UPDATED_N_RO)
            .xCpl(UPDATED_X_CPL)
            .xBairro(UPDATED_X_BAIRRO)
            .cMun(UPDATED_C_MUN)
            .xMun(UPDATED_X_MUN)
            .uF(UPDATED_U_F)
            .cEP(UPDATED_C_EP)
            .cPais(UPDATED_C_PAIS)
            .xPais(UPDATED_X_PAIS)
            .fone(UPDATED_FONE)
            .iE(UPDATED_I_E)
            .iEST(UPDATED_I_EST)
            .iM(UPDATED_I_M)
            .cNAE(UPDATED_C_NAE)
            .logotipo(UPDATED_LOGOTIPO)
            .logotipoContentType(UPDATED_LOGOTIPO_CONTENT_TYPE)
            .tpDocumentoEnum(UPDATED_TP_DOCUMENTO_ENUM)
            .nrDocumento(UPDATED_NR_DOCUMENTO)
            .regimeTributario(UPDATED_REGIME_TRIBUTARIO)
            .versao(UPDATED_VERSAO);

        restEmitenteMockMvc.perform(put("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmitente)))
            .andExpect(status().isOk());

        // Validate the Emitente in the database
        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeUpdate);
        Emitente testEmitente = emitenteList.get(emitenteList.size() - 1);
        assertThat(testEmitente.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
        assertThat(testEmitente.getxNome()).isEqualTo(UPDATED_X_NOME);
        assertThat(testEmitente.getxFant()).isEqualTo(UPDATED_X_FANT);
        assertThat(testEmitente.getxLgr()).isEqualTo(UPDATED_X_LGR);
        assertThat(testEmitente.getnRo()).isEqualTo(UPDATED_N_RO);
        assertThat(testEmitente.getxCpl()).isEqualTo(UPDATED_X_CPL);
        assertThat(testEmitente.getxBairro()).isEqualTo(UPDATED_X_BAIRRO);
        assertThat(testEmitente.getcMun()).isEqualTo(UPDATED_C_MUN);
        assertThat(testEmitente.getxMun()).isEqualTo(UPDATED_X_MUN);
        assertThat(testEmitente.getuF()).isEqualTo(UPDATED_U_F);
        assertThat(testEmitente.getcEP()).isEqualTo(UPDATED_C_EP);
        assertThat(testEmitente.getcPais()).isEqualTo(UPDATED_C_PAIS);
        assertThat(testEmitente.getxPais()).isEqualTo(UPDATED_X_PAIS);
        assertThat(testEmitente.getFone()).isEqualTo(UPDATED_FONE);
        assertThat(testEmitente.getiE()).isEqualTo(UPDATED_I_E);
        assertThat(testEmitente.getiEST()).isEqualTo(UPDATED_I_EST);
        assertThat(testEmitente.getiM()).isEqualTo(UPDATED_I_M);
        assertThat(testEmitente.getcNAE()).isEqualTo(UPDATED_C_NAE);
        assertThat(testEmitente.getLogotipo()).isEqualTo(UPDATED_LOGOTIPO);
        assertThat(testEmitente.getLogotipoContentType()).isEqualTo(UPDATED_LOGOTIPO_CONTENT_TYPE);
        assertThat(testEmitente.getTpDocumentoEnum()).isEqualTo(UPDATED_TP_DOCUMENTO_ENUM);
        assertThat(testEmitente.getNrDocumento()).isEqualTo(UPDATED_NR_DOCUMENTO);
        assertThat(testEmitente.getRegimeTributario()).isEqualTo(UPDATED_REGIME_TRIBUTARIO);
        assertThat(testEmitente.getVersao()).isEqualTo(UPDATED_VERSAO);
    }

    @Test
    public void updateNonExistingEmitente() throws Exception {
        int databaseSizeBeforeUpdate = emitenteRepository.findAll().size();

        // Create the Emitente

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmitenteMockMvc.perform(put("/api/emitentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emitente)))
            .andExpect(status().isBadRequest());

        // Validate the Emitente in the database
        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEmitente() throws Exception {
        // Initialize the database
        emitenteRepository.save(emitente);

        int databaseSizeBeforeDelete = emitenteRepository.findAll().size();

        // Delete the emitente
        restEmitenteMockMvc.perform(delete("/api/emitentes/{id}", emitente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Emitente> emitenteList = emitenteRepository.findAll();
        assertThat(emitenteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emitente.class);
        Emitente emitente1 = new Emitente();
        emitente1.setId("id1");
        Emitente emitente2 = new Emitente();
        emitente2.setId(emitente1.getId());
        assertThat(emitente1).isEqualTo(emitente2);
        emitente2.setId("id2");
        assertThat(emitente1).isNotEqualTo(emitente2);
        emitente1.setId(null);
        assertThat(emitente1).isNotEqualTo(emitente2);
    }
}
