package br.ead.axon.repositories;

import br.ead.axon.model.entities.Appointment;
import br.ead.axon.model.entities.AppointmentStatus;

import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

public interface AppointmentRepository extends ListCrudRepository<Appointment, String> {

    List<Appointment> findAppointmentByAppointmentStatusIsNot(AppointmentStatus appointmentStatus);

}
