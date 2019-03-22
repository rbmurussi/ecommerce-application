package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Transportadora;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Transportadora entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportadoraRepository extends MongoRepository<Transportadora, String> {

}
