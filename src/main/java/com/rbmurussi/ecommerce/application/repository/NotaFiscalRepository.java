package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.NotaFiscal;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the NotaFiscal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotaFiscalRepository extends MongoRepository<NotaFiscal, String> {

}
