package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Emitente;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Emitente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmitenteRepository extends MongoRepository<Emitente, String> {

}
