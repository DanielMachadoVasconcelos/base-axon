package br.ead.axon.messages.commands;

import br.ead.axon.model.api.Location;
import br.ead.axon.model.api.Participant;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class BookAppointmentCommand {
 
    @TargetAggregateIdentifier
    private final String appointmentId;

    private final ZonedDateTime startAt;
    private final ZonedDateTime endAt;
    private final Location location;
    private final Set<Participant> participants;

}