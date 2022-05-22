package br.ead.axon.messages.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentConfirmedEvent {

    private final String appointmentId;
 
}