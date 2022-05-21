package br.ead.axon.messages.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderShippedEvent {

    private final String orderId; 

}