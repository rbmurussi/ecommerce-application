package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Inutilizacao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Inutilizacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InutilizacaoRepository extends MongoRepository<Inutilizacao, String> {

}
