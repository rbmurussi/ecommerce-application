package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Pesquisa;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Pesquisa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PesquisaRepository extends MongoRepository<Pesquisa, String> {

}
