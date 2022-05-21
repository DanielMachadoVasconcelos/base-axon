package br.ead.axon.service;

import br.ead.axon.messages.OrderAggregate;
import br.ead.axon.messages.commands.CreateOrderCommand;
import br.ead.axon.messages.events.OrderCreatedEvent;
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
        String productId = "Deluxe Chair";
        fixture.givenNoPriorActivity()
                .when(new CreateOrderCommand(orderId, productId))
                .expectEvents(new OrderCreatedEvent(orderId, productId));
    }
}
