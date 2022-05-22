package br.ead.axon.messages.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class AppointmentRescheduleEvent {

    private final String appointmentId;
    private final ZonedDateTime startAt;
    private final ZonedDateTime endAt;

}