package br.ead.axon.repositories;

import br.ead.axon.model.entities.Appointment;
import br.ead.axon.model.entities.AppointmentStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AppointmentRepository extends ElasticsearchRepository<Appointment, String> {

    List<Appointment> findAppointmentByAppointmentStatusIsNot(AppointmentStatus appointmentStatus);

}
