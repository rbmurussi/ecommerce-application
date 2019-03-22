package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Icms;
import com.rbmurussi.ecommerce.application.repository.IcmsRepository;
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
 * Test class for the IcmsResource REST controller.
 *
 * @see IcmsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class IcmsResourceIntTest {

    private static final Integer DEFAULT_ID_ICMS = 1;
    private static final Integer UPDATED_ID_ICMS = 2;

    private static final String DEFAULT_ORIG = "AA";
    private static final String UPDATED_ORIG = "BB";

    private static final Integer DEFAULT_ID_PRODUTO = 1;
    private static final Integer UPDATED_ID_PRODUTO = 2;

    private static final String DEFAULT_CST = "AAAAAAA";
    private static final String UPDATED_CST = "BBBBBBB";

    private static final String DEFAULT_MOD_BC = "AA";
    private static final String UPDATED_MOD_BC = "BB";

    private static final String DEFAULT_P_REDBC = "AAAAAA";
    private static final String UPDATED_P_REDBC = "BBBBBB";

    private static final String DEFAULT_P_ICMS = "AAAAAA";
    private static final String UPDATED_P_ICMS = "BBBBBB";

    private static final String DEFAULT_MOD_BCST = "AA";
    private static final String UPDATED_MOD_BCST = "BB";

    private static final String DEFAULT_P_MVAST = "AAAAAA";
    private static final String UPDATED_P_MVAST = "BBBBBB";

    private static final String DEFAULT_P_RED_BCST = "AAAAAA";
    private static final String UPDATED_P_RED_BCST = "BBBBBB";

    private static final String DEFAULT_P_ICMSST = "AAAAAA";
    private static final String UPDATED_P_ICMSST = "BBBBBB";

    private static final String DEFAULT_MOT_DES_ICMS = "AA";
    private static final String UPDATED_MOT_DES_ICMS = "BB";

    private static final String DEFAULT_P_BCOP = "AAAAAA";
    private static final String UPDATED_P_BCOP = "BBBBBB";

    private static final String DEFAULT_U_FST = "AA";
    private static final String UPDATED_U_FST = "BB";

    private static final String DEFAULT_P_CRED_SN = "AAAAAA";
    private static final String UPDATED_P_CRED_SN = "BBBBBB";

    @Autowired
    private IcmsRepository icmsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restIcmsMockMvc;

    private Icms icms;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IcmsResource icmsResource = new IcmsResource(icmsRepository);
        this.restIcmsMockMvc = MockMvcBuilders.standaloneSetup(icmsResource)
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
    public static Icms createEntity() {
        Icms icms = new Icms()
            .idIcms(DEFAULT_ID_ICMS)
            .orig(DEFAULT_ORIG)
            .idProduto(DEFAULT_ID_PRODUTO)
            .cst(DEFAULT_CST)
            .modBc(DEFAULT_MOD_BC)
            .pREDBC(DEFAULT_P_REDBC)
            .pICMS(DEFAULT_P_ICMS)
            .modBCST(DEFAULT_MOD_BCST)
            .pMVAST(DEFAULT_P_MVAST)
            .pRedBCST(DEFAULT_P_RED_BCST)
            .pICMSST(DEFAULT_P_ICMSST)
            .motDesICMS(DEFAULT_MOT_DES_ICMS)
            .pBCOP(DEFAULT_P_BCOP)
            .uFST(DEFAULT_U_FST)
            .pCredSN(DEFAULT_P_CRED_SN);
        return icms;
    }

    @Before
    public void initTest() {
        icmsRepository.deleteAll();
        icms = createEntity();
    }

    @Test
    public void createIcms() throws Exception {
        int databaseSizeBeforeCreate = icmsRepository.findAll().size();

        // Create the Icms
        restIcmsMockMvc.perform(post("/api/icms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(icms)))
            .andExpect(status().isCreated());

        // Validate the Icms in the database
        List<Icms> icmsList = icmsRepository.findAll();
        assertThat(icmsList).hasSize(databaseSizeBeforeCreate + 1);
        Icms testIcms = icmsList.get(icmsList.size() - 1);
        assertThat(testIcms.getIdIcms()).isEqualTo(DEFAULT_ID_ICMS);
        assertThat(testIcms.getOrig()).isEqualTo(DEFAULT_ORIG);
        assertThat(testIcms.getIdProduto()).isEqualTo(DEFAULT_ID_PRODUTO);
        assertThat(testIcms.getCst()).isEqualTo(DEFAULT_CST);
        assertThat(testIcms.getModBc()).isEqualTo(DEFAULT_MOD_BC);
        assertThat(testIcms.getpREDBC()).isEqualTo(DEFAULT_P_REDBC);
        assertThat(testIcms.getpICMS()).isEqualTo(DEFAULT_P_ICMS);
        assertThat(testIcms.getModBCST()).isEqualTo(DEFAULT_MOD_BCST);
        assertThat(testIcms.getpMVAST()).isEqualTo(DEFAULT_P_MVAST);
        assertThat(testIcms.getpRedBCST()).isEqualTo(DEFAULT_P_RED_BCST);
        assertThat(testIcms.getpICMSST()).isEqualTo(DEFAULT_P_ICMSST);
        assertThat(testIcms.getMotDesICMS()).isEqualTo(DEFAULT_MOT_DES_ICMS);
        assertThat(testIcms.getpBCOP()).isEqualTo(DEFAULT_P_BCOP);
        assertThat(testIcms.getuFST()).isEqualTo(DEFAULT_U_FST);
        assertThat(testIcms.getpCredSN()).isEqualTo(DEFAULT_P_CRED_SN);
    }

    @Test
    public void createIcmsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = icmsRepository.findAll().size();

        // Create the Icms with an existing ID
        icms.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restIcmsMockMvc.perform(post("/api/icms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(icms)))
            .andExpect(status().isBadRequest());

        // Validate the Icms in the database
        List<Icms> icmsList = icmsRepository.findAll();
        assertThat(icmsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdIcmsIsRequired() throws Exception {
        int databaseSizeBeforeTest = icmsRepository.findAll().size();
        // set the field null
        icms.setIdIcms(null);

        // Create the Icms, which fails.

        restIcmsMockMvc.perform(post("/api/icms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(icms)))
            .andExpect(status().isBadRequest());

        List<Icms> icmsList = icmsRepository.findAll();
        assertThat(icmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdProdutoIsRequired() throws Exception {
        int databaseSizeBeforeTest = icmsRepository.findAll().size();
        // set the field null
        icms.setIdProduto(null);

        // Create the Icms, which fails.

        restIcmsMockMvc.perform(post("/api/icms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(icms)))
            .andExpect(status().isBadRequest());

        List<Icms> icmsList = icmsRepository.findAll();
        assertThat(icmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCstIsRequired() throws Exception {
        int databaseSizeBeforeTest = icmsRepository.findAll().size();
        // set the field null
        icms.setCst(null);

        // Create the Icms, which fails.

        restIcmsMockMvc.perform(post("/api/icms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(icms)))
            .andExpect(status().isBadRequest());

        List<Icms> icmsList = icmsRepository.findAll();
        assertThat(icmsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllIcms() throws Exception {
        // Initialize the database
        icmsRepository.save(icms);

        // Get all the icmsList
        restIcmsMockMvc.perform(get("/api/icms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(icms.getId())))
            .andExpect(jsonPath("$.[*].idIcms").value(hasItem(DEFAULT_ID_ICMS)))
            .andExpect(jsonPath("$.[*].orig").value(hasItem(DEFAULT_ORIG.toString())))
            .andExpect(jsonPath("$.[*].idProduto").value(hasItem(DEFAULT_ID_PRODUTO)))
            .andExpect(jsonPath("$.[*].cst").value(hasItem(DEFAULT_CST.toString())))
            .andExpect(jsonPath("$.[*].modBc").value(hasItem(DEFAULT_MOD_BC.toString())))
            .andExpect(jsonPath("$.[*].pREDBC").value(hasItem(DEFAULT_P_REDBC.toString())))
            .andExpect(jsonPath("$.[*].pICMS").value(hasItem(DEFAULT_P_ICMS.toString())))
            .andExpect(jsonPath("$.[*].modBCST").value(hasItem(DEFAULT_MOD_BCST.toString())))
            .andExpect(jsonPath("$.[*].pMVAST").value(hasItem(DEFAULT_P_MVAST.toString())))
            .andExpect(jsonPath("$.[*].pRedBCST").value(hasItem(DEFAULT_P_RED_BCST.toString())))
            .andExpect(jsonPath("$.[*].pICMSST").value(hasItem(DEFAULT_P_ICMSST.toString())))
            .andExpect(jsonPath("$.[*].motDesICMS").value(hasItem(DEFAULT_MOT_DES_ICMS.toString())))
            .andExpect(jsonPath("$.[*].pBCOP").value(hasItem(DEFAULT_P_BCOP.toString())))
            .andExpect(jsonPath("$.[*].uFST").value(hasItem(DEFAULT_U_FST.toString())))
            .andExpect(jsonPath("$.[*].pCredSN").value(hasItem(DEFAULT_P_CRED_SN.toString())));
    }
    
    @Test
    public void getIcms() throws Exception {
        // Initialize the database
        icmsRepository.save(icms);

        // Get the icms
        restIcmsMockMvc.perform(get("/api/icms/{id}", icms.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(icms.getId()))
            .andExpect(jsonPath("$.idIcms").value(DEFAULT_ID_ICMS))
            .andExpect(jsonPath("$.orig").value(DEFAULT_ORIG.toString()))
            .andExpect(jsonPath("$.idProduto").value(DEFAULT_ID_PRODUTO))
            .andExpect(jsonPath("$.cst").value(DEFAULT_CST.toString()))
            .andExpect(jsonPath("$.modBc").value(DEFAULT_MOD_BC.toString()))
            .andExpect(jsonPath("$.pREDBC").value(DEFAULT_P_REDBC.toString()))
            .andExpect(jsonPath("$.pICMS").value(DEFAULT_P_ICMS.toString()))
            .andExpect(jsonPath("$.modBCST").value(DEFAULT_MOD_BCST.toString()))
            .andExpect(jsonPath("$.pMVAST").value(DEFAULT_P_MVAST.toString()))
            .andExpect(jsonPath("$.pRedBCST").value(DEFAULT_P_RED_BCST.toString()))
            .andExpect(jsonPath("$.pICMSST").value(DEFAULT_P_ICMSST.toString()))
            .andExpect(jsonPath("$.motDesICMS").value(DEFAULT_MOT_DES_ICMS.toString()))
            .andExpect(jsonPath("$.pBCOP").value(DEFAULT_P_BCOP.toString()))
            .andExpect(jsonPath("$.uFST").value(DEFAULT_U_FST.toString()))
            .andExpect(jsonPath("$.pCredSN").value(DEFAULT_P_CRED_SN.toString()));
    }

    @Test
    public void getNonExistingIcms() throws Exception {
        // Get the icms
        restIcmsMockMvc.perform(get("/api/icms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateIcms() throws Exception {
        // Initialize the database
        icmsRepository.save(icms);

        int databaseSizeBeforeUpdate = icmsRepository.findAll().size();

        // Update the icms
        Icms updatedIcms = icmsRepository.findById(icms.getId()).get();
        updatedIcms
            .idIcms(UPDATED_ID_ICMS)
            .orig(UPDATED_ORIG)
            .idProduto(UPDATED_ID_PRODUTO)
            .cst(UPDATED_CST)
            .modBc(UPDATED_MOD_BC)
            .pREDBC(UPDATED_P_REDBC)
            .pICMS(UPDATED_P_ICMS)
            .modBCST(UPDATED_MOD_BCST)
            .pMVAST(UPDATED_P_MVAST)
            .pRedBCST(UPDATED_P_RED_BCST)
            .pICMSST(UPDATED_P_ICMSST)
            .motDesICMS(UPDATED_MOT_DES_ICMS)
            .pBCOP(UPDATED_P_BCOP)
            .uFST(UPDATED_U_FST)
            .pCredSN(UPDATED_P_CRED_SN);

        restIcmsMockMvc.perform(put("/api/icms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIcms)))
            .andExpect(status().isOk());

        // Validate the Icms in the database
        List<Icms> icmsList = icmsRepository.findAll();
        assertThat(icmsList).hasSize(databaseSizeBeforeUpdate);
        Icms testIcms = icmsList.get(icmsList.size() - 1);
        assertThat(testIcms.getIdIcms()).isEqualTo(UPDATED_ID_ICMS);
        assertThat(testIcms.getOrig()).isEqualTo(UPDATED_ORIG);
        assertThat(testIcms.getIdProduto()).isEqualTo(UPDATED_ID_PRODUTO);
        assertThat(testIcms.getCst()).isEqualTo(UPDATED_CST);
        assertThat(testIcms.getModBc()).isEqualTo(UPDATED_MOD_BC);
        assertThat(testIcms.getpREDBC()).isEqualTo(UPDATED_P_REDBC);
        assertThat(testIcms.getpICMS()).isEqualTo(UPDATED_P_ICMS);
        assertThat(testIcms.getModBCST()).isEqualTo(UPDATED_MOD_BCST);
        assertThat(testIcms.getpMVAST()).isEqualTo(UPDATED_P_MVAST);
        assertThat(testIcms.getpRedBCST()).isEqualTo(UPDATED_P_RED_BCST);
        assertThat(testIcms.getpICMSST()).isEqualTo(UPDATED_P_ICMSST);
        assertThat(testIcms.getMotDesICMS()).isEqualTo(UPDATED_MOT_DES_ICMS);
        assertThat(testIcms.getpBCOP()).isEqualTo(UPDATED_P_BCOP);
        assertThat(testIcms.getuFST()).isEqualTo(UPDATED_U_FST);
        assertThat(testIcms.getpCredSN()).isEqualTo(UPDATED_P_CRED_SN);
    }

    @Test
    public void updateNonExistingIcms() throws Exception {
        int databaseSizeBeforeUpdate = icmsRepository.findAll().size();

        // Create the Icms

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIcmsMockMvc.perform(put("/api/icms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(icms)))
            .andExpect(status().isBadRequest());

        // Validate the Icms in the database
        List<Icms> icmsList = icmsRepository.findAll();
        assertThat(icmsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteIcms() throws Exception {
        // Initialize the database
        icmsRepository.save(icms);

        int databaseSizeBeforeDelete = icmsRepository.findAll().size();

        // Delete the icms
        restIcmsMockMvc.perform(delete("/api/icms/{id}", icms.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Icms> icmsList = icmsRepository.findAll();
        assertThat(icmsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Icms.class);
        Icms icms1 = new Icms();
        icms1.setId("id1");
        Icms icms2 = new Icms();
        icms2.setId(icms1.getId());
        assertThat(icms1).isEqualTo(icms2);
        icms2.setId("id2");
        assertThat(icms1).isNotEqualTo(icms2);
        icms1.setId(null);
        assertThat(icms1).isNotEqualTo(icms2);
    }
}
