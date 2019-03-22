package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.CertificadoInfo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the CertificadoInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertificadoInfoRepository extends MongoRepository<CertificadoInfo, String> {

}
