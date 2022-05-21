package br.ead.axon.controllers;

import br.ead.axon.messages.commands.ConfirmOrderCommand;
import br.ead.axon.messages.commands.CreateOrderCommand;
import br.ead.axon.messages.commands.ShipOrderCommand;
import br.ead.axon.query.FindAllOrderedProductsQuery;
import br.ead.axon.query.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/place")
    public CompletableFuture<Void> placeOrder() {
        String orderId = UUID.randomUUID().toString();
        log.info("Received a Http request for place order. OrderId = {}", orderId);
        return commandGateway.send(new CreateOrderCommand(orderId, "Deluxe Chair"));
    }

    @PutMapping("{orderId}/confirm")
    public CompletableFuture<Void> confirmOrder(@PathVariable String orderId) {
        log.info("Received a Http request for confirm order. OrderId = {}", orderId);
        return commandGateway.send(new ConfirmOrderCommand(orderId));
    }

    @PutMapping("{orderId}/ship")
    public CompletableFuture<Void> shipOrder(@PathVariable String orderId) {
        log.info("Received a Http request for ship order. OrderId = {}", orderId);
        return commandGateway.send(new ShipOrderCommand(orderId));
    }

    @GetMapping("/")
    public CompletableFuture<List<Order>> findAllOrders() {
        log.info("Received a Http request for list all orders");
        return queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(Order.class));
    }
}

