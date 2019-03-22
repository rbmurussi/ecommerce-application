package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Parametros;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Parametros entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParametrosRepository extends MongoRepository<Parametros, String> {

}
