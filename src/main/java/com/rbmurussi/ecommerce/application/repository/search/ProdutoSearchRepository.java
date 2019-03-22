package com.rbmurussi.ecommerce.application.repository.search;

import com.rbmurussi.ecommerce.application.domain.Produto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Produto entity.
 */
public interface ProdutoSearchRepository extends ElasticsearchRepository<Produto, String> {
}
