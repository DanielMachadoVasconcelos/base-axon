package br.ead.axon.model.querry;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAppointmentByIdQuery {
    private final String appointmentId;
}
