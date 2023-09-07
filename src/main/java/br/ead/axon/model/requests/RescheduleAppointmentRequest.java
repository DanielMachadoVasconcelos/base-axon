package br.ead.axon.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.FutureOrPresent;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public final class RescheduleAppointmentRequest {

    private final @FutureOrPresent ZonedDateTime startAt;
    private final @FutureOrPresent ZonedDateTime endAt;

}
