package br.ead.axon.messages.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class CancelAppointmentCommand {

    @TargetAggregateIdentifier
    private final String appointmentId;

}
