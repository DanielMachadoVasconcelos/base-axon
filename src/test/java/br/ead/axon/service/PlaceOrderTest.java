package br.ead.axon.service;

import br.ead.axon.exceptions.UnconfirmedOrderException;
import br.ead.axon.messages.OrderAggregate;
import br.ead.axon.messages.commands.CreateOrderCommand;
import br.ead.axon.messages.commands.ShipOrderCommand;
import br.ead.axon.messages.events.OrderConfirmedEvent;
import br.ead.axon.messages.events.OrderCreatedEvent;
import br.ead.axon.messages.events.OrderShippedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PlaceOrderTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    void shouldPublishOrderCreatedEventWhenCreateOrderCommand() {
        String orderId = UUID.randomUUID().toString();
        String productId = "Deluxe Notebook";
        fixture.givenNoPriorActivity()
                .when(new CreateOrderCommand(orderId, productId))
                .expectEvents(new OrderCreatedEvent(orderId, productId));
    }

    @Test
    void shouldNotShipWhenOrderIsNotConfirmed() {
        String orderId = UUID.randomUUID().toString();
        String productId = "Deluxe Desktop Table";
        fixture.given(new OrderCreatedEvent(orderId, productId))
                .when(new ShipOrderCommand(orderId))
                .expectException(UnconfirmedOrderException.class);
    }

    @Test
    void shouldShipProductsWhenOrderIsConfirmed() {
        String orderId = UUID.randomUUID().toString();
        String productId = "Deluxe Chair";
        fixture.given(new OrderCreatedEvent(orderId, productId), new OrderConfirmedEvent(orderId))
                .when(new ShipOrderCommand(orderId))
                .expectEvents(new OrderShippedEvent(orderId));
    }
}
