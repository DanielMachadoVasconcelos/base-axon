package br.ead.axon.model.requests;

import br.ead.axon.model.api.Location;
import br.ead.axon.model.api.Participant;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public final class BookAppointmentRequest {

    private final @FutureOrPresent ZonedDateTime startAt;
    private final @FutureOrPresent ZonedDateTime endAt;
    private final @NotNull Location location;
    private final @NotNull Set<Participant> participants;

}
