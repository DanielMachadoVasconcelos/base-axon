package br.ead.axon.repositories;

import br.ead.axon.query.model.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderRepository extends ElasticsearchRepository<Order, String> {

}
