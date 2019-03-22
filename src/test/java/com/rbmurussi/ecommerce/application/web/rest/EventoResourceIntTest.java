package com.rbmurussi.ecommerce.application.web.rest;

import com.rbmurussi.ecommerce.application.EcommerceApplicationApp;

import com.rbmurussi.ecommerce.application.domain.Evento;
import com.rbmurussi.ecommerce.application.repository.EventoRepository;
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
 * Test class for the EventoResource REST controller.
 *
 * @see EventoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplicationApp.class)
public class EventoResourceIntTest {

    private static final Long DEFAULT_ID_EVENTO = 1L;
    private static final Long UPDATED_ID_EVENTO = 2L;

    private static final Long DEFAULT_ID_NOTAL_FISCAL = 1L;
    private static final Long UPDATED_ID_NOTAL_FISCAL = 2L;

    private static final String DEFAULT_TP_EVENTO = "AAAAAAA";
    private static final String UPDATED_TP_EVENTO = "BBBBBBB";

    private static final Integer DEFAULT_N_SEQ_EVENTO = 1;
    private static final Integer UPDATED_N_SEQ_EVENTO = 2;

    private static final ZonedDateTime DEFAULT_DATA_EVENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_EVENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NUM_PROTOCOLO = "AAAAAAAAAA";
    private static final String UPDATED_NUM_PROTOCOLO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_XML_PROC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_XML_PROC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_XML_PROC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_XML_PROC_CONTENT_TYPE = "image/png";

    private static final ZonedDateTime DEFAULT_DATA_REG_EVENTO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_REG_EVENTO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restEventoMockMvc;

    private Evento evento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventoResource eventoResource = new EventoResource(eventoRepository);
        this.restEventoMockMvc = MockMvcBuilders.standaloneSetup(eventoResource)
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
    public static Evento createEntity() {
        Evento evento = new Evento()
            .idEvento(DEFAULT_ID_EVENTO)
            .idNotalFiscal(DEFAULT_ID_NOTAL_FISCAL)
            .tpEvento(DEFAULT_TP_EVENTO)
            .nSeqEvento(DEFAULT_N_SEQ_EVENTO)
            .dataEvento(DEFAULT_DATA_EVENTO)
            .numProtocolo(DEFAULT_NUM_PROTOCOLO)
            .xmlProc(DEFAULT_XML_PROC)
            .xmlProcContentType(DEFAULT_XML_PROC_CONTENT_TYPE)
            .dataRegEvento(DEFAULT_DATA_REG_EVENTO);
        return evento;
    }

    @Before
    public void initTest() {
        eventoRepository.deleteAll();
        evento = createEntity();
    }

