package br.ead.axon.messages.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class RescheduleAppointmentCommand {
 
    @TargetAggregateIdentifier
    private final String appointmentId;

    private final ZonedDateTime startAt;
    private final ZonedDateTime endAt;
 
}