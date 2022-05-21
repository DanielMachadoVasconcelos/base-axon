package br.ead.axon.query.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Value
@AllArgsConstructor
@Document(indexName = "orders")
public final class Order {

    @Id
    private final String orderId;
    private final String productId;
    private final OrderStatus orderStatus;
}
