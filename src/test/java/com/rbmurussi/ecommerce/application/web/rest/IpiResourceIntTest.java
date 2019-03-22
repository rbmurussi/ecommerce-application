package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Ipi;
import com.rbmurussi.ecommerce.application.repository.IpiRepository;
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
 * Test class for the IpiResource REST controller.
 *
 * @see IpiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class IpiResourceIntTest {

    private static final Integer DEFAULT_ID_IPI = 1;
    private static final Integer UPDATED_ID_IPI = 2;

    private static final String DEFAULT_CL_ENQ = "AAAAA";
    private static final String UPDATED_CL_ENQ = "BBBBB";

    private static final String DEFAULT_C_NPJ_PROD = "AAAAAAAAAA";
    private static final String UPDATED_C_NPJ_PROD = "BBBBBBBBBB";

    private static final String DEFAULT_C_ENQ = "AAA";
    private static final String UPDATED_C_ENQ = "BBB";

    private static final Integer DEFAULT_ID_PRODUTO = 1;
    private static final Integer UPDATED_ID_PRODUTO = 2;

    @Autowired
    private IpiRepository ipiRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restIpiMockMvc;

    private Ipi ipi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IpiResource ipiResource = new IpiResource(ipiRepository);
        this.restIpiMockMvc = MockMvcBuilders.standaloneSetup(ipiResource)
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
    public static Ipi createEntity() {
        Ipi ipi = new Ipi()
            .idIpi(DEFAULT_ID_IPI)
            .clEnq(DEFAULT_CL_ENQ)
            .cNPJProd(DEFAULT_C_NPJ_PROD)
            .cEnq(DEFAULT_C_ENQ)
            .idProduto(DEFAULT_ID_PRODUTO);
        return ipi;
    }

    @Before
    public void initTest() {
        ipiRepository.deleteAll();
        ipi = createEntity();
    }

    @Test
    public void createIpi() throws Exception {
        int databaseSizeBeforeCreate = ipiRepository.findAll().size();

        // Create the Ipi
        restIpiMockMvc.perform(post("/api/ipis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipi)))
            .andExpect(status().isCreated());

        // Validate the Ipi in the database
        List<Ipi> ipiList = ipiRepository.findAll();
        assertThat(ipiList).hasSize(databaseSizeBeforeCreate + 1);
        Ipi testIpi = ipiList.get(ipiList.size() - 1);
        assertThat(testIpi.getIdIpi()).isEqualTo(DEFAULT_ID_IPI);
        assertThat(testIpi.getClEnq()).isEqualTo(DEFAULT_CL_ENQ);
        assertThat(testIpi.getcNPJProd()).isEqualTo(DEFAULT_C_NPJ_PROD);
        assertThat(testIpi.getcEnq()).isEqualTo(DEFAULT_C_ENQ);
        assertThat(testIpi.getIdProduto()).isEqualTo(DEFAULT_ID_PRODUTO);
    }

    @Test
    public void createIpiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ipiRepository.findAll().size();

        // Create the Ipi with an existing ID
        ipi.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restIpiMockMvc.perform(post("/api/ipis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipi)))
            .andExpect(status().isBadRequest());

        // Validate the Ipi in the database
        List<Ipi> ipiList = ipiRepository.findAll();
        assertThat(ipiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdIpiIsRequired() throws Exception {
        int databaseSizeBeforeTest = ipiRepository.findAll().size();
        // set the field null
        ipi.setIdIpi(null);

        // Create the Ipi, which fails.

        restIpiMockMvc.perform(post("/api/ipis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipi)))
            .andExpect(status().isBadRequest());

        List<Ipi> ipiList = ipiRepository.findAll();
        assertThat(ipiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdProdutoIsRequired() throws Exception {
        int databaseSizeBeforeTest = ipiRepository.findAll().size();
        // set the field null
        ipi.setIdProduto(null);

        // Create the Ipi, which fails.

        restIpiMockMvc.perform(post("/api/ipis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipi)))
            .andExpect(status().isBadRequest());

        List<Ipi> ipiList = ipiRepository.findAll();
        assertThat(ipiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllIpis() throws Exception {
        // Initialize the database
        ipiRepository.save(ipi);

        // Get all the ipiList
        restIpiMockMvc.perform(get("/api/ipis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ipi.getId())))
            .andExpect(jsonPath("$.[*].idIpi").value(hasItem(DEFAULT_ID_IPI)))
            .andExpect(jsonPath("$.[*].clEnq").value(hasItem(DEFAULT_CL_ENQ.toString())))
            .andExpect(jsonPath("$.[*].cNPJProd").value(hasItem(DEFAULT_C_NPJ_PROD.toString())))
            .andExpect(jsonPath("$.[*].cEnq").value(hasItem(DEFAULT_C_ENQ.toString())))
            .andExpect(jsonPath("$.[*].idProduto").value(hasItem(DEFAULT_ID_PRODUTO)));
    }
    
    @Test
    public void getIpi() throws Exception {
        // Initialize the database
        ipiRepository.save(ipi);

        // Get the ipi
        restIpiMockMvc.perform(get("/api/ipis/{id}", ipi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ipi.getId()))
            .andExpect(jsonPath("$.idIpi").value(DEFAULT_ID_IPI))
            .andExpect(jsonPath("$.clEnq").value(DEFAULT_CL_ENQ.toString()))
            .andExpect(jsonPath("$.cNPJProd").value(DEFAULT_C_NPJ_PROD.toString()))
            .andExpect(jsonPath("$.cEnq").value(DEFAULT_C_ENQ.toString()))
            .andExpect(jsonPath("$.idProduto").value(DEFAULT_ID_PRODUTO));
    }

    @Test
    public void getNonExistingIpi() throws Exception {
        // Get the ipi
        restIpiMockMvc.perform(get("/api/ipis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateIpi() throws Exception {
        // Initialize the database
        ipiRepository.save(ipi);

        int databaseSizeBeforeUpdate = ipiRepository.findAll().size();

        // Update the ipi
        Ipi updatedIpi = ipiRepository.findById(ipi.getId()).get();
        updatedIpi
            .idIpi(UPDATED_ID_IPI)
            .clEnq(UPDATED_CL_ENQ)
            .cNPJProd(UPDATED_C_NPJ_PROD)
            .cEnq(UPDATED_C_ENQ)
            .idProduto(UPDATED_ID_PRODUTO);

        restIpiMockMvc.perform(put("/api/ipis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIpi)))
            .andExpect(status().isOk());

        // Validate the Ipi in the database
        List<Ipi> ipiList = ipiRepository.findAll();
        assertThat(ipiList).hasSize(databaseSizeBeforeUpdate);
        Ipi testIpi = ipiList.get(ipiList.size() - 1);
        assertThat(testIpi.getIdIpi()).isEqualTo(UPDATED_ID_IPI);
        assertThat(testIpi.getClEnq()).isEqualTo(UPDATED_CL_ENQ);
        assertThat(testIpi.getcNPJProd()).isEqualTo(UPDATED_C_NPJ_PROD);
        assertThat(testIpi.getcEnq()).isEqualTo(UPDATED_C_ENQ);
        assertThat(testIpi.getIdProduto()).isEqualTo(UPDATED_ID_PRODUTO);
    }

    @Test
    public void updateNonExistingIpi() throws Exception {
        int databaseSizeBeforeUpdate = ipiRepository.findAll().size();

        // Create the Ipi

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIpiMockMvc.perform(put("/api/ipis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipi)))
            .andExpect(status().isBadRequest());

        // Validate the Ipi in the database
        List<Ipi> ipiList = ipiRepository.findAll();
        assertThat(ipiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteIpi() throws Exception {
        // Initialize the database
        ipiRepository.save(ipi);

        int databaseSizeBeforeDelete = ipiRepository.findAll().size();

        // Delete the ipi
        restIpiMockMvc.perform(delete("/api/ipis/{id}", ipi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ipi> ipiList = ipiRepository.findAll();
        assertThat(ipiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ipi.class);
        Ipi ipi1 = new Ipi();
        ipi1.setId("id1");
        Ipi ipi2 = new Ipi();
        ipi2.setId(ipi1.getId());
        assertThat(ipi1).isEqualTo(ipi2);
        ipi2.setId("id2");
        assertThat(ipi1).isNotEqualTo(ipi2);
        ipi1.setId(null);
        assertThat(ipi1).isNotEqualTo(ipi2);
    }
}
