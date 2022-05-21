package br.ead.axon.messages.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCreatedEvent {
 
    private final String orderId;
    private final String productId;
 
}