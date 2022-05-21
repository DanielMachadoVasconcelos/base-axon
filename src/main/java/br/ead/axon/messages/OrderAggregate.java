package br.ead.axon.messages;

import br.ead.axon.exceptions.UnconfirmedOrderException;
import br.ead.axon.messages.commands.ConfirmOrderCommand;
import br.ead.axon.messages.commands.CreateOrderCommand;
import br.ead.axon.messages.commands.ShipOrderCommand;
import br.ead.axon.messages.events.OrderConfirmedEvent;
import br.ead.axon.messages.events.OrderCreatedEvent;
import br.ead.axon.messages.events.OrderShippedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Data
@Log4j2
@Aggregate
@AllArgsConstructor
@NoArgsConstructor
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;
    private boolean orderShipped;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        log.info("Received a CreateOrderCommand. OrderId = {}", command.getOrderId());
        apply(new OrderCreatedEvent(command.getOrderId(), command.getProductId()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        log.info("Received a ConfirmOrderCommand. OrderId = {}", command.getOrderId());
        if (orderConfirmed) {
            return;
        }
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        log.info("Received a ShipOrderCommand. OrderId = {}", command.getOrderId());
        if (!orderConfirmed) {
            throw new UnconfirmedOrderException("Order is not confirmed yet!");
        }
        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }
}
