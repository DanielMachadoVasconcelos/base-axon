package br.ead.axon.messages.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class ConfirmOrderCommand {
 
    @TargetAggregateIdentifier
    private final String orderId;
    
}