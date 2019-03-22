package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Transportadora;
import com.rbmurussi.ecommerce.application.repository.TransportadoraRepository;
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
 * Test class for the TransportadoraResource REST controller.
 *
 * @see TransportadoraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class TransportadoraResourceIntTest {

    private static final Integer DEFAULT_ID_TRANSPORTADORA = 1;
    private static final Integer UPDATED_ID_TRANSPORTADORA = 2;

    private static final Integer DEFAULT_TP_DOCUMENTO_ENUM = 1;
    private static final Integer UPDATED_TP_DOCUMENTO_ENUM = 2;

    private static final String DEFAULT_NR_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NR_DOCUMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_X_NOME = "AAAAAAAAAA";
    private static final String UPDATED_X_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_I_E = "AAAAAAAAAA";
    private static final String UPDATED_I_E = "BBBBBBBBBB";

    private static final String DEFAULT_X_ENDER = "AAAAAAAAAA";
    private static final String UPDATED_X_ENDER = "BBBBBBBBBB";

    private static final String DEFAULT_U_F = "AA";
    private static final String UPDATED_U_F = "BB";

    private static final String DEFAULT_X_MUN = "AAAAAAAAAA";
    private static final String UPDATED_X_MUN = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    private static final String DEFAULT_VERSAO = "AAAAAAAAAA";
    private static final String UPDATED_VERSAO = "BBBBBBBBBB";

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restTransportadoraMockMvc;

    private Transportadora transportadora;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransportadoraResource transportadoraResource = new TransportadoraResource(transportadoraRepository);
        this.restTransportadoraMockMvc = MockMvcBuilders.standaloneSetup(transportadoraResource)
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
    public static Transportadora createEntity() {
        Transportadora transportadora = new Transportadora()
            .idTransportadora(DEFAULT_ID_TRANSPORTADORA)
            .tpDocumentoEnum(DEFAULT_TP_DOCUMENTO_ENUM)
            .nrDocumento(DEFAULT_NR_DOCUMENTO)
            .xNome(DEFAULT_X_NOME)
            .iE(DEFAULT_I_E)
            .xEnder(DEFAULT_X_ENDER)
            .uF(DEFAULT_U_F)
            .xMun(DEFAULT_X_MUN)
            .idEmitente(DEFAULT_ID_EMITENTE)
            .versao(DEFAULT_VERSAO);
        return transportadora;
    }

    @Before
    public void initTest() {
        transportadoraRepository.deleteAll();
        transportadora = createEntity();
    }

    @Test
    public void createTransportadora() throws Exception {
        int databaseSizeBeforeCreate = transportadoraRepository.findAll().size();

        // Create the Transportadora
        restTransportadoraMockMvc.perform(post("/api/transportadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportadora)))
            .andExpect(status().isCreated());

        // Validate the Transportadora in the database
        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeCreate + 1);
        Transportadora testTransportadora = transportadoraList.get(transportadoraList.size() - 1);
        assertThat(testTransportadora.getIdTransportadora()).isEqualTo(DEFAULT_ID_TRANSPORTADORA);
        assertThat(testTransportadora.getTpDocumentoEnum()).isEqualTo(DEFAULT_TP_DOCUMENTO_ENUM);
        assertThat(testTransportadora.getNrDocumento()).isEqualTo(DEFAULT_NR_DOCUMENTO);
        assertThat(testTransportadora.getxNome()).isEqualTo(DEFAULT_X_NOME);
        assertThat(testTransportadora.getiE()).isEqualTo(DEFAULT_I_E);
        assertThat(testTransportadora.getxEnder()).isEqualTo(DEFAULT_X_ENDER);
        assertThat(testTransportadora.getuF()).isEqualTo(DEFAULT_U_F);
        assertThat(testTransportadora.getxMun()).isEqualTo(DEFAULT_X_MUN);
        assertThat(testTransportadora.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
        assertThat(testTransportadora.getVersao()).isEqualTo(DEFAULT_VERSAO);
    }

    @Test
    public void createTransportadoraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transportadoraRepository.findAll().size();

        // Create the Transportadora with an existing ID
        transportadora.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransportadoraMockMvc.perform(post("/api/transportadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportadora)))
            .andExpect(status().isBadRequest());

        // Validate the Transportadora in the database
        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdTransportadoraIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportadoraRepository.findAll().size();
        // set the field null
        transportadora.setIdTransportadora(null);

        // Create the Transportadora, which fails.

        restTransportadoraMockMvc.perform(post("/api/transportadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportadora)))
            .andExpect(status().isBadRequest());

        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkxNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportadoraRepository.findAll().size();
        // set the field null
        transportadora.setxNome(null);

        // Create the Transportadora, which fails.

        restTransportadoraMockMvc.perform(post("/api/transportadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportadora)))
            .andExpect(status().isBadRequest());

        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportadoraRepository.findAll().size();
        // set the field null
        transportadora.setIdEmitente(null);

        // Create the Transportadora, which fails.

        restTransportadoraMockMvc.perform(post("/api/transportadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportadora)))
            .andExpect(status().isBadRequest());

        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVersaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = transportadoraRepository.findAll().size();
        // set the field null
        transportadora.setVersao(null);

        // Create the Transportadora, which fails.

        restTransportadoraMockMvc.perform(post("/api/transportadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportadora)))
            .andExpect(status().isBadRequest());

        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllTransportadoras() throws Exception {
        // Initialize the database
        transportadoraRepository.save(transportadora);

        // Get all the transportadoraList
        restTransportadoraMockMvc.perform(get("/api/transportadoras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transportadora.getId())))
            .andExpect(jsonPath("$.[*].idTransportadora").value(hasItem(DEFAULT_ID_TRANSPORTADORA)))
            .andExpect(jsonPath("$.[*].tpDocumentoEnum").value(hasItem(DEFAULT_TP_DOCUMENTO_ENUM)))
            .andExpect(jsonPath("$.[*].nrDocumento").value(hasItem(DEFAULT_NR_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].xNome").value(hasItem(DEFAULT_X_NOME.toString())))
            .andExpect(jsonPath("$.[*].iE").value(hasItem(DEFAULT_I_E.toString())))
            .andExpect(jsonPath("$.[*].xEnder").value(hasItem(DEFAULT_X_ENDER.toString())))
            .andExpect(jsonPath("$.[*].uF").value(hasItem(DEFAULT_U_F.toString())))
            .andExpect(jsonPath("$.[*].xMun").value(hasItem(DEFAULT_X_MUN.toString())))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].versao").value(hasItem(DEFAULT_VERSAO.toString())));
    }
    
    @Test
    public void getTransportadora() throws Exception {
        // Initialize the database
        transportadoraRepository.save(transportadora);

        // Get the transportadora
        restTransportadoraMockMvc.perform(get("/api/transportadoras/{id}", transportadora.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transportadora.getId()))
            .andExpect(jsonPath("$.idTransportadora").value(DEFAULT_ID_TRANSPORTADORA))
            .andExpect(jsonPath("$.tpDocumentoEnum").value(DEFAULT_TP_DOCUMENTO_ENUM))
            .andExpect(jsonPath("$.nrDocumento").value(DEFAULT_NR_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.xNome").value(DEFAULT_X_NOME.toString()))
            .andExpect(jsonPath("$.iE").value(DEFAULT_I_E.toString()))
            .andExpect(jsonPath("$.xEnder").value(DEFAULT_X_ENDER.toString()))
            .andExpect(jsonPath("$.uF").value(DEFAULT_U_F.toString()))
            .andExpect(jsonPath("$.xMun").value(DEFAULT_X_MUN.toString()))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE))
            .andExpect(jsonPath("$.versao").value(DEFAULT_VERSAO.toString()));
    }

    @Test
    public void getNonExistingTransportadora() throws Exception {
        // Get the transportadora
        restTransportadoraMockMvc.perform(get("/api/transportadoras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTransportadora() throws Exception {
        // Initialize the database
        transportadoraRepository.save(transportadora);

        int databaseSizeBeforeUpdate = transportadoraRepository.findAll().size();

        // Update the transportadora
        Transportadora updatedTransportadora = transportadoraRepository.findById(transportadora.getId()).get();
        updatedTransportadora
            .idTransportadora(UPDATED_ID_TRANSPORTADORA)
            .tpDocumentoEnum(UPDATED_TP_DOCUMENTO_ENUM)
            .nrDocumento(UPDATED_NR_DOCUMENTO)
            .xNome(UPDATED_X_NOME)
            .iE(UPDATED_I_E)
            .xEnder(UPDATED_X_ENDER)
            .uF(UPDATED_U_F)
            .xMun(UPDATED_X_MUN)
            .idEmitente(UPDATED_ID_EMITENTE)
            .versao(UPDATED_VERSAO);

        restTransportadoraMockMvc.perform(put("/api/transportadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransportadora)))
            .andExpect(status().isOk());

        // Validate the Transportadora in the database
        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeUpdate);
        Transportadora testTransportadora = transportadoraList.get(transportadoraList.size() - 1);
        assertThat(testTransportadora.getIdTransportadora()).isEqualTo(UPDATED_ID_TRANSPORTADORA);
        assertThat(testTransportadora.getTpDocumentoEnum()).isEqualTo(UPDATED_TP_DOCUMENTO_ENUM);
        assertThat(testTransportadora.getNrDocumento()).isEqualTo(UPDATED_NR_DOCUMENTO);
        assertThat(testTransportadora.getxNome()).isEqualTo(UPDATED_X_NOME);
        assertThat(testTransportadora.getiE()).isEqualTo(UPDATED_I_E);
        assertThat(testTransportadora.getxEnder()).isEqualTo(UPDATED_X_ENDER);
        assertThat(testTransportadora.getuF()).isEqualTo(UPDATED_U_F);
        assertThat(testTransportadora.getxMun()).isEqualTo(UPDATED_X_MUN);
        assertThat(testTransportadora.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
        assertThat(testTransportadora.getVersao()).isEqualTo(UPDATED_VERSAO);
    }

    @Test
    public void updateNonExistingTransportadora() throws Exception {
        int databaseSizeBeforeUpdate = transportadoraRepository.findAll().size();

        // Create the Transportadora

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportadoraMockMvc.perform(put("/api/transportadoras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transportadora)))
            .andExpect(status().isBadRequest());

        // Validate the Transportadora in the database
        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTransportadora() throws Exception {
        // Initialize the database
        transportadoraRepository.save(transportadora);

        int databaseSizeBeforeDelete = transportadoraRepository.findAll().size();

        // Delete the transportadora
        restTransportadoraMockMvc.perform(delete("/api/transportadoras/{id}", transportadora.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Transportadora> transportadoraList = transportadoraRepository.findAll();
        assertThat(transportadoraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transportadora.class);
        Transportadora transportadora1 = new Transportadora();
        transportadora1.setId("id1");
        Transportadora transportadora2 = new Transportadora();
        transportadora2.setId(transportadora1.getId());
        assertThat(transportadora1).isEqualTo(transportadora2);
        transportadora2.setId("id2");
        assertThat(transportadora1).isNotEqualTo(transportadora2);
        transportadora1.setId(null);
        assertThat(transportadora1).isNotEqualTo(transportadora2);
    }
}
