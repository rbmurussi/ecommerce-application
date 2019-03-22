package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Cliente;
import com.rbmurussi.ecommerce.application.repository.ClienteRepository;
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
 * Test class for the ClienteResource REST controller.
 *
 * @see ClienteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class ClienteResourceIntTest {

    private static final Integer DEFAULT_TP_DOCUMENTO_ENUM = 1;
    private static final Integer UPDATED_TP_DOCUMENTO_ENUM = 2;

    private static final Integer DEFAULT_ID_CLIENTE = 1;
    private static final Integer UPDATED_ID_CLIENTE = 2;

    private static final String DEFAULT_NR_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NR_DOCUMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_X_NOME = "AAAAAAAAAA";
    private static final String UPDATED_X_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_X_LGR = "AAAAAAAAAA";
    private static final String UPDATED_X_LGR = "BBBBBBBBBB";

    private static final String DEFAULT_N_RO = "AAAAAAAAAA";
    private static final String UPDATED_N_RO = "BBBBBBBBBB";

    private static final String DEFAULT_X_CPL = "AAAAAAAAAA";
    private static final String UPDATED_X_CPL = "BBBBBBBBBB";

    private static final String DEFAULT_X_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_X_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_X_MUN = "AAAAAAAAAA";
    private static final String UPDATED_X_MUN = "BBBBBBBBBB";

    private static final String DEFAULT_C_MUN = "AAAAAAA";
    private static final String UPDATED_C_MUN = "BBBBBBB";

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

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_EMITENTE = 1;
    private static final Integer UPDATED_ID_EMITENTE = 2;

    private static final String DEFAULT_I_E = "AAAAAAAAAA";
    private static final String UPDATED_I_E = "BBBBBBBBBB";

    private static final String DEFAULT_I_ESUF = "AAAAAAAAA";
    private static final String UPDATED_I_ESUF = "BBBBBBBBB";

    private static final String DEFAULT_VERSAO = "AAAAAAAAAA";
    private static final String UPDATED_VERSAO = "BBBBBBBBBB";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restClienteMockMvc;

    private Cliente cliente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClienteResource clienteResource = new ClienteResource(clienteRepository);
        this.restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteResource)
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
    public static Cliente createEntity() {
        Cliente cliente = new Cliente()
            .tpDocumentoEnum(DEFAULT_TP_DOCUMENTO_ENUM)
            .idCliente(DEFAULT_ID_CLIENTE)
            .nrDocumento(DEFAULT_NR_DOCUMENTO)
            .xNome(DEFAULT_X_NOME)
            .xLgr(DEFAULT_X_LGR)
            .nRo(DEFAULT_N_RO)
            .xCpl(DEFAULT_X_CPL)
            .xBairro(DEFAULT_X_BAIRRO)
            .xMun(DEFAULT_X_MUN)
            .cMun(DEFAULT_C_MUN)
            .uF(DEFAULT_U_F)
            .cEP(DEFAULT_C_EP)
            .cPais(DEFAULT_C_PAIS)
            .xPais(DEFAULT_X_PAIS)
            .fone(DEFAULT_FONE)
            .email(DEFAULT_EMAIL)
            .idEmitente(DEFAULT_ID_EMITENTE)
            .iE(DEFAULT_I_E)
            .iESUF(DEFAULT_I_ESUF)
            .versao(DEFAULT_VERSAO);
        return cliente;
    }

    @Before
    public void initTest() {
        clienteRepository.deleteAll();
        cliente = createEntity();
    }

    @Test
    public void createCliente() throws Exception {
        int databaseSizeBeforeCreate = clienteRepository.findAll().size();

        // Create the Cliente
        restClienteMockMvc.perform(post("/api/clientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isCreated());

        // Validate the Cliente in the database
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeCreate + 1);
        Cliente testCliente = clienteList.get(clienteList.size() - 1);
        assertThat(testCliente.getTpDocumentoEnum()).isEqualTo(DEFAULT_TP_DOCUMENTO_ENUM);
        assertThat(testCliente.getIdCliente()).isEqualTo(DEFAULT_ID_CLIENTE);
        assertThat(testCliente.getNrDocumento()).isEqualTo(DEFAULT_NR_DOCUMENTO);
        assertThat(testCliente.getxNome()).isEqualTo(DEFAULT_X_NOME);
        assertThat(testCliente.getxLgr()).isEqualTo(DEFAULT_X_LGR);
        assertThat(testCliente.getnRo()).isEqualTo(DEFAULT_N_RO);
        assertThat(testCliente.getxCpl()).isEqualTo(DEFAULT_X_CPL);
        assertThat(testCliente.getxBairro()).isEqualTo(DEFAULT_X_BAIRRO);
        assertThat(testCliente.getxMun()).isEqualTo(DEFAULT_X_MUN);
        assertThat(testCliente.getcMun()).isEqualTo(DEFAULT_C_MUN);
        assertThat(testCliente.getuF()).isEqualTo(DEFAULT_U_F);
        assertThat(testCliente.getcEP()).isEqualTo(DEFAULT_C_EP);
        assertThat(testCliente.getcPais()).isEqualTo(DEFAULT_C_PAIS);
        assertThat(testCliente.getxPais()).isEqualTo(DEFAULT_X_PAIS);
        assertThat(testCliente.getFone()).isEqualTo(DEFAULT_FONE);
        assertThat(testCliente.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCliente.getIdEmitente()).isEqualTo(DEFAULT_ID_EMITENTE);
        assertThat(testCliente.getiE()).isEqualTo(DEFAULT_I_E);
        assertThat(testCliente.getiESUF()).isEqualTo(DEFAULT_I_ESUF);
        assertThat(testCliente.getVersao()).isEqualTo(DEFAULT_VERSAO);
    }

    @Test
    public void createClienteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clienteRepository.findAll().size();

        // Create the Cliente with an existing ID
        cliente.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restClienteMockMvc.perform(post("/api/clientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        // Validate the Cliente in the database
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdClienteIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setIdCliente(null);

        // Create the Cliente, which fails.

        restClienteMockMvc.perform(post("/api/clientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkxNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setxNome(null);

        // Create the Cliente, which fails.

        restClienteMockMvc.perform(post("/api/clientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdEmitenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setIdEmitente(null);

        // Create the Cliente, which fails.

        restClienteMockMvc.perform(post("/api/clientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVersaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = clienteRepository.findAll().size();
        // set the field null
        cliente.setVersao(null);

        // Create the Cliente, which fails.

        restClienteMockMvc.perform(post("/api/clientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllClientes() throws Exception {
        // Initialize the database
        clienteRepository.save(cliente);

        // Get all the clienteList
        restClienteMockMvc.perform(get("/api/clientes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cliente.getId())))
            .andExpect(jsonPath("$.[*].tpDocumentoEnum").value(hasItem(DEFAULT_TP_DOCUMENTO_ENUM)))
            .andExpect(jsonPath("$.[*].idCliente").value(hasItem(DEFAULT_ID_CLIENTE)))
            .andExpect(jsonPath("$.[*].nrDocumento").value(hasItem(DEFAULT_NR_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].xNome").value(hasItem(DEFAULT_X_NOME.toString())))
            .andExpect(jsonPath("$.[*].xLgr").value(hasItem(DEFAULT_X_LGR.toString())))
            .andExpect(jsonPath("$.[*].nRo").value(hasItem(DEFAULT_N_RO.toString())))
            .andExpect(jsonPath("$.[*].xCpl").value(hasItem(DEFAULT_X_CPL.toString())))
            .andExpect(jsonPath("$.[*].xBairro").value(hasItem(DEFAULT_X_BAIRRO.toString())))
            .andExpect(jsonPath("$.[*].xMun").value(hasItem(DEFAULT_X_MUN.toString())))
            .andExpect(jsonPath("$.[*].cMun").value(hasItem(DEFAULT_C_MUN.toString())))
            .andExpect(jsonPath("$.[*].uF").value(hasItem(DEFAULT_U_F.toString())))
            .andExpect(jsonPath("$.[*].cEP").value(hasItem(DEFAULT_C_EP.toString())))
            .andExpect(jsonPath("$.[*].cPais").value(hasItem(DEFAULT_C_PAIS.toString())))
            .andExpect(jsonPath("$.[*].xPais").value(hasItem(DEFAULT_X_PAIS.toString())))
            .andExpect(jsonPath("$.[*].fone").value(hasItem(DEFAULT_FONE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].idEmitente").value(hasItem(DEFAULT_ID_EMITENTE)))
            .andExpect(jsonPath("$.[*].iE").value(hasItem(DEFAULT_I_E.toString())))
            .andExpect(jsonPath("$.[*].iESUF").value(hasItem(DEFAULT_I_ESUF.toString())))
            .andExpect(jsonPath("$.[*].versao").value(hasItem(DEFAULT_VERSAO.toString())));
    }
    
    @Test
    public void getCliente() throws Exception {
        // Initialize the database
        clienteRepository.save(cliente);

        // Get the cliente
        restClienteMockMvc.perform(get("/api/clientes/{id}", cliente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cliente.getId()))
            .andExpect(jsonPath("$.tpDocumentoEnum").value(DEFAULT_TP_DOCUMENTO_ENUM))
            .andExpect(jsonPath("$.idCliente").value(DEFAULT_ID_CLIENTE))
            .andExpect(jsonPath("$.nrDocumento").value(DEFAULT_NR_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.xNome").value(DEFAULT_X_NOME.toString()))
            .andExpect(jsonPath("$.xLgr").value(DEFAULT_X_LGR.toString()))
            .andExpect(jsonPath("$.nRo").value(DEFAULT_N_RO.toString()))
            .andExpect(jsonPath("$.xCpl").value(DEFAULT_X_CPL.toString()))
            .andExpect(jsonPath("$.xBairro").value(DEFAULT_X_BAIRRO.toString()))
            .andExpect(jsonPath("$.xMun").value(DEFAULT_X_MUN.toString()))
            .andExpect(jsonPath("$.cMun").value(DEFAULT_C_MUN.toString()))
            .andExpect(jsonPath("$.uF").value(DEFAULT_U_F.toString()))
            .andExpect(jsonPath("$.cEP").value(DEFAULT_C_EP.toString()))
            .andExpect(jsonPath("$.cPais").value(DEFAULT_C_PAIS.toString()))
            .andExpect(jsonPath("$.xPais").value(DEFAULT_X_PAIS.toString()))
            .andExpect(jsonPath("$.fone").value(DEFAULT_FONE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.idEmitente").value(DEFAULT_ID_EMITENTE))
            .andExpect(jsonPath("$.iE").value(DEFAULT_I_E.toString()))
            .andExpect(jsonPath("$.iESUF").value(DEFAULT_I_ESUF.toString()))
            .andExpect(jsonPath("$.versao").value(DEFAULT_VERSAO.toString()));
    }

    @Test
    public void getNonExistingCliente() throws Exception {
        // Get the cliente
        restClienteMockMvc.perform(get("/api/clientes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCliente() throws Exception {
        // Initialize the database
        clienteRepository.save(cliente);

        int databaseSizeBeforeUpdate = clienteRepository.findAll().size();

        // Update the cliente
        Cliente updatedCliente = clienteRepository.findById(cliente.getId()).get();
        updatedCliente
            .tpDocumentoEnum(UPDATED_TP_DOCUMENTO_ENUM)
            .idCliente(UPDATED_ID_CLIENTE)
            .nrDocumento(UPDATED_NR_DOCUMENTO)
            .xNome(UPDATED_X_NOME)
            .xLgr(UPDATED_X_LGR)
            .nRo(UPDATED_N_RO)
            .xCpl(UPDATED_X_CPL)
            .xBairro(UPDATED_X_BAIRRO)
            .xMun(UPDATED_X_MUN)
            .cMun(UPDATED_C_MUN)
            .uF(UPDATED_U_F)
            .cEP(UPDATED_C_EP)
            .cPais(UPDATED_C_PAIS)
            .xPais(UPDATED_X_PAIS)
            .fone(UPDATED_FONE)
            .email(UPDATED_EMAIL)
            .idEmitente(UPDATED_ID_EMITENTE)
            .iE(UPDATED_I_E)
            .iESUF(UPDATED_I_ESUF)
            .versao(UPDATED_VERSAO);

        restClienteMockMvc.perform(put("/api/clientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCliente)))
            .andExpect(status().isOk());

        // Validate the Cliente in the database
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeUpdate);
        Cliente testCliente = clienteList.get(clienteList.size() - 1);
        assertThat(testCliente.getTpDocumentoEnum()).isEqualTo(UPDATED_TP_DOCUMENTO_ENUM);
        assertThat(testCliente.getIdCliente()).isEqualTo(UPDATED_ID_CLIENTE);
        assertThat(testCliente.getNrDocumento()).isEqualTo(UPDATED_NR_DOCUMENTO);
        assertThat(testCliente.getxNome()).isEqualTo(UPDATED_X_NOME);
        assertThat(testCliente.getxLgr()).isEqualTo(UPDATED_X_LGR);
        assertThat(testCliente.getnRo()).isEqualTo(UPDATED_N_RO);
        assertThat(testCliente.getxCpl()).isEqualTo(UPDATED_X_CPL);
        assertThat(testCliente.getxBairro()).isEqualTo(UPDATED_X_BAIRRO);
        assertThat(testCliente.getxMun()).isEqualTo(UPDATED_X_MUN);
        assertThat(testCliente.getcMun()).isEqualTo(UPDATED_C_MUN);
        assertThat(testCliente.getuF()).isEqualTo(UPDATED_U_F);
        assertThat(testCliente.getcEP()).isEqualTo(UPDATED_C_EP);
        assertThat(testCliente.getcPais()).isEqualTo(UPDATED_C_PAIS);
        assertThat(testCliente.getxPais()).isEqualTo(UPDATED_X_PAIS);
        assertThat(testCliente.getFone()).isEqualTo(UPDATED_FONE);
        assertThat(testCliente.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCliente.getIdEmitente()).isEqualTo(UPDATED_ID_EMITENTE);
        assertThat(testCliente.getiE()).isEqualTo(UPDATED_I_E);
        assertThat(testCliente.getiESUF()).isEqualTo(UPDATED_I_ESUF);
        assertThat(testCliente.getVersao()).isEqualTo(UPDATED_VERSAO);
    }

    @Test
    public void updateNonExistingCliente() throws Exception {
        int databaseSizeBeforeUpdate = clienteRepository.findAll().size();

        // Create the Cliente

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClienteMockMvc.perform(put("/api/clientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
            .andExpect(status().isBadRequest());

        // Validate the Cliente in the database
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCliente() throws Exception {
        // Initialize the database
        clienteRepository.save(cliente);

        int databaseSizeBeforeDelete = clienteRepository.findAll().size();

        // Delete the cliente
        restClienteMockMvc.perform(delete("/api/clientes/{id}", cliente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cliente> clienteList = clienteRepository.findAll();
        assertThat(clienteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cliente.class);
        Cliente cliente1 = new Cliente();
        cliente1.setId("id1");
        Cliente cliente2 = new Cliente();
        cliente2.setId(cliente1.getId());
        assertThat(cliente1).isEqualTo(cliente2);
        cliente2.setId("id2");
        assertThat(cliente1).isNotEqualTo(cliente2);
        cliente1.setId(null);
        assertThat(cliente1).isNotEqualTo(cliente2);
    }
}
