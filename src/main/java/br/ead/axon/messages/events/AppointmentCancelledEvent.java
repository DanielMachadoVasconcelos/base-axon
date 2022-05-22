package br.ead.axon.messages.events;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AppointmentCancelledEvent {
    private final String appointmentId;
}
