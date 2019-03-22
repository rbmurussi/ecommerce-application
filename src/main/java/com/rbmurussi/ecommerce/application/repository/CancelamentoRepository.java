package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Cancelamento;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Cancelamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CancelamentoRepository extends MongoRepository<Cancelamento, String> {

}
