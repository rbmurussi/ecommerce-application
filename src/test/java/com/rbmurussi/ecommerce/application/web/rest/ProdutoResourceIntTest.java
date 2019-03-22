package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Produto;
import com.rbmurussi.ecommerce.application.repository.ProdutoRepository;
import com.rbmurussi.ecommerce.application.repository.search.ProdutoSearchRepository;
import com.rbmurussi.ecommerce.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;


import static com.rbmurussi.ecommerce.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProdutoResource REST controller.
 *
 * @see ProdutoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class ProdutoResourceIntTest {

    private static final String DEFAULT_C_PROD = "AAAAAAAAAA";
    private static final String UPDATED_C_PROD = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_PRODUTO = 1;
    private static final Integer UPDATED_ID_PRODUTO = 2;

    private static final String DEFAULT_X_PROD = "AAAAAAAAAA";
    private static final String UPDATED_X_PROD = "BBBBBBBBBB";

    private static final String DEFAULT_C_EAN = "AAAAAAAAAA";
    private static final String UPDATED_C_EAN = "BBBBBBBBBB";

    private static final String DEFAULT_N_CM = "AAAAAAAA";
    private static final String UPDATED_N_CM = "BBBBBBBB";

    private static final String DEFAULT_EX_TIPI = "AAA";
    private static final String UPDATED_EX_TIPI = "BBB";

    private static final String DEFAULT_GENERO = "AA";
    private static final String UPDATED_GENERO = "BB";

    private static final String DEFAULT_U_COM = "AAAAAA";
    private static final String UPDATED_U_COM = "BBBBBB";

    private static final String DEFAULT_C_EAN_TRIB = "AAAAAAAAAA";
    private static final String UPDATED_C_EAN_TRIB = "BBBBBBBBBB";

    private static final String DEFAULT_U_TRIB = "AAAAAA";
    private static final String UPDATED_U_TRIB = "BBBBBB";

    private static final String DEFAULT_V_UN_COM = "AAAAAAAAAA";
    private static final String UPDATED_V_UN_COM = "BBBBBBBBBB";

    private static final String DEFAULT_V_UN_TRIB = "AAAAAAAAAA";
    private static final String UPDATED_V_UN_TRIB = "BBBBBBBBBB";

    private static final String DEFAULT_Q_TRIB = "AAAAAAAAAA";
    private static final String UPDATED_Q_TRIB = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    private static final String DEFAULT_VERSAO = "AAAAAAAAAA";
    private static final String UPDATED_VERSAO = "BBBBBBBBBB";

    private static final String DEFAULT_CEST = "AAAAAAA";
    private static final String UPDATED_CEST = "BBBBBBB";

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * This repository is mocked in the com.rbmurussi.ecommerce.application.repository.search test package.
     *
     * @see com.rbmurussi.ecommerce.application.repository.search.ProdutoSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProdutoSearchRepository mockProdutoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restProdutoMockMvc;

    private Produto produto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProdutoResource produtoResource = new ProdutoResource(produtoRepository, mockProdutoSearchRepository);
        this.restProdutoMockMvc = MockMvcBuilders.standaloneSetup(produtoResource)
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
    public static Produto createEntity() {
        Produto produto = new Produto()
            .cProd(DEFAULT_C_PROD)
            .idProduto(DEFAULT_ID_PRODUTO)
            .xProd(DEFAULT_X_PROD)
            .cEAN(DEFAULT_C_EAN)
            .nCM(DEFAULT_N_CM)
            .exTipi(DEFAULT_EX_TIPI)
            .genero(DEFAULT_GENERO)
            .uCom(DEFAULT_U_COM)
            .cEANTrib(DEFAULT_C_EAN_TRIB)
            .uTrib(DEFAULT_U_TRIB)
            .vUNCom(DEFAULT_V_UN_COM)
            .vUNTrib(DEFAULT_V_UN_TRIB)
            .qTrib(DEFAULT_Q_TRIB)
            .idEmitente(DEFAULT_ID_EMITENTE)
            .versao(DEFAULT_VERSAO)
            .cest(DEFAULT_CEST);
        return produto;
    }

    @Before
    public void initTest() {
        produtoRepository.deleteAll();
        produto = createEntity();
    }

    @Test
    public void createProduto() throws Exception {
        int databaseSizeBeforeCreate = produtoRepository.findAll().size();

        // Create the Produto
        restProdutoMockMvc.perform(post("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produto)))
            .andExpect(status().isCreated());

        // Validate the Produto in the database
        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeCreate + 1);
        Produto testProduto = produtoList.get(produtoList.size() - 1);
        assertThat(testProduto.getcProd()).isEqualTo(DEFAULT_C_PROD);
        assertThat(testProduto.getIdProduto()).isEqualTo(DEFAULT_ID_PRODUTO);
        assertThat(testProduto.getxProd()).isEqualTo(DEFAULT_X_PROD);
        assertThat(testProduto.getcEAN()).isEqualTo(DEFAULT_C_EAN);
        assertThat(testProduto.getnCM()).isEqualTo(DEFAULT_N_CM);
        assertThat(testProduto.getExTipi()).isEqualTo(DEFAULT_EX_TIPI);
        assertThat(testProduto.getGenero()).isEqualTo(DEFAULT_GENERO);
        assertThat(testProduto.getuCom()).isEqualTo(DEFAULT_U_COM);
        assertThat(testProduto.getcEANTrib()).isEqualTo(DEFAULT_C_EAN_TRIB);
        assertThat(testProduto.getuTrib()).isEqualTo(DEFAULT_U_TRIB);
        assertThat(testProduto.getvUNCom()).isEqualTo(DEFAULT_V_UN_COM);
        assertThat(testProduto.getvUNTrib()).isEqualTo(DEFAULT_V_UN_TRIB);
        assertThat(testProduto.getqTrib()).isEqualTo(DEFAULT_Q_TRIB);
        assertThat(testProduto.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
        assertThat(testProduto.getVersao()).isEqualTo(DEFAULT_VERSAO);
        assertThat(testProduto.getCest()).isEqualTo(DEFAULT_CEST);

        // Validate the Produto in Elasticsearch
        verify(mockProdutoSearchRepository, times(1)).save(testProduto);
    }

    @Test
    public void createProdutoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = produtoRepository.findAll().size();

        // Create the Produto with an existing ID
        produto.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProdutoMockMvc.perform(post("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produto)))
            .andExpect(status().isBadRequest());

        // Validate the Produto in the database
        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeCreate);

        // Validate the Produto in Elasticsearch
        verify(mockProdutoSearchRepository, times(0)).save(produto);
    }

    @Test
    public void checkcProdIsRequired() throws Exception {
        int databaseSizeBeforeTest = produtoRepository.findAll().size();
        // set the field null
        produto.setcProd(null);

        // Create the Produto, which fails.

        restProdutoMockMvc.perform(post("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produto)))
            .andExpect(status().isBadRequest());

        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdProdutoIsRequired() throws Exception {
        int databaseSizeBeforeTest = produtoRepository.findAll().size();
        // set the field null
        produto.setIdProduto(null);

        // Create the Produto, which fails.

        restProdutoMockMvc.perform(post("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produto)))
            .andExpect(status().isBadRequest());

        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkxProdIsRequired() throws Exception {
        int databaseSizeBeforeTest = produtoRepository.findAll().size();
        // set the field null
        produto.setxProd(null);

        // Create the Produto, which fails.

        restProdutoMockMvc.perform(post("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produto)))
            .andExpect(status().isBadRequest());

        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = produtoRepository.findAll().size();
        // set the field null
        produto.setIdEmitente(null);

        // Create the Produto, which fails.

        restProdutoMockMvc.perform(post("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produto)))
            .andExpect(status().isBadRequest());

        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVersaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = produtoRepository.findAll().size();
        // set the field null
        produto.setVersao(null);

        // Create the Produto, which fails.

        restProdutoMockMvc.perform(post("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produto)))
            .andExpect(status().isBadRequest());

        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProdutos() throws Exception {
        // Initialize the database
        produtoRepository.save(produto);

        // Get all the produtoList
        restProdutoMockMvc.perform(get("/api/produtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produto.getId())))
            .andExpect(jsonPath("$.[*].cProd").value(hasItem(DEFAULT_C_PROD.toString())))
            .andExpect(jsonPath("$.[*].idProduto").value(hasItem(DEFAULT_ID_PRODUTO)))
            .andExpect(jsonPath("$.[*].xProd").value(hasItem(DEFAULT_X_PROD.toString())))
            .andExpect(jsonPath("$.[*].cEAN").value(hasItem(DEFAULT_C_EAN.toString())))
            .andExpect(jsonPath("$.[*].nCM").value(hasItem(DEFAULT_N_CM.toString())))
            .andExpect(jsonPath("$.[*].exTipi").value(hasItem(DEFAULT_EX_TIPI.toString())))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO.toString())))
            .andExpect(jsonPath("$.[*].uCom").value(hasItem(DEFAULT_U_COM.toString())))
            .andExpect(jsonPath("$.[*].cEANTrib").value(hasItem(DEFAULT_C_EAN_TRIB.toString())))
            .andExpect(jsonPath("$.[*].uTrib").value(hasItem(DEFAULT_U_TRIB.toString())))
            .andExpect(jsonPath("$.[*].vUNCom").value(hasItem(DEFAULT_V_UN_COM.toString())))
            .andExpect(jsonPath("$.[*].vUNTrib").value(hasItem(DEFAULT_V_UN_TRIB.toString())))
            .andExpect(jsonPath("$.[*].qTrib").value(hasItem(DEFAULT_Q_TRIB.toString())))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].versao").value(hasItem(DEFAULT_VERSAO.toString())))
            .andExpect(jsonPath("$.[*].cest").value(hasItem(DEFAULT_CEST.toString())));
    }
    
    @Test
    public void getProduto() throws Exception {
        // Initialize the database
        produtoRepository.save(produto);

        // Get the produto
        restProdutoMockMvc.perform(get("/api/produtos/{id}", produto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(produto.getId()))
            .andExpect(jsonPath("$.cProd").value(DEFAULT_C_PROD.toString()))
            .andExpect(jsonPath("$.idProduto").value(DEFAULT_ID_PRODUTO))
            .andExpect(jsonPath("$.xProd").value(DEFAULT_X_PROD.toString()))
            .andExpect(jsonPath("$.cEAN").value(DEFAULT_C_EAN.toString()))
            .andExpect(jsonPath("$.nCM").value(DEFAULT_N_CM.toString()))
            .andExpect(jsonPath("$.exTipi").value(DEFAULT_EX_TIPI.toString()))
            .andExpect(jsonPath("$.genero").value(DEFAULT_GENERO.toString()))
            .andExpect(jsonPath("$.uCom").value(DEFAULT_U_COM.toString()))
            .andExpect(jsonPath("$.cEANTrib").value(DEFAULT_C_EAN_TRIB.toString()))
            .andExpect(jsonPath("$.uTrib").value(DEFAULT_U_TRIB.toString()))
            .andExpect(jsonPath("$.vUNCom").value(DEFAULT_V_UN_COM.toString()))
            .andExpect(jsonPath("$.vUNTrib").value(DEFAULT_V_UN_TRIB.toString()))
            .andExpect(jsonPath("$.qTrib").value(DEFAULT_Q_TRIB.toString()))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE))
            .andExpect(jsonPath("$.versao").value(DEFAULT_VERSAO.toString()))
            .andExpect(jsonPath("$.cest").value(DEFAULT_CEST.toString()));
    }

    @Test
    public void getNonExistingProduto() throws Exception {
        // Get the produto
        restProdutoMockMvc.perform(get("/api/produtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProduto() throws Exception {
        // Initialize the database
        produtoRepository.save(produto);

        int databaseSizeBeforeUpdate = produtoRepository.findAll().size();

        // Update the produto
        Produto updatedProduto = produtoRepository.findById(produto.getId()).get();
        updatedProduto
            .cProd(UPDATED_C_PROD)
            .idProduto(UPDATED_ID_PRODUTO)
            .xProd(UPDATED_X_PROD)
            .cEAN(UPDATED_C_EAN)
            .nCM(UPDATED_N_CM)
            .exTipi(UPDATED_EX_TIPI)
            .genero(UPDATED_GENERO)
            .uCom(UPDATED_U_COM)
            .cEANTrib(UPDATED_C_EAN_TRIB)
            .uTrib(UPDATED_U_TRIB)
            .vUNCom(UPDATED_V_UN_COM)
            .vUNTrib(UPDATED_V_UN_TRIB)
            .qTrib(UPDATED_Q_TRIB)
            .idEmitente(UPDATED_ID_EMITENTE)
            .versao(UPDATED_VERSAO)
            .cest(UPDATED_CEST);

        restProdutoMockMvc.perform(put("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduto)))
            .andExpect(status().isOk());

        // Validate the Produto in the database
        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeUpdate);
        Produto testProduto = produtoList.get(produtoList.size() - 1);
        assertThat(testProduto.getcProd()).isEqualTo(UPDATED_C_PROD);
        assertThat(testProduto.getIdProduto()).isEqualTo(UPDATED_ID_PRODUTO);
        assertThat(testProduto.getxProd()).isEqualTo(UPDATED_X_PROD);
        assertThat(testProduto.getcEAN()).isEqualTo(UPDATED_C_EAN);
        assertThat(testProduto.getnCM()).isEqualTo(UPDATED_N_CM);
        assertThat(testProduto.getExTipi()).isEqualTo(UPDATED_EX_TIPI);
        assertThat(testProduto.getGenero()).isEqualTo(UPDATED_GENERO);
        assertThat(testProduto.getuCom()).isEqualTo(UPDATED_U_COM);
        assertThat(testProduto.getcEANTrib()).isEqualTo(UPDATED_C_EAN_TRIB);
        assertThat(testProduto.getuTrib()).isEqualTo(UPDATED_U_TRIB);
        assertThat(testProduto.getvUNCom()).isEqualTo(UPDATED_V_UN_COM);
        assertThat(testProduto.getvUNTrib()).isEqualTo(UPDATED_V_UN_TRIB);
        assertThat(testProduto.getqTrib()).isEqualTo(UPDATED_Q_TRIB);
        assertThat(testProduto.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
        assertThat(testProduto.getVersao()).isEqualTo(UPDATED_VERSAO);
        assertThat(testProduto.getCest()).isEqualTo(UPDATED_CEST);

        // Validate the Produto in Elasticsearch
        verify(mockProdutoSearchRepository, times(1)).save(testProduto);
    }

    @Test
    public void updateNonExistingProduto() throws Exception {
        int databaseSizeBeforeUpdate = produtoRepository.findAll().size();

        // Create the Produto

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProdutoMockMvc.perform(put("/api/produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produto)))
            .andExpect(status().isBadRequest());

        // Validate the Produto in the database
        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Produto in Elasticsearch
        verify(mockProdutoSearchRepository, times(0)).save(produto);
    }

    @Test
    public void deleteProduto() throws Exception {
        // Initialize the database
        produtoRepository.save(produto);

        int databaseSizeBeforeDelete = produtoRepository.findAll().size();

        // Delete the produto
        restProdutoMockMvc.perform(delete("/api/produtos/{id}", produto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Produto> produtoList = produtoRepository.findAll();
        assertThat(produtoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Produto in Elasticsearch
        verify(mockProdutoSearchRepository, times(1)).deleteById(produto.getId());
    }

    @Test
    public void searchProduto() throws Exception {
        // Initialize the database
        produtoRepository.save(produto);
        when(mockProdutoSearchRepository.search(queryStringQuery("id:" + produto.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(produto), PageRequest.of(0, 1), 1));
        // Search the produto
        restProdutoMockMvc.perform(get("/api/_search/produtos?query=id:" + produto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produto.getId())))
            .andExpect(jsonPath("$.[*].cProd").value(hasItem(DEFAULT_C_PROD)))
            .andExpect(jsonPath("$.[*].idProduto").value(hasItem(DEFAULT_ID_PRODUTO)))
            .andExpect(jsonPath("$.[*].xProd").value(hasItem(DEFAULT_X_PROD)))
            .andExpect(jsonPath("$.[*].cEAN").value(hasItem(DEFAULT_C_EAN)))
            .andExpect(jsonPath("$.[*].nCM").value(hasItem(DEFAULT_N_CM)))
            .andExpect(jsonPath("$.[*].exTipi").value(hasItem(DEFAULT_EX_TIPI)))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO)))
            .andExpect(jsonPath("$.[*].uCom").value(hasItem(DEFAULT_U_COM)))
            .andExpect(jsonPath("$.[*].cEANTrib").value(hasItem(DEFAULT_C_EAN_TRIB)))
            .andExpect(jsonPath("$.[*].uTrib").value(hasItem(DEFAULT_U_TRIB)))
            .andExpect(jsonPath("$.[*].vUNCom").value(hasItem(DEFAULT_V_UN_COM)))
            .andExpect(jsonPath("$.[*].vUNTrib").value(hasItem(DEFAULT_V_UN_TRIB)))
            .andExpect(jsonPath("$.[*].qTrib").value(hasItem(DEFAULT_Q_TRIB)))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].versao").value(hasItem(DEFAULT_VERSAO)))
            .andExpect(jsonPath("$.[*].cest").value(hasItem(DEFAULT_CEST)));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Produto.class);
        Produto produto1 = new Produto();
        produto1.setId("id1");
        Produto produto2 = new Produto();
        produto2.setId(produto1.getId());
        assertThat(produto1).isEqualTo(produto2);
        produto2.setId("id2");
        assertThat(produto1).isNotEqualTo(produto2);
        produto1.setId(null);
        assertThat(produto1).isNotEqualTo(produto2);
    }
}
