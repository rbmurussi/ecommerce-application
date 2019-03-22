package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Controleversao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Controleversao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ControleversaoRepository extends MongoRepository<Controleversao, String> {

}
