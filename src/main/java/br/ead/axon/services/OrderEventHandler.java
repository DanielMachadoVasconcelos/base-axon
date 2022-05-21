package br.ead.axon.services;

import br.ead.axon.messages.events.OrderConfirmedEvent;
import br.ead.axon.messages.events.OrderCreatedEvent;
import br.ead.axon.messages.events.OrderShippedEvent;
import br.ead.axon.query.FindAllOrderedProductsQuery;
import br.ead.axon.query.model.Order;
import br.ead.axon.query.model.OrderStatus;
import br.ead.axon.repositories.OrderRepository;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("Handling the OrderCreatedEvent [{}]", event);
        orderRepository.save(new Order(event.getOrderId(), event.getProductId(), OrderStatus.CREATED));
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        log.info("Handling the OrderConfirmedEvent [{}]", event);
        String orderId = event.getOrderId();
        orderRepository.findById(orderId)
                       .map(order -> new Order(order.getOrderId(), order.getProductId(), OrderStatus.CONFIRMED))
                       .map(orderRepository::save);
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        log.info("Handling the OrderShippedEvent [{}]", event);
        String orderId = event.getOrderId();
        orderRepository.findById(orderId)
                .map(order -> new Order(order.getOrderId(), order.getProductId(), OrderStatus.SHIPPED))
                .map(orderRepository::save);
    }

    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        log.info("Handling a query request for FindAllOrderedProductsQuery [{}]", query);
        return ImmutableList.copyOf(orderRepository.findAll());
    }
}
