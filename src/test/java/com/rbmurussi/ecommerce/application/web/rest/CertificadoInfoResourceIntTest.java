package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.CertificadoInfo;
import com.rbmurussi.ecommerce.application.repository.CertificadoInfoRepository;
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
 * Test class for the CertificadoInfoResource REST controller.
 *
 * @see CertificadoInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class CertificadoInfoResourceIntTest {

    private static final Integer DEFAULT_ID_CERTIFICADO_INFO = 1;
    private static final Integer UPDATED_ID_CERTIFICADO_INFO = 2;

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    private static final String DEFAULT_ALIAS = "AAAAAAAAAA";
    private static final String UPDATED_ALIAS = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_AUTORIDADE_CERTIFICADORA = "AAAAAAAAAA";
    private static final String UPDATED_AUTORIDADE_CERTIFICADORA = "BBBBBBBBBB";

    private static final String DEFAULT_C_NPJ = "AAAAAAAAAA";
    private static final String UPDATED_C_NPJ = "BBBBBBBBBB";

    private static final String DEFAULT_CAMINHO = "AAAAAAAAAA";
    private static final String UPDATED_CAMINHO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_CERTIFICADO = "AA";
    private static final String UPDATED_TIPO_CERTIFICADO = "BB";

    private static final ZonedDateTime DEFAULT_DATA_UTILIZACAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_UTILIZACAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATA_VALIDADE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_VALIDADE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private CertificadoInfoRepository certificadoInfoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCertificadoInfoMockMvc;

    private CertificadoInfo certificadoInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CertificadoInfoResource certificadoInfoResource = new CertificadoInfoResource(certificadoInfoRepository);
        this.restCertificadoInfoMockMvc = MockMvcBuilders.standaloneSetup(certificadoInfoResource)
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
    public static CertificadoInfo createEntity() {
        CertificadoInfo certificadoInfo = new CertificadoInfo()
            .idCertificadoInfo(DEFAULT_ID_CERTIFICADO_INFO)
            .idEmitente(DEFAULT_ID_EMITENTE)
            .alias(DEFAULT_ALIAS)
            .nome(DEFAULT_NOME)
            .autoridadeCertificadora(DEFAULT_AUTORIDADE_CERTIFICADORA)
            .cNPJ(DEFAULT_C_NPJ)
            .caminho(DEFAULT_CAMINHO)
            .tipoCertificado(DEFAULT_TIPO_CERTIFICADO)
            .dataUtilizacao(DEFAULT_DATA_UTILIZACAO)
            .dataValidade(DEFAULT_DATA_VALIDADE);
        return certificadoInfo;
    }

    @Before
    public void initTest() {
        certificadoInfoRepository.deleteAll();
        certificadoInfo = createEntity();
    }

    @Test
    public void createCertificadoInfo() throws Exception {
        int databaseSizeBeforeCreate = certificadoInfoRepository.findAll().size();

        // Create the CertificadoInfo
        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isCreated());

        // Validate the CertificadoInfo in the database
        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeCreate + 1);
        CertificadoInfo testCertificadoInfo = certificadoInfoList.get(certificadoInfoList.size() - 1);
        assertThat(testCertificadoInfo.getIdCertificadoInfo()).isEqualTo(DEFAULT_ID_CERTIFICADO_INFO);
        assertThat(testCertificadoInfo.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
        assertThat(testCertificadoInfo.getAlias()).isEqualTo(DEFAULT_ALIAS);
        assertThat(testCertificadoInfo.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCertificadoInfo.getAutoridadeCertificadora()).isEqualTo(DEFAULT_AUTORIDADE_CERTIFICADORA);
        assertThat(testCertificadoInfo.getcNPJ()).isEqualTo(DEFAULT_C_NPJ);
        assertThat(testCertificadoInfo.getCaminho()).isEqualTo(DEFAULT_CAMINHO);
        assertThat(testCertificadoInfo.getTipoCertificado()).isEqualTo(DEFAULT_TIPO_CERTIFICADO);
        assertThat(testCertificadoInfo.getDataUtilizacao()).isEqualTo(DEFAULT_DATA_UTILIZACAO);
        assertThat(testCertificadoInfo.getDataValidade()).isEqualTo(DEFAULT_DATA_VALIDADE);
    }

    @Test
    public void createCertificadoInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificadoInfoRepository.findAll().size();

        // Create the CertificadoInfo with an existing ID
        certificadoInfo.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        // Validate the CertificadoInfo in the database
        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdCertificadoInfoIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setIdCertificadoInfo(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setIdEmitente(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAliasIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setAlias(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setNome(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAutoridadeCertificadoraIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setAutoridadeCertificadora(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkcNPJIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setcNPJ(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCaminhoIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setCaminho(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTipoCertificadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setTipoCertificado(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDataValidadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificadoInfoRepository.findAll().size();
        // set the field null
        certificadoInfo.setDataValidade(null);

        // Create the CertificadoInfo, which fails.

        restCertificadoInfoMockMvc.perform(post("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCertificadoInfos() throws Exception {
        // Initialize the database
        certificadoInfoRepository.save(certificadoInfo);

        // Get all the certificadoInfoList
        restCertificadoInfoMockMvc.perform(get("/api/certificado-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificadoInfo.getId())))
            .andExpect(jsonPath("$.[*].idCertificadoInfo").value(hasItem(DEFAULT_ID_CERTIFICADO_INFO)))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].alias").value(hasItem(DEFAULT_ALIAS.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].autoridadeCertificadora").value(hasItem(DEFAULT_AUTORIDADE_CERTIFICADORA.toString())))
            .andExpect(jsonPath("$.[*].cNPJ").value(hasItem(DEFAULT_C_NPJ.toString())))
            .andExpect(jsonPath("$.[*].caminho").value(hasItem(DEFAULT_CAMINHO.toString())))
            .andExpect(jsonPath("$.[*].tipoCertificado").value(hasItem(DEFAULT_TIPO_CERTIFICADO.toString())))
            .andExpect(jsonPath("$.[*].dataUtilizacao").value(hasItem(sameInstant(DEFAULT_DATA_UTILIZACAO))))
            .andExpect(jsonPath("$.[*].dataValidade").value(hasItem(sameInstant(DEFAULT_DATA_VALIDADE))));
    }
    
    @Test
    public void getCertificadoInfo() throws Exception {
        // Initialize the database
        certificadoInfoRepository.save(certificadoInfo);

        // Get the certificadoInfo
        restCertificadoInfoMockMvc.perform(get("/api/certificado-infos/{id}", certificadoInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(certificadoInfo.getId()))
            .andExpect(jsonPath("$.idCertificadoInfo").value(DEFAULT_ID_CERTIFICADO_INFO))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE))
            .andExpect(jsonPath("$.alias").value(DEFAULT_ALIAS.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.autoridadeCertificadora").value(DEFAULT_AUTORIDADE_CERTIFICADORA.toString()))
            .andExpect(jsonPath("$.cNPJ").value(DEFAULT_C_NPJ.toString()))
            .andExpect(jsonPath("$.caminho").value(DEFAULT_CAMINHO.toString()))
            .andExpect(jsonPath("$.tipoCertificado").value(DEFAULT_TIPO_CERTIFICADO.toString()))
            .andExpect(jsonPath("$.dataUtilizacao").value(sameInstant(DEFAULT_DATA_UTILIZACAO)))
            .andExpect(jsonPath("$.dataValidade").value(sameInstant(DEFAULT_DATA_VALIDADE)));
    }

    @Test
    public void getNonExistingCertificadoInfo() throws Exception {
        // Get the certificadoInfo
        restCertificadoInfoMockMvc.perform(get("/api/certificado-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCertificadoInfo() throws Exception {
        // Initialize the database
        certificadoInfoRepository.save(certificadoInfo);

        int databaseSizeBeforeUpdate = certificadoInfoRepository.findAll().size();

        // Update the certificadoInfo
        CertificadoInfo updatedCertificadoInfo = certificadoInfoRepository.findById(certificadoInfo.getId()).get();
        updatedCertificadoInfo
            .idCertificadoInfo(UPDATED_ID_CERTIFICADO_INFO)
            .idEmitente(UPDATED_ID_EMITENTE)
            .alias(UPDATED_ALIAS)
            .nome(UPDATED_NOME)
            .autoridadeCertificadora(UPDATED_AUTORIDADE_CERTIFICADORA)
            .cNPJ(UPDATED_C_NPJ)
            .caminho(UPDATED_CAMINHO)
            .tipoCertificado(UPDATED_TIPO_CERTIFICADO)
            .dataUtilizacao(UPDATED_DATA_UTILIZACAO)
            .dataValidade(UPDATED_DATA_VALIDADE);

        restCertificadoInfoMockMvc.perform(put("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCertificadoInfo)))
            .andExpect(status().isOk());

        // Validate the CertificadoInfo in the database
        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeUpdate);
        CertificadoInfo testCertificadoInfo = certificadoInfoList.get(certificadoInfoList.size() - 1);
        assertThat(testCertificadoInfo.getIdCertificadoInfo()).isEqualTo(UPDATED_ID_CERTIFICADO_INFO);
        assertThat(testCertificadoInfo.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
        assertThat(testCertificadoInfo.getAlias()).isEqualTo(UPDATED_ALIAS);
        assertThat(testCertificadoInfo.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCertificadoInfo.getAutoridadeCertificadora()).isEqualTo(UPDATED_AUTORIDADE_CERTIFICADORA);
        assertThat(testCertificadoInfo.getcNPJ()).isEqualTo(UPDATED_C_NPJ);
        assertThat(testCertificadoInfo.getCaminho()).isEqualTo(UPDATED_CAMINHO);
        assertThat(testCertificadoInfo.getTipoCertificado()).isEqualTo(UPDATED_TIPO_CERTIFICADO);
        assertThat(testCertificadoInfo.getDataUtilizacao()).isEqualTo(UPDATED_DATA_UTILIZACAO);
        assertThat(testCertificadoInfo.getDataValidade()).isEqualTo(UPDATED_DATA_VALIDADE);
    }

    @Test
    public void updateNonExistingCertificadoInfo() throws Exception {
        int databaseSizeBeforeUpdate = certificadoInfoRepository.findAll().size();

        // Create the CertificadoInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertificadoInfoMockMvc.perform(put("/api/certificado-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificadoInfo)))
            .andExpect(status().isBadRequest());

        // Validate the CertificadoInfo in the database
        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCertificadoInfo() throws Exception {
        // Initialize the database
        certificadoInfoRepository.save(certificadoInfo);

        int databaseSizeBeforeDelete = certificadoInfoRepository.findAll().size();

        // Delete the certificadoInfo
        restCertificadoInfoMockMvc.perform(delete("/api/certificado-infos/{id}", certificadoInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CertificadoInfo> certificadoInfoList = certificadoInfoRepository.findAll();
        assertThat(certificadoInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificadoInfo.class);
        CertificadoInfo certificadoInfo1 = new CertificadoInfo();
        certificadoInfo1.setId("id1");
        CertificadoInfo certificadoInfo2 = new CertificadoInfo();
        certificadoInfo2.setId(certificadoInfo1.getId());
        assertThat(certificadoInfo1).isEqualTo(certificadoInfo2);
        certificadoInfo2.setId("id2");
        assertThat(certificadoInfo1).isNotEqualTo(certificadoInfo2);
        certificadoInfo1.setId(null);
        assertThat(certificadoInfo1).isNotEqualTo(certificadoInfo2);
    }
}
