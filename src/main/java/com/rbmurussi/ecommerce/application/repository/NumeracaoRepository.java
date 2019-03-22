package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Numeracao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Numeracao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NumeracaoRepository extends MongoRepository<Numeracao, String> {

}
