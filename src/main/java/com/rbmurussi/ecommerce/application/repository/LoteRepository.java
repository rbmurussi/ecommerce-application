package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Lote;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Lote entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoteRepository extends MongoRepository<Lote, String> {

}