    @Test
    public void createEvento() throws Exception {
        int databaseSizeBeforeCreate = eventoRepository.findAll().size();

        // Create the Evento
        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isCreated());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeCreate + 1);
        Evento testEvento = eventoList.get(eventoList.size() - 1);
        assertThat(testEvento.getIdEvento()).isEqualTo(DEFAULT_ID_EVENTO);
        assertThat(testEvento.getIdNotalFiscal()).isEqualTo(DEFAULT_ID_NOTAL_FISCAL);
        assertThat(testEvento.getTpEvento()).isEqualTo(DEFAULT_TP_EVENTO);
        assertThat(testEvento.getnSeqEvento()).isEqualTo(DEFAULT_N_SEQ_EVENTO);
        assertThat(testEvento.getDataEvento()).isEqualTo(DEFAULT_DATA_EVENTO);
        assertThat(testEvento.getNumProtocolo()).isEqualTo(DEFAULT_NUM_PROTOCOLO);
        assertThat(testEvento.getXmlProc()).isEqualTo(DEFAULT_XML_PROC);
        assertThat(testEvento.getXmlProcContentType()).isEqualTo(DEFAULT_XML_PROC_CONTENT_TYPE);
        assertThat(testEvento.getDataRegEvento()).isEqualTo(DEFAULT_DATA_REG_EVENTO);
    }

    @Test
    public void createEventoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventoRepository.findAll().size();

        // Create the Evento with an existing ID
        evento.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdEventoIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventoRepository.findAll().size();
        // set the field null
        evento.setIdEvento(null);

        // Create the Evento, which fails.

        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdNotalFiscalIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventoRepository.findAll().size();
        // set the field null
        evento.setIdNotalFiscal(null);

        // Create the Evento, which fails.

        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTpEventoIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventoRepository.findAll().size();
        // set the field null
        evento.setTpEvento(null);

        // Create the Evento, which fails.

        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checknSeqEventoIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventoRepository.findAll().size();
        // set the field null
        evento.setnSeqEvento(null);

        // Create the Evento, which fails.

        restEventoMockMvc.perform(post("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllEventos() throws Exception {
        // Initialize the database
        eventoRepository.save(evento);

        // Get all the eventoList
        restEventoMockMvc.perform(get("/api/eventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(evento.getId())))
            .andExpect(jsonPath("$.[*].idEvento").value(hasItem(DEFAULT_ID_EVENTO.intValue())))
            .andExpect(jsonPath("$.[*].idNotalFiscal").value(hasItem(DEFAULT_ID_NOTAL_FISCAL.intValue())))
            .andExpect(jsonPath("$.[*].tpEvento").value(hasItem(DEFAULT_TP_EVENTO.toString())))
            .andExpect(jsonPath("$.[*].nSeqEvento").value(hasItem(DEFAULT_N_SEQ_EVENTO)))
            .andExpect(jsonPath("$.[*].dataEvento").value(hasItem(sameInstant(DEFAULT_DATA_EVENTO))))
            .andExpect(jsonPath("$.[*].numProtocolo").value(hasItem(DEFAULT_NUM_PROTOCOLO.toString())))
            .andExpect(jsonPath("$.[*].xmlProcContentType").value(hasItem(DEFAULT_XML_PROC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].xmlProc").value(hasItem(Base64Utils.encodeToString(DEFAULT_XML_PROC))))
            .andExpect(jsonPath("$.[*].dataRegEvento").value(hasItem(sameInstant(DEFAULT_DATA_REG_EVENTO))));
    }
    
    @Test
    public void getEvento() throws Exception {
        // Initialize the database
        eventoRepository.save(evento);

        // Get the evento
        restEventoMockMvc.perform(get("/api/eventos/{id}", evento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(evento.getId()))
            .andExpect(jsonPath("$.idEvento").value(DEFAULT_ID_EVENTO.intValue()))
            .andExpect(jsonPath("$.idNotalFiscal").value(DEFAULT_ID_NOTAL_FISCAL.intValue()))
            .andExpect(jsonPath("$.tpEvento").value(DEFAULT_TP_EVENTO.toString()))
            .andExpect(jsonPath("$.nSeqEvento").value(DEFAULT_N_SEQ_EVENTO))
            .andExpect(jsonPath("$.dataEvento").value(sameInstant(DEFAULT_DATA_EVENTO)))
            .andExpect(jsonPath("$.numProtocolo").value(DEFAULT_NUM_PROTOCOLO.toString()))
            .andExpect(jsonPath("$.xmlProcContentType").value(DEFAULT_XML_PROC_CONTENT_TYPE))
            .andExpect(jsonPath("$.xmlProc").value(Base64Utils.encodeToString(DEFAULT_XML_PROC)))
            .andExpect(jsonPath("$.dataRegEvento").value(sameInstant(DEFAULT_DATA_REG_EVENTO)));
    }

    @Test
    public void getNonExistingEvento() throws Exception {
        // Get the evento
        restEventoMockMvc.perform(get("/api/eventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEvento() throws Exception {
        // Initialize the database
        eventoRepository.save(evento);

        int databaseSizeBeforeUpdate = eventoRepository.findAll().size();

        // Update the evento
        Evento updatedEvento = eventoRepository.findById(evento.getId()).get();
        updatedEvento
            .idEvento(UPDATED_ID_EVENTO)
            .idNotalFiscal(UPDATED_ID_NOTAL_FISCAL)
            .tpEvento(UPDATED_TP_EVENTO)
            .nSeqEvento(UPDATED_N_SEQ_EVENTO)
            .dataEvento(UPDATED_DATA_EVENTO)
            .numProtocolo(UPDATED_NUM_PROTOCOLO)
            .xmlProc(UPDATED_XML_PROC)
            .xmlProcContentType(UPDATED_XML_PROC_CONTENT_TYPE)
            .dataRegEvento(UPDATED_DATA_REG_EVENTO);

        restEventoMockMvc.perform(put("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEvento)))
            .andExpect(status().isOk());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeUpdate);
        Evento testEvento = eventoList.get(eventoList.size() - 1);
        assertThat(testEvento.getIdEvento()).isEqualTo(UPDATED_ID_EVENTO);
        assertThat(testEvento.getIdNotalFiscal()).isEqualTo(UPDATED_ID_NOTAL_FISCAL);
        assertThat(testEvento.getTpEvento()).isEqualTo(UPDATED_TP_EVENTO);
        assertThat(testEvento.getnSeqEvento()).isEqualTo(UPDATED_N_SEQ_EVENTO);
        assertThat(testEvento.getDataEvento()).isEqualTo(UPDATED_DATA_EVENTO);
        assertThat(testEvento.getNumProtocolo()).isEqualTo(UPDATED_NUM_PROTOCOLO);
        assertThat(testEvento.getXmlProc()).isEqualTo(UPDATED_XML_PROC);
        assertThat(testEvento.getXmlProcContentType()).isEqualTo(UPDATED_XML_PROC_CONTENT_TYPE);
        assertThat(testEvento.getDataRegEvento()).isEqualTo(UPDATED_DATA_REG_EVENTO);
    }

    @Test
    public void updateNonExistingEvento() throws Exception {
        int databaseSizeBeforeUpdate = eventoRepository.findAll().size();

        // Create the Evento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventoMockMvc.perform(put("/api/eventos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(evento)))
            .andExpect(status().isBadRequest());

        // Validate the Evento in the database
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEvento() throws Exception {
        // Initialize the database
        eventoRepository.save(evento);

        int databaseSizeBeforeDelete = eventoRepository.findAll().size();

        // Delete the evento
        restEventoMockMvc.perform(delete("/api/eventos/{id}", evento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Evento> eventoList = eventoRepository.findAll();
        assertThat(eventoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Evento.class);
        Evento evento1 = new Evento();
        evento1.setId("id1");
        Evento evento2 = new Evento();
        evento2.setId(evento1.getId());
        assertThat(evento1).isEqualTo(evento2);
        evento2.setId("id2");
        assertThat(evento1).isNotEqualTo(evento2);
        evento1.setId(null);
        assertThat(evento1).isNotEqualTo(evento2);
    }
}
