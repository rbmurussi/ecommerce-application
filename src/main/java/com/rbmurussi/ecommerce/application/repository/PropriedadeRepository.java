package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Propriedade;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Propriedade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropriedadeRepository extends MongoRepository<Propriedade, String> {

}
