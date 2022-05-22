package br.ead.axon.messages.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class ConfirmAppointmentCommand {
 
    @TargetAggregateIdentifier
    private final String appointmentId;

}